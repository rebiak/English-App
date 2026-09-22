package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences7 {
    const val FOLDER_NAME = "Translations Sentences 7"
    val categoryNames: List<String> = (1..30).map { "Translations 7 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations 7 - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
⏱️ | How long have you known about this situation? | ¿Cuánto tiempo llevas enterado de esta situación?
👈 | I have been investigating this matter all week | He estado investigando este asunto toda la semana
❓ | Has the committee made a formal announcement? | ¿Ha hecho el comité un anuncio oficial?
🚫 | They haven't reached a unanimous conclusion | No han llegado a una conclusión unánime
🧠 | Have you ever considered living abroad? | ¿Alguna vez has considerado vivir en el extranjero?
📞 | She has just called to confirm the appointment | Acaba de llamar para confirmar la cita
⏰ | We have waited patiently for their answer | Hemos esperado pacientemente su respuesta
🥶 | It has been unusually cold this winter | Ha hecho un frío inusual este invierno
💡 | They have implemented new efficiency protocols | Han implementado nuevos protocolos de eficiencia
📈 | Our team has accomplished remarkable milestones | Nuestro equipo ha alcanzado hitos extraordinarios
"""
            2 -> """
🎯 | If you had the opportunity, where would you travel? | Si tuvieras la oportunidad, ¿a dónde viajarías?
🏖️ | If weather permits, we will hold the event outside | Si el tiempo lo permite, celebraremos el evento al aire libre
💼 | If you require additional resources, inform us | Si necesitas recursos adicionales, infórmanos
🌧️ | If it rains continuously, the river may overflow | Si llueve continuamente, el río podría desbordarse
🏠 | I wouldn't underestimate their capability | Yo no subestimaría su capacidad
🚗 | If we had taken the bypass, we would be there now | Si hubiéramos tomado la circunvalación, ya estaríamos allí
💰 | If funding is secured, construction will begin | Si se asegura la financiación, comenzará la construcción
📞 | If he contacts you, please forward his message | Si se pone en contacto contigo, reenvía su mensaje
✈️ | Where would you study if tuition were free? | ¿Dónde estudiarías si la matrícula fuera gratuita?
📚 | If you master these principles, everything falls into place | Si dominas estos principios, todo encaja en su lugar
"""
            3 -> """
🗣️ | The spokesperson announced that negotiations had resumed | El portavoz anunció que las negociaciones se habían reanudado
❓ | She asked whether we had reviewed the revised terms | Preguntó si habíamos revisado los términos corregidos
💬 | They stated that they would honor all prior commitments | Declararon que cumplirían todos los compromisos previos
↔️ | He inquired about the expected delivery timeline | Preguntó por el plazo de entrega previsto
📣 | She advised us to verify all figures before publishing | Nos aconsejó verificar todas las cifras antes de publicar
🧗 | I explained that technical difficulties had caused the delay | Expliqué que dificultades técnicas habían causado el retraso
💬 | They maintained that their calculations were correct | Sostuvieron que sus cálculos eran correctos
🤝 | She agreed to participate in the international panel | Aceptó participar en el panel internacional
💡 | He suggested exploring alternative energy solutions | Sugirió explorar soluciones de energía alternativa
🔒 | She reminded the staff to secure confidential records | Recordó al personal que protegiera los registros confidenciales
"""
            4 -> """
🏗️ | The solar facility was inaugurated last autumn | La instalación solar fue inaugurada el otoño pasado
📅 | The annual report will be distributed tomorrow | El informe anual se distribuirá mañana
🚇 | The subway system is being modernized gradually | El sistema de metro está siendo modernizado gradualmente
📦 | All customer requests are processed systematically | Todas las peticiones de clientes se procesan sistemáticamente
🔒 | The archives are accessible only with authorization | Los archivos son accesibles solo con autorización
🏆 | He was awarded the prize for scientific innovation | Fue galardonado con el premio a la innovación científica
📰 | The laboratories are sanitized on a daily schedule | Los laboratorios se desinfectan según un horario diario
🏢 | This historic monument was preserved by the state | Este monumento histórico fue preservado por el estado
📧 | Official confirmation will be delivered by email | La confirmación oficial se enviará por correo electrónico
⚠️ | Strict safety guidelines must be maintained | Se deben mantener estrictas directrices de seguridad
"""
            5 -> """
⏰ | You ought to verify references before hiring | Deberías comprobar las referencias antes de contratar
🧑‍💼 | You should have consulted the chief engineer | Deberías haber consultado al ingeniero jefe
👓 | Personnel must not enter without protective goggles | El personal no debe entrar sin gafas de protección
👉 | Could you elaborate on that particular point? | ¿Podría profundizar en ese punto en particular?
🏥 | She might be consulting with the medical board | Puede que ella esté consultando con la junta médica
🌧️ | Thunderstorms may develop towards late afternoon | Puede que se desarrollen tormentas eléctricas hacia el final de la tarde
🔑 | You do not have to register in advance | No tienes que registrarte por adelantado
🧠 | He must have misunderstood the original instructions | Debe de haber entendido mal las instrucciones originales
🚫 | That hypothesis cannot be sustained with data | Esa hipótesis no se puede sostener con datos
📅 | We had better finalize the agenda today | Más vale que cerremos el orden del día hoy
"""
            6 -> """
🎒 | We look forward to establishing fruitful cooperation | Esperamos con ganas establecer una fructífera cooperación
⏰ | Do not put off critical maintenance routines | No pospongas las rutinas críticas de mantenimiento
💡 | She came up with a comprehensive workflow design | Se le ocurrió un diseño integral de flujo de trabajo
🧥 | Take off your safety gear once outside the zone | Quítate el equipo de seguridad una vez fuera de la zona
📋 | Please hold while I connect you with the specialist | Por favor, espere mientras le comunico con el especialista
🚗 | We ran out of spare components unexpectedly | Nos quedamos sin piezas de repuesto de forma inesperada
🔍 | I am looking into potential vendor alternatives | Estoy investigando posibles proveedores alternativos
📅 | They called off the press briefing until tomorrow | Cancelaron la rueda de prensa hasta mañana
🚪 | Switch off testing equipment after completing tests | Apague los equipos de prueba tras finalizar las pruebas
📈 | Productivity is looking up across all sectors | La productividad está mejorando en todos los sectores
"""
            7 -> """
🏦 | How many regional branches does the bank operate? | ¿Cuántas sucursales regionales opera el banco?
📈 | Fiscal quarterly revenue surpassed expectations | Los ingresos del trimestre fiscal superaron las expectativas
💼 | We have scheduled a board assembly for next month | Hemos programado una asamblea de la junta para el próximo mes
📝 | Please inspect the revised contract clauses | Por favor, inspeccione las cláusulas revisadas del contrato
🔑 | We formed a strategic alliance with key partners | Formamos una alianza estratégica con socios clave
💰 | The investment proposal was approved unanimously | La propuesta de inversión fue aprobada por unanimidad
📁 | Statistical projections indicate sustained expansion | Las proyecciones estadísticas indican una expansión sostenida
🎯 | Integrity and reliability guide our corporate vision | La integridad y la fiabilidad guían nuestra visión corporativa
👈 | We appreciate your prompt feedback on this draft | Agradecemos sus rápidos comentarios sobre este borrador
👥 | Empowering employees boosts workplace satisfaction | Empoderar a los empleados aumenta la satisfacción laboral
"""
            8 -> """
🚪 | Boarding will commence at terminal gate four | El embarque comenzará en la puerta cuatro de la terminal
🧳 | Please ensure your luggage tags are clearly marked | Asegúrese de que las etiquetas de su equipaje estén claramente marcadas
🏨 | We booked an executive suite for three evenings | Reservamos una suite ejecutiva para tres noches
⏰ | Room service is accessible around the clock | El servicio de habitaciones está accesible las 24 horas
🗺️ | Could you mark the main architectural sites? | ¿Podría marcar los principales sitios arquitectónicos?
🎓 | Does this express service stop at the university? | ¿Este servicio exprés para en la universidad?
🎟️ | Two admission passes for the historic gallery, please | Dos pases de entrada para la galería histórica, por favor
🚗 | Please direct the driver to the convention hall | Por favor, indique al conductor la sala de convenciones
💳 | Electronic contactless transactions are accepted | Se aceptan transacciones electrónicas sin contacto
🦶 | The harbor promenade is within easy walking distance | El paseo marítimo está a poca distancia a pie
"""
            9 -> """
🍵 | I start the morning with fresh fruit and herbal tea | Empiezo la mañana con fruta fresca e infusión de hierbas
🏃 | She walks five kilometers along the shoreline | Camina cinco kilómetros a lo largo de la costa
⚡ | A nutritious breakfast provides sustained energy | Un desayuno nutritivo proporciona energía sostenida
📰 | Public transportation eases daily urban commuting | El transporte público facilita el desplazamiento diario urbano
🎧 | Educational podcasts stimulate critical thinking | Los podcasts educativos estimulan el pensamiento crítico
🛒 | We patronize local cooperative markets regularly | Compramos en cooperativas locales con regularidad
🍲 | Fresh Mediterranean dishes were served at dinner | Se sirvieron platos mediterráneos frescos en la cena
🧹 | Maintaining an organized workspace optimizes focus | Mantener un espacio ordenado optimiza la concentración
📺 | We watched an insightful scientific lecture | Vimos una esclarecedora conferencia científica
🛏️ | Consistent sleep schedules enhance cognitive health | Los horarios de sueño regulares mejoran la salud cognitiva
"""
            10 -> """
🌡️ | The regional temperature hovered at eighteen degrees | La temperatura regional rondó los dieciocho grados
☀️ | Radiant sunshine illuminated the mountain valley | El sol radiante iluminó el valle de montaña
🌧️ | Intense rainfall nourished the dry countryside | La lluvia intensa nutrió el campo seco
⚡ | Powerful lightning flashed across the evening skyline | Fuertes relámpagos brillaron en el horizonte vespertino
❄️ | Mountain passes were temporarily closed by snowfall | Los puertos de montaña se cerraron temporalmente por nevadas
🧥 | High-altitude hikes require windproof apparel | Las rutas de alta montaña requieren ropa cortavientos
🌈 | A vivid rainbow spanned the western horizon | Un arcoíris nítido abarcó el horizonte occidental
🍂 | Deciduous trees shed foliage during late autumn | Los árboles caducifolios pierden el follaje a finales de otoño
🏖️ | Warm ocean breezes characterize the coastal climate | Las cálidas brisas marinas caracterizan el clima costero
🌸 | Wildflowers carpet the meadows every springtime | Las flores silvestres cubren las praderas cada primavera
"""
            11 -> """
🏥 | Comprehensive health checkups detect early risks | Los chequeos médicos completos detectan riesgos tempranos
🤕 | Preventative lifestyle changes reduce chronic pain | Cambios preventivos en el estilo de vida reducen el dolor crónico
💊 | Follow medical prescriptions with exact precision | Siga las recetas médicas con exactitud
🧑‍⚕️ | The physician conducted a detailed cardiac screening | El médico llevó a cabo una evaluación cardíaca detallada
🌡️ | Maintaining thermal homeostasis is vital for health | Mantener la homeostasis térmica es vital para la salud
✨ | Minor abrasions should be cleansed thoroughly | Las abrasiones menores deben limpiarse a fondo
🦷 | Regular dental hygiene prevents oral complications | La higiene dental regular previene complicaciones bucales
🤧 | Adequate hydration bolsters respiratory defenses | La hidratación adecuada refuerza las defensas respiratorias
📞 | Antioxidant-rich foods support cellular health | Los alimentos ricos en antioxidantes favorecen la salud celular
🏃 | Low-impact exercise strengthens joint mobility | El ejercicio de bajo impacto fortalece la movilidad articular
"""
            12 -> """
🛒 | Sustainable organic goods are stocked in aisle three | Los productos ecológicos sostenibles están en el pasillo tres
🛍️ | I am looking for an eco-friendly gift option | Estoy buscando una opción de regalo ecológica
👗 | This garment is manufactured from organic cotton | Esta prenda está confeccionada con algodón orgánico
👖 | We offer customized tailoring on all trousers | Ofrecemos sastrería a medida en todos los pantalones
🏷️ | Promotional offers apply during the spring sale | Las ofertas promocionales se aplican durante las rebajas de primavera
💳 | Secure biometric verification protects every payment | La verificación biométrica segura protege cada pago
🧾 | Digital receipts are emailed directly to customers | Los recibos digitales se envían directamente por correo al cliente
📦 | Express shipment arrives within business hours | El envío urgente llega dentro del horario comercial
🏪 | The flagship store offers personalized consultations | La tienda insignia ofrece asesoramiento personalizado
👩 | Sourdough loaves are baked using traditional ovens | Las barras de masa madre se hornean en hornos tradicionales
"""
            13 -> """
🍷 | May we examine the sommelier's wine recommendations? | ¿Podemos examinar las recomendaciones de vinos del sumiller?
🥗 | An appetizer of seasonal greens and walnut vinaigrette | Un entrante de verduras de temporada y vinagreta de nueces
🥩 | The chef prepared the prime tenderloin skillfully | El chef preparó el solomillo de primera con maestría
💧 | A glass of chilled sparkling spring water, please | Una copa de agua de manantial con gas fría, por favor
📅 | Today's catch includes sustainably sourced trout | La pesca de hoy incluye trucha de origen sostenible
🌾 | Please note any food sensitivities on the order | Por favor, anote cualquier intolerancia alimentaria en la comanda
🧾 | Two freshly brewed coffees and the settlement, please | Dos cafés recién hechos y la cuenta, por favor
👨‍🍳 | Our compliments for an unforgettable dining experience | Nuestras felicitaciones por una experiencia gastronómica inolvidable
🍝 | Handmade ravioli filled with spinach and ricotta | Raviolis caseros rellenos de espinacas y ricota
🍲 | Velvety pumpkin soup garnished with toasted seeds | Sopa aterciopelada de calabaza decorada con semillas tostadas
"""
            14 -> """
🏫 | Academic research enriches pedagogical excellence | La investigación académica enriquece la excelencia pedagógica
📚 | Scholarly journals undergo meticulous peer scrutiny | Las revistas académicas se someten a un meticuloso análisis por pares
📝 | The research methodology must be clearly articulated | La metodología de investigación debe articularse con claridad
🎓 | Postgraduate studies foster specialized expertise | Los estudios de posgrado fomentan una experiencia especializada
📖 | Analytical reading develops critical discernment | La lectura analítica desarrolla el discernimiento crítico
🔬 | Laboratory findings were corroborated by external teams | Los hallazgos de laboratorio fueron corroborados por equipos externos
🗣️ | Clear discourse facilitates academic debate | El discurso claro facilita el debate académico
🧠 | Deep conceptual synthesis outlasts rote memorization | La síntesis conceptual profunda perdura más que la memorización mecánica
👩‍🏫 | The symposium featured renowned keynote speakers | El simposio contó con ponentes principales de renombre
📑 | Academic integrity underpins all scientific inquiry | La integridad académica sustenta toda investigación científica
"""
            15 -> """
💻 | Cloud migration enhances institutional data redundancy | La migración a la nube mejora la redundancia de datos institucionales
📶 | High-capacity networks enable seamless collaboration | Las redes de alta capacidad permiten una colaboración fluida
🔒 | Robust cryptographic protocols protect user privacy | Protocolos criptográficos robustos protegen la privacidad del usuario
📱 | Continuous software patches mitigate cyber vulnerabilities | Los parches continuos de software mitigan las vulnerabilidades cibernéticas
💾 | Automated off-site backups ensure disaster recovery | Las copias de seguridad automáticas externas garantizan la recuperación ante desastres
📧 | Secure mail gateways filter malicious phishing attempts | Las pasarelas de correo seguro filtran los intentos maliciosos de phishing
🧗 | Battery optimization algorithms prolong hardware cycles | Los algoritmos de optimización de batería prolongan los ciclos del hardware
🎧 | Spatial audio technology delivers immersive soundscapes | La tecnología de audio espacial ofrece paisajes sonoros inmersivos
☁️ | Containerized architectures ensure reliable scalability | Las arquitecturas en contenedores aseguran una escalabilidad fiable
🦒 | High-refresh displays enhance graphic design workflows | Las pantallas de alto refresco mejoran los flujos de diseño gráfico
"""
            16 -> """
⚽ | Tactical versatility distinguishes elite soccer teams | La versatilidad táctica distingue a los equipos de fútbol de élite
🏀 | Spatial awareness is fundamental in court strategy | La percepción espacial es fundamental en la estrategia de pista
🎾 | Precise baseline play neutralizes aggressive returns | El juego preciso desde el fondo neutraliza las devoluciones agresivas
🏊 | Aerobic swimming strengthens pulmonary capacity | La natación aeróbica fortalece la capacidad pulmonar
🚴 | Velodrome cycling demands explosive muscular power | El ciclismo en velódromo exige una potencia muscular explosiva
🏃 | Consistent pacing is paramount during ultra marathons | El ritmo constante es primordial durante los ultramaratones
🏆 | The international squad displayed outstanding sportsmanship | El equipo internacional demostró una deportividad excepcional
🥊 | Disciplined conditioning underpins martial triumph | El acondicionamiento disciplinado sustenta el triunfo marcial
🏋️ | Compound movements maximize functional physical strength | Los ejercicios compuestos maximizan la fuerza física funcional
⛳ | Subtle green contours dictate precise putting strategy | Los sutiles desniveles del green dictan una estrategia de putt precisa
"""
            17 -> """
🎵 | Symphonic orchestrations inspire profound aesthetic contemplation | Las orquestaciones sinfónicas inspiran una profunda contemplación estética
🎸 | Traditional folk ballads preserve cultural memory | Las baladas folclóricas tradicionales conservan la memoria cultural
🎹 | Nuanced piano dynamics convey subtle emotional depth | Los matices dinámicos del piano transmiten una sutil profundidad emocional
🎨 | Visual art galleries foster communal dialogue | Las galerías de arte visual fomentan el diálogo comunitario
🎬 | Expressive cinematography enriches cinematic storytelling | La cinematografía expresiva enriquece la narración cinematográfica
🎟️ | Advance admission ensures entry to prime exhibits | La entrada anticipada asegura el acceso a las exposiciones principales
🎭 | Classical theater explores perpetual human dilemmas | El teatro clásico explora dilemas humanos perpetuos
🎻 | The string ensemble executed the cadence flawlessly | El conjunto de cuerda ejecutó la cadencia a la perfección
📚 | Literary classics illuminate historical epochs | Los clásicos literarios iluminan épocas históricas
📸 | Master photography captures the geometry of daily life | La fotografía magistral captura la geometría de la vida cotidiana
"""
            18 -> """
🤝 | Mutual respect nurtures enduring human relationships | El respeto mutuo nutre las relaciones humanas duraderas
🌉 | Sincere empathy bridges deep generational gaps | La empatía sincera salva profundas brechas generacionales
👨‍👩‍👧‍👦 | Celebrating heritage enriches family solidarity | Celebrar el patrimonio enriquece la solidaridad familiar
💬 | Compassionate dialogue overcomes long-standing grievances | El diálogo compasivo supera los agravios de larga data
📋 | Non-judgmental listening provides profound emotional comfort | La escucha sin juicios proporciona un profundo consuelo emocional
👶 | Early childhood environments shape emotional resilience | Los entornos en la primera infancia moldean la resiliencia emocional
👨‍👩‍👧‍👦 | Family anniversaries commemorate shared life journeys | Los aniversarios familiares conmemoran trayectorias de vida compartidas
🧑‍🤝‍🧑 | Loyal friendships provide shelter in times of adversity | Las amistades leales proporcionan cobijo en tiempos de adversidad
🎁 | Generosity of spirit transcends material expressions | La generosidad de espíritu trasciende las expresiones materiales
💔 | Understanding and patience heal interpersonal discord | La comprensión y la paciencia curan la discordia interpersonal
"""
            19 -> """
🌍 | Environmental stewardship is an ethical imperative | La protección medioambiental es un imperativo ético
♻️ | Resource circularity mitigates environmental degradation | La circularidad de recursos mitiga la degradación ambiental
🌳 | Native reforestation restores depleted natural watersheds | La reforestación autóctona restaura cuencas hidrográficas agotadas
⚡ | Decentralized microgrids empower green communities | Las microrredes descentralizadas empoderan a las comunidades ecológicas
💧 | Responsible water use secures future agricultural yields | El uso responsable del agua asegura los futuros rendimientos agrícolas
✨ | Low-emission transit networks clean metropolitan air | Las redes de transporte de bajas emisiones limpian el aire metropolitano
🦁 | Preserving biosphere reserves safeguards fragile species | Preservar reservas de la biosfera protege especies frágiles
🌊 | Coastal wetland restoration shields shores from storms | Restaurar humedales costeros protege las costas de las tormentas
🚲 | Pedestrianized boulevards encourage urban vitality | Los bulevares peatonales fomentan la vitalidad urbana
🍃 | Immersion in wild nature revitalizes human vitality | La inmersión en la naturaleza salvaje revitaliza la vitalidad humana
"""
            20 -> """
💼 | I stumbled upon a groundbreaking historical archive | Descubrí por casualidad un archivo histórico pionero
🔍 | Let us pinpoint the exact source of discrepancy | Determinemos el origen exacto de la discrepancia
🚗 | The electrical circuit broke down during testing | El circuito eléctrico se averió durante las pruebas
🚪 | Reliable collaborators uphold their commitments unfailingly | Los colaboradores fiables mantienen sus compromisos sin falta
📞 | I will return your inquiry before close of business | Responderé a su consulta antes del cierre de la jornada
🦶 | Please transcribe these verbatim witness accounts | Por favor, transcriba estas declaraciones de testigos al pie de la letra
🧥 | Don thermal gear when operating in arctic conditions | Use ropa térmica al operar en condiciones árticas
💡 | The committee turned down the unsubstantiated proposal | El comité rechazó la propuesta no fundamentada
📁 | Project milestones require immediate execution | Los hitos del proyecto requieren una ejecución inmediata
✈️ | We accompanied foreign dignitaries to their departure | Acompañamos a los dignatarios extranjeros hasta su salida
"""
            21 -> """
🍞 | That technical certification was a piece of cake | Esa certificación técnica fue pan comido
🐕 | It is raining cats and dogs throughout the river basin | Está lloviendo a cántaros en toda la cuenca del río
👁️ | State-of-the-art laboratory gear costs an arm and a leg | Los equipos de laboratorio de última generación cuestan un ojo de la cara
👂 | Present your detailed thesis; we are all ears | Exponga su tesis detallada; somos todo oídos
⏳ | Better late than never when correcting systemic errors | Más vale tarde que nunca a la hora de corregir errores sistémicos
🥊 | Your incisive evaluation hit the nail on the head | Su incisiva evaluación dio en el clavo
🥱 | After concluding research, it is time to hit the sack | Tras concluir la investigación, es hora de irse a dormir
🧊 | A shared humorous anecdote broke the ice seamlessly | Una anécdota humorística compartida rompió el hielo con fluidez
🤫 | Do not spill the beans regarding proprietary patents | No desveles el secreto con respecto a las patentes registradas
⚡ | The technological breakthrough emerged out of the blue | El avance tecnológico surgió de la nada
"""
            22 -> """
👨‍👩‍👧‍👦 | Sound monetary planning secures lasting family welfare | Una sólida planificación monetaria asegura un bienestar familiar duradero
🦒 | Liquidate high-interest balances systematically | Liquide los saldos con altos intereses de forma sistemática
🏠 | Long-term mortgage structures protect homeowner equity | Las estructuras hipotecarias a largo plazo protegen el patrimonio
📈 | Diversified asset allocations weather market volatility | Las asignaciones diversificadas de activos resisten la volatilidad del mercado
📉 | Deflationary and inflationary cycles affect capital reserves | Los ciclos deflacionarios e inflacionarios afectan a las reservas de capital
💵 | Maintain a robust contingency reserve for unforeseen events | Mantenga una sólida reserva de contingencia para imprevistos
🧾 | Conduct regular audits of corporate expenditures | Realice auditorías periódicas de los gastos corporativos
🏧 | Secure payment kiosks are situated throughout terminals | Quioscos de pago seguros están situados por todas las terminales
💸 | Prudent investment strategies cultivate enduring independence | Estrategias de inversión prudentes cultivan una independencia duradera
📊 | Sovereign bond markets exhibited stability this quarter | Los mercados de bonos soberanos mostraron estabilidad este trimestre
"""
            23 -> """
⚖️ | Statutory justice guarantees equal protection for all | La justicia estatutaria garantiza igual protección para todos
🏛️ | The constitutional tribunal issued its landmark judgment | El tribunal constitucional emitió su sentencia histórica
👨‍⚖️ | The judiciary evaluated extensive documented evidence | El poder judicial evaluó abundantes pruebas documentales
📝 | Contractual clauses mandate strict compliance schedules | Las cláusulas contractuales exigen estrictos calendarios de cumplimiento
🔒 | Law enforcement executed the mandate with professionalism | Las fuerzas del orden ejecutaron el mandato con profesionalidad
🔍 | Forensic protocols guaranteed integrity of the evidence | Los protocolos forenses garantizaron la integridad de las pruebas
🗣️ | The expert witness provided compelling technical testimony | El perito aportó un testimonio técnico convincente
🛡️ | Fundamental civil liberties remain non-negotiable | Las libertades civiles fundamentales siguen siendo innegociables
📜 | Legal codifications delineate societal duties | Las codificaciones legales delimitan los deberes sociales
💼 | Corporate arbitration yielded an equitable outcome | El arbitraje corporativo produjo un resultado equitativo
"""
            24 -> """
🔬 | Peer-reviewed methodologies validate scientific breakthroughs | Metodologías revisadas por pares validan los avances científicos
🚀 | Planetary probes transmit unprecedented atmospheric telemetry | Las sondas planetarias transmiten telemetría atmosférica sin precedentes
🧬 | Genomic sequencing clarifies disease vulnerabilities | La secuenciación genómica aclara las vulnerabilidades ante enfermedades
🔭 | Radiotelescopes detect cosmic radiation from early epochs | Los radiotelescopios detectan radiación cósmica de épocas tempranas
🧪 | Controlled chemical synthesis produces advanced polymers | La síntesis química controlada produce polímeros avanzados
⚡ | Renewable microgrids ensure decentralized electrical stability | Las microrredes renovables aseguran la estabilidad eléctrica descentralizada
🌱 | Photosynthetic efficiency dictates agricultural biomass | La eficiencia fotosintética dictamina la biomasa agrícola
🤖 | Deep neural architectures accelerate pattern recognition | Las arquitecturas neuronales profundas aceleran el reconocimiento de patrones
🌊 | Hydrothermal vents sustain chemoautotrophic biospheres | Los respiraderos hidrotermales sustentan biosferas quimioautótrofas
🌋 | Volcanological monitoring safeguards surrounding populations | La vigilancia vulcanológica protege a las poblaciones circundantes
"""
            25 -> """
🏛️ | Ancient civilizations engineered monumental urban centers | Civilizaciones antiguas diseñaron monumentales centros urbanos
⚔️ | Diplomatic treatises mediated centuries of regional conflicts | Tratados diplomáticos mediaron en siglos de conflictos regionales
🏰 | Medieval fortresses embodied strategic defense networks | Las fortalezas medievales encarnaron redes estratégicas de defensa
📜 | Archival manuscripts illuminate everyday renaissance trade | Manuscritos de archivo iluminan el comercio cotidiano renacentista
🚢 | Global navigation networks connected world economies | Las redes de navegación global conectaron las economías mundiales
🏭 | Technological revolutions transformed civic infrastructure | Las revoluciones tecnológicas transformaron la infraestructura cívica
🗽 | Democratic principles enshrined fundamental civic freedoms | Los principios democráticos consagraron las libertades cívicas fundamentales
📖 | Historical analysis illuminates contemporary social patterns | El análisis histórico ilumina los patrones sociales contemporáneos
🏛️ | Antiquity's heritage remains an architectural benchmark | El patrimonio de la antigüedad sigue siendo un referente arquitectónico
🗺️ | Historical cartography reveals evolving territorial perspectives | La cartografía histórica revela perspectivas territoriales en evolución
"""
            26 -> """
🏠 | Rural homesteads offer respite from metropolitan urgency | Las casas de campo ofrecen un respiro de la urgencia metropolitana
🌆 | Urban ecosystems pulse with cultural innovation | Los ecosistemas urbanos vibran con innovación cultural
🚗 | Integrated transit networks alleviate urban congestion | Redes de transporte integradas alivian la congestión urbana
🌳 | Municipal greenways enhance urban quality of life | Las vías verdes municipales mejoran la calidad de vida urbana
🚲 | Interconnected cycle paths foster sustainable commuting | Los carriles bici interconectados fomentan los desplazamientos sostenibles
🏢 | Sustainable high-rises redefine commercial skylines | Los rascacielos sostenibles redefinen los horizontes comerciales
🚜 | Precision agriculture sustains food production capacity | La agricultura de precisión sostiene la capacidad de producción de alimentos
🚇 | High-frequency underground transit links urban districts | El metro de alta frecuencia conecta los distritos urbanos
🏡 | Cultivating domestic greenery brings serenity to daily life | Cultivar vegetación doméstica aporta serenidad a la vida diaria
🛒 | Regional markets showcase artisan agricultural produce | Los mercados regionales muestran productos agrícolas artesanales
"""
            27 -> """
✈️ | Exploratory voyages enrich personal and cultural perspectives | Los viajes de exploración enriquecen las perspectivas personales y culturales
📸 | Documentary photography preserves human history in real-time | La fotografía documental preserva la historia humana en tiempo real
🍳 | Masterful cuisine balances aesthetic presentation with flavor | La cocina magistral equilibra la presentación estética con el sabor
🪴 | Cultivating rare botanicals requires horticultural discipline | Cultivar especies botánicas raras requiere disciplina hortícola
♟️ | Grandmaster strategy balances tactical offense with defense | La estrategia de grandes maestros equilibra el ataque táctico con la defensa
📖 | Immersing oneself in profound books enlightens the spirit | Sumergirse en libros profundos ilumina el espíritu
🎨 | Expressive artistic creation communicates beyond words | La creación artística expresiva se comunica más allá de las palabras
🎣 | River navigation fosters meditative connection with nature | La navegación fluvial fomenta una conexión meditativa con la naturaleza
🥾 | Alpine expeditions demand endurance and navigational acumen | Las expediciones alpinas exigen resistencia y destreza en la navegación
🧩 | Algorithmic logic puzzles develop sharp cognitive faculties | Los acertijos de lógica algorítmica desarrollan agudas facultades cognitivas
"""
            28 -> """
🗣️ | Eloquent expression enhances interpersonal influence | La expresión elocuente mejora la influencia interpersonal
📋 | Deep listening establishes genuine mutual comprehension | La escucha profunda establece una comprensión mutua auténtica
📧 | Professional correspondence demands clarity and tact | La correspondencia profesional exige claridad y tacto
🎯 | Oratorical poise is refined through deliberate practice | El aplomo oratorio se perfecciona mediante la práctica deliberada
💡 | Translate nuanced thoughts into persuasive explanations | Traduzca pensamientos con matices en explicaciones persuasivas
👀 | Attentive posture conveys genuine respect and engagement | Una postura atenta transmite auténtico respeto e interés
📱 | Mindful messaging prevents digital misinterpretations | La mensajería consciente previene malas interpretaciones digitales
📋 | Strategic pauses allow listeners to assimilate concepts | Las pausas estratégicas permiten a los oyentes asimilar conceptos
🤝 | Empathetic reasoning resolves intricate workplace disputes | El razonamiento empático resuelve disputas laborales complejas
📁 | Resonant speech projection commands audience attention | La proyección de voz resonante capta la atención del público
"""
            29 -> """
🧭 | Ethical governance inspires widespread civic confidence | El buen gobierno ético inspira una amplia confianza cívica
🤝 | Uncompromising honor elevates personal character | El honor inquebrantable eleva el carácter personal
💖 | Altruistic benevolence enriches human society | La benevolencia altruista enriquece la sociedad humana
⚖️ | Equitable justice guarantees social harmony and trust | La justicia equitativa garantiza la armonía y la confianza social
🌱 | Relentless self-cultivation expands human potential | El cultivo personal constante amplía el potencial humano
🛡️ | Moral fortitude withstands the pressures of conformity | La fortaleza moral resiste las presiones del conformismo
⏳ | Resilient perseverance overcomes formidable hurdles | La perseverancia resiliente supera obstáculos formidables
🌟 | Daily gratitude transforms ordinary moments into joy | La gratitud diaria transforma los momentos ordinarios en alegría
🎯 | Singularity of purpose guides exceptional achievements | La claridad de propósito guía logros excepcionales
🕊️ | Universal mutual respect fosters enduring global peace | El respeto mutuo universal fomenta una paz global duradera
"""
            30 -> """
🇬🇧 | Dedicated daily application leads to true bilingualism | La dedicación diaria constante conduce al verdadero bilingüismo
🎧 | Daily immersion fine-tunes your ear for colloquial speech | La inmersión diaria afina tu oído para el habla coloquial
📖 | Reading sophisticated literature builds an elite vocabulary | Leer literatura sofisticada construye un vocabulario de élite
🗣️ | Continuous speaking practice turns confidence into second nature | La práctica oral continua convierte la seguridad en una segunda naturaleza
✍️ | Writing structured essays reinforces complex grammar mastery | Escribir ensayos estructurados refuerza el dominio de la gramática compleja
🇬🇧 | Thinking seamlessly in English removes mental translation | Pensar con fluidez en inglés elimina la traducción mental
🎬 | Authentic audiovisual immersion reveals subtle humor and idioms | La inmersión audiovisual auténtica revela el humor sutil y los modismos
🎯 | Sustained focus on language milestones delivers excellence | El enfoque constante en los hitos lingüísticos brinda excelencia
🇬🇧 | Persistence and curiosity unlock the beauty of English | La perseverancia y la curiosidad desbloquean la belleza del inglés
🎉 | Congratulations on conquering this entire translation module! | ¡Enhorabuena por conquistar este módulo completo de traducción!
"""
            else -> ""
        }
    }
}
