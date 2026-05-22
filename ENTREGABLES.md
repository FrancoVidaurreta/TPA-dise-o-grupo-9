# Entregables: DonaTrack (Entrega 1)

> [!NOTE]
> Este documento contiene los diagramas y justificaciones de diseño solicitados en el enunciado de la Entrega 1.

## 1. Justificaciones de Diseño

- **Polimorfismo en donantes:** armamos una clase abstracta Donante y de ahi heredan PersonaHumana y PersonaJuridica. La idea de esto es no llenarnos de if o casteos raros cada vez que entra alguien a donar, el sistema los agarra a todos como "Donante" y listo.

- **Patron Strategy para los avisos:** como teniamos q mandar msjs por mail, wpp o sms, y seguro despues nos piden agregar telegram o algo de eso, armamos una interfaz Notificador. la clase MedioDeContacto tiene uno de estos y le delega el trabajo, asi q cumple re bien el principio open closed. 

- **El tema de dividir las donaciones:** la caja grande que trae la gente no se toca, entra como DonacionGeneral. despues hicimos una clase SegmentadorDonaciones que agarra eso y lo rompe en pedacitos (DonacionSegmentada) agrupando por subcat, si es nuevo/usado y vencimiento. 

- **State para los estados logisticos:** el viaje de las donaciones (deposito -> asignado -> viaje -> entregado) no se podia hacer con un simple string porque teniamos q atajar errores si alguien queria saltar de deposito directo a entregado. Usamos el patron state donde cada estado es una clase q solo te deja hacer las acciones validas. Chau ifs gigantes.

- **Polimorfismo para las Necesidades:** las entidades pueden tener necesidades recurrentes o extraordinarias. como el calculo para saber si estan "satisfechas" es re distinto, pusimos un metodo abstracto estaSatisfecha() en la clase padre Necesidad. Cada hija hace la suya gracias a la ligadura dinamica (late binding).

## 2. Diagrama de Clases (Modelo de Dominio)

```mermaid
classDiagram

    %% Donantes
    class Donante {
        <<abstract>>
        -List~MedioDeContacto~ mediosDeContacto
        -MedioDeContacto predeterminado
        +agregarMedioDeContacto(MedioDeContacto)
        +notificar(String)
    }

    class PersonaHumana {
        -String nombre
        -String apellido
        -String documento
        -String genero
    }

    class PersonaJuridica {
        -String razonSocial
        -TipoOrganizacion tipo
        -String cuit
        -List~Representante~ representantes
        +agregarRepresentante(Representante)
    }

    class Representante {
        -String nombre
        -String apellido
        -List~MedioDeContacto~ mediosDeContacto
    }

    Donante <|-- PersonaHumana
    Donante <|-- PersonaJuridica
    PersonaJuridica "1" *-- "*" Representante : tiene

    %% Notificaciones
    class MedioDeContacto {
        -String valor
        +notificar(String)
    }
    
    class Notificador {
        <<interface>>
        +enviar(String, String)
    }

    Donante "1" *-- "*" MedioDeContacto
    MedioDeContacto "1" o-- "1" Notificador : strategy

    class NotificadorMail {
        +enviar(String, String)
    }
    class NotificadorSMS {
        +enviar(String, String)
    }
    class NotificadorWhatsApp {
        +enviar(String, String)
    }

    Notificador <|.. NotificadorMail
    Notificador <|.. NotificadorSMS
    Notificador <|.. NotificadorWhatsApp

    %% Donaciones
    class DonacionGeneral {
        -LocalDate fechaRegistro
        -String descripcionGeneral
        -List~Bien~ bienes
    }

    class DonacionSegmentada {
        -Boolean esUsado
        -LocalDate fechaVencimiento
        -List~Bien~ bienes
        +cambiarEstado(EstadoDonacion)
        +asignar()
        +iniciarTraslado()
        +confirmarEntrega()
    }

    class Bien {
        -String descripcion
        -double cantidad
        -String unidadMedida
        -Boolean esUsado
        -LocalDate fechaVencimiento
    }

    class Categoria {
        -String nombre
        -boolean requiereEstadoUsadoNuevo
    }

    class Subcategoria {
        -String nombre
    }

    DonacionGeneral "1" *-- "*" Bien
    DonacionSegmentada "1" *-- "*" Bien
    Donante "1" -- "*" DonacionGeneral : registra
    DonacionSegmentada "*" -- "1" Subcategoria : clasifica
    Bien "*" -- "1" Subcategoria : pertenece
    Categoria "1" *-- "*" Subcategoria : contiene

    class SegmentadorDonaciones {
        +segmentar(DonacionGeneral): List~DonacionSegmentada~
    }

    %% Estados Donacion (State)
    class EstadoDonacion {
        <<abstract>>
        +asignar()
        +planificarRuta()
        +iniciarTraslado()
        +confirmarEntrega()
        +fallarEntrega(String)
        +vencer()
    }

    DonacionSegmentada "1" *-- "1" EstadoDonacion : estado actual

    class EnDeposito
    class AsignacionRealizada
    class ListaParaEntregar
    class EnTraslado
    class Entregada
    class EntregaFallida
    class Vencida

    EstadoDonacion <|-- EnDeposito
    EstadoDonacion <|-- AsignacionRealizada
    EstadoDonacion <|-- ListaParaEntregar
    EstadoDonacion <|-- EnTraslado
    EstadoDonacion <|-- Entregada
    EstadoDonacion <|-- EntregaFallida
    EstadoDonacion <|-- Vencida

    %% Entidades y Necesidades
    class EntidadBeneficiaria {
        -String razonSocial
        -String direccion
        -List~Representante~ representantes
        -List~Necesidad~ necesidades
        +registrarNecesidad(Necesidad)
    }

    class Necesidad {
        <<abstract>>
        -String descripcion
        -double cantidadObjetivo
        -List~DonacionSegmentada~ donacionesRecibidas
        +recibirDonacion(DonacionSegmentada)
        +estaSatisfecha() bool
    }

    class NecesidadExtraordinaria {
        +estaSatisfecha() bool
    }

    class NecesidadRecurrente {
        -String periodo
        +estaSatisfecha() bool
    }

    EntidadBeneficiaria "1" *-- "*" Necesidad
    Necesidad <|-- NecesidadExtraordinaria
    Necesidad <|-- NecesidadRecurrente
    Necesidad "*" -- "1" Subcategoria : requiere
    Necesidad "1" o-- "*" DonacionSegmentada : recibe
```


## 3. Diagrama de Arquitectura (Logica/Componentes)

`mermaid
flowchart TD
    UI[App / Main Consola] --> Fachada[Sistema DonaTrack]
    Fachada --> Importador[Modulo Importador CSV]
    Fachada --> ModDonaciones[Modulo Donaciones]
    Fachada --> ModEntidades[Modulo Entidades]
    
    ModDonaciones --> Segmentador[Segmentador]
    ModDonaciones --> Estados[Maquina de Estados]
    ModDonaciones --> ModNotific[Modulo Notificaciones]
    
    Importador -.->|Lee| CSV[(Archivo donantes.csv)]
    ModNotific -.->|Simula uso| ExtAPI((APIs Externas: Mail/SMS))
`

## 4. Diagrama General de Casos de Uso

`mermaid
flowchart LR
    Admin((Administrador))
    Don((Donante))
    Ent((Entidad))
    
    UC1([Importar donantes CSV])
    UC2([Registrar donacion general])
    UC3([Segmentar donacion])
    UC4([Registrar necesidad])
    UC5([Asignar donacion])
    UC6([Manejar logistica y estados])
    
    Admin --- UC1
    Don --- UC2
    UC2 -.->|include| UC3
    Ent --- UC4
    Admin --- UC5
    Admin --- UC6
`
