package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences6 {
    const val FOLDER_NAME = "Translations Sentences 6"
    val categoryNames: List<String> = (1..30).map { "Translations 6 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations 6 - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
💼 | How long have you been managing this team? | ¿Cuánto tiempo llevas gestionando este equipo?
📈 | We have seen substantial growth over the past year | Hemos visto un crecimiento sustancial durante el último año
🔍 | Have you reviewed the final draft yet? | ¿Has revisado ya el borrador final?
☕ | Would anyone care for a hot drink? | ¿A alguien le apetecería una bebida caliente?
🚗 | He has just arrived from the airport | Acaba de llegar del aeropuerto
⏰ | We haven't received confirmation from the supplier | No hemos recibido confirmación del proveedor
🌧️ | It has been raining continuously since dawn | Ha estado lloviendo continuamente desde el amanecer
💡 | She has proposed an innovative marketing plan | Ha propuesto un plan de marketing innovador
🔑 | I have misplaced my office badge | He extraviado mi tarjeta de acceso a la oficina
📈 | Company productivity has increased notably | La productividad de la empresa ha aumentado notablemente
"""
            2 -> """
🏖️ | If we leave early, we will avoid rush hour | Si salimos temprano, evitaremos la hora punta
🚗 | If he drove more slowly, he would save fuel | Si condujera más despacio, ahorraría combustible
🌧️ | What will you do if it pours tomorrow? | ¿Qué harás si diluvia mañana?
🏠 | I wouldn't make that decision in haste | Yo no tomaría esa decisión con prisas
📱 | If you send the files now, I'll review them | Si envías los archivos ahora, los revisaré
💼 | If you receive the grant, will you accept? | Si recibes la beca, ¿la aceptarás?
🍕 | If anyone is hungry, we can order lunch | Si alguien tiene hambre, podemos pedir el almuerzo
🤝 | We would gladly support your campaign | Apoyaríamos con gusto tu campaña
❓ | What would happen if we changed the schedule? | ¿Qué pasaría si cambiáramos el horario?
📚 | If you study regularly, exams are much easier | Si estudias con regularidad, los exámenes son mucho más fáciles
"""
            3 -> """
🗣️ | She mentioned that the conference was postponed | Mencionó que la conferencia fue pospuesta
❓ | He inquired whether the tickets were still available | Preguntó si las entradas aún estaban disponibles
💬 | They promised they would deliver the goods on time | Prometieron que entregarían la mercancía a tiempo
📌 | She asked where the nearest underground station was | Preguntó dónde estaba la estación de metro más cercana
📣 | He warned us that the road was under repair | Nos advirtió que la carretera estaba en obras
🧠 | I stated that I had already sent the email | Declaré que ya había enviado el correo electrónico
💬 | They insisted that they had followed all procedures | Insistieron en que habían seguido todos los procedimientos
🤝 | She agreed to lead the new workgroup | Aceptó liderar el nuevo grupo de trabajo
💡 | He suggested that we take a brief intermission | Sugirió que hiciéramos un breve intermedio
🔒 | She reminded everyone to submit their timesheets | Recordó a todos que entregaran sus hojas de horas
"""
            4 -> """
🏗️ | The modern hospital was constructed in 2020 | El moderno hospital se construyó en 2020
📄 | All invitations were dispatched this morning | Todas las invitaciones fueron despachadas esta mañana
🚗 | The railway track is being upgraded currently | La vía ferroviaria está siendo mejorada actualmente
📦 | All customer inquiries are handled promptly | Todas las consultas de clientes se atienden con prontitud
🔒 | The museum entrance is closed on state holidays | La entrada del museo está cerrada en días festivos
🏆 | She was honored with a prestigious literature award | Fue galardonada con un prestigioso premio de literatura
🧹 | The headquarters is cleaned thoroughly every night | La sede se limpia a fondo todas las noches
🏢 | This historic cathedral was built centuries ago | Esta catedral histórica fue construida hace siglos
📧 | You will be notified once results are published | Se le notificará una vez publicados los resultados
⚠️ | Protective gear must be worn at the construction site | Se debe llevar equipo de protección en la obra
"""
            5 -> """
⏰ | You ought to take regular breaks while studying | Deberías tomar descansos periódicos mientras estudias
💡 | You should have consulted your advisor beforehand | Deberías haber consultado a tu asesor de antemano
⚠️ | Visitors must not enter restricted laboratory zones | Los visitantes no deben entrar en zonas de laboratorio restringidas
❓ | Could you direct me toward the main auditorium? | ¿Podría dirigirme hacia el auditorio principal?
🏥 | She might be attending a seminar right now | Puede que ella esté asistiendo a un seminario ahora mismo
🌧️ | It may clear up by early afternoon | Puede que se despeje a primera hora de la tarde
🔑 | You do not need to print the ticket; show your phone | No necesitas imprimir el billete; muestra tu móvil
🧠 | He must have left his umbrella in the taxi | Debe de haberse dejado el paraguas en el taxi
🚫 | That explanation cannot be accurate | Esa explicación no puede ser precisa
🏋️ | We had better depart before traffic intensifies | Más vale que salgamos antes de que se intensifique el tráfico
"""
            6 -> """
🎒 | I look forward to working with your team | Espero con ganas trabajar con tu equipo
⏰ | Don't put off filing your tax return | No pospongas presentar tu declaración de impuestos
💡 | He came up with an inventive engineering concept | Se le ocurrió un concepto de ingeniería ingenioso
🧥 | Take off your wet coat and hang it up | Quítate el abrigo mojado y cuélgalo
📞 | Please hold the line for the next representative | Por favor, manténgase en la línea para el siguiente representante
🚗 | We ran out of printing paper at the office | Nos quedamos sin papel de imprimir en la oficina
🔍 | I'm looking for my reading spectacles | Estoy buscando mis gafas de lectura
🏢 | They called off the summit due to bad weather | Cancelaron la cumbre debido al mal tiempo
🚪 | Switch off the monitors when leaving work | Apaga los monitores al salir del trabajo
📈 | Demand is picking up in overseas markets | La demanda está repuntando en los mercados de ultramar
"""
            7 -> """
🏢 | How many members constitute your department? | ¿Cuántos miembros componen tu departamento?
📈 | Our division achieved unprecedented performance | Nuestra división logró un rendimiento sin precedentes
💼 | Let us arrange an executive meeting for Friday | Concertemos una reunión ejecutiva para el viernes
📄 | Please review the contract terms with diligence | Por favor, revise los términos del contrato con diligencia
🤝 | We entered into a multi-year strategic partnership | Entramos en una colaboración estratégica de varios años
💰 | The board approved the expansion budget | La junta directiva aprobó el presupuesto de expansión
📊 | Market analytics indicate sustainable demand | Los análisis de mercado indican una demanda sostenible
🎯 | Excellence remains our foremost organizational goal | La excelencia sigue siendo nuestro principal objetivo organizativo
✉️ | Thank you for your swift and courteous response | Gracias por su rápida y cortés respuesta
👥 | Inspiring leadership elevates organizational culture | Un liderazgo inspirador eleva la cultura de la organización
"""
            8 -> """
✈️ | Flight 408 will begin boarding at gate twelve | El vuelo 408 comenzará el embarque en la puerta doce
🧳 | Please verify that your luggage is within limits | Por favor, compruebe que su equipaje esté dentro de los límites
🏨 | We reserved a double room with ocean view | Reservamos una habitación doble con vistas al mar
🛏️ | Continental breakfast is available until ten | El desayuno continental está disponible hasta las diez
🗺️ | Can you highlight historical landmarks on the map? | ¿Puede marcar lugares de interés histórico en el mapa?
🚆 | Is this the rapid service to the financial district? | ¿Es este el servicio rápido al distrito financiero?
🎟️ | Two adult admissions and one child ticket, please | Dos entradas de adulto y una de niño, por favor
🚕 | Please take us to the historic city center | Por favor, llévenos al centro histórico de la ciudad
💳 | We accept all major credit and debit cards | Aceptamos las principales tarjetas de crédito y débito
📍 | The botanical gardens are a short stroll away | El jardín botánico está a un breve paseo a pie
"""
            9 -> """
☕ | I begin each day with green tea and meditation | Empiezo cada día con té verde y meditación
🏃 | He jogs four miles through the scenic park | Corre cuatro millas por el pintoresco parque
🍳 | We prepared oatmeal with fresh berries this morning | Preparamos avena con frutos rojos frescos esta mañana
🚗 | The morning commute was unusually smooth today | El trayecto matutino fue inusualmente fluido hoy
🎧 | Informative audiobooks enrich my daily travel | Los audiolibros informativos enriquecen mi viaje diario
🛒 | We purchased fresh produce at the weekend farmers market | Compramos productos frescos en el mercado de agricultores del fin de semana
🍲 | Homemade soup was prepared for this evening's dinner | Se preparó sopa casera para la cena de esta noche
🧹 | Keeping living spaces orderly clears the mind | Mantener los espacios ordenados despeja la mente
📺 | We watched an inspiring historical documentary | Vimos un documental histórico inspirador
🛏️ | Adequate rest restores mental and physical energy | El descanso adecuado restaura la energía física y mental
"""
            10 -> """
🌡️ | The ambient temperature is twenty-two degrees | La temperatura ambiente es de veintidós grados
☀️ | Gentle sunlight illuminated the entire valley | La suave luz solar iluminó todo el valle
🌧️ | A sudden thunderstorm swept across the coastline | Una repentina tormenta eléctrica barrió la costa
⚡ | Distant lightning flickered across the dark horizon | Relámpagos lejanos parpadearon en el oscuro horizonte
❄️ | A blanket of white snow covered the rooftops | Un manto de nieve blanca cubrió los tejados
🧥 | A wool sweater provides great warmth in winter | Un suéter de lana proporciona mucho abrigo en invierno
🌈 | A double rainbow appeared after the afternoon shower | Un arcoíris doble apareció tras el chaparrón de la tarde
🍂 | Golden leaves blanket the forest pathway | Hojas doradas cubren el sendero del bosque
🏖️ | The ocean breeze brought welcome relief from heat | La brisa marina trajo un bienvenido alivio del calor
🌸 | Blossoming cherry trees announce the arrival of spring | Cerezos en flor anuncian la llegada de la primavera
"""
            11 -> """
🏥 | Please schedule an annual preventive medical review | Por favor, programe una revisión médica preventiva anual
🤕 | Chronic migraines can be relieved with treatment | Las migrañas crónicas pueden aliviarse con tratamiento
💊 | Adhere strictly to the prescribed dosage schedule | Cumpla estrictamente con el horario de dosificación prescrito
🩺 | The cardiologist performed a detailed examination | El cardiólogo realizó una exploración detallada
🌡️ | Body temperature fluctuates naturally during the day | La temperatura corporal fluctúa de forma natural durante el día
🩹 | Keep minor cuts clean and properly dressed | Mantén los cortes menores limpios y debidamente vendados
🦷 | Preventive dental care avoids costly dental work | El cuidado dental preventivo evita costosos tratamientos dentales
🤧 | Rest and hydration accelerate recovery from illness | El reposo y la hidratación aceleran la recuperación de la enfermedad
🥗 | A nutrient-dense diet bolsters your immune defense | Una dieta rica en nutrientes refuerza tus defensas inmunitarias
🏃 | Daily aerobic activity enhances cardiovascular endurance | La actividad aeróbica diaria mejora la resistencia cardiovascular
"""
            12 -> """
🛒 | Fresh organic produce is located in the left aisle | Los productos ecológicos frescos están en el pasillo izquierdo
🛍️ | I am seeking a thoughtful retirement present | Estoy buscando un regalo pensado para una jubilación
👗 | This tailored blazer complements your formal attire | Esta americana a medida complementa tu atuendo formal
👖 | Are these denim jeans available in slim fit? | ¿Están estos pantalones vaqueros disponibles en corte ajustado?
🏷️ | Clearance discounts apply to selected merchandise | Los descuentos de liquidación se aplican a artículos seleccionados
💳 | Tap your payment card against the contactless terminal | Acerque su tarjeta de pago al terminal sin contacto
🧾 | Retain your sales invoice for tax deductibility | Conserve su factura de venta para desgravación fiscal
📦 | Express postal delivery guarantees next-day arrival | El envío postal urgente garantiza la llegada al día siguiente
🏪 | The boutique remains open throughout the weekend | La boutique permanece abierta durante todo el fin de semana
🥖 | Artisan bakery goods are baked fresh at sunrise | Los productos de panadería artesanal se hornean frescos al amanecer
"""
            13 -> """
🍽️ | May we request the seasonal wine and dessert menu? | ¿Podemos solicitar la carta de vinos y postres de temporada?
🥗 | I shall begin with the Mediterranean garden salad | Empezaré con la ensalada de la huerta mediterránea
🥩 | The herb-crusted lamb was cooked to perfection | El cordero con costra de hierbas estaba cocinado a la perfección
🍷 | A glass of chilled sparkling mineral water, please | Una copa de agua mineral con gas fría, por favor
🐟 | Today's special features wild-caught sea bass | La sugerencia de hoy incluye lubina salvaje
🌾 | Please inform kitchen staff of severe nut allergies | Por favor, informe al personal de cocina de alergias graves a frutos secos
☕ | Two decaffeinated coffees and the check, please | Dos cafés descafeinados y la cuenta, por favor
👨‍🍳 | Our sincere compliments to the culinary team | Nuestras sinceras felicitaciones al equipo culinario
🍝 | Handcrafted tagliatelle served with truffled mushrooms | Tagliatelle artesanal servido con setas trufadas
🥣 | Warm roasted tomato soup served with sourdough crust | Sopa caliente de tomate asado servida con corteza de masa madre
"""
            14 -> """
🏫 | He graduated summa cum laude in civil engineering | Se graduó summa cum laude en ingeniería civil
📚 | Scholarly research requires rigorous source verification | La investigación académica requiere una rigurosa verificación de fuentes
📝 | The thesis proposal must be defended next semester | La propuesta de tesis debe defenderse el próximo semestre
🎓 | Higher academic degrees expand professional trajectories | Los títulos académicos superiores amplían las trayectorias profesionales
📖 | Comprehensive reading fosters deep critical analysis | La lectura comprensiva fomenta un profundo análisis crítico
🔬 | Laboratory findings were documented systematically | Los hallazgos de laboratorio se documentaron sistemáticamente
🗣️ | Articulate communication is crucial in academia | La comunicación elocuente es crucial en el ámbito académico
🧠 | Understanding fundamental principles supersedes memorization | Entender los principios fundamentales supera a la memorización
👩‍🏫 | The lecture provided enlightening historical context | La conferencia proporcionó un esclarecedor contexto histórico
📑 | All references must conform to standard citation style | Todas las referencias deben ajustarse al estilo de citación estándar
"""
            15 -> """
💻 | Did you backup your database before the migration? | ¿Hiciste copia de seguridad de la base de datos antes de la migración?
📶 | High-speed fiber optic ensures stable connectivity | La fibra óptica de alta velocidad asegura una conectividad estable
🔒 | Implement multi-factor authentication across systems | Implemente autenticación multifactor en todos los sistemas
📱 | Firmware updates resolve security vulnerabilities | Las actualizaciones de firmware resuelven vulnerabilidades de seguridad
💾 | Automated cloud storage prevents catastrophic data loss | El almacenamiento automatizado en la nube evita pérdidas catastróficas de datos
📧 | Encrypted messaging preserves sensitive communications | La mensajería cifrada preserva las comunicaciones confidenciales
🔋 | Lithium battery management extends device longevity | La gestión de baterías de litio prolonga la vida útil del dispositivo
🎧 | Noise-cancelling headsets enhance workplace focus | Los auriculares con cancelación de ruido mejoran la concentración en el trabajo
☁️ | Distributed cloud architectures improve system resilience | Las arquitecturas de nube distribuida mejoran la resistencia del sistema
🖥️ | Ergonomic monitors alleviate occupational eye strain | Los monitores ergonómicos alivian la fatiga visual laboral
"""
            16 -> """
⚽ | The championship final delivered an exhilarating match | La final del campeonato ofreció un partido emocionante
🏀 | Agile footwork is crucial for basketball defense | El juego de pies ágil es crucial para la defensa en baloncesto
🎾 | Precision serving establishes control in tennis games | El saque de precisión establece el control en los partidos de tenis
🏊 | Lap swimming promotes full-body muscular balance | Nadar largos promueve el equilibrio muscular de todo el cuerpo
🚴 | Endurance cycling develops cardiovascular stamina | El ciclismo de resistencia desarrolla la resistencia cardiovascular
🏃 | Morning distance running fosters mental resilience | La carrera de fondo matutina fomenta la resiliencia mental
🏆 | The athletic delegation celebrated Olympic achievements | La delegación atlética celebró los logros olímpicos
🥊 | Martial discipline cultivates respect and self-control | La disciplina marcial cultiva el respeto y el autocontrol
🏋️ | Progressive overload drives muscular adaptation | La sobrecarga progresiva impulsa la adaptación muscular
⛳ | Golf requires strategic course management and patience | El golf requiere una gestión estratégica del campo y paciencia
"""
            17 -> """
🎵 | Orchestral harmonies evoke profound human emotions | Las armonías orquestales evocan profundas emociones humanas
🎸 | Acoustic melodies bring warmth to intimate gatherings | Las melodías acústicas aportan calidez a reuniones íntimas
🎹 | Classical piano compositions demand refined touch | Las composiciones clásicas de piano exigen un toque refinado
🎨 | Renaissance paintings showcase mastery of light | Las pinturas renacentistas muestran el dominio de la luz
🎬 | Cinematography transformed storytelling in modern film | La cinematografía transformó la narrativa en el cine moderno
🎟️ | Reserve admission tickets prior to exhibition opening | Reserve las entradas antes de la inauguración de la exposición
🎭 | Dramatic theater portrays diverse aspects of life | El teatro dramático retrata diversos aspectos de la vida
🎻 | The string quartet delivered an inspiring recital | El cuarteto de cuerda ofreció un recital inspirador
📚 | Classic literature explores timeless human conditions | La literatura clásica explora condiciones humanas atemporales
📸 | Fine art photography captures fleeting visual poetry | La fotografía de bellas artes captura poesía visual fugaz
"""
            18 -> """
🤝 | Mutual trust sustains lifelong personal friendships | La confianza mutua sostiene amistades personales para toda la vida
❤️ | Empathy and kindness form the bedrock of marriage | La empatía y la amabilidad forman los cimientos del matrimonio
👨‍👩‍👧‍👦 | Cherishing family traditions strengthens bonds | Valorar las tradiciones familiares fortalece los lazos
💬 | Constructive dialogue resolves domestic disagreements | El diálogo constructivo resuelve los desacuerdos domésticos
👂 | Attentive listening validates a companion's feelings | Escuchar con atención valida los sentimientos de un compañero
👶 | Welcoming a newborn inspires profound joy | Dar la bienvenida a un recién nacido inspira una alegría profunda
🎂 | Milestone celebrations bring generations together | Las celebraciones señaladas unen a diferentes generaciones
🧑‍🤝‍🧑 | Reliable friends stand by you during adversity | Los amigos fiables están a tu lado durante la adversidad
🎁 | Thoughtful gestures speak louder than costly items | Los gestos considerados dicen más que los objetos costosos
💔 | Patience and understanding heal relational fractures | La paciencia y la comprensión curan las fracturas en las relaciones
"""
            19 -> """
🌍 | Preserving ecosystems ensures a sustainable future | Preservar los ecosistemas garantiza un futuro sostenible
♻️ | Circular economy models reduce industrial waste | Los modelos de economía circular reducen los residuos industriales
🌳 | Reforestation initiatives counteract deforestation | Las iniciativas de reforestación contrarrestan la deforestación
⚡ | Solar and wind energy power modern clean grids | La energía solar y eólica alimentan las redes limpias modernas
💧 | Water management is essential during prolonged droughts | La gestión del agua es esencial durante sequías prolongadas
🚗 | Zero-emission transit enhances urban air quality | El transporte de cero emisiones mejora la calidad del aire urbano
🦁 | Habitat preservation protects endangered biodiversity | La preservación de hábitats protege la biodiversidad amenazada
🌊 | Marine sanctuaries allow ocean ecosystems to recover | Los santuarios marinos permiten que los ecosistemas oceánicos se recuperen
🚲 | Urban bicycle lanes encourage low-impact travel | Los carriles bici urbanos fomentan desplazamientos de bajo impacto
🍃 | Connecting with nature restores physical well-being | Conectar con la naturaleza restaura el bienestar físico
"""
            20 -> """
💼 | I stumbled upon a rare historical manuscript | Me topé por casualidad con un manuscrito histórico raro
🔍 | Let us ascertain the underlying system anomaly | Determinemos la anomalía subyacente del sistema
🚗 | The engine broke down during the mountain climb | El motor se averió durante la subida a la montaña
🚪 | Reliable collaborators never let their partners down | Los colaboradores fiables nunca defraudan a sus socios
📞 | I shall return your telephone call at four o'clock | Devolveré su llamada telefónica a las cuatro en punto
📝 | Please note down these critical safety instructions | Por favor, anote estas instrucciones críticas de seguridad
🧥 | Don your winter outerwear before braving the snow | Ponte la ropa de invierno antes de desafiar la nieve
💡 | The board turned down the unsolicited acquisition bid | La junta directiva rechazó la oferta de adquisición no solicitada
⏰ | Project deadlines are approaching very rapidly | Los plazos del proyecto se están acercando muy rápidamente
✈️ | We accompanied our delegates to the airport lounge | Acompañamos a nuestros delegados a la sala vip del aeropuerto
"""
            21 -> """
📌 | That certification exam was a piece of cake | Ese examen de certificación fue pan comido
🌧️ | It is raining cats and dogs across the entire region | Está lloviendo a cántaros en toda la región
💰 | Prime real estate costs an arm and a leg nowadays | Los inmuebles en zonas privilegiadas cuestan un ojo de la cara hoy en día
👂 | Explain your proposal; we are all ears | Explique su propuesta; somos todo oídos
⏳ | Better late than never when pursuing your dreams | Más vale tarde que nunca a la hora de perseguir tus sueños
🥊 | Your analytical assessment hit the nail on the head | Tu valoración analítica dio en el clavo
🥱 | After sixteen hours awake, I must hit the sack | Tras dieciséis horas despierto, debo irme a dormir
🧊 | A witty remark served to break the ice smoothly | Un comentario ingenioso sirvió para romper el hielo con suavidad
🤫 | Do not spill the beans regarding the secret merger | No desveles el secreto respecto a la fusión confidencial
⚡ | The groundbreaking announcement came out of the blue | El innovador anuncio llegó completamente de la nada
"""
            22 -> """
💰 | Allocate emergency savings in interest-bearing accounts | Destina los ahorros de emergencia a cuentas con intereses
💳 | Settle revolving credit balances promptly each cycle | Liquida los saldos de crédito rotativo puntualmente cada ciclo
🏠 | Fixed-rate mortgages provide long-term payment stability | Las hipotecas a tipo fijo aportan estabilidad de pagos a largo plazo
📈 | Index funds provide diversified, cost-effective growth | Los fondos indexados proporcionan un crecimiento diversificado y rentable
📉 | Inflationary pressures erode real household income | Las presiones inflacionarias erosionan los ingresos reales de los hogares
💵 | Establish a contingency reserve for unexpected costs | Establece una reserva de contingencia para gastos imprevistos
🧾 | Audit your financial expenditures on a monthly basis | Audita tus gastos financieros mensualmente
🏧 | Automated teller machines are located in the lobby | Los cajeros automáticos se encuentran en el vestíbulo
💸 | Prudent budget management fosters long-term prosperity | Una gestión presupuestaria prudente fomenta la prosperidad a largo plazo
📊 | Equity markets rallied following positive economic reports | Los mercados bursátiles repuntaron tras los informes económicos positivos
"""
            23 -> """
⚖️ | Due process of law protects every citizen | El debido proceso legal protege a todos los ciudadanos
🏛️ | The appellate court upheld the prior ruling | El tribunal de apelación confirmó la sentencia anterior
👨‍⚖️ | The presiding magistrate reviewed legal precedents | El magistrado presidente revisó los precedentes legales
📄 | Formal legal agreements necessitate careful scrutiny | Los acuerdos legales formales requieren un examen cuidadoso
🔒 | Law enforcement officers executed the search warrant | Los agentes del orden ejecutaron la orden de registro
🔍 | Material evidence was entered into the court record | Las pruebas materiales se incorporaron al acta judicial
🗣️ | The witness provided compelling sworn testimony | El testigo aportó un testimonio bajo juramento convincente
🛡️ | Constitutional liberties safeguard democratic principles | Las libertades constitucionales salvaguardan los principios democráticos
📜 | Legislative statutes define civic responsibilities | Los estatutos legislativos definen las responsabilidades cívicas
💼 | Both commercial parties reached a binding settlement | Ambas partes comerciales alcanzaron un acuerdo vinculante
"""
            24 -> """
🔬 | Peer-reviewed discovery validates the research hypothesis | El descubrimiento revisado por pares valida la hipótesis de investigación
🚀 | Lunar exploration opens frontiers for space science | La exploración lunar abre fronteras para la ciencia espacial
🧬 | Molecular genetics unveils the mechanisms of inheritance | La genética molecular desvela los mecanismos de la herencia
🔭 | Astronomical observatories map distant stellar clusters | Los observatorios astronómicos cartografían cúmulos estelares lejanos
🧪 | Chemical compounds must be handled under controlled hoods | Los compuestos químicos deben manipularse bajo campanas controladas
⚡ | Sustainable energy technologies reduce fossil reliance | Las tecnologías de energía sostenible reducen la dependencia de fósiles
🌱 | Cellular respiration powers biological life processes | La respiración celular impulsa los procesos de la vida biológica
🤖 | Machine intelligence accelerates complex data modeling | La inteligencia artificial acelera el modelado de datos complejos
🌊 | Oceanic trenches harbor unique and resilient organisms | Las fosas oceánicas albergan organismos únicos y resistentes
🌋 | Geothermal activity powers renewable regional grids | La actividad geotérmica alimenta redes regionales renovables
"""
            25 -> """
🏛️ | Classical antiquity bequeathed monumental architectural wonders | La antigüedad clásica legó maravillas arquitectónicas monumentales
⚔️ | Medieval chronicles document shifting territorial boundaries | Las crónicas medievales documentan fronteras territoriales cambiantes
🏰 | Feudal fortifications stood as bastions of regional power | Las fortificaciones feudales se alzaban como bastiones de poder regional
📜 | Illuminated manuscripts preserve cultural lore through ages | Los manuscritos iluminados conservan la tradición cultural a través de los siglos
🚢 | Transoceanic navigation redefined international trade | La navegación transoceánica redefinió el comercio internacional
🏭 | Mechanized production altered demographic patterns globally | La producción mecanizada alteró los patrones demográficos a nivel global
🗽 | Democratic revolutions enshrined universal human rights | Las revoluciones democráticas consagraron los derechos humanos universales
📖 | Historical literacy enables nuanced societal reflection | El conocimiento histórico permite una reflexión social matizada
🏛️ | The Parthenon symbolizes ancient democratic ideals | El Partenón simboliza los ideales democráticos antiguos
🗺️ | Cartographic evolution mirrors centuries of exploration | La evolución cartográfica refleja siglos de exploración
"""
            26 -> """
🏠 | Countryside estates provide serenity away from bustle | Las fincas de campo proporcionan serenidad lejos del bullicio
🌆 | Cosmopolitan capitals offer endless culinary variety | Las capitales cosmopolitas ofrecen una infinita variedad gastronómica
🚗 | Commuter traffic requires efficient public transport | El tráfico de cercanías requiere un transporte público eficiente
🌳 | Urban green belts enhance communal life quality | Los cinturones verdes urbanos mejoran la calidad de vida comunitaria
🚲 | Dedicated bicycle infrastructure promotes safety | La infraestructura ciclista específica fomenta la seguridad
🏢 | Contemporary architecture transforms modern horizons | La arquitectura contemporánea transforma los horizontes modernos
🚜 | Sustainable agriculture sustains regional food security | La agricultura sostenible mantiene la seguridad alimentaria regional
🚇 | Underground metro lines connect outlying suburbs | Las líneas de metro subterráneas conectan los suburbios periféricos
🏡 | Cultivating private gardens connects homeowners to nature | Cultivar jardines privados conecta a los propietarios con la naturaleza
🛒 | Farmers markets showcase seasonal agricultural harvest | Los mercados de agricultores muestran las cosechas agrícolas de temporada
"""
            27 -> """
✈️ | International journeys broaden one's worldview | Los viajes internacionales amplían la visión del mundo
📸 | Portrait photography captures authentic human expression | La fotografía de retrato captura la expresión humana auténtica
🍳 | Culinary arts blend creativity with exact techniques | Las artes culinarias combinan creatividad con técnicas exactas
🪴 | Indoor botany enlivens interior living spaces | La botánica de interior da vida a los espacios residenciales interiores
♟️ | Grandmaster chess exemplifies deep tactical foresight | El ajedrez de grandes maestros ejemplifica una profunda previsión táctica
📚 | Devouring literature cultivates lifelong wisdom | Devorar literatura cultiva una sabiduría duradera
🎨 | Oil canvas painting requires patience and technique | La pintura al óleo sobre lienzo requiere paciencia y técnica
🎣 | Fly fishing along quiet rivers fosters tranquility | La pesca con mosca en ríos tranquilos fomenta la tranquilidad
🥾 | Alpine trekking offers panoramic mountain vistas | El senderismo alpino ofrece vistas panorámicas de la montaña
🧩 | Complex logic puzzles stimulate cognitive agility | Los rompecabezas de lógica compleja estimulan la agilidad cognitiva
"""
            28 -> """
🗣️ | Articulate enunciation enhances speech clarity | Una vocalización clara mejora la claridad del discurso
🤝 | Respectful listening bridges ideological divides | La escucha respetuosa salva las divisiones ideológicas
📧 | Concise written correspondence respects the recipient | La correspondencia escrita concisa respeta al destinatario
🎯 | Oratorical poise develops through repeated practice | El aplomo oratorio se desarrolla mediante la práctica repetida
💡 | Convey complex ideas in accessible language | Transmite ideas complejas en un lenguaje accesible
👀 | Sincere gaze establishes immediate personal trust | Una mirada sincera establece una confianza personal inmediata
📱 | Digital communication requires thoughtful phrasing | La comunicación digital requiere una redacción meditada
🤫 | Prudent silence often speaks louder than argument | El silencio prudente a menudo dice más que una discusión
🤝 | Empathic understanding disarms interpersonal tensions | La comprensión empática desarma las tensiones interpersonales
📢 | Clear projection ensures message comprehension | Una buena proyección asegura la comprensión del mensaje
"""
            29 -> """
🧭 | Unwavering ethics govern principled leadership | Una ética inquebrantable rige el liderazgo basado en principios
🤝 | Personal honor entails keeping every promise made | El honor personal conlleva cumplir cada promesa hecha
💖 | Selfless altruism ennobles human communities | El altruismo desinteresado ennoblece a las comunidades humanas
⚖️ | Impartial fairness must underwrite public policy | La imparcialidad justa debe respaldar las políticas públicas
🌱 | Lifelong learning expands human potential | El aprendizaje permanente amplía el potencial humano
🛡️ | Moral fortitude withstands popular pressure | La fortaleza moral resiste la presión popular
⏳ | Steadfast endurance overcomes daunting challenges | La resistencia constante supera desafíos abrumadores
🌟 | Expressing gratitude enriches daily existence | Expresar gratitud enriquece la existencia diaria
🎯 | Clear purpose channels focused human energy | Un propósito claro canaliza la energía humana enfocada
🕊️ | Harmonious coexistence demands mutual respect | La coexistencia armoniosa exige respeto mutuo
"""
            30 -> """
🇬🇧 | Relentless dedication ensures linguistic mastery | La dedicación constante garantiza el dominio lingüístico
🎧 | Immersive listening sharpens auditory perception | La escucha inmersiva agudiza la percepción auditiva
📖 | Reading diverse texts enriches linguistic nuance | Leer textos variados enriquece el matiz lingüístico
🗣️ | Conversational fluency emerges from persistent speech | La fluidez conversacional surge del habla persistente
✍️ | Rigorous writing cements syntactic precision | La escritura rigurosa afianza la precisión sintáctica
🧠 | Thinking in the target language eliminates translation delay | Pensar en el idioma de destino elimina el retraso de la traducción
🎬 | Authentic media exposes learners to natural idioms | Los medios auténticos exponen a los estudiantes a expresiones idiomáticas naturales
🎯 | Structured study schedules foster continuous progress | Los horarios de estudio estructurados fomentan un progreso continuo
⚡ | Diligence and patience transform foreign words into second nature | La diligencia y la paciencia transforman las palabras extranjeras en una segunda naturaleza
🎉 | Hearty congratulations on finishing this translation booklet! | ¡Mis más sinceras felicitaciones por terminar este cuaderno de traducción!
"""
            else -> ""
        }
    }
}
