package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentencesB {
    const val FOLDER_NAME = "Translations Sentences B"
    val categoryNames: List<String> = (1..30).map { "Translations B - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations B - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
❓ | What have the inspectors discovered during the audit? | ¿Qué han descubierto los inspectores durante la auditoría?
💼 | We have been reviewing the international trade protocols | Hemos estado revisando los protocolos de comercio internacional
🔍 | Have you noticed any statistical anomaly in the data? | ¿Has notado alguna anomalía estadística en los datos?
☕ | Would you care for some herbal infusion or espresso? | ¿Le apetecería una infusión de hierbas o un café expreso?
🚗 | The delegation has just arrived at the venue | La delegación acaba de llegar al recinto
⏰ | We haven't received official authorization yet | Todavía no hemos recibido la autorización oficial
🌧️ | It has been unusually dry throughout the spring | Ha estado inusualmente seco durante toda la primavera
💡 | She has proposed a comprehensive restructuring plan | Ha propuesto un plan integral de reestructuración
🔑 | I have verified the digital security certificates | He verificado los certificados de seguridad digital
📈 | Overall efficiency has improved by fifteen percent | La eficiencia general ha mejorado un quince por ciento
"""
            2 -> """
❓ | If you had unlimited resources, what would you build? | Si tuvieras recursos ilimitados, ¿qué construirías?
🏖️ | If circumstances allow, we will expand operations | Si las circunstancias lo permiten, ampliaremos las operaciones
💼 | Should any unforeseen issue arise, notify management | Si surgiera algún problema imprevisto, notifíquelo a la dirección
🌧️ | If the heavy snowfall continues, flights will be halted | Si continúan las fuertes nevadas, se suspenderán los vuelos
🏠 | I wouldn't rush into any binding commitment | No me apresuraría a asumir ningún compromiso vinculante
🚗 | If the shipment had arrived earlier, we would be finished | Si el envío hubiera llegado antes, ya habríamos terminado
💰 | Provided all conditions are satisfied, funding is guaranteed | Siempre que se cumplan todas las condiciones, la financiación está garantizada
📞 | In case of urgent questions, call the helpline | En caso de preguntas urgentes, llame a la línea de ayuda
✈️ | Where would you establish an international branch? | ¿Dónde establecerías una sucursal internacional?
📚 | If you review these sentence structures, speaking becomes fluid | Si repasas estas estructuras de oraciones, el habla se vuelve fluida
"""
            3 -> """
🗣️ | The spokesperson confirmed that an agreement was signed | El portavoz confirmó que se había firmado un acuerdo
❓ | She asked whether all regulatory standards had been met | Preguntó si se habían cumplido todas las normas reglamentarias
💬 | They promised that the software patch would be deployed today | Prometieron que el parche de software se desplegaría hoy
📌 | He inquired about the feasibility of the proposed timeline | Preguntó sobre la viabilidad del calendario propuesto
📣 | She advised all participants to read the documentation | Aconsejó a todos los participantes leer la documentación
🧠 | I stated that thorough research had informed our strategy | Declaré que una investigación minuciosa había fundamentado nuestra estrategia
💬 | They maintained that all environmental regulations were observed | Sostuvieron que se observaron todas las normas medioambientales
🤝 | She agreed to coordinate the interdisciplinary committee | Aceptó coordinar el comité interdisciplinar
💡 | He suggested adopting automated verification workflows | Sugirió adoptar flujos de trabajo de verificación automatizados
🔒 | She reminded the team to encrypt all outgoing files | Recordó al equipo que cifrara todos los archivos salientes
"""
            4 -> """
🏗️ | The state-of-the-art research facility was completed in 2023 | El centro de investigación de última generación se completó en 2023
📄 | The findings will be published in a scientific journal | Los hallazgos se publicarán en una revista científica
🚗 | The regional power grid is being modernized currently | La red eléctrica regional está siendo modernizada actualmente
📦 | All customer orders are tracked from warehouse to doorstep | Todos los pedidos de clientes se rastrean desde el almacén hasta la puerta
🔒 | The server room is secured with biometric authentication | La sala de servidores está protegida con autenticación biométrica
🏆 | She was recognized for outstanding contributions to medicine | Fue reconocida por sus destacadas contribuciones a la medicina
🧹 | Industrial equipment is sanitized according to strict guidelines | El equipo industrial se desinfecta según estrictas directrices
🏢 | This historic fortress was restored by master craftsmen | Esta fortaleza histórica fue restaurada por maestros artesanos
📧 | Confirmation will be transmitted upon receipt of payment | La confirmación se transmitirá al recibir el pago
⚠️ | Personal protective equipment must be worn at all times | El equipo de protección individual debe llevarse en todo momento
"""
            5 -> """
⏰ | One ought to evaluate all available options carefully | Uno debería evaluar detenidamente todas las opciones disponibles
💡 | You should have consulted the legal department beforehand | Deberías haber consultado al departamento legal de antemano
⚠️ | Visitors must not photograph sensitive technical equipment | Los visitantes no deben fotografiar equipos técnicos confidenciales
❓ | Could you provide more context on that recommendation? | ¿Podría aportar más contexto sobre esa recomendación?
🏥 | She might be consulting with clinical specialists right now | Puede que ella esté consultando con especialistas clínicos ahora mismo
🌧️ | Coastal fog may reduce visibility along the highway | La niebla costera podría reducir la visibilidad a lo largo de la autopista
🔑 | You do not need to present a physical badge; use your phone | No necesitas presentar una tarjeta física; usa tu móvil
🧠 | He must have overlooked the updated project timeline | Debe de haber pasado por alto el calendario actualizado del proyecto
🚫 | That conclusion cannot be reconciled with empirical data | Esa conclusión no se puede conciliar con los datos empíricos
🏋️ | We had better calibrate the measurement instruments first | Más vale que calibremos primero los instrumentos de medición
"""
            6 -> """
🎒 | We look forward to establishing a lasting partnership | Esperamos establecer una colaboración duradera
⏰ | Do not put off essential system diagnostics | No pospongas los diagnósticos esenciales del sistema
💡 | He came up with an ingenious structural design | Se le ocurrió un diseño estructural ingenioso
🧥 | Take off your safety gear once outside the test area | Quítese el equipo de seguridad una vez fuera del área de pruebas
📞 | Please hold while I connect you with our lead engineer | Por favor, espere mientras le comunico con nuestro ingeniero jefe
🚗 | Supplies ran out during the peak manufacturing run | Los suministros se agotaron durante la fase de máxima producción
🔍 | Researchers are looking into promising new materials | Los investigadores están estudiando nuevos materiales prometedores
🏢 | The symposium was called off due to logistical issues | El simposio fue cancelado debido a problemas logísticos
🚪 | Turn off auxiliary power units after concluding operations | Apague las unidades de energía auxiliar tras concluir las operaciones
📈 | Global demand is picking up in the renewable sector | La demanda global está repuntando en el sector renovable
"""
            7 -> """
🏢 | How many international subsidiaries does the firm manage? | ¿Cuántas filiales internacionales gestiona la empresa?
📈 | Operating revenue increased substantially this financial year | Los ingresos operativos aumentaron sustancialmente este ejercicio
💼 | We have scheduled an extraordinary board meeting for Friday | Hemos programado una reunión extraordinaria de la junta para el viernes
📄 | Please examine the confidentiality clauses with attention | Por favor, examine las cláusulas de confidencialidad con atención
🤝 | We reached a mutually beneficial licensing agreement | Llegamos a un acuerdo de licencia mutuamente beneficioso
💰 | Capital investment was approved by the executive board | La inversión de capital fue aprobada por la junta ejecutiva
📊 | Quantitative analytics forecast continued market growth | Los análisis cuantitativos prevén un continuo crecimiento del mercado
🎯 | Corporate integrity remains our foundational guiding principle | La integridad corporativa sigue siendo nuestro principio rector fundamental
✉️ | We appreciate your thorough assessment of the proposal | Agradecemos su exhaustiva valoración de la propuesta
👥 | Fostering team innovation leads to extraordinary results | Fomentar la innovación en equipo conduce a resultados extraordinarios
"""
            8 -> """
✈️ | Boarding will commence at terminal gate number seven | El embarque comenzará en la puerta número siete de la terminal
🧳 | Please verify that your baggage complies with airline policy | Por favor, compruebe que su equipaje cumple la política de la aerolínea
🏨 | We reserved a premier suite with panoramic city views | Reservamos una suite premier con vistas panorámicas de la ciudad
🛏️ | Concierge services are available twenty-four hours a day | Los servicios de conserjería están disponibles las 24 horas del día
🗺️ | Could you indicate prominent cultural sites on the map? | ¿Podría señalar lugares culturales destacados en el mapa?
🚆 | Does this express service connect directly to the airport? | ¿Este servicio exprés conecta directamente con el aeropuerto?
🎟️ | Two admission passes for the modern art gallery, please | Dos pases de entrada para la galería de arte moderno, por favor
🚕 | Please take us to the historic amphitheater | Por favor, llévenos al anfiteatro histórico
💳 | We accept all major international payment cards | Aceptamos las principales tarjetas de pago internacionales
📍 | The botanical garden is located within walking distance | El jardín botánico está ubicado a poca distancia a pie
"""
            9 -> """
☕ | I begin the day with fresh citrus juice and silent reflection | Comienzo el día con zumo de cítricos fresco y reflexión silenciosa
🏃 | She engages in distance running along the shoreline | Practica carrera de fondo a lo largo de la costa
🍳 | A balanced breakfast provides sustained energy for the day | Un desayuno equilibrado proporciona energía sostenida para el día
🚗 | Commuting by light rail reduces daily travel stress | Desplazarse en tren ligero reduce el estrés del viaje diario
🎧 | Informative podcasts stimulate critical curiosity | Los podcasts informativos estimulan la curiosidad crítica
🛒 | We purchase fresh organic produce from local growers | Compramos productos ecológicos frescos a agricultores locales
🍲 | A hearty vegetable stew was prepared for tonight's dinner | Se preparó un sabroso estofado de verduras para la cena de esta noche
🧹 | An orderly environment promotes intellectual clarity | Un entorno ordenado promueve la claridad intelectual
📺 | We watched an inspiring scientific documentary | Vimos un inspirador documental científico
🛏️ | Consistent sleep schedules support cognitive well-being | Los horarios de sueño regulares favorecen el bienestar cognitivo
"""
            10 -> """
🌡️ | Atmospheric readings indicate twenty-four degrees Celsius | Las lecturas atmosféricas indican veinticuatro grados centígrados
☀️ | Brilliant sunlight illuminated the entire coastal valley | Una luz solar brillante iluminó todo el valle costero
🌧️ | Heavy rainfall replenished the regional aquifers | Fuertes lluvias reabastecieron los acuíferos regionales
⚡ | Powerful lightning illuminated the towering clouds | Fuertes relámpagos iluminaron los imponentes nubarrones
❄️ | Fresh snow covered the high mountain passes | Nieve fresca cubrió los altos puertos de montaña
🧥 | Thermal outerwear protects against severe mountain winds | La ropa térmica de abrigo protege contra los fuertes vientos de montaña
🌈 | A magnificent rainbow arched across the valley | Un magnífico arcoíris se arqueó a través del valle
🍂 | Amber leaves blanket the peaceful forest trails | Hojas de color ámbar cubren los tranquilos senderos del bosque
🏖️ | The ocean breeze brought welcome relief from the warmth | La brisa marina trajo un bienvenido alivio del calor
🌸 | Wild flora blossoms across the meadows in spring | La flora silvestre florece por las praderas en primavera
"""
            11 -> """
🏥 | Comprehensive wellness screenings identify early risks | Los chequeos integrales de bienestar identifican riesgos tempranos
🤕 | Preventative lifestyle habits alleviate chronic strain | Los hábitos de vida preventivos alivian la tensión crónica
💊 | Follow medication instructions with strict precision | Siga las instrucciones de la medicación con estricta precisión
🩺 | The cardiologist performed a thorough diagnostic exam | El cardiólogo realizó un examen diagnóstico exhaustivo
🌡️ | Body temperature regulation is essential for overall health | La regulación de la temperatura corporal es esencial para la salud general
🩹 | Keep minor skin cuts clean and properly protected | Mantén los pequeños cortes en la piel limpios y debidamente protegidos
🦷 | Daily dental hygiene prevents long-term complications | La higiene dental diaria previene complicaciones a largo plazo
🤧 | Adequate rest and fluids strengthen immune resilience | El descanso y los líquidos adecuados fortalecen la resistencia inmunitaria
🥗 | Nutrient-rich meals support sustained cellular energy | Las comidas ricas en nutrientes respaldan una energía celular sostenida
🏃 | Moderate daily exercise enhances cardiac fitness | El ejercicio diario moderado mejora la forma física cardíaca
"""
            12 -> """
🛒 | Fresh organic goods are displayed in the produce aisle | Los productos ecológicos frescos se exhiben en el pasillo de frutería
🛍️ | I am looking for a distinctive retirement present | Estoy buscando un regalo de jubilación distintivo
👗 | This tailored suit fits you with great elegance | Este traje a medida te queda con gran elegancia
👖 | Tailoring adjustments are available upon request | Los ajustes de sastrería están disponibles a petición
🏷️ | Promotional discounts apply to seasonal collections | Los descuentos promocionales se aplican a las colecciones de temporada
💳 | Secure payment terminals ensure transaction privacy | Los terminales de pago seguros garantizan la privacidad de la transacción
🧾 | Digital invoices are sent directly to your registered email | Las facturas digitales se envían directamente a su correo registrado
📦 | Express courier delivery ensures arrival within one day | La entrega por mensajería urgente asegura la llegada en un día
🏪 | The flagship store offers personalized shopping assistance | La tienda insignia ofrece asistencia personalizada en compras
🥖 | Traditional sourdough is prepared with natural fermentation | La masa madre tradicional se prepara con fermentación natural
"""
            13 -> """
🍽️ | May we examine the sommelier's recommended pairings? | ¿Podemos examinar los maridajes recomendados por el sumiller?
🥗 | A starter of roasted heirloom vegetables and herbs | Un entrante de verduras tradicionales asadas y hierbas
🥩 | The chef prepared the prime cut with exquisite skill | El chef preparó el corte de primera con exquisita maestría
🍷 | A glass of chilled sparkling mineral water with lime | Una copa de agua mineral con gas fría con lima
🐟 | Today's special features fresh wild-caught cod | La sugerencia de hoy incluye bacalao fresco salvaje
🌾 | Please notify the waitstaff of any food sensitivities | Por favor, avise a los camareros de cualquier intolerancia alimentaria
☕ | Two freshly roasted coffees and the bill, please | Dos cafés recién tostados y la cuenta, por favor
👨‍🍳 | Our sincere appreciation for a memorable dining experience | Nuestro sincero agradecimiento por una experiencia gastronómica memorable
🍝 | Handmade ravioli served in a delicate herb butter sauce | Raviolis caseros servidos en una delicada salsa de mantequilla y hierbas
🥣 | Roasted butternut squash soup garnished with toasted seeds | Sopa de calabaza asada decorada con semillas tostadas
"""
            14 -> """
🏫 | Academic research elevates the quality of higher education | La investigación académica eleva la calidad de la educación superior
📚 | Scholarly articles undergo rigorous peer review | Los artículos académicos se someten a una rigurosa revisión por pares
📝 | The thesis defense is scheduled for the end of the term | La defensa de tesis está programada para el final del trimestre
🎓 | Doctoral programs cultivate world-class specialized talent | Los programas de doctorado cultivan talento especializado de primer nivel
📖 | Analytical reading develops critical discernment and clarity | La lectura analítica desarrolla el discernimiento crítico y la claridad
🔬 | Laboratory findings were validated through replication | Los hallazgos de laboratorio fueron validados mediante replicación
🗣️ | Clear articulation is essential in academic presentations | La articulación clara es esencial en las presentaciones académicas
🧠 | Deep conceptual synthesis outlasts simple memorization | La síntesis conceptual profunda perdura más que la simple memorización
👩‍🏫 | The symposium welcomed eminent researchers from abroad | El simposio dio la bienvenida a eminentes investigadores del extranjero
📑 | Methodological rigor underpins scientific integrity | El rigor metodológico sustenta la integridad científica
"""
            15 -> """
💻 | Cloud migration ensures robust infrastructure redundancy | La migración a la nube asegura una sólida redundancia de infraestructura
📶 | High-capacity optical networks enable rapid data transfer | Las redes ópticas de gran capacidad permiten una rápida transferencia de datos
🔒 | End-to-end cryptographic protocols protect communication | Los protocolos criptográficos de extremo a extremo protegen la comunicación
📱 | Regular system updates resolve critical vulnerabilities | Las actualizaciones periódicas del sistema resuelven vulnerabilidades críticas
💾 | Automated backups protect against unexpected data loss | Las copias de seguridad automáticas protegen contra pérdidas de datos imprevistas
📧 | Secure email filters block malicious cyber threats | Los filtros de correo seguro bloquean amenazas cibernéticas maliciosas
🔋 | Advanced power management maximizes device efficiency | La gestión de energía avanzada maximiza la eficiencia del dispositivo
🎧 | Noise-cancelling headphones improve focus in open spaces | Los auriculares con cancelación de ruido mejoran la concentración en espacios abiertos
☁️ | Containerized systems provide flexible scalability | Los sistemas en contenedores proporcionan una escalabilidad flexible
🖥️ | High-resolution displays enhance visual design work | Las pantallas de alta resolución mejoran el trabajo de diseño visual
"""
            16 -> """
⚽ | Tactical discipline is key to tournament victories | La disciplina táctica es clave para las victorias en torneos
🏀 | Fast transitions and precision passing dominate the game | Las transiciones rápidas y los pases de precisión dominan el juego
🎾 | Controlled baseline rallies require stamina and focus | Los peloteos controlados desde el fondo requieren resistencia y concentración
🏊 | Swimming improves full-body cardiovascular endurance | La natación mejora la resistencia cardiovascular de todo el cuerpo
🚴 | Long-distance cycling builds aerobic power and mental grit | El ciclismo de larga distancia desarrolla potencia aeróbica y determinación
🏃 | Steady pacing is essential during a full marathon | Un ritmo constante es esencial durante un maratón completo
🏆 | The athletic delegation celebrated historic achievements | La delegación atlética celebró logros históricos
🥊 | Disciplined martial practice cultivates inner calm and respect | La práctica marcial disciplinada cultiva la calma interior y el respeto
🏋️ | Functional resistance training supports joint stability | El entrenamiento de resistencia funcional favorece la estabilidad articular
⛳ | Strategic course planning improves golf performance | La planificación estratégica del campo mejora el rendimiento en golf
"""
            17 -> """
🎵 | Symphonic music evokes profound and universal emotions | La música sinfónica evoca emociones profundas y universales
🎸 | Acoustic guitar melodies bring warmth to intimate rooms | Las melodías de guitarra acústica aportan calidez a salas íntimas
🎹 | Classical piano interpretations demand refined sensitivity | Las interpretaciones de piano clásico exigen una sensibilidad refinada
🎨 | Art exhibitions stimulate creative and cultural dialogue | Las exposiciones de arte estimulan el diálogo creativo y cultural
🎬 | Cinematography creates unforgettable atmosphere on screen | La cinematografía crea una atmósfera inolvidable en la pantalla
🎟️ | Advance tickets guarantee admission to premier showcases | Las entradas anticipadas garantizan el acceso a muestras exclusivas
🎭 | Classical theater reflects timeless aspects of human life | El teatro clásico refleja aspectos intemporales de la vida humana
🎻 | The string ensemble delivered an exquisite performance | El conjunto de cuerda ofreció una actuación exquisita
📚 | Great literature explores the complexities of character | La gran literatura explora las complejidades del carácter
📸 | Photography captures the poetry of everyday moments | La fotografía captura la poesía de los momentos cotidianos
"""
            18 -> """
🤝 | Mutual trust is the bedrock of lifelong friendship | La confianza mutua es la base de una amistad duradera
❤️ | Sincere empathy and patience foster lasting relationships | La empatía sincera y la paciencia fomentan relaciones duraderas
👨‍👩‍👧‍👦 | Celebrating shared traditions strengthens family unity | Celebrar tradiciones compartidas fortalece la unión familiar
💬 | Compassionate dialogue overcomes deep misunderstandings | El diálogo compasivo supera profundos malentendidos
👂 | Attentive listening offers genuine comfort to others | La escucha atenta ofrece un auténtico consuelo a los demás
👶 | Welcoming a child brings immense joy and purpose | Dar la bienvenida a un hijo trae una inmensa alegría y propósito
🎂 | Milestone gatherings celebrate precious shared memories | Los encuentros señalados celebran valiosos recuerdos compartidos
🧑‍🤝‍🧑 | Reliable companions support you through challenging times | Los compañeros fiables te apoyan en momentos difíciles
🎁 | Thoughtful sincerity means far more than material cost | La sinceridad pensada significa mucho más que el coste material
💔 | Patience and open hearts heal relational fractures | La paciencia y los corazones abiertos curan las fracturas en las relaciones
"""
            19 -> """
🌍 | Environmental stewardship is essential for the future | El cuidado del medio ambiente es esencial para el futuro
♻️ | Circular economic models minimize resource depletion | Los modelos económicos circulares minimizan el agotamiento de recursos
🌳 | Native tree planting revitalizes natural watersheds | La plantación de árboles autóctonos revitaliza las cuencas naturales
⚡ | Renewable clean energy powers modern communities | La energía limpia renovable alimenta a las comunidades modernas
💧 | Responsible water management safeguards future harvests | La gestión responsable del agua salvaguarda las cosechas futuras
🚗 | Electric public transit improves urban air quality | El transporte público eléctrico mejora la calidad del aire urbano
🦁 | Protecting nature reserves preserves vital biodiversity | Proteger las reservas naturales preserva la biodiversidad vital
🌊 | Healthy marine ecosystems protect coastal shorelines | Los ecosistemas marinos sanos protegen las costas
🚲 | Bicycle-friendly streets foster vibrant communities | Las calles adaptadas para bicicletas fomentan comunidades vibrantes
🍃 | Spending time in nature restores mental equilibrium | Pasar tiempo en la naturaleza restaura el equilibrio mental
"""
            20 -> """
💼 | I came across an important historical document today | Me encontré con un documento histórico importante hoy
🔍 | Let us identify the fundamental cause of the anomaly | Identifiquemos la causa fundamental de la anomalía
🚗 | The engine broke down during the high-altitude test | El motor se averió durante la prueba de gran altitud
🚪 | Trustworthy partners never let their teams down | Los socios de confianza nunca defraudan a sus equipos
📞 | I will return your call before the end of the day | Devolveré su llamada antes del final del día
📝 | Please note down these vital operating procedures | Por favor, anote estos procedimientos operativos vitales
🧥 | Put on your heavy jacket before going outside | Ponte la chaqueta gruesa antes de salir fuera
💡 | The committee turned down the initial proposal | El comité rechazó la propuesta inicial
⏰ | Time is running out to submit the final application | Se acaba el tiempo para presentar la solicitud final
✈️ | We accompanied our colleagues to the departure lounge | Acompañamos a nuestros colegas a la sala de salidas
"""
            21 -> """
📌 | That certification exam was a piece of cake | Ese examen de certificación fue pan comido
🌧️ | It is raining cats and dogs across the entire valley | Está lloviendo a cántaros en todo el valle
💰 | High-end laboratory equipment costs an arm and a leg | El equipo de laboratorio de gama alta cuesta un ojo de la cara
👂 | Share your thoughts; we are all ears | Comparte tus pensamientos; somos todo oídos
⏳ | Better late than never when making things right | Más vale tarde que nunca a la hora de hacer las cosas bien
🥊 | Your analytical assessment hit the nail on the head | Tu valoración analítica dio en el clavo
🥱 | After concluding research, it is time to hit the sack | Tras concluir la investigación, es hora de irse a dormir
🧊 | A witty comment served to break the ice smoothly | Un comentario ingenioso sirvió para romper el hielo con suavidad
🤫 | Do not spill the beans about the upcoming project | No desveles el secreto sobre el próximo proyecto
⚡ | The breakthrough announcement came out of the blue | El anuncio del avance llegó totalmente por sorpresa
"""
            22 -> """
💰 | Financial planning ensures lasting family stability | La planificación financiera asegura una estabilidad familiar duradera
💳 | Pay off credit balances systematically every month | Liquida los saldos de crédito sistemáticamente cada mes
🏠 | Fixed-rate loans provide peace of mind for homeowners | Los préstamos a tipo fijo brindan tranquilidad a los propietarios
📈 | Broad index investments yield sustainable long-term growth | Las inversiones en índices amplios generan un crecimiento sostenible a largo plazo
📉 | Inflation impacts the cost of everyday consumer goods | La inflación afecta al coste de los bienes de consumo cotidianos
💵 | Maintain a liquid emergency reserve for unforeseen costs | Mantén una reserva de emergencia líquida para gastos imprevistos
🧾 | Audit your financial expenditures on a monthly basis | Audita tus gastos financieros mensualmente
🏧 | Secure payment terminals are placed in the lobby | Los terminales de pago seguros están situados en el vestíbulo
💸 | Prudent budget management leads to true financial freedom | La gestión presupuestaria prudente conduce a una auténtica libertad financiera
📊 | Financial markets demonstrated resilience this quarter | Los mercados financieros mostraron resistencia este trimestre
"""
            23 -> """
⚖️ | Equal protection under the law is a constitutional right | La igual protección ante la ley es un derecho constitucional
🏛️ | The supreme court delivered its definitive judgment | El tribunal supremo dictó su sentencia definitiva
👨‍⚖️ | The judge examined the documented evidence carefully | El juez examinó detenidamente las pruebas documentales
📄 | Contractual agreements require careful legal review | Los acuerdos contractuales requieren una cuidadosa revisión legal
🔒 | Law enforcement executed the mandate with professionalism | Las fuerzas de seguridad ejecutaron el mandato con profesionalidad
🔍 | Forensic procedures preserved the integrity of the evidence | Los procedimientos forenses preservaron la integridad de las pruebas
🗣️ | The witness provided clear and honest testimony | El testigo aportó un testimonio claro y sincero
🛡️ | Civil liberties are the bedrock of a free society | Las libertades civiles son la base de una sociedad libre
📜 | Statutory laws define civic rights and responsibilities | Las leyes estatutarias definen los derechos y responsabilidades cívicas
💼 | Both organizations signed a comprehensive settlement | Ambas organizaciones firmaron un acuerdo integral
"""
            24 -> """
🔬 | Empirical scientific discovery advances human knowledge | El descubrimiento científico empírico avanza el conocimiento humano
🚀 | Planetary probes return high-resolution data from space | Las sondas planetarias envían datos de alta resolución desde el espacio
🧬 | Genetic research helps treat hereditary disorders | La investigación genética ayuda a tratar trastornos hereditarios
🔭 | Advanced observatories map distant planetary systems | Los observatorios avanzados cartografían sistemas planetarios lejanos
🧪 | Chemical compounds must be handled with appropriate safety | Los compuestos químicos deben manipularse con la seguridad adecuada
⚡ | Sustainable clean energy replaces fossil fuel dependence | La energía limpia sostenible sustituye la dependencia de combustibles fósiles
🌱 | Photosynthesis powers plant growth and oxygen production | La fotosíntesis impulsa el crecimiento vegetal y la producción de oxígeno
🤖 | Machine intelligence accelerates complex scientific analysis | La inteligencia artificial acelera el análisis científico complejo
🌊 | Deep marine ecosystems hold fascinating scientific discoveries | Los ecosistemas marinos profundos albergan fascinantes descubrimientos científicos
🌋 | Geological sensors monitor active volcanic activity | Sensores geológicos vigilan la actividad volcánica activa
"""
            25 -> """
🏛️ | Classical architecture established enduring design principles | La arquitectura clásica estableció principios de diseño imperecederos
⚔️ | Historic treaties shaped international borders over centuries | Tratados históricos moldearon las fronteras internacionales durante siglos
🏰 | Medieval castles stood as strategic regional strongholds | Los castillos medievales se alzaban como estratégicas fortalezas regionales
📜 | Ancient manuscripts preserve valuable cultural knowledge | Manuscritos antiguos conservan valiosos conocimientos culturales
🚢 | Maritime trade connected distant global civilizations | El comercio marítimo conectó lejanas civilizaciones mundiales
🏭 | The industrial age revolutionized manufacturing and transit | La era industrial revolucionó la manufactura y el transporte
🗽 | Universal human rights are enshrined in international charters | Los derechos humanos universales están consagrados en cartas internacionales
📖 | Studying history helps societies make informed choices | Estudiar historia ayuda a las sociedades a tomar decisiones fundamentadas
🏛️ | Ancient monuments stand as testaments to human ambition | Los monumentos antiguos se alzan como testimonios de la ambición humana
🗺️ | Historical maps reveal the evolution of geographic knowledge | Los mapas históricos revelan la evolución del conocimiento geográfico
"""
            26 -> """
🏠 | Country retreats offer quiet serenity away from the city | Los retiros en el campo ofrecen tranquila serenidad lejos de la ciudad
🌆 | Cosmopolitan capitals pulse with cultural energy | Las capitales cosmopolitas vibran con energía cultural
🚗 | Integrated transit networks reduce daily traffic delays | Redes de transporte integradas reducen los retrasos del tráfico diario
🌳 | Urban green spaces enhance community health and well-being | Los espacios verdes urbanos mejoran la salud y el bienestar comunitario
🚲 | Dedicated bicycle lanes promote safe sustainable commuting | Los carriles bici exclusivos promueven desplazamientos seguros y sostenibles
🏢 | Modern high-rises define the contemporary skyline | Rascacielos modernos definen el horizonte contemporáneo
🚜 | Sustainable farming practices protect agricultural soil | Las prácticas agrícolas sostenibles protegen el suelo agrícola
🚇 | High-speed underground rail links distant districts | El metro de alta velocidad conecta distritos lejanos
🏡 | Cultivating home gardens brings daily tranquility | Cultivar jardines en casa aporta tranquilidad diaria
🛒 | Farmers markets showcase the freshest seasonal harvest | Los mercados de agricultores muestran la cosecha de temporada más fresca
"""
            27 -> """
✈️ | Travel expands your horizons and cultural understanding | Viajar amplía tus horizontes y la comprensión cultural
📸 | Photography captures unrepeatable moments in time | La fotografía captura momentos irrepetibles en el tiempo
🍳 | Cooking homemade dishes combines creativity and nutrition | Cocinar platos caseros combina creatividad y nutrición
🪴 | Caring for indoor plants creates a calming living space | Cuidar plantas de interior crea un espacio habitable relajante
♟️ | Chess develops strategic thinking and deep foresight | El ajedrez desarrolla el pensamiento estratégico y una profunda previsión
📚 | Reading great literature broadens your worldview | Leer gran literatura amplía tu visión del mundo
🎨 | Painting is a wonderful medium for personal expression | La pintura es un medio maravilloso para la expresión personal
🎣 | Fishing along a quiet stream brings meditative peace | Pescar junto a un arroyo tranquilo aporta paz meditativa
🥾 | Mountain trekking builds stamina and mental resilience | El senderismo de montaña desarrolla resistencia y fortaleza mental
🧩 | Logic puzzles stimulate creative analytical thinking | Los acertijos lógicos estimulan el pensamiento analítico creativo
"""
            28 -> """
🗣️ | Articulate communication enhances your influence | La comunicación clara y elocuente mejora tu influencia
🤝 | Active listening is the foundation of mutual respect | La escucha activa es la base del respeto mutuo
📧 | Professional correspondence demands clarity and politeness | La correspondencia profesional exige claridad y cortesía
🎯 | Public speaking poise grows through steady practice | El aplomo al hablar en público crece mediante la práctica constante
💡 | Present your ideas with conviction and enthusiasm | Presenta tus ideas con convicción y entusiasmo
👀 | Direct eye contact conveys sincere engagement | El contacto visual directo transmite un compromiso sincero
📱 | Mindful messaging prevents digital misunderstandings | La mensajería consciente previene malentendidos digitales
🤫 | Strategic silence can emphasize important concepts | El silencio estratégico puede enfatizar conceptos importantes
🤝 | Empathy helps resolve complex workplace disputes | La empatía ayuda a resolver disputas laborales complejas
📢 | Clear vocal projection keeps listeners attentive | La proyección vocal clara mantiene a los oyentes atentos
"""
            29 -> """
🧭 | Guided by strong ethical and moral principles | Guiado por sólidos principios éticos y morales
🤝 | Personal integrity means keeping your promises | La integridad personal significa cumplir tus promesas
💖 | Showing kindness enriches every human community | Mostrar amabilidad enriquece a toda comunidad humana
⚖️ | Fairness and equality must guide social policies | La justicia y la igualdad deben guiar las políticas sociales
🌱 | Continuous self-improvement unlocks your true potential | La superación personal continua desbloquea tu verdadero potencial
🛡️ | Courage means taking action despite your fears | El valor significa actuar a pesar de tus miedos
⏳ | Patience and persistence overcome long-term challenges | La paciencia y la perseverancia superan desafíos a largo plazo
🌟 | Daily gratitude transforms ordinary routines into joy | La gratitud diaria transforma las rutinas ordinarias en alegría
🎯 | Clear goals concentrate human effort and success | Las metas claras concentran el esfuerzo humano y el éxito
🕊️ | Mutual respect creates enduring peace among people | El respeto mutuo crea una paz duradera entre las personas
"""
            30 -> """
🇬🇧 | Consistent daily practice guarantees English fluency | La práctica diaria constante garantiza la fluidez en inglés
🎧 | Listening to natural podcasts refines your comprehension | Escuchar podcasts naturales afina tu comprensión
📖 | Reading diverse literature enriches your vocabulary | Leer literatura variada enriquece tu vocabulario
🗣️ | Speaking confidently turns knowledge into second nature | Hablar con seguridad convierte el conocimiento en una segunda naturaleza
✍️ | Writing structured sentences solidifies grammar mastery | Escribir oraciones estructuradas consolida el dominio gramatical
🧠 | Thinking directly in English eliminates translation lag | Pensar directamente en inglés elimina el retraso de la traducción
🎬 | Authentic video media reveals natural idioms and rhythm | Los medios de vídeo auténticos revelan modismos y ritmos naturales
🎯 | Stay dedicated to your daily language learning goals | Mantente dedicado a tus objetivos diarios de aprendizaje de idiomas
⚡ | Persistence and enthusiasm lead to complete mastery | La perseverancia y el entusiasmo conducen al dominio completo
🎉 | Fantastic achievement completing this entire translation series! | ¡Fantástico logro completando toda esta serie de traducción!
"""
            else -> ""
        }
    }
}
