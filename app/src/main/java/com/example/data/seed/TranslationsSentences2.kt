package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences2 {
    const val FOLDER_NAME = "Translations Sentences 2"
    val categoryNames: List<String> = (1..30).map { "Translations 2 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 1", list1))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 2", list2))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 3", list3))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 4", list4))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 5", list5))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 6", list6))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 7", list7))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 8", list8))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 9", list9))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 10", list10))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 11", list11))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 12", list12))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 13", list13))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 14", list14))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 15", list15))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 16", list16))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 17", list17))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 18", list18))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 19", list19))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 20", list20))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 21", list21))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 22", list22))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 23", list23))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 24", list24))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 25", list25))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 26", list26))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 27", list27))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 28", list28))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 29", list29))
        list.addAll(TranslationDataHelper.parseList("Translations 2 - List 30", list30))
        return list
    }

    private const val list1 = """
📌 | I'll do it when I can | Lo hare cuando pueda
⚡ | I'll see him as soon as I can | Le vere en cuanto pueda
📍 | When will they be here? | ¿Cuando estarán aquí?
💡 | I have no idea | No tengo ni idea
⏱️ | Don't you know when they are coming? | ¿No sabes cuando vienen?
📞 | They'll come when I call them | Vendran cuando yo les llame
📞 | When are you going to call them? | ¿Cuando vas a llamarles?
⏰ | I'll call them when I have time | Les llamare cuando tenga tiempo
⏰ | Don't you have time now? | ¿No tienes tiempo ahora?
📝 | Of course I do | Por supuesto que si
🤔 | Then why don't you do it? | Entonces ¿por que no lo haces?
✔️ | All right, I'll do it | Vale, lo hare
🌅 | We'll visit them tomorrow morning | Les visitaremos mañana por la mañana
🌇 | He'll be here a little later | El estará aquí un poco mas tarde
📞 | Why don't you call back? | ¿Por que no vuelves a llamar?
❓ | Will they ask me a lot of questions? | ¿Me harán muchas preguntas?
🚶 | She'll come when she wants to | Ella vendra cuando quiera
📥 | When will they receive / get it? | ¿Cuando lo recibirán?
📦 | It'll probably take place here | Probablemente tendra lugar aqui
📅 | Things will change someday | Las cosas cambiaran algun dia
📌 | Things have to change | Las cosas tienen que cambiar
📦 | When will it take place? | ¿Cuando tendrá lugar?
👥 | When will the meeting begin? | ¿Cuando empezara la reunión?
❓ | When will it end / be over? | ¿Cuando terminara?
🛑 | When will you finish / be through? | ¿Cuando terminaras?
⭕ | Nothing will happen | No pasara nada
⏰ | How long will they be here? | ¿Cuanto tiempo estarán aquí?
📍 | Where will they stay? | ¿Donde se alojaran?
✈️ | Who will pick them up at the airport? | ¿Quien les recogerá en el aeropuerto?
📅 | Who'll bring them here every day? | ¿Quien les traerá aquí cada día?
📅 | Where'll we take them on Sunday? | ¿A donde les llevaremos el domingo?
🍽️ | What'll we do if they don't like the food? | ¿Que haremos si no les gusta la comida?
💵 | How much money will they need? | ¿Cuanto dinero necesitaran?
😟 | Don't worry, I'll do it | No te preocupes, yo lo hare.
🤔 | Why do you want to do it? | ¿Por que lo quieres hacer tu?
⏱️ | Because I'm the only one who knows how to do it | Por que soy el único que sabe hacerlo.
✨ | Do you really know how to do it? | ¿Sabes hacerlo de verdad?
⭕ | Nobody knows more than me /I do | Nadie sabe mas que yo.
❓ | Whe are you going to do it? | ¿Cuando vas a hacerlo?
📌 | I'll do it when they let me | Lo hare cuando me dejen.
❓ | When do you think they'll let you? | ¿Cuando crees que te dejaran?
🌙 | They'll let me do it tonight | Me dejaran hacerlo esta noche.
💬 | Can you tell me what you're going to do? | ¿Puedes decirme lo que vas a hacer?
🤫 | It's a secret I must keep | Es un secreto que debo guardar.
🔹 | What'll happen if someone finds out? | ¿Que pasara si alguien se entera?
🚫 | I prefer not to think about it | Prefiero no pensar en ello
❓ | What'll happen if they catch you? | ¿Que pasara si te cogen?
🚫 | They're not going to catch me | No me van a coger
👀 | What'll you do if they see you? | ¿Que harás si te ven?
❓ | Stop asking me so many questions | Deja de hacerme tantas preguntas
"""

    private const val list2 = """
1️⃣ | Who wants to be the first? | ¿Quien quiere ser el primero?
🔨 | The first to do what? | ¿El primero para hacer que?
👈 | You're not a member of this club | Tu no eres socio de este club
📍 | What are you doing here? | ¿Que haces aquí?
📌 | I want to become a member | Quiero hacerme socio
📜 | Do you know all the rules? | ¿Conoces todas las reglas?
🧠 | I'll learn them, don't worry | Las aprendere, no te preocupes
⚖️ | Do you agree with our laws? | ¿Estas de acuerdo con nuestras leyes?
🤝 | I promise that I'll obey them | Prometo que las acatare
📌 | Then you can be a member if that's what you want | Entonces puedes ser socio si eso es lo que quieres.
⏱️ | You know what I want | Tu sabes lo que quiero.
🤷 | I don't know anything about you | No se nada acerca de ti
⚡ | You'll learn a lot of things very soon | Aprenderas muchas cosas muy pronto
⭕ | There's nothing you can teach me | No hay nada que tu me puedas enseñar
✅ | Maybe not, but we'll see | Tal vez no, pero ya veremos
⏰ | How long does the class last? | ¿Cuanto tiempo dura la clase?
🍽️ | It lasts until lunch time | Dura hasta la hora de comer
⏰ | Why does it last so long? | ¿Por que dura tanto tiempo?
⏰ | Because dance classes always last long /a long time | Porque las clases de danza siempre duran mucho
📝 | Do you like the class? | ¿Te gusta la clase?
👍 | I like it more than anything else | Me gusta mas que ninguna otra cosa
👍 | Why do you like it so much? | ¿Por que te gusta tanto?
⭐ | Because I'm one of the best | Porque soy uno de los mejores
📝 | Can I be in your class too? | ¿Puedo estar en tu clase también?
📝 | Of course you can | Por supuesto que si
🔨 | What do I have to do? | ¿Que tengo que hacer?
🗣️ | Who do I have to talk to? | ¿Con quien tengo que hablar?
📍 | Where do I have to go? | ¿A donde tengo que ir?
💵 | How much do I have to pay? | ¿Cuanto tengo que pagar?
🚶 | How often do I have to come? | ¿Con que frecuencia tengo que venir?
🧑‍🏫 | Who'll be the teacher? | ¿Quien será el profesor?
👍 | Will she treat me like the rest? | ¿Me tratara ella como a los demás?
💃 | What she do if I don't dance well? | ¿Que hará ella si no bailo bien?
🌊 | I hope she's a patient person | Espero que sea una persona paciente
📝 | Do you think I'll like the class | ¿Crees que me gustará la clase?
👍 | I'm sure you'll like it | Estoy seguro de que te gustará
🛡️ | How can you be so sure? | ¿Como puedes estar tan seguro?
⏱️ | Because I know everything | Porque lo se todo.
✔️ | I hope you're right | Espero que tengas razón
♾️ | I'm almost always right | Casi siempre tengo razón
🏆 | I won't do it | No lo haré.
❓ | They won't take you if you don't ask them | No te llevaran si no se lo pides
🌙 | I won't get there until midnight | No llegare alli hasta la media noche
👍 | He's not going to like it | No le va a gustar (a él)
👍 | Why isn't he going to like it? | ¿Por qué no le va a gustar?
⏳ | He never likes anything | Nunca le gusta nada
❓ | Do you think he'll aprove it? | ¿Crees que lo aprobara?
🔨 | He'll do what he wants to do | Hara lo que quiera hacer
⚖️ | Will he make a decision soon? | ¿Tomará una decisión pronto?
♾️ | He always does what he wants | El siempre hace lo que quiere
"""

    private const val list3 = """
🏆 | They won't cancel it if you go | No lo cancelaran si tu vas
🏆 | I won't go if they include him | No iré si le incluyen a él
❓ | What do you have against him? | ¿Que tienes en contra de él?
🌐 | Nothing at all | Nada en absoluto
🤔 | Then, why aren't you going to invite him? | Entonces ¿por que no le vas a invitar?
🚫 | Because I don't want to | Porque no quiero
🏁 | When will the report be ready? | ¿Cuando estará listo el informe?
🏁 | It's already prepared | Ya está preparado
📤 | When are you going to send it? | ¿Cuando lo vas a mandar?
💬 | I'll send it when you tell me | Lo mandaré cuando Udme diga
📤 | You can send it whenever you want | Lo puedes mandar cuando quieras
👀 | I'll believe it when I see it | Lo creeré cuando lo vea
👍 | How often do you see things like that? | ¿Con que frecuencia ves cosas así?
🗣️ | What kind of things are you talking about? | ¿De que clase de cosas estás hablando?
🎯 | This will be our last chance | Esta será nuestra última oportunidad
⭕ | What will you do if nothing happens? | ¿Que harás si no pasa nada?
🔹 | Something has to happen | Algo tiene que pasar
♾️ | Something always happens when I go there | Siempre pasa algo cuando voy allí
🚶 | Who will meet me when I arrive? | ¿Quién me esperara cuando yo llegue?
🍂 | They'll get married this fall/autum | Se casarán en otoño
🤔 | Why won't they come sooner? | ¿Por que no vendrán mas pronto?
🚶 | I'll go if you come with me | Iré si vienes conmigo
🆘 | They'll do it if you help them | Lo harán si les ayudas
⏰ | I won't stay here any longer | No me quedaré aqui por mas tiempo
🍽️ | I'll eat mine if you eat yours | Comeré el mio si comes el tuyo
✍️ | Don't worry, I'll write it well | No te preocupes, lo escribiré bien
🏆 | Why won't he show you his tooth? | ¿Por que no te enseñaré él su diente?
🛑 | They won't stop looking at you | No dejarán de mirarte
👀 | Why will they look at me so much? | ¿Por que me miraran tanto?
📌 | Because that's what they do | Porque eso es lo que hacen
😟 | Don't worry, I'll be carefull | No te preocupes, tendré cuidado
🧹 | I'll wash it and you'll dry it | Yo lo lavare y tu lo secaras
1️⃣ | Who'll be the first to try? | ¿Quien será el primero en intentarlo?
👍 | Try it, you'll like it | Pruébalo, te gustará
⏱️ | I know I won't like it at all | Se que no me gustará en absoluto
🤔 | Why don't you try it? | ¿Por que no lo pruebas?
❓ | Because I don't want to, OK? | Porque no quiero ¿vale?
⏰ | It takes three hours to come | Se tardan tres horas en venir
📦 | How long does it take to go back? | ¿Cuanto se tarda en volver?
🔬 | It takes a little longer | Se tarda un poco más
📦 | Why does it take longer? | ¿Por que se tarda mas?
🐢 | Because you have to drive slowly | Porque tienes que conducir despacio
📅 | Is it windy today? | ¿Hace viento hoy?
⏳ | Not yet | Todavía no
🌅 | It's never windy in the morning | Nunca hace viento por la mañana
⏱️ | How do you know? | ¿Como lo sabes?
⏱️ | I'll tell you in a minute | Te lo diré en un minuto
⏳ | Are you waiting for me? | ¿Me estás esperando a mi?
🚶 | They'll come if you invite them | Vendran si les invitas
🏆 | I won't do it unless he's here | No lo hare a menos que el este aqui
"""

    private const val list4 = """
♾️ | I always have to do it | Siempre tengo que hacerlo
⏳ | They never have to call us | No tienen que llamarnos nunca
📌 | She often has to convince him | A menudo ella tiene que convencerle
📅 | You'll have to do it tomorrow | Tendrás que hacerlo mañana
💼 | Everyone has to work | Todo el mundo tiene que trabajar
▶️ | Who has to start the engine? | ¿Quien tiene que encender el motor?
⏱️ | He knows what he has to do | El sabe lo que tiene que hacer
🔨 | What does he have to do? | ¿Que tiene que hacer?
👀 | Why does he have to see her? | ¿Por que tiene que verla a ella?
💬 | He has to do what I tell him | El tiene que hacer lo que le digo
🤑 | He must be rich | El debe ser rico
🤔 | Why do you think that? | ¿Por que crees eso?
🚗 | Can't you see the cars he has? | ¿No puedes ver los coches que tiene?
🌊 | That doesn't mean he's rich | Eso no significa que sea rico
💬 | If you say so | Si tu lo dices...
🪵 | They must be on the first floor | Deben estar en la primera planta
❓ | What makes you think that? | ¿Que te hace creer eso?
🪟 | There's a light on in the window | Hay una luz encendida en la ventana
✅ | That doesn't mean there's someone there | Eso no quiere decir que haya alguien allí
📈 | He must have a lot of problems | El debe tener muchos problemas
🏠 | This must be the Pope's house | Esto debe ser la casa del Papa
🥶 | It must be cold outside | Debe de hacer frio fuera
🥶 | It's always cold in the winter | Siempre hace frio en invierno
😊 | He must be a pleasant person | El debe ser una persona agradable
👥 | These meetings always last long | Estas reuniones siempre duran mucho tiempo
⏰ | You never know how long they're going to last | Nunca sabes cuanto tiempo van a durar
⏰ | A speech usually last an hour | Un discurso suele durar una hora
📈 | Some speeches last much less | Algunos discursos duran mucho menos
⏰ | It takes a long time to learn a language | Se tarda mucho tiempo en aprender un idioma
⏰ | Why does it take so long? | ¿Por que se tarda tanto tiempo?
📚 | Because you have to study a lot | Porque tienes que estudiar mucho
📚 | Who likes to study? | ¿A quien le gusta estudiar?
👥 | A lot of people like to study | A mucha gente le gusta estudiar
👥 | They must be strange people | Debe de ser gente extraña
🧒 | They're like you and me | Son como tu y yo
❓ | Can I ask you a question? | ¿Puedo hacerte una pregunta?
📝 | Of course you can | Por supuesto que si
🦒 | Can you speak a little louder? | ¿Puedes hablar un poco mas alto?
👂 | Can't you hear me? | ¿No me puedes oír?
🧗 | I can hardly/barely understand what you're saying | Apenas puedo entender lo que dices
🔨 | You can do whatever you want | Puedes hacer lo que quieras
🔨 | He can do it better than I can | El lo puede hacer mejor que yo
🚫 | It can't be | No puede ser
📌 | I can cancel it if you want | Puedo cancelarlo si quieres
🔨 | Anyone can do that | Cualquiera puede hacer eso
🔨 | Well, I'm not going to do it! | !Pues yo no lo voy a hacer¡
📍 | I'll tell him where to put it | Le dire (a el) donde ponerlo
🏃 | I won't run that risk | No correre ese riesgo
😨 | Are you afraid? | ¿Tienes miedo?
😨 | I'm not afraid of anything or anyone | No tengo miedo de nada ni de nadie
"""

    private const val list5 = """
⏱️ | I know more than anyone here | Se mas que nadie aqui
👌 | This is a little easier to read | Esto es un poco mas fácil de leer
⚡ | He runs faster than me/Ido | El corre mas deprisa que yo
😨 | Don't be afraid | No tengas miedo
⭕ | I'm not going to do anything to you | No voy a hacerte nada
👍 | Why are you looking to me like that? | ¿Por que me estas mirando así?
❓ | Don't you trust me? | ¿No te fías de mi?
💬 | Don't you want to tell me anything? | ¿No quieres decirme nada?
🚗 | Who doesn't have a car? | ¿Quien no tiene coche?
⏱️ | Does anyone know where Pepe is? | ¿Alguien sabe donde esta Pepe?
⏱️ | They don't know where I am | No saben donde estoy
🌍 | Everyone knows there isn't anyone there | Todo el mundo sabe que no hay nadie allí
⭕ | Nobody tells me anything | Nadie me dice nada
👥 | I don't agree with them | No estoy de acuerdo con ellos
💭 | Do they know what her opinion is? | ¿Saben cual es su opinión?
⏳ | She never tells anyone anything | Ella nunca dice nada a nadie
🗣️ | Why don't you talk to her? | ¿Por que no hablas con ella?
🗣️ | I'll talk to her when I see her | Le hablare cuando la vea
👀 | How often do you see her? | ¿Con que frecuencia la ves?
🧗 | I hardly ever see her | Casi nunca la veo
👥 | Why don't you agree with him? | ¿Por que no estas de acuerdo con el?
⏳ | We never agree on anything | Nunca estamos de acuerdo en nada
⏱️ | He doesn't know when the party is | El no sabe cuando es la fiesta
⏱️ | Doesn't anyone know where Tom is? | ¿No sabe nadie donde esta Tom?
⏱️ | I know what he does | Yo se lo que el hace
🤔 | I also know why he does it | Se tambien por que lo hace
🤔 | Why does he do it? | ¿Por que lo hace el?
🎁 | When will they give it to me? | ¿Cuando me lo darán?
🎁 | When will they give you what? | ¿Cuando te darán que?
🎁 | Give me what they owe me | Darme lo que me deben
❓ | What do they owe you? | ¿Que te deben?
💵 | They owe me a lot of money | Me deben mucho dinero
💳 | I'm afraid they're not going to pay you | Me temo que no te lo vayan a pagar
❓ | What makes you think that? | ¿Que te hace creer eso?
⏱️ | Because I know they don't have a penny | Porque se que no tienen ni un duro
⏱️ | Who knows that besides you? | ¿Quien sabe eso además de ti?
👥 | Quite a few people | Bastante gente
🧾 | Doesn't she realise where she is? | ¿No se da ella cuenta de donde esta?
🧾 | Do you realise what you're doing? | ¿Te das cuenta de lo que haces?
👌 | Isn't this easier than that? | ¿No es esto mas fácil que aquello?
🧑‍🤝‍🧑 | She's prettier than her sister | Ella es mas bonita que su hermana
🦒 | They're taller than the Germans | Son mas altos que los alemanes
🧗 | This is more complicated | Esto es mas complicado
😟 | Don't worry, I won't be afraid | No te preocupes, no tendre miedo
😨 | What are you afraid of? | ¿De que tienes miedo?
👤 | Who is going to ask her for it? | ¿Quien se lo va a pedir?
❓ | He's afraid to ask me for it | El tiene miedo de pedírmelo
⚠️ | You have to be more careful | Tienes que tener mas cuidado
👔 | Why do you wear shirts like that? | ¿Por que llevas camisas como esas?
👍 | Don't be like them | No seas como ellos
"""

    private const val list6 = """
💬 | Do what I say, not what I do | Haz lo que digo, no lo que hago
🤔 | Why do I have to do what you say? | ¿Por que tengo que hacer lo que dices?
⏱️ | Because I know what's right | Porque se lo que esta bien
🚶 | Why don't you walk there? | ¿Por que no vas allí andando?
🔬 | Don't you think it's a little far? | ¿No crees que esta un poco lejos?
🎁 | Maybe for you, but not for me | Quizas para ti, pero no para mi
📈 | You have a lot of practice | Tienes mucha practica
🔨 | What are you going to do? | ¿Que vas a hacer?
🧠 | I'm going to learn how to play the piano | Voy a aprender a tocar el piano
🤔 | Why are you interested in that? | ¿Por que estas interesado en esto?
🎵 | Because music interests me | Porque la musica me interesa
⏱️ | What else do you know how to do? | ¿Que más sabes hacer?
🕊️ | Leave me alone | Dejame en paz
📆 | Prices will fall at the end of the year | Los precios caeran a finales de año
🍂 | Why do you think they'll fall | ¿Por que crees que caeran?
🍂 | Because they have to fall | Porque tienen que caer
⭕ | Nobody's buying anything | Nadie esta comprando nada
⭕ | Why isn't anyone buying anything? | ¿Por que nadie esta comprando nada?
💵 | Because nobody has any money | Porque nadie tiene dinero
⏱️ | Do you want to know what I think? | ¿Quieres saber lo que yo pienso?
👥 | I think people spend too much | Creo que la gente gasta demasiado
❓ | Do you include yourself? | ¿Te incluyes a ti mismo?
👍 | I'm not like the rest of you | No soy como el resto de vosotros
👨 | I consider myself a special man | Me considero un hombre especial
❓ | What makes you so special? | ¿Que te hace tan especial?
👨 | Do you know many men with wings? | ¿Conoces a muchos hombres con alas?
💬 | Do you mean you have wings? | ¿Quieres decir que tienes alas?
2️⃣ | I have two to be exact | Tengo dos para ser mas exacto
👥 | What do you do with them? | ¿Que haces con ellas?
🧒 | What do you think wings are for? | ¿Para que crees que son las alas?
🧒 | They're for flying | Son para volar
📅 | You seem more intelligent every day | Pareces mas inteligente cada dia
✈️ | Then you can fly | Entonces puedes volar
✔️ | That's right | Asi es
🤷 | I don't know what to say | No se que decir
🤝 | Promise me you won't tell anyone | Prometeme que no lo diras a nadie
🔤 | All right, you have my word | De acuerdo, tienes mi palabra
✈️ | Let me see you fly | Dejame verte volar
✈️ | I never let anyone see me fly | Nunca dejo a nadie verme volar
👥 | How many people know you have wings? | ¿Cuanta gente sabe que tienes alas?
⏱️ | Only a few know (about it) | Solo unos pocos lo saben
👥 | I don't trust people like him | No me fio de gente como el
❓ | What do you have against him? | ¿Que tienes en contra de el?
📝 | I'm going to teach him a lesson | Le voy a dar una leccion
🔨 | What are you going to do to him? | ¿Que le vas a hacer?
⏳ | I'm going to do something that he'll never forget | Voy a hacer algo que él nunca olvidara
❓ | Don't ask me any more questions | No me hagas mas preguntas
🛑 | I'll leave when I finish | Me marchare cuando termine
💬 | They leave when I tell them | Se iran cuando yo se lo diga
📌 | I need to think about it | Necesito pensarlo
"""

    private const val list7 = """
👀 | Why won't they look for him? | ¿Por qué no le buscarán?
😟 | Don't worry, I won't lose it | No te preocupes, no lo perderé
⏳ | I'll never do that again | Nunca volveré a hacer eso
⏳ | He'll never come back if I'm here | Él nunca volverá si yo estoy aquí
♾️ | I'll always be here if you need me | Siempre estaré aquí si me necesitas
🌧️ | It won't rain until next week | No lloverá hasta la semana que viene
⏱️ | How do you know so many things? | ¿Cómo sabes tantas cosas?
💪 | He won't make that kind of effort | Él no hará ese tipo de esfuerzo
🏷️ | I won't give them your name unless they ask me for it | No les daré tu nombre a no ser que me lo pidan
🇬🇧 | You won't speak English if you don't study it | No hablarás inglés si no lo estudias
🥱 | I'm tired of studying | Estoy cansado de estudiar
1️⃣ | You're not the only one | No eres el único
🧒 | I won't teach children like him | No enseñaré a niños como él
📌 | I refuse | Me niego
💼 | Then you can't work here | Entonces, no puedes trabajar aquí
🤔 | Why can't I? | ¿Por qué no puedo?
👉 | Because most children are like that child | Porque la mayoría de los niños son como ese niño
📈 | This doesn't last long | Esto no dura mucho
🏆 | I won't do it for anyone but him | No lo haré para nadie salvo para él
🌙 | It's getting dark | Está oscureciendo
🧗 | Things are getting difficult | Las cosas están poniéndose difíciles
🧗 | It's too hard for me | Es demasiado duro para mí
💵 | He doesn't have enough money | Él no tiene suficiente dinero
🚫 | He's not strong enough | Él no es lo suficientemente fuerte
✅ | That's enough | Basta ya
👥 | What's the matter with you? | ¿Qué te pasa?
📢 | Why doesn't he explain it to us? | ¿Por qué no nos lo explica?
♾️ | Why does he always have to shout? | ¿Por qué tiene siempre que gritar?
📅 | I'll be back in a couple of days | Estaré de vuelta en un par de días
✔️ | I'll be right back | Enseguida vuelvo
⏳ | I'm never going to offer it to her | Nunca se lo voy a ofrecer ( a ella)
⏰ | What time will they get here? | ¿A qué hora llegarán aquí?
💡 | I have no idea | No tengo ni idea
🏆 | They won't jump over the fence | No saltarán la valla
🦒 | It's too high for them | Es demasiado alta para ellos
🌊 | I don't think it's high enough | Yo no creo que sea lo bastante alta
💭 | I'm not asking your opinion | No estoy pidiendo tu opinión
❗ | Get out of here! | !Lárgate de aquí¡
👍 | How can you do a thing like that? | ¿Cómo puedes hacer una cosa así?
🚫 | Because I have no choice | Porque no tengo más remedio
🎯 | When are you going to choose it? | ¿Cuándo lo vas a elegir?
🧒 | They're very similar, aren't they? | Son muy parecidos, ¿no?
👈 | This one's gold | Este es de oro
📦 | I'll take the silver one | Me quedaré con el de plata
🏆 | I won't check it if that's what you want | No lo verificaré si eso es lo que quieres
🗓️ | It'll take me about three months | Tardaré unos tres meses
🪵 | It usually takes me an hour | Suelo tardar una hora
👨 | You're a very brave man | Ud.es un hombre muy valiente
🇬🇧 | It takes a long time to learn English | Se tarda mucho en aprender inglés
✅ | It won't take me long, you'll see | Yo no tardaré mucho, ya verás
"""

    private const val list8 = """
📅 | I was with them yesterday | Estuve con ellos ayer
📍 | Where were you? | ¿Dónde estuviste?
📍 | Where were you born? | ¿Dónde naciste?
⏰ | What were you doing at ten o'clock? | ¿Quá hacías a las diez?
⭕ | I wasn't doing anything | Yo no hacía nada
💭 | What was his opinion? | ¿Cuál fue su opinión?
📍 | He wasn't there | Él no estuvo allí
🌧️ | It wasn't raining | No estaba lloviendo
👥 | There was a meeting here | Hubo una reunión aquí
📅 | Was there a strike yesterday? | ¿Hubo huelga ayer?
🌐 | There were strikes everywhere | Hubo huelgas en todas partes
🪧 | Wasn't there a strike here? | ¿No hubo una huelga aquí?
⏳ | There are never any strikes here | Nunca hay huelgas aquí
📆 | Everything was the same last year | Todo fue igual el año pasado
👩 | There was a woman here yeterday, wasn't there? | Hubo una mujer aquí ayer, ¿no?
👌 | It was quite easy | Fue bastante fácil
✅ | He was a great leader | Él era un gran lider
📌 | The truck was in the garage | El camión estaba en el garaje
📍 | Where was it? | ¿Dónde estaba?
💬 | I said it was in the garage | Dije que estaba en el garaje
💬 | What did you say? | ¿Qué dijiste?
👂 | Can't you hear me? | ¿No me puedes oir?
🗣️ | Are you talking to me? | ¿Me estas hablando a mí?
❓ | What were you saying? | ¿Qué decías?
⭕ | I wasn't saying anything | Yo no decía nada
💬 | But didn't you say something about Tom? | ¿Pero no dijiste algo acerca de Tom?
💬 | I didn't say anything at all | No dije nada en absoluto
💬 | What did you tell him? | ¿Qué le dijiste?
💬 | I told him everything | Le dije todo
💬 | Who told you that? | ¿Quién te dijo eso?
💬 | Who said that? | ¿Quién dijo eso?
📍 | Did you go there? | ¿Fuiste allí?
🚶 | Why didn't you come here? | ¿Por qué no viniste aquí?
🔹 | Did anyone go with you? | ¿Fue alguien contigo?
👤 | Who were you with? | ¿Con quién estuviste?
👨 | Did you see Pepe's parents? | ¿Viste a los padres de Pepe?
👀 | What were they doing when you saw them? | ¿Qué hacían cuando les viste?
⏱️ | Did they know you were looking at them? | ¿Sabían que les estabas mirando?
👀 | Did they see you? | ¿Te vieron a tí?
👀 | What did you do when they saw you? | ¿Qué hiciste cuando te vieron?
💵 | They must have a lot of money | Ellos deben de tener mucho dinero
📌 | I'm sorry but I have to go | Lo siento pero me tengo que ir
🤔 | Why do you have to go? | ¿Por qué te tienes que ir?
🔨 | I have some things to do | Tengo cosas que hacer
🤝 | Don't you want to be with me? | ¿No quieres estar conmigo?
👍 | I'd like to but I can't | Me gustaría, pero no puedo
👀 | When will I see you again? | ¿Cuándo volveré a verte?
🌅 | When I come back tomorrow morning | Cuando vuelva mañana por la mañana
🔨 | Can you do me a favour? | ¿Me puedes hacer un favor?
📌 | Whatever you want | Lo que quieras
"""

    private const val list9 = """
👀 | Who saw him? | ¿Quién le vió?
❓ | Who did it? | ¿Quién lo hizo?
❓ | Who followed him? | ¿Quién le siguió?
🧃 | Who played with it? | ¿Quién jugo con ello?
❓ | Who attended the concert? | ¿Quién asistió al concierto?
🚪 | Who left this here? | ¿Quién dejó esto aquí?
💥 | Who broke the lamp? | ¿Quién rompió la lámpara?
🌙 | Who tried to call me last night? | ¿Quién intento llamarme anoche?
❓ | What happened? | ¿Qué pasó?
👥 | How many people were there whe it happened? | ¿Cuánta gente estaba allí cuando ocurrió?
📞 | Why didn't anyone call me? | ¿Por qué no me llamó nadie?
❓ | Did you kill him? | ¿Le mataste tú?
🧑‍🤝‍🧑 | Did your brother see her? | ¿La vió tu hermano?
👀 | What was she doing when he saw her? | ¿Qué hacía ella cuando él la vio?
🗣️ | Why don't you want to answer me? | ¿Por qué no quieres contestarme?
🚪 | Why did you leave this here? | ¿Por qué dejaste esto aquí?
📦 | When did you take her there? | ¿Cuándo la llevaste allí?
👥 | Did she want to go with you? | ¿Quería ir ella contigo?
👀 | When did you see the fight? | ¿Cuándo viste la pelea?
▶️ | How did it start? | ¿Cómo empezó?
▶️ | What were you doing when it started? | ¿Que hacías cuando empezó?
❓ | How did it end? | ¿Cómo terminó?
🏆 | Who won the fight? | ¿Quién ganó la pelea?
👨 | What happened to the other man? | ¿Qué le pasó al otro hombre?
🏨 | Did they take him to the hotel? | ¿Le llevaron al hotel?
📦 | Why did they take him there? | ¿Por qué le llevaron allí?
🧑‍⚕️ | Did a doctor see him? | ¿Le vio un médico?
🛑 | Why didn't you try to stop him? | ¿Por qué no intentaste pararle?
🔨 | Who decided to do that? | ¿Quién decidió hacer eso?
💡 | Whose idea was that? | ¿De quién fue esa idea?
❓ | And then what happened? | ¿Y después qué pasó?
🍴 | Whose knife was it? | ¿De quién era el cuchillo?
❓ | Did it belong to Morgan? | ¿Pertenecía a Morgan?
🛡️ | Are you sure he was drunk? | ¿Estás seguro de que estaba borracho?
🤔 | Why do you think he did it? | ¿Por qué crees que el lo hizo?
🔹 | Did he have anything against her? | ¿Tenía el algo contra ella?
😨 | Was he afraid of her? | ¿La tenía él miedo?
🍴 | What did he do with the knife? | ¿Qué hizo él con el cuchillo?
❓ | Who kill him? | ¿Quién le mató?
🍴 | Who gave him the knife? | ¿Quién le dio el cuchillo?
🎁 | Why did you give it to him? | ¿Por qué se lo diste?
📍 | Where did you get that gun? | ¿Donde conseguiste esa pistola?
🤔 | Why didn't you use it? | ¿Por qué no la usaste?
⏱️ | Did he know you had it? | ¿Sabía él que la tenías?
👀 | What did he do when he saw it? | ¿Qué hizo cuando la vio?
📦 | Did he try to take it from you? | ¿Intento quitartela?
👉 | What was your first reaction? | ¿Cuál fue tu primera reacción?
😊 | Was he glad to see you? | ¿Se alegraba él de verte?
😰 | Why was he so nervous? | ¿Por qué estaba el tan nervioso?
❓ | Thank you very much for answering my questions | Muchas gracias por contestar a mis preguntas
"""

    private const val list10 = """
📦 | Nobody brought him here | Nadie le trajo aquí
❓ | Who took him to the zoo? | ¿Quién le llevó al zoo?
📖 | No one / nobody read it to me | Nadie me lo leyó
🌐 | We all thoght they were here | Todos pensábamos que estaban aquí
🚶 | Most of them came with him | La mayoría de ellos vinieron con él
👗 | Who made this dress? | ¿Quién hizo ese vestido?
⏰ | I waited for him for an hour | La esperé durante una hora
❓ | Who did you save? | ¿A quién salvaste?
📤 | Where did they send it? | ¿A donde lo mandaron?
📤 | Who did you send it to? | ¿A quién lo mandaste?
📤 | Why didn't you send me a copy? | ¿Por qué no me mandaste una copia?
❓ | Who covered it? | ¿Quién lo cubrió?
📌 | They lied to me | Me mintieron
🌅 | I found out this morning | Me enteré esta mañana
👈 | Who painted this ceilling? | ¿Quién pintó este techo?
👉 | Did you smoke in front of him? | ¿Fumaste delante de él?
💬 | Did he say anything? | ¿Dijo él algo?
💧 | They push me into the water | Me empujaron al agua
📌 | They used me | Me utilizaron
🌇 | They got here late | Llegaron aquí tarde
🌧️ | It rained all day | Llovió todo el día
❄️ | It didn't snow last winter | No nevó el invierno pasado
📞 | Who invented the telephone? | ¿Quién inventó el teléfono?
❓ | Did you invent it? | ¿Lo inventaste tú?
❓ | Who stole the picture? | ¿Quién robó el cuadro?
🤔 | Why did you steal it? | ¿Por qué lo robaste?
🍲 | I had dinner with her | Cené con ella
🍲 | They had dinner out | Cenaron fuera
✋ | I ate it with my hands | Lo comí con las manos
🐢 | I typed it very slowly | Lo mecanografié muy despacio
⏰ | It took me an hour | Tardé una hora
⏰ | I finished an hour later | Terminé una hora mas tarde
▶️ | He began to shout at me | Él empezó a gritarme
🌅 | I got up early | Me levanté pronto
🌙 | I got dressed in the dark | Me vestí en la oscuridad
👩 | I had breakfast with my wife | Desayuné con mi mujer
⏰ | I left home at o'clock | Salí de casa a las
🚕 | I looked for a taxi | Busqué un taxi
🚌 | I decided to catch a bus | Decidí coger un autobus
🏢 | I got to the office late | Llegué tarde a la oficina
🧑‍💼 | My boss was waiting for me | Mi jefe me estaba esperando
🌅 | He said good morning to me | Me dijo buenos dias
🏢 | He went back to his office | Volvió a su despacho
🍽️ | He invited me to lunch | Me invitó a comer
🧾 | He paid the bill | Pagó la cuenta
🐘 | He left a big tip | Dejó una buena propina
🗣️ | He spoke to me about my future | Me habló de mi futuro
🎁 | He gave me advice | Me dio consejos
📌 | He changed the subject | Cambió de tema
🚪 | He got up and left | Se levantó y se fue
"""

    private const val list11 = """
🥵 | They won't try it if it's hot | No lo probarán si está caliente
📅 | There are very few days like today | Hay muy pocos dias como hoy
🚫 | It's incredible, I can't believe it | Es increible, no me lo puedo creer
❓ | What'll he do if we accuse him? | ¿Qué hará él si lo acusamos?
🚪 | Leave that to me | Déjame eso a mí
⏱️ | I know what I'm doing | Sé lo que hago
🇬🇧 | I met her on a trip to London | La conocí en un viaje a Londres
🇬🇧 | What were you doing on a trip to England? | ¿Qué hacías en un viaje a Inglaterra?
🇬🇧 | I wanted to improve my English | Yo queía mejorar mi inglés
❓ | Did you succeed? | ¿Tuvíste éxito?
🧠 | I learned very litle | Aprendí muy poco
🗓️ | But you were there for months! | ¡Pero estuviste allí dos meses!
👥 | I spent them with her | Los pasé con ella
🇪🇸 | We spoke Spanish the whole time | Hablamos español todo el tiempo
💵 | Did you spend all your money? | ¿Gastaste todo tu dinero?
📈 | Much more than you think | Mucho más de lo que tú crees
🤑 | They say you're quit rich | Dicen que eres bastante rico
👂 | Don't trust everything you hear | No te fies de todo lo que oyes
✔️ | But they're right, aren't they? | Pero tiene razón, ¿no?
🚫 | Maybe they are, maybe they aren't | Quizás sí, quizás no
🚪 | Somebody left something somewhere | Alguien dejó algo en alguna parte
🚪 | Nobody left anything anywhere | Nadie dejó nada en ninguna parte
📌 | The party's over | Se acabó la fiesta
👌 | Things are never as easy as they seem | Las cosas nunca son tan fáciles como parecen
❌ | It was so bad that I left | Fue tan malo que me marché
🦒 | He's a lot taller than you think | Él es mucho más alto de lo que crees
👨 | I'm stronger than the strongest man in the world | Soy mas fuerte que el hombre más fuerte del mundo
🍽️ | They were so good that I ate three | Estaban tan buenos que me comí tres
🍽️ | Why did you eat so many? | ¿Por qué comiste tantos?
👎 | It's much worse than you think | Es mucho peor de lo que crees
💪 | You'll have to make an effort | Tendrás que hacer un esfuerzo
⏳ | I'll never do it again | Nunca volveré a hacerlo
🏆 | You'll be here, won't you? | Estarás aquí, ¿no?
🤕 | It hurts much more than I thought | Duele mucho más de lo que pensé
🤕 | Where does it hurt? | ¿Donde te duele?
🤕 | My shoulder hurts | Me duele el hombro
📅 | There will be a strike tomorrow | Habrá una huelga mañana
👧 | There will be at least girls | Habrá por lo menos diez chicas
📈 | How many will there be? | ¿Cuantas habrá?
📉 | There will be quite a few | Habrá bastantes
💬 | What do you mean by "quite a few"? | ¿Que quieres decir con "bastantes"?
📌 | More than you can imagine | Más de lo que te puedes imaginar
🎁 | When you get there, give this to him | Cuando llegues allí, dale esto
🤷 | I don't know why you like her so much? | No sé por qué ella te gusta tanto
👧 | There are a lot of girls like her | Hay muchas chicas como ella
🧒 | Very few are as sharp as she is | Muy pocas son tan agudas como ella
👩 | That's what I look for in women | Eso es lo que busco en las mujeres
👥 | There are few people as rich as he is | Hay poca gente tan rica como él
👍 | I'd like to have another chance | Me gustaría tener otra oportunidad
🔍 | We'll have to find another way | Tendremos que encontar otra manera
"""

    private const val list12 = """
📞 | If you ever need anything, call me | Si alguna vez necesitas algo, llamame
🔹 | Does he ever try to influence you? | ¿Intenta él alguna vez influenciarte?
🤕 | Does your finger ever hurt? | ¿Te duele alguna vez el dedo?
📌 | They ussualy do it themselves | Suelen hacerlo ellos mismos
💼 | Working is good for your health | El trabajar es bueno para la salud
💵 | How much do I owe you? | ¿Cuanto te debo?
📌 | I owe him a favour | Le debo un favor
✨ | It was really interesting | Fue realmente interesante
❓ | When did he die? | ¿Cuándo murió él?
❓ | How did he die? | ¿Cómo murió?
👢 | He died with his boots on | Murió con las botas puestas
1️⃣ | I think he took them off first | Creo que se las quitó primero
💬 | Who told you that? | ¿Quién te dijo eso?
💬 | I don't remember anything I said | No me acuerdo de nada de lo que dije
💳 | Remind me to pay them a visit | Recuérdame hacerles una visita
🧾 | I didn't realise you were here | No me dí cuenta de que estabas aquí
📤 | Who sent you? | ¿Quién te mandó?
🇮🇹 | Do you know anyone who lives in Rome? | ¿Conoces a alguien que viva en Roma?
⏱️ | I know who you are | Sé quien eres
⏰ | I know what time is it | Sé qué hora es
👥 | I'm not afraid of people like him | No tengo miedo de gente como él
🍽️ | Make sure they're eating | Asegúrate de que están comiendo
🚭 | I'm looking for someone who doesn't smoke | Estoy buscando a alguien que no fume
♾️ | He always agrees with me | El siempre está de acuerdo conmigo
📦 | It doesn't take long | No se tarda mucho
🚗 | I drive to work / go by car | Voy al trabajo en coche
🚪 | Who's at the door? | ¿Quién está en la puerta?
👨 | You look like your father | Te pareces a tu padre (fisicamente)
👍 | I tried it but I didn't like it | Lo probé pero no me gustó
👍 | Why didn't you like it? | ¿Por qué no te gustó?
🚭 | What I need is a good cigar | Lo que necesito es un buen puro
📍 | Where were you born? | ¿Dónde naciste?
📈 | He's much older than I am | Él es mucho mayor que yo
🏛️ | He's not as old as you think | Él no es tan mayor como tu crees
📆 | How old do you think he is? | ¿Cuántos años crees que tiene?
📆 | He looks about years old | Parece tener unos años
📌 | I'm sorry, but I had to do it | Lo siento, pero tuve que hacerlo
🚫 | I had no choice | No tuve mas remedio
💡 | I hope you understand why I did it | Espero que entiendas por qué lo hice
🚫 | It wasn't my fault | No fue culpa mía
💬 | I told you I was going to do it | Te dije que lo iba a hacer
🛑 | Why didn't you try to stop me? | ¿Por qué no intentaste pararme?
🛑 | Nobody did anything to stop me | Nadie hizo nada para pararme
🤝 | I lost the only friend I had | Perdí el único amigo que tenía
📈 | I'm very sorry | Lo siento mucho
📌 | I'm going to apologise to him | Le voy a pedir perdón
📌 | If you throw that stone, I'll punish you | Si tiras esa piedra, te castigaré
💬 | Do you believe everything they tell you? | ¿Crees todo lo que te dicen?
🧍 | I only believe what you tell me | Solo creo lo que tu me dices
👂 | That's what I like to hear | Eso es lo que me gusta oir
"""

    private const val list13 = """
🐘 | It was much bigger than I thought | Fue mucho mas grande de lo que crei
👀 | What were you doing when he saw you? | ¿Qué hacías cuando él te vió?
🚫 | I'll do it if you don't mind | Lo haré yo si no te importa
🌐 | I don't mind at all | No me importa en absoluto
📌 | I think so | Creo que sí
🚫 | I don't think so | Creo que no
❓ | What was that? | ¿Qué era eso?
🧠 | I thought you knew | Creí que sabías
⚠️ | They'll have to be very careful | Tendrán que tener mucho cuidado
🧩 | There weren't any problems | No hubo problemas
1️⃣ | It happened once | Ocurrió una vez
🌙 | There will be a meeting tonight | Habrá una reunión esta noche
❓ | What will it be about? | ¿De qué tratará?
📖 | What's the book about? | ¿De qué trata el libro?
📖 | You are too young to read it | Eres demasiado joven para leerlo
🔨 | You can do whatever you want | Puedes hacer lo que quieras
🚫 | I don't care /It doesn't matter | Me da igual
👋 | How was the concert | ¿Qué tal el concierto?
👀 | Who did you see there? | ¿A quién viste allí?
👥 | Were there a lot of people? | ¿Hubo mucha gente?
📌 | It was crowded | Estaba abarrotado
📌 | Pleased to meet you | Encantado de conocerla
💬 | Don't open it untill I tell you | No lo abras hasta que yo te diga
🗣️ | I'm still waiting for an answer | Todavía estoy esperando una respuesta
📍 | Didn't tell you I was here? | ¿No te dijeron que yo estaba aquí?
📈 | He was quite drunk | Él estaba bastante borracho
⏱️ | He didn't know who I was | Él no sabía quien era yo
📌 | He thought we were ghosts | Creyó que éramos fantasmas
📉 | He lost everything he had | Él perdió todo lo que tenía
💵 | How much did he have? | ¿Cuánto tenía?
📌 | Enough to fill a barrel | Lo suficiente para llenar un barril
⏰ | Don't waste time | No pierdas tiempo
📅 | I feld pretty bad yesterday | Me encontré bastante mal ayer
❌ | I hope you didn't do anything bad | Espero que no hicieras nada malo
❌ | I never do bad things | Nunca hago cosas malas
👍 | I tried it but I didn't like it | Lo probé pero no me gusto
🧗 | It must not be very difficult | No debe de ser muy dificil
🍲 | I took her out to dinner | La llevé a cenar
📌 | They took me to the cementery | Me llevaron al cementerio
1️⃣ | Once is enough | Una vez es suficiente
1️⃣ | It was a wonderful experience | Fue una experiencia maravillosa
❓ | What did you do? | ¿Qué hiciste?
📍 | Were there any storms? | ¿Hubo tormentas?
❓ | How's Pepe (doing)? | ¿Como esta Pepe?
👍 | What's Pepe like? | ¿Como es Pepe?
🦒 | He's tall, dark and handsome | Es alto, moreno y guapo
🙈 | He's short, fat and ugly | Es bajo, gordo y feo
💵 | Maybe, but do you know how much he earns? | Quizás, pero ¿sabes cuanto gana?
📈 | Much more than we do, that's for sure | Mucho mas que nosotros, eso si
👓 | He was wearing dark glasses | Él llevaba unas gafas oscuras
"""

    private const val list14 = """
❓ | Who stepped on it? | ¿Quién lo pisó?
⚡ | The plant grew quickly | La planta creció rápidamente
❓ | Who planted it? | ¿Quién la plantó?
👍 | I slept like a log | Dormí como un tronco
💊 | They run to the pharmacy | Corrieron a la farmacia
🤥 | Why did you lie to him? | ¿Por qué le mentiste?
🧳 | They travel all over Europe | Viajaron por toda Europa
🚆 | I missed the train | Perdí el tren
♾️ | He always loved to dance | Siempre le encantaba bailar
🌙 | It rained all night | Llovió toda la noche
🚪 | They let us leave | Nos dejaron marchar
⏰ | How long did it last? | ¿Cuánto tiempo duró?
⏰ | It last at least two hours | Duró al menos dos horas
🤷 | I don't know why he talks so much | No sé por qué él habla tanto
🦶 | I signed it at the bottom | Lo firmé al pie
📌 | They broke the record | Batieron el record
🆘 | Who helped them? | ¿Quién les ayudó?
🚫 | It doesn't happened often | No ocurre a menudo
⏰ | It took them an hour | Tardaron una hora
🛏️ | Who made the bed? | ¿Quién hizo la cama?
🪑 | Who set /laid the table? | ¿Quién puso la mesa?
🔥 | What caused the fire? | ¿Qué causó el incendio?
▶️ | How did it start? | ¿Cómo empezó?
💧 | Who brought the water? | ¿Quién trajo el agua?
📍 | Who got here first? | ¿Quién llegó aquí primero?
👀 | They didn't want to see him | No querían verle
🤕 | I had a headache | Tuve un dolor de cabeza
🐘 | There was a big explsion | Hubo una gran explosión
📍 | There were several explosions | Hubo varias explosiones
📌 | He escaped through the hole | Él se escapó por el agujero
🏆 | They won the bet | Ganaron la apuesta
🌍 | They fought against everyone | Lucharon contra todo el mundo
🌙 | It got dark soon | Oscureció pronto
🎁 | They didn't give it to me | No me lo dieron
📌 | The speech was recorded | El discurso fue grabado
❓ | Whose arrow was it? | ¿De quién era la flecha?
❓ | It was yours, wasn't it? | Era tuyo, ¿no?
🥱 | I felt tired | Me encontraba cansado
🧗 | Those yaers were difficult | Aquellos años fueron difíciles
💰 | I paid for it in dollars | Lo pagué en dólares
⭕ | Nothing happened | No pasó nada
📝 | I fell asleep in the class | Me dormí en clase
💡 | Whose idea was it? | ¿De quién fue la idea?
😢 | He was sad | El estaba triste
📥 | Where did they put it? | ¿Donde lo pusieron?
📈 | There were too many | Hubo demasiados
❄️ | It was snowing when I left | Nevaba cuando yo me fui
🥵 | It was very hot | Hacía mucho calor
😋 | They were hungrier than I was | Tenían mas hambre que yo
📆 | He was years old when I was born | Él tenía cuando yo nací
"""

    private const val list15 = """
🏷️ | I think they're going to sell it | Creo que van a venderlo
🏷️ | I thought they were going to sell it | Creí que iban a venderlo
🚗 | I thought you were coming by car | Creí que venías en coche
🎁 | I kept /saved it for you | Lo guardé para tí
🤔 | Why did he want to do that? | ¿Por qué quería él hacer eso?
📈 | It meant a lot to me | Significaba mucho para mí
📝 | The class was so boring taht I felt asleep | La clase fue tan aburrida que me dormí
🏁 | They're not as smart as you are | No son tan listos como tú
💡 | Whose idea was that | ¿De quién fue esa idea?
📌 | It was my fault | Fue culpa mía
👀 | What was he doing when you saw him? | ¿Que hacía él cuando le viste?
❓ | Who tried to steal it? | ¿Quién intentó robarlo?
🚫 | It wasn't stolen | No fue robado
🤔 | Why wasn't I informed about it? | ¿Por qué no fuí informado de ello?
🚫 | We thought you weren't interested | Creímos que no estabas interesado
⏳ | Don't ever do that again | No volvais a hacer eso nunca
💳 | He didn't pay any attention to me | Él no me prestó atención
📈 | They're much happier than before | Están mucho mas contentos que antes
⏳ | I still feel a little weak | Todavía me encuentro un poco debil
✅ | Don't worry, you'll get better | Descuida, ya te pondrás mejor
❓ | What makes you think that? | ¿Qué te hace pensar eso?
👉 | I have a feeling | Tengo un presentimiento
✔️ | I hope you're right | Espero que tengas razón
🧗 | I'm hardly ever wrong | Casi nunca me equivoco
📈 | There's not as much as before | No hay tantos como antes
📅 | How much was there yesterday? | ¿Cuánto hubo ayer?
📅 | Much more than there is today | Mucho mas de lo que hay hoy
🚫 | They failed to meet the deadline | No lograron cumplir el plazo
📰 | Where can I buy a newspaper? | ¿Donde puedo comprar un periódico?
📍 | Where can I get a haircut? | ¿Donde puedo cortarme el pelo?
👦 | It's so easy, that even a little boy can do it | Es tan fácil, que incluso un niño pequeño lo puede hacer
👌 | Maybe, but it doesn't look easy | A lo mejor, pero no parece fácil
📌 | I'll do it if you do it too | Lo haré si lo haces tú también
📄 | Where can I get paper like that? | ¿Donde puedo conseguir papel así?
💡 | I have no idea | No tengo ni idea
👌 | It must be easy to work with him | Debe ser fácil trabajar con el
👌 | It's not as easy as you think | No es tan fácil como crees
💼 | It's easier than working with her | Es mas facil que trabajar con ella
🧗 | Nothing's as difficult as that | Nada es tan dificil como eso
📌 | I agree | Estoy de acuerdo
👔 | Take it out of my shirt pocket | Sácalo del bolsillo de mi camisa
💬 | What are you going to say? | ¿Qué ibas a decir?
💬 | Were you going to say something? | ¿Ibas a decir algo?
💬 | I thought you were going to tell me something | Creía que ibas a decirme algo...
🛑 | Where were you going when they stopped you? | ¿A donde ibas cuando te pararon?
❓ | What was your impression of him? | ¿Cual fue tu impresión de él?
🦒 | He was taller than I expected | Era mas alto de lo que yo esperaba
🦒 | He's much taller than you | Él es mucho más alto que tú
🌱 | He's quit a bit younger too | Es bastante más joven también
🌱 | He looks younger than he is | Parece más joven de lo que es
"""

    private const val list16 = """
💬 | What did the wolf say to her when he saw her? | ¿Qué le dijo el lobo cuando la vio?
📍 | He asked her where she was going | La preguntó a dónde iba
💬 | What did she say? | ¿Qué dijo ella?
🏠 | She told him she was going to her grandmother's house | Ella le dijo que iba a casa de su abuela
❓ | And then what did she do? | Y después, ¿qué hizo ella?
💭 | I don't remember the rest of the story | No me acuerdo del resto del cuento
🍽️ | Did the wolf eat her at the end? | ¿La comió el lobo al final?
📌 | I think a hunter saved her | Creo que un cazador la salvó
❓ | And what happened to the wolf? | ¿Y qué le pasó al lobo?
📌 | The hunter kill him/it | El cazador lo mató
⏱️ | That's not the version I know | Esa no es la versión que yo conozco
🔨 | What difference does it make? | ¿Qué importa?
🗣️ | He doesn't talk as much as you do | Él no habla tanto como tú
🗣️ | You talk too much | Hablas demasiado
🚭 | Don't smoke so much | No fumes tanto
🚭 | I don't smoke as much as you do | No fumo tanto como tú
🚭 | You smoke too many cigars | Fumas demasiados puros
🚭 | I don't smoke as many as he does | No fumo tantos como él
🤷 | You smoke so many that I don't know what to do | Fumas tantos que no sé que hacer
👂 | Can't you hear me? | ¿No me puedes oír?
👥 | Can I attend the meeting? | ¿Puedo asistir a la reunión?
📌 | That's life | Así es la vida
💬 | I don't believe anything you say | No creo nada de lo que dices
❓ | Don't you trust me? | ¿No te fias de mí?
🚫 | It's not that I don't trust you... | No es que no me fíe de tí...
❓ | Then what is it? | Entonces, ¿qué es?
👥 | What's the matter with you? | ¿Qué te pasa?
✅ | Don't you like me anymore? | ¿Ya no te caigo bien?
✅ | Don't you want to see me anymore? | ¿Ya no quieres verme?
🤝 | I thought we were friends | Creí que éramos amigos
🤝 | I thought I was your best friend | Creí que era tu mejor amigo
🤔 | Why don't you say something? | ¿Por qué no dices algo?
⏱️ | I'd like to know what you think | Me gustaría saber lo que piensas
😠 | Since when do you hate me? | ¿Desde cuando me odias?
❓ | What did I do to offend you? | ¿Qué hice yo para ofenderte?
⏰ | They had a good time | Se lo pasaron bien
😊 | They wished us a merry Christmas | Nos desearon una feliz Navidad
👉 | We wished them the same | Los deseamos lo mismo
✅ | He seemed to be in a good mood | Él parecía estar de buen humor
⏱️ | How do you know? | ¿Cómo lo sabes?
🌅 | Because he said good morning to me | Porque me dijo buenos días
🧩 | There were some problems at first | Hubo algunos problemas al principio
❓ | How did you solve them? | ¿Cómo los resolvisteis?
📌 | In the usual way | De la forma habitual
❓ | How was that? | ¿Cómo era eso?
🌇 | I'll tell you later | Te lo diré más tarde
🤔 | Why can't you tell me now? | ¿Por qué no puedes decírmelo ahora?
💬 | Okay, I'll tell you | Vale, te lo diré
✔️ | Thank you for telling me the truth | Gracias por decirme la verdad
⭕ | You're wellcome/Don't mention it | De nada
"""

    private const val list17 = """
⏰ | Explain it to me one more time | Explícamelo una vez más
👌 | It was much easier than the last time | Fue mucho más fácil que la última vez
📦 | How long did it take you to do it? | ¿Cuánto tardaste en hacerlo?
🏁 | He was better prepared than I was | Él estaba mejor preparado que yo
❌ | It was due to an error/a mistake | Fue debido a un error
🌳 | He had to climb the tree | Él tuvo que subirse al árbol
🍷 | The plumber came to look at it | Vino el fontanero para miraralo
📋 | They were complaining about the lack of a concrete plan | Se estaban quejando de la falta de un plan concreto
👀 | But they saw this, didn't they? | Pero vieron esto ¿no?
👤 | Who was with him when he fell? | ¿Quién estaba con él cuando se cayó?
👨 | Everyone expected a man | Todo el mundo esperaba un hombre
👥 | There will be several meetings | Habrá varias reuniones
👥 | Will there be many people there? | ¿Habrá mucha gente allí?
⏱️ | We don't need your help for now | No necesitamos tu ayuda por ahora
📞 | Thank Good they didn't call me | Gracias a Dios no me llamaron
🛏️ | It'll take place in that room | Tendrá lugar en aquella sala
📝 | There won't be any kind of party | No habrá ninguna clase de fiesta
✅ | Don't send that to me anymore | No me envíes eso ya
🔹 | There are still some mistakes | Todavía hay algunos errores
📌 | He tried to kill himself | Él trató de matarse
🪞 | I can see myself in the mirror | Puedo verme en el espejo
📞 | They call themselves the outlaws | Se llaman a sí mismos los forajidos
✨ | Are they really outlaws? | ¿Son de verdad forajidos?
⏱️ | Who knows? | ¿Quién sabe?
❌ | They must be bad | Deben ser malos
⏰ | I met him a long time ago | Le conocí hace mucho tiempo
✈️ | I picked him up at the airport | Le recogí en el aeropuerto
🛏️ | I went to bed but I couldn't go to sleep/fall sleep | Me acosté pero no pude dormir
🚫 | I woke up but I didn't get up | Me desperté pero no me levanté
📌 | We'll have to wake her up | Tendremos que despertarla
❓ | What for? | ¿Para qué?
📦 | Put them in (to) the square boxes | Metelos en las cajas cuadradas
📦 | Don't take them out | No lo saques
📦 | Don't take them out of the boxes | No los saques de las cajas
📌 | It's useless, throw it away | Es inútil, tíralo
🚫 | I don't need it | No me hace falta
👍 | I don't like to throw things away | No me gusta tirar las cosas
📌 | Then I'll throw it away | Entonces yo lo tiraré
❓ | May I? | ¿Se puede?
🏢 | I joined the firm years ago | Ingresé en la firma hace años?
💼 | I joined the army because I couldn't find another job | Ingresé en el ejército porque no pude encontrar otro trabajo
💪 | I appreciate your effort | Agradezco su esfuerzo
📌 | Pleased to meet you | Encantado de conocerla
🚭 | Do you mind if I smoke? | ¿Te importa que yo fume?
🌐 | I don't mind at all | No me importa en absoluto
🚫 | It doesn't matter | Da igual
🤔 | Why don't you joined the club? | ¿Por qué no te haces socio del club?
👍 | I'd like to, but I can't | Me gustaría, pero no puedo
❓ | What do you want to be when you grow up | ¿Qué quieres ser cuando seas mayor?
📌 | I want to join the Navy | Quiero entrar en la Marina
"""

    private const val list18 = """
💼 | You work too much. | Trabajas demasiado.
💵 | How much did it cost? | ¿Cuánto costó?
🛍️ | I decided not to buy it. | Decidí no comprarlo.
👂 | When did you hear it? | ¿Cuándo lo oíste?
🤒 | I got sick / ill. | Me puse enfermo.
📅 | It took me several days. | Tardé varios días.
💵 | Who brought the money? | ¿Quién trajo el dinero?
📌 | I did. | Yo
☂️ | I forgot my umbrella. | Se me olvidó el paraguas.
📌 | That happens to me too. | Eso me pasa a mí también.
👀 | They look alike. | Ellos se parecen ( físicamente)
🎂 | I sat on the cake. | Me senté en la tarta.
🌐 | Everyone laughed at me. | Todos se rieron de mí.
✉️ | I went to sleep around three. | Me dormí sobre las tres.
📖 | He read it to me. | Él me lo leyó.
⚡ | They grew quickly / fast. | Crecieron rápidamente.
⏰ | How long did it last? | ¿Cuánto tiempo duró?
🏷️ | Who sold it? | ¿Quién lo vendió?
👍 | They drove like professionals. | Condujeron como profesionales.
✍️ | I wrote it with you in mind. | Lo escribí pensando en ti.
📌 | That's very kind of you. | Eso es muy amable de tu parte.
📖 | I hope you read it. | Espero que lo leas.
📖 | I won't let anyone read it. | No dejaré que nadie lo lea.
🔨 | You don't have to do that. | No tienes que hacer eso.
📌 | We did it ourselves. | Lo hicimos nosotros mismos.
📌 | They taught me the alphabet. | Me enseñaron el alfabeto.
🎁 | Give it back to me / Return it to me. | Devuélvemelo.
🤔 | Why didn't you use it? | ¿Por qué no lo usaste?
👉 | What were their reactions? | ¿Cuáles fueron sus reacciones?
🔍 | What did you find out? | ¿Qué averiguaste?
🌙 | It's getting dark. | Está oscureciendo.
👍 | I didn't like your speech. | No me gustó tu discurso.
📌 | They showed me the picture. | Me enseñaron el cuadro.
🐘 | It wasn't as big as I thought. | No era tan grande como yo creía.
1️⃣ | They turned at the first corner. | Giraron en la primera esquina.
🌍 | They went back to their country. | Volvieron a su país.
😊 | Everyone was glad. | Todos se alegraban.
👥 | I didn't call the meeting. | No convoqué la reunión.
😋 | They were hungry. | Tenían hambre.
🎁 | They gave it to me. | Me lo dieron.
📉 | I didn't lose it. | No lo perdí.
💨 | It was windy. | Hacia viento.
🛏️ | I made the bed. | Hice la cama.
💪 | I made an effort. | Hice un esfuerzo.
⭐ | I did the best I could. | Hice lo mejor que pude.
🚫 | I didn't steal it. | No lo robé.
🚪 | They left. | Se fueron.
❓ | What happened? | ¿Qué pasó?
❓ | How did it happen? | ¿Cómo pasó?
❓ | What were you doing when it happened? | ¿Qué hacías cuando ocurrió?
"""

    private const val list19 = """
🗣️ | Who do you have to talk to? | ¿Con quién tienes que hablar?
📍 | They told me I had to come here. | Me dijeron que tenia que venir aquí.
🚫 | It wasn't my fault. | No fue culpa mía.
🧠 | Nobody knew it was going to happen. | Nadie sabia que iba a ocurrir.
💬 | Do you mean it was a surprise? | ¿Quieres decir que fue una sorpresa?
⭐ | I don't consider it important. | No lo considero importante.
⭐ | There are more important things in life. | Hay cosas más importantes en la vida.
🤝 | Do you consider him a friend? | ¿Le consideras un amigo?
🔨 | What else can I do? | ¿Qué más puedo hacer?
🌐 | I do everything I can. | Hago todo lo que puedo.
🚫 | That's not enough. | Eso no es suficiente.
⭕ | Nobody wanted to argue with him. | Nadie quería discutir con él.
👨 | He was the most difficult man I knew. | Fue el hombre más difícil que yo conocía.
1️⃣ | They were the first ones to do it. | Fueron los primeros en hacerlo.
🚗 | I lost control of the car. | Perdí el control del coche.
🏷️ | We sold everything we produced. | Vendimos todo lo que produjimos.
🏷️ | We don't sell anything anymore. | Ya no vendemos nada.
👀 | I took her to see my aunt. | La llevé a ver a mi tía.
🌧️ | It was raining when we left. | Llovía cuando salimos.
🚗 | The car didn't start. | El coche no arrancó.
🚕 | We caught a taxi / cab. | Cogimos un taxi.
🚕 | The taxi driver was a woman. | El conductor del taxi era una mujer.
❓ | Was she polite? | ¿Era educada?
🚕 | Most taxi drivers are polite. | La mayoría de los taxistas son educados.
🌊 | I doubt that it's true. | Dudo que sea verdad.
📌 | I have my doubts. | Tengo mis dudas.
💵 | How much do I owe you? | ¿Cuánto te debo?
💵 | How much does he owe you? | ¿Cuánto te debe él?
⏰ | Don't waste your time. | No pierdas tu tiempo.
⏰ | That's a waste of time. | Eso es una pérdida de tiempo.
⏳ | He never knew the meaning of life | Él nunca supo el significado de la vida.
🤷 | I don't know it, either. | Yo tampoco lo sé.
👨 | You're a very lucky man. | Eres un hombre muy afortunado.
👥 | Few people have what you have. | Poca gente tiene lo que tú tienes.
🏆 | Won't there be anything? | ¿No habrá nada?
⭕ | Wasn't there anything? | ¿No había nada?
⭕ | Isn't there anything? | ¿No hay nada?
🚪 | Who's knocking at / on the door? | ¿Quién llama a la puerta?
⏳ | Be still. | Estate quieto.
❓ | What does this mean? | ¿Qué significa esto?
📌 | We have to calculate the amount. | Tenemos que calcular la cantidad.
👥 | Leave it here so that other people can use it, too. | Déjalo aquí para que otra gente lo pueda usar también.
💡 | That's a terrific idea. | Esa es una idea fabulosa.
🚫 | It didn't occur to me. | No se me ocurrió.
🤔 | Why didn't it occur to you? | ¿Por qué no se te ocurrió?
⏳ | Thinks like that never occur to me. | Cosas así nunca se me ocurren.
⚖️ | You'll have to make a decision. | Tendrás que tomar una decisión.
⏳ | Can't you wait a little longer? | ¿No puedes esperar un poco más?
🍽️ | He never liked to eat out. | Nunca le gustaba comer fuera.
❓ | What do you propose we do? | ¿Qué propones que hagamos?
"""

    private const val list20 = """
💵 | How much time do we have left? | ¿Cuánto tiempo nos queda?
⏰ | We have two hours left. | Nos quedan dos horas.
👔 | There's a hole in your shirt. | Hay un agujero en tu camisa.
💵 | Let's divide the money. | Dividamos el dinero.
🌐 | I want it all for me. | Lo quiero todo para mí.
👥 | I don't like to deal with people like you. | No me gusta tratar con gente como tú.
🔨 | You can do whatever you want. | Puedes hacer lo que quieras.
💼 | It'll work if you treat it well. | Funcionará si lo tratas bien.
⏱️ | You don't know how to do anything. | No sabes hacer nada.
💼 | Does it work? | ¿Funciona?
⏱️ | I'm glad to know that. | Me alegro de saber eso.
⏱️ | There's no reason to be worried. | No hay razón para estar preocupado.
✈️ | They must be afraid of flying. | Deben de tener miedo de volar.
👶 | He drinks too much. | Él bebe demasiado.
👂 | I can hear everything they do. | Puedo oír todo lo que hacen.
📋 | Don't listen to them. | No les escuches.
🆘 | I can't help it. | No lo puedo evitar.
📌 | It must be terrible. | Debe de ser terrible.
📌 | I think she envies him. | Creo que ella le envidia a él.
⏳ | Don't ever hit me again. | No vuelvas a pegarme nunca.
👩 | Are you still hitting your wife? | ¿Sigues pegando a tu mujer?
⚖️ | What was the final decision? | ¿Cuál fue la decisión final?
🤔 | Why weren't you there? | ¿Por qué no estuviste allí?
📍 | I told them you were going to be there. | Les dije que ibas a estar allí.
📌 | They're going to think I'm a liar. | Van a pensar que soy un mentiroso.
👍 | You look like a wet rat. | Pareces una rata mojada.
🥱 | Rest for a while, you look tired. | Descansa un rato, pareces cansado.
📦 | Take it or leave it. | Tómalo o déjalo.
🧱 | They jumped over the wall. | Saltaron el muro.
🔨 | Who gave them permission to do such a thing? | ¿Quién les dio permiso para hacer semejante cosa?
🔍 | My search ended in failure. | Mi búsqueda terminó en fracaso.
🤔 | Why did you fail? | ¿Por qué fracasaste?
📅 | Someday I'll succeed. | Algún dia tendré éxito.
⭐ | Success isn't the most important thing. | El éxito no es lo más importante.
👨 | He's a successful man. | Él es un hombre de éxito.
📌 | I'm a failure. | Soy un fracasado.
🎁 | Do you feel sorry for yourself? | ¿Sientes pena de ti mismo?
🎮 | The game / match was very exciting. | El partido fue muy emocionante.
🏆 | Who won? | ¿Quién ganó?
📌 | They tied. | Empataron.
📌 | It was a tie / draw. | Fue un empate.
❓ | Who scored the goals? | ¿Quién marcó los goles?
👉 | What was the final score? | ¿Cuál fue el resultado final?
👦 | I took my son to see it. | Llevé a mi hijo a verlo
🔬 | He's a little young, isn't he? | Es un poco joven, ¿no?
👀 | Apparently not. | Por lo visto, no.
🎮 | He seemed to enjoy the game. | Parecía disfrutar con el partido.
🏠 | What time did you get back home? | ¿A qué hora legasteis a casa?
🌇 | Much later than I excepted. | Mucho más tarde de lo que esperaba.
💬 | What will they say when they find out? | ¿Qué dirán cuando se enteren?
"""

    private const val list21 = """
📌 | It was a complete failure | Fue un completo fracaso
❓ | Whose fault was it? | ¿De quién fue la culpa?
⭕ | It wasn't anybody's fault | No fue culpa de nadie
📍 | There will be an investigation | Habrá una investigación
🏆 | You won't find anything | No encontrarás nada
✅ | We'll see | Ya veremos
📅 | What plans do you have for Sunday? | ¿Que planes tienes para el domingo?
🤝 | We're going to see some friends | Vamos a ver a unos amigos
⏱️ | Do I know them? | ¿Les conozco?
🚫 | I don't think so | Creo que no
👋 | How's your job? | ¿Qué tal tu trabajo?
✅ | Everything's going fine | Todo va bien
✅ | Everything's going great | Todo va de maravilla
👂 | I'm glad to hear that | Me alegro de oir eso
🔥 | Then they don't plan to fire you | Entonces no piensan despedirte
📌 | I hope not | Espero que no
👍 | I don't like to be near him | No me gusta estar cerca de el
❌ | Does he smell bad? | ¿Huele mal?
🚫 | Don't be funny | No seas gracioso
🚫 | I'm not trying to be funny | No pretendo ser gracioso
💬 | I have something important to tell you | Tengo algo importante que decirte
📌 | Go ahead | Adelante
💬 | I'm going to tell you something you're not going to like | Voy a decirte algo que no te va a gustar
📌 | Let me decide that | Deja que yo decida eso
📌 | If that's what you want | Si eso es lo que quieres
⏱️ | You know very well what I want | Tu sabes muy bien lo que quiero
💡 | I have no idea | No tengo ni idea
👧 | I want to marry your daugther | Quiero casarme con tu hija
❓ | You want what? | ¿Quieres que?
✅ | You heard me | Ya me oiste
🌱 | But she's much younger than you are | Pero ella es mucho mas joven que tu
👨 | Are you calling me an old man? | ¿Me estas llamando viejo?
❤️ | Do you really love her? | ¿La quieres de verdad?
❤️ | I love her and she loves me | La quiero y ella me quiere a mi
🗓️ | We want to get married next week | Queremos casarnos la proxima semana
▶️ | When did you start going out together? | ¿Cuando empezasteis a salir juntos?
🗓️ | A couple of months ago | Hace un par de meses
👧 | Where did you meet my daughter? | ¿Donde conociste a mi hija?
👩 | Does my wife know anything about this? | ¿Sabe mi mujer algo de esto?
💬 | Who told her? | ¿Quien se lo dijo?
⏱️ | I hope you know what you're doing | Espero que sepas lo que haces
😊 | What an unpleasant surprise! | ¡Que desagradable sorpresa!
🚫 | Don't expect to have my consent | No esperes tener mi consentimiento
🤔 | Why didn't she tell me anything? | ¿Por qué no me dijo nada ella?
👶 | I hope she's not expecting a baby | Espero que no este esperando un niño
❓ | What do you suggest we do? | ¿Que sugieres que hagamos?
⚖️ | Make your own decisions | Toma tus propias decisiones
🎁 | Aren't you going to give any advice? | ¿No me vas a dar consejos?
❓ | What for? | ¿Para que?
📌 | At least congratule us | Al menos felicitanos
"""

    private const val list22 = """
💬 | Tell him to come | Dile que venga
👍 | I'd like to try it | Me gustaria probarlo
💬 | Tell them to be careful | Diles que tengan cuidado
👥 | I want to go with him | Quiero ir con el
👥 | I want you to go with him | Quiero que vayas con el
💬 | Don't tell him who I am | No le digas quien soy
⏱️ | I don't want him to know anything | No quiero que el sepa nada
💬 | They told me to look for him | Me dijeron que le buscara
💬 | Nobody told you to do anything | Nadie te dijo que hicieras nada
💬 | I told you to do it again | Te dije que volvieras a hacerlo
🤔 | Why didn't you it again? | ¿Por que no volviste a hacerlo?
💬 | Who told you to call me? | ¿Quien te dijo que me llamaras?
👀 | I want you to look at me | Quiero que me mires
⏱️ | I want you to know something | Quiero que sepas una cosa
📌 | I want you to share your toys | Quiero que compartas tus juguetes
📌 | I want you to be the judge | Quiero que seas el juez
📌 | I want you to be fair | Quiero que seas justo
✅ | Tell him to leave | Dile que se vaya
⌚ | Watch them | Vigilales
📌 | They want us to loose | Quieren que perdamos
🏆 | We want you to win | Queremos que ganeis
⏰ | I want you to have a good time | Quiero que os lo paseis bien
📌 | I want you to think about it | Quiero que lo pienses
🚶 | They don't want us to come | No quieren que vengamos
↔️ | I want you to find a solution | Quiero que encuentres una solucion
👌 | It's not going to be easy | No va a ser facil
📌 | I want you to have this | Quiero que tengas esto
👌 | It's easy to give orders | Es fácil dar ordenes
🧗 | It's difficult to carry them out | Es dificil llevarlas a cabo
❓ | I didn't ask you to do anything | No te pedi que hicieras nada
🤝 | I'm asking you to be my friend | Te estoy pidiendo que seas mi amigo
💬 | Tell them not to look at me | Diles que no me miren
❓ | Ask them not to smoke | Pideles que no fumes
🤔 | Why do you want me to do that? | ¿Por que quieres que yo haga eso?
❓ | What do you want me to do? | ¿Que quieres que yo haga?
💬 | What do you want me to say?. | ¿Que quieres que diga?
✅ | Where do you want me to go? | ¿A donde quieres que yo vaya?
📍 | When do you want me to be here? | ¿Cuando quieres que yo esté aquí?
🗣️ | Who do you want me to speak to? | ¿Con quién quieres que hable?
📞 | How often do you want me to call him? | ¿Con que frecuencia quieres que le llame?
🎯 | Which one do you want me to choose? | ¿Cual quieres que elija?
👥 | How long do you want the meetimg to last? | ¿Cuanto tiempo queres que dure la reunion?
❓ | How do you want me to convince her? | ¿Como quieres que la convenza?
📞 | How do you want me to call you? | ¿Como quieres que te llame?
📦 | Where do you want me to take you? | ¿A donde quieres que te lleve?
🍞 | I hardly ever eat bread | Casi nunca como pan
♾️ | I always use ashtrays | Siempre uso ceniceros
🧳 | We seldom travel | Raramente viajamos
⏳ | They're never satisfied | Nunca estan satisfechos
⏰ | Have a good time | Divertios
"""

    private const val list23 = """
👍 | Would you like to join us? | ¿Te gustaria juntarte con nosotros?
👥 | What kind of person is he? | ¿Que clase de persona es el?
📌 | I appreciate your interest | Agradezco tu interes
🔹 | Let's do something | Hagamos algo
❓ | Shall we go? | ¿Nos vamos?
📌 | Let's go | Vamonos
🆘 | Shall I help them? | ¿Les ayudo?
🎯 | This is your last chance | Esta es tu ultima oportunidad
📍 | You can go wherever you want | Puedes ir a donde quieras
🔨 | You can do whatever you want | Puedes hacer lo que quieras
📞 | You can call whoever you want | Puedes llamar a quien quieras
🚪 | You can leave whenever you want | Te puedes ir cuando quieras
❓ | When will you pick them up? | ¿Cuando les recogeras?
⏳ | Will they be waiting for you? | ¿Estarán esperándote?
🚗 | Why don't you take my car? | ¿Por que no llevas mi coche?
🤝 | Let's try to be friends | Intentemos ser amigos
📖 | I'd like to give you this book | Me gustaría regalarte este libro
📌 | That's very kind of you | Eso es muy amable por tu parte
👈 | Don't let them bother you | No dejes que te molesten
❓ | Am I bothering you? | ¿Te estoy molestando?
✔️ | I'll be right back, don't go away | Enseguida vuelvo, no te vayas
🔨 | What can we do? | ¿Que podemos hacer?
🤷 | Forget about her | Olvidate de ella
📋 | I love listening to you talk | Me encanta escucharte hablar
❤️ | I'd love to be with you | Me encantaría estar contigo
🚶 | Do you want to come with us? | ¿Te quieres venir con nosotros?
❤️ | Yes, I'd love to | Si, me enacantaría
⏳ | Then, what are we waiting for? | Entonces, ¿a que estamos esperando?
👀 | This has nothing to do with that | Esto no tiene nada que ver con aquello
❓ | What shall we do? | ¿Qué hacemos?
⭐ | Things will get better | Las cosas mejoraran
🥱 | It was such a boring speech that I felt asleep | Era un discurso tan aburrido que me dormé
1️⃣ | I wasn't the only one | No fuí el único
🆘 | Let me help you | Dejame que te ayude
😊 | I'm glad you won | Me alegro de que ganaras
📉 | I'm sorry you lost | Siento que perdieras
👀 | Let's see what you have | Veamos lo que tienes
🐢 | Ask them to walk more slowly | Pideles que caminen mas despacio
🌧️ | I'm afraid it's going to rain | Me temo que vaya a llover
💬 | Tell him whatever you want | Dile lo que quieras
🤔 | Why does he want you to do that? | ¿Por que quiere él que hagas eso?
📅 | I learned about it yesterday | Me enteré de ello ayer
🌅 | Don't wake me up too early | No me despiertes demasiado temprano
⏱️ | Shall I turn on the light now? | ¿Enciendo la luz ahora?
📍 | Tell him to stay where he his | Dile que se quede donde esta
💬 | Tell him not to move | Dile que no se mueva
⭐ | Mine is much better than yours | El mío es mucho mejor que el tuyo
🇬🇧 | I'm looking for someone who knows how to speak English | Estoy buscando a alguien que sepa hablar ingles
🇬🇧 | I was borne in an English-speaking country | Nací en un país de habla inglesa
🇬🇧 | Is English your mother tongue? | ¿Es el ingles tu lengua materna?
"""

    private const val list24 = """
⚖️ | When will you make a decision? | ¿Cuando tomarás una decisión?
🌧️ | When it stop raining | Cuando deje de llover
🏫 | He quit school at the age of | El dejó la escuela a los años
🚭 | I have to quit smoking | Tengo que dejar de fumar
🕊️ | Leave me alone! | ¡Dejame en paz!
💬 | Do you have anything to say? | ¿Tienes algo que decir?
👥 | Few people are as impatient as you are | Poca gente es tan impaciente como tu
❓ | When will they be back? | ¿Cuando estarán de vuelta?
❓ | What shall I do in the meantime? | ¿Que hago mientras tanto?
📖 | I never read thick books | Nunca leo libros gruesos
📄 | I always use thin paper | Siempre utilizo papel fino
🥗 | You're getting fat | Estás engordando
📌 | I'm trying to loose weight | Estoy intentando adelgazar
💵 | How much do you weigh? | ¿Cuanto pesas?
📈 | Not as much as you do | No tanto como tu
🎬 | What an exciting movie! | ¡Que pelicula mas emocionante!
👧 | She is a very sensible girl | Es una chica muy sensata
👧 | She's a very sensitive girl | Es una chica muy sensible
📌 | Be sensible | Sé sensato
📷 | Who took the picture? | ¿Quién sacó la foto?
👎 | Things are getting worse | Las cosas están empeorando
👥 | He's the worst person for the job | Él es la peor persona para el trabajo
💬 | I told you not to touch anything | Te dije que no tocaras nada
🤔 | Why didn't you obey me? | ¿Por qué no me obedeciste?
♾️ | I always trusted him | Siempre me fiaba de él
💼 | They brought us here to work | Nos trajeron aquí para trabajar
🧾 | I didn't realise you were here | No me dí cuenta de que estabas aquí
🧠 | Not even John knew what to do | Ni siquiera John sabía qué hacer
🎯 | Choose / pick whichever one you want | Elige el que quieras
📌 | He weighs more than I do | Él pesa más que yo
🏙️ | This is the dirtiest city in the world | Esta es la ciudad mas sucia del mundo
📈 | There isn't as much oil here as in Saudi Arabia | No hay tanto petroleo aquí como en Arabia Saudita
👍 | Don't behave like him | No te comportes como él
🎁 | Don't give up | No te des por vencido
🎯 | Didn't you have a chance? | ¿No tuviste una oportunidad?
💳 | Don't pay any attention to them | No les hagas caso
👁️ | Keep your eyes open | Manten los ojos abiertos
🚫 | I can't reach it | No puedo alcanzarlo
🏠 | He must not be at home | Él no debe estar en casa
⏱️ | How do you know? | ¿Como lo sabes?
📞 | He doesn't answer the telephone | No coge el teléfono
📌 | Keep trying | Sigue intentando
⏰ | It's a waste of time | Es una pérdida de tiempo
📌 | I have to contact him | Tengo que ponerme en contacto con el
⏳ | You'll never find him | Nunca le encontarás
📍 | Where there's a will, there's a way | Querer es poder
🚫 | We don't need your proverbs | No necesitamos tus proverbios
🗣️ | Who taught you to speak like that? | ¿Quién te enseñó a hablar así?
👩 | My mother taught me everything I know | Mi madre me enseñó todo lo que sé
👨 | You're a lucky man | Eres un hombre afortunado
"""

    private const val list25 = """
📋 | When will there be an exam? | ¿Cuando habrá un examen?
⚡ | We'll call you as soon as we receive it? | Te llamaremos en cuanto lo recibamos
📥 | When do you think you'll receive it? | ¿Cuando creeis que lo recibireis?
👍 | I'd like to discuss these matters with you | Quiesiera discutir estos asuntos contigo
📌 | I want to apologise to you n | Quiero pedirte perdo
😟 | Don't worry, it was nothing | No te preocupes, no fue nada
😡 | I hope you're not angry | Espero que no estés enfadado
❓ | How do they want us to solve it? | ¿Como quieren ellos que lo resolvamos?
⚡ | I got dressed after breakfast | Me vestí despues del desayuno
📦 | How long does it take you to get dressed? | ¿Cuanto tardas en vestirte?
🌐 | The thieves took everything | Los ladrones se llevaron todo
❗ | What a pity! | ¡Que lastima!
💳 | The insurance will pay for it | El seguro lo pagará
🏷️ | Don't mention my name | No menciones mi nombre
💬 | Don't tell him I told you | No le digas que te lo dije
🚫 | He wont believe it when it hears it | Él no lo creera cuando lo oiga
😟 | Don't let that worry you | No dejes que eso te preocupe
🌙 | The operation will begin tonight | La operación comenzará esta noche
📌 | It's success will depend on you | Su exito dependera de tí
🚫 | Don't scare me | No me asustes
📝 | What kind of behaviour is that? | ¿Que clase de comportamiento es ese?
👋 | You'll burn your arm | Te quemarás el brazo
💼 | Do you really want this job? | ¿Realmente quieres este trabajo?
🏆 | Then you'll have to earn it | Entonces lo tendrás que ganar
💵 | It'll mean a lot more money | Significará mucho mas dinero
📈 | But they'll expect a lot from you | Pero esperarán mucho de ti
🌅 | You'll have to get here earlier | Tendrás que llegar aquí mas temprano
📌 | You'll have to prove your worth | Tendrás que demostrar to valía
⚖️ | You'll have to make decisions | Tendrás que tomar decisiones
👥 | You'll have to deal with people | Tendrás que tratar con gente
🧗 | You'll have to be hard at times | Tendrás que ser duro a veces
💼 | Do you still want the job? | ¿Todavía quieres el trabajo?
📝 | Of course I do | Por supuesto que sí
📌 | Then it's yours, enjoy it | Entonces es tuyo, disfruta con él
🤷 | I don't know how to thank you | No sé como agradecertelo
🚫 | You don't have to thank me | No tienes que agradecermelo
▶️ | When do I start? | ¿Cuando empiezo?
🏢 | Where will my office be? | ¿Donde estará mi despacho?
📊 | How many people will report to me? | ¿Cuantas personas dependerán de mí?
📊 | Who will I report to? | ¿De quién dependeré?
👍 | What's he like? | ¿Como es él?
👥 | What kind of person is he? | ¿Que clase de persona es?
✅ | Do you think we'll get along? | ¿Crees que nos llevaremos bien?
🧑‍💼 | Is he a though boos? | ¿Es un jefe duro?
💼 | How long did you work with him? | ¿Cuanto tiempo trabajaste con el?
🏢 | Why did you leave the company? | ¿Por qué te marchaste de la compañía?
🧑‍💼 | Who was the boos at that time? | ¿Quién era el jefe en aquel entonces?
🔨 | Did he try to make you come back? | ¿Intento él hacer que volvieras?
🚫 | He tried but he didn't succeed | Lo intentó pero no tuvo exito
🍀 | Wish me luck | Deseame suerte
"""

    private const val list26 = """
🎁 | Draw it for me. | Dibújamelo.
🏙️ | They flew over the city. | Volaron sobre la ciudad.
💬 | l didn't say anything. | No dije nada.
💬 | Tell him what you said. | Dile lo que dijiste.
✨ | Tell the truth. | Di la verdad.
😂 | Tell us a joke. | Cuéntanos un chiste.
🧾 | Count to ten. | Cuenta hasta diez.
🔍 | When l find out, I'll kill her. | Cuando me entere, la mataré.
❤️ | l'd love to help you. | Me encantaría ayudarte.
✋ | Can you give me a hand? | ¿Me puedes echar una mano?
🧠 | I thought you knew. | Creí que lo sabias.
🚫 | Don't shout at me. | No me grites.
🚫 | If you don't do it, I'll do it. | Si no lo haces, lo haré yo.
⏰ | Let's do it one more time. | Hagámoslo una vez más.
🔨 | What difference does it make? | ¿Qué importa?
👩 | I thought you liked women. | Creí que te gustaban las mujeres.
💧 | They pushed him into the water. | Le empujaron al agua.
👔 | Don't tear his shirt. | No le rompas la camisa.
💥 | Don't break my machine. | No me rompas la máquina.
🚫 | We have no choice. | No tenemos alternativa.
❓ | When is he going to pick us up? | ¿Cuándo nos va él a recoger?
⏱️ | I hope you know what you're doing. | Espero que sepas lo que haces.
🗣️ | Stop talking so much. | Deja de hablar tanto.
🎁 | Who gave you permission? | ¿Quién te dio permiso?
1️⃣ | You're the one I'm looking for. | Tú eres el que estoy buscando.
🌐 | Everything was your fault. | Todo fue culpa tuya.
🤷 | I don't know what you're talking about. | No sé de qué estás hablando.
🤥 | Don't lie to me. | No me mientas.
🥱 | I'm tired of listening to your complaints. | Estoy cansado de escuchar tus quejas.
♾️ | You're always complaining. | Siempre te estás quejando.
📌 | We're going to move to Norway. | Nos vamos a mudar a Noruega.
✍️ | Write it down. | Apúntalo.
📥 | Put it on. | Póntelo.
📦 | Take it off | Quítatelo.
📌 | Try it on | Pruébatelo.
👀 | Look for them. | Búscales.
⏳ | Wait for her. | Espérala.
👀 | Look at that. | Mira eso.
📌 | Pick them up. | Recógelos.
🍲 | Take her out to dinner. | Llévala a cenar.
🚫 | You don't have to shout at them. | No tienes que gritarles.
📌 | Throw it away. | Tiralo(a la basura)
🪟 | Throw it out the window. | Tiralo por la ventana.
🎁 | Don't give up. | No te rindas.
📌 | Get out. | Lárgate.
📍 | Get out of here. | Lárgate de aquí
📞 | Call him back. | Vuelve a llarmarle.
📌 | Keep trying. | Sigue intentando.
😂 | Don't laugh at me. | No te rías de mí.
😭 | Stop crying. | Deja de llorar.
"""

    private const val list27 = """
🧒 | Whose drawings are tose? | ¿De quién son esos dibujos?
📌 | They'll probably try to kill them. | Probablemente intentarán matarles.
👩 | She's the most beautiful woman in the world. | Es la mujer más hermosa del mundo.
🌍 | Everyone will take the exam. | Todo el mundo hará el examen.
📝 | If you pass the exam, you'll pass the course. | Si apruebas el examen, aprobarás el curso.
📚 | How many subjects do I have to study? | ¿Cuántas asignaturas tengo que estudiar?
🛒 | There was an explosion in that shop. | Hubo una explosión en aquella tienda.
❓ | When did it happen? | ¿Cuándo ocurrió?
👥 | Were there any people nearby? | ¿Había gente cerca?
📞 | The telephone's ringing. | Está sonando el teléfono.
🗣️ | Who's going to answer it? | ¿Quién lo va a coger?
⭕ | Nobody's interested in your drawing. | Nadie está interesado en tu dibujo.
⏰ | Do you know how long it took me to draw it’ | ¿Sabes cuánto tiempo tardé en dibujarlo?
📌 | Let me guess. | Déjame que lo adivine.
✅ | Well, gentlemen, what shall we do? | Bueno señores, ¿qué hacemos?
👨 | He's the best man we have. | Es el mejor hombre que tenemos.
🧗 | It'll be difficult to find a replacement. | Será difícil encontrar un sustituto.
📈 | Nobody knows as much as he does. | Nadie sabe tanto como él.
📈 | Nobody speaks as many languages as he does. | Nadie habla tantos idiomas como él.
⭕ | Nobody is as persuasive as he is. | Nadie es tan persuasivo como él.
📈 | Nobody has as many contacts as he does. | Nadie tiene tantos contactos como él.
⭕ | Nobody's as attractive as he is. | Nadie es tan atractivo como él.
👍 | We'll have to find someone like him. | Tendremos que encontrar a alguien como él.
👌 | It's not going to be easy. | No va a ser fácil.
📌 | It's our only alternative / choice. | Es nuestra única alternativa.
⏱️ | We'd like to know what he thinks. | Quisiéramos saber lo que él piensa.
📞 | Why don't we call him? | ¿Por qué no le llamamos?
🆘 | If you think he'll help us, you're crazy. | Si crees que él nos ayudará, estás loco.
📌 | Get dressed and let's go. | Vístete y vámonos.
🚻 | Shall l get dressed here or in the bathroom? | ¿Me visto aquí o en el baño?
📍 | I don't care where you get dressed. | No me importa donde te vistas.
😡 | He's getting angry. | Él se está enfadando.
✅ | I feel great. | Me encuentro de maravilla.
🌇 | Do you mind calling back later? | ¿Te importa llamar más tarde?
🌇 | I'll be here all afternoon. | Estaré aquí toda la tarde.
🚪 | If I'm not here, leave a message. | Si no estoy, deja un recado.
📞 | If no one answers, keep calling. | Si nadie contesta, sigue llamando.
🔨 | I'm sorry to have to do this. | Siento tener que hacer esto.
🚭 | I smoke because I like to. | Fumo porque me gusta.
📍 | He comes here because he likes to. | Él viene aquí porque le gusta.
🧽 | It's getting dirty. | Se está ensuciando.
✨ | How often do you clean it? | ¿Con qué frecuencia lo limpias?
✨ | I have a maid to clean it. | Tengo a una criada para limpiarlo.
😭 | Crying won't help. | El llorar no ayudará.
✅ | Things are going well. | Las cosas van bien.
👋 | How are you doing? | ¿Qué tal estás?
🧒 | What are the Americans like? | ¿Cómo son los Americanos?
👁️ | They have eyes and ears like we do. | Tienen ojos y orejas como nosotros.
📌 | They're ancestors were European. | Sus antepasados eran europeos.
👍 | Do they have houses like ours? | ¿Tienen casas como las nuestras?
"""

    private const val list28 = """
❓ | What was the verdict | ¿Cual fue et veredicto?
🔍 | They found him guilty. | Le encontraron culpable.
❓ | Do you think he did it? | ¿Crees que lo hizo?
🤷 | I don't know what to think. | No sé qué creer.
🌍 | It's like living in the country. | Es como vivir en el campo.
📋 | This plan is our salvation. | Este plan es nuestra salvación.
📦 | It'll only bring us problems. | Ello sólo nos traerá problemas.
👥 | I disagree with you. | Estoy en desacuerdo contigo.
💭 | What do you base your opinion on? | ¿En qué basas tu opinión?
🧒 | They look exactly alike. | Son iguales(de parecido físico)
😟 | They must worry a lot about you. | Deben de preocuparse mucho por ti.
👂 | I heard it but l don't believe it. | Lo oí pero no lo creo.
⏳ | Don't wait too long. | No esperes demasiado.
❓ | What was the article about? | ¿De qué trataba el artículo?
♾️ | About scandals, as always. | De escándalos como siempre.
👥 | Aren't people interested in anything else? | ¿No esta la gente interesada en otras cosas?
📌 | I guess not / I suppose not. | Supongo que no.
📖 | Do you read those articles? | ¿Lees esos artículos?
📖 | I try not to read them. | Trato de no leerlos.
☀️ | It's a sunny day. | Hace un día soleado.
☀️ | The sun is shining. | Luce el sol.
☁️ | It's getting cloudy. | Se está nublando.
⛅ | What's the weather like? | ¿Qué tiempo hace?
🌧️ | It's starting to rain. | Está empezando a llover.
📌 | It's foggy. | Hay niebla.
📌 | The fog's very thick. | La niebla está muy espesa.
❄️ | The snow's melting. | La nieve se está derritiendo.
⚡ | When will the storm reach us? | ¿Cuándo nos alcanzará la tormenta?
🐕 | It's raining cats and dogs. | Está lloviendo a cántaros.
☀️ | The sun's going down. | Está bajando el sol.
🌙 | It'll be dark soon. | Pronto estará oscuro.
🧒 | Children don't go out when it's dark. | Los niños no salen cuando está oscuro.
📅 | Today's the hottest day of the year. | Hoy es el día más caluroso del año.
🥚 | You can fry an egg on the pavement. | Puedes freir un huevo en la acera.
🌙 | I can't sleep at night. | No puedo dormir de noche.
📍 | There isn't any breeze. | No hay brisa.
😊 | A slight breeze would be nice. | Una leve brisa estaría bien.
🥵 | I like the heat. | A mi me gusta el calor.
⛅ | Do you mean you like this weather? | ¿Quieres decir que te gusta este tiempo?
📌 | It makes me feel strong. | Me hace sentir fuerte.
📌 | You must be crazy. | Debes de estar loco.
🚌 | I had to stand up in the bus. | Tuve que estar de pié en el autobús.
⭕ | Didn't anybody offer you a seat? | ¿No te ofreció nadie un asiento?
🧾 | Didn't they realise you were pregnant? | ¿No se dieron cuenta de que estabas embarazada?
📌 | Apparently not. | Al parecer no.
⭐ | It was a very important event. | Fue un acontecimiento muy importante.
🚫 | I didn't want to miss it. | Yo no quería perderlo.
👀 | Icouldn't see it for the crowd. | No lo pude ver por la muchedumbre.
🌳 | You can't see the forest for the trees. | Los árboles no te dejan ver el bosque.
👈 | e'll get to the bottom of this affair. | Llegaremos al fondo de este asunto.
"""

    private const val list29 = """
✈️ | What time does the plane leave? | ¿A qué hora sale el avión?
🚪 | What gate does it leave from? | ¿De qué puerta sale?
🏆 | You won't be able to do it. | No podrás hacerlo.
📅 | We'll be able to see him tomorrow. | Podremos verle mañana.
❓ | Who woke her up? | ¿Quién la despertó?
🚫 | lt wasn't me. | No fui yo.
❓ | Then who was it? | Entonces, ¿quién fue?
🤔 | Why are you looking at me? | ¿Por qué me estás mirando a mí?
💬 | I want you to do what I tell you. | Quiero que hagas lo que te digo.
🚫 | I don't want you to get into trouble. | No quiero que te metas en líos.
😊 | I want everyone to be happy. | Quiero que todo el mundo sea feliz.
😢 | I don't want anyone to be sad. | No quiero que nadie esté triste.
🧒 | I want my children to prosper. | Quiero que mis hijos prosperen.
📌 | I want you to succeed in life. | Quiero que triunfes en la vida.
🚫 | I don't want you to fail. | No quiero que fracases.
👩 | I want you to marry a good woman. | Quiero que te cases con una buena mujer.
⏱️ | I want you to increase your knowledge. | Quiero que aumentes tus conocimientos.
⭐ | I want you to do the best you can. | Quiero que hagas lo mejor que puedas.
📉 | He wants the team to lose. | Él quiere que el equipo pierda.
🎟️ | He wants us to buy a ticket. | Quiere que compremos un billete.
🌧️ | He wants it to rain. | Quiere que lueva.
🌧️ | Does it rain a lot here? | ¿Llueve mucho aquí?
🔤 | What does this sentence mean? | ¿Qué significa esta frase?
🏆 | He won't last long. | Él no durará mucho.
🎬 | I'm like the bad guy in the movie. | Soy como el malo de la película.
❓ | In what way? | ¿En qué sentido?
♾️ | I always end up losing. | Siempre termino perdiendo.
🎁 | Do you feel sorry for yourself? | ¿Sientes pena de ti mismo?
🌐 | Not at all. | En absoluto.
😭 | Then why are you crying? | Entonces ¿por qué loras?
🆘 | I can't help it. | No lo puedo evitar.
💬 | What do you mean you can't help it? | ¿Qué quieres decir con que no lo puedes evitar?
1️⃣ | You're peeling an onion. | Estás pelando una cebolla.
🚫 | Don't try to be funny. | No intentes ser gracioso.
📌 | Let's get back to the subject. | Volvamos al tema.
🗣️ | What were we talking about? | ¿De qué hablábamos?
🚫 | Whether ghosts exist or not. | De si existen o no los fantasmas.
🚫 | I don't think they exist. | Yo no creo que existan.
🚫 | I don't believe in them. | No creo en ellos.
❓ | What do you base your belief on? | ¿En qué basas tu creencia?
🗣️ | You don't know what you're talking about. | No sabes de lo que estás hablando.
🗣️ | I have better things to talk about. | Tenemos cosas mejores de que hablar.
👍 | Like what? | ¿Cómo qué?
👍 | Like the true meaning of beauty. | Como el verdadero significado de la belleza.
👍 | Beauty is relative, like everything else. | La belleza es relativa, como todo.
💡 | Where did you get those ideas? | ¿Dónde conseguiste esas ideas?
🚫 | My godfather taught them to me. | Mi padrino me las enseñó.
⏳ | Is he still alive? | ¿Vive aún?
👩 | He's married to my mother. | Está casado con mi madre.
❓ | Then he's you stepfather too, isn't he? | Asi que es tu padrastro también, ¿no?
"""

    private const val list30 = """
💼 | It won't work. | No funcionará.
📌 | They'll beat you. | Te ganarán.
📌 | Lift it. | Levántalo.
📌 | Catch him. | Cógele.
🚫 | Don't let him go. | No le sueltes.
📌 | Hit him if he tries to get away. | Pégale si trata de escaparse.
⏳ | Be still. | Estate quieto.
🐕 | Don't let the dog bite you. | No dejes que el perro te muerda.
❓ | Can I ask you a favour? | ¿Puedo pedirte un favor?
🤔 | That's why I'm leaving. | Es por eso por lo que me marcho.
✉️ | She wrote me a letter. | Ella me escribió una carta.
💵 | It cost me a lot. | Me costó mucho (dinero).
📈 | They charged me too much. | Me cobraron demasiado.
📌 | They translated the message. | Tradujeron el mensaje.
🧑‍💼 | He introduced me to the manager. | Él me presentó al gerente.
🐢 | I was embarrassed. | Y o estaba violento.
⏱️ | I didn't know what to do. | No sabía qué hacer.
✋ | I didn't know where to put my hands. | No sabia donde poner las manos.
🧾 | He realised I was nervous. | Él se dio cuenta de que yo estaba nervioso.
🚫 | I couldn't hide my feelings. | No pude ocultar mis sentimientos.
🍾 | He filled my glass. | Él me llenó el vaso.
📌 | I thanked him. | Se lo agradecí.
🚫 | He ignored me. | No me hizo caso.
🤑 | They made a fortune. | Hicieron una fortuna.
🤝 | Make me a promise. | Hazme una promesa.
📌 | Do me a favour. | Hazme un favor.
👉 | Did you make reservations? | ¿Hiciste reservas?
📌 | I did a crossword puzzle. | Hice un crucigrama.
📌 | Do exercises. | Haz ejercicios.
🔨 | I have to do an errand. | Tengo que hacer un recado.
⭕ | He didn't do anything. | Él no hizo nada.
🔨 | We make sewing machines. | Hacemos máquinas de coser.
💼 | We do business with them. | Hacemos negocios con ellos.
♾️ | I always do the best I can. | Siempre hago lo mejor que puedo.
😡 | Don't make him angry. | No le hagas enfadar.
🔨 | They'll make you do it. | Harán que lo hagas.
👗 | I make dresses. | Hago vestidos.
🔊 | You make a lot of noise. | Haces mucho ruido.
🔨 | I want to make you an offer. | Quiero hacerte una oferta.
🔨 | I make a living teaching. | Me gano la vida enseñando.
⚖️ | Who made the decision? | ¿Quién tomó la decisión?
✅ | You made a good impression. | Hiciste una buena impresión.
🤑 | They'll make you rich. | Te harán rico.
✅ | Do it well. | Hazlo bien.
🛏️ | Make the bed. | Haz la cama.
💪 | Make an effort. | Haz un esfuerzo.
😂 | Don't make me laugh. | No me hagas reír.
🚫 | Don't do the housework. | No hagas los quehaceres domésticos.
🏫 | Do your homework. | Haz tus deberes(de escuela)
😰 | He makes me nervous. | Él me pone nervioso.
"""
}
