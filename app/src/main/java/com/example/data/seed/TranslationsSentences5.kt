package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences5 {
    const val FOLDER_NAME = "Translations Sentences 5"
    val categoryNames: List<String> = (1..30).map { "Translations 5 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations 5 - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
❓ | What have you been doing all morning? | ¿Qué has estado haciendo toda la mañana?
🏢 | I have been working on the monthly report | He estado trabajando en el informe mensual
🔍 | Has anyone seen my notebook? | ¿Alguien ha visto mi cuaderno?
☕ | Would you care for some coffee or tea? | ¿Le apetecería un poco de café o té?
🚗 | My brother has just bought a new car | Mi hermano se acaba de comprar un coche nuevo
⏰ | We haven't received their reply yet | Todavía no hemos recibido su respuesta
🌧️ | It has been snowing in the mountains | Ha estado nevando en las montañas
💡 | She has come up with an interesting idea | Se le ha ocurrido una idea interesante
🔑 | I have left my keys inside the house | Me he dejado las llaves dentro de la casa
📈 | The company's profits have doubled this year | Los beneficios de la empresa se han duplicado este año
"""
            2 -> """
🏖️ | If we had more money, we would go on vacation | Si tuviéramos más dinero, nos iríamos de vacaciones
🚗 | If he drove slower, he would be safer | Si condujera más despacio, estaría más seguro
🌧️ | What will you do if it rains this weekend? | ¿Qué harás si llueve este fin de semana?
🏠 | I wouldn't accept that offer if I were you | Yo no aceptaría esa oferta si fuera tú
📱 | If you send me a message, I will reply immediately | Si me envías un mensaje, responderé de inmediato
💼 | If they offer you the position, will you take it? | Si te ofrecen el puesto, ¿lo aceptarás?
🍕 | If you are hungry, we can cook something | Si tienes hambre, podemos cocinar algo
🤝 | We would gladly help you if we had time | Te ayudaríamos con gusto si tuviéramos tiempo
❓ | What would happen if nobody came to the event? | ¿Qué pasaría si nadie viniera al evento?
📚 | If you study every day, you will improve quickly | Si estudias todos los días, mejorarás rápidamente
"""
            3 -> """
🗣️ | She told me that she was moving to Spain | Me dijo que se mudaba a España
❓ | He asked me if I had seen his glasses | Me preguntó si había visto sus gafas
💬 | They promised they would arrive before dark | Prometieron que llegarían antes del anochecer
📌 | She asked what time the bank opened | Preguntó a qué hora abría el banco
📣 | He warned us that the road was dangerous | Nos advirtió que la carretera era peligrosa
🧠 | I explained that I didn't know the answer | Expliqué que no sabía la respuesta
💬 | They claimed that they had never been there | Afirmaron que nunca habían estado allí
🤝 | She agreed to help us with the preparations | Aceptó ayudarnos con los preparativos
💡 | He suggested that we meet at the café | Sugirió que nos viéramos en la cafetería
🔒 | She reminded him to lock the front door | Le recordó que cerrara la puerta principal
"""
            4 -> """
🏗️ | A new stadium was built in the city | Se construyó un nuevo estadio en la ciudad
📄 | The invitations were sent out yesterday | Las invitaciones fueron enviadas ayer
🚗 | The road is being repaired right now | La carretera está siendo reparada ahora mismo
📦 | All orders are shipped within twenty-four hours | Todos los pedidos se envían en 24 horas
🔒 | The museum is closed on Mondays | El museo está cerrado los lunes
🏆 | She was awarded first prize in the competition | Fue galardonada con el primer premio en el concurso
🧹 | The windows are cleaned every month | Las ventanas se limpian todos los meses
🏢 | This historic building was restored last year | Este edificio histórico fue restaurado el año pasado
📧 | You will be notified by email | Se le notificará por correo electrónico
⚠️ | Safety helmets must be worn at all times | Se deben llevar cascos de seguridad en todo momento
"""
            5 -> """
⏰ | You should drink more water every day | Deberías beber más agua todos los días
💡 | You should have consulted a specialist | Deberías haber consultado a un especialista
⚠️ | You must not cross the street on red | No debes cruzar la calle en rojo
❓ | Could you tell me the way to the station? | ¿Podría indicarme el camino a la estación?
🏥 | He might be at the pharmacy right now | Puede que él esté en la farmacia ahora mismo
🌧️ | It may get colder later tonight | Puede que haga más frío más tarde esta noche
🔑 | You don't need to bring any extra equipment | No necesitas traer ningún equipo adicional
🧠 | She must have missed her train | Debe de haber perdido su tren
🚫 | That can't be true | Eso no puede ser verdad
🏋️ | We had better leave before the storm begins | Más vale que nos vayamos antes de que empiece la tormenta
"""
            6 -> """
🎒 | I look forward to hearing from you soon | Espero tener noticias tuyas pronto
⏰ | Don't put off cleaning your room | No pospongas limpiar tu habitación
💡 | He came up with an innovative proposal | Se le ocurrió una propuesta innovadora
🧥 | Take off your jacket if you feel warm | Quítate la chaqueta si tienes calor
📞 | Please hold on while I transfer your call | Por favor, espere mientras transfiero su llamada
🚗 | We ran out of coffee this morning | Nos quedamos sin café esta mañana
🔍 | I'm looking for my car keys | Estoy buscando las llaves de mi coche
🏢 | They called off the match due to rain | Cancelaron el partido debido a la lluvia
🚪 | Turn off the lights when leaving the room | Apaga las luces al salir de la habitación
📈 | Sales are picking up this month | Las ventas están repuntando este mes
"""
            7 -> """
🏢 | How many people work in your department? | ¿Cuántas personas trabajan en tu departamento?
📈 | Our team achieved great results this quarter | Nuestro equipo logró grandes resultados este trimestre
💼 | Let's arrange a meeting for next Tuesday | Concertemos una reunión para el próximo martes
📄 | Please review the contract terms carefully | Por favor, revise los términos del contrato con atención
🤝 | We signed a multi-year partnership | Firmamos una colaboración de varios años
💰 | The board approved the annual budget | La junta directiva aprobó el presupuesto anual
📊 | Market research shows growing demand | El estudio de mercado muestra una creciente demanda
🎯 | Quality is our highest priority | La calidad es nuestra máxima prioridad
✉️ | Thank you for your swift reply | Gracias por su rápida respuesta
👥 | Good leadership inspires the whole team | Un buen liderazgo inspira a todo el equipo
"""
            8 -> """
✈️ | Flight 105 will board in fifteen minutes | El vuelo 105 embarcará en quince minutos
🧳 | Is this bag within the carry-on limit? | ¿Este bolso está dentro del límite de equipaje de mano?
🏨 | We have a reservation for two nights | Tenemos una reserva para dos noches
🛏️ | What time is breakfast served? | ¿A qué hora se sirve el desayuno?
🗺️ | Can you show me this place on the map? | ¿Puede mostrarme este lugar en el mapa?
🚆 | Is this the express train to the capital? | ¿Es este el tren directo a la capital?
🎟️ | I'd like one round-trip ticket, please | Quisiera un billete de ida y vuelta, por favor
🚕 | Take me to the city center, please | Lléveme al centro de la ciudad, por favor
💳 | Do you accept contactless payment? | ¿Aceptan pago sin contacto?
📍 | It's just a ten-minute walk from here | Está a solo diez minutos a pie desde aquí
"""
            9 -> """
☕ | I drink two glasses of water every morning | Bebo dos vasos de agua todas las mañanas
🏃 | She exercises for thirty minutes every day | Hace ejercicio durante treinta minutos todos los días
🍳 | We usually have eggs and toast for breakfast | Solemos desayunar huevos y tostadas
🚗 | Traffic is always heavy in the morning | El tráfico siempre está pesado por la mañana
🎧 | Listening to podcasts makes commuting fun | Escuchar podcasts hace que el trayecto sea divertido
🛒 | We need to buy milk, bread and fruit | Necesitamos comprar leche, pan y fruta
🍲 | Dinner will be ready in twenty minutes | La cena estará lista en veinte minutos
🧹 | I like to keep my workspace organized | Me gusta mantener mi espacio de trabajo organizado
📺 | We watched an interesting movie last night | Vimos una película interesante anoche
🛏️ | Getting enough rest is essential for health | Descansar lo suficiente es esencial para la salud
"""
            10 -> """
🌡️ | It's twenty-five degrees Celsius today | Hace veinticinco grados centígrados hoy
☀️ | The weather is pleasant and sunny | El tiempo está agradable y soleado
🌧️ | It started raining heavily this afternoon | Empezó a llover con fuerza esta tarde
⚡ | Lightning lit up the dark sky | Los relámpagos iluminaron el cielo oscuro
❄️ | Fresh snow covered the streets | La nieve fresca cubrió las calles
🧥 | Put on your scarf, it's very cold | Ponte la bufanda, hace mucho frío
🌈 | Look at that bright rainbow over there | Mira ese brillante arcoíris por allí
🍂 | The trees are losing their leaves | Los árboles están perdiendo sus hojas
🏖️ | We spent the whole afternoon at the beach | Pasamos toda la tarde en la playa
🌸 | Spring is full of color and life | La primavera está llena de color y vida
"""
            11 -> """
🏥 | I need to schedule a health checkup | Necesito programar un chequeo médico
🤕 | My throat has been sore for three days | Me ha dolido la garganta durante tres días
💊 | Don't forget to take your vitamins | No olvides tomarte las vitaminas
🩺 | The doctor listened to my heart rate | El médico escuchó mi ritmo cardíaco
🌡️ | Your temperature is completely normal | Tu temperatura es completamente normal
🩹 | Wash the wound before applying a bandage | Lava la herida antes de poner una tirita
🦷 | I brush my teeth three times a day | Me cepillo los dientes tres veces al día
🤧 | Cover your mouth when you cough or sneeze | Cúbrete la boca al toser o estornudar
🥗 | Fresh vegetables are packed with vitamins | Las verduras frescas están llenas de vitaminas
🏃 | A daily walk improves cardiovascular health | Un paseo diario mejora la salud cardiovascular
"""
            12 -> """
🛒 | Where can I find fresh organic fruit? | ¿Dónde puedo encontrar fruta fresca ecológica?
🛍️ | I'm looking for a warm winter jacket | Estoy buscando una chaqueta de invierno abrigada
👗 | This dress fits you really well | Este vestido te queda realmente bien
👖 | Do you have these pants in dark blue? | ¿Tienen estos pantalones en azul oscuro?
🏷️ | Is there any discount on this item? | ¿Hay algún descuento en este artículo?
💳 | Can I pay by debit card? | ¿Puedo pagar con tarjeta de débito?
🧾 | Here is your receipt and change | Aquí tiene su recibo y el cambio
📦 | The parcel arrived in great condition | El paquete llegó en excelentes condiciones
🏪 | This supermarket is open on Sundays | Este supermercado abre los domingos
🥖 | We need to buy a loaf of whole-wheat bread | Necesitamos comprar una barra de pan integral
"""
            13 -> """
🍽️ | May I see the dessert selection? | ¿Puedo ver la selección de postres?
🥗 | I'll have the soup of the day to start | Tomaré la sopa del día para empezar
🥩 | The grilled salmon was delicious | El salmón a la plancha estaba delicioso
🍷 | A glass of fresh orange juice, please | Un vaso de zumo de naranja natural, por favor
🐟 | Is this seafood locally caught? | ¿Este marisco es de captura local?
🌾 | I have an allergy to shellfish | Tengo alergia al marisco
☕ | Could you bring the bill, please? | ¿Podría traer la cuenta, por favor?
👨‍🍳 | That was a fantastic meal | Ha sido una comida fantástica
🍝 | Fresh pasta with tomato and basil | Pasta fresca con tomate y albahaca
🥣 | This vegetable soup is very comforting | Esta sopa de verduras es muy reconfortante
"""
            14 -> """
🏫 | He graduated from high school with honors | Se graduó del instituto con honores
📚 | She spends hours studying in the library | Pasa horas estudiando en la biblioteca
📝 | The final project is due on Friday | El proyecto final se entrega el viernes
🎓 | Higher education opens many new doors | La educación superior abre muchas puertas nuevas
📖 | Remember to read chapter five tonight | Recuerda leer el capítulo cinco esta noche
🔬 | We conducted a science experiment | Realizamos un experimento de ciencias
🗣️ | Practice your speaking skills daily | Practica tus habilidades orales a diario
🧠 | Learning a new language takes patience | Aprender un idioma nuevo requiere paciencia
👩‍🏫 | The teacher gave helpful explanations | La profesora dio explicaciones útiles
📑 | Make sure to organize your study notes | Asegúrate de organizar tus apuntes de estudio
"""
            15 -> """
💻 | Did you save your work before closing? | ¿Guardaste tu trabajo antes de cerrar?
📶 | The internet speed is very fast here | La velocidad de internet es muy rápida aquí
🔒 | Use a two-factor authentication method | Utiliza un método de autenticación en dos pasos
📱 | Update your apps to the newest version | Actualiza tus aplicaciones a la versión más reciente
💾 | Always keep a backup of important data | Guarda siempre una copia de seguridad de los datos importantes
📧 | Check your email for the confirmation link | Revisa tu correo para el enlace de confirmación
🔋 | My phone battery is at ninety percent | La batería de mi móvil está al noventa por ciento
🎧 | Connect your bluetooth device now | Conecta tu dispositivo bluetooth ahora
☁️ | Cloud storage is secure and convenient | El almacenamiento en la nube es seguro y práctico
🖥️ | Turn off your screen when you leave | Apaga la pantalla cuando te vayas
"""
            16 -> """
⚽ | The team played exceptionally well | El equipo jugó excepcionalmente bien
🏀 | He scored twenty points in the match | Anotó veinte puntos en el partido
🎾 | Tennis is a very demanding sport | El tenis es un deporte muy exigente
🏊 | Swimming laps builds full-body endurance | Nadar largos desarrolla la resistencia de todo el cuerpo
🚴 | We went on a long bicycle ride today | Hicimos una larga ruta en bicicleta hoy
🏃 | Running in the morning boosts your energy | Correr por la mañana aumenta tu energía
🏆 | They celebrated their championship victory | Celebraron su victoria en el campeonato
🥊 | Martial arts teach discipline and focus | Las artes marciales enseñan disciplina y concentración
🏋️ | Strength training prevents joint injuries | El entrenamiento de fuerza previene lesiones articulares
⛳ | The golf course was in pristine condition | El campo de golf estaba en condiciones impecables
"""
            17 -> """
🎵 | Music has the power to inspire people | La música tiene el poder de inspirar a las personas
🎸 | He loves playing Spanish guitar melodies | Le encanta tocar melodías de guitarra española
🎹 | She practiced piano for two hours | Practicó el piano durante dos horas
🎨 | The art gallery had beautiful paintings | La galería de arte tenía hermosas pinturas
🎬 | We watched a classic film last night | Vimos una película clásica anoche
🎟️ | I reserved two tickets for the concert | Reservé dos entradas para el concierto
🎭 | The theater production was magnificent | La producción teatral fue magnífica
🎻 | The violin music filled the entire hall | La música de violín llenó toda la sala
📚 | Literature reflects the human experience | La literatura refleja la experiencia humana
📸 | Taking photos captures precious memories | Hacer fotos captura recuerdos preciosos
"""
            18 -> """
🤝 | A good friend is always there for you | Un buen amigo siempre está ahí para ti
❤️ | Love and respect are vital in relationships | El amor y el respeto son vitales en las relaciones
👨‍👩‍👧‍👦 | Spending time with family is essential | Pasar tiempo con la familia es esencial
💬 | Open dialogue resolves most conflicts | El diálogo abierto resuelve la mayoría de los conflictos
👂 | Listening is a key part of communication | Escuchar es una parte clave de la comunicación
👶 | The newborn baby slept peacefully | El recién nacido durmió plácidamente
🎂 | We celebrated her birthday with a cake | Celebramos su cumpleaños con una tarta
🧑‍🤝‍🧑 | We have been close friends for a decade | Hemos sido amigos cercanos durante una década
🎁 | A thoughtful present shows you care | Un regalo pensado demuestra que te importa
💔 | Time heals all emotional wounds | El tiempo cura todas las heridas emocionales
"""
            19 -> """
🌍 | We must protect the environment for future generations | Debemos proteger el medio ambiente para las generaciones futuras
♻️ | Recycling reduces landfill waste | El reciclaje reduce los residuos en los vertederos
🌳 | Plant a tree and help the planet | Planta un árbol y ayuda al planeta
⚡ | Solar and wind energy are renewable | La energía solar y eólica son renovables
💧 | Water is our most precious resource | El agua es nuestro recurso más preciado
🚗 | Public transportation reduces pollution | El transporte público reduce la contaminación
🦁 | Wildlife conservation is critical | La conservación de la vida silvestre es crítica
🌊 | Protect our oceans from plastic pollution | Protege nuestros océanos de la contaminación por plásticos
🚲 | Riding a bicycle reduces your carbon footprint | Montar en bicicleta reduce tu huella de carbono
🍃 | Nature provides tranquility and balance | La naturaleza proporciona tranquilidad y equilibrio
"""
            20 -> """
💼 | I came across an old photo album | Me encontré con un viejo álbum de fotos
🔍 | Let's figure out the root cause | Averigüemos la causa principal
🚗 | My car broke down on the bridge | Mi coche se averió en el puente
🚪 | Don't let your friends down | No defraudes a tus amigos
📞 | Call me back when you get a chance | Llámame de vuelta cuando tengas oportunidad
📝 | Please write down your contact details | Por favor, anota tus datos de contacto
🧥 | Put on your jacket before leaving | Ponte la chaqueta antes de salir
💡 | They turned down our initial request | Rechazaron nuestra petición inicial
⏰ | Time is running out, let's hurry | El tiempo se acaba, démonos prisa
✈️ | We saw our guests off at the train station | Despedimos a nuestros invitados en la estación de tren
"""
            21 -> """
📌 | That test was a piece of cake | Ese examen fue pan comido
🌧️ | It's raining cats and dogs right now | Está lloviendo a cántaros ahora mismo
💰 | The new phone costs an arm and a leg | El teléfono nuevo cuesta un ojo de la cara
👂 | Tell me the news, I'm all ears | Cuéntame las novedades, soy todo oídos
⏳ | Better late than never, my friend | Más vale tarde que nunca, amigo mío
🥊 | You hit the nail right on the head | Has dado en el clavo justo
🥱 | I'm exhausted, time to hit the sack | Estoy agotado, hora de irse a dormir
🧊 | A funny story broke the ice nicely | Una historia divertida rompió el hielo muy bien
🤫 | Don't spill the beans about the party | No reveles el secreto sobre la fiesta
⚡ | The news arrived completely out of the blue | La noticia llegó completamente por sorpresa
"""
            22 -> """
💰 | Save ten percent of your monthly income | Ahorra el diez por ciento de tus ingresos mensuales
💳 | Avoid carry-over credit card debt | Evita acumular deudas de tarjeta de crédito
🏠 | They financed their home with a mortgage | Financiaron su casa con una hipoteca
📈 | Long-term investing builds financial security | La inversión a largo plazo construye seguridad financiera
📉 | Inflation affects the cost of goods | La inflación afecta al coste de los bienes
💵 | Build an emergency fund for peace of mind | Construye un fondo de emergencia para tener tranquilidad
🧾 | Keep track of your daily expenses | Lleva un registro de tus gastos diarios
🏧 | Is there an ATM nearby? | ¿Hay un cajero automático cerca?
💸 | Smart budgeting leads to financial freedom | Un presupuesto inteligente conduce a la libertad financiera
📊 | The market index reached a new peak | El índice bursátil alcanzó un nuevo máximo
"""
            23 -> """
⚖️ | All individuals are equal under the law | Todas las personas son iguales ante la ley
🏛️ | The supreme court announced its decision | El tribunal supremo anunció su decisión
👨‍⚖️ | The judge presided over the trial | El juez presidió el juicio
📄 | Read the contract before putting your signature | Lee el contrato antes de poner tu firma
🔒 | The police conducted a thorough investigation | La policía llevó a cabo una investigación exhaustiva
🔍 | Forensic evidence was presented in court | Se presentaron pruebas forenses en el tribunal
🗣️ | The witness answered all questions honestly | El testigo respondió a todas las preguntas con sinceridad
🛡️ | Legal rights must be defended | Los derechos legales deben ser defendidos
📜 | The constitution guarantees free speech | La constitución garantiza la libertad de expresión
💼 | Both companies agreed to an out-of-court settlement | Ambas empresas acordaron un acuerdo extrajudicial
"""
            24 -> """
🔬 | Scientists made a breakthrough in medicine | Los científicos lograron un gran avance en medicina
🚀 | The satellite was launched successfully | El satélite fue lanzado con éxito
🧬 | Genetics explains hereditary characteristics | La genética explica las características hereditarias
🔭 | Telescopes allow us to study distant galaxies | Los telescopios nos permiten estudiar galaxias lejanas
🧪 | Always follow laboratory safety protocols | Sigue siempre los protocolos de seguridad de laboratorio
⚡ | Clean energy helps combat global warming | La energía limpia ayuda a combatir el calentamiento global
🌱 | Plants absorb carbon dioxide through leaves | Las plantas absorben dióxido de carbono por las hojas
🤖 | Artificial intelligence continues to evolve | La inteligencia artificial sigue evolucionando
🌊 | Ocean depths remain largely unexplored | Las profundidades oceánicas siguen en gran parte inexploradas
🌋 | Volcanic ash can travel across continents | La ceniza volcánica puede viajar a través de continentes
"""
            25 -> """
🏛️ | Ancient civilizations built amazing structures | Las civilizaciones antiguas construyeron estructuras asombrosas
⚔️ | History teaches us valuable lessons | La historia nos enseña valiosas lecciones
🏰 | Medieval history is full of fascinating events | La historia medieval está llena de acontecimientos fascinantes
📜 | Ancient scrolls were preserved in jars | Pergaminos antiguos se conservaron en vasijas
🚢 | Explorers mapped the world during the age of discovery | Los exploradores cartografiaron el mundo durante la era de los descubrimientos
🏭 | The industrial age revolutionized manufacturing | La era industrial revolucionó la manufactura
🗽 | Freedom is a fundamental human value | La libertad es un valor humano fundamental
📖 | Understanding history helps us understand the present | Entender la historia nos ayuda a comprender el presente
🏛️ | The Roman Forum was the center of ancient life | El Foro Romano era el centro de la vida antigua
🗺️ | Old trade routes connected distant continents | Antiguas rutas comerciales conectaban continentes distantes
"""
            26 -> """
🏠 | Country life offers fresh air and tranquility | La vida en el campo ofrece aire fresco y tranquilidad
🌆 | Big cities offer vibrant cultural activities | Las grandes ciudades ofrecen actividades culturales vibrantes
🚗 | Avoid driving during peak rush hours | Evita conducir durante las horas punta
🌳 | City parks provide refreshing green spaces | Los parques urbanos proporcionan espacios verdes refrescantes
🚲 | More people are commuting on bicycles | Más personas se desplazan en bicicleta
🏢 | Modern skyscrapers define the skyline | Rascacielos modernos definen el horizonte
🚜 | Farming requires patience and dedication | La agricultura requiere paciencia y dedicación
🚇 | The subway connects all major neighborhoods | El metro conecta los principales barrios
🏡 | Having a small garden is very enjoyable | Tener un pequeño jardín es muy agradable
🛒 | Local markets sell fresh regional produce | Los mercados locales venden productos regionales frescos
"""
            27 -> """
✈️ | Travel exposes you to new perspectives | Viajar te expone a nuevas perspectivas
📸 | Photography captures unique moments in time | La fotografía captura momentos únicos en el tiempo
🍳 | Homemade meals are both tasty and healthy | Las comidas caseras son sabrosas y saludables
🪴 | Houseplants make indoor spaces lively | Las plantas de interior alegran los espacios interiores
♟️ | Chess teaches patience and strategy | El ajedrez enseña paciencia y estrategia
📚 | Reading daily broadens your imagination | Leer a diario amplía tu imaginación
🎨 | Creative painting is a wonderful outlet | La pintura creativa es una salida maravillosa
🎣 | Fishing early in the morning is relaxing | Pescar temprano por la mañana es relajante
🥾 | Mountain hiking boosts physical endurance | El senderismo de montaña aumenta la resistencia física
🧩 | Solving puzzles keeps your mind active | Resolver rompecabezas mantiene tu mente activa
"""
            28 -> """
🗣️ | Clear speech prevents misunderstandings | El habla clara evita malentendidos
🤝 | Active listening shows genuine respect | La escucha activa demuestra respeto genuino
📧 | Write clear and professional emails | Escribe correos claros y profesionales
🎯 | Confidence grows through constant practice | La confianza crece con la práctica constante
💡 | Share your ideas with enthusiasm | Comparte tus ideas con entusiasmo
👀 | Maintain pleasant eye contact during conversations | Mantén un contacto visual agradable durante las conversaciones
📱 | Be mindful with digital communications | Ten cuidado con las comunicaciones digitales
🤫 | Knowing when to stay silent is an art | Saber cuándo guardar silencio es un arte
🤝 | Try to understand diverse viewpoints | Intenta comprender diversos puntos de vista
📢 | Speak loud enough for everyone to hear | Habla lo suficientemente alto para que todos escuchen
"""
            29 -> """
🧭 | Guided by strong moral principles | Guiado por sólidos principios morales
🤝 | Integrity is doing right even when alone | La integridad es hacer lo correcto incluso estando solo
💖 | Compassion makes the world a better place | La compasión hace del mundo un lugar mejor
⚖️ | Treat everyone with equal dignity and respect | Trata a todos con igual dignidad y respeto
🌱 | Continuous self-improvement is key | La mejora personal continua es clave
🛡️ | Courage means acting despite your fears | El valor significa actuar a pesar de tus miedos
⏳ | Patience leads to long-term success | La paciencia conduce al éxito a largo plazo
🌟 | Appreciate the positive things in your life | Aprecia las cosas positivas de tu vida
🎯 | Keep your focus on your main goals | Mantén el foco en tus metas principales
🕊️ | Inner peace comes from within yourself | La paz interior viene de tu propio interior
"""
            30 -> """
🇬🇧 | Dedicate time to English study every day | Dedica tiempo al estudio del inglés todos los días
🎧 | Listening regularly improves comprehension | Escuchar con regularidad mejora la comprensión
📖 | Reading articles expands your active vocabulary | Leer artículos amplía tu vocabulario activo
🗣️ | Speak without fearing small grammatical mistakes | Habla sin miedo a pequeños errores gramaticales
✍️ | Writing sentences reinforces your grammar skills | Escribir oraciones refuerza tus habilidades gramaticales
🧠 | Try to think in English whenever possible | Intenta pensar en inglés siempre que sea posible
🎬 | Movies with subtitles help natural listening | Las películas con subtítulos ayudan a la escucha natural
🎯 | Consistent practice delivers great fluency | La práctica constante brinda gran fluidez
⚡ | You are making great progress every single day | Estás haciendo un gran progreso todos los días
🎉 | Excellent work completing this practice booklet! | ¡Excelente trabajo completando este cuaderno de práctica!
"""
            else -> ""
        }
    }
}
