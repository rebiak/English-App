package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentencesA {
    const val FOLDER_NAME = "Translations Sentences A"
    val categoryNames: List<String> = (1..30).map { "Translations A - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations A - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
❓ | What have you been discussing in the conference room? | ¿Qué habéis estado debatiendo en la sala de conferencias?
💼 | We have been analyzing the competitive landscape | Hemos estado analizando el panorama competitivo
🔍 | Have you noticed any discrepancy in these statistics? | ¿Has notado alguna discrepancia en estas estadísticas?
☕ | Would you prefer sparkling or still mineral water? | ¿Preferiría agua mineral con o sin gas?
🚗 | He has just landed after a transcontinental flight | Acaba de aterrizar tras un vuelo transcontinental
⏰ | We haven't finalized the procurement agreement yet | Todavía no hemos finalizado el contrato de aprovisionamiento
🌧️ | It has been unusually temperate throughout the autumn | Ha hecho una temperatura inusualmente suave durante todo el otoño
💡 | She has devised an ingenious logistics framework | Ha ideado un ingenioso marco logístico
🔑 | I have secured all sensitive intellectual property | He protegido toda la propiedad intelectual confidencial
📈 | Market capitalisation has exceeded initial forecasts | La capitalización de mercado ha superado las previsiones iniciales
"""
            2 -> """
❓ | Were you to be offered the position, would you relocate? | Si te ofrecieran el puesto, ¿te trasladarías?
🏖️ | Had we known about the storm, we would have postponed departure | De haber sabido de la tormenta, habríamos pospuesto la salida
💼 | Should you need further clarification, do not hesitate to ask | Si necesitara mayor aclaración, no dude en preguntar
🌧️ | If rainfall persists, harvesting will be delayed | Si persisten las lluvias, la cosecha se retrasará
🏠 | I wouldn't rush into signing without legal review | No me apresuraría a firmar sin una revisión legal
🚗 | If the train had arrived on schedule, we would be there | Si el tren hubiera llegado a tiempo, ya estaríamos allí
💰 | Provided financial covenants are met, the loan will proceed | Siempre que se cumplan los pactos financieros, el préstamo seguirá adelante
📞 | In the event she inquires, provide the summary sheet | En caso de que pregunte, facilítele la hoja resumen
✈️ | Where would you establish headquarters if given the choice? | ¿Dónde establecerías la sede si pudieras elegir?
📚 | If you master these principles, advanced grammar becomes intuitive | Si dominas estos principios, la gramática avanzada se vuelve intuitiva
"""
            3 -> """
🗣️ | The ambassador remarked that bilateral relations were stable | El embajador señaló que las relaciones bilaterales eran estables
❓ | He inquired whether the board had approved the acquisition | Preguntó si la junta directiva había aprobado la adquisición
💬 | They pledged that environmental targets would be strictly met | Se comprometieron a cumplir estrictamente los objetivos medioambientales
📌 | She requested a comprehensive breakdown of operational costs | Solicitó un desglose detallado de los costes operativos
📣 | The director urged all teams to maintain rigorous standards | El director instó a todos los equipos a mantener rigurosos estándares
🧠 | I stated that thorough analysis had preceded the recommendation | Declaré que un análisis minucioso había precedido a la recomendación
💬 | They conceded that unforeseen market volatility had occurred | Reconocieron que se había producido una volatilidad imprevista en el mercado
🤝 | She consented to represent the organization at the summit | Aceptó representar a la organización en la cumbre
💡 | He proposed integrating renewable generation across facilities | Propuso integrar la generación renovable en todas las instalaciones
🔒 | She instructed the team to archive all transaction records | Instruyó al equipo para que archivara todos los registros de transacciones
"""
            4 -> """
🏗️ | The high-speed rail corridor was completed ahead of time | El corredor ferroviario de alta velocidad se completó antes de tiempo
📄 | The quarterly statement will be published tomorrow at noon | El estado trimestral se publicará mañana al mediodía
🚗 | Telecommunications infrastructure is being overhauled | La infraestructura de telecomunicaciones está siendo renovada
📦 | Cargo consignments are inspected prior to customs release | Los envíos de carga se inspeccionan antes del despacho de aduanas
🔒 | Access to archives is restricted to authorized personnel | El acceso a los archivos está restringido al personal autorizado
🏆 | The architectural prize was conferred upon the lead designer | El premio de arquitectura fue otorgado al diseñador principal
🧹 | Cleanrooms are maintained under strict sterile protocols | Las salas blancas se mantienen bajo estrictos protocolos estériles
🏢 | This heritage site was designated a national landmark | Este sitio patrimonial fue declarado monumento nacional
📧 | Notification will be transmitted via encrypted channels | La notificación se transmitirá por canales cifrados
⚠️ | Compliance with environmental regulations is strictly enforced | El cumplimiento de las normas medioambientales se aplica estrictamente
"""
            5 -> """
⏰ | One ought to weigh the consequences before acting | Uno debería sopesar las consecuencias antes de actuar
💡 | You should have consulted the compliance department | Deberías haber consultado al departamento de cumplimiento normativo
⚠️ | Unauthorized personnel must not enter the server room | El personal no autorizado no debe entrar en la sala de servidores
❓ | Could you articulate the primary rationale behind this? | ¿Podría exponer la razón principal detrás de esto?
🏥 | She might be conferring with clinical specialists | Puede que ella esté deliberando con especialistas clínicos
🌧️ | Severe gales may disrupt maritime transport tonight | Fuertes vendavales podrían alterar el transporte marítimo esta noche
🔑 | You need not submit hard copies; digital files suffice | No es necesario presentar copias impresas; los archivos digitales bastan
🧠 | He must have overlooked the updated timeline | Debe de haber pasado por alto el calendario actualizado
🚫 | That inference cannot be reconciled with observable facts | Esa inferencia no se puede conciliar con los hechos observables
🏋️ | We had better verify the calibrations before proceeding | Más vale que verifiquemos las calibraciones antes de proceder
"""
            6 -> """
🎒 | We look forward to a productive and lasting collaboration | Esperamos una colaboración productiva y duradera
⏰ | Do not put off vital risk assessments | No pospongas las evaluaciones de riesgo vitales
💡 | She came up with a remarkably elegant algorithm | Se le ocurrió un algoritmo notablemente elegante
🧥 | Remove protective gloves before touching clean surfaces | Quítese los guantes de protección antes de tocar superficies limpias
📞 | Please remain on the line while your call is routed | Por favor, permanezca en la línea mientras se desvía su llamada
🚗 | Supplies ran out during the peak production cycle | Los suministros se agotaron durante el ciclo de máxima producción
🔍 | Investigators are looking into potential system flaws | Los investigadores están examinando posibles fallos del sistema
🏢 | The symposium was called off in light of weather warnings | El simposio fue suspendido a la luz de las alertas meteorológicas
🚪 | Deactivate peripheral devices before disconnecting main power | Desactive los dispositivos periféricos antes de desconectar la alimentación
📈 | Economic indicators are picking up across manufacturing | Los indicadores económicos están repuntando en el sector manufacturero
"""
            7 -> """
🏢 | How many subsidiaries does the conglomerate oversee? | ¿Cuántas filiales supervisa el conglomerado?
📈 | Net operating margins expanded significantly this semester | Los márgenes operativos netos se ampliaron significativamente este semestre
💼 | We have convened an extraordinary general meeting | Hemos convocado una junta general extraordinaria
📄 | Please scrutinize the indemnification clauses in detail | Por favor, examine minuciosamente las cláusulas de indemnización
🤝 | We executed a cross-border licensing agreement | Formalizamos un acuerdo de licencia transfronterizo
💰 | Capital expenditure was sanctioned by the board | El gasto de capital fue autorizado por la junta directiva
📊 | Quantitative models project sustained economic yield | Los modelos cuantitativos proyectan un rendimiento económico sostenido
🎯 | Ethical governance remains our core institutional pillar | La gobernanza ética sigue siendo nuestro pilar institucional central
✉️ | We welcome your comprehensive appraisal of this dossier | Agradecemos su valoración exhaustiva de este expediente
👥 | Fostering professional autonomy inspires creative breakthroughs | Fomentar la autonomía profesional inspira avances creativos
"""
            8 -> """
✈️ | International departures will commence through concourse B | Las salidas internacionales comenzarán por la sala B
🧳 | Please confirm your baggage complies with customs allowances | Confirme que su equipaje cumple las franquicias aduaneras
🏨 | We reserved a panoramic suite overlooking the historic square | Reservamos una suite panorámica con vistas a la plaza histórica
🛏️ | Concierge assistance is accessible throughout your stay | La asistencia de conserjería está disponible durante toda su estancia
🗺️ | Could you indicate prominent architectural landmarks? | ¿Podría señalar lugares arquitectónicos destacados?
🚆 | Does this high-speed service feature dedicated quiet cars? | ¿Este servicio de alta velocidad dispone de vagones silenciosos?
🎟️ | Priority admission passes for the retrospective exhibit | Pases de acceso prioritario para la exposición retrospectiva
🚕 | Please convey us to the diplomatic quarter | Por favor, trasládenos al barrio diplomático
💳 | All standard electronic payment mechanisms are accepted | Se aceptan todos los mecanismos estándar de pago electrónico
📍 | The botanical conservatory is within immediate walking distance | El conservatorio botánico está a poca distancia a pie
"""
            9 -> """
☕ | I begin the day with fresh citrus juice and silent reflection | Comienzo el día con zumo de cítricos fresco y reflexión silenciosa
🏃 | He engages in interval training along the riverside promenade | Realiza entrenamiento a intervalos por el paseo fluvial
🍳 | Wholesome grain bowls and fresh fruit fuel the morning | Boles de cereales integrales y fruta fresca alimentan la mañana
🚗 | Commuting via electric light rail minimizes travel friction | Desplazarse en tren ligero eléctrico minimiza las complicaciones del viaje
🎧 | Analytical podcasts illuminate contemporary international affairs | Podcasts analíticos arrojan luz sobre los asuntos internacionales contemporáneos
🛒 | We source heirloom produce from certified organic growers | Obtenemos productos tradicionales de agricultores ecológicos certificados
🍲 | A slow-simmered vegetable stew was served for supper | Se sirvió un estofado de verduras a fuego lento para la cena
🧹 | A pristine working environment cultivates intellectual clarity | Un entorno de trabajo impecable cultiva la claridad intelectual
📺 | We attended a livestreamed symposium on renewable energy | Asistimos a un simposio transmitido en directo sobre energía renovable
🛏️ | Consistent circadian rhythms optimize cognitive performance | Los ritmos circadianos regulares optimizan el rendimiento cognitivo
"""
            10 -> """
🌡️ | Atmospheric readings indicate twenty-one degrees Celsius | Las lecturas atmosféricas indican veintiún grados centígrados
☀️ | Unbroken sunshine illuminated the undulating plateau | Un sol resplandeciente iluminó la meseta ondulada
🌧️ | Persistent precipitation replenished regional water reservoirs | Las precipitaciones persistentes reabastecieron los embalses regionales
⚡ | Powerful electrical discharges illuminated towering storm clouds | Fuertes descargas eléctricas iluminaron imponentes nubarrones
❄️ | Alpine passes received substantial snowfall overnight | Los puertos alpinos recibieron importantes nevadas durante la noche
🧥 | Insulated technical garments withstand severe mountain winds | Las prendas técnicas térmicas resisten los fuertes vientos de montaña
🌈 | A broad rainbow arched gracefully across the eastern sky | Un amplio arcoíris se arqueó majestuosamente en el cielo oriental
🍂 | Deciduous canopies transform into brilliant amber hues | Las copas caducifolias se transforman en brillantes tonos ámbar
🏖️ | Temperate coastal currents moderate seasonal extremes | Las corrientes costeras templadas moderan los extremos estacionales
🌸 | Vernal flora blossoms profusely across the countryside | La flora primaveral florece con profusión por todo el campo
"""
            11 -> """
🏥 | Comprehensive wellness evaluations mitigate long-term health risks | Las evaluaciones integrales de bienestar mitigan riesgos para la salud
🤕 | Therapeutic interventions alleviate chronic musculoskeletal strain | Las intervenciones terapéuticas alivian la tensión musculoesquelética crónica
💊 | Administer pharmaceuticals in strict accordance with instructions | Administre los fármacos en estricta conformidad con las instrucciones
🩺 | The internist completed a thorough diagnostic workup | El internista completó una exhaustiva evaluación diagnóstica
🌡️ | Thermoregulation is maintained by neurovascular reflexes | La termorregulación se mantiene mediante reflejos neurovasculares
🩹 | Sterile dressings prevent bacterial contamination of wounds | Los vendajes estériles previenen la contaminación bacteriana de las heridas
🦷 | Regular prophylactic dental care preserves periodontal health | La profilaxis dental periódica preserva la salud periodontal
🤧 | Pulmonary defenses are strengthened by clean air and hydration | Las defensas pulmonares se fortalecen con aire limpio e hidratación
🥗 | Nutrient-dense whole foods support cellular vitality | Los alimentos integrales ricos en nutrientes favorecen la vitalidad celular
🏃 | Systematic aerobic conditioning improves metabolic longevity | El acondicionamiento aeróbico sistemático mejora la longevidad metabólica
"""
            12 -> """
🛒 | Specialty artisanal provisions are situated in wing four | Las provisiones artesanales selectas se ubican en el ala cuatro
🛍️ | I am seeking a bespoke presentation gift for a colleague | Busco un regalo de gala personalizado para un colega
👗 | This bespoke ensemble exhibits exceptional craftsmanship | Este conjunto a medida muestra una artesanía excepcional
👖 | Tailoring services include precise hem adjustments | Los servicios de sastrería incluyen ajustes precisos de dobladillo
🏷️ | Preferential rates apply to certified institutional patrons | Tarifas preferenciales se aplican a clientes institucionales acreditados
💳 | Encrypted payment protocols ensure financial security | Protocolos de pago cifrados garantizan la seguridad financiera
🧾 | Itemized digital statements are dispatched via secure portal | Los extractos digitales detallados se envían por un portal seguro
📦 | Courier transport includes tracked international delivery | El transporte por mensajería incluye entrega internacional con seguimiento
🏪 | The flagship emporium provides bespoke curation services | La tienda insignia ofrece servicios de asesoramiento personalizado
🥖 | Traditional sourdough is leavened through extended fermentation | La masa madre tradicional se fermenta mediante fermentación prolongada
"""
            13 -> """
🍽️ | May we review the chef's tasting degustation menu? | ¿Podemos revisar el menú degustación del chef?
🥗 | A starter of heritage beets, goat cheese and microgreens | Un entrante de remolachas tradicionales, queso de cabra y brotes
🥩 | The chef prepared the prime tenderloin with culinary finesse | El chef preparó el solomillo de primera con maestría culinaria
🍷 | A glass of chilled mineral water infused with mint | Una copa de agua mineral fría infusionada con menta
🐟 | Today's fish course features sustainably harvested turbot | El plato de pescado de hoy presenta rodaballo de captura sostenible
🌾 | Please record all dietary restrictions with the maitre d' | Registre todas las restricciones dietéticas con el maître
☕ | Two specialty espressos and the finalized account, please | Dos cafés expreso selectos y la cuenta definitiva, por favor
👨‍🍳 | Our profound appreciation for an exceptional gastronomic feast | Nuestro profundo agradecimiento por un banquete gastronómico excepcional
🍝 | Hand-rolled tortellini served in a delicate consommé | Tortellini enrollados a mano servidos en un delicado consomé
🥣 | Velvety roasted chestnut soup with a touch of truffle | Sopa aterciopelada de castañas asadas con un toque de trufa
"""
            14 -> """
🏫 | Rigorous academic scholarship enriches institutional legacy | La rigurosa labor académica enriquece el legado institucional
📚 | Peer-reviewed treatises undergo extensive methodological scrutiny | Los tratados revisados por pares se someten a un amplio análisis metodológico
📝 | The dissertation thesis must be formally defended | La tesis doctoral debe ser defendida formalmente
🎓 | Doctoral fellowships cultivate world-class specialized domain research | Las becas doctorales cultivan una investigación especializada de primer nivel
📖 | Deep textual analysis sharpens philosophical argumentation | El análisis textual profundo agudiza la argumentación filosófica
🔬 | Experimental hypotheses were corroborated through replication | Las hipótesis experimentales fueron corroboradas mediante replicación
🗣️ | Clear rhetorical structure elevates academic discourse | Una estructura retórica clara eleva el discurso académico
🧠 | Synthesizing multidisciplinary theories yields novel insights | Sintetizar teorías multidisciplinares genera perspectivas novedosas
👩‍🏫 | The colloquium hosted eminent researchers from across the globe | El coloquio acogió a eminentes investigadores de todo el mundo
📑 | Methodological transparency underpins scholarly integrity | La transparencia metodológica sustenta la integridad académica
"""
            15 -> """
💻 | Cloud migration ensures robust infrastructure resilience | La migración a la nube asegura una sólida resistencia de la infraestructura
📶 | High-bandwidth optical connectivity facilitates distributed computing | La conectividad óptica de gran ancho de banda facilita el cálculo distribuido
🔒 | End-to-end cryptographic encryption guarantees confidentiality | El cifrado criptográfico de extremo a extremo garantiza la confidencialidad
📱 | System firmware patches eliminate zero-day vulnerabilities | Los parches de firmware del sistema eliminan vulnerabilidades de día cero
💾 | Redundant off-site backups safeguard critical datasets | Las copias de seguridad redundantes externas protegen los conjuntos de datos críticos
📧 | Automated threat gateways neutralize sophisticated phishing | Las pasarelas automáticas de amenazas neutralizan el phishing sofisticado
🔋 | Advanced thermal management maximizes battery durability | La gestión térmica avanzada maximiza la durabilidad de la batería
🎧 | Acoustic isolation enhances auditory focus in noisy environments | El aislamiento acústico mejora la concentración auditiva en entornos ruidosos
☁️ | Container orchestration scales microservices dynamically | La orquestación de contenedores escala microservicios de forma dinámica
🖥️ | Color-calibrated monitors are essential for studio grading | Los monitores con calibración de color son esenciales para el etalonaje en estudio
"""
            16 -> """
⚽ | Strategic tactical discipline underpins football championship victories | La disciplina táctica estratégica sustenta las victorias en campeonatos de fútbol
🏀 | Defensive positioning and transition speed dictate basketball outcomes | La colocación defensiva y la velocidad de transición dictan los resultados en baloncesto
🎾 | Controlled baseline depth neutralizes powerful service returns | La profundidad controlada desde el fondo neutraliza saques potentes
🏊 | Streamlined hydrodynamics reduce friction in competitive swimming | La hidrodinámica depurada reduce la fricción en la natación de competición
🚴 | Power-to-weight optimization is critical on mountain climbs | La optimización de potencia y peso es crítica en puertos de montaña
🏃 | Aerobic pacing and metabolic fueling sustain ultramarathon running | El ritmo aeróbico y la nutrición metabólica sostienen la carrera de ultramaratón
🏆 | The athletic delegation showcased exemplary professionalism | La delegación atlética demostró una profesionalidad ejemplar
🥊 | Kinetic chain mechanics maximize striking precision | La mecánica de la cadena cinética maximiza la precisión de golpeo
🏋️ | Biomechanical efficiency prevents strain during heavy lifting | La eficiencia biomecánica previene sobrecargas durante levantamientos pesados
⛳ | Course management and psychological focus determine tournament success | La gestión del campo y la concentración psicológica determinan el éxito en torneos
"""
            17 -> """
🎵 | Polyphonic textures evoke transcendent emotional resonance | Las texturas polifónicas evocan una resonancia emocional trascendente
🎸 | Fingerstyle acoustic arrangements reveal intricate harmonic layers | Los arreglos acústicos de fingerstyle revelan complejas capas armónicas
🎹 | Delicate phrasing on the concert grand creates subtle nuance | El fraseo delicado en el piano de cola de concierto crea sutiles matices
🎨 | Renaissance perspective revolutionized Western spatial painting | La perspectiva renacentista revolucionó la pintura espacial occidental
🎬 | Visual storytelling and lighting sculpt evocative cinematic atmosphere | La narrativa visual y la iluminación esculpen una atmósfera cinematográfica evocadora
🎟️ | Advance reservations guarantee access to restricted gallery exhibits | Las reservas anticipadas garantizan el acceso a exposiciones de galería restringidas
🎭 | Classical drama explores enduring archetypes of the human condition | El drama clásico explora arquetipos imperecederos de la condición humana
🎻 | The string orchestra interpreted the composition with virtuosity | La orquesta de cuerda interpretó la composición con virtuosismo
📚 | Canonical literature invites timeless ethical and aesthetic inquiry | La literatura canónica invita a una indagación ética y estética intemporal
📸 | Fine art black-and-white photography isolates pure form and shadow | La fotografía artística en blanco y negro aísla la forma pura y la sombra
"""
            18 -> """
🤝 | Mutual trust and steadfast loyalty sustain lasting friendships | La confianza mutua y la lealtad inquebrantable sostienen amistades duraderas
❤️ | Deep emotional empathy and patience constitute true intimacy | La empatía emocional profunda y la paciencia constituyen la verdadera intimidad
👨‍👩‍👧‍👦 | Honoring generational heritage enriches family identity | Honrar el patrimonio generacional enriquece la identidad familiar
💬 | Compassionate and reasoned dialogue dissolves longstanding animosity | El diálogo compasivo y razonado disuelve la animadversión arraigada
👂 | Attentive, non-judgmental presence offers profound consolation | La presencia atenta y sin juicios ofrece un profundo consuelo
👶 | Nurturing developmental environments foster lifelong resilience | Los entornos de crianza enriquecedores fomentan una resiliencia duradera
🎂 | Commemorative family gatherings unite distant generations | Los encuentros familiares conmemorativos unen a generaciones distantes
🧑‍🤝‍🧑 | True companions provide unwavering shelter during crises | Los verdaderos compañeros brindan un refugio inquebrantable durante las crisis
🎁 | The sincerity of an act far outweighs any material gift | La sinceridad de un acto supera con creces cualquier regalo material
💔 | Forgiveness and shared understanding mend fractured relationships | El perdón y el entendimiento compartido sanan relaciones fracturadas
"""
            19 -> """
🌍 | Global ecological stewardship is an ethical imperative | La protección ecológica global es un imperativo ético
♻️ | Closed-loop circular systems eliminate structural resource waste | Los sistemas circulares de ciclo cerrado eliminan el desperdicio estructural de recursos
🌳 | Native reforestation stabilizes watersheds and captures atmospheric carbon | La reforestación autóctona estabiliza cuencas y captura carbono atmosférico
⚡ | Grid-scale renewable storage ensures clean energy reliability | El almacenamiento renovable a escala de red asegura la fiabilidad de la energía limpia
💧 | Advanced hydrological management secures agricultural food stability | La gestión hidrológica avanzada asegura la estabilidad alimentaria agrícola
🚗 | Decarbonized mobility networks substantially purify metropolitan air | Las redes de movilidad descarbonizada purifican sustancialmente el aire metropolitano
🦁 | Marine and terrestrial protected corridors safeguard biodiversity | Los corredores protegidos marinos y terrestres salvaguardan la biodiversidad
🌊 | Coastal mangrove restoration buffers shorelines against rising waters | La restauración de manglares costeros protege las costas de la crecida de las aguas
🚲 | Segregated active-mobility lanes cultivate vibrant, healthy cities | Los carriles segregados de movilidad activa cultivan ciudades vibrantes y saludables
🍃 | Immersion in pristine wilderness restores human cognitive balance | La inmersión en la naturaleza virgen restaura el equilibrio cognitivo humano
"""
            20 -> """
💼 | I encountered an uncatalogued archival manuscript by serendipity | Me encontré con un manuscrito de archivo no catalogado por casualidad
🔍 | Let us identify the fundamental cause of the systematic variance | Identifiquemos la causa fundamental de la variación sistemática
🚗 | The power transmission failed during high-stress testing | La transmisión de potencia falló durante las pruebas de alto esfuerzo
🚪 | Principled collaborators stand by their colleagues in all circumstances | Los colaboradores íntegros apoyan a sus colegas en toda circunstancia
📞 | I will return your detailed inquiry before the market close | Responderé a su consulta detallada antes del cierre de mercado
📝 | Please transcribe these technical depositions with absolute fidelity | Por favor, transcriba estas declaraciones técnicas con absoluta fidelidad
🧥 | Wear protective thermal outer layers during sub-zero operations | Use capas exteriores térmicas protectoras durante operaciones bajo cero
💡 | The review panel dismissed the unsubstantiated technical claim | El panel de revisión desestimó la afirmación técnica no fundamentada
⏰ | Critical milestone deadlines require coordinated and prompt execution | Los plazos de los hitos críticos exigen una ejecución coordinada y rápida
✈️ | We escorted the visiting delegation to their departure terminal | Acompañamos a la delegación visitante hasta su terminal de salida
"""
            21 -> """
📌 | That rigorous professional qualification was a piece of cake | Esa rigurosa titulación profesional fue pan comido
🌧️ | It is raining cats and dogs throughout the entire mountain basin | Está lloviendo a cántaros en toda la cuenca montañosa
💰 | Advanced cryogenic apparatus costs an arm and a leg | El instrumental criogénico avanzado cuesta un ojo de la cara
👂 | Present your comprehensive findings; we are all ears | Exponga sus conclusiones completas; somos todo oídos
⏳ | Better late than never when rectifying structural oversights | Más vale tarde que nunca a la hora de corregir descuidos estructurales
🥊 | Your incisive economic forecast hit the nail on the head | Su incisiva previsión económica dio en el clavo
🥱 | After concluding the clinical study, it is time to hit the sack | Tras concluir el estudio clínico, es hora de irse a dormir
🧊 | A witty opening remark served to break the ice effortlessly | Un comentario inicial ingenioso sirvió para romper el hielo sin esfuerzo
🤫 | Do not spill the beans regarding proprietary intellectual property | No desveles el secreto con respecto a la propiedad intelectual registrada
⚡ | The groundbreaking medical discovery occurred out of the blue | El revolucionario descubrimiento médico se produjo de la nada
"""
            22 -> """
💰 | Prudential asset preservation secures intergenerational family prosperity | La preservación prudente de activos asegura la prosperidad familiar intergeneracional
💳 | Extinguish high-cost revolving debt systematically and swiftly | Extinga la deuda rotativa de alto coste de forma sistemática y rápida
🏠 | Fixed-rate financing shields homeowners from interest volatility | La financiación a tipo fijo protege a los propietarios de la volatilidad de los intereses
📈 | Low-cost index funds deliver compounding, broad-market growth | Los fondos indexados de bajo coste brindan un crecimiento compuesto del mercado general
📉 | Currency depreciation erodes the purchasing power of liquid reserves | La depreciación monetaria erosiona el poder adquisitivo de las reservas líquidas
💵 | Maintain a liquid liquidity cushion for systemic eventualities | Mantenga un colchón de liquidez disponible para eventualidades sistémicas
🧾 | Undertake quarterly forensic audits of operational expenditures | Realice auditorías forenses trimestrales de los gastos operativos
🏧 | Biometric automated financial terminals ensure secure withdrawals | Terminales financieros automáticos biométricos aseguran retiradas seguras
💸 | Disciplined capital stewardship cultivates lasting sovereignty | La gestión disciplinada del capital cultiva una soberanía duradera
📊 | Sovereign treasury instruments demonstrated institutional stability | Los instrumentos del tesoro soberano demostraron estabilidad institucional
"""
            23 -> """
⚖️ | Constitutional jurisprudence guarantees equitable protection under the law | La jurisprudencia constitucional garantiza la protección equitativa ante la ley
🏛️ | The supreme appellate tribunal pronounced its definitive judgment | El tribunal supremo de apelación dictó su sentencia definitiva
👨‍⚖️ | The presiding magistrate weighed the admissible evidentiary record | El magistrado presidente sopesó el conjunto de pruebas admisibles
📄 | Complex multilateral covenants demand exhaustive legal review | Los pactos multilaterales complejos exigen una exhaustiva revisión legal
🔒 | Regulatory authorities enforced statutory mandates with precision | Las autoridades reguladoras hicieron cumplir los mandatos legales con precisión
🔍 | Chain-of-custody protocols verified the authenticity of exhibits | Los protocolos de cadena de custodia verificaron la autenticidad de las pruebas
🗣️ | The technical expert delivered authoritative sworn testimony | El perito técnico prestó un testimonio bajo juramento de gran autoridad
🛡️ | Fundamental civil liberties remain non-derogable pillars of statehood | Las libertades civiles fundamentales siguen siendo pilares inderogables del Estado
📜 | Codified statutes define the balance of rights and duties | Los estatutos codificados definen el equilibrio de derechos y obligaciones
💼 | Institutional arbitration concluded with a binding resolution | El arbitraje institucional concluyó con una resolución vinculante
"""
            24 -> """
🔬 | Replicable empirical experimentation underpins scientific progress | La experimentación empírica reproducible sustenta el progreso científico
🚀 | Interplanetary probes return high-resolution gravitational telemetry | Las sondas interplanetarias envían telemetría gravitacional de alta resolución
🧬 | Epigenetic mechanisms regulate hereditary gene expression | Los mecanismos epigenéticos regulan la expresión génica hereditaria
🔭 | Deep-space interferometers observe gravitational wave signatures | Los interferómetros del espacio profundo observan firmas de ondas gravitacionales
🧪 | Stoichiometric reactions proceed according to thermodynamic principles | Las reacciones estequiométricas se desarrollan según principios termodinámicos
⚡ | Fusion research aspires to deliver limitless clean baseload energy | La investigación en fusión aspira a proporcionar energía base limpia e ilimitada
🌱 | Chlorophyll complexes optimize photonic absorption efficiency | Los complejos de clorofila optimizan la eficiencia de absorción fotónica
🤖 | Autonomous reinforcement algorithms master complex control topologies | Algoritmos de refuerzo autónomos dominan complejas topologías de control
🌊 | Abyssal plains shelter unique chemotrophic bacterial ecosystems | Las llanuras abisales albergan ecosistemas bacterianos quimiotróficos únicos
🌋 | Geomechanical strain sensors forecast seismic volcanic episodes | Sensores de deformación geomecánica predicen episodios volcánicos sísmicos
"""
            25 -> """
🏛️ | Classical civilizations laid the structural foundations of jurisprudence | Las civilizaciones clásicas sentaron las bases estructurales de la jurisprudencia
⚔️ | Multilateral treaties historically resolved balance-of-power disputes | Los tratados multilaterales resolvieron históricamente disputas de equilibrio de poder
🏰 | Medieval bastion architecture reflected evolving siege technologies | La arquitectura de los bastiones medievales reflejaba las cambiantes tecnologías de asedio
📜 | Illuminated codices preserve philosophical wisdom across centuries | Los códices iluminados preservan la sabiduría filosófica a través de los siglos
🚢 | Transoceanic trade networks integrated ancient merchant economies | Las redes de comercio transoceánico integraron las antiguas economías mercantiles
🏭 | Industrial mechanization transformed human occupational patterns | La mecanización industrial transformó los patrones laborales humanos
🗽 | Constitutional charters codified immutable human liberties | Las cartas constitucionales codificaron libertades humanas inmutables
📖 | Rigorous historical historiography illuminates contemporary dilemmas | La rigurosa historiografía histórica ilumina los dilemas contemporáneos
🏛️ | The Acropolis embodies the artistic zenith of classical antiquity | La Acrópolis encarna el cenit artístico de la antigüedad clásica
🗺️ | Cartographic antiquities document shifting geographic comprehension | Las antigüedades cartográficas documentan la cambiante comprensión geográfica
"""
            26 -> """
🏠 | Pastoral retreats provide sanctuary from metropolitan overstimulation | Los retiros campestres proporcionan un santuario frente a la sobreestimulación urbana
🌆 | Cosmopolitan hubs foster dynamic artistic and commercial cross-pollination | Los centros cosmopolitas fomentan una dinámica polinización artística y comercial
🚗 | Intelligent transit systems mitigate chronic highway congestion | Los sistemas de transporte inteligente mitigan la congestión crónica de las autopistas
🌳 | Urban canopy expansion enhances microclimatic resilience and comfort | La expansión de la masa forestal urbana mejora la resiliencia y el confort microclimático
🚲 | Integrated bicycle highways promote non-motorized urban mobility | Las autopistas ciclistas integradas promueven la movilidad urbana no motorizada
🏢 | Low-carbon skyscrapers demonstrate sustainable structural engineering | Los rascacielos de bajo carbono demuestran una ingeniería estructural sostenible
🚜 | Regenerative agronomy restores soil organic microbiome vitality | La agronomía regenerativa restaura la vitalidad del microbioma orgánico del suelo
🚇 | High-throughput rail corridors seamlessly link distant metropolitan zones | Los corredores ferroviarios de alta capacidad conectan sin fisuras zonas metropolitanas lejanas
🏡 | Private botanical sanctuaries nurture peace and daily equilibrium | Los santuarios botánicos privados nutren la paz y el equilibrio diario
🛒 | Regional growers markets celebrate biodiverse local gastronomy | Los mercados de productores regionales celebran la gastronomía local biodiversa
"""
            27 -> """
✈️ | Mindful world exploration cultivates deep cosmopolitan empathy | La exploración consciente del mundo cultiva una profunda empatía cosmopolita
📸 | Documentary photography captures unvarnished historical moments | La fotografía documental captura momentos históricos sin retoques
🍳 | Gastronomic mastery unites technical precision with creative flair | La maestría gastronómica une la precisión técnica con el talento creativo
🪴 | Specialized indoor arboriculture requires horticultural expertise | La arboricultura de interior especializada requiere experiencia hortícola
♟️ | Grandmaster games illustrate deep strategic equilibrium and counterplay | Las partidas de grandes maestros ilustran un profundo equilibrio estratégico y contrajuego
📚 | Literary masterpieces provide profound insights into the human soul | Las obras maestras literarias ofrecen profundas reflexiones sobre el alma humana
🎨 | Figurative oil painting demands exhaustive mastery of chiaroscuro | La pintura al óleo figurativa exige un dominio exhaustivo del claroscuro
🎣 | Catch-and-release fly fishing honors natural river ecologies | La pesca con mosca sin muerte honra las ecologías fluviales naturales
🥾 | High-altitude mountaineering tests physical endurance and mental fortitude | El alpinismo de alta montaña pone a prueba la resistencia física y la fortaleza mental
🧩 | Multidimensional logic puzzles sharpen abstract problem-solving | Los rompecabezas de lógica multidimensional agudizan la resolución abstracta de problemas
"""
            28 -> """
🗣️ | Nuanced rhetoric communicates complex ideas with persuasive clarity | La retórica matizada comunica ideas complejas con claridad persuasiva
🤝 | Active listening underpins constructive conflict resolution | La escucha activa sustenta la resolución constructiva de conflictos
📧 | Professional correspondence reflects organizational values and respect | La correspondencia profesional refleja los valores y el respeto de la organización
🎯 | Public eloquence develops through disciplined rhetorical practice | La elocuencia pública se desarrolla mediante una práctica retórica disciplinada
💡 | Articulate synthesis distills complex concepts into accessible insights | La síntesis elocuente sintetiza conceptos complejos en reflexiones accesibles
👀 | Direct and sincere eye contact establishes immediate interpersonal rapport | El contacto visual directo y sincero establece una sintonía interpersonal inmediata
📱 | Considered digital messaging prevents unwarranted misunderstandings | La mensajería digital meditada evita malentendidos injustificados
🤫 | Deliberate rhetorical pauses underscore salient thematic points | Las pausas retóricas deliberadas subrayan puntos temáticos destacados
🤝 | Empathic perspective-taking dissolves deep inter-departmental divides | Adoptar una perspectiva empática disuelve profundas divisiones entre departamentos
📢 | Controlled vocal resonance commands respect and audience engagement | La resonancia vocal controlada infunde respeto y capta al público
"""
            29 -> """
🧭 | Uncompromising ethical integrity defines enduring leadership | La integridad ética inquebrantable define el liderazgo duradero
🤝 | Honoring one's commitments reflects profound personal dignity | Cumplir los propios compromisos refleja una profunda dignidad personal
💖 | Altruistic empathy enriches and elevates human society | La empatía altruista enriquece y eleva la sociedad humana
⚖️ | Just social institutions protect individual rights and universal dignity | Las instituciones sociales justas protegen los derechos individuales y la dignidad universal
🌱 | Continuous intellectual cultivation unlocks limitless human potential | El cultivo intelectual continuo desbloquea un potencial humano ilimitado
🛡️ | Moral resolve perseveres against collective apathy and cynicism | La firmeza moral persevera frente a la apatía y el cinismo colectivos
⏳ | Persistent diligence triumphs over transient worldly obstacles | La diligencia constante triunfa sobre los obstáculos mundanos transitorios
🌟 | Sincere daily gratitude transforms routine existence into purposeful life | La gratitud sincera diaria transforma la existencia rutinaria en una vida con propósito
🎯 | Clarity of singular purpose concentrates human endeavor and triumph | La claridad de un único propósito concentra el esfuerzo humano y el triunfo
🕊️ | Universal mutual respect lays the foundation for global harmony | El respeto mutuo universal sienta las bases de la armonía global
"""
            30 -> """
🇬🇧 | Relentless dedication transforms foreign language study into fluent mastery | La dedicación constante transforma el estudio de idiomas en un dominio fluido
🎧 | Immersive auditory practice attunes the mind to subtle idioms | La práctica auditiva inmersiva sintoniza la mente con modismos sutiles
📖 | Consuming classical literature enriches linguistic depth and precision | Consumir literatura clásica enriquece la profundidad y precisión lingüísticas
🗣️ | Confident spontaneous dialogue emerges from continuous daily practice | El diálogo espontáneo y seguro surge de la práctica diaria continua
✍️ | Rigorous academic writing cements sophisticated grammatical architecture | La escritura académica rigurosa afianza una sofisticada arquitectura gramatical
🧠 | Intuitive internal thought in English dispenses with translation steps | El pensamiento interno intuitivo en inglés prescinde de los pasos de traducción
🎬 | Authentic cultural media reveals the living soul of the language | Los medios culturales auténticos revelan el alma viva de la lengua
🎯 | Sustained focus on communicative mastery achieves true bilingualism | El enfoque continuo en el dominio comunicativo logra el verdadero bilingüismo
⚡ | Diligence, curiosity and practice transform every linguistic challenge | La diligencia, la curiosidad y la práctica transforman cada desafío lingüístico
🎉 | Congratulations on mastering this entire translation booklet! | ¡Enhorabuena por dominar este cuaderno completo de traducción!
"""
            else -> ""
        }
    }
}
