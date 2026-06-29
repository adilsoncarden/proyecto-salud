
===== ARCHIVO 1: AvanceProy3_GestiónSalud.pdf =====
Página 1 — Portada

INGENIERÍA DE SISTEMAS E INFORMÁTICA

TITULO:
Implementación de un sistema de información para la gestión de atención médica en el Centro de Salud Base San Martín de Porres - Perú

CURSO:
Análisis y Diseño de Sistemas de Información

Página 2 — Índice
INDICE

Tabla de contenido

Introducción............................................................................................. 4
Justificación .............................................................................................. 4
Objetivos.................................................................................................. 4
3.1. Objetivo General.................................................................................. 4
3.2. Objetivos Específicos............................................................................ 4
Aspectos Generales ................................................................................. 4
4.1. Descripción de la Empresa.................................................................... 4
4.2. Visión ................................................................................................. 5
4.3. Misión................................................................................................. 5
Ámbito y Alcance...................................................................................... 5
5.1. Área de Aplicación del Proyecto ........................................................... 5
5.2. Recursos Humanos para la Elaboración del Proyecto ............................. 6
5.3. Software .............................................................................................. 6
5.4. Hardware............................................................................................. 6
5.5 Cronograma........................................................................................... 7
Fase de Inicio .......................................................................................... 7
6.1. Modelado de Negocio ........................................................................... 8
6.1.1 Modelado del Proceso de Negocio .................................................. 8
6.1.2. Modelado de Análisis de Negocio .................................................. 8
6.1.3. Recopilación de los Requerimientos ............................................. 10
Fase de Elaboración ................................................................................ 13
7.1. Casos de Usos .................................................................................... 13
7.2. Especificaciones del caso de uso......................................................... 15
7.3. Análisis del Sistema ............................................................................ 18
7.3.1. Paquete de Análisis .................................................................... 18
7.3.2. Clases de Entidad........................................................................ 19
7.3.3. Realizaciones de Caso de Uso ..................................................... 22
7.3.4. Diagrama de comunicación ......................................................... 23
7.3.5. Diagrama de secuencia ............................................................... 25
7.4. Modelo conceptual o Diagrama de clase ............................................. 28
7.5. Modelo Lógico ................................................................................... 29
7.6. Modelo Físico ..................................................................................... 30
7.7. Tarjeta CRC de cada clase ................................................................... 30
7.8. Diseño del Sistema ............................................................................. 32
7.8.1. Patrón de diseño ......................................................................... 32
7.8.2. Diagrama de estructura compuesta............................................. 33
7.8.3. Diagrama de tiempo .................................................................... 34
7.8.4. Diagrama de despliegue ............................................................... 35
7.8.5. Diagrama de componentes........................................................... 36
Referencias Bibliográficas: ....................................................................... 38
Página 3 — 1. Introducción
En el contexto actual, los sistemas de información desempeñan un papel fundamental en la optimización de los procesos organizacionales, especialmente en el sector salud, donde la gestión eficiente de la información puede impactar directamente en la calidad de vida de la población. La digitalización de procesos permite mejorar la accesibilidad, precisión y oportunidad de los datos, contribuyendo a una toma de decisiones más informada y oportuna.

El centro de Salud Base San Martín de Porres, como institución pública de atención primaria, enfrenta diversos desafíos relacionados con la gestión manual de información clínica y administrativa. Estas limitaciones afectan la eficiencia operativa, la calidad del servicio y la satisfacción del paciente.

En este contexto, el presente proyecto tiene como finalidad desarrollar una propuesta de sistema de información que permita optimizar los procesos de atención médica, registro de pacientes y gestión de citas, contribuyendo al fortalecimiento del sistema de salud pública y al cumplimiento de los principios de Responsabilidad Social Universitaria (RSU).

2. Justificación
El presente proyecto se justifica desde tres dimensiones fundamentales:

Académica, debido a que permite aplicar conocimientos de análisis y diseño de sistemas, modelo de procesos y levantamiento de requerimientos en un entorno real.

Tecnológica, ya que propone la implementación de un sistema de información que permitirá automatizar procesos críticos, reducir errores humanos y mejorar la trazabilidad de la información.

Social, dado que impacta directamente en la mejora de la atención de salud en una población vulnerable, contribuyendo a una atención más rápida, ordenada y eficiente.

3. Objetivos
3.1. Objetivo General
Diseñar un sistema de información que optimice la gestión de atención médica en el Centro de Salud Base San Martín de Porres.

3.2. Objetivos Específicos
Analizar los procesos actuales del centro de salud.
Identificar requerimientos funcionales y no funcionales.
Modelar el sistema mediante herramientas UML.
Proponer una solución tecnológica viable.
Evaluar el impacto social del sistema propuesto.
4. Aspectos Generales
4.1. Descripción de la Empresa
Rubro: Salud Pública
Ubicación: Pje. los Leones 258, San Martín de Porres 15101
Tipo de institución: Establecimiento de Salud
Página 4
Servicios: Medicina General, Pediatría, Ginecología, Inmunizaciones, Odontología y Laboratorio.
Problemática

Uso de registros manuales
Pérdida de historiales clínicos
Largas colas para atención
Falta de integración de información
4.2. Visión
Ser un centro de salud líder en atención primaria, reconocido por su calidad, accesibilidad y eficiencia.

4.3. Misión
Brindar servicios de salud integrales con calidad, equidad y enfoque preventivo a la población.

5. Ámbito y Alcance
5.1. Área de Aplicación del Proyecto
El área de aplicación del presente proyecto se centra administrativa y funcionalmente a la gestión operativa del Centro de Salud Base San Martín de Porres. De acuerdo con Pressman (2010), la delimitación del ámbito del software es el primer paso crítico para establecer el flujo de datos y las interfaces de control. En este contexto, el sistema propuesto intervendrá en las siguientes áreas de la institución:

Área de Admisión y Triaje: Actuará como el punto de origen del flujo de datos, automatizando el registro de filiación y la programación de citas para reducir los tiempos de espera y mejorar la experiencia del paciente.
Unidad de Archivo e Historias Clínicas: Se enfocará en la digitalización de los registros. Esta intervención se fundamenta en la Norma Técnica de Salud N° 139-MINSA/2018, la cual establece los lineamientos para la gestión y estandarización de la historia clínica, asegurando la integridad y disponibilidad de la información en tiempo real para el personal autorizado.
Consultorios Externos: El sistema será la herramienta principal para el registro de la atención médica y la prescripción electrónica, integrando el uso de la Clasificación Internacional de Enfermedades (CIE-10) para asegurar la precisión en los diagnósticos.
Límites del Proyecto: Basándonos en lo expuesto por Sommerville (2011) sobre los límites del sistema, el proyecto se enfoca estrictamente en la fase de Análisis y Diseño de la solución de software. El alcance funcional comprende desde el proceso de admisión hasta la generación de la ficha clínica de atención médica. Se excluyen de esta fase los procesos de contabilidad financiera, la gestión de planillas de recursos humanos y la integración con redes externas de salud de alta complejidad, priorizando la estabilidad del diseño para la operación interna del centro.

Página 5
5.2. Recursos Humanos para la Elaboración del Proyecto
Para el desarrollo de esta propuesta, se requiere un equipo multidisciplinario que cubra las fases de análisis, diseño y validación:

Analista de Sistemas: Responsable del levantamiento de requerimientos y el modelado de procesos (UML).
Arquitecto de Software: Encargado de definir la estructura tecnológica y la viabilidad de la solución.
Especialista en UX/UI: Diseñador de interfaces para asegurar que el personal médico pueda usar el sistema con facilidad.
Líder de Proyecto (Project Manager): Encargado del cumplimiento de objetivos y la gestión del cronograma.
Stakeholders: Personal administrativo y médico del Centro de Salud (como fuentes de información).

5.3. Software
Se detallan las herramientas lógicas necesarias tanto para el desarrollo del proyecto como para el funcionamiento de la propuesta:

Herramientas de Modelado: Enterprise Architect o Lucidchart (para diagramas UML de Casos de Uso, Clases y Secuencia).
Gestor de Base de Datos (Propuesta): MySQL o PostgreSQL (por su robustez y escalabilidad en salud).
Entorno de Desarrollo (IDE): Visual Studio Code o IntelliJ IDEA.
Gestión de Proyectos: Trello o Jira (para el seguimiento de actividades).
Documentación: Microsoft Office 365 o Google Workspace.

5.4. Hardware
Equipamiento físico mínimo requerido para la etapa de diseño y la implementación piloto:

Estaciones de Trabajo: Laptops/PCs con procesadores de gama media (Core i5 o superior) y 16GB de RAM para el equipo de desarrollo.
Servidor de Pruebas: Servidor local o instancia en la nube (AWS/Azure) para el despliegue de la base de datos y el backend.
Dispositivos de Red: Routers y cableado estructurado para garantizar la conectividad dentro del centro de salud.

Página 6
Periféricos de Entrada: Escáneres de documentos (para digitalizar historiales antiguos) y lectoras de DNI electrónico o códigos de barra.

5.5 Cronograma
Imagen: Diagrama de Gantt con 6 fases distribuidas en 12 semanas:

Fase	Actividad	Semanas (aprox.)
I. Inicio y Diagnóstico	Entrevistas con el personal y observación directa del proceso manual actual	1-2
II. Requerimientos	Definición de requerimientos funcionales (citas, historias clínicas) y no funcionales	3-4
III. Análisis y Diseño	Creación de diagramas de Casos de Uso, Modelo Entidad-Relación y prototipos (Wireframes)	5-7
IV. Propuesta Tecnológica	Selección de arquitectura (Cloud vs On-premise) y diseño de la infraestructura	8-9
V. Evaluación e Impacto	Análisis de costo-beneficio y evaluación del impacto social (RSU)	10-11
VI. Entrega Final	Redacción del informe final y presentación de la propuesta de sistema	12
6. Fase de Inicio
La presente fase constituye el punto de partida estratégico del ciclo de vida del desarrollo de software. Su propósito fundamental es establecer una base de conocimiento sólida y compartida sobre la estructura organizacional y operativa del Centro de Salud Base San Martín de Porres.

En el panorama actual, la institución gestiona sus flujos de información de manera manual y fragmentada mediante el uso de cuadernos físicos y hojas de cálculo (Excel) que no interactúan entre sí. Por ello, la Fase de Inicio se vuelve crítica para:

Identificar brechas de eficiencia: Analizar los puntos donde el registro manual genera retrasos, duplicidad de datos o pérdida de trazabilidad en la atención al paciente.
Alinear la tecnología con el negocio: Asegurar que la propuesta técnica no solo sea funcional, sino que responda directamente a las necesidades reales del personal administrativo y médico del centro.
Reducir la incertidumbre: Definir con precisión el alcance del sistema, permitiendo que el modelado posterior (BPMN y UML) se fundamente en la realidad operativa y no en supuestos.
Página 7
6.1. Modelado de Negocio
6.1.1 Modelado del Proceso de Negocio
Imagen: Diagrama BPMN "Proceso de Atención y Registro de Pacientes" con 4 carriles (lanes):

Carril Paciente:

Inicio → Llega al centro y solicita atención → [conexión con carril Admisión] → ... → Recibe receta e indicaciones → Fin
Carril Admisión y Archivo:

Busca datos en excel o archivador → Compuerta de decisión "¿Paciente Registrado?" → Si: Entregar ticket de turno físico → No: Registrar Datos → Entregar ticket de turno físico
Carril Enfermería:

Toma de Signos Vitales → Anotar en hoja de triaje manual
Carril Consultorio Médico:

Realiza Consulta Médica → Escribe receta y diagnóstico a mano
6.1.2. Modelado de Análisis de Negocio
6.1.2.1 Vista Interna
Imagen: Tabla "Vista Interna"

Trabajadores de Negocio	Entidades de Negocio	Realizaciones de Caso de uso del Negocio
Personal de Admisión	Ficha de paciente	Realización de Registro de Admisión
Ticket de Cita	Realización de Gestión de Citas
Enfermera	Ficha de Triaje / Historia Clínica	Realización de Atención Médica Integral
Medico	Receta Medica	
Página 8
6.1.2.2 Vista Externa
Imagen: Diagrama de relación Trabajadores-Entidades de negocio con actor "Personal de Admisión" conectado a "Ficha de Paciente" y "Ticket de Cita"; actor "Enfermera" conectado a "Ticket de Triaje"; actor "Medico" conectado a "Historia Clínica" y "Receta Médica".

Imagen: Tabla "Vista Externa"

Actores del Negocio	Casos de uso del Negocio	Objetivos del Negocio
Paciente	Atención Medica	Reducir tiempo de espera en ventanilla
Gestión de Citas	Asegurar que las historias clínicas estén disponibles
Registro de Admisión	Eliminar el uso de registros manuales
Página 9-10
6.1.3. Recopilación de los Requerimientos
Imagen: Diagrama de objetivos de negocio del actor "Paciente" conectado mediante líneas punteadas a tres casos de uso: "Registro de Admisión" → <<Bussines Goal>> Reducir tiempo de espera en ventanilla; "Atención Medica" → <<Bussines Goal>> Asegurar que las historias clínicas estén disponibles; "Gestión de Citas" → <<Bussines Goal>> Eliminar el uso de registros manuales.

La recopilación de requerimientos se realizó con el objetivo de identificar las necesidades reales del Centro de Salud Base San Martín de Porres, considerando tanto al personal administrativo como al personal médico y pacientes. Para ello, se aplicaron técnicas como entrevistas, cuestionarios y observación directa.

Diseño y Ejecución de Entrevistas

Se realizaron entrevistas semiestructuradas dirigidas al personal del centro de salud (médicos, administrativos y encargados de admisión), con la finalidad de comprender los procesos actuales y detectar problemas existentes.

Objetivo de la entrevista:
Identificar dificultades en la gestión de atención médica y oportunidades de mejora mediante un sistema de información.

N°	PREGUNTA ENTREVISTA
1	¿Cómo se realiza actualmente el registro de pacientes?
2	¿Qué dificultades presenta el proceso de atención médica?
3	¿Cómo se gestionan las citas médicas?
4	¿Qué problemas existen con los historiales clínicos?
5	¿Cuánto tiempo demora la atención a un paciente?
6	¿Existen errores frecuentes en el registro de información?
7	¿Cómo se comparte la información entre áreas?
Página 11
| 8 | ¿Qué herramientas tecnológicas utilizan actualmente? |
| 9 | ¿Qué mejoras considera necesarias en el sistema actual? |
| 10 | ¿Estaría dispuesto a usar un sistema digital para mejorar los procesos? |

Resultados obtenidos:

El 80% del personal confirmó que el registro es manual.
El 80% indicó que existen retrasos en la atención.
El 70% reportó pérdida o desorden de historiales clínicos.
El 90% afirmó que no existe integración entre áreas.
El 100% mostró disposición a usar un sistema digital.
Conclusión de entrevistas: Existe una clara necesidad de automatizar procesos, integrar áreas y mejorar la gestión de información. No existe integración entre áreas.

Elaboración de Cuestionarios

Se aplicaron cuestionarios al personal del centro de salud y algunos pacientes con el fin de obtener información cuantitativa sobre la calidad del servicio.

Objetivo del cuestionario:
Evaluar la percepción de los usuarios respecto al servicio actual.

N°	PREGUNTAS CUESTIONARIO	CRITERIO "SI O NO"
1	¿Considera eficiente el sistema de atención actual?	☐Si ☐No
2	¿Ha experimentado demoras en la atención médica?	☐Si ☐No
3	¿Ha tenido dificultades para obtener una cita médica?	☐Si ☐No
4	¿Se ha extraviado alguna vez su historial clínico?	☐Si ☐No
5	¿Considera adecuada la organización del centro de salud?	☐Si ☐No
6	¿El tiempo de espera para la atención es razonable?	☐Si ☐No
7	¿Le gustaría que el sistema de atención sea digital?	☐Si ☐No
8	¿Considera importante reducir las colas de atención?	☐Si ☐No
9	¿Recomendaría el servicio del centro de salud a otras personas?	☐Si ☐No
10		
Página 12
Resultados:

80% considera ineficiente el sistema actual.
75% indica que siempre hay demoras.
65% ha tenido dificultades para obtener citas.
60% ha experimentado pérdida de información.
70% No percibe como adecuada la organización del centro de salud.
90% considera importante reducir colas.
95% está de acuerdo con implementar un sistema digital.
Conclusión del cuestionario: Los usuarios perciben un servicio lento, desorganizado y con fallas en la gestión de información.

Reporte de Observación

Se realizó observación directa en el centro de salud para analizar el flujo de atención y los procesos internos.

Aspectos observados:

Registro manual en cuadernos y fichas físicas.
Acumulación de pacientes en cola para citas.
Búsqueda lenta de historiales clínicos.
Falta de coordinación entre áreas.
Problemas identificados:

Procesos repetitivos y poco eficientes.
Alta probabilidad de error humano.
Alta congestión durante las horas de mayor demanda.
Baja calidad en la atención al paciente.
Sugerencia: Listado Final de Requerimientos

Requerimientos Funcionales (RF):

RF01: El sistema debe permitir el registro digital de pacientes. (Derivado del problema de registros manuales)
RF02: El sistema debe permitir agendar citas médicas. (Problemas en obtención de citas)

Página 13
RF03: El sistema debe gestionar historiales clínicos electrónicos. (Pérdida de información)
RF04: El sistema debe permitir la consulta de citas por fecha y especialidad. (Desorganización)
RF05: El sistema debe generar reportes de atención médica. (Falta de control)
RF06: El sistema debe permitir la actualización de datos del paciente. (Errores en registros)
RF07: El sistema debe integrar las áreas del centro de salud. (Falta de comunicación)
RF08: El sistema debe permitir autenticación de usuarios. (Seguridad)
RF09: El sistema debe permitir la búsqueda rápida de pacientes. (Tiempo de atención)
RF10: El sistema debe registrar el historial de atenciones médicas. (Seguimiento del paciente)

Requerimientos No Funcionales (RNF):

RNF01: El sistema debe ser fácil de usar. (Usuarios sin experiencia técnica)
RNF02: El sistema debe garantizar la seguridad de la información. (Datos sensibles)
RNF03: El sistema debe tener alta disponibilidad. (Uso continuo)
RNF04: El sistema debe responder en menos de 3 segundos. (Demoras actuales)
RNF05: El sistema debe ser accesible desde diferentes dispositivos. (Flexibilidad)
RNF06: El sistema debe permitir copias de seguridad automáticas. (Evitar pérdida de datos)
RNF07: El sistema debe cumplir normas de protección de datos. (Regulación)
RNF08: El sistema debe ser escalable. (Crecimiento futuro)
RNF09: El sistema debe tener una interfaz amigable. (Mejor experiencia)
RNF10: El sistema debe permitir mantenimiento y actualizaciones. (Sostenibilidad)

7. Fase de Elaboración
7.1. Casos de Usos
Identificación de Actores del Sistema

Paciente: Actor externo. Persona que solicita atención médica, reserva de citas y es el sujeto central de la historia clínica.
Personal de Admisión: Actor interno. Encargado de registrar a los pacientes, verificar su afiliación al seguro (SIS/Particular) y gestionar la programación y cancelación de citas médicas.
Personal de Enfermería: Actor interno. Responsable de la toma de signos vitales, somatometría y registro del estado inicial del paciente antes de la consulta médica.
Página 14
Médico: Actor interno. Profesional de la salud encargado de realizar la atención médica, prescribir tratamientos, registrar diagnósticos y actualizar la historia clínica electrónica.
Administrador del Sistema: Actor interno. Responsable de la gestión de usuarios, roles, mantenimiento de catálogos y auditoría de la plataforma.
Lista de Casos de Uso del Sistema

CU01: Registrar Paciente
CU02: Gestionar Citas Médicas (Programar, Reprogramar, Cancelar)
CU03: Realizar Triaje
CU04: Registrar Atención Médica (Consulta)
CU05: Consultar Historia Clínica Electrónica
CU06: Emitir Receta Médica y Órdenes de Examen
CU07: Autenticar Usuario (Caso de uso transversal e include para el personal del centro de salud)
Relación entre Actores y Casos de Uso

Paciente: Gestionar Citas Médicas.
Personal de Admisión: Registrar Paciente, Gestionar Citas Médicas, Autenticar Usuario.
Personal de Enfermería: Realizar Triaje, Consultar Historia Clínica Electrónica, Autenticar Usuario
Médico: Registrar Atención Médica, Consultar Historia Clínica Electrónica, Emitir Receta Médica y Órdenes de Examen, Autenticar Usuario.
Administrador del Sistema: Autenticar Usuario.
Página 15-17
7.2. Especificaciones del caso de uso
Imagen: Diagrama de Casos de Uso UML "Sistema de Salud" mostrando los actores (Personal de Admisión, Paciente, Personal de Enfermería, Médico, Administrador) conectados a los casos de uso (Registrar Paciente, Gestionar Citas Médicas, Realizar Triaje, Registrar Atención Médica, Consultar Historia Clínica Electrónica, Emitir Receta Médica, Autenticar Usuario), con relaciones <<include>> hacia Autenticar Usuario y <<extend>> entre Consultar Historia Clínica Electrónica/Registrar Atención Médica y Emitir Receta Médica.

Especificación de Caso de Uso: CU02 - Gestionar Citas Médicas

Nombre del caso de uso: Gestionar Citas Médicas
Actor(es): Personal de Admisión, Paciente
Descripción: Permite la reserva, reprogramación o cancelación de un cupo de atención médica en una especialidad y horario específicos para un paciente registrado.
Precondiciones:
El Paciente debe estar previamente registrado en el sistema.
El Personal de Admisión debe haber iniciado sesión (en caso de trámite presencial).
Deben existir horarios y cupos disponibles configurados por el médico/especialidad.
Postcondiciones: El sistema genera un ticket de cita con estado "Programada", actualiza la disponibilidad del médico y genera el registro en la agenda diaria.
Flujo principal:
El actor solicita la programación de una cita médica.
El sistema solicita el número de documento de identidad (DNI/CE) del paciente.
El actor ingresa el documento y el sistema valida la existencia del paciente.
El sistema despliega las especialidades médicas disponibles.
El actor selecciona la especialidad, el médico y la fecha requerida.
El sistema muestra los horarios y cupos disponibles para la selección efectuada.
El actor selecciona el horario idóneo y confirma la reserva.
El sistema registra la cita con el estado "Programada" y emite el comprobante digital de la reserva.
Flujos alternos:
Página 16-17
I. A1: Paciente no registrado: En el paso 3, si el sistema no encuentra el documento, muestra un mensaje de alerta. Si el actor es el Personal de Admisión, se desvía al CU01: Registrar Paciente; si es el Paciente, se le notifica que debe acudir a admisión.
II. A2: Inexistencia de cupos disponibles: En el paso 6, si no hay cupos para la fecha o médico seleccionado, el sistema sugiere las fechas próximas más cercanas con disponibilidad. El actor selecciona una nueva fecha o cancela la operación.
III. A3: Cancelación de Cita: En el paso 1, el actor selecciona la opción "Cancelar Cita". El sistema busca las citas activas del paciente, el actor selecciona la cita a anular e ingresa el motivo. El sistema cambia el estado a "Cancelada" y libera el cupo.

Especificación de Caso de Uso: CU03 – Realizar Triaje

Nombre del caso de uso: Realizar Triaje
Actor(es): Personal de Enfermería
Descripción: Permite el registro de las funciones vitales, medidas antropométricas y sintomatología general del paciente previo a su ingreso al consultorio médico.
Precondiciones:
El Personal de Enfermería debe estar autenticado en el sistema.
El paciente debe contar con una cita programada para el día en estado "Por Triar" (con asistencia confirmada en admisión).
Postcondiciones: Los datos del triaje quedan consolidados y enlazados a la cita del paciente, cambiando el estado de la cita a "Pendiente de Atención" y habilitándola en la cola del médico.
Flujo principal:
El Personal de Enfermería visualiza la lista de pacientes en espera para triaje del día.
El actor selecciona al paciente correspondiente de la lista.
El sistema despliega el formulario de captura de datos de triaje.
El actor evalúa al paciente e ingresa los datos requeridos: Presión Arterial, Frecuencia Cardíaca, Frecuencia Respiratoria, Temperatura, Saturación de Oxígeno, Peso, Talla y Motivo de Consulta escrito por el paciente.
El sistema calcula automáticamente el Índice de Masa Corporal (IMC) y valida si los signos vitales están dentro de los rangos normales (alertando visualmente si hay anomalías).
El actor confirma y guarda el registro.
El sistema almacena la información de forma definitiva y actualiza el estado de la cita a "Pendiente de Atención".
Flujos alternos:
IV. A1: Error de consistencia en datos ingresados: En el paso 5, si los valores numéricos ingresados están fuera de la lógica biológica (ej. Temperatura superior a 43°C o peso igual a 0), el sistema bloquea el guardado, resalta los campos erróneos en rojo y solicita la rectificación del dato.
Especificación de Caso de Uso: CU04 – Registrar Atención Médica

Nombre del caso de uso: Realizar Atención Médica
Página 17-18
Actor(es): Médico
Descripción: Permite al profesional de la salud registrar el acto médico completo de la consulta, influyendo la anamnesis, la exploración física, el diagnóstico codificado y el plan de tratamiento.
Precondiciones:
El Médico debe estar debidamente autenticado en la plataforma.
El paciente debe encontrarse en estado "Pendiente de Atención" con el triaje debidamente completado y guardado en el sistema.
Postcondiciones: Se registra el acto médico en la Historia Clínica Electrónica (HCE) de forma permanente, y la cita pasa al estado "Atendido".
Flujo principal:
El Médico visualiza en su bandeja de entrada la cola de pacientes con triaje completado.
El actor selecciona al paciente a atender y solicita iniciar la consulta.
El sistema abre el módulo de atención médica mostrando en paralelo los datos de triaje vigentes y los antecedentes de la Historia Clínica del paciente.
El actor registra la Anamnesis (relato cronológico de la enfermedad) y los hallazgos del Examen Físico.
El actor ingresa los diagnósticos presuntivos o definitivos utilizando el buscador integrado con codificación estándar CIE-10.
El actor prescribe el tratamiento en la sección de Plan de Trabajo (Medicamentos, indicaciones, dosis).
El actor finaliza la consulta y guarda la información.
El sistema consolida la información en la base de datos, restringe la edición posterior del registro para garantizar la seguridad legal y cambia el estado de la cita a "Atendido".
Flujos alternos:
V. A1: Necesidad de emitir receta digital u órdenes adicionales: En el paso 6, el médico requiere generar documentos oficiales impresos o digitales para el paciente. El sistema deriva el flujo de manera automatizada al caso de uso extendido CU06: Emitir Receta Médica y Órdenes de Examen. Al finalizar dicho proceso, se retorna al paso 7 del flujo principal.
Representación Visual Adicional del Proceso: Flujo General de Atención del Paciente

Para garantizar un correcto entendimiento de cómo interactúan estos casos de uso secuencialmente dentro del Centro de Salud Base San Martín de Porres, se propone el siguiente modelo dinámico.

Página 18-19
7.3. Análisis del Sistema
Imagen: Diagrama de Actividad UML "Flujo de Atención de Paciente" con cuatro columnas (Paciente, Admisión, Enfermería/Triaje, Médico) mostrando el flujo desde "Solicitar cita presencialmente" hasta "Recibir Receta Médica y Retirarse", pasando por registro de paciente, programación de cita, toma de signos vitales, evaluación, diagnóstico CIE-10 y emisión de receta.

7.3.1. Paquete de Análisis
Para la estructuración del sistema de información orientado a objetos del Centro de Salud Base San Martín de Porres, se determina una arquitectura modular basada en paquetes de análisis. Esta organización incrementa la cohesión de los elementos de software y minimiza el acoplamiento entre los procesos clínicos y administrativos. Tomando como base el diagnóstico situacional de la fase de inicio —donde se identificó un 90% de falta de integración entre las áreas y un 80% de ineficiencia por flujos manuales (ver sección 6.1.3)—, el sistema se segmenta en cuatro (4) paquetes funcionales:

Paquete de Seguridad y Accesos: Este módulo encapsula la lógica asociada al control de la seguridad perimetral del software, la verificación de credenciales y la asignación dinámica de permisos según el rol del usuario. Gobierna el comportamiento de las clases de autenticación para mitigar el riesgo de accesos no autorizados a datos sensibles de salud, respondiendo directamente al requerimiento de seguridad RF08.
Paquete de Admisión y Citas: Módulo encargado de gestionar el flujo inicial del paciente en el establecimiento. Centraliza los procesos de alta de usuarios en la base de datos, la verificación de disponibilidad de los profesionales de la salud y el agendamiento electrónico de citas. Su desarrollo mitiga las largas colas en ventanilla y resuelve de manera directa los requerimientos RF01, RF02 y RF04.
Paquete de Triaje: Diseñado de manera exclusiva para asistir al personal de enfermería en el flujo de toma de signos vitales. Este paquete procesa la captura de datos antropométricos y fisiológicos previos a la consulta. Reemplaza de forma definitiva la "hoja de triaje manual" detectada en el diagrama AS-IS del centro de salud, automatizando el paso previo a la atención médica.
Página 19
Paquete de Atención Médica e Historia Clínica: Representa el núcleo del sistema de información hospitalaria. Gestiona la consulta interactiva llevada a cabo por el médico, la lectura y actualización cronológica de la historia clínica electrónica (RF03), así como la generación de prescripciones farmacéuticas. Este paquete erradica la problemática del 70% de pérdida o desorden de historiales clínicos físicos reportados por el personal (ver Tabla 3).
7.3.2. Clases de Entidad
En concordancia con los requerimientos identificados y la abstracción conceptual del dominio del problema, se definen las clases de entidad que permitirán la persistencia de la información en el sistema. Cada clase incluye la definición de sus atributos de negocio con la nomenclatura técnica adecuada (notación camelCase) para garantizar una correcta transición hacia los modelos lógicos y físicos desarrollados por el equipo.

1. Clase: Usuario (Clase Base / Generalización)

Descripción: Entidad abstracta que centraliza los atributos comunes de identificación y seguridad para cualquier persona con credenciales de acceso al sistema, soportando el requerimiento RF08.
Atributos:
idUsuario: int (Identificador único correlativo del sistema)
correo: String (Dirección de correo electrónico institucional o registrado)
contraseña: String (Cadena de texto encriptada para validación de identidad)
rol: String (Perfil asignado: Administrador, Médico, PersonalEnfermeria, PersonalAdmision, Paciente)
2. Clase: Administrador (Especialización de Usuario)

Descripción: Representa al personal de TI responsable del mantenimiento operativo, la auditoría del software y la configuración de los parámetros globales del sistema.
Atributos:
idUsuario: int (Heredado de la clase base Usuario)
nombres: String (Nombres del personal administrativo)
apellidos: String (Apellidos del personal administrativo)
3. Clase: Administrador (Especialización de Usuario) [Nota: el documento repite este encabezado, pero la descripción corresponde a Personal de Admisión]

Descripción: Entidad que modela los operarios de la ventanilla de recepción, encargados del agendamiento y la gestión de la demanda de citas.
Atributos:
idUsuario: int (Heredado de la clase base Usuario)
dni: String (Documento Nacional de Identidad del trabajador para fines legales)
nombres: String (Nombres del personal de admisión)
apellidos: String (Apellidos del personal de admisión)
Página 20
turnoLaboral: String (Horario asignado para el control de actividades: Mañana / Tarde)
4. Clase: PersonalEnfermeria (Especialización de Usuario)

Descripción: Modela al personal técnico y profesional encargado del registro y validación inicial del paciente en el área de triaje.
Atributos:
idUsuario: int (Heredado de la clase base Usuario)
dni: String (Documento Nacional de Identidad del profesional de enfermería)
nombres: String (Nombres de la enfermera/o)
apellidos: String (Apellidos de la enfermera/o)
areaAsignada: String (Sector físico o módulo de atención dentro del establecimiento)
5. Clase: Médico (Especialización de Usuario)

Descripción: Entidad que almacena los datos de los profesionales de la salud facultados para la atención diagnóstica, tratamiento y prescripción dentro de los consultorios externos.
Atributos:
idUsuario: int (Heredado de la clase base Usuario)
dni: String (Documento Nacional de Identidad del profesional médico)
nombres: String (Nombres del médico especialista)
apellidos: String (Apellidos del médico especialista)
especialidad: String (Rama de la medicina en la que ejerce: Medicina General, Pediatría, Ginecología, etc.)
6. Clase: Paciente (Especialización de Usuario)

Descripción: Entidad que registra la información de los ciudadanos que reciben los servicios médicos preventivos y recuperativos en el centro de salud, dando soporte a los requerimientos RF01 y RF06.
Atributos:
idUsuario: int (Heredado de la clase base Usuario)
dni: String (Documento Nacional de Identidad del paciente)
nombres: String (Nombres completos del paciente)
apellidos: String (Apellidos completos del paciente)
fechaNacimiento: date (Fecha de nacimiento útil para el cálculo automático de la edad en consulta)
telefono: String (Número telefónico de contacto para alertas o notificaciones de citas)
7. Clase: CitaMedica

Página 21
Descripción: Registro transaccional que formaliza la reserva de una franja horaria de atención solicitada por el paciente, respondiendo a los requerimientos RF02 y RF04.
Atributos:
idCita: int (Código numérico incremental único de la cita médica)
fechaCita: date (Día programado para la consulta)
horaCita: time (Bloque horario asignado para la atención)
estado: String (Indicador de situación de la cita: Pendiente, Triado, Atendido, Cancelado)
consultorio: String (Identificador del espacio físico asignado en el centro de salud)
8. Clase: Triaje

Descripción: Entidad clínica que almacena los parámetros vitales básicos recolectados obligatoriamente por enfermería antes de la derivación al consultorio médico.
Atributos:
idTriaje: int (Clave primaria única del registro de triaje)
peso: float (Masa corporal del paciente expresada en kilogramos)
talla: float (Estatura del paciente expresada en metros)
presionArterial: String (Lectura de la tensión sistólica/diastólica en mmHg)
temperatura: float (Nivel térmico corporal medido en grados Celsius)
motivoConsulta: String (Sintomatología primaria o razón de la visita declarada por el paciente)
9. Clase: AtencionMedica

Descripción: Registra el desenlace de la evaluación clínica directa realizada por el médico, consolidando el juicio diagnóstico y dando soporte al requerimiento RF05 y RF10.
Atributos:
idAtencion: int (Código único e incremental de la consulta efectuada)
diagnostico: String (Codificación de la patología hallada bajo el estándar internacional CIE-10)
observaciones: String (Anotaciones detalladas de la anamnesis y el examen físico)
10. Clase: HistoriaClinica

Descripción:
Página 22
Expediente médico digital, único y perenne que unifica cronológicamente todas las atenciones del paciente, bajo los lineamientos de la Norma Técnica N° 139-MINSA/2018 (RF03).

Atributos:
idHistoria: int (Identificador correlativo único asignado al expediente clínico)
fechaCreacion: date (Fecha de la apertura inicial del archivo clínico digital)
tipoSangre: String (Grupo sanguíneo y factor Rh verificado del paciente)
alergias: String (Registro explícito de contraindicaciones y reacciones alérgicas del usuario)
11. Clase: RecetaMedica

Descripción: Documento digital generado de forma adyacente a la atención por el médico para prescribir la terapia farmacológica necesaria.
Atributos:
idReceta: int (Código identificador del documento electrónico de prescripción)
fechaEmision: date (Día de generación y firma de la orden de farmacia)
medicamentos: String (Detalle nominativo de los compuestos químicos o insumos clínicos ordenados)
indicaciones: String (Instrucciones específicas de dosificación, frecuencia de consumo y duración del tratamiento)
7.3.3. Realizaciones de Caso de Uso
Realización del Caso de Uso: CU02 – Gestionar Citas Médicas

Objeto / Clase	Responsabilidad
Paciente	Solicita la reserva, reprogramación o cancelación de citas médicas.
PersonalAdmision	Registra y valida los datos del paciente.
:SistemaSalud	Procesa la validación de información y controla el flujo de citas.
CitaMedica	Almacena la información y estado de la cita médica.
Medico	Proporciona la disponibilidad de horarios y especialidad.
Descripción de realización:
El proceso inicia cuando el Paciente solicita una cita médica mediante el Personal de Admisión. El objeto :SistemaSalud valida la existencia del paciente y consulta la disponibilidad del médico. Posteriormente, se registra la cita en la entidad CitaMedica y el sistema genera el comprobante correspondiente con estado "Programada".

Página 23
Realización del Caso de Uso: CU03 – Realizar Triaje

Objeto / Clase	Responsabilidad
PersonalEnfermeria	Registra signos vitales y datos clínicos del paciente.
Paciente	Proporciona información médica básica para el triaje.
:SistemaSalud	Valida y procesa los datos ingresados.
Triaje	Almacena la información clínica inicial del paciente.
Descripción de realización:
El Personal de Enfermería selecciona al paciente pendiente de atención y registra los signos vitales en el sistema. El objeto :SistemaSalud valida los datos ingresados y almacena la información en la entidad Triaje, actualizando el estado de la cita a "Pendiente de Atención".

Realización del Caso de Uso: CU04 – Registrar Atención Médica

Objeto / Clase	Responsabilidad
Medico	Registra diagnóstico, observaciones y tratamiento del paciente.
:SistemaSalud	Gestiona el proceso de atención médica y actualización de datos.
HistoriaClinica	Guarda el historial clínico electrónico del paciente.
AtencionMedica	Registra la consulta y diagnóstico médico realizado.
RecetaMedica	Genera la prescripción médica digital.
Descripción de realización:
El Médico inicia la consulta médica y revisa la información del paciente. El objeto :SistemaSalud muestra el historial clínico y permite registrar el diagnóstico, observaciones y tratamiento. Finalmente, se actualiza la HistoriaClinica, se genera la RecetaMedica y la cita cambia al estado "Atendido".

7.3.4. Diagrama de comunicación
Diagrama de Comunicación: CU02 – Gestionar Citas Médicas

Objetos participantes:

Paciente
PersonalAdmision
:SistemaSalud
Medico
Página 24
CitaMedica
Secuencia de mensajes:

Paciente → PersonalAdmision : solicitarCita()
PersonalAdmision → :SistemaSalud : validarPaciente()
:SistemaSalud → Medico : consultarDisponibilidad()
Medico → :SistemaSalud : disponibilidadHorarios()
:SistemaSalud → CitaMedica : registrarCita()
CitaMedica → :SistemaSalud : confirmarRegistro()
:SistemaSalud → PersonalAdmision : generarComprobante()
PersonalAdmision → Paciente : entregarComprobante()
Diagrama de Comunicación: CU03 – Realizar Triaje

Objetos participantes:

PersonalEnfermeria
Paciente
:SistemaSalud
Triaje
Secuencia de mensajes:

PersonalEnfermeria → :SistemaSalud : seleccionarPaciente()
:SistemaSalud → Paciente : mostrarDatos()
PersonalEnfermeria → Triaje : registrarSignosVitales()
Triaje → :SistemaSalud : guardarDatos()
:SistemaSalud → PersonalEnfermeria : confirmarRegistro()
Diagrama de Comunicación: CU04 – Registrar Atención Médica

Objetos participantes:

Medico
:SistemaSalud
Página 25
HistoriaClinica
AtencionMedica
RecetaMedica
Secuencia de mensajes:

Medico → :SistemaSalud : iniciarConsulta()
:SistemaSalud → HistoriaClinica : consultarHistorial()
HistoriaClinica → :SistemaSalud : mostrarHistorial()
Medico → AtencionMedica : registrarDiagnostico()
Medico → RecetaMedica : generarReceta()
:SistemaSalud → HistoriaClinica : actualizarHistorial()
:SistemaSalud → Medico : confirmarAtencion()
7.3.5. Diagrama de secuencia
Diagrama de Secuencia: CU02 – Gestionar Citas Médicas

Participantes:
Paciente — PersonalAdmision — :SistemaSalud — Medico — CitaMedica

Imagen: Diagrama de Comunicación UML "CD - CU02: Gestionar Citas Médicas" mostrando objetos Paciente, :PersonalAdmision, :SistemaSalud, :Medico y :CitaMedica con flechas de mensajes numeradas (1: solicitarCita(), 2: validarPaciente(dni), 3: consultarDisponibilidad(especialidad, fecha), etc.) y leyenda de tipos de mensaje/objeto.

Página 26
Flujo secuencial:

El Paciente solicita una cita médica.
El Personal de Admisión ingresa el DNI del paciente.
:SistemaSalud valida la existencia del paciente.
:SistemaSalud consulta la disponibilidad del médico.
El Médico devuelve los horarios disponibles.
El Personal de Admisión selecciona el horario requerido.
:SistemaSalud registra la cita médica.
El sistema genera el comprobante digital.
El Paciente recibe la confirmación de la cita.
Diagrama de Secuencia: CU03 – Realizar Triaje

Participantes:
PersonalEnfermeria — Paciente — :SistemaSalud — Triaje

Flujo secuencial:

El Personal de Enfermería selecciona al paciente.
:SistemaSalud muestra los datos del paciente.
El Personal de Enfermería registra los signos vitales.
:SistemaSalud valida la información ingresada.
El sistema guarda el registro en Triaje.
:SistemaSalud actualiza el estado de la cita.
El Personal de Enfermería confirma el proceso.
Diagrama de Secuencia: CU04 – Registrar Atención Médica

Participantes:
Medico — :SistemaSalud — HistoriaClinica — AtencionMedica — RecetaMedica

Flujo secuencial:

El Médico inicia la consulta médica.
Página 27
:SistemaSalud muestra el historial clínico del paciente.
El Médico registra observaciones y diagnóstico.
El Médico prescribe medicamentos e indicaciones.
:SistemaSalud genera la receta médica digital.
El sistema actualiza la HistoriaClinica.
:SistemaSalud cambia el estado de la cita a "Atendido".
El Médico finaliza la atención médica.
Imagen: Diagrama de Secuencia UML "sd CU02 - Gestionar Citas Médicas" con línea de vida para Paciente, :PersonalAdmision, :SistemaSalud, :Medico y :CitaMedica, mostrando mensajes sincrónicos/asincrónicos numerados (solicitarCita(), validarPaciente(dni), consultarDisponibilidad(), mostrarHorariosDisponibles(), seleccionarHorario(), registrarCita(), confirmacionRegistro(), generarComprobante(), entregarComprobante()), incluyendo un fragmento de referencia "ref: Manejar errores" y leyenda de simbología (mensaje sincrónico, asincrónico, ejecución de operación, objeto/instancia, fin de línea de vida).

Página 28
7.4. Modelo conceptual o Diagrama de clase
Imagen: Diagrama de clases UML mostrando la jerarquía de generalización a partir de la clase base "Usuario", de la cual heredan "Administrador", "PersonalEnfermeria", "Medico", "Paciente" y "PersonalAdmision" (esta última mediante una relación de composición). Las relaciones incluyen: PersonalEnfermeria "+Registra" → Triaje; Medico "+Realiza" → AtencionMedica; Paciente "+Tiene" → Historia Clinica; PersonalAdmision "+Registra" → CitaMedica; Paciente "+Solicita" → CitaMedica; Triaje "+requiere" → AtencionMedica; AtencionMedica "+Actualiza" → Historia Clinica; AtencionMedica "+Prescribe" → RecetaMedica.

Página 29
7.5. Modelo Lógico
Imagen: Diagrama de clases UML detallado (Modelo Lógico) mostrando cada clase con sus atributos y métodos:

Usuario: idUsuario: int, correo: String, contraseña: String, rol: String | +autenticarUsuario(), +cerrarSesion()
Administrador: nombres: String, apellidos: String | +gestionarUsuarios(), +asignarPermisos(), +configurarSistema()
PersonalAdmision: dni: String, nombres: String, apellidos: String, turnoLaboral: String | +registrarPaciente(), +gestionarCitas()
PersonalEnfermeria: dni: String, nombres: String, apellidos: String, areaAsignada | +realizarTriaje(), +consultarHistorial()
Medico: dni: String, nombres: String, apellidos: String, especialidad: String | +registrarAtencionMedica(), +emitirReceta(), +consultarHistoria()
Paciente: dni: String, nombres: String, apellidos: String, fechaNacimiento: date, telefono: String | +registrarse(), +solicitarCita(), +verHistoriaClinica()
Triaje: idTriaje: int, peso: float, talla: float, presionArterial: String, temperatura: float, motivoConsulta: String | +registrarDatosTriaje()
AtencionMedica: idAtencion: int, diagnostico: String, observaciones: String | +guardarAtencion(), +generarRecetaOpcional()
Historia Clinica: idHistoria: int, fechaCreacion: date, tipoSangre: String, alergias: String | +crearHistoria(), +actualizarHistoria(), +obtenerDetallesHistoria()
CitaMedica: idCita: int, fechaHora: dateTime, estado: String, consultorio: String | +programarCita(), +cancelarCita()
RecetaMedica: idReceta: int, fechaEmision: date, medicamentos: String, indicaciones: String | +emitirReceta(), +imprimirReceta()
Relaciones: PersonalEnfermeria "+Registra" → Triaje; Triaje "+requiere" → AtencionMedica; Medico "+Realiza" → AtencionMedica; AtencionMedica "+Actualiza" → Historia Clínica; AtencionMedica "+Prescribe" → RecetaMedica; Paciente "+Tiene" → Historia Clínica; Paciente "+Solicita" → CitaMedica; PersonalAdmision "+Registra" → CitaMedica.

Página 30
7.6. Modelo Físico
Imagen: Diagrama Entidad-Relación "Modelo Físico" mostrando las tablas de base de datos con sus campos y tipos de dato:

PACIENTE: ID_Paciente (PK, AI, INT), DNI (VARCHAR 8 U), Nombres (VARCHAR 100), Apellidos (VARCHAR 100), Fecha_Nacimiento (DATE), Telefono (VARCHAR 15), Direccion (VARCHAR 255)
PERSONAL_SALUD: ID_Personal (PK, AI, INT), DNI (VARCHAR 8 U), Nombres (VARCHAR 100), Apellidos (VARCHAR 100), Rol_Usuario (VARCHAR 50), Especialidad (VARCHAR 100)
CITA_MEDICA: ID_Cita (PK, AI, INT), ID_Paciente (FK, INT), ID_Personal_Medico (FK, INT), Fecha_Hora (DATETIME), Estado (VARCHAR 20)
TRIAJE: ID_Triaje (PK, AI, INT), ID_Cita (FK, INT), ID_Personal_Enfermeria (FK, INT), Peso_Kg (DECIMAL 5,2), Talla_Cm (DECIMAL 5,2), Presion_Arterial (VARCHAR 15), Temperatura_C (DECIMAL 4,2), Fecha_Registro (DATETIME)
HISTORIA_CLINICA: ID_Historia (PK, AI, INT), ID_Paciente (FK, INT), Fecha_Creacion (DATE)
ATENCION_MEDICA: ID_Atencion (PK, INT), ID_Historia (FK, INT), ID_Cita (FK, INT), Codigo_CIE10 (VARCHAR 10), Diagnostico_Detalle (TEXT), Receta_Medica (TEXT), Fecha_Atencion (DATETIME)
7.7. Tarjeta CRC de cada clase
Las tarjetas CRC (Clase - Responsabilidad - Colaborador) permiten definir de manera ágil el rol de cada componente dentro del flujo del sistema de atención del Centro de Salud.

Clase: Paciente

Responsabilidades	Colaboradores
• Conocer sus datos de afiliación personal (DNI, Nombres, Contacto). • Solicitar y mantener el registro de sus citas médicas. • Actualizar sus datos en el sistema.	• Historia_Clinica • Cita_Medica
Clase: Cita_Medica

Responsabilidades	Colaboradores
• Registrar la fecha, hora y especialidad de la atención requerida. • Mantener el estado de la cita (Pendiente, En curso, Finalizada).	• Paciente • Personal_Salud (Admisión/Médico) • Triaje
Página 31
• Vincular al paciente con el médico correspondiente.

Clase: Personal_Salud

Responsabilidades	Colaboradores
• Autenticarse en el sistema de forma segura. • (Si es Admisión): Registrar pacientes y entregar tickets de cita. • (Si es Enfermera): Tomar y registrar signos vitales. • (Si es Médico): Realizar consultas y emitir recetas.	• Cita_Medica • Triaje • Atencion_Medica
Clase: Triaje

Responsabilidades	Colaboradores
• Registrar los datos previos a la consulta (peso, presión, temperatura). • Proveer la información inicial al médico antes de la atención.	• Cita_Medica • Personal_Salud (Enfermera)
Clase: Historia_Clinica

Responsabilidades	Colaboradores
• Almacenar centralizadamente todo el registro de atenciones y diagnósticos del paciente para garantizar su disponibilidad. • Evitar la pérdida de registros médicos físicos.	• Paciente • Atencion_Medica
Clase: Atencion_Medica

| Responsabilidades | Colaboradores |

Página 32
| • Registrar el diagnóstico utilizando el estándar CIE-10. • Generar la receta médica electrónica y las indicaciones del tratamiento. • Actualizar la Historia_Clinica con el nuevo registro de atención. | • Historia_Clinica • Personal_Salud (Médico) • Cita_Medica |

7.8. Diseño del Sistema
El diseño del sistema de información para la gestión de atención médica en el Centro de Salud Base San Martín de Porres tiene como objetivo definir la estructura lógica y física del software, así como la forma en que sus componentes interactúan entre sí para garantizar una atención eficiente, segura y organizada. Para ello, se aplican patrones de diseño de software y modelos de arquitectura que permiten obtener un sistema modular, escalable, mantenible y con una adecuada separación de responsabilidades.

7.8.1. Patrón de diseño
Para el desarrollo del sistema de información se emplearán los patrones de diseño Modelo Vista Controlador (MVC), Data Access Object (DAO) y una arquitectura en capas, debido a que proporcionan una estructura organizada que facilita el mantenimiento, la seguridad y la evolución del software.

Patrón Modelo Vista Controlador (MVC)

El patrón MVC permite dividir la aplicación en tres componentes principales:

Modelo (Model): representa los datos y la lógica de negocio del sistema. En este caso, gestiona la información relacionada con pacientes, citas médicas, triaje, diagnósticos, tratamientos, historias clínicas y usuarios del sistema.
Vista (View): corresponde a la interfaz con la que interactúan los usuarios, como el personal de admisión, enfermería, médicos y administradores. A través de esta capa se muestran formularios, reportes, historiales médicos y otros elementos visuales del sistema.
Controlador (Controller): funciona como intermediario entre la vista y el modelo. Recibe las solicitudes realizadas por los usuarios, procesa las acciones requeridas y coordina la comunicación con la lógica del sistema para generar una respuesta adecuada.
La implementación del patrón MVC permite separar la interfaz de usuario de la lógica de negocio, facilitando la actualización del sistema sin afectar sus demás componentes y permitiendo un desarrollo más ordenado.

Patrón Data Access Object (DAO)

El patrón DAO se utiliza para gestionar la comunicación entre la aplicación y la base de datos. Su función principal es encapsular todas las operaciones relacionadas con el almacenamiento y recuperación de información.

Cada módulo del sistema puede contar con sus propios objetos de acceso a datos, por ejemplo:

Página 33
PacienteDAO: administra las operaciones de registro, consulta y actualización de los datos del paciente.
CitaDAO: controla el almacenamiento y la gestión de las citas médicas.
HistoriaClinicaDAO: permite registrar y recuperar la información correspondiente a antecedentes, diagnósticos y tratamientos.
UsuarioDAO: gestiona la información de autenticación y permisos de acceso.
Mediante la utilización del patrón DAO se logra desacoplar la lógica del sistema de los detalles específicos de la base de datos, permitiendo realizar cambios en el sistema gestor de bases de datos o en la estructura de almacenamiento con un menor impacto sobre el resto de la aplicación.

Arquitectura en capas

La arquitectura en capas organiza el sistema en diferentes niveles funcionales, donde cada capa tiene responsabilidades específicas y se comunica únicamente con las capas correspondientes.

Las capas consideradas en el sistema son:

Capa de presentación: contiene las interfaces gráficas utilizadas por los usuarios para realizar acciones como registrar pacientes, programar citas, ingresar datos del triaje y consultar historias clínicas.
Capa de lógica de negocio: procesa las reglas del sistema médico, como la validación de datos del paciente, disponibilidad de horarios, gestión del flujo de atención y control de permisos según el tipo de usuario.
Capa de acceso a datos: implementa los objetos DAO encargados de realizar operaciones de lectura, inserción, modificación y eliminación de información almacenada.
Capa de base de datos: corresponde al sistema gestor de base de datos donde se almacenan los registros de pacientes, citas, evaluaciones de triaje, atenciones médicas, historias clínicas y usuarios.
La conexión con la base de datos se realiza siguiendo el flujo: la interfaz del usuario envía una solicitud al controlador, el controlador invoca los servicios de la lógica de negocio, esta utiliza los objetos DAO y finalmente los DAO establecen la comunicación con el gestor de base de datos mediante consultas y transacciones. Este modelo mejora la seguridad, la organización del código y la escalabilidad del sistema.

7.8.2. Diagrama de estructura compuesta
El diagrama de estructura compuesta tiene como finalidad representar la organización interna del sistema, mostrando sus componentes principales, sus partes internas y las relaciones existentes entre los diferentes módulos funcionales.

Página 34
Imagen: Diagrama de Estructura Compuesta UML "Sistema de Gestión de Atención Médica" mostrando el sistema con componentes "Admisión y Citas", "Triaje", "Atención Médica", "Historia Clínica" y "Seguridad y Autenticación", con puertos para actores "Paciente" (Verificar Identidad) y "Personal Administrativo" (Agendar Cita), y un puerto "Global Access". Las flechas muestran relaciones como "registra paciente", "deriva a triaje", "valida acceso", "Actualizar HC", "audita logs", entre otras.

Dentro del sistema de gestión de atención médica se identifican los siguientes módulos principales:

Módulo de Admisión: encargado del registro de nuevos pacientes, actualización de información personal y programación de citas médicas. Constituye el punto inicial del proceso de atención.
Módulo de Triaje: recibe la información del paciente registrado y permite al personal de enfermería ingresar datos preliminares como signos vitales, peso, talla y nivel de prioridad de atención.
Módulo de Atención Médica: es utilizado por el profesional de salud para consultar los datos del paciente, revisar los resultados del triaje, realizar el diagnóstico y registrar el tratamiento correspondiente.
Módulo de Historia Clínica Electrónica: almacena de manera centralizada toda la información médica generada durante las diferentes atenciones, permitiendo consultar antecedentes, diagnósticos anteriores, tratamientos y evolución del paciente.
La interacción entre estos módulos sigue una secuencia integrada: el paciente es registrado en admisión, posteriormente pasa al área de triaje, luego es atendido por el médico y finalmente toda la información generada se almacena y actualiza dentro de la historia clínica electrónica. Esta integración permite mantener la continuidad de la atención y reducir la pérdida de información.

7.8.3. Diagrama de tiempo
El diagrama de tiempo permite representar el comportamiento del sistema a lo largo de un período determinado, mostrando la secuencia de eventos, los cambios de estado y la duración de las actividades que ocurren durante un proceso específico.

Página 35
Imagen: Diagrama de tiempo UML mostrando líneas de vida horizontales para Paciente, Personal de Admisión (PA), Sistema de Gestión de Salud (SG), Enfermería (EN) y Médico (ME), divididas en 4 fases en un eje temporal de 0 a 70 minutos: Fase 1 "Registro y Cita" (min 0-15), Fase 2 "Triaje" (min 15-30), Fase 3 "Atención médica" (min 30-65), Fase 4 "Registro y Cierre" (min 65-70). Incluye mensajes numerados (Msg 1 a Msg 16) con sus respectivos timestamps mostrando estados como "Registrando", "Guardando", "Agendando", "Confirmada", "Checking", "Espera", "Triaje", "Eval. SVitales", "Recuperando Datos", "Consulta/Examen", "Registrando HC", "Actualizando HC".

Aplicado al proceso de atención médica, la secuencia temporal del sistema puede describirse de la siguiente manera:

Solicitud de atención: el paciente llega al centro de salud y sus datos son registrados o verificados en el módulo de admisión.
Programación y confirmación de cita: el sistema asigna una fecha, hora y profesional de salud disponible, cambiando el estado de la cita a "programada".
Evaluación de triaje: cuando el paciente ingresa al área de enfermería, se registran sus signos vitales y nivel de prioridad, actualizando su estado a "apto para atención médica".
Consulta médica: el médico accede a la información del paciente, revisa su historial clínico, realiza el diagnóstico y registra las indicaciones médicas correspondientes.
Actualización de historia clínica: una vez finalizada la consulta, el sistema almacena los datos generados durante la atención y cambia el estado del proceso a "atención finalizada".
Este diagrama permite analizar la duración y el orden de las actividades, ayudando a identificar posibles retrasos y optimizar el flujo de atención dentro del establecimiento de salud.

7.8.4. Diagrama de despliegue
El diagrama de despliegue representa la arquitectura física del sistema, mostrando los dispositivos de hardware, servidores, redes y recursos tecnológicos necesarios para su funcionamiento dentro del Centro de Salud Base San Martín de Porres.

Página 36
Imagen: Diagrama de Despliegue UML "Sistema Web de Gestión Médica" mostrando tres nodos principales: "Cliente (PC en Centro de Salud)" con un Navegador Web; "Servidor de Aplicaciones" conteniendo Plataforma de Backend (Java/Spring Boot, Python/Django) con Controlador de Usuario, Lógica de Negocio y Generador de Informes, además de un Motor de Plantillas de Frontend (HTML/JS/CSS); y "Servidor de Base de Datos" con un Sistema de Gestión de Base de Datos (PostgreSQL, MySQL) conteniendo Historia Clínica Electrónica, Citas y Horarios, Pacientes, y Usuarios y Permisos. Las conexiones se etiquetan como "Conexión HTTPS (Internet/VPN)" entre cliente y servidor de aplicaciones, y "Red Local (LAN) - Conexión de Base de Datos" entre servidor de aplicaciones y servidor de base de datos.

La infraestructura propuesta está compuesta por los siguientes elementos:

Clientes de usuario: corresponden a las computadoras utilizadas por el personal de admisión, enfermería, médicos y administradores. Desde estos equipos se accede al sistema mediante un navegador web o aplicación cliente.
Red local del centro de salud: permite la comunicación segura entre los equipos de los usuarios y el servidor donde se encuentra alojado el sistema.
Servidor de aplicaciones: contiene el software encargado de ejecutar la lógica del sistema, procesar las solicitudes de los usuarios, aplicar las reglas del negocio y administrar la comunicación con la base de datos.
Servidor de base de datos: almacena la información del sistema, incluyendo datos personales de pacientes, citas médicas, registros de triaje, historias clínicas, usuarios y permisos de acceso.
El flujo de comunicación inicia cuando un usuario realiza una solicitud desde su estación de trabajo, la cual viaja a través de la red interna hacia el servidor de aplicaciones. Este procesa la petición, consulta o actualiza la base de datos cuando es necesario y devuelve una respuesta al usuario. Esta estructura permite centralizar la información médica y garantizar un mejor control y seguridad de los datos.

7.8.5. Diagrama de componentes
El diagrama de componentes permite representar la estructura modular del software, identificando los elementos principales del sistema y las dependencias existentes entre ellos.

Página 37
Imagen: Diagrama de Componentes UML "Sistema de Gestión de Salud" mostrando los componentes "Interfaz de Usuario (Frontend)", "Módulo de Autenticación", "API Gateway / Backend", "Módulo de Citas", "Módulo de Triaje", "Módulo de Historia Clínica" y "Base de Datos", con dependencias etiquetadas como "Verificar Sesión", "Gestionar Citas", "Registrar Triaje", "Actualizar HC", "Interfaz de Triaje", "Triaje & Signos Vitales", "Interfaz de Historia Clínica" y "Datos de Historia Clínica".

Los componentes principales del sistema son los siguientes:

Frontend o interfaz de usuario: proporciona las pantallas mediante las cuales los usuarios realizan operaciones como registro de pacientes, gestión de citas, ingreso de datos de triaje, consultas médicas y administración del sistema.
Backend o servidor de aplicación: contiene los controladores, servicios y reglas de negocio responsables de procesar las solicitudes provenientes del frontend y coordinar las operaciones del sistema.
Servicios del sistema: agrupan las funcionalidades específicas relacionadas con la gestión de pacientes, programación de citas, administración de usuarios, control de acceso, atención médica e historia clínica electrónica.
Componente de acceso a datos (DAO): administra la comunicación entre los servicios del sistema y la base de datos mediante operaciones de consulta y actualización de información.
Base de datos: almacena toda la información persistente necesaria para el funcionamiento del sistema.
La relación entre los componentes sigue una arquitectura organizada donde el frontend envía solicitudes al backend, este utiliza los servicios de negocio para procesar la información, los cuales se comunican con los componentes DAO para acceder a la base de datos. Esta separación permite que cada componente pueda mantenerse o modificarse de manera independiente, favoreciendo la escalabilidad, la reutilización del código y la seguridad del sistema.

Página 38 — 8. Referencias Bibliográficas
Ministerio de Salud. (2018, 16 de enero). Norma Técnica de Salud N° 139-MINSA/2018/DGAIN: Norma Técnica de Salud para la Gestión de la Historia Clínica. Diario Oficial El Peruano. https://busquedas.elperuano.pe/normaslegales/aprueban-la-nts-n-139-minsa2018dgain-norma-tecnicade-sal-resolucion-ministerial-no-030-2018-minsa-1606551-1/

Pressman, R. S. (2010). Ingeniería del Software: Un enfoque práctico (7ma ed.). McGraw-Hill Education.

Sommerville, I. (2011). Ingeniería de Software (9na ed.). Pearson Educación.

Gómez, J., & Rivas, A. (2013). Análisis de los procesos centrados al paciente en el área de radiología: un enfoque orientado al modelado de procesos de negocio. Revista Mexicana de Ingeniería Biomédica, 34(3), 205–218.

Ministerio de Salud. (2018, 16 de enero). Norma Técnica de Salud N° 139-MINSA/2018/DGAIN: Norma Técnica de Salud para la Gestión de la Historia Clínica. Diario Oficial El Peruano. https://busquedas.elperuano.pe/normaslegales/aprueban-la-nts-n-139-minsa2018dgain-norma-tecnicade-sal-resolucion-ministerial-no-030-2018-minsa-1606551-1/

Pressman, R. S. (2010). Ingeniería del Software: Un enfoque práctico (7ma ed.). McGraw-Hill Education.

Sommerville, I. (2011). Ingeniería de Software (9na ed.). Pearson Educación.