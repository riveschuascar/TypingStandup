# Plan General

## Fase 1 — Finalizar Signup

### Objetivo

Completar la arquitectura limpia del módulo Signup conectando Presentación → Dominio → Datos.

---

### 1.1 Analizar implementación actual

**Tareas**

* Revisar estructura actual de `signup`.
* Identificar qué existe en:

  * presentation
  * domain
  * data
* Verificar modelos utilizados.
* Identificar dependencias con Firebase.

**Entregables**

* Mapa de arquitectura actual.
* Lista de clases faltantes.

---

### 1.2 Implementar capa Domain

**Tareas**

Crear:

```text
signup/
 └─ domain/
     ├─ model/
     ├─ repository/
     ├─ usecase/
```

Implementar:

```kotlin
interface SignupRepository
```

Casos de uso:

```kotlin
CreateUserUseCase
ValidateSignupUseCase
SavePendingSignupUseCase
```

Modelos de dominio:

```kotlin
User
SignupData
SignupResult
```

**Criterios**

* Domain no debe depender de Firebase.
* Domain no debe depender de Room.
* Solo interfaces y lógica de negocio.

---

### 1.3 Implementar capa Data

#### Firestore

Crear datasource:

```kotlin
SignupFirestoreDataSource
```

Responsabilidades:

* Crear documento de usuario.
* Actualizar usuario si existe.
* Manejar errores.

#### Room

Crear datasource:

```kotlin
SignupLocalDataSource
```

Responsabilidades:

* Persistir usuario registrado localmente.
* Recuperar usuario.

#### Repository

Implementar:

```kotlin
SignupRepositoryImpl
```

Flujo:

```text
Signup
   ↓
Firestore
   ↓ éxito
Room
   ↓
Result.Success
```

Si Firestore falla:

```text
Firestore
   ↓ error
Result.Error
```

No guardar en Room cuando falle el registro remoto.

---

### 1.4 Conectar Presentation

Revisar ViewModel actual.

Implementar:

#### Eventos

```kotlin
sealed interface SignupEvent
```

Ejemplo:

```kotlin
OnNameChanged
OnEmailChanged
OnPasswordChanged
OnSignupClicked
```

---

#### Estado

```kotlin
data class SignupState
```

Ejemplo:

```kotlin
isLoading
name
email
password
error
```

---

#### Efectos

```kotlin
sealed interface SignupEffect
```

Ejemplo:

```kotlin
NavigateNext
ShowError
```

---

#### ViewModel

Responsabilidades:

* Procesar eventos.
* Ejecutar UseCases.
* Actualizar State.
* Emitir Effects.

Flujo:

```text
UI
 ↓
Event
 ↓
ViewModel
 ↓
UseCase
 ↓
Repository
 ↓
Firestore
 ↓
Room
 ↓
Effect
```

**Criterios de aceptación**

* Signup completo funcionando.
* Firestore persiste usuario.
* Room guarda usuario tras éxito.
* Navegación disparada mediante Effect.
* Sin llamadas directas a Firebase desde UI.

---

# Fase 2 — Refactorizar Onboard

### Objetivo

Validar migración Android → Common y soportar Remote Config.

---

### 2.1 Auditoría del traslado

Revisar:

```text
androidMain/onboard
        ↓
commonMain/onboard
```

Verificar:

* Imports rotos.
* Dependencias Android.
* Context.
* Recursos.
* ViewModels.
* Navegación.

Buscar:

```kotlin
android.*
androidx.lifecycle.*
Context
Activity
```

---

### 2.2 Adaptar código multiplataforma

Mover cualquier lógica Android específica a:

```text
androidMain
```

Mantener en common:

```text
UI
State
Events
Effects
ViewModel
UseCases
```

---

### 2.3 ViewModel para Remote Config

Crear:

```kotlin
OnboardingViewModel
```

Estados:

```kotlin
Loading
Success
Error
```

Datasource:

```kotlin
RemoteConfigDataSource
```

Repositorio:

```kotlin
OnboardingRepository
```

Caso de uso:

```kotlin
GetOnboardingConfigUseCase
```

Flujo:

```text
ViewModel -> UseCase -> Repository -> RemoteConfig -> UI
```

---

### Criterios

* Onboarding 100% funcional desde commonMain.
* Datos obtenidos desde Remote Config.
* UI espera correctamente carga remota.

---

# Fase 3 — Inyección de Dependencias (Koin)

### Objetivo

Registrar todos los módulos faltantes.

---

### 3.1 Auditar carpeta DI

Revisar:

```text
commonMain/di/
```

Los 4 archivos existentes.

Identificar:

* módulos definidos
* módulos faltantes
* dependencias sin registrar

---

### 3.2 Registrar Signup

Agregar:

```kotlin
single<SignupRepository>
factory<CreateUserUseCase>()
factory<ValidateSignupUseCase>()
viewModel<SignupViewModel>()
```

Registrar:

```kotlin
SignupFirestoreDataSource
SignupLocalDataSource
```

---

### 3.3 Registrar Onboarding

Agregar:

```kotlin
RemoteConfigDataSource
OnboardingRepository
GetOnboardingConfigUseCase
OnboardingViewModel
```

---

### 3.4 Verificar módulos platform

Confirmar que:

```kotlin
RoomDatabase
```

sigue siendo creado desde:

```text
androidMain
```

y expuesto a common mediante DI.

---

### Criterios

* Koin inicia sin errores.
* Todas las dependencias resuelven correctamente.
* Ningún ViewModel se crea manualmente.

---

# Fase 4 — Navegación

### Objetivo

Integrar Signup y Onboarding en el sistema actual.

---

### 4.1 Revisar carpeta navigation

Archivos existentes:

```text
navigation/
 ├─ Routes
 └─ NavigationGraph
```

Validar:

* rutas registradas
* parámetros
* deep links (si existen)

---

### 4.2 Integrar Onboarding

Agregar ruta:

```kotlin
OnboardingRoute
```

Registrar pantalla.

---

### 4.3 Integrar Signup

Agregar:

```kotlin
SignupRoute
```

Registrar pantalla.

---

### 4.4 Conectar Effects → Navigation

Ejemplo:

```kotlin
SignupEffect.NavigateNext
```

debe disparar:

```kotlin
navigator.navigate(...)
```

sin navegación directa desde ViewModel.

---

### Criterios

* Onboarding → Signup → siguiente pantalla.
* Navegación controlada por Effects.
* Sin dependencias de navegación dentro del dominio.

---

# Orden recomendado para el agente

1. Auditar Signup actual.
2. Implementar Domain de Signup.
3. Implementar Data de Signup (Firestore + Room).
4. Conectar Presentation.
5. Probar flujo completo Signup.
6. Auditar Onboarding movido a commonMain.
7. Crear ViewModel + Remote Config.
8. Revisar y completar módulos Koin.
9. Integrar navegación.
10. Ejecutar validación final de arquitectura KMP.

**Resultado esperado:** Signup funcional end-to-end, Onboarding migrado correctamente a commonMain con Remote Config, DI centralizada en Koin y navegación completamente integrada.
