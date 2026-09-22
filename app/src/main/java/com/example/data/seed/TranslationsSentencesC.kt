package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentencesC {
    const val FOLDER_NAME = "Translations Sentences C"
    val categoryNames: List<String> = (1..30).map { "Translations C - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        for (i in 1..30) {
            list.addAll(TranslationDataHelper.parseList("Translations C - List $i", getListData(i)))
        }
        return list
    }

    private fun getListData(index: Int): String {
        return when (index) {
            1 -> """
↔️ | What have you accomplished during this intensive training? | ¿Qué has logrado durante este entrenamiento intensivo?
💼 | We have completed all advanced modules with distinction | Hemos completado todos los módulos avanzados con distinción
💵 | Have you noticed how much your fluency has improved? | ¿Has notado cuánto ha mejorado tu fluidez?
☕ | Would you like to celebrate your achievement with coffee? | ¿Te gustaría celebrar tu logro con un café?
🚗 | We have reached the final destination of our course | Hemos llegado al destino final de nuestro curso
🧗 | We haven't encountered any insurmountable difficulty | No hemos encontrado ninguna dificultad insuperable
🌧️ | The storm has passed and the skies are clear | La tormenta ha pasado y los cielos están despejados
💡 | You have developed exceptional language intuition | Has desarrollado una intuición lingüística excepcional
🔑 | You hold the key to effortless bilingual communication | Tienes la clave para una comunicación bilingüe fluida
🇬🇧 | Your command of English has reached an advanced level | Tu dominio del inglés ha alcanzado un nivel avanzado
"""
            2 -> """
🇬🇧 | If you had to teach someone English, what advice would you give? | Si tuvieras que enseñar inglés a alguien, ¿qué consejo le darías?
🏖️ | If you keep practicing, your confidence will continue to grow | Si sigues practicando, tu seguridad seguirá creciendo
📋 | Should you need any refresher material, revisit these lists | Si necesitas repasar material, vuelve a consultar estas listas
🌧️ | Even if challenges arise, persistence guarantees success | Aunque surjan dificultades, la perseverancia garantiza el éxito
🏠 | I wouldn't trade this language journey for anything | No cambiaría este viaje con el idioma por nada
🚗 | If we had given up early, we wouldn't be here celebrating | Si nos hubiéramos rendido antes, no estaríamos aquí celebrando
💰 | The investment of your time has paid off tremendously | La inversión de tu tiempo ha valido la pena enormemente
❓ | If anyone asks how you learned so fast, share your secret | Si alguien te pregunta cómo aprendiste tan rápido, comparte tu secreto
🇬🇧 | Where will you travel first to practice your spoken English? | ¿A dónde viajarás primero para practicar tu inglés hablado?
📚 | Mastery is not a destination, but a lifelong habit | El dominio no es un destino, sino un hábito para toda la vida
"""
            3 -> """
🧑‍🏫 | The instructor announced that all students had passed | El profesor anunció que todos los estudiantes habían aprobado
❓ | She asked what topic was the most enjoyable to study | Preguntó qué tema había sido el más ameno de estudiar
🇬🇧 | They promised they would continue reading in English every day | Prometieron que seguirían leyendo en inglés todos los días
📌 | He inquired about additional advanced conversation clubs | Preguntó por clubes adicionales de conversación avanzada
📣 | She advised everyone to stay curious and ambitious | Aconsejó a todos mantenerse curiosos y ambiciosos
🧠 | I stated that consistent daily practice made all the difference | Declaré que la práctica diaria constante marcó toda la diferencia
💬 | They confirmed that their speaking confidence had skyrocketed | Confirmaron que su seguridad al hablar se había disparado
🤝 | We agreed to stay in touch and support each other | Acordamos mantenernos en contacto y apoyarnos mutuamente
💡 | He suggested creating a community of language learners | Sugirió crear una comunidad de estudiantes de idiomas
🔒 | She reminded us that true knowledge remains with you forever | Nos recordó que el verdadero conocimiento permanece contigo para siempre
"""
            4 -> """
🏗️ | A strong linguistic foundation has been built | Se ha construido una sólida base lingüística
📄 | Your certificate of completion will be issued shortly | Tu certificado de finalización se emitirá en breve
🚗 | Your progress is being celebrated by the whole team | Todo el equipo celebra tu progreso
📦 | All essential grammar structures have been mastered | Se han dominado todas las estructuras gramaticales esenciales
🔒 | Your new language skills are permanently unlocked | Tus nuevas habilidades lingüísticas están desbloqueadas permanentemente
🏆 | You have been awarded highest praise for your dedication | Has recibido el mayor elogio por tu dedicación
🧹 | Any remaining doubts have been cleared up thoroughly | Cualquier duda restante ha sido aclarada a fondo
🏢 | This milestone marks the start of global opportunities | Este hito marca el inicio de oportunidades globales
📧 | Official confirmation of your achievement has been sent | Se ha enviado la confirmación oficial de tu logro
⚠️ | Remember that regular practice keeps your skills sharp | Recuerda que la práctica regular mantiene tus habilidades afiladas
"""
            5 -> """
⏰ | You ought to feel immensely proud of your progress | Deberías sentirte inmensamente orgulloso de tu progreso
💡 | You should share your experience with other learners | Deberías compartir tu experiencia con otros estudiantes
⚠️ | You must never underestimate the power of consistency | Nunca debes subestimar el poder de la constancia
💵 | Could you believe how much you have learned? | ¿Podrías creer cuánto has aprendido?
🏥 | Language learning is great for your brain health | El aprendizaje de idiomas es genial para la salud del cerebro
🧗 | Even on difficult days, you kept moving forward | Incluso en los días difíciles, seguiste avanzando
🇬🇧 | You do not need to hesitate when speaking English now | Ya no necesitas dudar al hablar en inglés
🧗 | You must have worked very hard to reach this stage | Has debido trabajar muy duro para llegar a esta etapa
🚫 | Nothing can stop you when you have determination | Nada puede detenerte cuando tienes determinación
🏋️ | We had better celebrate this remarkable milestone | Más vale que celebremos este extraordinario hito
"""
            6 -> """
🎒 | We look forward to seeing your future accomplishments | Esperamos con ganas ver tus futuros logros
⏰ | Never put off your personal and professional dreams | Nunca pospongas tus sueños personales y profesionales
💡 | You came up with great discipline throughout this course | Mostraste una gran disciplina a lo largo de este curso
🧥 | Put on your confidence and speak without fear | Ponte tu confianza y habla sin miedo
🤝 | Keep in touch with international friends and colleagues | Mantén el contacto con amigos y colegas internacionales
🇬🇧 | You will never run out of opportunities with English | Nunca te quedarás sin oportunidades con el inglés
🔍 | Always look forward to learning new expressions | Ten siempre ganas de aprender nuevas expresiones
🏢 | Keep up the wonderful work you have started here | Mantén el maravilloso trabajo que has empezado aquí
🚪 | A new door has opened for your career and life | Se ha abierto una nueva puerta para tu carrera y tu vida
📈 | Your linguistic fluency will keep looking up | Tu fluidez lingüística seguirá mejorando
"""
            7 -> """
🏁 | You are now ready for any international business meeting | Ahora estás listo para cualquier reunión de negocios internacional
📈 | Your communication skills will drive your professional success | Tus habilidades de comunicación impulsarán tu éxito profesional
📁 | Lead global projects with clarity and confidence | Lidera proyectos globales con claridad y seguridad
📊 | Draft professional emails and reports with ease | Redacta correos e informes profesionales con facilidad
🤝 | Build strong partnerships across borders and cultures | Construye sólidas colaboraciones a través de fronteras y culturas
💰 | Your multilingual ability adds immense professional value | Tu habilidad multilingüe añade un inmenso valor profesional
📊 | Present complex ideas with elegance and impact | Presenta ideas complejas con elegancia e impacto
🎯 | Reach every professional target you set for yourself | Alcanza cada objetivo profesional que te propongas
✉️ | Express gratitude to all who supported your journey | Expresa gratitud a todos los que apoyaron tu camino
👥 | Inspire colleagues with your dedication and growth | Inspira a tus compañeros con tu dedicación y crecimiento
"""
            8 -> """
🏁 | You are fully prepared to travel anywhere in the world | Estás totalmente preparado para viajar a cualquier parte del mundo
👜 | Pack your bags and explore new cultures with confidence | Haz las maletas y explora nuevas culturas con seguridad
✈️ | Communicate effortlessly in hotels, restaurants and airports | Comunícate sin esfuerzo en hoteles, restaurantes y aeropuertos
🤝 | Make lifelong friends wherever your travels take you | Haz amigos para toda la vida allá donde te lleven tus viajes
🗺️ | Navigate foreign cities like a seasoned traveler | Muévete por ciudades extranjeras como un viajero experimentado
🚆 | Board trains, flights and ferries with complete peace of mind | Sube a trenes, vuelos y ferris con total tranquilidad
🎟️ | Experience world-class museums, theaters and festivals | Disfruta de museos, teatros y festivales de primer nivel
🇬🇧 | Ask for directions and recommendations in native English | Pide indicaciones y recomendaciones en un inglés natural
💳 | Handle international transactions with total ease | Gestiona transacciones internacionales con total facilidad
📍 | The whole world is now open and accessible to you | El mundo entero está ahora abierto y accesible para ti
"""
            9 -> """
🇬🇧 | Incorporate English naturally into your daily routine | Incorpora el inglés de forma natural en tu rutina diaria
📋 | Listen to podcasts while exercising or walking outside | Escucha podcasts mientras haces ejercicio o paseas fuera
🇬🇧 | Think in English while preparing your morning breakfast | Piensa en inglés mientras preparas tu desayuno matutino
📰 | Practice shadowing audio during your daily commute | Practica imitar audios durante tu trayecto diario
🎵 | Enjoy global music, audiobooks and storytelling | Disfruta de la música global, los audiolibros y la narración
🍳 | Browse international recipes and cook exciting dishes | Explora recetas internacionales y cocina platos interesantes
🇬🇧 | Share English conversations over dinner with friends | Comparte conversaciones en inglés durante la cena con amigos
🇬🇧 | Label household objects in English to expand vocabulary | Etiqueta objetos del hogar en inglés para ampliar vocabulario
🇬🇧 | Watch your favorite series in original English audio | Mira tus series favoritas con audio original en inglés
🇬🇧 | Reflect in English for five minutes before sleeping | Reflexiona en inglés durante cinco minutos antes de dormir
"""
            10 -> """
🌡️ | Whatever the weather, keep your passion for learning bright | Haga el tiempo que haga, mantén viva tu pasión por aprender
☀️ | Every sunny day brings fresh motivation to practice | Cada día soleado trae una motivación renovada para practicar
🇬🇧 | Rainy afternoons are perfect for reading English novels | Las tardes lluviosas son perfectas para leer novelas en inglés
🗣️ | Let your enthusiasm ignite new language breakthroughs | Deja que tu entusiasmo encienda nuevos avances lingüísticos
🇬🇧 | Winter evenings are ideal for cozy English study sessions | Las tardes de invierno son ideales para acogedoras sesiones de estudio
🧥 | Embrace every opportunity to speak without hesitation | Aprovecha cada oportunidad para hablar sin vacilar
🌈 | Language learning adds vibrant colors to your life | El aprendizaje de idiomas añade colores vibrantes a tu vida
🍂 | Like changing seasons, your fluency evolves naturally | Como las estaciones, tu fluidez evoluciona de forma natural
🏖️ | Enjoy the journey as much as the final destination | Disfruta del camino tanto como del destino final
💪 | Your hard work has blossomed into beautiful fluency | Tu esfuerzo ha florecido en una hermosa fluidez
"""
            11 -> """
🏥 | A healthy mind and positive attitude accelerate learning | Una mente sana y una actitud positiva aceleran el aprendizaje
🤕 | Don't worry about occasional mistakes; they mean progress | No te preocupes por errores ocasionales; significan progreso
💊 | Daily practice is the best medicine for language growth | La práctica diaria es la mejor medicina para el crecimiento del idioma
🩺 | Assess your progress regularly and celebrate milestones | Evalúa tu progreso periódicamente y celebra los hitos
🌡️ | Maintain high enthusiasm throughout your learning journey | Mantén un gran entusiasmo a lo largo de tu viaje de aprendizaje
🩹 | Every challenge overcome makes you stronger and wiser | Cada desafío superado te hace más fuerte y más sabio
🦷 | Good study habits protect your long-term memory | Los buenos hábitos de estudio protegen tu memoria a largo plazo
🧗 | Stay resilient when learning difficult concepts | Mantén la resiliencia al aprender conceptos difíciles
🇬🇧 | Feed your mind with rich, authentic English content | Alimenta tu mente con contenido en inglés rico y auténtico
🏃 | Keep up the momentum and never stop moving forward | Mantén el impulso y nunca dejes de avanzar
"""
            12 -> """
🛒 | You can shop and negotiate anywhere in the world now | Ya puedes comprar y negociar en cualquier parte del mundo
📋 | Treat yourself to a reward for completing these lists | Date una recompensa por completar estas listas
👗 | Dress your thoughts in rich and expressive vocabulary | Viste tus pensamientos con un vocabulario rico y expresivo
👖 | Tailor your communication style to every audience | Adapta tu estilo de comunicación a cada público
🏷️ | True knowledge is priceless and lasts a lifetime | El verdadero conocimiento no tiene precio y dura toda la vida
💳 | Invest in yourself; it pays the greatest dividends | Invierte en ti mismo; rinde los mayores beneficios
🧾 | Keep a record of all the words you have mastered | Lleva un registro de todas las palabras que has dominado
📦 | You have accumulated a rich treasure of sentences | Has acumulado un rico tesoro de oraciones
🏪 | Step into any international venue with complete poise | Entra en cualquier recinto internacional con total aplomo
🥖 | Savor the flavor of expressing yourself authentically | Saborea el placer de expresarte con autenticidad
"""
            13 -> """
🍽️ | Celebrate your success with a wonderful gourmet meal | Celebra tu éxito con una maravillosa comida gourmet
🇬🇧 | Enjoy the rich variety of English idioms and flavors | Disfruta de la rica variedad de modismos y matices del inglés
🇬🇧 | You have conquered the meat of advanced English grammar | Has conquistado lo sustancial de la gramática inglesa avanzada
🍷 | Raise a toast to your dedication and perseverance | Brinda por tu dedicación y perseverancia
🇬🇧 | Dive deep into the vast ocean of English literature | Sumérgete en el vasto océano de la literatura en inglés
🌾 | Cultivate the seeds of knowledge you have planted here | Cultiva las semillas de conocimiento que has plantado aquí
🤝 | Savor every conversation with international friends | Saborea cada conversación con amigos internacionales
🏁 | You have cooked up a recipe for lifelong fluency | Has preparado una receta para una fluidez de por vida
↔️ | Weave complex sentences together with total ease | Entrelaza oraciones complejas con total facilidad
🥣 | Warm your heart with the joy of learning something new | Llena tu corazón con la alegría de aprender algo nuevo
"""
            14 -> """
🏫 | You have graduated from this comprehensive sentence series | Te has graduado de esta serie completa de oraciones
🇬🇧 | Your personal library of English knowledge is vast | Tu biblioteca personal de conocimiento en inglés es enorme
📝 | Write essays, stories and articles with confidence | Escribe ensayos, historias y artículos con seguridad
🎓 | Wear your bilingual achievement like a badge of honor | Lleva tu logro bilingüe como una insignia de honor
📖 | Read complex texts with total comprehension and speed | Lee textos complejos con total comprensión y rapidez
🇬🇧 | Continue exploring the subtleties of the English language | Sigue explorando las sutilezas de la lengua inglesa
👥 | Speak eloquently in debates, meetings and presentations | Habla con elocuencia en debates, reuniones y presentaciones
🇬🇧 | Your brain has adapted to thinking naturally in English | Tu cerebro se ha adaptado a pensar con naturalidad en inglés
👩‍🏫 | Share your wisdom and encourage other fellow learners | Comparte tu sabiduría y anima a otros compañeros de estudio
📓 | Every page of your study notebook tells a success story | Cada página de tu cuaderno de estudio cuenta una historia de éxito
"""
            15 -> """
🇬🇧 | Navigate the digital world in English without friction | Navega por el mundo digital en inglés sin problemas
🇬🇧 | Connect with millions of English speakers worldwide | Conéctate con millones de angloparlantes en todo el mundo
🔒 | Your language proficiency is a secure lifelong asset | Tu dominio del idioma es un activo seguro para toda la vida
🇬🇧 | Change your digital life into an English immersion hub | Convierte tu vida digital en un centro de inmersión en inglés
⚡ | Save your favorite quotes and idioms for quick review | Guarda tus citas y modismos favoritos para un repaso rápido
🇬🇧 | Communicate with global partners in seamless English | Comunícate con socios globales en un inglés perfecto
🔋 | Recharge your motivation whenever you discover new words | Recarga tu motivación cada vez que descubras palabras nuevas
🎧 | Immerse yourself in podcasts, talks and audiobooks | Sumérgete en podcasts, charlas y audiolibros
⌚ | Store your learnings and watch your confidence soar | Guarda tus aprendizajes y mira cómo despega tu seguridad
🖥️ | The digital frontier is entirely accessible to you | La frontera digital es enteramente accesible para ti
"""
            16 -> """
↔️ | You have scored the winning goal in your language training | Has marcado el gol de la victoria en tu entrenamiento de idioma
🇬🇧 | Your English skills are fast, agile and accurate | Tus habilidades en inglés son rápidas, ágiles y precisas
🎾 | Return any conversation with natural confidence | Devuelve cualquier conversación con seguridad natural
🇬🇧 | Swim effortlessly through complex English discussions | Nada sin esfuerzo a través de complejas conversaciones en inglés
🚴 | Pedal forward on your journey to total bilingual mastery | Pedalea hacia adelante en tu camino al dominio bilingüe total
🏃 | You have crossed the finish line of this major series | Has cruzado la línea de meta de esta gran serie
🙈 | Lift your trophy of knowledge with great pride | Levanta tu trofeo de conocimiento con gran orgullo
🥊 | Knock down any doubt with daily disciplined practice | Derriba cualquier duda con práctica diaria disciplinada
🏋️ | Your mental muscles have grown stronger than ever | Tus músculos mentales se han vuelto más fuertes que nunca
🦒 | Aim high and hit every language milestone you set | Apunta alto y alcanza cada hito del idioma que te propongas
"""
            17 -> """
🇬🇧 | Let your English speech flow like beautiful music | Deja que tu habla en inglés fluya como hermosa música
🎸 | Strum the chords of fluent and natural conversation | Toca los acordes de una conversación fluida y natural
🎹 | Play with the rhythm and melody of native expressions | Juega con el ritmo y la melodía de las expresiones nativas
🎨 | Paint your ideas with vibrant and precise words | Pinta tus ideas con palabras vibrantes y precisas
🎬 | Star in your own international life story | Protagoniza tu propia historia de vida internacional
🎟️ | Gain access to the world's greatest cultural treasures | Accede a los mayores tesoros culturales del mundo
🇬🇧 | Express every emotion authentically in English | Expresa cada emoción con autenticidad en inglés
🎻 | Harmonize grammar and vocabulary into seamless speech | Armoniza la gramática y el vocabulario en un habla fluida
📚 | Immerse yourself in the timeless beauty of world literature | Sumérgete en la belleza atemporal de la literatura universal
📸 | Capture memorable moments in conversations across the globe | Captura momentos memorables en conversaciones por todo el mundo
"""
            18 -> """
🇬🇧 | English connects you with friends in every corner of Earth | El inglés te conecta con amigos en cada rincón de la Tierra
❤️ | Share your heart and thoughts across cultural boundaries | Comparte tu corazón y pensamientos a través de fronteras culturales
👨‍👩‍👧‍👦 | Inspire your family with your dedication and growth | Inspira a tu familia con tu dedicación y crecimiento
🌉 | Build bridges of understanding through clear dialogue | Construye puentes de entendimiento mediante un diálogo claro
📋 | Listen with empathy to stories from diverse cultures | Escucha con empatía historias de diversas culturas
👶 | Pass on the gift of multilingualism to future generations | Transmite el regalo del multilingüismo a las generaciones futuras
🇬🇧 | Celebrate every friendship made through English | Celebra cada amistad hecha a través del inglés
🧑‍🤝‍🧑 | Stand united with global peers and collaborators | Manténte unido con compañeros y colaboradores globales
🎁 | Knowledge is the greatest gift you can ever receive | El conocimiento es el mayor regalo que puedes recibir
💔 | Overcome any barrier with kindness and clear speech | Supera cualquier barrera con amabilidad y habla clara
"""
            19 -> """
🌍 | You are now a true citizen of the global community | Ya eres un auténtico ciudadano de la comunidad global
♻️ | Renew your commitment to learning something every day | Renueva tu compromiso de aprender algo todos los días
🌳 | Plant the seeds of curiosity wherever you go | Planta las semillas de la curiosidad allá donde vayas
⚡ | Power your dreams with fluent global communication | Impulsa tus sueños con una comunicación global fluida
💧 | Let your knowledge flow smoothly like clean water | Deja que tu conocimiento fluya con suavidad como agua limpia
🚗 | Drive your future towards exciting global horizons | Conduce tu futuro hacia emocionantes horizontes globales
🇬🇧 | Be brave and speak English with pride and dignity | Sé valiente y habla en inglés con orgullo y dignidad
🌊 | Navigate the vast ocean of human knowledge freely | Navega libremente por el vasto océano del conocimiento humano
🚲 | Keep moving forward with steady and joyful momentum | Sigue avanzando con un impulso constante y alegre
🍃 | Breathe in the satisfaction of completing ten full booklets | Respira la satisfacción de completar diez cuadernos enteros
"""
            20 -> """
💼 | You have overcome every grammatical challenge in this course | Has superado cada desafío gramatical en este curso
🔍 | Keep discovering new vocabulary in everything you read | Sigue descubriendo nuevo vocabulario en todo lo que leas
🚗 | Nothing can stop your linguistic momentum now | Nada puede detener tu impulso lingüístico ahora
🚪 | Walk through every open door with total confidence | Cruza cada puerta abierta con total seguridad
📞 | Pick up the phone and speak to native speakers easily | Coge el teléfono y habla con nativos con facilidad
📝 | Write your own success story with the words you learned | Escribe tu propia historia de éxito con las palabras que aprendiste
🧥 | Wear your language skills with confidence every single day | Luce tus habilidades lingüísticas con seguridad todos los días
💡 | Turn your language fluency into creative solutions | Convierte tu fluidez con el idioma en soluciones creativas
🇬🇧 | Make English a joyful part of every passing hour | Haz del inglés una parte alegre de cada hora que pase
🦒 | Fly high and reach the pinnacle of your aspirations | Vuela alto y alcanza la cima de tus aspiraciones
"""
            21 -> """
🇬🇧 | Translating English sentences has become a piece of cake | Traducir oraciones en inglés se ha convertido en pan comido
🇬🇧 | Rain or shine, your English skills shine bright | Llueva o truene, tus habilidades en inglés brillan con fuerza
💰 | The value of your bilingual mind is truly priceless | El valor de tu mente bilingüe no tiene precio
🇬🇧 | Keep your ears open to natural English conversations | Mantén los oídos atentos a conversaciones naturales en inglés
⏳ | Better late than never to embark on lifelong mastery | Más vale tarde que nunca para emprender un dominio de por vida
📋 | You have hit the nail on the head across three hundred lists | Has dado en el clavo a lo largo de trescientas listas
🌙 | Rest well tonight knowing you have accomplished something great | Descansa bien esta noche sabiendo que has logrado algo grande
👥 | You can break the ice in any international gathering | Puedes romper el hielo en cualquier reunión internacional
🤫 | The secret to fluency is simple: dedication and practice | El secreto de la fluidez es sencillo: dedicación y práctica
⚡ | Breakthroughs happen when you least expect them | Los grandes avances ocurren cuando menos te lo esperas
"""
            22 -> """
🇬🇧 | Your English skills are an asset that continuously appreciates | Tus habilidades en inglés son un activo que se revaloriza continuamente
💳 | Spend time on learning; it always yields compound growth | Dedica tiempo a aprender; siempre rinde un crecimiento compuesto
🏠 | Build a solid foundation of daily language habits | Construye una base sólida de hábitos lingüísticos diarios
📈 | Your progress chart shows continuous upward trajectory | Tu gráfica de progreso muestra una trayectoria ascendente continua
📉 | Eliminate doubt and replace it with unwavering confidence | Elimina la duda y sustitúyela por una seguridad inquebrantable
💵 | The wealth of knowledge is the true measure of success | La riqueza del conocimiento es la verdadera medida del éxito
🧾 | Review your achievements and feel proud of the journey | Revisa tus logros y siéntete orgulloso del camino
🏧 | Draw upon your knowledge whenever the occasion calls | Recurre a tu conocimiento siempre que la ocasión lo requiera
💸 | Invest your energy in mastering communication | Invierte tu energía en dominar la comunicación
📊 | All metrics show that you are now an advanced speaker | Todas las métricas muestran que ya eres un hablante avanzado
"""
            23 -> """
🇬🇧 | You have mastered the rules of English syntax | Has dominado las reglas de la sintaxis inglesa
🏛️ | Build a monumental vocabulary that stands the test of time | Construye un vocabulario monumental que resista el paso del tiempo
💵 | Judge your progress by how much you have grown | Juzga tu progreso por cuánto has crecido
📄 | Every sentence you practice is a signed promise of mastery | Cada oración que practicas es una promesa firmada de dominio
🧗 | Your hard-earned knowledge is locked securely in memory | Tu conocimiento bien ganado está guardado con seguridad en la memoria
🔍 | Keep exploring the fascinating nuances of the language | Sigue explorando los fascinantes matices de la lengua
🗣️ | Speak your truth with eloquence and conviction | Di tu verdad con elocuencia y convicción
🛡️ | Let your knowledge protect you in any dialogue | Deja que tu conocimiento te respalde en cualquier diálogo
📜 | Your dedication is written into every completed exercise | Tu dedicación está escrita en cada ejercicio completado
🧂 | Step into the world as a confident, articulate speaker | Sal al mundo como un hablante seguro y elocuente
"""
            24 -> """
🗣️ | The science of language acquisition proves that practice works | La ciencia de la adquisición de idiomas demuestra que la práctica funciona
🚀 | Launch your ambitions into the international arena | Lanza tus ambiciones a la arena internacional
🇬🇧 | Make English a natural part of your cognitive DNA | Haz del inglés una parte natural de tu ADN cognitivo
🔭 | Look ahead to a future full of global opportunities | Mira hacia un futuro lleno de oportunidades globales
🧪 | Mix curiosity and discipline to create lasting fluency | Mezcla curiosidad y disciplina para crear fluidez duradera
💪 | Channel your energy into effortless daily practice | Canaliza tu energía hacia una práctica diaria fluida
⌚ | Watch your language abilities branch out in new directions | Mira cómo tus habilidades lingüísticas se ramifican en nuevas direcciones
🤖 | Human communication connects hearts in ways machines cannot | La comunicación humana conecta corazones como las máquinas no pueden
🌊 | Sail smoothly across the ocean of global languages | Navega con fluidez por el océano de las lenguas globales
🌋 | Let your passion for learning erupt with enthusiasm | Deja que tu pasión por aprender estalle con entusiasmo
"""
            25 -> """
🏛️ | You have joined the timeless tradition of scholars | Te has unido a la tradición atemporal de los eruditos
⚔️ | Conquer any language challenge with determination | Conquista cualquier desafío lingüístico con determinación
🏰 | Build your castle of knowledge stone by stone | Construye tu castillo de conocimiento piedra a piedra
📜 | Read historical masterpieces in their original tongue | Lee obras maestras históricas en su lengua original
🚢 | Navigate international waters with total confidence | Navega por aguas internacionales con total seguridad
🏭 | Forge your communication skills through daily practice | Forja tus habilidades de comunicación mediante la práctica diaria
🗽 | Experience the true freedom of bilingual expression | Experimenta la verdadera libertad de la expresión bilingüe
📖 | Write your own glorious chapter in language learning | Escribe tu propio capítulo glorioso en el aprendizaje de idiomas
🇬🇧 | Stand tall as a master of English sentence translation | Mantente firme como un maestro de la traducción de oraciones en inglés
🗺️ | Map out your future with boundless global horizons | Diseña tu futuro con horizontes globales ilimitados
"""
            26 -> """
🇬🇧 | Make English feel as comfortable as your own home | Haz que el inglés se sienta tan cómodo como tu propia casa
🌆 | Shine brightly in any metropolitan setting on Earth | Brilla con luz propia en cualquier entorno metropolitano de la Tierra
🚗 | Drive your conversations with smoothness and poise | Conduce tus conversaciones con fluidez y aplomo
🌳 | Let your vocabulary grow like a majestic ancient tree | Deja que tu vocabulario crezca como un majestuoso árbol centenario
🚲 | Pedal smoothly through every conversational topic | Pedalea con fluidez por cualquier tema de conversación
🪵 | Reach the top floor of linguistic achievement | Llega a la planta más alta del logro lingüístico
🧗 | Harvest the wonderful fruits of your hard labor | Cosecha los maravillosos frutos de tu arduo trabajo
💪 | Connect effortlessly with people from all walks of life | Conéctate sin esfuerzo con personas de todos los ámbitos
🏡 | Find comfort and joy in daily language exploration | Encuentra comodidad y alegría en la exploración diaria del idioma
🛒 | Choose the finest words to express your deepest thoughts | Elige las mejores palabras para expresar tus pensamientos más profundos
"""
            27 -> """
✈️ | Your language journey has been a magnificent adventure | Tu viaje con el idioma ha sido una aventura magnífica
📸 | Remember every milestone you achieved along the way | Recuerda cada hito que lograste a lo largo del camino
🍳 | Season your speech with wit, humor and natural idioms | Sazona tu habla con ingenio, humor y modismos naturales
🪴 | Nurture your mind with continuous lifelong learning | Nutre tu mente con un aprendizaje continuo para toda la vida
♟️ | Play the long game of personal growth and mastery | Juega la partida a largo plazo del crecimiento y dominio personal
📖 | Treasure the books and sentences that inspired you | Atesora los libros y oraciones que te inspiraron
🎨 | Express yourself as a true artist of human language | Exprésate como un verdadero artista de la lengua humana
🎣 | Cast your net wide and catch every learning moment | Lanza tu red bien lejos y aprovecha cada momento de aprendizaje
🦒 | Climb to the summit of your highest linguistic dreams | Sube a la cima de tus sueños lingüísticos más altos
🇬🇧 | Complete the grand puzzle of complete English fluency | Completa el gran rompecabezas de la fluidez total en inglés
"""
            28 -> """
📋 | Speak clearly, listen deeply, and communicate with love | Habla con claridad, escucha profundamente y comunícate con amor
🤝 | Inspire everyone you meet with your articulate voice | Inspira a todos los que conozcas con tu voz elocuente
🌸 | Craft beautiful words that touch hearts and minds | Elabora hermosas palabras que toquen corazones y mentes
🎯 | Hit the mark of excellence in every conversation | Da en la diana de la excelencia en cada conversación
💡 | Illuminate the world with your unique perspective | Ilumina el mundo con tu perspectiva única
👀 | Look forward to the endless adventures that await you | Mira hacia adelante a las infinitas aventuras que te esperan
📱 | Connect with the global community with authentic joy | Conéctate con la comunidad global con auténtica alegría
🤫 | Speak with calm confidence and inner tranquility | Habla con serena seguridad y tranquilidad interior
🤝 | Unite people across languages and backgrounds | Une a las personas más allá de idiomas y orígenes
📢 | Proclaim your success with pride and humility | Proclama tu éxito con orgullo y humildad
"""
            29 -> """
🧭 | Let your integrity and passion guide your future path | Deja que tu integridad y pasión guíen tu camino futuro
💪 | Honor the journey and the effort you invested here | Honra el camino y el esfuerzo que invertiste aquí
💖 | Share your knowledge generously with those around you | Comparte tu conocimiento generosamente con quienes te rodean
⚖️ | Treat every human being with dignity and respect | Trata a todo ser humano con dignidad y respeto
🦒 | Never stop growing, learning and reaching higher | Nunca dejes de crecer, aprender y llegar más alto
🛡️ | Stand firm in your belief in yourself and your abilities | Manténte firme en la fe en ti mismo y en tus capacidades
⏳ | Every second spent learning enriches your entire life | Cada segundo dedicado a aprender enriquece toda tu vida
🌟 | Shine like a beacon of dedication and achievement | Brilla como un faro de dedicación y logro
🪵 | Keep your eyes on the stars and your feet on the ground | Mantén tus ojos en las estrellas y tus pies en la tierra
🕊️ | Live in peace, harmony and boundless joy | Vive en paz, armonía y alegría ilimitada
"""
            30 -> """
🇬🇧 | You have completed all 10 Translations booklets! | ¡Has completado los 10 cuadernos de Traducciones!
📋 | Three hundred comprehensive lists successfully conquered | Trescientos listados completos conquistados con éxito
🇬🇧 | You have achieved true mastery of English sentence translation | Has alcanzado un verdadero dominio de la traducción de oraciones en inglés
📋 | Speak, write, listen and read with absolute freedom | Habla, escribe, escucha y lee con absoluta libertad
🚀 | Step forward into the world as a confident bilingual speaker | Da un paso adelante en el mundo como un hablante bilingüe y seguro
💡 | Your dedication is an inspiration to language learners everywhere | Tu dedicación es una inspiración para estudiantes de idiomas de todo el mundo
📚 | The journey of learning never ends, but you have reached the peak | El viaje del aprendizaje nunca termina, pero has alcanzado la cima
🇬🇧 | The entire English-speaking world welcomes you with open arms | Todo el mundo angloparlante te da la bienvenida con los brazos abiertos
👈 | Celebrate this monumental milestone with immense pride | Celebra este hito monumental con inmenso orgullo
🎊 | Congratulations on your extraordinary accomplishment! | ¡Enhorabuena por tu extraordinario logro!
"""
            else -> ""
        }
    }
}
