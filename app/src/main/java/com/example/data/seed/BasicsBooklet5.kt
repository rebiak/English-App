package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object BasicsBooklet5 {
    const val FOLDER_NAME = "Basics 5"

    val categoryNames: List<String> = (1..8).map { "Basics 5 - List $it" }

    private fun parseCards(rawText: String, categoryName: String): List<Flashcard> {
        return rawText.lineSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() && it.contains("|") }
            .map { line ->
                val parts = line.split("|").map { it.trim() }
                Flashcard(
                    emoji = parts.getOrElse(0) { "💡" }.ifBlank { "💡" },
                    english = parts.getOrElse(1) { "" },
                    spanish = parts.getOrElse(2) { "" },
                    example = parts.getOrElse(3) { "" },
                    exampleTranslation = parts.getOrElse(4) { "" },
                    type = parts.getOrElse(5) { "Word" },
                    category = categoryName,
                    cefrLevel = "A2",
                    status = FlashcardStatus.NEW.name
                )
            }
            .toList()
    }

    fun getAllCards(): List<Flashcard> {
        return getList1() + getList2() + getList3() + getList4() +
                getList5() + getList6() + getList7() + getList8()
    }

    private fun getList1(): List<Flashcard> = parseCards("""
🌍 | Review of Level 4 | Repaso del Nivel 4 | Let's review Level 4 | Repasemos el Nivel 4 | Phrase
🗺️ | Talking about continents and cardinal directions | Hablando sobre continentes y direcciones cardinales | We are talking about continents and cardinal directions | Estamos hablando sobre continentes y direcciones cardinales | Phrase
🌍 | Africa is east of South America. | África está al este de América del Sur. | Look, Africa is east of South America. | Mira, África está al este de América del Sur. | Phrase
🎭 | Act in a play | Actuar en una obra de teatro | I act in a play | Actúo en una obra de teatro | Phrase
🤿 | Learn how to dive | Aprender a bucear | I learn how to dive | Aprendo a bucear | Phrase
🎢 | Ride a roller coaster | Subirse a una montaña rusa | I ride a roller coaster | Me subo a una montaña rusa | Phrase
📚 | Read a lot of books | Leer muchos libros | I read a lot of books | Leo muchos libros | Phrase
🏆 | Win a competition | Ganar una competencia | I win a competition | Gano una competencia | Phrase
⏰ | Sleep late | Dormir hasta tarde | I sleep late on weekends | Duermo hasta tarde los fines de semana | Phrase
🎮 | He acted in a play when he was on vacation. | Él actuó en una obra de teatro cuando estaba de vacaciones. | Look, he acted in a play when he was on vacation. | Mira, él actuó en una obra de teatro cuando estaba de vacaciones. | Phrase
🤔 | What did she do when she was on vacation? | ¿Qué hizo ella cuando estaba de vacaciones? | Tell me, what did she do when she was on vacation? | Dime, ¿qué hizo ella cuando estaba de vacaciones? | Question
🎭 | She acted in a play. | Ella actuó en una obra de teatro. | Look, she acted in a play. | Mira, ella actuó en una obra de teatro. | Phrase
😌 | Relaxed | Relajado | I feel relaxed | Me siento relajado | Word
😰 | Nervous | Nervioso | The boy is nervous | El niño está nervioso | Word
😎 | Confident | Confiado | She is confident | Ella está confiada | Word
😳 | Shy | Tímido | The girl is shy | La niña es tímida | Word
👀 | Wide-awake | Muy despierto | He is wide-awake | Él está muy despierto | Word
😴 | Sleepy | Somnoliento | I am sleepy | Tengo sueño | Word
😌 | I felt relaxed, but he felt nervous. | Me sentí relajado, pero él se sintió nervioso. | Look, I felt relaxed, but he felt nervous. | Mira, me sentí relajado, pero él se sintió nervioso. | Phrase
🤔 | How did you feel when you rode the roller coaster? | ¿Cómo te sentiste cuando te subiste a la montaña rusa? | Tell me, how did you feel when you rode the roller coaster? | Dime, ¿cómo te sentiste cuando te subiste a la montaña rusa? | Question
😌 | I felt relaxed, but she felt nervous. | Me sentí relajado, pero ella se sintió nerviosa. | Look, I felt relaxed, but she felt nervous. | Mira, me sentí relajado, pero ella se sintió nerviosa. | Phrase
🏄 | The Surfing Lesson | La lección de surf | This is The Surfing Lesson | Esta es La lección de surf | Phrase
✨ | Trying new things | Probar cosas nuevas | I like trying new things | Me gusta probar cosas nuevas | Phrase
🏄 | Come on! Let's learn how to surf. | ¡Vamos! Aprendamos a surfear. | Come on! Let's learn how to surf. | ¡Vamos! Aprendamos a surfear. | Phrase
🎮 | No, thanks. I'd rather play video games. | No, gracias. Prefiero jugar videojuegos. | No, thanks. I'd rather play video games. | No, gracias. Prefiero jugar videojuegos. | Phrase
👍 | OK. | Está bien. | Yes, OK. | Sí, está bien. | Expression
🎉 | Sounds fun. I'm tired of playing video games. | Suena divertido. Estoy cansado de jugar videojuegos. | Sounds fun. I'm tired of playing video games. | Suena divertido. Estoy cansado de jugar videojuegos. | Phrase
🌟 | Great! | ¡Genial! | Yes, great! | ¡Sí, genial! | Expression
🦁 | Be brave. | Sé valiente. | Please, be brave. | Por favor, sé valiente. | Phrase
🧵 | Silk | Seda | The shirt is made of silk | La camisa es de seda | Word
📦 | Goods | Mercancías / Bienes | They sell goods | Ellos venden mercancías | Word
🧱 | Difficult | Difícil | The math test is difficult | La prueba de matemáticas es difícil | Word
📏 | Ruler | Regla | I use a ruler | Uso una regla | Word
🔄 | Return | Regresar / Volver | I return home | Regreso a casa | Word
⭐ | Become famous | Volverse famoso | He wants to become famous | Él quiere volverse famoso | Phrase
🤔 | What did Marco Polo do when he was 17? | ¿Qué hizo Marco Polo cuando tenía 17 años? | Tell me, what did Marco Polo do when he was 17? | Dime, ¿qué hizo Marco Polo cuando tenía 17 años? | Question
🐫 | He traveled on the Silk Road. | Él viajó por la Ruta de la Seda. | Look, he traveled on the Silk Road. | Mira, él viajó por la Ruta de la Seda. | Phrase
🤿 | He learned how to dive when he was on vacation. | Él aprendió a bucear cuando estaba de vacaciones. | Look, he learned how to dive when he was on vacation. | Mira, él aprendió a bucear cuando estaba de vacaciones. | Phrase
🎢 | He rode a roller coaster when he was on vacation. | Él se subió a una montaña rusa cuando estaba de vacaciones. | Look, he rode a roller coaster when he was on vacation. | Mira, él se subió a una montaña rusa cuando estaba de vacaciones. | Phrase
📚 | He read a lot of books when he was on vacation. | Él leyó muchos libros cuando estaba de vacaciones. | Look, he read a lot of books when he was on vacation. | Mira, él leyó muchos libros cuando estaba de vacaciones. | Phrase
🏆 | He won a competition when he was on vacation. | Él ganó una competencia cuando estaba de vacaciones. | Look, he won a competition when he was on vacation. | Mira, él ganó una competencia cuando estaba de vacaciones. | Phrase
⏰ | He slept late when he was on vacation. | Él durmió hasta tarde cuando estaba de vacaciones. | Look, he slept late when he was on vacation. | Mira, él durmió hasta tarde cuando estaba de vacaciones. | Phrase
🤿 | She learned how to dive when she was on vacation. | Ella aprendió a bucear cuando estaba de vacaciones. | Look, she learned how to dive when she was on vacation. | Mira, ella aprendió a bucear cuando estaba de vacaciones. | Phrase
🎢 | She rode a roller coaster when she was on vacation. | Ella se subió a una montaña rusa cuando estaba de vacaciones. | Look, she rode a roller coaster when she was on vacation. | Mira, ella se subió a una montaña rusa cuando estaba de vacaciones. | Phrase
📚 | She read a lot of books when she was on vacation. | Ella leyó muchos libros cuando estaba de vacaciones. | Look, she read a lot of books when she was on vacation. | Mira, ella leyó muchos libros cuando estaba de vacaciones. | Phrase
🏆 | She won a competition when she was on vacation. | Ella ganó una competencia cuando estaba de vacaciones. | Look, she won a competition when she was on vacation. | Mira, ella ganó una competencia cuando estaba de vacaciones. | Phrase
⏰ | She slept late when she was on vacation. | Ella durmió hasta tarde cuando estaba de vacaciones. | Look, she slept late when she was on vacation. | Mira, ella durmió hasta tarde cuando estaba de vacaciones. | Phrase
🤔 | What did he do when he was on vacation? | ¿Qué hizo él cuando estaba de vacaciones? | Tell me, what did he do when he was on vacation? | Dime, ¿qué hizo él cuando estaba de vacaciones? | Question
🤿 | He learned how to dive. | Él aprendió a bucear. | Look, he learned how to dive. | Mira, él aprendió a bucear. | Phrase
🎢 | He rode a roller coaster. | Él se subió a una montaña rusa. | Look, he rode a roller coaster. | Mira, él se subió a una montaña rusa. | Phrase
📚 | He read a lot of books. | Él leyó muchos libros. | Look, he read a lot of books. | Mira, él leyó muchos libros. | Phrase
🏆 | He won a competition. | Él ganó una competencia. | Look, he won a competition. | Mira, él ganó una competencia. | Phrase
⏰ | He slept late. | Él durmió hasta tarde. | Look, he slept late. | Mira, él durmió hasta tarde. | Phrase
🤿 | She learned how to dive. | Ella aprendió a bucear. | Look, she learned how to dive. | Mira, ella aprendió a bucear. | Phrase
🎢 | She rode a roller coaster. | Ella se subió a una montaña rusa. | Look, she rode a roller coaster. | Mira, ella se subió a una montaña rusa. | Phrase
📚 | She read a lot of books. | Ella leyó muchos libros. | Look, she read a lot of books. | Mira, ella leyó muchos libros. | Phrase
🏆 | She won a competition. | Ella ganó una competencia. | Look, she won a competition. | Mira, ella ganó una competencia. | Phrase
⏰ | She slept late. | Ella durmió hasta tarde. | Look, she slept late. | Mira, ella durmió hasta tarde. | Phrase
😌 | I felt relaxed, but he felt confident. | Me sentí relajado, pero él se sintió confiado. | Look, I felt relaxed, but he felt confident. | Mira, me sentí relajado, pero él se sintió confiado. | Phrase
😌 | I felt relaxed, but he felt shy. | Me sentí relajado, pero él se sintió tímido. | Look, I felt relaxed, but he felt shy. | Mira, me sentí relajado, pero él se sintió tímido. | Phrase
😌 | I felt relaxed, but he felt wide-awake. | Me sentí relajado, pero él se sintió muy despierto. | Look, I felt relaxed, but he felt wide-awake. | Mira, me sentí relajado, pero él se sintió muy despierto. | Phrase
😌 | I felt relaxed, but he felt sleepy. | Me sentí relajado, pero él se sintió somnoliento. | Look, I felt relaxed, but he felt sleepy. | Mira, me sentí relajado, pero él se sintió somnoliento. | Phrase
😌 | I felt relaxed, but she felt confident. | Me sentí relajado, pero ella se sintió confiada. | Look, I felt relaxed, but she felt confident. | Mira, me sentí relajado, pero ella se sintió confiada. | Phrase
😌 | I felt relaxed, but she felt shy. | Me sentí relajado, pero ella se sintió tímida. | Look, I felt relaxed, but she felt shy. | Mira, me sentí relajado, pero ella se sintió tímida. | Phrase
😌 | I felt relaxed, but she felt wide-awake. | Me sentí relajado, pero ella se sintió muy despierta. | Look, I felt relaxed, but she felt wide-awake. | Mira, me sentí relajado, pero ella se sintió muy despierta. | Phrase
😌 | I felt relaxed, but she felt sleepy. | Me sentí relajado, pero ella se sintió somnolienta. | Look, I felt relaxed, but she felt sleepy. | Mira, me sentí relajado, pero ella se sintió somnolienta. | Phrase
😰 | I felt nervous, but he felt relaxed. | Me sentí nervioso, pero él se sintió relajado. | Look, I felt nervous, but he felt relaxed. | Mira, me sentí nervioso, pero él se sintió relajado. | Phrase
😰 | I felt nervous, but he felt confident. | Me sentí nervioso, pero él se sintió confiado. | Look, I felt nervous, but he felt confident. | Mira, me sentí nervioso, pero él se sintió confiado. | Phrase
😰 | I felt nervous, but he felt shy. | Me sentí nervioso, pero él se sintió tímido. | Look, I felt nervous, but he felt shy. | Mira, me sentí nervioso, pero él se sintió tímido. | Phrase
😰 | I felt nervous, but he felt wide-awake. | Me sentí nervioso, pero él se sintió muy despierto. | Look, I felt nervous, but he felt wide-awake. | Mira, me sentí nervioso, pero él se sintió muy despierto. | Phrase
😰 | I felt nervous, but he felt sleepy. | Me sentí nervioso, pero él se sintió somnoliento. | Look, I felt nervous, but he felt sleepy. | Mira, me sentí nervioso, pero él se sintió somnoliento. | Phrase
😰 | I felt nervous, but she felt relaxed. | Me sentí nervioso, pero ella se sintió relajada. | Look, I felt nervous, but she felt relaxed. | Mira, me sentí nervioso, pero ella se sintió relajada. | Phrase
😰 | I felt nervous, but she felt confident. | Me sentí nervioso, pero ella se sintió confiada. | Look, I felt nervous, but she felt confident. | Mira, me sentí nervioso, pero ella se sintió confiada. | Phrase
😰 | I felt nervous, but she felt shy. | Me sentí nervioso, pero ella se sintió tímida. | Look, I felt nervous, but she felt shy. | Mira, me sentí nervioso, pero ella se sintió tímida. | Phrase
😰 | I felt nervous, but she felt wide-awake. | Me sentí nervioso, pero ella se sintió muy despierta. | Look, I felt nervous, but she felt wide-awake. | Mira, me sentí nervioso, pero ella se sintió muy despierta. | Phrase
😰 | I felt nervous, but she felt sleepy. | Me sentí nervioso, pero ella se sintió somnolienta. | Look, I felt nervous, but she felt sleepy. | Mira, me sentí nervioso, pero ella se sintió somnolienta. | Phrase
🤔 | How did you feel when you learned how to dive? | ¿Cómo te sentiste cuando aprendiste a bucear? | Tell me, how did you feel when you learned how to dive? | Dime, ¿cómo te sentiste cuando aprendiste a bucear? | Question
📖 | How did you feel when you read a lot of books? | ¿Cómo te sentiste cuando leíste muchos libros? | Tell me, how did you feel when you read a lot of books? | Dime, ¿cómo te sentiste cuando leíste muchos libros? | Question
🤔 | How did you feel when you won a competition? | ¿Cómo te sentiste cuando ganaste una competencia? | Tell me, how did you feel when you won a competition? | Dime, ¿cómo te sentiste cuando ganaste una competencia? | Question
🤔 | How did you feel when you slept late? | ¿Cómo te sentiste cuando dormiste hasta tarde? | Tell me, how did you feel when you slept late? | Dime, ¿cómo te sentiste cuando dormiste hasta tarde? | Question
😌 | I felt relaxed, but he felt nervous. | Me sentí relajado, pero él se sintió nervioso. | Look, I felt relaxed, but he felt nervous. | Mira, me sentí relajado, pero él se sintió nervioso. | Phrase
    """.trimIndent(), "Basics 5 - List 1")

    private fun getList2(): List<Flashcard> = parseCards("""
🐛 | Study insects | Estudiar insectos | I study insects | Estudio insectos | Phrase
🌳 | Identify trees | Identificar árboles | I identify trees | Identifico árboles | Phrase
🍓 | Pick wild strawberries | Coger fresas silvestres | I pick wild strawberries | Cojo fresas silvestres | Phrase
🐾 | Finds animal tracks | Encuentra huellas de animales | He finds animal tracks | Él encuentra huellas de animales | Phrase
🦇 | Explore a cave | Explorar una cueva | I explore a cave | Exploro una cueva | Phrase
🍃 | Collect leaves | Recolectar hojas | I collect leaves | Recolecto hojas | Phrase
🤔 | What was she doing in the morning? | ¿Qué estaba haciendo ella por la mañana? | Tell me, what was she doing in the morning? | Dime, ¿qué estaba haciendo ella por la mañana? | Question
🤔 | What was she doing in the afternoon? | ¿Qué estaba haciendo ella por la tarde? | Tell me, what was she doing in the afternoon? | Dime, ¿qué estaba haciendo ella por la tarde? | Question
🐛 | She was studying insects. | Ella estaba estudiando insectos. | Look, she was studying insects. | Mira, ella estaba estudiando insectos. | Phrase
🤔 | Was he studying insect in the morning? | ¿Estaba él estudiando insecto por la mañana? | Tell me, was he studying insect in the morning? | Dime, ¿estaba él estudiando insecto por la mañana? | Question
🤔 | Was he studying insect in the afternoon? | ¿Estaba él estudiando insecto por la tarde? | Tell me, was he studying insect in the afternoon? | Dime, ¿estaba él estudiando insecto por la tarde? | Question
👍 | Yes, he was. | Sí, él estaba. | Yes, he was. | Sí, él estaba. | Phrase
🙅 | No, he wasn't. | No, él no estaba. | No, he wasn't. | No, él no estaba. | Phrase
🌳 | He was identifying trees. | Él estaba identificando árboles. | Look, he was identifying trees. | Mira, él estaba identificando árboles. | Phrase
⛺ | Set up the tent | Armar la tienda de campaña | I set up the tent | Armo la tienda de campaña | Phrase
🔥 | Build a campfire | Hacer una fogata | I build a campfire | Hago una fogata | Phrase
🐟 | Roast fish | Asar pescado | I roast fish | Aso pescado | Phrase
📖 | Tell stories | Contar historias | I tell stories | Cuento historias | Phrase
🧯 | Put out the campfire | Apagar la fogata | I put out the campfire | Apago la fogata | Phrase
🌟 | Look at the stars | Mirar las estrellas | I look at the stars | Miro las estrellas | Phrase
⛺ | She was setting up the tent when I arrived at the campsite. | Ella estaba armando la tienda de campaña cuando llegué al campamento. | Look, she was setting up the tent when I arrived at the campsite. | Mira, ella estaba armando la tienda de campaña cuando llegué al campamento. | Phrase
⛺ | When I arrived at the campsite, she was setting up the tent. | Cuando llegué al campamento, ella estaba armando la tienda de campaña. | Look, when I arrived at the campsite, she was setting up the tent. | Mira, cuando llegué al campamento, ella estaba armando la tienda de campaña. | Phrase
🤔 | What were you doing when you saw the deer? | ¿Qué estabas haciendo cuando viste al ciervo? | Tell me, what were you doing when you saw the deer? | Dime, ¿qué estabas haciendo cuando viste al ciervo? | Question
⛺ | I was setting up the tent. | Estaba armando la tienda de campaña. | Look, I was setting up the tent. | Mira, estaba armando la tienda de campaña. | Phrase
⛰️ | The Cave | La cueva | This is The Cave | Esta es La cueva | Phrase
🤔 | Which way is the campsite? | ¿Por dónde es el campamento? | Tell me, which way is the campsite? | Dime, ¿por dónde es el campamento? | Question
📍 | It's that way. | Es por ahí. | Look, it's that way. | Mira, es por ahí. | Phrase
🙏 | Thanks a lot! | ¡Muchas gracias! | Thanks a lot! | ¡Muchas gracias! | Expression
🤔 | Which way is the campsite? | ¿Por dónde es el campamento? | Tell me, which way is the campsite? | Dime, ¿por dónde es el campamento? | Question
🙇 | Sorry, I don't know. | Lo siento, no lo sé. | Sorry, I don't know. | Lo siento, no lo sé. | Phrase
🙏 | Thanks, anyway. | Gracias de todos modos. | Thanks, anyway. | Gracias de todos modos. | Phrase
🤝 | Be helpful. | Sé servicial. | Please, be helpful. | Por favor, sé servicial. | Phrase
💨 | Oxygen | Oxígeno | We need oxygen | Necesitamos oxígeno | Word
🌱 | Seed | Semilla | The seed is small | La semilla es pequeña | Word
📏 | Size | Tamaño | What size is it? | ¿De qué tamaño es? | Word
🌱 | Root | Raíz | The root is under the ground | La raíz está bajo tierra | Word
🚇 | Underground | Subterráneo | The root is underground | La raíz es subterránea | Word
🌿 | Stem | Tallo | The stem is green | El tallo es verde | Word
🤔 | Which parts of the plant are these? | ¿Qué partes de la planta son estas? | Tell me, which parts of the plant are these? | Dime, ¿qué partes de la planta son estas? | Question
🌱 | Those are the seeds. | Esas son las semillas. | Look, those are the seeds. | Mira, esas son las semillas. | Phrase
🤔 | What do they do? | ¿Qué hacen? | Tell me, what do they do? | Dime, ¿qué hacen? | Question
🌱 | They grow and become new plants. | Crecen y se convierten en nuevas plantas. | Look, they grow and become new plants. | Mira, crecen y se convierten en nuevas plantas. | Phrase
🌳 | He was picking wild strawberries when I arrived at the campsite. | Él estaba cogiendo fresas silvestres cuando llegué al campamento. | Look, he was picking wild strawberries when I arrived at the campsite. | Mira, él estaba cogiendo fresas silvestres cuando llegué al campamento. | Phrase
🌳 | When I arrived at the campsite, he was picking wild strawberries. | Cuando llegué al campamento, él estaba cogiendo fresas silvestres. | Look, when I arrived at the campsite, he was picking wild strawberries. | Mira, cuando llegué al campamento, él estaba cogiendo fresas silvestres. | Phrase
🐾 | He was finding animal tracks when I arrived at the campsite. | Él estaba encontrando huellas de animales cuando llegué al campamento. | Look, he was finding animal tracks when I arrived at the campsite. | Mira, él estaba encontrando huellas de animales cuando llegué al campamento. | Phrase
🐾 | When I arrived at the campsite, he was finding animal tracks. | Cuando llegué al campamento, él estaba encontrando huellas de animales. | Look, when I arrived at the campsite, he was finding animal tracks. | Mira, cuando llegué al campamento, él estaba encontrando huellas de animales. | Phrase
🦇 | He was exploring a cave when I arrived at the campsite. | Él estaba explorando una cueva cuando llegué al campamento. | Look, he was exploring a cave when I arrived at the campsite. | Mira, él estaba explorando una cueva cuando llegué al campamento. | Phrase
🦇 | When I arrived at the campsite, he was exploring a cave. | Cuando llegué al campamento, él estaba explorando una cueva. | Look, when I arrived at the campsite, he was exploring a cave. | Mira, cuando llegué al campamento, él estaba explorando una cueva. | Phrase
🍃 | He was collecting leaves when I arrived at the campsite. | Él estaba recolectando hojas cuando llegué al campamento. | Look, he was collecting leaves when I arrived at the campsite. | Mira, él estaba recolectando hojas cuando llegué al campamento. | Phrase
🍃 | When I arrived at the campsite, he was collecting leaves. | Cuando llegué al campamento, él estaba recolectando hojas. | Look, when I arrived at the campsite, he was collecting leaves. | Mira, cuando llegué al campamento, él estaba recolectando hojas. | Phrase
🔥 | He was building a campfire when I arrived at the campsite. | Él estaba haciendo una fogata cuando llegué al campamento. | Look, he was building a campfire when I arrived at the campsite. | Mira, él estaba haciendo una fogata cuando llegué al campamento. | Phrase
🔥 | When I arrived at the campsite, he was building a campfire. | Cuando llegué al campamento, él estaba haciendo una fogata. | Look, when I arrived at the campsite, he was building a campfire. | Mira, cuando llegué al campamento, él estaba haciendo una fogata. | Phrase
🐟 | He was roasting fish when I arrived at the campsite. | Él estaba asando pescado cuando llegué al campamento. | Look, he was roasting fish when I arrived at the campsite. | Mira, él estaba asando pescado cuando llegué al campamento. | Phrase
🐟 | When I arrived at the campsite, he was roasting fish. | Cuando llegué al campamento, él estaba asando pescado. | Look, when I arrived at the campsite, he was roasting fish. | Mira, cuando llegué al campamento, él estaba asando pescado. | Phrase
📖 | He was telling stories when I arrived at the campsite. | Él estaba contando historias cuando llegué al campamento. | Look, he was telling stories when I arrived at the campsite. | Mira, él estaba contando historias cuando llegué al campamento. | Phrase
📖 | When I arrived at the campsite, he was telling stories. | Cuando llegué al campamento, él estaba contando historias. | Look, when I arrived at the campsite, he was telling stories. | Mira, cuando llegué al campamento, él estaba contando historias. | Phrase
🧯 | He was putting out the campfire when I arrived at the campsite. | Él estaba apagando la fogata cuando llegué al campamento. | Look, he was putting out the campfire when I arrived at the campsite. | Mira, él estaba apagando la fogata cuando llegué al campamento. | Phrase
🧯 | When I arrived at the campsite, he was putting out the campfire. | Cuando llegué al campamento, él estaba apagando la fogata. | Look, when I arrived at the campsite, he was putting out the campfire. | Mira, cuando llegué al campamento, él estaba apagando la fogata. | Phrase
🌟 | He was looking at the stars when I arrived at the campsite. | Él estaba mirando las estrellas cuando llegué al campamento. | Look, he was looking at the stars when I arrived at the campsite. | Mira, él estaba mirando las estrellas cuando llegué al campamento. | Phrase
🌟 | When I arrived at the campsite, he was looking at the stars. | Cuando llegué al campamento, él estaba mirando las estrellas. | Look, when I arrived at the campsite, he was looking at the stars. | Mira, cuando llegué al campamento, él estaba mirando las estrellas. | Phrase
🐻 | What were you doing when you saw the bear? | ¿Qué estabas haciendo cuando viste al oso? | Tell me, what were you doing when you saw the bear? | Dime, ¿qué estabas haciendo cuando viste al oso? | Question
🐦 | What were you doing when you saw the bird? | ¿Qué estabas haciendo cuando viste al pájaro? | Tell me, what were you doing when you saw the bird? | Dime, ¿qué estabas haciendo cuando viste al pájaro? | Question
🐛 | I was studying insects. | Estaba estudiando insectos. | Look, I was studying insects. | Mira, estaba estudiando insectos. | Phrase
🌳 | I was identifying trees. | Estaba identificando árboles. | Look, I was identifying trees. | Mira, estaba identificando árboles. | Phrase
🍓 | I was picking wild strawberries. | Estaba cogiendo fresas silvestres. | Look, I was picking wild strawberries. | Mira, estaba cogiendo fresas silvestres. | Phrase
🐾 | I was finding animal tracks. | Estaba encontrando huellas de animales. | Look, I was finding animal tracks. | Mira, estaba encontrando huellas de animales. | Phrase
🦇 | I was exploring a cave. | Estaba explorando una cueva. | Look, I was exploring a cave. | Mira, estaba explorando una cueva. | Phrase
🍃 | I was collecting leaves. | Estaba recolectando hojas. | Look, I was collecting leaves. | Mira, estaba recolectando hojas. | Phrase
🔥 | I was building a campfire. | Estaba haciendo una fogata. | Look, I was building a campfire. | Mira, estaba haciendo una fogata. | Phrase
🐟 | I was roasting fish. | Estaba asando pescado. | Look, I was roasting fish. | Mira, estaba asando pescado. | Phrase
📖 | I was telling stories. | Estaba contando historias. | Look, I was telling stories. | Mira, estaba contando historias. | Phrase
🧯 | I was putting out the campfire. | Estaba apagando la fogata. | Look, I was putting out the campfire. | Mira, estaba apagando la fogata. | Phrase
🌟 | I was looking at the stars. | Estaba mirando las estrellas. | Look, I was looking at the stars. | Mira, estaba mirando las estrellas. | Phrase
    """.trimIndent(), "Basics 5 - List 2")

    private fun getList3(): List<Flashcard> = parseCards("""
🍕 | Order pizzas | Pedir pizzas | I order pizzas | Pido pizzas | Phrase
🧁 | Bake cupcakes | Hornear pastelitos | I bake cupcakes | Horneo pastelitos | Phrase
🧃 | Bring fruit juice | Traer jugo de fruta | I bring fruit juice | Traigo jugo de fruta | Phrase
🎵 | Choose the music | Elegir la música | I choose the music | Elijo la música | Phrase
🎈 | Make decorations | Hacer decoraciones | I make decorations | Hago decoraciones | Phrase
🎈 | Buy balloons | Comprar globos | I buy balloons | Compro globos | Phrase
🎉 | I'll order pizzas for the party. | Pediré pizzas para la fiesta. | Look, I'll order pizzas for the party. | Mira, pediré pizzas para la fiesta. | Phrase
🎉 | We'll order pizzas for the party. | Pediremos pizzas para la fiesta. | Look, We'll order pizzas for the party. | Mira, pediremos pizzas para la fiesta. | Phrase
🤔 | What will he do? | ¿Qué hará él? | Tell me, what will he do? | Dime, ¿qué hará él? | Question
🍕 | He'll order the pizzas. | Él pedirá las pizzas. | Look, he'll order the pizzas. | Mira, él pedirá las pizzas. | Phrase
🧃 | Pour the juice | Servir el jugo | I pour the juice | Sirvo el jugo | Phrase
🍕 | Serve the pizzas | Servir las pizzas | I serve the pizzas | Sirvo las pizzas | Phrase
🎵 | Set up the music | Configurar la música | I set up the music | Configuro la música | Phrase
🎈 | Blow up the balloons | Inflar los globos | I blow up the balloons | Inflo los globos | Phrase
🎈 | Put up the decorations | Colocar las decoraciones | I put up the decorations | Coloco las decoraciones | Phrase
🧁 | Put out the cupcakes | Sacar los pastelitos | I put out the cupcakes | Saco los pastelitos | Phrase
🧃 | Will she pour the juice? | ¿Servirá ella el jugo? | Tell me, will she pour the juice? | Dime, ¿servirá ella el jugo? | Question
👍 | Yes, she will. | Sí, ella lo hará. | Yes, she will. | Sí, ella lo hará. | Phrase
🙅 | No, she won't. | No, ella no lo hará. | No, she won't. | No, ella no lo hará. | Phrase
🧃 | Who will pour the juice? | ¿Quién servirá el jugo? | Tell me, who will pour the juice? | Dime, ¿quién servirá el jugo? | Question
👥 | They will. | Ellos lo harán. | Yes, they will. | Sí, ellos lo harán. | Phrase
⚠️ | It's Not Safe | No es seguro | This is It's Not Safe | Esta es No es seguro | Phrase
🎉 | Talking about planning a party | Hablar sobre planificar una fiesta | We are talking about planning a party | Estamos hablando sobre planificar una fiesta | Phrase
⚠️ | Talking about traffic safety | Hablar sobre seguridad vial | We are talking about traffic safety | Estamos hablando sobre seguridad vial | Phrase
🧃 | We're out of juice. Could you get some more? | Se nos acabó el jugo. ¿Podrías traer un poco más? | We're out of juice. Could you get some more? | Se nos acabó el jugo. ¿Podrías traer un poco más? | Phrase
👍 | Sure, no problem. | Claro, no hay problema. | Sure, no problem. | Claro, no hay problema. | Phrase
🙏 | Thanks. | Gracias. | Thanks a lot. | Muchas gracias. | Expression
🏃 | I can't right now. I'm busy. | No puedo ahora mismo. Estoy ocupado. | I can't right now. I'm busy. | No puedo ahora mismo. Estoy ocupado. | Phrase
👍 | That's OK. | Está bien. | That's OK. | Está bien. | Expression
🚶 | Let's walk across the highway. | Crucemos la carretera caminando. | Let's walk across the highway. | Crucemos la carretera caminando. | Phrase
🚀 | We'll get there faster. | Llegaremos más rápido. | We'll get there faster. | Llegaremos más rápido. | Phrase
⚠️ | It's not safe. | No es seguro. | It's not safe. | No es seguro. | Phrase
🛡️ | Be safe. | Cuídate. | Please, be safe. | Por favor, cuídate. | Phrase
🎉 | Celebrate | Celebrar | We celebrate | Celebramos | Word
🗓️ | Seasons | Estaciones | There are four seasons | Hay cuatro estaciones | Word
📜 | Tradition | Tradición | It is a tradition | Es una tradición | Word
😋 | Delicious | Delicioso | The food is delicious | La comida está deliciosa | Word
👶 | Child | Niño / Niña | The child is happy | El niño está feliz | Word
💃 | Samba parade | Desfile de samba | We see a samba parade | Vemos un desfile de samba | Word
🌸 | What will people in Japan do in the spring? | ¿Qué hará la gente en Japón en la primavera? | Tell me, what will people in Japan do in the spring? | Dime, ¿qué hará la gente en Japón en la primavera? | Question
🎉 | They'll celebrate Children's Day. | Celebrarán el Día del Niño. | Look, they'll celebrate Children's Day. | Mira, celebrarán el Día del Niño. | Phrase
🎉 | I'll bake cupcakes for the party. | Hornearé pastelitos para la fiesta. | Look, I'll bake cupcakes for the party. | Mira, hornearé pastelitos para la fiesta. | Phrase
🎉 | We'll bake cupcakes for the party. | Hornearemos pastelitos para la fiesta. | Look, We'll bake cupcakes for the party. | Mira, hornearemos pastelitos para la fiesta. | Phrase
🧁 | He'll bake the cupcakes. | Él horneará los pastelitos. | Look, he'll bake the cupcakes. | Mira, él horneará los pastelitos. | Phrase
🎉 | I'll bring fruit juice for the party. | Traeré jugo de fruta para la fiesta. | Look, I'll bring fruit juice for the party. | Mira, traeré jugo de fruta para la fiesta. | Phrase
🎉 | We'll bring fruit juice for the party. | Traeremos jugo de fruta para la fiesta. | Look, We'll bring fruit juice for the party. | Mira, traeremos jugo de fruta para la fiesta. | Phrase
🧃 | He'll bring the fruit juice. | Él traerá el jugo de fruta. | Look, he'll bring the fruit juice. | Mira, él traerá el jugo de fruta. | Phrase
🎉 | I'll choose the music for the party. | Elegiré la música para la fiesta. | Look, I'll choose the music for the party. | Mira, elegiré la música para la fiesta. | Phrase
🎉 | We'll choose the music for the party. | Elegiremos la música para la fiesta. | Look, We'll choose the music for the party. | Mira, elegiremos la música para la fiesta. | Phrase
🎵 | He'll choose the music. | Él elegirá la música. | Look, he'll choose the music. | Mira, él elegirá la música. | Phrase
🎉 | I'll make decorations for the party. | Haré decoraciones para la fiesta. | Look, I'll make decorations for the party. | Mira, haré decoraciones para la fiesta. | Phrase
🎉 | We'll make decorations for the party. | Haremos decoraciones para la fiesta. | Look, We'll make decorations for the party. | Mira, haremos decoraciones para la fiesta. | Phrase
🎈 | He'll make the decorations. | Él hará las decoraciones. | Look, he'll make the decorations. | Mira, él hará las decoraciones. | Phrase
🎉 | I'll buy balloons for the party. | Compraré globos para la fiesta. | Look, I'll buy balloons for the party. | Mira, compraré globos para la fiesta. | Phrase
🎉 | We'll buy balloons for the party. | Compraremos globos para la fiesta. | Look, We'll buy balloons for the party. | Mira, compraremos globos para la fiesta. | Phrase
🎈 | He'll buy the balloons. | Él comprará los globos. | Look, he'll buy the balloons. | Mira, él comprará los globos. | Phrase
🍕 | Will he order pizzas? | ¿Pedirá él pizzas? | Tell me, will he order pizzas? | Dime, ¿pedirá él pizzas? | Question
🧁 | Will she bake cupcakes? | ¿Horneará ella pastelitos? | Tell me, will she bake cupcakes? | Dime, ¿horneará ella pastelitos? | Question
👍 | Yes, he will. | Sí, él lo hará. | Yes, he will. | Sí, él lo hará. | Phrase
🙅 | No, he won't. | No, él no lo hará. | No, he won't. | No, él no lo hará. | Phrase
🧃 | Will he bring fruit juice? | ¿Traerá él jugo de fruta? | Tell me, will he bring fruit juice? | Dime, ¿traerá él jugo de fruta? | Question
🎵 | Will she choose the music? | ¿Elegirá ella la música? | Tell me, will she choose the music? | Dime, ¿elegirá ella la música? | Question
🎈 | Will he make decorations? | ¿Hará él decoraciones? | Tell me, will he make decorations? | Dime, ¿hará él decoraciones? | Question
🎈 | Will she buy balloons? | ¿Comprará ella globos? | Tell me, will she buy balloons? | Dime, ¿comprará ella globos? | Question
🍕 | Who will order pizzas? | ¿Quién pedirá pizzas? | Tell me, who will order pizzas? | Dime, ¿quién pedirá pizzas? | Question
🧁 | Who will bake cupcakes? | ¿Quién horneará pastelitos? | Tell me, who will bake cupcakes? | Dime, ¿quién horneará pastelitos? | Question
👥 | They will. | Ellos lo harán. | Yes, they will. | Sí, ellos lo harán. | Phrase
🧃 | Who will bring fruit juice? | ¿Quién traerá jugo de fruta? | Tell me, who will bring fruit juice? | Dime, ¿quién traerá jugo de fruta? | Question
🎵 | Who will choose the music? | ¿Quién elegirá la música? | Tell me, who will choose the music? | Dime, ¿quién elegirá la música? | Question
🎈 | Who will make decorations? | ¿Quién hará decoraciones? | Tell me, who will make decorations? | Dime, ¿quién hará decoraciones? | Question
🎈 | Who will buy balloons? | ¿Quién comprará globos? | Tell me, who will buy balloons? | Dime, ¿quién comprará globos? | Question
🧃 | Will he pour the juice? | ¿Servirá él el jugo? | Tell me, will he pour the juice? | Dime, ¿servirá él el jugo? | Question
🍕 | Will she serve the pizzas? | ¿Servirá ella las pizzas? | Tell me, will she serve the pizzas? | Dime, ¿servirá ella las pizzas? | Question
👍 | Yes, he will. | Sí, él lo hará. | Yes, he will. | Sí, él lo hará. | Phrase
🙅 | No, he won't. | No, él no lo hará. | No, he won't. | No, él no lo hará. | Phrase
🎵 | Will he set up the music? | ¿Configurará él la música? | Tell me, will he set up the music? | Dime, ¿configurará él la música? | Question
🎈 | Will she blow up the balloons? | ¿Inflará ella los globos? | Tell me, will she blow up the balloons? | Dime, ¿inflará ella los globos? | Question
🤔 | Will he put up the decorations? | ¿Colocará él las decoraciones? | Tell me, will he put up the decorations? | Dime, ¿colocará él las decoraciones? | Question
🧁 | Will she put out the cupcakes? | ¿Sacará ella los pastelitos? | Tell me, will she put out the cupcakes? | Dime, ¿sacará ella los pastelitos? | Question
🍕 | Who will serve the pizzas? | ¿Quién servirá las pizzas? | Tell me, who will serve the pizzas? | Dime, ¿quién servirá las pizzas? | Question
🎵 | Who will set up the music? | ¿Quién configurará la música? | Tell me, who will set up the music? | Dime, ¿quién configurará la música? | Question
🎈 | Who will blow up the balloons? | ¿Quién inflará los globos? | Tell me, who will blow up the balloons? | Dime, ¿quién inflará los globos? | Question
🤔 | Who will put up the decorations? | ¿Quién colocará las decoraciones? | Tell me, who will put up the decorations? | Dime, ¿quién colocará las decoraciones? | Question
🧁 | Who will put out the cupcakes? | ¿Quién sacará los pastelitos? | Tell me, who will put out the cupcakes? | Dime, ¿quién sacará los pastelitos? | Question
❄️ | What will people in Canada do in the winter? | ¿Qué hará la gente en Canadá en el invierno? | Tell me, what will people in Canada do in the winter? | Dime, ¿qué hará la gente en Canadá en el invierno? | Question
☀️ | What will people in Mexico do in the summer? | ¿Qué hará la gente en México en el verano? | Tell me, what will people in Mexico do in the summer? | Dime, ¿qué hará la gente en México en el verano? | Question
🍂 | What will people in Egypt do in the fall? | ¿Qué hará la gente en Egipto en el otoño? | Tell me, what will people in Egypt do in the fall? | Dime, ¿qué hará la gente en Egipto en el otoño? | Question
    """.trimIndent(), "Basics 5 - List 3")

    private fun getList4(): List<Flashcard> = parseCards("""
🦜 | Colorful macaw | Guacamaya colorida | The colorful macaw flies | La guacamaya colorida vuela | Word
🐦 | Plain egret | Garceta simple | The plain egret is white | La garceta simple es blanca | Word
🐆 | Dangerous jaguar | Jaguar peligroso | The dangerous jaguar runs | El jaguar peligroso corre | Word
🐬 | Friendly river dolphin | Delfín de río amistoso | The friendly river dolphin swims | El delfín de río amistoso nada | Word
🐒 | Energetic spider monkey | Mono araña enérgico | The energetic spider monkey jumps | El mono araña enérgico salta | Word
🦥 | Calm sloth | Perezoso tranquilo | The calm sloth sleeps | El perezoso tranquilo duerme | Word
🦜 | The macaw is more colorful than the egret. | La guacamaya es más colorida que la garceta. | Look, the macaw is more colorful than the egret. | Mira, la guacamaya es más colorida que la garceta. | Phrase
🤔 | Is the macaw more colorful than the egret? | ¿Es la guacamaya más colorida que la garceta? | Tell me, is the macaw more colorful than the egret? | Dime, ¿es la guacamaya más colorida que la garceta? | Question
👍 | Yes, it is. | Sí, lo es. | Yes, it is. | Sí, lo es. | Phrase
🙅 | No, it isn't. | No, no lo es. | No, it isn't. | No, no lo es. | Phrase
🧩 | Easy puzzle | Rompecabezas fácil | The easy puzzle is fun | El rompecabezas fácil es divertido | Word
🩴 | Comfortable sandals | Sandalias cómodas | I wear comfortable sandals | Uso sandalias cómodas | Word
📿 | Cheap bracelet | Pulsera barata | The cheap bracelet is nice | La pulsera barata es bonita | Word
🧩 | Difficult puzzle | Rompecabezas difícil | The difficult puzzle is hard | El rompecabezas difícil es duro | Word
🩴 | Uncomfortable sandals | Sandalias incómodas | The uncomfortable sandals hurt | Las sandalias incómodas duelen | Word
📿 | Expensive bracelet | Pulsera cara | The expensive bracelet is gold | La pulsera cara es de oro | Word
🧩 | This puzzle is the easiest one here. | Este rompecabezas es el más fácil aquí. | Look, this puzzle is the easiest one here. | Mira, este rompecabezas es el más fácil aquí. | Phrase
🩴 | These sandals are the most comfortable ones here. | Estas sandalias son las más cómodas aquí. | Look, these sandals are the most comfortable ones here. | Mira, estas sandalias son las más cómodas aquí. | Phrase
🧩 | Which puzzle is the easiest? | ¿Cuál rompecabezas es el más fácil? | Tell me, which puzzle is the easiest? | Dime, ¿cuál rompecabezas es el más fácil? | Question
🧩 | This puzzle. | Este rompecabezas. | Look, this puzzle. | Mira, este rompecabezas. | Phrase
🤔 | Which sandals are the most comfortable? | ¿Cuáles sandalias son las más cómodas? | Tell me, which sandals are the most comfortable? | Dime, ¿cuáles sandalias son las más cómodas? | Question
🩴 | These sandals. | Estas sandalias. | Look, these sandals. | Mira, estas sandalias. | Phrase
🏂 | The New Snowboard | El nuevo snowboard | This is The New Snowboard | Este es El nuevo snowboard | Phrase
✨ | Comparing and describing things | Comparando y describiendo cosas | We are comparing and describing things | Estamos comparando y describiendo cosas | Phrase
👉 | I want that one. | Quiero ese. | Look, I want that one. | Mira, quiero ese. | Phrase
1️⃣ | How about this one instead? | ¿Qué tal este en su lugar? | Tell me, how about this one instead? | Dime, ¿qué tal este en su lugar? | Question
💰 | It's cheaper. | Es más barato. | Look, it's cheaper. | Mira, es más barato. | Phrase
👉 | I want that one. | Quiero ese. | Look, I want that one. | Mira, quiero ese. | Phrase
👍 | Good choice! | ¡Buena elección! | Yes, good choice! | ¡Sí, buena elección! | Expression
⏳ | Be patient. | Ten paciencia. | Please, be patient. | Por favor, ten paciencia. | Phrase
🌿 | Natural community | Comunidad natural | The natural community is big | La comunidad natural es grande | Word
💧 | Freshwater | Agua dulce | Fish live in freshwater | Los peces viven en agua dulce | Word
🏜️ | Desert | Desierto | The desert is hot | El desierto es caluroso | Word
🌲 | Forest | Bosque | The forest has trees | El bosque tiene árboles | Word
🌾 | Grassland | Pradera | The grassland is green | La pradera es verde | Word
❄️ | Tundra | Tundra | The tundra is cold | La tundra es fría | Word
🤔 | Which biome is the biggest? | ¿Cuál bioma es el más grande? | Tell me, which biome is the biggest? | Dime, ¿cuál bioma es el más grande? | Question
🌊 | The ocean. | El océano. | Look, the ocean. | Mira, el océano. | Phrase
🦜 | The egret is more colorful than the jaguar. | La garceta es más colorida que el jaguar. | Look, the egret is more colorful than the jaguar. | Mira, la garceta es más colorida que el jaguar. | Phrase
🐆 | The jaguar is more dangerous than the dolphin. | El jaguar es más peligroso que el delfín. | Look, the jaguar is more dangerous than the dolphin. | Mira, el jaguar es más peligroso que el delfín. | Phrase
🐬 | The dolphin is more friendly than the monkey. | El delfín es más amistoso que el mono. | Look, the dolphin is more friendly than the monkey. | Mira, el delfín es más amistoso que el mono. | Phrase
🐒 | The monkey is more energetic than the sloth. | El mono es más enérgico que el perezoso. | Look, the monkey is more energetic than the sloth. | Mira, el mono es más enérgico que el perezoso. | Phrase
🦥 | The sloth is calmer than the jaguar. | El perezoso es más tranquilo que el jaguar. | Look, the sloth is calmer than the jaguar. | Mira, el perezoso es más tranquilo que el jaguar. | Phrase
🤔 | Is the jaguar more colorful than the egret? | ¿Es el jaguar más colorido que la garceta? | Tell me, is the jaguar more colorful than the egret? | Dime, ¿es el jaguar más colorido que la garceta? | Question
🐬 | Is the dolphin more dangerous than the jaguar? | ¿Es el delfín más peligroso que el jaguar? | Tell me, is the dolphin more dangerous than the jaguar? | Dime, ¿es el delfín más peligroso que el jaguar? | Question
📿 | This bracelet is the cheapest one here. | Esta pulsera es la más barata aquí. | Look, this bracelet is the cheapest one here. | Mira, esta pulsera es la más barata aquí. | Phrase
🧩 | That puzzle is the most difficult one here. | Ese rompecabezas es el más difícil aquí. | Look, that puzzle is the most difficult one here. | Mira, ese rompecabezas es el más difícil aquí. | Phrase
🩴 | These sandals are the most uncomfortable ones here. | Estas sandalias son las más incómodas aquí. | Look, these sandals are the most uncomfortable ones here. | Mira, estas sandalias son las más incómodas aquí. | Phrase
📿 | That bracelet is the most expensive one here. | Esa pulsera es la más cara aquí. | Look, that bracelet is the most expensive one here. | Mira, esa pulsera es la más cara aquí. | Phrase
🤔 | Which bracelet is the cheapest? | ¿Cuál pulsera es la más barata? | Tell me, which bracelet is the cheapest? | Dime, ¿cuál pulsera es la más barata? | Question
📿 | This bracelet. | Esta pulsera. | Look, this bracelet. | Mira, esta pulsera. | Phrase
🤔 | Which bracelet is the most expensive? | ¿Cuál pulsera es la más cara? | Tell me, which bracelet is the most expensive? | Dime, ¿cuál pulsera es la más cara? | Question
📿 | That bracelet. | Esa pulsera. | Look, that bracelet. | Mira, esa pulsera. | Phrase
    """.trimIndent(), "Basics 5 - List 4")

    private fun getList5(): List<Flashcard> = parseCards("""
💇 | Wash my hair | Lavarme el cabello | I wash my hair | Lavo mi cabello | Phrase
🚿 | Take a shower | Tomar una ducha | I take a shower | Tomo una ducha | Phrase
🦷 | Floss my teeth | Usar hilo dental | I floss my teeth | Uso hilo dental | Phrase
📅 | Check my calendar | Revisar mi calendario | I check my calendar | Reviso mi calendario | Phrase
🎒 | Pack my schoolbag | Preparar mi mochila | I pack my schoolbag | Preparo mi mochila | Phrase
👔 | Iron my clothes | Planchar mi ropa | I iron my clothes | Plancho mi ropa | Phrase
💇 | I always wash my hair before I go to bed. | Siempre me lavo el cabello antes de ir a la cama. | Look, I always wash my hair before I go to bed. | Mira, siempre me lavo el cabello antes de ir a la cama. | Phrase
🛏️ | Do you wash your hair before you go to bed? | ¿Te lavas el cabello antes de ir a la cama? | Tell me, do you wash your hair before you go to bed? | Dime, ¿te lavas el cabello antes de ir a la cama? | Question
👍 | Yes, I always do. | Sí, siempre lo hago. | Yes, I always do. | Sí, siempre lo hago. | Phrase
🙅 | No, I rarely do. | No, rara vez lo hago. | No, I rarely do. | No, rara vez lo hago. | Phrase
🐢 | Slowly | Lentamente | He walks slowly | Él camina lentamente | Word
🐇 | Quickly | Rápidamente | He runs quickly | Él corre rápidamente | Word
⚠️ | Carefully | Cuidadosamente | She works carefully | Ella trabaja cuidadosamente | Word
💥 | Carelessly | Descuidadamente | He drives carelessly | Él conduce descuidadamente | Word
🤫 | Quietly | Silenciosamente | They speak quietly | Ellos hablan silenciosamente | Word
📢 | Loudly | En voz alta / Ruidosamente | He sings loudly | Él canta en voz alta | Word
🚶 | How is she walking? | ¿Cómo está caminando ella? | Tell me, how is she walking? | Dime, ¿cómo está caminando ella? | Question
🐢 | She's walking slowly. | Ella está caminando lentamente. | Look, she's walking slowly. | Mira, ella está caminando lentamente. | Phrase
🐇 | Is he walking slowly or quickly? | ¿Está él caminando lenta o rápidamente? | Tell me, is he walking slowly or quickly? | Dime, ¿está él caminando lenta o rápidamente? | Question
🐢 | He's walking slowly. | Él está caminando lentamente. | Look, he's walking slowly. | Mira, él está caminando lentamente. | Phrase
😌 | I felt relaxed, but she felt nervous. | Me sentí relajado, pero ella se sintió nerviosa. | Look, I felt relaxed, but she felt nervous. | Mira, me sentí relajado, pero ella se sintió nerviosa. | Phrase
🎭 | The Recital | El recital | This is The Recital | Este es El recital | Phrase
💬 | Talking about meeting commitments | Hablar sobre cumplir compromisos | We are talking about meeting commitments | Estamos hablando sobre cumplir compromisos | Phrase
📈 | Getting better at something through practice | Mejorar en algo mediante la práctica | We are getting better at something through practice | Estamos mejorando en algo mediante la práctica | Phrase
🤔 | Are you ready for your recital? | ¿Estás listo para tu recital? | Tell me, are you ready for your recital? | Dime, ¿estás listo para tu recital? | Question
🙅 | No, I'm not. I still need to practice. | No, no lo estoy. Todavía necesito practicar. | No, I'm not. I still need to practice. | No, no lo estoy. Todavía necesito practicar. | Phrase
🤔 | Are you ready for your recital? | ¿Estás listo para tu recital? | Tell me, are you ready for your recital? | Dime, ¿estás listo para tu recital? | Question
👍 | Yes, I think so. I practiced all week. | Sí, eso creo. Practiqué toda la semana. | Yes, I think so. I practiced all week. | Sí, eso creo. Practiqué toda la semana. | Phrase
🛡️ | Be responsible. | Sé responsable. | Please, be responsible. | Por favor, sé responsable. | Phrase
🌟 | Successful | Exitoso | The test is successful | La prueba es exitosa | Word
⚽ | Exercise | Hacer ejercicio | I exercise every day | Hago ejercicio todos los días | Word
✔️ | Possible | Posible | It is possible | Es posible | Word
🥗 | Balanced meal | Comida equilibrada | I eat a balanced meal | Como una comida equilibrada | Word
🔄 | Habit | Hábito | Reading is a good habit | Leer es un buen hábito | Word
🌅 | Early | Temprano | I wake up early | Me despierto temprano | Word
🚶 | How often do you go for a walk? | ¿Qué tan seguido sales a caminar? | Tell me, how often do you go for a walk? | Dime, ¿qué tan seguido sales a caminar? | Question
🚶 | I go for a walk twice a week. | Salgo a caminar dos veces por semana. | Look, I go for a walk twice a week. | Mira, salgo a caminar dos veces por semana. | Phrase
🚿 | I always take a shower before I go to bed. | Siempre me tomo una ducha antes de ir a la cama. | Look, I always take a shower before I go to bed. | Mira, siempre me tomo una ducha antes de ir a la cama. | Phrase
🦷 | I always floss my teeth before I go to bed. | Siempre uso hilo dental antes de ir a la cama. | Look, I always floss my teeth before I go to bed. | Mira, siempre uso hilo dental antes de ir a la cama. | Phrase
📅 | I always check my calendar before I go to bed. | Siempre reviso mi calendario antes de ir a la cama. | Look, I always check my calendar before I go to bed. | Mira, siempre reviso mi calendario antes de ir a la cama. | Phrase
🎒 | I always pack my schoolbag before I go to bed. | Siempre preparo mi mochila antes de ir a la cama. | Look, I always pack my schoolbag before I go to bed. | Mira, siempre preparo mi mochila antes de ir a la cama. | Phrase
👔 | I always iron my clothes before I go to bed. | Siempre plancho mi ropa antes de ir a la cama. | Look, I always iron my clothes before I go to bed. | Mira, siempre plancho mi ropa antes de ir a la cama. | Phrase
🛏️ | Do you take a shower before you go to bed? | ¿Te tomas una ducha antes de ir a la cama? | Tell me, do you take a shower before you go to bed? | Dime, ¿te tomas una ducha antes de ir a la cama? | Question
🛏️ | Do you floss your teeth before you go to bed? | ¿Usas hilo dental antes de ir a la cama? | Tell me, do you floss your teeth before you go to bed? | Dime, ¿usas hilo dental antes de ir a la cama? | Question
🛏️ | Do you check your calendar before you go to bed? | ¿Revisas tu calendario antes de ir a la cama? | Tell me, do you check your calendar before you go to bed? | Dime, ¿revisas tu calendario antes de ir a la cama? | Question
🛏️ | Do you pack your schoolbag before you go to bed? | ¿Preparas tu mochila antes de ir a la cama? | Tell me, do you pack your schoolbag before you go to bed? | Dime, ¿preparas tu mochila antes de ir a la cama? | Question
🛏️ | Do you iron your clothes before you go to bed? | ¿Planchas tu ropa antes de ir a la cama? | Tell me, do you iron your clothes before you go to bed? | Dime, ¿planchas tu ropa antes de ir a la cama? | Question
🚶 | How is he walking? | ¿Cómo está caminando él? | Tell me, how is he walking? | Dime, ¿cómo está caminando él? | Question
🐇 | She's walking quickly. | Ella está caminando rápidamente. | Look, she's walking quickly. | Mira, ella está caminando rápidamente. | Phrase
🐇 | He's walking quickly. | Él está caminando rápidamente. | Look, he's walking quickly. | Mira, él está caminando rápidamente. | Phrase
⚠️ | She's walking carefully. | Ella está caminando cuidadosamente. | Look, she's walking carefully. | Mira, ella está caminando cuidadosamente. | Phrase
⚠️ | He's walking carefully. | Él está caminando cuidadosamente. | Look, he's walking carefully. | Mira, él está caminando cuidadosamente. | Phrase
💥 | She's walking carelessly. | Ella está caminando descuidadamente. | Look, she's walking carelessly. | Mira, ella está caminando descuidadamente. | Phrase
💥 | He's walking carelessly. | Él está caminando descuidadamente. | Look, he's walking carelessly. | Mira, él está caminando descuidadamente. | Phrase
🤫 | She's walking quietly. | Ella está caminando silenciosamente. | Look, she's walking quietly. | Mira, ella está caminando silenciosamente. | Phrase
🤫 | He's walking quietly. | Él está caminando silenciosamente. | Look, he's walking quietly. | Mira, él está caminando silenciosamente. | Phrase
📢 | She's walking loudly. | Ella está caminando ruidosamente. | Look, she's walking loudly. | Mira, ella está caminando ruidosamente. | Phrase
📢 | He's walking loudly. | Él está caminando ruidosamente. | Look, he's walking loudly. | Mira, él está caminando ruidosamente. | Phrase
🐇 | Is she walking slowly or quickly? | ¿Está ella caminando lenta o rápidamente? | Tell me, is she walking slowly or quickly? | Dime, ¿está ella caminando lenta o rápidamente? | Question
💥 | Is he walking carefully or carelessly? | ¿Está él caminando cuidadosa o descuidadamente? | Tell me, is he walking carefully or carelessly? | Dime, ¿está él caminando cuidadosa o descuidadamente? | Question
💥 | Is she walking carefully or carelessly? | ¿Está ella caminando cuidadosa o descuidadamente? | Tell me, is she walking carefully or carelessly? | Dime, ¿está ella caminando cuidadosa o descuidadamente? | Question
📢 | Is he walking quietly or loudly? | ¿Está él caminando silenciosa o ruidosamente? | Tell me, is he walking quietly or loudly? | Dime, ¿está él caminando silenciosa o ruidosamente? | Question
📢 | Is she walking quietly or loudly? | ¿Está ella caminando silenciosa o ruidosamente? | Tell me, is she walking quietly or loudly? | Dime, ¿está ella caminando silenciosa o ruidosamente? | Question
    """.trimIndent(), "Basics 5 - List 5")

    private fun getList6(): List<Flashcard> = parseCards("""
🥣 | A cup of flour | Una taza de harina | I need a cup of flour | Necesito una taza de harina | Phrase
💧 | A half cup of water | Media taza de agua | I need a half cup of water | Necesito media taza de agua | Phrase
🧂 | A quarter cup of salt | Un cuarto de taza de sal | I need a quarter cup of salt | Necesito un cuarto de taza de sal | Phrase
🥄 | A tablespoon of cooking oil | Una cucharada de aceite de cocina | I need a tablespoon of cooking oil | Necesito una cucharada de aceite de cocina | Phrase
🥄 | A teaspoon of baking soda | Una cucharadita de bicarbonato de sodio | I need a teaspoon of baking soda | Necesito una cucharadita de bicarbonato de sodio | Phrase
🎨 | A drop of food coloring | Una gota de colorante alimentario | I need a drop of food coloring | Necesito una gota de colorante alimentario | Phrase
🤔 | How much flour does he need? | ¿Cuánta harina necesita él? | Tell me, how much flour does he need? | Dime, ¿cuánta harina necesita él? | Question
🥣 | He needs a cup of flour. | Él necesita una taza de harina. | Look, he needs a cup of flour. | Mira, él necesita una taza de harina. | Phrase
🤔 | Does he have enough flour? | ¿Tiene él suficiente harina? | Tell me, does he have enough flour? | Dime, ¿tiene él suficiente harina? | Question
👍 | Yes, he does. | Sí, sí tiene. | Yes, he does. | Sí, sí tiene. | Phrase
🙅 | No, he doesn't. | No, no tiene. | No, he doesn't. | No, no tiene. | Phrase
👕 | Aprons | Delantales | We wear aprons | Usamos delantales | Word
🦷 | Toothpicks | Palillos de dientes | The toothpicks are small | Los palillos de dientes son pequeños | Word
📎 | Paper clips | Clips de papel / Ganchos para papel | I need paper clips | Necesito clips de papel | Word
📦 | Cardboard | Cartón | The box is cardboard | La caja es de cartón | Word
🩹 | Masking tape | Cinta de enmascarar (cinta de papel) | I use masking tape | Uso cinta de enmascarar | Word
🏺 | Modeling clay | Plastilina | The modeling clay is blue | La plastilina es azul | Word
📦 | How much cardboard do we have? | ¿Cuánto cartón tenemos? | Tell me, how much cardboard do we have? | Dime, ¿cuánto cartón tenemos? | Question
📦 | We have six sheets of cardboard. | Tenemos seis hojas de cartón. | Look, we have six sheets of cardboard. | Mira, tenemos seis hojas de cartón. | Phrase
👕 | How many aprons do they have? | ¿Cuántos delantales tienen ellos? | Tell me, how many aprons do they have? | Dime, ¿cuántos delantales tienen ellos? | Question
👕 | They have a few aprons. | Ellos tienen unos pocos delantales. | Look, they have a few aprons. | Mira, ellos tienen unos pocos delantales. | Phrase
👕 | They have a lot of aprons. | Ellos tienen muchos delantales. | Look, they have a lot of aprons. | Mira, ellos tienen muchos delantales. | Phrase
📦 | How much cardboard do they have? | ¿Cuánto cartón tienen ellos? | Tell me, how much cardboard do they have? | Dime, ¿cuánto cartón tienen ellos? | Question
📦 | They have a little cardboard. | Ellos tienen un poco de cartón. | Look, they have a little cardboard. | Mira, ellos tienen un poco de cartón. | Phrase
📦 | They have a lot of cardboard. | Ellos tienen mucho cartón. | Look, they have a lot of cardboard. | Mira, ellos tienen mucho cartón. | Phrase
🏛️ | Where's the Parthenon? | ¿Dónde está el Partenón? | Tell me, where's the Parthenon? | Dime, ¿dónde está el Partenón? | Phrase
🧠 | Forgetting and remembering where you put things | Olvidar y recordar dónde pones las cosas | Forgetting and remembering where you put things is common | Olvidar y recordar dónde pones las cosas es común | Phrase
🗺️ | Did we bring the map? | ¿Trajimos el mapa? | Tell me, did we bring the map? | Dime, ¿trajimos el mapa? | Question
🧠 | I don't remember. | No recuerdo. | No, I don't remember. | No, no recuerdo. | Phrase
🧠 | I remember. It's under the seat. | Recuerdo. Está debajo del asiento. | Look, I remember. It's under the seat. | Mira, recuerdo. Está debajo del asiento. | Phrase
🗺️ | Did we bring the map? | ¿Trajimos el mapa? | Tell me, did we bring the map? | Dime, ¿trajimos el mapa? | Question
🤷 | I'm not sure. | No estoy seguro. | I'm not sure about that. | No estoy seguro de eso. | Phrase
👍 | Never mind, I found it. | No importa, lo encontré. | Never mind, I found it. | No importa, lo encontré. | Phrase
🛡️ | Be prepared. | Prepárate. | Always be prepared. | Siempre prepárate. | Phrase
🧱 | Take | Tomar / Llevar | I take a book | Tomo un libro | Word
👨‍🌾 | Farmer | Granjero / Agricultor | The farmer works | El granjero trabaja | Word
🎨 | Artisan | Artesano | The artisan makes art | El artesano hace arte | Word
🏃 | Move | Mover | I move the box | Muevo la caja | Word
🧗 | Pull | Jalar / Tirar | I pull the door | Jalo la puerta | Word
📍 | Site | Sitio / Lugar | The site is big | El sitio es grande | Word
🧱 | How many people did it take to build the Great Pyramid of Giza? | ¿Cuántas personas se necesitaron para construir la Gran Pirámide de Guiza? | Tell me, how many people did it take to build the Great Pyramid of Giza? | Dime, ¿cuántas personas se necesitaron para construir la Gran Pirámide de Guiza? | Question
👥 | It took around thirty thousand people. | Se necesitaron alrededor de treinta mil personas. | Look, it took around thirty thousand people. | Mira, se necesitaron alrededor de treinta mil personas. | Phrase
🏃 | Rules for Running | Reglas para correr | Follow the rules for running | Sigue las reglas para correr | Phrase
🥣 | How much water does he need? | ¿Cuánta agua necesita él? | Tell me, how much water does he need? | Dime, ¿cuánta agua necesita él? | Question
💧 | He needs a half cup of water. | Él necesita media taza de agua. | Look, he needs a half cup of water. | Mira, él necesita media taza de agua. | Phrase
💧 | Does he have enough water? | ¿Tiene él suficiente agua? | Tell me, does he have enough water? | Dime, ¿tiene él suficiente agua? | Question
🥣 | How much salt does he need? | ¿Cuánta sal necesita él? | Tell me, how much salt does he need? | Dime, ¿cuánta sal necesita él? | Question
🧂 | He needs a quarter cup of salt. | Él necesita un cuarto de taza de sal. | Look, he needs a quarter cup of salt. | Mira, él necesita un cuarto de taza de sal. | Phrase
🤔 | Does he have enough salt? | ¿Tiene él suficiente sal? | Tell me, does he have enough salt? | Dime, ¿tiene él suficiente sal? | Question
🥣 | How much cooking oil does he need? | ¿Cuánto aceite de cocina necesita él? | Tell me, how much cooking oil does he need? | Dime, ¿Cuánto aceite de cocina necesita él? | Question
🥄 | He needs a tablespoon of cooking oil. | Él necesita una cucharada de aceite de cocina. | Look, he needs a tablespoon of cooking oil. | Mira, él necesita una cucharada de aceite de cocina. | Phrase
🤔 | Does he have enough cooking oil? | ¿Tiene él suficiente aceite de cocina? | Tell me, does he have enough cooking oil? | Dime, ¿tiene él suficiente aceite de cocina? | Question
🥣 | How much baking soda does he need? | ¿Cuánto bicarbonato de sodio necesita él? | Tell me, how much baking soda does he need? | Dime, ¿cuánto bicarbonato de sodio necesita él? | Question
🥄 | He needs a teaspoon of baking soda. | Él necesita una cucharadita de bicarbonato de sodio. | Look, he needs a teaspoon of baking soda. | Mira, él necesita una cucharadita de bicarbonato de sodio. | Phrase
🥤 | Does he have enough baking soda? | ¿Tiene él suficiente bicarbonato de sodio? | Tell me, does he have enough baking soda? | Dime, ¿tiene él suficiente bicarbonato de sodio? | Question
🥣 | How much food coloring does he need? | ¿Cuánto colorante alimentario necesita él? | Tell me, how much food coloring does he need? | Dime, ¿cuánto colorante alimentario necesita él? | Question
🎨 | He needs a drop of food coloring. | Él necesita una gota de colorante alimentario. | Look, he needs a drop of food coloring. | Mira, él necesita una gota de colorante alimentario. | Phrase
🤔 | Does he have enough food coloring? | ¿Tiene él suficiente colorante alimentario? | Tell me, does he have enough food coloring? | Dime, ¿tiene él suficiente colorante alimentario? | Question
🦷 | How many toothpicks do they have? | ¿Cuántos palillos de dientes tienen ellos? | Tell me, how many toothpicks do they have? | Dime, ¿cuántos palillos de dientes tienen ellos? | Question
🦷 | They have a few toothpicks. | Ellos tienen unos pocos palillos de dientes. | Look, they have a few toothpicks. | Mira, ellos tienen unos pocos palillos de dientes. | Phrase
🦷 | They have a lot of toothpicks. | Ellos tienen muchos palillos de dientes. | Look, they have a lot of toothpicks. | Mira, ellos tienen muchos palillos de dientes. | Phrase
📄 | How many paper clips do they have? | ¿Cuántos clips de papel tienen ellos? | Tell me, how many paper clips do they have? | Dime, ¿cuántos clips de papel tienen ellos? | Question
📎 | They have a few paper clips. | Ellos tienen unos pocos clips de papel. | Look, they have a few paper clips. | Mira, ellos tienen unos pocos clips de papel. | Phrase
📎 | They have a lot of paper clips. | Ellos tienen muchos clips de papel. | Look, they have a lot of paper clips. | Mira, ellos tienen muchos clips de papel. | Phrase
🩹 | How much masking tape do they have? | ¿Cuánta cinta de enmascarar tienen ellos? | Tell me, how much masking tape do they have? | Dime, ¿cuánta cinta de enmascarar tienen ellos? | Question
🩹 | They have a little masking tape. | Ellos tienen un poco de cinta de enmascarar. | Look, they have a little masking tape. | Mira, ellos tienen un poco de cinta de enmascarar. | Phrase
🩹 | They have a lot of masking tape. | Ellos tienen mucha cinta de enmascarar. | Look, they have a lot of masking tape. | Mira, ellos tienen mucha cinta de enmascarar. | Phrase
🏺 | How much modeling clay do they have? | ¿Cuánta plastilina tienen ellos? | Tell me, how much modeling clay do they have? | Dime, ¿cuánta plastilina tienen ellos? | Question
🏺 | They have a little modeling clay. | Ellos tienen un poco de plastilina. | Look, they have a little modeling clay. | Mira, ellos tienen un poco de plastilina. | Phrase
🏺 | They have a lot of modeling clay. | Ellos tienen mucha plastilina. | Look, they have a lot of modeling clay. | Mira, ellos tienen mucha plastilina. | Phrase
    """.trimIndent(), "Basics 5 - List 6")

    private fun getList7(): List<Flashcard> = parseCards("""
🇮🇳 | India | India | I want to visit India | Quiero visitar la India | Word
🇮🇹 | Italy | Italia | The food in Italy is good | La comida en Italia es buena | Word
🇰🇪 | Kenya | Kenia | Lions live in Kenya | Los leones viven en Kenia | Word
🇳🇿 | New Zealand | Nueva Zelanda | New Zealand is green | Nueva Zelanda es verde | Word
🇬🇧 | The UK | El Reino Unido | He lives in the UK | Él vive en el Reino Unido | Word
🇵🇪 | Peru | Perú | Peru has old ruins | Perú tiene ruinas antiguas | Word
🇮🇳 | I've been to India. | He estado en India. | Look, I've been to India. | Mira, he estado en India. | Phrase
🇮🇹 | I've never been to Italy. | Nunca he estado en Italia. | No, I've never been to Italy. | No, nunca he estado en Italia. | Phrase
🇮🇳 | Have you ever been to India? | ¿Alguna vez has estado en India? | Tell me, have you ever been to India? | Dime, ¿alguna vez has estado en India? | Question
👨 | Yes, I have. | Sí, sí he estado. | Yes, I have. | Sí, sí he estado. | Phrase
👨 | No, I haven't. | No, no he estado. | No, I haven't. | No, no he estado. | Phrase
🇮🇳 | Has she ever been to India? | ¿Alguna vez ha estado ella en India? | Tell me, has she ever been to India? | Dime, ¿alguna vez ha estado ella en India? | Question
👍 | Yes, she has. | Sí, ella ha estado. | Yes, she has. | Sí, ella ha estado. | Phrase
🙅 | No, she hasn't. | No, ella no ha estado. | No, she hasn't. | No, ella no ha estado. | Phrase
🚣 | Go rafting | Hacer rafting | I go rafting in the river | Hago rafting en el río | Phrase
🐪 | Ride a camel | Montar en camello | I ride a camel | Monto en camello | Phrase
⛰️ | Climb a mountain | Escalada de montaña | I climb a mountain | Escalo una montaña | Phrase
🌴 | Hike in a rainforest | Hacer senderismo en una selva tropical | I hike in a rainforest | Hago senderismo en una selva tropical | Phrase
🏛️ | See the pyramids | Ver las pirámides | We see the pyramids | Vemos las pirámides | Phrase
🤿 | Go scuba diving | Hacer buceo | I go scuba diving | Hago buceo | Phrase
🚣 | He has gone rafting, but he hasn't ridden a camel. | Él ha hecho rafting, pero no ha montado en camello. | Look, he has gone rafting, but he hasn't ridden a camel. | Mira, él ha hecho rafting, pero no ha montado en camello. | Phrase
🤔 | Has he gone rafting before? | ¿Ha hecho él rafting antes? | Tell me, has he gone rafting before? | Dime, ¿ha hecho él rafting antes? | Question
👍 | Yes, he has. | Sí, él ha hecho. | Yes, he has. | Sí, él ha hecho. | Phrase
🙅 | No, he hasn't. | No, él no ha hecho. | No, he hasn't. | No, él no ha hecho. | Phrase
🤝 | Making new friends | Hacer nuevos amigos | Making new friends is fun | Hacer nuevos amigos es divertido | Phrase
❤️ | Sharing interests | Compartir intereses | Sharing interests is good | Compartir intereses es bueno | Phrase
🛹 | Do you like skateboarding? | ¿Te gusta andar en monopatín? | Tell me, do you like skateboarding? | Dime, ¿te gusta andar en monopatín? | Question
🛹 | I'm not very good at it. | No soy muy bueno en eso. | No, I'm not very good at it. | No, no soy muy bueno en eso. | Phrase
🤔 | Could you show me how? | ¿Podrías enseñarme cómo? | Tell me, could you show me how? | Dime, ¿podrías enseñarme cómo? | Question
👍 | Sure! | ¡Claro! | Yes, sure! | ¡Sí, claro! | Expression
🛹 | Do you like skateboarding? | ¿Te gusta andar en monopatín? | Tell me, do you like skateboarding? | Dime, ¿te gusta andar en monopatín? | Question
👍 | Yes, I do. | Sí, sí me gusta. | Yes, I do. | Sí, sí me gusta. | Phrase
🛹 | Cool! Let's go skateboarding after school. | ¡Genial! Vayamos a andar en monopatín después de la escuela. | Cool! Let's go skateboarding after school. | ¡Genial! Vayamos a andar en monopatín después de la escuela. | Phrase
🤝 | Be friendly. | Sé amigable. | Please, be friendly. | Por favor, sé amigable. | Phrase
⛰️ | High | Alto | The mountain is high | La montaña es alta | Word
📏 | Reach | Alcanzar | I can reach the book | Puedo alcanzar el libro | Word
🇬🇧 | British | Británico | He is British | Él es británico | Word
🚪 | Leave | Irse / Salir | I leave home | Salgo de casa | Word
🙏 | Grateful | Agradecido | I am grateful | Estoy agradecido | Word
➡️ | Lead | Guiar / Conducir | The guide will lead us | El guía nos guiará | Word
🤔 | What happened in 1924? | ¿Qué pasó en 1924? | Tell me, what happened in 1924? | Dime, ¿qué pasó en 1924? | Question
🧗 | George Mallory and Andrew Irvine tried to reach the top of Mount Everest. | George Mallory y Andrew Irvine intentaron llegar a la cima del Monte Everest. | George Mallory and Andrew Irvine tried to reach the top of Mount Everest. | George Mallory y Andrew Irvine intentaron llegar a la cima del Monte Everest. | Phrase
🇮🇹 | I've been to Italy. | He estado en Italia. | Look, I've been to Italy. | Mira, he estado en Italia. | Phrase
🇰🇪 | I've been to Kenya. | He estado en Kenia. | Look, I've been to Kenya. | Mira, he estado en Kenia. | Phrase
🇳🇿 | I've been to New Zealand. | He estado en Nueva Zelanda. | Look, I've been to New Zealand. | Mira, he estado en Nueva Zelanda. | Phrase
🇬🇧 | I've been to the UK. | He estado en el Reino Unido. | Look, I've been to the UK. | Mira, he estado en el Reino Unido. | Phrase
🇵🇪 | I've been to Peru. | He estado en Perú. | Look, I've been to Peru. | Mira, he estado en Perú. | Phrase
🇮🇹 | I've never been to India. | Nunca he estado en India. | No, I've never been to India. | No, nunca he estado en India. | Phrase
🇰🇪 | I've never been to Kenya. | Nunca he estado en Kenia. | No, I've never been to Kenia. | No, nunca he estado en Kenia. | Phrase
🇳🇿 | I've never been to New Zealand. | Nunca he estado en Nueva Zelanda. | No, I've never been to New Zealand. | No, nunca he estado en Nueva Zelanda. | Phrase
🇬🇧 | I've never been to the UK. | Nunca he estado en el Reino Unido. | No, I've never been to the UK. | No, nunca he estado en el Reino Unido. | Phrase
🇵🇪 | I've never been to Peru. | Nunca he estado en Perú. | No, I've never been to Peru. | No, nunca he estado en Perú. | Phrase
🇮🇹 | Have you ever been to Italy? | ¿Alguna vez has estado en Italia? | Tell me, have you ever been to Italy? | Dime, ¿alguna vez has estado en Italia? | Question
🇰🇪 | Have you ever been to Kenya? | ¿Alguna vez has estado en Kenia? | Tell me, have you ever been to Kenya? | Dime, ¿alguna vez has estado en Kenia? | Question
🇳🇿 | Have you ever been to New Zealand? | ¿Alguna vez has estado en Nueva Zelanda? | Tell me, have you ever been to New Zealand? | Dime, ¿alguna vez has estado en Nueva Zelanda? | Question
🇬🇧 | Have you ever been to the UK? | ¿Alguna vez has estado en el Reino Unido? | Tell me, have you ever been to the UK? | Dime, ¿alguna vez has estado en el Reino Unido? | Question
🇵🇪 | Have you ever been to Peru? | ¿Alguna vez has estado en Perú? | Tell me, have you ever been to Peru? | Dime, ¿alguna vez has estado en Peru? | Question
🇮🇹 | Has she ever been to Italy? | ¿Alguna vez ha estado ella en Italia? | Tell me, has she ever been to Italy? | Dime, ¿alguna vez ha estado ella en Italia? | Question
🇰🇪 | Has she ever been to Kenya? | ¿Alguna vez ha estado ella en Kenia? | Tell me, has she ever been to Kenya? | Dime, ¿alguna vez ha estado ella en Kenia? | Question
🇳🇿 | Has she ever been to New Zealand? | ¿Alguna vez ha estado ella en Nueva Zelanda? | Tell me, has she ever been to New Zealand? | Dime, ¿alguna vez ha estado ella en Nueva Zelanda? | Question
🇬🇧 | Has she ever been to the UK? | ¿Alguna vez ha estado ella en el Reino Unido? | Tell me, has she ever been to the UK? | Dime, ¿alguna vez ha estado ella en el Reino Unido? | Question
🇵🇪 | Has she ever been to Peru? | ¿Alguna vez ha estado ella en Perú? | Tell me, has she ever been to Peru? | Dime, ¿alguna vez ha estado ella en Perú? | Question
🐪 | He has ridden a camel, but he hasn't gone rafting. | Él ha montado en camello, pero no ha hecho rafting. | Look, he has ridden a camel, but he hasn't gone rafting. | Mira, él ha montado en camello, pero no ha hecho rafting. | Phrase
⛰️ | He has climbed a mountain, but he hasn't gone rafting. | Él ha escalado una montaña, pero no ha hecho rafting. | Look, he has climbed a mountain, but he hasn't gone rafting. | Mira, él ha escalado una montaña, pero no ha hecho rafting. | Phrase
🌴 | He has hiked in a rainforest, but he hasn't gone rafting. | Él ha hecho senderismo en una selva tropical, pero no ha hecho rafting. | Look, he has hiked in a rainforest, but he hasn't gone rafting. | Mira, él ha hecho senderismo en una selva tropical, pero no ha hecho rafting. | Phrase
🏛️ | He has seen the pyramids, but he hasn't gone rafting. | Él ha visto las pirámides, pero no ha hecho rafting. | Look, he has seen the pyramids, but he hasn't gone rafting. | Mira, él ha visto las pirámides, pero no ha hecho rafting. | Phrase
🤿 | He has gone scuba diving, but he hasn't gone rafting. | Él ha hecho buceo, pero no ha hecho rafting. | Look, he has gone scuba diving, but he hasn't gone rafting. | Mira, él ha hecho buceo, pero no ha hecho rafting. | Phrase
🤔 | Has he ridden a camel before? | ¿Ha montado en camello él antes? | Tell me, has he ridden a camel before? | Dime, ¿ha montado en camello él antes? | Question
🤔 | Has he climbed a mountain before? | ¿Ha escalado una montaña él antes? | Tell me, has he climbed a mountain before? | Dime, ¿ha escalado una montaña él antes? | Question
🤔 | Has he hiked in a rainforest before? | ¿Ha hecho senderismo en una selva tropical él antes? | Tell me, has he hiked in a rainforest before? | Dime, ¿ha hecho senderismo en una selva tropical él antes? | Question
🤔 | Has he seen the pyramids before? | ¿Ha visto las pirámides él antes? | Tell me, has he seen the pyramids before? | Dime, ¿ha visto las pirámides él antes? | Question
🤔 | Has he gone scuba diving before? | ¿Ha hecho buceo él antes? | Tell me, has he gone scuba diving before? | Dime, ¿ha hecho buceo él antes? | Question
    """.trimIndent(), "Basics 5 - List 7")

    private fun getList8(): List<Flashcard> = parseCards("""
💻 | Turn on the computer | Encender la computadora | I turn on the computer | Enciendo la computadora | Phrase
💻 | Turn off the computer | Apagar la computadora | I turn off the computer | Apago la computadora | Phrase
🔊 | Turn up the volume | Subir el volumen | I turn up the volume | Subo el volumen | Phrase
🔉 | Turn down the volume | Bajar el volumen | I turn down the volume | Bajo el volumen | Phrase
🌐 | Log in to the website | Iniciar sesión en el sitio web | I log in to the website | Inicio sesión en el sitio web | Phrase
🌐 | Log out of the website | Cerrar sesión en el sitio web | I log out of the website | Cierro sesión en el sitio web | Phrase
💻 | I've just turned on the computer. | Acabo de encender la computadora. | Look, I've just turned on the computer. | Mira, acabo de encender la computadora. | Phrase
💻 | I haven't turned on the computer yet. | Todavía no he encendido la computadora. | Look, I haven't turned on the computer yet. | Mira, todavía no he encendido la computadora. | Phrase
🤔 | Has he turned on the computer yet? | ¿Ya ha encendido él la computadora? | Tell me, has he turned on the computer yet? | Dime, ¿ya ha encendido él la computadora? | Question
👍 | Yes, he has. | Sí, ya lo ha hecho. | Yes, he has. | Sí, ya lo ha hecho. | Phrase
🙅 | No, he hasn't. | No, todavía no. | No, he hasn't. | No, todavía no. | Phrase
🖼️ | Upload the photos | Subir las fotos | I upload the photos | Subo las fotos | Phrase
🖼️ | Print the photos | Imprimir las fotos | I print the photos | Imprimo las fotos | Phrase
🎵 | Download the music | Descargar la música | I download the music | Descargo la música | Phrase
🎵 | Play the music | Reproducir la música | I play the music | Reproduzco la música | Phrase
✉️ | Write the email | Escribir el correo electrónico | I write the email | Escribo el correo electrónico | Phrase
✉️ | Send the email | Enviar el correo electrónico | I send the email | Envío el correo electrónico | Phrase
🖼️ | She's already uploaded the photos, but she hasn't printed them yet. | Ella ya ha subido las fotos, pero aún no las ha impreso. | Look, she's already uploaded the photos, but she hasn't printed them yet. | Mira, ella ya ha subido las fotos, pero aún no las ha impreso. | Phrase
✉️ | He's already written the email, but he hasn't sent it yet. | Él ya ha escrito el correo electrónico, pero aún no lo ha enviado. | Look, he's already written the email, but he hasn't sent it yet. | Mira, él ya ha escrito el correo electrónico, pero aún no lo ha enviado. | Phrase
🤔 | Has she uploaded the photos yet? | ¿Ya ha subido ella las fotos? | Tell me, has she uploaded the photos yet? | Dime, ¿ya ha subido ella las fotos? | Question
👍 | Yes, she's uploaded them. | Sí, ya las ha subido. | Yes, she's uploaded them. | Sí, ya las ha subido. | Phrase
🙅 | No, she hasn't uploaded them. | No, aún no las ha subido. | No, she hasn't uploaded them. | No, aún no las ha subido. | Phrase
⏱️ | Just a minute | Un minuto | Wait just a minute, please | Espera solo un minuto, por favor | Phrase
💻 | Are you almost done with the computer? | ¿Ya casi terminas con la computadora? | Tell me, are you almost done with the computer? | Dime, ¿ya casi terminas con la computadora? | Question
💻 | Just a minute, I haven't finished downloading these songs yet. | Un minuto, todavía no he terminado de descargar estas canciones. | Just a minute, I haven't finished downloading these songs yet. | Un minuto, todavía no he terminado de descargar estas canciones. | Phrase
👍 | OK. Let me know when you're done. | De acuerdo. Avísame cuando termines. | OK. Let me know when you're done. | De acuerdo. Avísame cuando termines. | Phrase
💻 | Are you almost done with the computer? | ¿Ya casi terminas con la computadora? | Tell me, are you almost done with the computer? | Dime, ¿ya casi terminas con la computadora? | Question
👍 | Yes, I just finished. Go ahead and use it. | Sí, acabo de terminar. Adelante, úsala. | Yes, I just finished. Go ahead and use it. | Sí, acabo de terminar. Adelante, úsala. | Phrase
🙏 | Thanks! | ¡Gracias! | Thanks a lot! | ¡Muchas gracias! | Expression
⚖️ | Be fair. | Sé justo. | Please, be fair. | Por favor, sé justo. | Phrase
⚡ | Energy | Energía | The sun gives energy | El sol da energía | Word
🔋 | Source | Fuente | Water is a source of life | El agua es fuente de vida | Word
💨 | Wind turbine | Turbina eólica | The wind turbine turns | La turbina eólica gira | Word
⚡ | Electricity | Electricidad | The house has electricity | La casa tiene electricidad | Word
🧱 | Dam | Presa | The dam holds water | La presa contiene agua | Word
☀️ | Solar panel | Panel solar | The solar panel makes energy | El panel solar produce energía | Word
⚡ | What have scientists designed to use wind energy? | ¿Qué han diseñado los científicos para usar energía eólica? | Tell me, what have scientists designed to use wind energy? | Dime, ¿qué han diseñado los científicos para usar energía eólica? | Question
💨 | Scientists have designed wind turbines that use strong winds to make energy. | Los científicos han diseñado turbinas eólicas que utilizan vientos fuertes para generar energía. | Scientists have designed wind turbines that use strong winds to make energy. | Los científicos han diseñado turbinas eólicas que utilizan vientos fuertes para generar energía. | Phrase
🔊 | I've just turned up the volume. | Acabo de subir el volumen. | Look, I've just turned up the volume. | Mira, acabo de subir el volumen. | Phrase
🔉 | I've just turned down the volume. | Acabo de bajar el volumen. | Look, I've just turned down the volume. | Mira, acabo de bajar el volumen. | Phrase
🌐 | I've just logged in to the website. | Acabo de iniciar sesión en el sitio web. | Look, I've just logged in to the website. | Mira, acabo de iniciar sesión en el sitio web. | Phrase
🌐 | I've just logged out of the website. | Acabo de cerrar sesión en el sitio web. | Look, I've just logged out of the website. | Mira, acabo de cerrar sesión en el sitio web. | Phrase
🔊 | I haven't turned up the volume yet. | Todavía no he subido el volumen. | Look, I haven't turned up the volume yet. | Mira, todavía no he subido el volumen. | Phrase
🔉 | I haven't turned down the volume yet. | Todavía no he bajado el volumen. | Look, I haven't turned down the volume yet. | Mira, todavía no he bajado el volumen. | Phrase
🌐 | I haven't logged in to the website yet. | Todavía no he iniciado sesión en el sitio web. | Look, I haven't logged in to the website yet. | Mira, todavía no he iniciado sesión en el sitio web. | Phrase
🌐 | I haven't logged out of the website yet. | Todavía no he cerrado sesión en el sitio web. | Look, I haven't logged out of the website yet. | Mira, todavía no he cerrado sesión en el sitio web. | Phrase
🤔 | Has he turned off the computer yet? | ¿Ya ha apagado él la computadora? | Tell me, has he turned off the computer yet? | Dime, ¿ya ha apagado él la computadora? | Question
🤔 | Has he turned up the volume yet? | ¿Ya ha subido él el volumen? | Tell me, has he turned up the volume yet? | Dime, ¿ya ha subido él el volumen? | Question
🤔 | Has he turned down the volume yet? | ¿Ya ha bajado él el volumen? | Tell me, has he turned down the volume yet? | Dime, ¿ya ha bajado él el volumen? | Question
🤔 | Has he logged in to the website yet? | ¿Ya ha iniciado sesión él en el sitio web? | Tell me, has he logged in to the website yet? | Dime, ¿ya ha iniciado sesión él en el sitio web? | Question
🤔 | Has he logged out of the website yet? | ¿Ya ha cerrado sesión él en el sitio web? | Tell me, has he logged out of the website yet? | Dime, ¿ya ha cerrado sesión él en el sitio web? | Question
🎵 | He's already downloaded the music, but he hasn't played it yet. | Él ya ha descargado la música, pero aún no la ha reproducido. | Look, he's already downloaded the music, but he hasn't played it yet. | Mira, él ya ha descargado la música, pero aún no la ha reproducido. | Phrase
🖼️ | He's already printed the photos, but he hasn't uploaded them yet. | Él ya ha impreso las fotos, pero aún no las ha subido. | Look, he's already printed the photos, but he hasn't uploaded them yet. | Mira, él ya ha impreso las fotos, pero aún no las ha subido. | Phrase
🎵 | He's already played the music, but he hasn't downloaded it yet. | Él ya ha reproducido la música, pero aún no la ha descargado. | Look, he's already played the music, but he hasn't downloaded it yet. | Mira, él ya ha reproducido la música, pero aún no la ha descargado. | Phrase
✉️ | He's already sent the email, but he hasn't written it yet. | Él ya ha enviado el correo electrónico, pero aún no lo ha escrito. | Look, he's already sent the email, but he hasn't written it yet. | Mira, él ya ha enviado el correo electrónico, pero aún no lo ha escrito. | Phrase
🖼️ | She's already printed the photos, but she hasn't uploaded them yet. | Ella ya ha impreso las fotos, pero aún no las ha subido. | Look, she's already printed the photos, but she hasn't uploaded them yet. | Mira, ella ya ha impreso las fotos, pero aún no las ha subido. | Phrase
🎵 | She's already downloaded the music, but she hasn't played it yet. | Ella ya ha descargado la música, pero aún no la ha reproducido. | Look, she's already downloaded the music, but she hasn't played it yet. | Mira, ella ya ha descargado la música, pero aún no la ha reproducido. | Phrase
🎵 | She's already played the music, but she hasn't downloaded it yet. | Ella ya ha reproducido la música, pero aún no la ha descargado. | Look, she's already played the music, but she hasn't downloaded it yet. | Mira, ella ya ha reproducido la música, pero aún no la ha descargado. | Phrase
✉️ | She's already written the email, but she hasn't sent it yet. | Ella ya ha escrito el correo electrónico, pero aún no lo ha enviado. | Look, she's already written the email, but she hasn't sent it yet. | Mira, ella ya ha escrito el correo electrónico, pero aún no lo ha enviado. | Phrase
✉️ | She's already sent the email, but she hasn't written it yet. | Ella ya ha enviado el correo electrónico, pero aún no lo ha escrito. | Look, she's already sent the email, but she hasn't written it yet. | Mira, ella ya ha enviado el correo electrónico, pero aún no lo ha escrito. | Phrase
🤔 | Has he printed the photos yet? | ¿Ya ha impreso él las fotos? | Tell me, has he printed the photos yet? | Dime, ¿ya ha impreso él las fotos? | Question
🎵 | Has he downloaded the music yet? | ¿Ya ha descargado él la música? | Tell me, has he downloaded the music yet? | Dime, ¿ya ha descargado él la música? | Question
🎵 | Has he played the music yet? | ¿Ya ha reproducido él la música? | Tell me, has he played the music yet? | Dime, ¿ya ha reproducido él la música? | Question
🤔 | Has he written the email yet? | ¿Ya ha escrito él el correo electrónico? | Tell me, has he written the email yet? | Dime, ¿ya ha escrito él el correo electrónico? | Question
🤔 | Has he sent the email yet? | ¿Ya ha enviado él el correo electrónico? | Tell me, has he sent the email yet? | Dime, ¿ya ha enviado él el correo electrónico? | Question
🤔 | Has she printed the photos yet? | ¿Ya ha impreso ella las fotos? | Tell me, has she printed the photos yet? | Dime, ¿ya ha impreso ella las fotos? | Question
🎵 | Has she downloaded the music yet? | ¿Ya ha descargado ella la música? | Tell me, has she downloaded the music yet? | Dime, ¿ya ha descargado ella la música? | Question
🎵 | Has she played the music yet? | ¿Ya ha reproducido ella la música? | Tell me, has she played the music yet? | Dime, ¿ya ha reproducido ella la música? | Question
🤔 | Has she written the email yet? | ¿Ya ha escrito ella el correo electrónico? | Tell me, has she written the email yet? | Dime, ¿ya ha escrito ella el correo electrónico? | Question
🤔 | Has she sent the email yet? | ¿Ya ha enviado ella el correo electrónico? | Tell me, has she sent the email yet? | Dime, ¿ya ha enviado ella el correo electrónico? | Question
👍 | Yes, he's printed them. | Sí, ya las ha impreso. | Yes, he's printed them. | Sí, ya las ha impreso. | Phrase
👍 | Yes, he's downloaded it. | Sí, ya la ha descargado. | Yes, he's downloaded it. | Sí, ya la ha descargado. | Phrase
👍 | Yes, he's played it. | Sí, ya la ha reproducido. | Yes, he's played it. | Sí, ya la ha reproducido. | Phrase
👍 | Yes, he's written it. | Sí, ya lo ha escrito. | Yes, he's written it. | Sí, ya lo ha escrito. | Phrase
👍 | Yes, he's sent it. | Sí, ya lo ha enviado. | Yes, he's sent it. | Sí, ya lo ha enviado. | Phrase
👍 | Yes, she's printed them. | Sí, ya las ha impreso. | Yes, she's printed them. | Sí, ya las ha impreso. | Phrase
👍 | Yes, she's downloaded it. | Sí, ya la ha descargado. | Yes, she's downloaded it. | Sí, ya la ha descargado. | Phrase
👍 | Yes, she's played it. | Sí, ya la ha reproducido. | Yes, she's played it. | Sí, ya la ha reproducido. | Phrase
👍 | Yes, she's written it. | Sí, ya lo ha escrito. | Yes, she's written it. | Sí, ya lo ha escrito. | Phrase
👍 | Yes, she's sent it. | Sí, ya lo ha enviado. | Yes, she's sent it. | Sí, ya lo ha enviado. | Phrase
🙅 | No, he hasn't printed them. | No, aún no las ha impreso. | No, he hasn't printed them. | No, aún no las ha impreso. | Phrase
🙅 | No, he hasn't downloaded it. | No, aún no la ha descargado. | No, he hasn't downloaded it. | No, aún no la ha descargado. | Phrase
🙅 | No, he hasn't played it. | No, aún no la ha reproducido. | No, he hasn't played it. | No, aún no la ha reproducido. | Phrase
🙅 | No, he hasn't written it. | No, aún no lo ha escrito. | No, he hasn't written it. | No, aún no lo ha escrito. | Phrase
🙅 | No, he hasn't sent it. | No, aún no lo ha enviado. | No, he hasn't sent it. | No, aún no lo ha enviado. | Phrase
🙅 | No, she hasn't printed them. | No, aún no las ha impreso. | No, she hasn't printed them. | No, aún no las ha impreso. | Phrase
🙅 | No, she hasn't downloaded it. | No, aún no la ha descargado. | No, she hasn't downloaded it. | No, aún no la ha descargado. | Phrase
🙅 | No, she hasn't played it. | No, aún no la ha reproducido. | No, she hasn't played it. | No, aún no la ha reproducido. | Phrase
🙅 | No, she hasn't written it. | No, aún no lo ha escrito. | No, she hasn't written it. | No, aún no lo ha escrito. | Phrase
🙅 | No, she hasn't sent it. | No, aún no lo ha enviado. | No, she hasn't sent it. | No, aún no lo ha enviado. | Phrase
⚡ | What have scientists designed to use solar energy? | ¿Qué han diseñado los científicos para usar energía solar? | Tell me, what have scientists designed to use solar energy? | Dime, ¿qué han diseñado los científicos para usar energía solar? | Question
☀️ | Scientists have designed solar panels that use sunlight to make energy. | Los científicos han diseñado paneles solares que utilizan la luz solar para generar energía. | Scientists have designed solar panels that use sunlight to make energy. | Los científicos han diseñado paneles solares que utilizan la luz solar para generar energía. | Phrase
    """.trimIndent(), "Basics 5 - List 8")
}
