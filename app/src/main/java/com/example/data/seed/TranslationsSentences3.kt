package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences3 {
    const val FOLDER_NAME = "Translations Sentences 3"
    val categoryNames: List<String> = (1..30).map { "Translations 3 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 1", list1))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 2", list2))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 3", list3))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 4", list4))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 5", list5))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 6", list6))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 7", list7))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 8", list8))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 9", list9))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 10", list10))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 11", list11))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 12", list12))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 13", list13))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 14", list14))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 15", list15))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 16", list16))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 17", list17))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 18", list18))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 19", list19))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 20", list20))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 21", list21))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 22", list22))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 23", list23))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 24", list24))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 25", list25))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 26", list26))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 27", list27))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 28", list28))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 29", list29))
        list.addAll(TranslationDataHelper.parseList("Translations 3 - List 30", list30))
        return list
    }

    private const val list1 = """
📍 | I have been working here for three years | Llevo tres años trabajando aquí
⏳ | How long have you lived in this city? | ¿Cuánto tiempo llevas viviendo en esta ciudad?
🏠 | We've been waiting for over an hour | Llevamos más de una hora esperando
🌧️ | It has been raining since this morning | Lleva lloviendo desde esta mañana
📊 | Have you read this report yet? | ¿Has leído ya este informe?
⏳ | I haven't finished it yet | Todavía no lo he terminado
🏁 | I've already sent the email | Ya he enviado el correo electrónico
🔍 | Have you ever seen anything like this? | ¿Alguna vez has visto algo parecido a esto?
🇬🇧 | I have never been to London | Nunca he estado en Londres
🤝 | We have known each other for a long time | Nos conocemos desde hace mucho tiempo
🚗 | He has bought a brand-new car | Se ha comprado un coche nuevo
🔑 | I have lost my house keys | He perdido las llaves de mi casa
🔍 | Have you found what you were looking for? | ¿Has encontrado lo que estabas buscando?
🚪 | Somebody has left the back door open | Alguien ha dejado abierta la puerta trasera
☕ | Would you like another cup of coffee? | ¿Te gustaría otra taza de café?
💭 | I have changed my mind | He cambiado de opinión
📞 | Has anyone called while I was out? | ¿Ha llamado alguien mientras estaba fuera?
📈 | Sales have increased this quarter | Las ventas han aumentado este trimestre
🧾 | They haven't paid the bill yet | Todavía no han pagado la factura
🕒 | It's been a very productive day | Ha sido un día muy productivo
"""

    private const val list2 = """
❓ | What would you do in my situation? | ¿Qué harías en mi situación?
🏖️ | If I had more time, I would travel more | Si tuviera más tiempo, viajaría más
💼 | If you need any assistance, let me know | Si necesitas ayuda, házmelo saber
🏠 | If it rains, we will stay at home | Si llueve, nos quedaremos en casa
🏠 | I wouldn't do that if I were you | Yo no haría eso si fuera tú
🚗 | If he had a car, he would drive to work | Si tuviera coche, iría al trabajo conduciendo
💵 | If they had enough money, they would buy the house | Si tuvieran suficiente dinero, comprarían la casa
📞 | If she calls, tell her I'll be back soon | Si llama ella, dile que volveré pronto
✈️ | Where would you go if you could go anywhere? | ¿A dónde irías si pudieras ir a cualquier parte?
🧗 | If you study hard, you will pass the exam | Si estudias mucho, aprobarás el examen
⏱️ | As soon as I get the confirmation, I'll let you know | En cuanto reciba la confirmación, te avisaré
🚆 | Unless you hurry, you will miss the train | A menos que te des prisa, perderás el tren
📅 | Provided that you agree, we can sign today | Siempre que estés de acuerdo, podemos firmar hoy
🛡️ | In case of emergency, press this button | En caso de emergencia, pulsa este botón
🤔 | Suppose we don't finish in time, what then? | Supongamos que no terminamos a tiempo, ¿qué pasará?
🏢 | If the company expands, they will hire more staff | Si la empresa se expande, contratarán a más personal
🥱 | If you're tired, why don't you take a break? | Si estás cansado, ¿por qué no te tomas un descanso?
🗣️ | If you had spoken to him, he would have understood | Si hubieras hablado con él, lo habría entendido
🏆 | If we had played better, we would have won | Si hubiéramos jugado mejor, habríamos ganado
📝 | If I had known, I would have warned you | Si lo hubiera sabido, te habría advertido
"""

    private const val list3 = """
📅 | He told me that he would come tomorrow | Me dijo que vendría mañana
📍 | She asked me where I lived | Ella me preguntó dónde vivía
🏁 | They said they had already finished the project | Dijeron que ya habían terminado el proyecto
🏁 | He asked whether we were ready | Preguntó si estábamos listos
📣 | She told us not to worry about the delay | Nos dijo que no nos preocupáramos por el retraso
🤔 | I wondered why he hadn't answered my message | Me preguntaba por qué no había contestado mi mensaje
🚆 | He explained that the train had been delayed | Explicó que el tren se había retrasado
🤝 | She agreed to help us with the presentation | Ella aceptó ayudarnos con la presentación
📌 | He admitted that he had made a mistake | Reconoció que había cometido un error
⚠️ | They warned us about the traffic jam | Nos advirtieron del atasco de tráfico
🍽️ | She suggested going to a different restaurant | Sugirió ir a un restaurante diferente
📝 | He promised that he wouldn't tell anyone | Prometió que no se lo diría a nadie
🔒 | She reminded me to lock the front gate | Me recordó que cerrara el portón delantero
🗣️ | They claimed that they knew nothing about it | Afirmaron que no sabían nada al respecto
❓ | I asked him how long he had been waiting | Le pregunté cuánto tiempo llevaba esperando
📅 | She mentioned that she had seen you yesterday | Mencionó que te había visto ayer
⚖️ | He refused to sign the contract without his lawyer | Se negó a firmar el contrato sin su abogado
🏢 | They announced that the store would close early | Anunciaron que la tienda cerraría temprano
📌 | She pointed out several errors in the document | Señaló varios errores en el documento
📋 | He told me to listen carefully to the instructions | Me dijo que escuchara atentamente las instrucciones
"""

    private const val list4 = """
🌉 | The new bridge was built in six months | El nuevo puente se construyó en seis meses
📊 | The report will be published next week | El informe será publicado la próxima semana
🚗 | My car is being repaired at the moment | Mi coche está siendo reparado en este momento
📦 | All orders are processed within 24 hours | Todos los pedidos se procesan en un plazo de 24 horas
🚪 | The doors were locked at eight o'clock | Las puertas se cerraron a las ocho en punto
🏆 | The prize was awarded to the youngest participant | El premio fue otorgado al participante más joven
🏢 | The office is cleaned every evening | La oficina se limpia todas las tardes
🏢 | That building was designed by a famous architect | Ese edificio fue diseñado por un arquitecto famoso
📧 | An invitation has been sent to all members | Se ha enviado una invitación a todos los miembros
⚠️ | You will be notified when the decision is made | Se le notificará cuando se tome la decisión
📅 | The missing documents were discovered yesterday | Los documentos perdidos se descubrieron ayer
💻 | The software must be updated regularly | El software debe actualizarse regularmente
📰 | Fast food is consumed by millions daily | Millones de personas consumen comida rápida a diario
✈️ | A new hotel is going to be built near the airport | Se va a construir un nuevo hotel cerca del aeropuerto
🇯🇵 | These devices are manufactured in Japan | Estos dispositivos se fabrican en Japón
🔑 | The keys were handed over to the new owner | Las llaves fueron entregadas al nuevo propietario
📌 | Mistakes were made during the negotiation | Se cometieron errores durante la negociación
🛡️ | Safety rules must be followed at all times | Las normas de seguridad deben seguirse en todo momento
💰 | Taxes have been reduced for small businesses | Se han reducido los impuestos para pequeñas empresas
📖 | The book was translated into fifteen languages | El libro fue traducido a quince idiomas
"""

    private const val list5 = """
⏰ | You ought to leave earlier to avoid traffic | Deberías salir antes para evitar el tráfico
🧑‍💼 | You should have consulted your supervisor first | Deberías haber consultado primero con tu supervisor
⚠️ | You must not disclose this confidential information | No debes revelar esta información confidencial
🧂 | Could you please pass me the salt? | ¿Podrías pasarme la sal, por favor?
🏥 | She might be at the clinic right now | Puede que ella esté en la clínica ahora mismo
☂️ | It may rain later, so take an umbrella | Puede que llueva más tarde, así que lleva paraguas
🔑 | You don't have to pay anything in advance | No tienes que pagar nada por adelantado
🧠 | He must have forgotten about the appointment | Se debe haber olvidado de la cita
🚫 | You can't be serious about quitting your job | No puedes estar hablando en serio de dejar tu trabajo
🏋️ | We had better leave now before it gets dark | Más vale que nos vayamos ahora antes de que oscurezca
⏱️ | Shall we order dinner now or wait a bit? | ¿Pedimos la cena ahora o esperamos un poco?
🪟 | Would you mind opening the window slightly? | ¿Te importaría abrir la ventana un poco?
💼 | Employees have to wear identification badges | Los empleados tienen que llevar tarjetas de identificación
🧾 | He was able to fix the issue on his own | Pudo solucionar el problema por su cuenta
🚗 | You shouldn't drive when you are exhausted | No deberías conducir cuando estés agotado
🤔 | She could have warned us about the change | Ella nos podría haber advertido sobre el cambio
🥤 | They must be working late at the headquarters | Deben de estar trabajando hasta tarde en la sede
💊 | You must take this medication with food | Debes tomar esta medicina con comida
🖥️ | All visitors must register at the reception desk | Todos los visitantes deben registrarse en recepción
✈️ | We might go abroad for our summer holiday | Puede que nos vayamos al extranjero para las vacaciones de verano
"""

    private const val list6 = """
👥 | I look forward to meeting you next week | Tengo muchas ganas de conocerte la semana que viene
📅 | Don't put off until tomorrow what you can do today | No dejes para mañana lo que puedas hacer hoy
💡 | He came up with a brilliant solution | Se le ocurrió una solución brillante
🧥 | Take off your coat and make yourself at home | Quítate el abrigo y ponte cómodo
📞 | Hold on a second, I'll check for you | Espera un segundo, lo comprobaré por ti
🚗 | We ran out of petrol on the motorway | Nos quedamos sin gasolina en la autopista
👓 | I'm looking for my reading glasses | Estoy buscando mis gafas de leer
👥 | The meeting was called off at the last minute | La reunión fue cancelada a última hora
🚪 | Turn off the lights before you go to sleep | Apaga las luces antes de irte a dormir
📈 | Things are beginning to look up for us | Las cosas están empezando a mejorar para nosotros
🤝 | I get along very well with my colleagues | Me llevo muy bien con mis compañeros
🍳 | Let's clean up the kitchen after dinner | Vamos a limpiar la cocina después de cenar
📍 | She pointed out that there was a typo | Señaló que había una errata
📝 | Please fill out this registration form | Por favor, rellena este formulario de registro
🚉 | Pick me up outside the central station | Recógeme fuera de la estación central
🛑 | Give up smoking if you want to be healthy | Deja de fumar si quieres estar sano
✈️ | The flight took off right on schedule | El vuelo despegó exactamente a la hora prevista
↔️ | Show up on time for the job interview | Preséntate puntual para la entrevista de trabajo
💰 | He paid back the loan within a year | Devolvió el préstamo en el plazo de un año
📖 | Put away your books and listen to me | Guarda tus libros y escúchame
"""

    private const val list7 = """
🏢 | How long have you been working for this firm? | ¿Cuánto tiempo llevas trabajando para esta empresa?
📈 | Our team achieved all the quarterly targets | Nuestro equipo alcanzó todos los objetivos trimestrales
📅 | We need to schedule a conference call tomorrow | Necesitamos programar una conferencia telefónica mañana
📝 | Please review the attached contract carefully | Por favor, revise el contrato adjunto con atención
🤝 | We reached a mutually beneficial agreement | Llegamos a un acuerdo mutuamente beneficioso
💰 | The budget has been approved by the board | El presupuesto ha sido aprobado por la junta directiva
📊 | According to the latest market trends | Según las últimas tendencias del mercado
🎯 | Our main objective is customer satisfaction | Nuestro principal objetivo es la satisfacción del cliente
✉️ | I look forward to receiving your quotation | Quedo a la espera de recibir su presupuesto
👥 | We should delegate some of these tasks | Deberíamos delegar algunas de estas tareas
📁 | We are running behind schedule on this project | Vamos con retraso respecto al calendario en este proyecto
💡 | Any constructive feedback is welcome | Cualquier comentario constructivo es bienvenido
📈 | The company generated record profits this year | La empresa generó beneficios récord este año
🌐 | We are expanding into international markets | Nos estamos expandiendo a mercados internacionales
🧑‍💼 | He was promoted to senior department manager | Fue ascendido a director de departamento sénior
📉 | We must find ways to reduce operating costs | Debemos encontrar formas de reducir los costes operativos
📦 | The shipment has been delayed due to customs | El envío se ha retrasado debido a la aduana
🦶 | Sign here at the bottom of the last page | Firme aquí al pie de la última página
🤝 | Thank you for your continued partnership | Gracias por su continua colaboración
📞 | Don't hesitate to reach out if you have questions | No dude en ponerse en contacto si tiene preguntas
"""

    private const val list8 = """
✈️ | What time does boarding start for flight 302? | ¿A qué hora empieza el embarque del vuelo 302?
🧳 | Could you tell me where baggage claim is located? | ¿Podría decirme dónde se encuentra la recogida de equipaje?
🏨 | I have a reservation under the name of Smith | Tengo una reserva a nombre de Smith
🛏️ | Is breakfast included in the room price? | ¿El desayuno está incluido en el precio de la habitación?
🍽️ | Could you recommend a good local restaurant nearby? | ¿Podría recomendar un buen restaurante local cerca?
🚆 | Which platform does the train to Oxford leave from? | ¿De qué andén sale el tren a Oxford?
🎟️ | I'd like a return ticket to Manchester, please | Quisiera un billete de ida y vuelta a Manchester, por favor
🚕 | Can you call a taxi for me to the airport? | ¿Puede pedirme un taxi al aeropuerto?
💳 | Do you accept foreign credit cards here? | ¿Aceptan tarjetas de crédito extranjeras aquí?
🏨 | How far is the city centre from this hotel? | ¿A qué distancia está el centro de la ciudad de este hotel?
🏁 | Please have your passport ready for inspection | Por favor, tenga su pasaporte listo para la inspección
🏨 | What time is checkout tomorrow morning? | ¿A qué hora es la salida del hotel mañana por la mañana?
📶 | What is the password for the guest Wi-Fi? | ¿Cuál es la contraseña del Wi-Fi para huéspedes?
🚇 | Which subway line goes to the museum? | ¿Qué línea de metro va al museo?
🪑 | Could we get a table for four outside, please? | ¿Podríamos tener una mesa para cuatro fuera, por favor?
💧 | Would you like still or sparkling water? | ¿Desea agua sin gas o con gas?
🧾 | Can we split the bill equally, please? | ¿Podemos dividir la cuenta a partes iguales, por favor?
🏥 | Where is the nearest pharmacy or hospital? | ¿Dónde está la farmacia o el hospital más cercano?
💵 | How much is this souvenir in local currency? | ¿Cuánto cuesta este recuerdo en moneda local?
🏠 | Have a safe and pleasant journey back home | Que tengas un viaje de vuelta a casa seguro y agradable
"""

    private const val list9 = """
🪵 | I usually start my day with a cup of black coffee | Suelo empezar el día con una taza de café solo
🌅 | She goes jogging in the park every morning | Ella va a correr al parque todas las mañanas
⚡ | What do you normally have for breakfast? | ¿Qué sueles desayunar normalmente?
🚗 | It takes me forty minutes to commute to work | Tardo cuarenta minutos en desplazarme al trabajo
📋 | I listen to audiobooks while doing housework | Escucho audiolibros mientras hago las tareas del hogar
🛒 | We do the weekly grocery shopping on Saturdays | Hacemos la compra semanal los sábados
🏠 | Who usually cooks dinner at your house? | ¿Quién suele preparar la cena en tu casa?
🧹 | I need to do the laundry before the weekend | Necesito hacer la colada antes del fin de semana
⌚ | We rarely watch television on weekdays | Rara vez vemos la televisión entre semana
🛏️ | I try to get at least seven hours of sleep | Intento dormir al menos siete horas
📞 | Don't check your phone right after waking up | No mires el móvil justo al levantarte
🐕 | I take my dog for a walk twice a day | Saco a pasear a mi perro dos veces al día
📰 | Meditation helps me manage daily stress | La meditación me ayuda a gestionar el estrés diario
🛏️ | Take a warm shower before going to bed | Date una ducha caliente antes de acostarte
⏰ | I always set my alarm for half past six | Siempre pongo la alarma para las seis y media
💧 | Don't forget to water the indoor plants | No te olvides de regar las plantas de interior
📖 | Reading ten pages a day is a healthy habit | Leer diez páginas al día es un hábito saludable
🚲 | He rides his bicycle to work when the weather is nice | Va en bicicleta al trabajo cuando hace buen tiempo
👨‍👩‍👧‍👦 | We always have family dinner around eight | Siempre cenamos en familia sobre las ocho
🌙 | Good night, sleep tight and sweet dreams | Buenas noches, que descanses y dulces sueños
"""

    private const val list10 = """
🌡️ | What's the temperature outside right now? | ¿Qué temperatura hace fuera ahora mismo?
☀️ | It's sunny and warm with a gentle breeze | Hace sol y calor con una brisa suave
🌧️ | It looks like it's going to pour down soon | Parece que va a caer un chaparrón pronto
⚡ | We could hear thunder in the distance | Podíamos oír truenos a lo lejos
❄️ | The roads are slippery because of the frost | Las carreteras están resbaladizas debido a la escarcha
🧥 | You'll need a heavy coat; it's freezing | Necesitarás un abrigo grueso; hace un frío que pela
🌈 | Look at that stunning rainbow over the hill | Mira ese arcoíris impresionante sobre la colina
🍂 | The leaves change color during autumn | Las hojas cambian de color durante el otoño
🏖️ | Summer in Seville can be extremely hot | El verano en Sevilla puede ser extremadamente caluroso
🚉 | Spring is my favourite season of the whole year | La primavera es mi estación favorita de todo el año
✈️ | Heavy fog caused several flight cancellations | La densa niebla provocó varias cancelaciones de vuelos
🇬🇧 | Always carry an umbrella when you visit London | Lleva siempre un paraguas cuando visites Londres
🌪️ | The strong wind damaged several roof tiles | El fuerte viento dañó varias tejas del tejado
🏊 | Let's go to the beach to cool off | Vamos a la playa a refrescarnos
🍫 | A cup of hot chocolate is perfect on a rainy afternoon | Una taza de chocolate caliente es perfecta en una tarde lluviosa
🧤 | Don't forget your gloves and warm scarf | No olvides tus guantes y bufanda abrigada
☀️ | The sun rises earlier during the summer months | El sol sale más temprano durante los meses de verano
🌊 | High humidity makes the heat feel more intense | La alta humedad hace que el calor se sienta más intenso
☀️ | The sky cleared up just before sunset | El cielo se despejó justo antes de la puesta de sol
🌙 | You can see thousands of stars on a clear night | Puedes ver miles de estrellas en una noche despejada
"""

    private const val list11 = """
🧑‍⚕️ | I need to make an appointment with Dr. Garcia | Necesito pedir cita con el doctor García
📅 | I've had a severe migraine since yesterday evening | He tenido una migraña muy fuerte desde ayer por la tarde
💊 | Take two tablets three times a day after meals | Tome dos pastillas tres veces al día después de las comidas
📋 | Let me listen to your chest and breathing | Déjeme auscultar su pecho y respiración
🌡️ | Do you have a fever or a sore throat? | ¿Tiene fiebre o dolor de garganta?
🩹 | Apply this antiseptic cream to the wound | Aplica esta crema antiséptica en la herida
🦷 | I have an appointment at the dentist next Tuesday | Tengo cita en el dentista el próximo martes
🥶 | I think I've caught a bad cold or flu | Creo que he pillado un resfriado fuerte o gripe
🥗 | A balanced diet is essential for good health | Una dieta equilibrada es esencial para la buena salud
🏃 | Regular exercise reduces the risk of heart disease | El ejercicio regular reduce el riesgo de enfermedades cardíacas
🧑‍⚕️ | The nurse administered the annual vaccine | La enfermera administró la vacuna anual
🧑‍⚕️ | The doctor took an X-ray of my left ankle | El médico me hizo una radiografía del tobillo izquierdo
💧 | Drink plenty of fluids to stay properly hydrated | Bebe abundantes líquidos para mantenerte bien hidratado
😴 | Adequate sleep is crucial for your immune system | El descanso adecuado es crucial para tu sistema inmunitario
🚑 | Call an ambulance immediately, it's an emergency | Llama a una ambulancia inmediatamente, es una emergencia
🌾 | Are you allergic to any specific medications? | ¿Es alérgico a algún medicamento específico?
🧘 | Stress can negatively affect your physical health | El estrés puede afectar negativamente a tu salud física
👀 | You should get your eyesight tested regularly | Deberías revisarte la vista periódicamente
🍵 | Herbal tea with honey can soothe a sore throat | La infusión con miel puede calmar el dolor de garganta
💪 | I hope you make a speedy and full recovery | Espero que tengas una recuperación rápida y completa
"""

    private const val list12 = """
📍 | Could you tell me where the dairy section is? | ¿Podría decirme dónde está la sección de lácteos?
👨 | I'm looking for a gift for my father's birthday | Estoy buscando un regalo para el cumpleaños de mi padre
👔 | Do you have this shirt in a medium size? | ¿Tienen esta camisa en talla mediana?
🛏️ | Can I try these trousers on in the fitting room? | ¿Puedo probarme estos pantalones en el probador?
🧥 | How much does this jacket cost after the discount? | ¿Cuánto cuesta esta chaqueta después del descuento?
💵 | Can I pay with contactless card or cash? | ¿Puedo pagar con tarjeta contactless o en efectivo?
🧾 | Keep your receipt in case you want an exchange | Guarda el ticket por si quieres hacer un cambio
🏠 | Is home delivery available for large appliances? | ¿Hay entrega a domicilio para electrodomésticos grandes?
⏰ | What time do you close on Saturday evenings? | ¿A qué hora cierran los sábados por la tarde?
🍞 | Is this bread freshly baked this morning? | ¿Este pan está recién horneado de esta mañana?
🍎 | How much are the organic apples per kilo? | ¿Cuánto cuestan las manzanas ecológicas por kilo?
🎁 | Could you please gift-wrap this for me? | ¿Podría envolver esto para regalo, por favor?
👞 | These shoes are a bit tight; do you have a larger size? | Estos zapatos me aprietan un poco; ¿tiene una talla más?
💸 | This brand offers great quality at a fair price | Esta marca ofrece gran calidad a un precio justo
🛍️ | There are massive discounts during Black Friday | Hay grandes descuentos durante el Black Friday
🛒 | I need to buy some fresh vegetables for salad | Necesito comprar verduras frescas para ensalada
🏷️ | Buy two items and get the third one free | Compra dos artículos y llévate el tercero gratis
📱 | Does this warranty cover accidental screen damage? | ¿Esta garantía cubre daños accidentales de pantalla?
🛍️ | I'm just browsing around, thank you very much | Solo estoy mirando, muchas gracias
👜 | That leather handbag matches your outfit perfectly | Ese bolso de cuero combina perfectamente con tu ropa
"""

    private const val list13 = """
✉️ | Could we see the dessert menu, please? | ¿Podríamos ver la carta de postres, por favor?
🥗 | I'll have the mixed salad to start with | Tomaré la ensalada mixta de primero
🥩 | How would you like your steak cooked? | ¿Cómo le gustaría que le preparen el filete?
🍷 | A glass of dry red wine, please | Una copa de vino tinto seco, por favor
🐟 | Is the catch of the day fresh? | ¿El pescado del día es fresco?
🌾 | I am allergic to nuts and gluten | Soy alérgico a los frutos secos y al gluten
🧾 | Could we get two espressos and the bill? | ¿Nos trae dos cafés solos y la cuenta?
👨‍🍳 | My compliments to the chef, food was delicious | Mis felicitaciones al chef, la comida estuvo deliciosa
🍝 | What do you recommend as a main course? | ¿Qué me recomienda como plato principal?
🍲 | The soup is too salty for my taste | La sopa está demasiado salada para mi gusto
🧀 | Would you like to share a slice of cheesecake? | ¿Te gustaría compartir una porción de tarta de queso?
🥂 | Let's propose a toast to our friendship! | ¡Hagamos un brindis por nuestra amistad!
🍴 | Excuse me, could I have an extra napkin? | Disculpe, ¿podría traerme una servilleta extra?
🧀 | Let's order a large pizza with extra cheese | Vamos a pedir una pizza grande con extra de queso
🥔 | Does this dish come with fries or boiled potatoes? | ¿Este plato viene con patatas fritas o cocidas?
🇪🇸 | Paella is the most famous Spanish dish | La paella es el plato español más famoso
💧 | A slice of lemon with sparkling water, please | Una rodaja de limón con agua con gas, por favor
🧂 | Could you pass the salt and black pepper? | ¿Podrías pasar la sal y la pimienta negra?
😋 | That smells absolutely delicious! | ¡Eso huele absolutamente delicioso!
🌙 | We are fully booked for this evening | Tenemos todas las mesas reservadas para esta noche
"""

    private const val list14 = """
💻 | He graduated with honors in computer science | Se graduó con matrícula de honor en informática
📚 | The library is open twenty-four hours during exams | La biblioteca está abierta las veinticuatro horas en época de exámenes
↔️ | The assignment is due next Friday at noon | El trabajo debe entregarse el próximo viernes al mediodía
🎓 | She decided to pursue a master's degree abroad | Decidió cursar un máster en el extranjero
📖 | You should take notes during the lecture | Deberías tomar apuntes durante la clase magistral
✨ | We conducted an interesting physics experiment | Realizamos un experimento de física interesante
🇬🇧 | Practice speaking English every single day | Practica hablar inglés todos los días
🧠 | Understanding the concept is better than memorizing | Entender el concepto es mejor que memorizar
🧑‍🏫 | The professor gave a very clear explanation | El profesor dio una explicación muy clara
📑 | I need to print out my research paper | Necesito imprimir mi trabajo de investigación
✏️ | Don't forget your notebook and pencil case | No olvides tu cuaderno y tu estuche
🏛️ | Oxford is one of the oldest universities in the world | Oxford es una de las universidades más antiguas del mundo
💡 | Asking questions helps clarify doubts | Hacer preguntas ayuda a aclarar dudas
📈 | His grades improved significantly this term | Sus notas mejoraron notablemente este trimestre
📅 | The academic semester ends in late June | El semestre académico termina a finales de junio
📁 | We have to work on a group project together | Tenemos que trabajar juntos en un proyecto en grupo
🧗 | Hard work and dedication lead to academic success | El trabajo duro y la dedicación conducen al éxito académico
💻 | Online learning platforms have gained popularity | Las plataformas de aprendizaje online han ganado popularidad
🔍 | Cite all your sources in the bibliography | Cita todas tus fuentes en la bibliografía
🏆 | She won a scholarship to study architecture | Ganó una beca para estudiar arquitectura
"""

    private const val list15 = """
💻 | Have you tried turning the device off and on again? | ¿Has probado a apagar y encender el dispositivo de nuevo?
📅 | The internet connection is very slow today | La conexión a internet está muy lenta hoy
🔒 | Make sure to choose a strong and unique password | Asegúrate de elegir una contraseña segura y única
📱 | Download the latest app update from the store | Descarga la última actualización de la aplicación desde la tienda
💾 | Remember to back up all your critical files | Recuerda hacer una copia de seguridad de todos tus archivos críticos
📧 | Beware of suspicious emails asking for credentials | Ten cuidado con correos sospechosos que pidan credenciales
🔋 | My smartphone battery is running extremely low | La batería de mi smartphone se está agotando por completo
🎧 | Connect your wireless headphones via Bluetooth | Conecta tus auriculares inalámbricos por Bluetooth
☁️ | Storing documents in the cloud makes them accessible | Almacenar documentos en la nube los hace accesibles
🖥️ | I need a monitor with higher resolution | Necesito un monitor con mayor resolución
🤖 | Artificial intelligence is transforming many industries | La inteligencia artificial está transformando muchas industrias
⌨️ | This mechanical keyboard is very comfortable | Este teclado mecánico es muy cómodo para escribir
💻 | Where can I plug in my laptop charger? | ¿Dónde puedo enchufar el cargador de mi portátil?
🚀 | Technology advances at an astonishing pace | La tecnología avanza a un ritmo asombroso
📷 | The camera quality on this new model is superb | La calidad de la cámara en este nuevo modelo es magnífica
🌐 | You need an active internet connection to sync | Necesitas una conexión a internet activa para sincronizar
📁 | The file format is not supported by this program | El formato de archivo no es compatible con este programa
🔔 | Turn off notifications to focus on your deep work | Desactiva las notificaciones para concentrarte en el trabajo profundo
🖱️ | Double-click the icon to launch the application | Haz doble clic en el icono para iniciar la aplicación
⚡ | Fast charging allows you to charge up in thirty minutes | La carga rápida te permite cargar en treinta minutos
"""

    private const val list16 = """
📅 | Real Madrid played against Barcelona yesterday | El Real Madrid jugó contra el Barcelona ayer
🤝 | He plays basketball with his friends on weekends | Juega al baloncesto con sus amigos los fines de semana
🎾 | Tennis requires speed, stamina and coordination | El tenis requiere velocidad, resistencia y coordinación
🏊 | Swimming is one of the most complete sports | La natación es uno de los deportes más completos
🚲 | She rode fifty kilometers on her road bike | Recorrió cincuenta kilómetros en su bicicleta de carretera
↔️ | He is currently training for the autumn marathon | Actualmente está entrenando para el maratón de otoño
🙈 | The national team won the championship trophy | La selección nacional ganó el trofeo del campeonato
🥊 | Boxing requires immense mental and physical discipline | El boxeo requiere una inmensa disciplina mental y física
🏋️ | Lifting weights helps build muscle strength | Levantar pesas ayuda a desarrollar la fuerza muscular
🌸 | Golf is played on beautiful green courses | El golf se juega en hermosos campos verdes
🧗 | Rock climbing tests your courage and agility | La escalada en roca pone a prueba tu valentía y agilidad
⛷️ | We went skiing in the Pyrenees last winter | Fuimos a esquiar a los Pirineos el invierno pasado
🧘 | Yoga improves flexibility and core stability | El yoga mejora la flexibilidad y la estabilidad central
🙈 | Surfing giant waves requires years of practice | Surfear olas gigantes requiere años de práctica
🏟️ | The stadium was packed with enthusiastic fans | El estadio estaba abarrotado de aficionados entusiastas
🥇 | She received a gold medal for her performance | Recibió una medalla de oro por su actuación
⚽ | He scored the winning goal in extra time | Marcó el gol de la victoria en la prórroga
👞 | Make sure you wear appropriate running shoes | Asegúrate de llevar zapatillas de correr adecuadas
💧 | Hydration is crucial before and after exercise | La hidratación es crucial antes y después de hacer ejercicio
🔑 | Consistency is the true key to physical fitness | La constancia es la verdadera clave para la forma física
"""

    private const val list17 = """
🎵 | What genre of music do you like listening to? | ¿Qué género de música te gusta escuchar?
🎸 | He has been playing acoustic guitar since childhood | Lleva tocando la guitarra acústica desde la infancia
🌸 | The pianist played a Beethoven sonata beautifully | El pianista tocó una sonata de Beethoven de forma hermosa
🎨 | We visited the modern art exhibition in Madrid | Visitamos la exposición de arte moderno en Madrid
🎬 | Have you seen the latest Christopher Nolan movie? | ¿Has visto la última película de Christopher Nolan?
🎟️ | I bought two tickets for the rock concert tonight | Compré dos entradas para el concierto de rock de esta noche
🎭 | We watched a wonderful play at the Royal Theatre | Vimos una obra de teatro maravillosa en el Teatro Real
🎻 | The orchestra gave a breathtaking performance | La orquesta dio una actuación impresionante
📚 | Reading classic literature expands your vocabulary | Leer literatura clásica amplía tu vocabulario
📸 | Photography allows you to capture fleeting moments | La fotografía te permite capturar momentos fugaces
🏛️ | The Prado Museum houses thousands of masterpieces | El Museo del Prado alberga miles de obras maestras
✍️ | She writes poetry in her spare time | Escribe poesía en su tiempo libre
🎵 | Jazz music originated in New Orleans | La música jazz se originó en Nueva Orleans
🎬 | Let's grab some popcorn before the film starts | Vamos a coger palomitas antes de que empiece la película
🎵 | Listening to instrumental music helps me study | Escuchar música instrumental me ayuda a estudiar
🎨 | Oil painting requires a great deal of patience | La pintura al óleo requiere una gran cantidad de paciencia
🎤 | The singer has a truly remarkable vocal range | El cantante tiene un rango vocal verdaderamente notable
📽️ | The documentary explores marine biodiversity | El documental explora la biodiversidad marina
💃 | Flamenco is an expressive and passionate dance | El flamenco es un baile expresivo y apasionado
🌟 | Art reflects culture, emotion and history | El arte refleja la cultura, la emoción y la historia
"""

    private const val list18 = """
🤝 | Honesty is the foundation of any strong friendship | La honestidad es la base de cualquier amistad sólida
❤️ | They celebrated their twenty-fifth anniversary | Celebraron su vigésimo quinto aniversario
👨‍👩‍👧‍👦 | Family gatherings during holidays are memorable | Las reuniones familiares en vacaciones son memorables
💬 | Good communication resolves misunderstandings | Una buena comunicación resuelve los malentendidos
📋 | It's important to be an empathetic listener | Es importante ser un oyente empático
👦 | They are expecting their first child next spring | Están esperando su primer hijo la próxima primavera
😊 | Happy birthday! Wishing you many happy returns | ¡Feliz cumpleaños! Deseándote muchos éxitos más
🧑‍🤝‍🧑 | We have been best friends since primary school | Hemos sido mejores amigos desde la escuela primaria
🧾 | It's the thought that counts when giving a gift | Lo que cuenta es la intención al dar un regalo
💔 | Breaking up after a long relationship is painful | Romper después de una larga relación es doloroso
👵 | My grandparents have a wealth of life wisdom | Mis abuelos tienen una gran sabiduría de vida
🏡 | Moving to a new neighbourhood can be challenging | Mudarse a un nuevo barrio puede ser un reto
🤗 | A warm hug can make someone's bad day better | Un abrazo cálido puede alegrar el mal día de alguien
🎉 | Congratulations on your outstanding achievement! | ¡Enhorabuena por tu logro extraordinario!
🤝 | Trust takes years to build and seconds to break | La confianza tarda años en construirse y segundos en romperse
💍 | He proposed to her during their romantic trip to Rome | Le pidió matrimonio durante su romántico viaje a Roma
👨‍👩‍👧‍👦 | Pets become an integral part of the family | Las mascotas se convierten en una parte integral de la familia
🗣️ | Talk things through instead of holding grudges | Habla las cosas en lugar de guardar rencor
🤝 | True friends support you through thick and thin | Los verdaderos amigos te apoyan en las buenas y en las malas
🕊️ | Forgiveness brings peace of mind and closure | El perdón trae paz mental y tranquilidad
"""

    private const val list19 = """
🌍 | Climate change is one of our greatest challenges | El cambio climático es uno de nuestros mayores desafíos
♻️ | Recycling plastic reduces environmental pollution | Reciclar plástico reduce la contaminación ambiental
🌳 | Planting trees helps absorb carbon dioxide | Plantar árboles ayuda a absorber dióxido de carbono
✨ | Solar energy is a clean and renewable resource | La energía solar es un recurso limpio y renovable
💧 | We must conserve water in times of severe drought | Debemos ahorrar agua en tiempos de sequía severa
🚗 | Electric vehicles produce zero tailpipe emissions | Los vehículos eléctricos producen cero emisiones de escape
🦁 | Protecting endangered species preserves biodiversity | Proteger especies en peligro de extinción preserva la biodiversidad
🌊 | Plastic waste poses a serious threat to marine life | Los residuos plásticos suponen una grave amenaza para la vida marina
🚲 | Using public transit reduces your carbon footprint | Usar el transporte público reduce tu huella de carbono
🍃 | Nature has a remarkable ability to heal itself | La naturaleza tiene una notable capacidad para regenerarse
🏔️ | Glaciers are melting at an unprecedented rate | Los glaciares se están derritiendo a un ritmo sin precedentes
👜 | Bring reusable bags when you go to the supermarket | Lleva bolsas reutilizables cuando vayas al supermercado
💡 | Turn off household appliances on standby to save power | Apaga los aparatos en reposo para ahorrar energía
🌾 | Organic farming avoids the use of harmful pesticides | La agricultura ecológica evita el uso de pesticidas dañinos
🌿 | Preserving our national parks is a vital priority | Preservar nuestros parques nacionales es una prioridad vital
🌡️ | Global temperatures have risen steadily over decades | Las temperaturas globales han subido de forma constante durante décadas
🐦 | Many bird species migrate thousands of miles | Muchas especies de aves migran miles de kilómetros
🌱 | Every small eco-friendly habit makes a difference | Cada pequeño hábito ecológico marca la diferencia
🌍 | We only have one planet, so let's protect it | Solo tenemos un planeta, así que vamos a protegerlo
🌅 | Enjoying sunrise in nature is truly revitalizing | Disfrutar del amanecer en la naturaleza es verdaderamente revitalizante
"""

    private const val list20 = """
📅 | I came across an interesting article yesterday | Me encontré por casualidad con un artículo interesante ayer
🔍 | We need to figure out why the system crashed | Necesitamos averiguar por qué se cayó el sistema
🚗 | My car broke down on the way to work | Mi coche se averió de camino al trabajo
🚪 | Don't let me down, I'm counting on you | No me defraudes, cuento contigo
📞 | I'll call you back as soon as I'm free | Te devolveré la llamada en cuanto esté libre
📞 | Could you write down your phone number here? | ¿Podrías apuntar tu número de teléfono aquí?
🧥 | Put on your raincoat; it's pouring outside | Ponte el chubasquero; está diluviando fuera
💡 | They turned down our initial compromise proposal | Rechazaron nuestra propuesta inicial de compromiso
⏰ | Time is running out, we need to decide now | El tiempo se está acabando, tenemos que decidir ahora
🚪 | She saw her family off at the departure gate | Despidió a su familia en la puerta de salidas
🧹 | He wiped out all the saved data by mistake | Borró todos los datos guardados por error
🤝 | We look up to our mentors for guidance | Admiramos a nuestros mentores en busca de orientación
🛒 | I ran into an old classmate at the supermarket | Me topé con un antiguo compañero en el supermercado
📖 | Look up the word in the dictionary if unsure | Busca la palabra en el diccionario si no estás seguro
🔑 | Lock up the office before leaving for the night | Cierra bien la oficina con llave antes de irte por la noche
💰 | They cut down their monthly expenses drastically | Redujeron drásticamente sus gastos mensuales
⚡ | The fireworks went off with a loud explosion | Los fuegos artificiales se dispararon con una fuerte explosión
🛑 | We must put an end to this waste of resources | Debemos poner fin a este despilfarro de recursos
💪 | Keep on practicing and you will master fluency | Sigue practicando y dominarás la fluidez
🧂 | Set off early to avoid the peak rush hour | Sal temprano para evitar la hora punta
"""

    private const val list21 = """
🍞 | Piece of cake! It was very easy to complete | ¡Pan comido! Fue muy fácil de completar
🐕 | It's raining cats and dogs outside | Está lloviendo a cántaros fuera
🚗 | That sports car costs an arm and a leg | Ese coche deportivo cuesta un ojo de la cara
👂 | I'm all ears; tell me what happened | Soy todo oídos; cuéntame qué pasó
⏳ | Better late than never, as the old saying goes | Más vale tarde que nunca, como dice el viejo refrán
🥊 | Hit the nail on the head with your analysis | Has dado en el clavo con tu análisis
🌙 | I'm going to hit the sack early tonight | Me voy a ir al sobre temprano esta noche
🧊 | An icebreaker game helps ease group tension | Un juego para romper el hielo ayuda a aliviar la tensión del grupo
🤫 | Don't spill the beans about the surprise party | No te vayas de la lengua sobre la fiesta sorpresa
⚡ | Out of the blue, he decided to resign | De la nada / De repente, decidió dimitir
🚢 | We are all in the same boat in this crisis | Todos estamos en el mismo barco en esta crisis
🎯 | You took the words right out of my mouth | Me has quitado las palabras de la boca
👁️ | Keep an eye on the stove while I go upstairs | Échale un ojo a los fogones mientras subo arriba
🌙 | Break a leg at your theatrical audition tonight! | ¡Mucha mierda / Mucho éxito en tu audición de teatro esta noche!
🤷 | It's not rocket science; anyone can do it | No es física cuántica; cualquiera puede hacerlo
💵 | A penny saved is a penny earned | Dinero ahorrado es dinero ganado
🤐 | Bite your tongue and stay calm in arguments | Muérdete la lengua y mantén la calma en las discusiones
🐕 | Barking dogs seldom bite, don't be afraid | Perro ladrador poco mordedor, no tengas miedo
🐦 | The early bird catches the worm | A quien madruga, Dios le ayuda
🧗 | Every cloud has a silver lining in difficult times | No hay mal que por bien no venga en tiempos difíciles
"""

    private const val list22 = """
🧾 | I opened a high-interest savings account | Abrí una cuenta de ahorros con alto interés
💳 | What is the annual interest rate on this loan? | ¿Cuál es la tasa de interés anual de este préstamo?
🏠 | They applied for a twenty-year mortgage | Solicitaron una hipoteca a veinte años
📈 | Investing in diversified index funds is wise | Invertir en fondos indexados diversificados es prudente
📉 | Inflation reduces consumer purchasing power | La inflación reduce el poder adquisitivo de los consumidores
💵 | Always keep an emergency fund for unforeseen costs | Mantén siempre un fondo de emergencia para gastos imprevistos
🏦 | Review your bank statement at the end of each month | Revisa tu extracto bancario al final de cada mes
💰 | Where is the nearest ATM to withdraw some euros? | ¿Dónde está el cajero más cercano para sacar euros?
🦒 | Avoid accumulating high-interest credit card debt | Evita acumular deudas de tarjetas de crédito con altos intereses
👜 | The stock market experienced high volatility today | La bolsa experimentó una alta volatilidad hoy
🏦 | The central bank decided to raise interest rates | El banco central decidió subir los tipos de interés
💼 | Setting a monthly budget prevents overspending | Fijar un presupuesto mensual previene el gasto excesivo
💰 | She earns a steady passive income from rental units | Gana unos ingresos pasivos constantes de alquileres
👨‍👩‍👧‍👦 | Life insurance protects your family's future | El seguro de vida protege el futuro de tu familia
🪙 | Cryptocurrency values fluctuate rapidly | El valor de las criptomonedas fluctúa rápidamente
📝 | File your annual tax return before the deadline | Presenta tu declaración de la renta anual antes de la fecha límite
🏢 | He started his own consulting business with savings | Empezó su propio negocio de consultoría con sus ahorros
⚡ | Contactless payments are fast and secure | Los pagos sin contacto son rápidos y seguros
📉 | Economic recessions impact small businesses heavily | Las recesiones económicas afectan mucho a las pequeñas empresas
🎯 | Financial freedom requires discipline and foresight | La libertad financiera requiere disciplina y previsión
"""

    private const val list23 = """
⚖️ | Everyone is equal before the rule of law | Todos somos iguales ante la ley
🏛️ | The supreme court announced a historic ruling | El tribunal supremo anunció una sentencia histórica
👨‍⚖️ | The judge ordered the jury to deliberate | El juez ordenó al jurado que deliberara
⚖️ | You should consult a lawyer before signing | Deberías consultar a un abogado antes de firmar
✋ | The suspect was arrested for armed robbery | El sospechoso fue detenido por robo a mano armada
🔍 | Forensic investigators found crucial evidence | Los investigadores forenses hallaron pruebas cruciales
🗣️ | The witness testified under oath in the courtroom | El testigo declaró bajo juramento en la sala del tribunal
🛡️ | Every citizen has the right to a fair trial | Todo ciudadano tiene derecho a un juicio justo
📜 | The constitution guarantees fundamental human rights | La constitución garantiza los derechos humanos fundamentales
📝 | A breach of contract may lead to legal litigation | Un incumplimiento de contrato puede derivar en litigio judicial
🏢 | The intellectual property rights belong to the author | Los derechos de propiedad intelectual pertenecen al autor
📑 | Both parties agreed to settle the dispute out of court | Ambas partes acordaron resolver la disputa extrajudicialmente
👮 | Traffic laws must be strictly obeyed by all drivers | Las normas de tráfico deben ser cumplidas estrictamente por todos
⚖️ | Justice must be served impartially and fairly | La justicia debe aplicarse de forma imparcial y justa
🔒 | He was released on bail pending his formal appeal | Quedó en libertad bajo fianza a la espera de su apelación formal
📝 | The tenant signed a two-year residential lease | El inquilino firmó un contrato de arrendamiento de dos años
🧾 | Parliament passed a new consumer protection bill | El parlamento aprobó una nueva ley de protección al consumidor
🔍 | There was insufficient evidence to convict him | Hubo pruebas insuficientes para condenarle
💡 | Ignorance of the law is no valid excuse | El desconocimiento de la ley no es una excusa válida
🤝 | The mediator helped reach an amicable settlement | El mediador ayudó a alcanzar un acuerdo amistoso
"""

    private const val list24 = """
💧 | Scientists discovered a new water treatment method | Los científicos descubrieron un nuevo método de tratamiento de agua
🚀 | The spacecraft entered orbit around the moon | La nave espacial entró en órbita alrededor de la luna
🧬 | Genetic research helps treat rare hereditary diseases | La investigación genética ayuda a tratar enfermedades hereditarias raras
🔭 | The James Webb Telescope captures deep space images | El telescopio James Webb captura imágenes del espacio profundo
🧪 | Chemical reactions produce heat and light | Las reacciones químicas producen calor y luz
⚡ | Renewable energy will replace fossil fuels gradually | Las energías renovables sustituirán gradualmente a los combustibles fósiles
🌱 | Photosynthesis converts sunlight into chemical energy | La fotosíntesis convierte la luz solar en energía química
🤖 | Robotics enhances precision in surgical procedures | La robótica mejora la precisión en los procedimientos quirúrgicos
🌊 | Ocean exploration reveals new fascinating species | La exploración oceánica revela nuevas especies fascinantes
🌋 | Volcanic eruptions can alter local weather patterns | Las erupciones volcánicas pueden alterar los patrones climáticos locales
💻 | Quantum computing will revolutionize cybersecurity | La computación cuántica revolucionará la ciberseguridad
🧲 | Magnetic fields are used in medical MRI scanning | Los campos magnéticos se usan en resonancias magnéticas médicas
🧠 | Neuroscience studies how memories are formed | La neurociencia estudia cómo se forman los recuerdos
🌌 | Black holes exert tremendous gravitational pull | Los agujeros negros ejercen una atracción gravitatoria tremenda
🔬 | Nanotechnology operates at atomic molecular scales | La nanotecnología opera a escalas atómicas y moleculares
💡 | Innovation stems from curiosity and experimentation | La innovación surge de la curiosidad y la experimentación
📊 | Peer-reviewed studies validate scientific claims | Los estudios revisados por pares validan las afirmaciones científicas
🌡️ | Thermodynamics governs heat and energy transfer | La termodinámica rige la transferencia de calor y energía
🌍 | Continental drift shaped the continents over epochs | La deriva continental dio forma a los continentes a lo largo de las eras
🚀 | Exploration inspires future generations of thinkers | La exploración inspira a futuras generaciones de pensadores
"""

    private const val list25 = """
🏛️ | Ancient Greek philosophy shaped Western thinking | La filosofía griega antigua dio forma al pensamiento occidental
⚔️ | The Roman Empire expanded across Europe and Africa | El Imperio Romano se expandió por Europa y África
🏰 | Medieval castles were built for defense and status | Los castillos medievales se construían para la defensa y el estatus
📜 | The Renaissance marked a revival of arts and science | El Renacimiento marcó un renacimiento de las artes y las ciencias
🚢 | Maritime voyages connected previously isolated worlds | Los viajes marítimos conectaron mundos anteriormente aislados
🏭 | The Industrial Revolution transformed global labour | La Revolución Industrial transformó el trabajo global
🇫🇷 | The French Revolution championed liberty and equality | La Revolución Francesa defendió la libertad y la igualdad
📜 | The printing press democratized access to knowledge | La imprenta democratizó el acceso al conocimiento
🏛️ | Archaeological excavations uncover lost civilizations | Las excavaciones arqueológicas descubren civilizaciones perdidas
🛣️ | The Silk Road facilitated trade across Eurasia | La Ruta de la Seda facilitó el comercio por toda Eurasia
🥶 | The Cold War defined international politics for decades | La Guerra Fría definió la política internacional durante décadas
👑 | Monarchy was the predominant form of government | La monarquía fue la forma de gobierno predominante
📖 | Studying history helps us avoid repeating past errors | Estudiar historia nos ayuda a evitar repetir errores del pasado
🏛️ | The Pyramids of Giza stand as timeless monuments | Las Pirámides de Guiza se alzan como monumentos atemporales
🛡️ | Treaties were signed to establish lasting peace | Se firmaron tratados para establecer una paz duradera
🌍 | Migration has driven cultural exchange throughout time | La migración ha impulsado el intercambio cultural a lo largo del tiempo
📜 | Ancient manuscripts reveal daily life centuries ago | Manuscritos antiguos revelan la vida cotidiana de hace siglos
🏛️ | Athens is regarded as the birthplace of democracy | Atenas es considerada la cuna de la democracia
🗺️ | Historical maps show how geographic borders shifted | Los mapas históricos muestran cómo cambiaron las fronteras geográficas
🌟 | Heritage preservation connects us to our ancestors | La preservación del patrimonio nos conecta con nuestros antepasados
"""

    private const val list26 = """
🏠 | Living in a quiet rural village offers peace of mind | Vivir en un pueblo rural tranquilo ofrece paz mental
🌆 | Metropolises offer vibrant culture and dining | Las metrópolis ofrecen una cultura y gastronomía vibrantes
🚗 | Rush-hour traffic in capitals can be unbearable | El tráfico en hora punta en las capitales puede ser insoportable
🌳 | Green urban spaces improve resident well-being | Los espacios verdes urbanos mejoran el bienestar de los residentes
🚲 | Bike-friendly cities encourage healthy commuting | Las ciudades adaptadas para bicis fomentan desplazamientos saludables
🏬 | High-rise apartment complexes dominate the skyline | Los bloques de pisos de gran altura dominan el horizonte
🚜 | Agriculture remains the backbone of rural economies | La agricultura sigue siendo la columna vertebral de las economías rurales
🚇 | A reliable metro system makes city navigation easy | Un sistema de metro fiable facilita el desplazamiento por la ciudad
🏡 | Having a private garden is a major countryside benefit | Tener un jardín privado es una gran ventaja del campo
🦶 | In the city, everything you need is within walking distance | En la ciudad, todo lo que necesitas está a poca distancia a pie
🌌 | You can see clear starry skies far from city lights | Puedes ver cielos estrellados y despejados lejos de las luces de la ciudad
🏭 | Urban pollution can negatively impact air quality | La contaminación urbana puede afectar negativamente a la calidad del aire
👥 | Close-knit rural communities foster mutual support | Las comunidades rurales unidas fomentan el apoyo mutuo
🎭 | Cities host international concerts and festivals | Las ciudades acogen conciertos y festivales internacionales
🛏️ | Country living provides ample room for energetic pets | La vida en el campo ofrece amplio espacio para mascotas enérgicas
💰 | The cost of living is typically lower in small towns | El coste de la vida suele ser más bajo en pueblos pequeños
🛍️ | Mega shopping malls are common in metropolitan suburbs | Los grandes centros comerciales son comunes en los suburbios
🌊 | Coastal towns attract thousands during summer | Los pueblos costeros atraen a miles de personas durante el verano
🚆 | High-speed trains link major economic hubs efficiently | Los trenes de alta velocidad conectan centros económicos con eficiencia
⚖️ | Choosing where to live involves balancing priorities | Elegir dónde vivir implica sopesar prioridades
"""

    private const val list27 = """
✈️ | She loves traveling and exploring exotic cultures | Le encanta viajar y explorar culturas exóticas
📸 | Landscape photography requires patience and lighting | La fotografía de paisajes requiere paciencia e iluminación
🍳 | Cooking gourmet meals is his favourite weekend hobby | Cocinar platos gourmet es su pasatiempo favorito de fin de semana
🪴 | Gardening is a therapeutic and rewarding pastime | La jardinería es un pasatiempo terapéutico y gratificante
🌙 | Knitting woollen blankets helps her unwind at night | Tejer mantas de lana la ayuda a relajarse por la noche
♟️ | Playing chess sharpens strategic decision-making | Jugar al ajedrez agudiza la toma de decisiones estratégicas
📖 | Joining a book club encourages reading diversity | Unirse a un club de lectura fomenta la diversidad lectora
🎨 | Watercolor painting is both relaxing and creative | La pintura a la acuarela es a la vez relajante y creativa
🎣 | Fishing by the tranquil lake brings utter calm | Pescar junto al tranquilo lago aporta total tranquilidad
🥾 | Hiking steep mountain trails improves endurance | Hacer senderismo por senderos de montaña mejora la resistencia
🧩 | Solving crossword puzzles keeps your mind active | Resolver crucigramas mantiene tu mente activa
🎸 | Learning a musical instrument requires dedication | Aprender un instrumento musical requiere dedicación
↔️ | Skateboarding is popular among teenage youth | El monopatín es popular entre los jóvenes adolescentes
🏕️ | Camping under the open stars connects you to nature | Acampar bajo las estrellas te conecta con la naturaleza
💃 | Salsa dancing is an energetic social hobby | Bailar salsa es un pasatiempo social muy enérgico
🏊 | Scuba diving allows you to explore coral reefs | El buceo te permite explorar arrecifes de coral
🪵 | Woodworking allows you to craft bespoke furniture | La carpintería te permite fabricar muebles a medida
🧘 | Practicing mindfulness reduces chronic workplace anxiety | Practicar la atención plena reduce la ansiedad laboral crónica
🍞 | Baking homemade sourdough bread is very satisfying | Hornear pan de masa madre casero es muy satisfactorio
🌟 | Dedicating time to your passions enriches your life | Dedicar tiempo a tus pasiones enriquece tu vida
"""

    private const val list28 = """
🗣️ | Body language conveys more than spoken words | El lenguaje corporal transmite más que las palabras habladas
📋 | Active listening is essential for resolving conflicts | La escucha activa es esencial para resolver conflictos
📧 | Keep your emails concise, courteous and clear | Mantén tus correos electrónicos concisos, corteses y claros
🎯 | Public speaking confidence comes with practice | La confianza al hablar en público se adquiere con la práctica
💡 | Clarity of thought leads to clarity of speech | La claridad de pensamiento conduce a la claridad de expresión
👁️ | Maintaining appropriate eye contact shows engagement | Mantener un contacto visual adecuado muestra interés
👌 | Misunderstandings happen easily in text messages | Los malentendidos ocurren fácilmente en los mensajes de texto
🤫 | Sometimes staying silent is the wisest response | A veces guardar silencio es la respuesta más sabia
🤝 | Empathy enables you to see from another perspective | La empatía te permite ver desde la perspectiva de otra persona
📢 | State your main points clearly at the beginning | Expón tus puntos principales claramente al principio
❓ | Asking open-ended questions encourages deep dialogue | Hacer preguntas abiertas fomenta el diálogo profundo
💭 | Assertiveness means expressing opinions respectfully | La asertividad significa expresar opiniones con respeto
📝 | Proofread important messages before hitting send | Revisa los mensajes importantes antes de darle a enviar
🎭 | Tone of voice can completely alter a sentence's meaning | El tono de voz puede alterar por completo el significado de una frase
🌐 | Cross-cultural communication requires cultural awareness | La comunicación intercultural requiere conciencia cultural
🏢 | Building rapport is the first step in negotiations | Establecer sintonía es el primer paso en las negociaciones
💬 | Constructive criticism should be balanced with praise | La crítica constructiva debe equilibrarse con elogios
📋 | Listen to understand, not just to formulate a reply | Escucha para comprender, no solo para formular una respuesta
🌉 | Effective communication builds lasting bridges | La comunicación eficaz construye puentes duraderos
😊 | A warm, sincere smile is understood in every language | Una sonrisa cálida y sincera se entiende en todos los idiomas
"""

    private const val list29 = """
🧭 | Moral values guide our everyday ethical decisions | Los valores morales guían nuestras decisiones éticas cotidianas
🤝 | Integrity means doing the right thing when unwatched | La integridad significa hacer lo correcto cuando nadie mira
💖 | Compassion for the vulnerable reflects true strength | La compasión hacia los vulnerables refleja verdadera fortaleza
⚖️ | Fairness and equality must guide social policy | La justicia y la igualdad deben guiar las políticas sociales
🌱 | Personal growth requires stepping outside your comfort zone | El crecimiento personal requiere salir de tu zona de confort
🛡️ | Courage is not the absence of fear, but acting anyway | El valor no es la ausencia de miedo, sino actuar a pesar de él
⏳ | Patience is a virtue when facing long-term goals | La paciencia es una virtud al afrontar metas a largo plazo
🌟 | Gratitude transforms what we have into enough | La gratitud transforma lo que tenemos en suficiente
🤝 | Respect for differing viewpoints enriches society | El respeto por los puntos de vista divergentes enriquece la sociedad
🐢 | Persistence overcomes obstacles that talent cannot | La perseverancia supera obstáculos que el talento no puede
📚 | Humility allows you to keep learning from everyone | La humildad te permite seguir aprendiendo de todo el mundo
🕊️ | Inner peace begins the moment you choose not to resent | La paz interior comienza en el momento en que decides no guardar rencor
💪 | Resilience is the ability to bounce back from adversity | La resiliencia es la capacidad de recuperarse de la adversidad
❤️ | Kindness costs nothing but means everything to the receiver | La amabilidad no cuesta nada pero significa todo para quien la recibe
🌉 | Self-discipline is the bridge between goals and results | La autodisciplina es el puente entre las metas y los resultados
🌟 | Strive to leave the world better than you found it | Esfuérzate por dejar el mundo mejor de lo que lo encontraste
🔍 | Self-reflection helps identify areas for self-improvement | La autorreflexión ayuda a identificar áreas de mejora personal
🧗 | Loyalty is proven during difficult and trying seasons | La lealtad se demuestra en épocas difíciles y complicadas
🧗 | Optimism empowers you to see opportunities in hardship | El optimismo te capacita para ver oportunidades en la dificultad
🎯 | Living with purpose gives deep meaning to existence | Vivir con propósito da un profundo significado a la existencia
"""

    private const val list30 = """
🚪 | Mastering a foreign language opens international doors | Dominar un idioma extranjero abre puertas internacionales
🇬🇧 | Immerse yourself in authentic English podcasts daily | Sumérgete a diario en podcasts auténticos en inglés
🇬🇧 | Read newspapers, novels and essays in English | Lee periódicos, novelas y ensayos en inglés
🗣️ | Don't be afraid to make mistakes; they facilitate learning | No tengas miedo a cometer errores; facilitan el aprendizaje
🇬🇧 | Keep a daily journal written entirely in English | Lleva un diario personal escrito completamente en inglés
🇬🇧 | Think directly in English rather than translating mentally | Piensa directamente en inglés en lugar de traducir mentalmente
🇬🇧 | Watch movies and series with original English audio | Mira películas y series con audio original en inglés
🇬🇧 | Switch your smartphone and computer settings to English | Cambia los ajustes de tu móvil y ordenador a inglés
👥 | Engage in language exchange meetups with native speakers | Participa en intercambios de idiomas con hablantes nativos
🎯 | Set achievable daily language practice goals | Establece objetivos diarios de práctica de idiomas alcanzables
⚡ | Review flashcards spaced out over weeks for retention | Repasa tarjetas espaciadas a lo largo de las semanas para retener
📚 | Learn vocabulary in context within full sentences | Aprende vocabulario en contexto dentro de oraciones completas
🇬🇧 | Sing along to English songs to refine pronunciation | Canta canciones en inglés para perfeccionar la pronunciación
🗣️ | Shadowing native speakers improves speech rhythm | Imitar a hablantes nativos mejora el ritmo del habla
🏆 | Consistency beats intensity when learning languages | La constancia supera a la intensidad al aprender idiomas
💡 | Celebrate your progress along your language journey | Celebra tu progreso a lo largo de tu viaje con el idioma
🇬🇧 | English is the global bridge between diverse cultures | El inglés es el puente global entre culturas diversas
📝 | Practice writing essays and summaries regularly | Practica la redacción de ensayos y resúmenes con regularidad
🌟 | Fluency comes with continuous everyday exposure | La fluidez llega con la exposición continua del día a día
📋 | Congratulations on completing this advanced translation list! | ¡Enhorabuena por completar esta lista avanzada de traducción!
"""
}
