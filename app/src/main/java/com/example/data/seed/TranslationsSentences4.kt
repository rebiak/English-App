package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences4 {
    const val FOLDER_NAME = "Translations Sentences 4"
    val categoryNames: List<String> = (1..30).map { "Translations 4 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations 4 - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
🗣️ | Have you ever spoken to a native speaker? | ¿Alguna vez has hablado con un hablante nativo?
✈️ | I have been abroad several times this year | He estado en el extranjero varias veces este año
⏰ | We haven't heard from him since last month | No sabemos nada de él desde el mes pasado
🏁 | She has already completed the quarterly report | Ya ha completado el informe trimestral
🚗 | Have they repaired your car yet? | ¿Ya te han arreglado el coche?
🌧️ | It has been raining all afternoon | Lleva lloviendo toda la tarde
🧾 | I have just realized what the problem is | Me acabo de dar cuenta de cuál es el problema
🔑 | Where have you put my keys? | ¿Dónde has puesto mis llaves?
🏢 | He has worked in this company since 2015 | Ha trabajado en esta empresa desde 2015
📚 | Have you ever read anything by this author? | ¿Alguna vez has leído algo de este autor?
"""
            2 -> """
❓ | If you had more time, what would you study? | Si tuvieras más tiempo, ¿qué estudiarías?
☀️ | We would go to the beach if it were sunny | Iríamos a la playa si hiciera sol
🏠 | If I won the lottery, I would buy a house | Si ganara la lotería, compraría una casa
🚗 | If he drove more carefully, he wouldn't crash | Si condujera con más cuidado, no chocaría
📞 | If I had your phone number, I would call you | Si tuviera tu número de teléfono, te llamaría
🌧️ | If it rains tomorrow, we'll cancel the trip | Si llueve mañana, cancelaremos el viaje
🤝 | I would help you if I could | Te ayudaría si pudiera
🏢 | If they offer you the job, will you accept? | Si te ofrecen el trabajo, ¿lo aceptarás?
😋 | If you're hungry, we can order something | Si tienes hambre, podemos pedir algo
☕ | Would you like some tea or coffee? | ¿Te gustaría té o café?
"""
            3 -> """
🥱 | He said that he was very tired | Dijo que estaba muy cansado
⏰ | She asked me what time the meeting started | Me preguntó a qué hora empezaba la reunión
✈️ | They told us that the flight had been delayed | Nos dijeron que el vuelo se había retrasado
🗣️ | He asked whether I knew the answer | Preguntó si yo sabía la respuesta
📅 | She promised that she would help us tomorrow | Prometió que nos ayudaría mañana
🧠 | I told him that I hadn't seen the document | Le dije que no había visto el documento
💬 | They claimed that they were innocent | Afirmaron que eran inocentes
🤝 | He agreed to sign the agreement | Aceptó firmar el acuerdo
⚠️ | She warned me not to touch the wire | Me advirtió que no tocara el cable
💡 | He suggested taking a short break | Sugirió tomar un breve descanso
"""
            4 -> """
🌉 | The bridge was constructed in 1995 | El puente fue construido en 1995
🌅 | The documents will be sent tomorrow morning | Los documentos serán enviados mañana por la mañana
🚗 | My car is being serviced today | Mi coche está siendo revisado hoy
↔️ | All packages are delivered within two days | Todos los paquetes se entregan en dos días
🚪 | The door was locked from the inside | La puerta estaba cerrada con llave por dentro
🏆 | First prize was won by our team | El primer premio fue ganado por nuestro equipo
🛏️ | The room is cleaned twice a week | La habitación se limpia dos veces por semana
🏥 | That hospital was built fifty years ago | Ese hospital fue construido hace cincuenta años
📧 | An email has been sent to all clients | Se ha enviado un correo a todos los clientes
⚠️ | You will be informed as soon as possible | Se le informará lo antes posible
"""
            5 -> """
⏰ | You ought to apologize for your behavior | Deberías disculparte por tu comportamiento
💡 | You should have told me earlier | Deberías habérmelo dicho antes
⚠️ | You must not smoke inside this facility | No debes fumar dentro de esta instalación
🪟 | Could you open the window, please? | ¿Podrías abrir la ventana, por favor?
🏢 | She might be at the doctor's office | Puede que ella esté en la consulta del médico
🌧️ | It may rain this afternoon | Puede que llueva esta tarde
🔑 | You don't have to pay now | No tienes que pagar ahora
📞 | He must have left his phone at home | Debe de haberse dejado el móvil en casa
🚫 | You can't be serious about that | No puedes estar hablando en serio sobre eso
🏋️ | We had better leave right now | Más vale que nos vayamos ahora mismo
"""
            6 -> """
🎒 | I look forward to seeing you again | Tengo muchas ganas de verte de nuevo
⏰ | Don't put off your duties | No pospongas tus obligaciones
💡 | He came up with an excellent idea | Se le ocurrió una idea excelente
👞 | Take off your shoes before entering | Quítate los zapatos antes de entrar
📞 | Hold on a minute, please | Espera un minuto, por favor
🥛 | We ran out of sugar and milk | Nos quedamos sin azúcar y leche
👛 | I'm looking for my wallet | Estoy buscando mi cartera
🌧️ | The event was called off due to rain | El evento fue cancelado debido a la lluvia
💻 | Turn off the computer when finished | Apaga el ordenador cuando termines
📈 | The economic situation is looking up | La situación económica está mejorando
"""
            7 -> """
🏢 | How long have you been with the company? | ¿Cuánto tiempo llevas en la empresa?
📈 | Our quarterly revenue increased by ten percent | Nuestros ingresos trimestrales aumentaron un diez por ciento
👥 | Let's schedule a meeting for Monday | Programemos una reunión para el lunes
📄 | Please check the attached spreadsheet | Por favor, compruebe la hoja de cálculo adjunta
🤝 | We reached a satisfactory deal | Llegamos a un acuerdo satisfactorio
💰 | The budget was approved unanimously | El presupuesto fue aprobado por unanimidad
📊 | Market trends indicate strong growth | Las tendencias del mercado indican un fuerte crecimiento
🎯 | Customer satisfaction is our top goal | La satisfacción del cliente es nuestra meta principal
✉️ | I look forward to your prompt response | Quedo a la espera de su pronta respuesta
👥 | We should delegate more responsibilities | Deberíamos delegar más responsabilidades
"""
            8 -> """
✈️ | What time does the plane board? | ¿A qué hora embarca el avión?
🧳 | Where can I find baggage claim? | ¿Dónde puedo encontrar la recogida de equipaje?
🏨 | I'd like to check in, please | Me gustaría hacer el registro, por favor
🛏️ | Is Wi-Fi complimentary in the rooms? | ¿El Wi-Fi es gratuito en las habitaciones?
🍽️ | Can you recommend a local restaurant? | ¿Puede recomendar un restaurante local?
🚆 | Which train goes to downtown? | ¿Qué tren va al centro?
🎟️ | Two tickets for the museum, please | Dos entradas para el museo, por favor
🚕 | Please call a cab for me | Por favor, pídame un taxi
💳 | Do you take credit cards? | ¿Aceptan tarjetas de crédito?
🦶 | How far is the city center on foot? | ¿A qué distancia está el centro a pie?
"""
            9 -> """
☕ | I start every morning with hot coffee | Empiezo cada mañana con café caliente
🏃 | He runs five kilometers every day | Corre cinco kilómetros todos los días
⚡ | What did you have for breakfast? | ¿Qué desayunaste?
📰 | My daily commute takes thirty minutes | Mi trayecto diario dura treinta minutos
🚌 | I love listening to podcasts on the bus | Me encanta escuchar podcasts en el autobús
🛒 | Let's go grocery shopping this evening | Vamos a hacer la compra esta tarde
🌙 | Who is making dinner tonight? | ¿Quién hace la cena esta noche?
🛏️ | I need to clean my bedroom today | Necesito limpiar mi habitación hoy
⌚ | I rarely watch the news on TV | Rara vez veo las noticias en la tele
🛏️ | I usually go to bed around eleven | Suelo acostarme alrededor de las once
"""
            10 -> """
🌡️ | What is the temperature right now? | ¿Qué temperatura hace ahora mismo?
☀️ | The sun is shining brightly today | El sol brilla intensamente hoy
🌧️ | It's starting to pour outside | Está empezando a diluviar fuera
👉 | Did you hear that loud thunder? | ¿Oíste ese fuerte trueno?
❄️ | There is snow on the mountain peaks | Hay nieve en las cumbres de las montañas
🧥 | You should wear a warm coat | Deberías ponerte un abrigo abrigado
🌸 | There is a beautiful rainbow in the sky | Hay un hermoso arcoíris en el cielo
🍂 | Autumn is my favorite time of year | El otoño es mi época favorita del año
🏖️ | The summer was very hot and dry | El verano fue muy caluroso y seco
🌸 | Spring brings colorful flowers everywhere | La primavera trae flores coloridas por todas partes
"""
            11 -> """
🧑‍⚕️ | I have a doctor's appointment at ten | Tengo cita con el médico a las diez
🤕 | I have had a headache all morning | He tenido dolor de cabeza toda la mañana
💊 | Take this medicine twice a day | Tome esta medicina dos veces al día
🧑‍⚕️ | The doctor checked my blood pressure | El médico me tomó la tensión arterial
🌡️ | I think I have a slight fever | Creo que tengo un poco de fiebre
🩹 | Put a bandage on that small cut | Pon una tirita en ese pequeño corte
🦷 | I need to see the dentist soon | Necesito ir al dentista pronto
🤧 | I have a bad cough and runny nose | Tengo una tos fuerte y goteo nasal
🥗 | Eating vegetables improves your health | Comer verduras mejora tu salud
📰 | Daily exercise is great for your heart | El ejercicio diario es genial para tu corazón
"""
            12 -> """
🛒 | Where can I find the dairy products? | ¿Dónde puedo encontrar los lácteos?
🤝 | I'm looking for a gift for a friend | Estoy buscando un regalo para un amigo
👗 | Do you have this in size large? | ¿Tienen esto en talla grande?
📍 | Where are the dressing rooms? | ¿Dónde están los probadores?
🧥 | How much does this coat cost? | ¿Cuánto cuesta este abrigo?
💵 | Can I pay with cash or card? | ¿Puedo pagar en efectivo o con tarjeta?
🧾 | Don't forget to take your receipt | No olvides llevarte el recibo
🏠 | Do you offer home delivery? | ¿Ofrecen entrega a domicilio?
⏰ | What time do you open tomorrow? | ¿A qué hora abren mañana?
🍞 | This bread is fresh and warm | Este pan está fresco y caliente
"""
            13 -> """
✉️ | Could we have the menu, please? | ¿Nos trae la carta, por favor?
🥗 | I will start with a green salad | Empezaré con una ensalada verde
🥩 | I would like my steak medium rare | Me gustaría el filete al punto
🍷 | A glass of red wine for me | Una copa de vino tinto para mí
🐟 | The grilled fish was very tasty | El pescado a la plancha estaba muy rico
🌾 | Does this contain any peanuts? | ¿Esto contiene cacahuetes?
🧾 | Two coffees and the check, please | Dos cafés y la cuenta, por favor
👨‍🍳 | The food was absolutely wonderful | La comida estuvo absolutamente maravillosa
🍝 | I recommend the homemade pasta | Recomiendo la pasta casera
🍲 | The soup is too hot to eat | La sopa está demasiado caliente para comer
"""
            14 -> """
🏫 | She graduated with top honors | Se graduó con las mejores calificaciones
📚 | I spent the whole day studying at the library | Pasé todo el día estudiando en la biblioteca
↔️ | The essay must be submitted by Monday | El ensayo debe entregarse para el lunes
🎓 | He plans to apply for university next year | Planea solicitar plaza en la universidad el año que viene
📖 | Make sure to take good notes | Asegúrate de tomar buenos apuntes
📅 | We did a chemistry experiment today | Hicimos un experimento de química hoy
🗣️ | Speaking practice is very important | La práctica oral es muy importante
🧠 | Review your lessons every evening | Repasa tus lecciones todas las tardes
🧑‍🏫 | The teacher explained everything clearly | La profesora explicó todo con claridad
📑 | I have three exams next week | Tengo tres exámenes la próxima semana
"""
            15 -> """
💻 | Did you restart your computer? | ¿Reiniciaste el ordenador?
📶 | The Wi-Fi connection keeps dropping | La conexión Wi-Fi se corta continuamente
🔒 | Change your password regularly | Cambia tu contraseña periódicamente
🦒 | Install the latest system update | Instala la última actualización del sistema
💾 | Don't forget to save your progress | No olvides guardar tu progreso
📧 | I received an unexpected message | Recibí un mensaje inesperado
🔋 | The battery lasts all day long | La batería dura todo el día
📞 | Pair the headphones with your phone | Empareja los auriculares con tu teléfono
☁️ | My photos are stored in the cloud | Mis fotos están guardadas en la nube
🖥️ | The screen resolution is excellent | La resolución de la pantalla es excelente
"""
            16 -> """
⚽ | The soccer match ended in a draw | El partido de fútbol terminó en empate
📅 | We played basketball yesterday evening | Jugamos al baloncesto ayer por la tarde
🎾 | She is a fantastic tennis player | Es una jugadora de tenis fantástica
🏊 | Swimming helps you stay in great shape | Nadar te ayuda a mantenerte en gran forma
🚲 | He rides his bike every Sunday | Monta en bicicleta todos los domingos
🌅 | She ran ten kilometers this morning | Corrió diez kilómetros esta mañana
🏆 | Our team won the regional championship | Nuestro equipo ganó el campeonato regional
📰 | He trains at the boxing gym daily | Entrena en el gimnasio de boxeo a diario
🚆 | Weightlifting builds upper body strength | Levantar pesas desarrolla la fuerza del tren superior
⛳ | They played eighteen holes of golf | Jugaron dieciocho hoyos de golf
"""
            17 -> """
🎵 | What kind of music do you prefer? | ¿Qué tipo de música prefieres?
🎸 | He plays electric guitar in a band | Toca la guitarra eléctrica en un grupo
🎹 | She can play the piano very well | Sabe tocar el piano muy bien
📅 | We visited an art gallery yesterday | Visitamos una galería de arte ayer
🎬 | The movie had great visual effects | La película tenía grandes efectos visuales
🎟️ | I have two tickets for the theater | Tengo dos entradas para el teatro
↔️ | The play was very entertaining | La obra de teatro fue muy entretenida
🎻 | The violin solo was emotional | El solo de violín fue emotivo
📚 | I love reading historical fiction novels | Me encanta leer novelas de ficción histórica
📸 | She takes amazing portrait photos | Hace fotos de retrato increíbles
"""
            18 -> """
🤝 | True friendship is extremely valuable | La verdadera amistad es sumamente valiosa
😊 | They have been happily married for years | Han estado felizmente casados durante años
👨‍👩‍👧‍👦 | We had dinner with the whole family | Cenamos con toda la familia
💬 | We need to have an honest conversation | Necesitamos tener una conversación sincera
📋 | Thank you for listening to me | Gracias por escucharme
👶 | Their new baby was born yesterday | Su nuevo bebé nació ayer
🎂 | We organized a surprise birthday party | Organizamos una fiesta de cumpleaños sorpresa
🧑‍🤝‍🧑 | He is one of my closest friends | Es uno de mis amigos más cercanos
🌸 | Thank you so much for the lovely present | Muchísimas gracias por el bonito regalo
💔 | They decided to go their separate ways | Decidieron seguir caminos separados
"""
            19 -> """
🌍 | We must take care of our environment | Debemos cuidar nuestro medio ambiente
♻️ | Remember to recycle plastic bottles | Recuerda reciclar las botellas de plástico
✨ | Trees produce oxygen and clean the air | Los árboles producen oxígeno y limpian el aire
🧍 | Solar panels generate green energy | Los paneles solares generan energía verde
💧 | Save water while brushing your teeth | Ahorra agua mientras te lavas los dientes
🚗 | Electric cars are becoming common | Los coches eléctricos se están volviendo comunes
🦁 | Wild animals belong in nature | Los animales salvajes pertenecen a la naturaleza
✨ | Clean beaches protect marine animals | Las playas limpias protegen a los animales marinos
🚲 | Cycling is eco-friendly and healthy | Andar en bicicleta es ecológico y saludable
🍃 | Protect our forests from fires | Protege nuestros bosques de los incendios
"""
            20 -> """
🤝 | I ran into an old friend yesterday | Me encontré con un viejo amigo ayer
🔍 | We need to figure out the solution | Necesitamos averiguar la solución
🚗 | His car broke down on the highway | Su coche se averió en la autovía
🚪 | Please don't let me down | Por favor no me defraudes
📞 | I will call you back later | Te llamaré de vuelta más tarde
📝 | Write down this address, please | Apunta esta dirección, por favor
📅 | Put on a warm sweater today | Ponte un suéter abrigado hoy
💡 | They turned down the proposal | Rechazaron la propuesta
⚡ | Time is running out quickly | El tiempo se está agotando rápidamente
✈️ | We saw them off at the airport | Los despedimos en el aeropuerto
"""
            21 -> """
🍞 | It's a piece of cake | Es pan comido
🐕 | It is raining cats and dogs | Está lloviendo a cántaros
👁️ | It costs an arm and a leg | Cuesta un ojo de la cara
👂 | I am all ears | Soy todo oídos
⏳ | Better late than never | Más vale tarde que nunca
🥊 | You hit the nail on the head | Has dado en el clavo
🥱 | Time to hit the sack | Hora de irse a dormir
🧊 | Let's break the ice | Vamos a romper el hielo
🤫 | Don't spill the beans | No te vayas de la lengua
⚡ | It happened out of the blue | Ocurrió de la nada
"""
            22 -> """
🧾 | I put money in my savings account | Puse dinero en mi cuenta de ahorros
💳 | Pay off your credit card every month | Paga tu tarjeta de crédito todos los meses
🏠 | They bought a house with a mortgage | Compraron una casa con hipoteca
📈 | Invest for the long term | Invierte a largo plazo
📉 | Prices are rising due to inflation | Los precios suben debido a la inflación
💵 | Always keep an emergency fund | Mantén siempre un fondo de emergencia
🏦 | Check your monthly bank statement | Revisa tu extracto bancario mensual
🏧 | I need to find an ATM machine | Necesito encontrar un cajero automático
💸 | Try to reduce unnecessary spending | Intenta reducir gastos innecesarios
📊 | The market had a strong week | El mercado tuvo una semana fuerte
"""
            23 -> """
⚖️ | Follow the rules of the law | Sigue las normas de la ley
🏛️ | The court made its final ruling | El tribunal dictó su sentencia final
👨‍⚖️ | The judge heard both sides | El juez escuchó a ambas partes
⚖️ | Consult a lawyer before signing | Consulta a un abogado antes de firmar
👮 | The police arrested the suspect | La policía arrestó al sospechoso
🔍 | They found important new evidence | Encontraron pruebas nuevas importantes
🗣️ | The witness spoke in court | El testigo habló en el tribunal
🛡️ | Everyone deserves a fair hearing | Todos merecen una audiencia justa
📜 | The constitution protects citizens | La constitución protege a los ciudadanos
💼 | Both parties signed the agreement | Ambas partes firmaron el acuerdo
"""
            24 -> """
🔬 | Scientists made a new discovery | Los científicos hicieron un nuevo descubrimiento
🚀 | The rocket launched into space | El cohete se lanzó al espacio
🧬 | DNA contains genetic information | El ADN contiene información genética
🔭 | Look at the stars with a telescope | Mira las estrellas con un telescopio
🧪 | Mix the chemicals carefully | Mezcla los productos químicos con cuidado
✨ | Clean energy is the future | La energía limpia es el futuro
🌱 | Plants grow towards the light | Las plantas crecen hacia la luz
🤖 | Robots can perform complex tasks | Los robots pueden realizar tareas complejas
🌊 | The oceans cover most of Earth | Los océanos cubren la mayor parte de la Tierra
🌙 | The volcano erupted last night | El volcán entró en erupción anoche
"""
            25 -> """
🏛️ | Rome has many ancient monuments | Roma tiene muchos monumentos antiguos
⚔️ | Knights lived in medieval times | Los caballeros vivían en la época medieval
🏰 | The castle was built on a high hill | El castillo fue construido sobre una colina alta
📜 | Ancient documents tell great stories | Los documentos antiguos cuentan grandes historias
🚢 | Sailors crossed vast oceans | Los marineros cruzaron vastos océanos
🏭 | Industry changed the modern world | La industria cambió el mundo moderno
🗽 | People fought for their freedom | La gente luchó por su libertad
📖 | Learn from historical events | Aprende de los acontecimientos históricos
🏛️ | Pyramids were built thousands of years ago | Las pirámides se construyeron hace miles de años
🗺️ | Old maps showed uncharted lands | Los mapas antiguos mostraban tierras inexploradas
"""
            26 -> """
🏠 | Country life is peaceful and quiet | La vida en el campo es pacífica y tranquila
⚡ | City life is fast and exciting | La vida en la ciudad es rápida y emocionante
🚗 | Traffic can be heavy in big cities | El tráfico puede ser pesado en las grandes ciudades
🌳 | Parks make cities much greener | Los parques hacen las ciudades mucho más verdes
🚲 | Many people commute by bike | Mucha gente se desplaza en bicicleta
🦒 | Tall skyscrapers fill the skyline | Rascacielos altos llenan el horizonte
🧗 | Farmers work hard in the fields | Los agricultores trabajan duro en el campo
🚇 | The subway is fast and convenient | El metro es rápido y práctico
🏡 | A big backyard is great for kids | Un patio trasero grande es genial para los niños
🛒 | Supermarkets are open late in cities | Los supermercados abren hasta tarde en las ciudades
"""
            27 -> """
✈️ | Travel opens up your mind | Viajar te abre la mente
📸 | Photography is a creative hobby | La fotografía es un pasatiempo creativo
🏠 | Cooking at home is fun and healthy | Cocinar en casa es divertido y saludable
🪴 | Taking care of plants is relaxing | Cuidar de las plantas es relajante
♟️ | Chess requires strategic thinking | El ajedrez requiere pensamiento estratégico
📖 | Reading books expands your horizons | Leer libros amplía tus horizontes
🎨 | Painting allows you to express yourself | Pintar te permite expresarte
🎣 | Fishing requires great patience | Pescar requiere gran paciencia
🥾 | Hiking in the mountains is energizing | Hacer senderismo en las montañas es energizante
🧩 | Puzzles keep your brain sharp | Los rompecabezas mantienen tu cerebro ágil
"""
            28 -> """
🗣️ | Communicate clearly with others | Comunícate con claridad con los demás
📋 | Listen carefully when people speak | Escucha con atención cuando hable la gente
📧 | Write professional and polite emails | Escribe correos profesionales y educados
🎯 | Practice your public speaking skills | Practica tus habilidades para hablar en público
💡 | Express your ideas with confidence | Expresa tus ideas con seguridad
👁️ | Make friendly eye contact | Mantén un contacto visual amable
📱 | Be mindful when texting | Ten cuidado al mandar mensajes de texto
🤫 | Sometimes silence is the best answer | A veces el silencio es la mejor respuesta
🤝 | Try to understand other perspectives | Intenta entender otras perspectivas
📢 | Speak clearly and loud enough | Habla con claridad y suficiente volumen
"""
            29 -> """
🧭 | Always follow your moral values | Sigue siempre tus valores morales
🤝 | Honesty and integrity matter most | La honestidad y la integridad son lo que más importa
💖 | Show kindness to everyone you meet | Muestra amabilidad a todos los que conozcas
⚖️ | Treat all people with fairness | Trata a todas las personas con justicia
🌱 | Work on your personal growth | Trabaja en tu crecimiento personal
🛡️ | Have the courage to do what is right | Ten la valentía de hacer lo correcto
⏳ | Be patient with your progress | Sé paciente con tu progreso
🌟 | Be grateful for what you have | Agradece lo que tienes
🎯 | Stay focused on your long-term goals | Mantente enfocado en tus metas a largo plazo
🕊️ | Find peace in everyday moments | Encuentra paz en los momentos cotidianos
"""
            30 -> """
🇬🇧 | Practice your English every single day | Practica tu inglés todos los días
🇬🇧 | Listen to English audio materials | Escucha materiales de audio en inglés
🇬🇧 | Read books and articles in English | Lee libros y artículos en inglés
🗣️ | Don't be afraid of making mistakes | No tengas miedo de cometer errores
✍️ | Write sentences to remember new words | Escribe oraciones para recordar palabras nuevas
🇬🇧 | Try thinking in English directly | Intenta pensar en inglés directamente
🇬🇧 | Watch movies with English subtitles | Mira películas con subtítulos en inglés
🎯 | Set achievable language learning goals | Fija metas de aprendizaje de idiomas alcanzables
🔑 | Consistency is the key to success | La constancia es la clave del éxito
📋 | Great job completing this practice list! | ¡Gran trabajo completando esta lista de práctica!
"""
            else -> ""
        }
    }
}
