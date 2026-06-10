Puedes plantearlo como una especificación de trabajo para un agente de desarrollo:

# Objetivo

Implementar la capa **Domain** y **Data** de una nueva feature siguiendo **Clean Architecture** en un proyecto móvil (Kotlin Multiplatform), conectar la capa **Presentation** ya existente con estas capas, registrar la feature en navegación y configurar la inyección de dependencias.

La estructura objetivo es:

```text
feature/
├── data/
├── domain/
└── presentation/
```

---

# Contexto del proyecto

* La capa `presentation/` ya existe y contiene las pantallas y ViewModels necesarios.
* Existe una base de datos local Room:

  * Constructor Android en `androidMain`.
  * Constructor compartido y definición de tablas en `commonMain`.
* Actualmente no existe autenticación funcional (signup/login).
* Para pruebas y demo se pueden generar datos mock o aleatorios.
* La fuente principal de datos será la caché local (Room).
* No se requiere integración con backend real.

---

# Responsabilidades del agente

## 1. Analizar la feature existente

Revisar:

```text
feature/presentation/
```

Identificar:

* Pantallas.
* ViewModels.
* Estados (`UiState`)
* Modelos utilizados por la UI.

Determinar qué datos necesita realmente la presentación.

Implementar:
* Eventos.
* Efectos.

Segun la funcionalidad y la integracion con la capa de datos.

ejemplos:
```sealed interface SignUpEvent {
    data class UsernameChanged(
        val value: String
    ) : SignUpEvent
}

sealed interface SignUpEffect {
    data class ShowError(
        val message: String
    ) : SignUpEffect

    data object NavigateToHome : SignUpEffect
}
```
---

## 2. Crear la capa Domain

Crear la estructura:

```text
feature/domain/
├── model/
├── repository/
└── usecase/
```

### Modelos de dominio

Definir entidades puras de negocio.

Ejemplo:

```kotlin
data class Item(
    val id: String,
    val title: String,
    val description: String
)
```

### Repositorio

Crear contrato:

```kotlin
interface FeatureRepository {
    suspend fun getItems(): List<Item>
}
```

### Casos de uso

Crear los casos de uso necesarios.

Ejemplo:

```kotlin
class GetItemsUseCase(
    private val repository: FeatureRepository
) {
    suspend operator fun invoke() =
        repository.getItems()
}
```

Regla:

* La capa Domain no debe depender de Data.
* Solo interfaces y lógica de negocio.

---

## 3. Crear la capa Data

Crear estructura:

```text
feature/data/
├── datasource/
├── local/
├── mapper/
└── repository/
```

### Entidades Room

Utilizar las tablas ya registradas en el constructor común.

Si la entidad no existe:

```kotlin
@Entity
data class ItemEntity(...)
```

### DAO

Crear DAO necesarios:

```kotlin
@Dao
interface ItemDao {
    @Query(...)
    suspend fun getAll(): List<ItemEntity>

    @Insert(...)
    suspend fun insertAll(...)
}
```

### Mappers

Implementar:

```kotlin
ItemEntity -> Item
Item -> ItemEntity
```

### DataSource local

```kotlin
class LocalItemDataSource(
    private val dao: ItemDao
)
```

### Implementación del repositorio

```kotlin
class FeatureRepositoryImpl(
    private val localDataSource: LocalItemDataSource
) : FeatureRepository
```

Flujo:

1. Consultar Room.
2. Si existen datos:

   * devolverlos.
3. Si no existen:

   * generar datos demo/mock.
   * persistirlos.
   * devolverlos.

Ejemplo:

```text
Room -> datos encontrados -> retornar

Room vacío ->
generar mocks ->
guardar ->
retornar
```

---

## 4. Generación de datos demo

Mientras no exista login/backend:

Crear un proveedor:

```kotlin
object FeatureFakeDataProvider
```

Generar:

```kotlin
List(10) {
    Item(...)
}
```

Los datos deben:

* Ser consistentes.
* Tener IDs únicos.
* Permitir probar la UI.

Ejemplo:

```text
Usuario Demo 1
Usuario Demo 2
Usuario Demo 3
...
```

---

## 5. Conectar Presentation con Domain

Modificar ViewModels existentes.

Antes:

```kotlin
class FeatureViewModel
```

Después:

```kotlin
class FeatureViewModel(
    private val getItemsUseCase: GetItemsUseCase
)
```

Reemplazar cualquier mock interno por:

```kotlin
getItemsUseCase()
```

Mantener intactos:

* UiState.
* Eventos.
* Navegación interna.

La UI no debe conocer Room ni DataSource.

---

## 6. Registrar dependencias en DI

Agregar la feature en:

```text
di/
```

Crear módulo:

```kotlin
featureModule
```

Registrar:

### DAO

```kotlin
factory { get<AppDatabase>().itemDao() }
```

### DataSource

```kotlin
factory {
    LocalItemDataSource(get())
}
```

### Repository

```kotlin
single<FeatureRepository> {
    FeatureRepositoryImpl(get())
}
```

### UseCases

```kotlin
factory {
    GetItemsUseCase(get())
}
```

### ViewModel

```kotlin
viewModel {
    FeatureViewModel(get())
}
```

Agregar el módulo al inicializador principal de DI.

---

## 7. Registrar navegación

Agregar la nueva feature al router:

```text
navigation/
```

Registrar:

### Route

```kotlin
object FeatureRoute
```

o

```kotlin
sealed class Route
```

según la arquitectura existente.

### Destino

```kotlin
composable(...)
```

### Integración

Incluir la pantalla en el grafo principal.

Ejemplo:

```kotlin
NavHost {
    featureGraph()
}
```

---

## 8. Flujo esperado final

```text
UI
 ↓
ViewModel
 ↓
UseCase
 ↓
Repository
 ↓
LocalDataSource
 ↓
Room
```

Si Room está vacío:

```text
Room vacío
 ↓
FakeDataProvider
 ↓
Guardar en Room
 ↓
Retornar datos
```

Si Room tiene datos:

```text
Room
 ↓
Repository
 ↓
UseCase
 ↓
ViewModel
 ↓
UI
```

---

# Criterios de aceptación

* Se crea completamente `domain/`.
* Se crea completamente `data/`.
* La presentación utiliza UseCases.
* No quedan mocks dentro de ViewModels.
* La feature está registrada en `navigation/`.
* La feature está registrada en `di/`.
* Los datos provienen de Room.
* Si Room está vacío se generan datos demo automáticamente.
* La aplicación compila sin errores en KMP Android.
