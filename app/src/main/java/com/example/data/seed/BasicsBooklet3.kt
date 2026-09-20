package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object BasicsBooklet3 {
    const val FOLDER_NAME = "Basics 3"

    val categoryNames: List<String> = (1..8).map { "Basics 3 - List $it" }

    private fun parseCards(rawText: String, categoryName: String): List<Flashcard> {
        return rawText.lineSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() && it.contains("|") }
            .map { line ->
                val parts = line.split("|").map { it.trim() }
                Flashcard(
                    emoji = parts.getOrElse(0) { "💡" },
                    english = parts.getOrElse(1) { "" },
                    spanish = parts.getOrElse(2) { "" },
                    example = parts.getOrElse(3) { "" },
                    exampleTranslation = parts.getOrElse(4) { "" },
                    type = parts.getOrElse(5) { "Word" },
                    category = categoryName,
                    cefrLevel = "A1",
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
🔬 | Science | Ciencias | I like science | Me gustan las ciencias | Word
🎨 | Art | Arte | We study art | Estudiamos arte | Word
🔢 | Math | Matemáticas | Math is fun | Las matemáticas son divertidas | Word
🏃 | P.E. | Educación física | We have P.E. today | Tenemos educación física hoy | Word
🎵 | Music | Música | Listen to music | Escuchar música | Word
🌍 | Social Studies | Estudios sociales | Social studies class | Clase de estudios sociales | Word
❓ | What's his favorite subject? | ¿Cuál es su materia favorita? | Tell me, what's his favorite subject? | Dime, ¿cuál es su materia favorita? | Question
🔬 | It's science. | Son las ciencias. | Look, it's science. | Mira, son las ciencias. | Phrase
❓ | What's her favorite subject? | ¿Cuál es su materia favorita? | Tell me, what's her favorite subject? | Dime, ¿cuál es su materia favorita? | Question
🔬 | It's science. | Son las ciencias. | Look, it's science. | Mira, son las ciencias. | Phrase
🇧🇷 | Brazil | Brasil | He lives in Brazil | Él vive en Brasil | Word
🇨🇦 | Canada | Canadá | Canada is big | Canadá es grande | Word
🇪🇬 | Egypt | Egipto | The pyramids in Egypt | Las pirámides en Egipto | Word
🇰🇷 | South Korea | Corea del Sur | Welcome to South Korea | Bienvenidos a Corea del Sur | Word
❓ | Where is he from? | ¿De dónde es él? | Tell me, where is he from? | Dime, ¿de dónde es él? | Question
🇧🇷 | He's from Brazil. | Él es de Brasil. | Look, he's from Brazil. | Mira, él es de Brasil. | Phrase
❓ | Where is she from? | ¿De dónde es ella? | Tell me, where is she from? | Dime, ¿de dónde es ella? | Question
🇧🇷 | She's from Brazil. | Ella es de Brasil. | Look, she's from Brazil. | Mira, ella es de Brasil. | Phrase
🤷 | I'm not sure. | No estoy seguro. | Well, I'm not sure. | Bueno, no estoy seguro. | Phrase
👀 | Look at this. | Mira esto. | Please, look at this. | Por favor, mira esto. | Phrase
👋 | Welcome. | Bienvenido. | Welcome to our school. | Bienvenido a nuestra escuela. | Phrase
👋 | See you later. | Hasta luego. | OK, see you later. | Está bien, hasta luego. | Phrase
👋 | See you tomorrow. | Hasta mañana. | Bye, see you tomorrow. | Adiós, hasta mañana. | Phrase
🤝 | Be helpful. | Sé servicial. | Always be helpful. | Siempre sé servicial. | Phrase
🤲 | Help others. | Ayuda a los demás. | Please, help others. | Por favor, ayuda a los demás. | Phrase
📜 | History | Historia | We read history | Leemos historia | Word
🗺️ | Geography | Geografía | Study geography | Estudia geografía | Word
📖 | English | Inglés | Speak English | Habla inglés | Word
🇪🇸 | Spanish | Español | Practice Spanish | Practica español | Word
🇨🇦 | He's from Canada. | Él es de Canadá. | Look, he's from Canada. | Mira, él es de Canadá. | Phrase
🇪🇬 | He's from Egypt. | Él es de Egipto. | Look, he's from Egypt. | Mira, él es de Egipto. | Phrase
🇰🇷 | He's from South Korea. | Él es de Corea del Sur. | Look, he's from South Korea. | Mira, él es de Corea del Sur. | Phrase
🇨🇦 | She's from Canada. | Ella es de Canadá. | Look, she's from Canada. | Mira, ella es de Canadá. | Phrase
🇪🇬 | She's from Egypt. | Ella es de Egipto. | Look, she's from Egypt. | Mira, ella es de Egipto. | Phrase
🇰🇷 | She's from South Korea. | Ella es de Corea del Sur. | Look, she's from South Korea. | Mira, ella es de Corea del Sur. | Phrase
🎨 | What's his favorite subject? It's art. | ¿Cuál es su materia favorita? Es arte. | What's his favorite subject? It's art. | ¿Cuál es su materia favorita? Es arte. | Phrase
🔢 | What's his favorite subject? It's math. | ¿Cuál es su materia favorita? Son las matemáticas. | What's his favorite subject? It's math. | ¿Cuál es su materia favorita? Son las matemáticas. | Phrase
🏃 | What's his favorite subject? It's P.E. | ¿Cuál es su materia favorita? Es educación física. | What's his favorite subject? It's P.E. | ¿Cuál es su materia favorita? Es educación física. | Phrase
🎵 | What's his favorite subject? It's music. | ¿Cuál es su materia favorita? Es música. | What's his favorite subject? It's music. | ¿Cuál es su materia favorita? Es música. | Phrase
🌍 | What's his favorite subject? It's social studies. | ¿Cuál es su materia favorita? Son los estudios sociales. | What's his favorite subject? It's social studies. | ¿Cuál es su materia favorita? Son los estudios sociales. | Phrase
🎨 | What's her favorite subject? It's art. | ¿Cuál es su materia favorita? Es arte. | What's her favorite subject? It's art. | ¿Cuál es su materia favorita? Es arte. | Phrase
🔢 | What's her favorite subject? It's math. | ¿Cuál es su materia favorita? Son las matemáticas. | What's her favorite subject? It's math. | ¿Cuál es su materia favorita? Son las matemáticas. | Phrase
🏃 | What's her favorite subject? It's P.E. | ¿Cuál es su materia favorita? Es educación física. | What's her favorite subject? It's P.E. | ¿Cuál es su materia favorita? Es educación física. | Phrase
🎵 | What's her favorite subject? It's music. | ¿Cuál es su materia favorita? Es música. | What's her favorite subject? It's music. | ¿Cuál es su materia favorita? Es música. | Phrase
🌍 | What's her favorite subject? It's social studies. | ¿Cuál es su materia favorita? Son los estudios sociales. | What's her favorite subject? It's social studies. | ¿Cuál es su materia favorita? Son los estudios sociales. | Phrase
❓ | What's your favorite subject? | ¿Cuál es tu materia favorita? | Tell me, what's your favorite subject? | Dime, ¿cuál es tu materia favorita? | Question
🔬 | It's science. | Son las ciencias. | For me, it's science. | Para mí, son las ciencias. | Phrase
🎨 | It's art. | Es arte. | For me, it's art. | Para mí, es arte. | Phrase
🔢 | It's math. | Son las matemáticas. | For me, it's math. | Para mí, son las matemáticas. | Phrase
🏃 | It's P.E. | Es educación física. | For me, it's P.E. | Para mí, es educación física. | Phrase
🎵 | It's music. | Es música. | For me, it's music. | Para mí, es música. | Phrase
🌍 | It's social studies. | Son los estudios sociales. | For me, it's social studies. | Para mí, son los estudios sociales. | Phrase
❓ | Where are you from? | ¿De dónde eres? | Tell me, where are you from? | Dime, ¿de dónde eres? | Question
🇧🇷 | I'm from Brazil. | Soy de Brasil. | Look, I'm from Brazil. | Mira, soy de Brasil. | Phrase
🇨🇦 | I'm from Canada. | Soy de Canadá. | Look, I'm from Canada. | Mira, soy de Canadá. | Phrase
🇪🇬 | I'm from Egypt. | Soy de Egipto. | Look, I'm from Egypt. | Mira, soy de Egipto. | Phrase
🇰🇷 | I'm from South Korea. | Soy de Corea del Sur. | Look, I'm from South Korea. | Mira, soy de Corea del Sur. | Phrase
📜 | He's from history class. | Él viene de la clase de historia. | Look, he's from history class. | Mira, él viene de la clase de historia. | Phrase
📖 | She's from English class. | Ella viene de la clase de inglés. | Look, she's from English class. | Mira, ella viene de la clase de inglés. | Phrase
🗺️ | I like geography. | Me gusta la geografía. | Yes, I like geography. | Sí, me gusta la geografía. | Phrase
🇪🇸 | I like Spanish. | Me gusta el español. | Yes, I like Spanish. | Sí, me gusta el español. | Phrase
📜 | What's his favorite subject? It's history. | ¿Cuál es su materia favorita? Es historia. | What's his favorite subject? It's history. | ¿Cuál es su materia favorita? Es historia. | Phrase
🗺️ | What's her favorite subject? It's geography. | ¿Cuál es su materia favorita? Es geografía. | What's her favorite subject? It's geography. | ¿Cuál es su materia favorita? Es geografía. | Phrase
📖 | What's his favorite subject? It's English. | ¿Cuál es su materia favorita? Es inglés. | What's his favorite subject? It's English. | ¿Cuál es su materia favorita? Es inglés. | Phrase
🇪🇸 | What's her favorite subject? It's Spanish. | ¿Cuál es su materia favorita? Es español. | What's her favorite subject? It's Spanish. | ¿Cuál es su materia favorita? Es español. | Phrase
🇨🇦 | Where is he from? He's from Canada. | ¿De dónde es él? Él es de Canadá. | Where is he from? He's from Canada. | ¿De dónde es él? Él es de Canadá. | Phrase
🇪🇬 | Where is she from? She's from Egypt. | ¿De dónde es ella? Ella es de Egipto. | Where is she from? She's from Egypt. | ¿De dónde es ella? Ella es de Egipto. | Phrase
    """.trimIndent(), "Basics 3 - List 1")

    private fun getList2(): List<Flashcard> = parseCards("""
🌸 | Spring | Primavera | Flowers in spring | Flores en primavera | Word
☀️ | Summer | Verano | Hot in summer | Calor en verano | Word
🍂 | Fall | Otoño | Leaves fall in fall | Las hojas caen en otoño | Word
❄️ | Winter | Invierno | Cold in winter | Frío en invierno | Word
📅 | January | Enero | January is month one | Enero es el mes uno | Word
📅 | February | Febrero | February is short | Febrero es corto | Word
📅 | March | Marzo | Wind in March | Viento en marzo | Word
📅 | April | Abril | Rain in April | Lluvia en abril | Word
📅 | May | Mayo | Sunny May | Mayo soleado | Word
📅 | June | Junio | Summer in June | Verano en junio | Word
📅 | July | Julio | Hot July | Julio caluroso | Word
📅 | August | Agosto | Beach in August | Playa en agosto | Word
📅 | September | Septiembre | School in September | Escuela en septiembre | Word
📅 | October | Octubre | Fall in October | Otoño en octubre | Word
📅 | November | Noviembre | Cool November | Noviembre fresco | Word
📅 | December | Diciembre | Holidays in December | Fiestas en diciembre | Word
❓ | When is his birthday? | ¿Cuándo es su cumpleaños? | Tell me, when is his birthday? | Dime, ¿cuándo es su cumpleaños? | Question
📅 | It's in January. | Es en enero. | Look, it's in January. | Mira, es en enero. | Phrase
❓ | When is her birthday? | ¿Cuándo es su cumpleaños? | Tell me, when is her birthday? | Dime, ¿cuándo es su cumpleaños? | Question
📅 | It's in January. | Es en enero. | Look, it's in January. | Mira, es en enero. | Phrase
❓ | What's the date today? | ¿Cuál es la fecha de hoy? | Tell me, what's the date today? | Dime, ¿cuál es la fecha de hoy? | Question
📅 | It's January 15th. | Es 15 de enero. | Look, it's January 15th. | Mira, es 15 de enero. | Phrase
🗓️ | Look at the calendar. | Mira el calendario. | Please, look at the calendar. | Por favor, mira el calendario. | Phrase
🎂 | Happy birthday! | ¡Feliz cumpleaños! | Wow, happy birthday! | ¡Vaya, feliz cumpleaños! | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
🎉 | Have fun. | Que te diviertas. | Go and have fun. | Ve y diviértete. | Phrase
🌟 | Enjoy your day. | Disfruta tu día. | Please, enjoy your day. | Por favor, disfruta tu día. | Phrase
🥰 | Be thoughtful. | Sé considerado. | Always be thoughtful. | Siempre sé considerado. | Phrase
💭 | Think of others. | Piensa en los demás. | Please, think of others. | Por favor, piensa en los demás. | Phrase
1️⃣ | First | Primero | First place | Primer lugar | Word
2️⃣ | Second | Segundo | Second turn | Segundo turno | Word
3️⃣ | Third | Tercero | Third time | Tercera vez | Word
4️⃣ | Fourth | Cuarto | Fourth lesson | Cuarta lección | Word
5️⃣ | Fifth | Quinto | Fifth day | Quinto día | Word
6️⃣ | Sixth | Sexto | Sixth grade | Sexto grado | Word
7️⃣ | Seventh | Séptimo | Seventh street | Séptima calle | Word
8️⃣ | Eighth | Octavo | Eighth step | Octavo paso | Word
9️⃣ | Ninth | Noveno | Ninth chapter | Noveno capítulo | Word
🔟 | Tenth | Décimo | Tenth floor | Décimo piso | Word
❓ | When is your birthday? | ¿Cuándo es tu cumpleaños? | Tell me, when is your birthday? | Dime, ¿cuándo es tu cumpleaños? | Question
📅 | It's in May. | Es en mayo. | Look, it's in May. | Mira, es en mayo. | Phrase
📅 | When is his birthday? It's in February. | ¿Cuándo es su cumpleaños? Es en febrero. | When is his birthday? It's in February. | ¿Cuándo es su cumpleaños? Es en febrero. | Phrase
📅 | When is his birthday? It's in March. | ¿Cuándo es su cumpleaños? Es en marzo. | When is his birthday? It's in March. | ¿Cuándo es su cumpleaños? Es en marzo. | Phrase
📅 | When is his birthday? It's in April. | ¿Cuándo es su cumpleaños? Es en abril. | When is his birthday? It's in April. | ¿Cuándo es su cumpleaños? Es en abril. | Phrase
📅 | When is his birthday? It's in May. | ¿Cuándo es su cumpleaños? Es en mayo. | When is his birthday? It's in May. | ¿Cuándo es su cumpleaños? Es en mayo. | Phrase
📅 | When is his birthday? It's in June. | ¿Cuándo es su cumpleaños? Es en junio. | When is his birthday? It's in June. | ¿Cuándo es su cumpleaños? Es en junio. | Phrase
📅 | When is his birthday? It's in July. | ¿Cuándo es su cumpleaños? Es en julio. | When is his birthday? It's in July. | ¿Cuándo es su cumpleaños? Es en julio. | Phrase
📅 | When is his birthday? It's in August. | ¿Cuándo es su cumpleaños? Es en agosto. | When is his birthday? It's in August. | ¿Cuándo es su cumpleaños? Es en agosto. | Phrase
📅 | When is his birthday? It's in September. | ¿Cuándo es su cumpleaños? Es en septiembre. | When is his birthday? It's in September. | ¿Cuándo es su cumpleaños? Es en septiembre. | Phrase
📅 | When is his birthday? It's in October. | ¿Cuándo es su cumpleaños? Es en octubre. | When is his birthday? It's in October. | ¿Cuándo es su cumpleaños? Es en octubre. | Phrase
📅 | When is his birthday? It's in November. | ¿Cuándo es su cumpleaños? Es en noviembre. | When is his birthday? It's in November. | ¿Cuándo es su cumpleaños? Es en noviembre. | Phrase
📅 | When is his birthday? It's in December. | ¿Cuándo es su cumpleaños? Es en diciembre. | When is his birthday? It's in December. | ¿Cuándo es su cumpleaños? Es en diciembre. | Phrase
📅 | When is her birthday? It's in February. | ¿Cuándo es su cumpleaños? Es en febrero. | When is her birthday? It's in February. | ¿Cuándo es su cumpleaños? Es en febrero. | Phrase
📅 | When is her birthday? It's in March. | ¿Cuándo es su cumpleaños? Es en marzo. | When is her birthday? It's in March. | ¿Cuándo es su cumpleaños? Es en marzo. | Phrase
📅 | When is her birthday? It's in April. | ¿Cuándo es su cumpleaños? Es en abril. | When is her birthday? It's in April. | ¿Cuándo es su cumpleaños? Es en abril. | Phrase
📅 | When is her birthday? It's in May. | ¿Cuándo es su cumpleaños? Es en mayo. | When is her birthday? It's in May. | ¿Cuándo es su cumpleaños? Es en mayo. | Phrase
📅 | When is her birthday? It's in June. | ¿Cuándo es su cumpleaños? Es en junio. | When is her birthday? It's in June. | ¿Cuándo es su cumpleaños? Es en junio. | Phrase
📅 | When is her birthday? It's in July. | ¿Cuándo es su cumpleaños? Es en julio. | When is her birthday? It's in July. | ¿Cuándo es su cumpleaños? Es en julio. | Phrase
📅 | When is her birthday? It's in August. | ¿Cuándo es su cumpleaños? Es en agosto. | When is her birthday? It's in August. | ¿Cuándo es su cumpleaños? Es en agosto. | Phrase
📅 | When is her birthday? It's in September. | ¿Cuándo es su cumpleaños? Es en septiembre. | When is her birthday? It's in September. | ¿Cuándo es su cumpleaños? Es en septiembre. | Phrase
📅 | When is her birthday? It's in October. | ¿Cuándo es su cumpleaños? Es en octubre. | When is her birthday? It's in October. | ¿Cuándo es su cumpleaños? Es en octubre. | Phrase
📅 | When is her birthday? It's in November. | ¿Cuándo es su cumpleaños? Es en noviembre. | When is her birthday? It's in November. | ¿Cuándo es su cumpleaños? Es en noviembre. | Phrase
📅 | When is her birthday? It's in December. | ¿Cuándo es su cumpleaños? Es en diciembre. | When is her birthday? It's in December. | ¿Cuándo es su cumpleaños? Es en diciembre. | Phrase
📅 | What's the date today? It's March 1st. | ¿Cuál es la fecha de hoy? Es primero de marzo. | What's the date today? It's March 1st. | ¿Cuál es la fecha de hoy? Es primero de marzo. | Phrase
📅 | What's the date today? It's April 2nd. | ¿Cuál es la fecha de hoy? Es 2 de abril. | What's the date today? It's April 2nd. | ¿Cuál es la fecha de hoy? Es 2 de abril. | Phrase
📅 | What's the date today? It's May 3rd. | ¿Cuál es la fecha de hoy? Es 3 de mayo. | What's the date today? It's May 3rd. | ¿Cuál es la fecha de hoy? Es 3 de mayo. | Phrase
📅 | What's the date today? It's June 4th. | ¿Cuál es la fecha de hoy? Es 4 de junio. | What's the date today? It's June 4th. | ¿Cuál es la fecha de hoy? Es 4 de junio. | Phrase
📅 | What's the date today? It's July 5th. | ¿Cuál es la fecha de hoy? Es 5 de julio. | What's the date today? It's July 5th. | ¿Cuál es la fecha de hoy? Es 5 de julio. | Phrase
📅 | What's the date today? It's August 6th. | ¿Cuál es la fecha de hoy? Es 6 de agosto. | What's the date today? It's August 6th. | ¿Cuál es la fecha de hoy? Es 6 de agosto. | Phrase
📅 | What's the date today? It's September 7th. | ¿Cuál es la fecha de hoy? Es 7 de septiembre. | What's the date today? It's September 7th. | ¿Cuál es la fecha de hoy? Es 7 de septiembre. | Phrase
📅 | What's the date today? It's October 8th. | ¿Cuál es la fecha de hoy? Es 8 de octubre. | What's the date today? It's October 8th. | ¿Cuál es la fecha de hoy? Es 8 de octubre. | Phrase
📅 | What's the date today? It's November 9th. | ¿Cuál es la fecha de hoy? Es 9 de noviembre. | What's the date today? It's November 9th. | ¿Cuál es la fecha de hoy? Es 9 de noviembre. | Phrase
📅 | What's the date today? It's December 10th. | ¿Cuál es la fecha de hoy? Es 10 de diciembre. | What's the date today? It's December 10th. | ¿Cuál es la fecha de hoy? Es 10 de diciembre. | Phrase
1️⃣1️⃣ | Eleventh | Undécimo | Eleventh lesson | Undécima lección | Word
1️⃣2️⃣ | Twelfth | Duodécimo | Twelfth player | Duodécimo jugador | Word
1️⃣3️⃣ | Thirteenth | Décimo tercero | Thirteenth row | Décima tercera fila | Word
1️⃣4️⃣ | Fourteenth | Décimo cuarto | Fourteenth floor | Décimo cuarto piso | Word
1️⃣5️⃣ | Fifteenth | Décimo quinto | Fifteenth day | Décimo quinto día | Word
1️⃣6️⃣ | Sixteenth | Décimo sexto | Sixteenth chapter | Décimo sexto capítulo | Word
1️⃣7️⃣ | Seventeenth | Décimo séptimo | Seventeenth street | Décima séptima calle | Word
1️⃣8️⃣ | Eighteenth | Décimo octavo | Eighteenth birthday | Décimo octavo cumpleaños | Word
1️⃣9️⃣ | Nineteenth | Décimo noveno | Nineteenth century | Siglo diecinueve | Word
2️⃣0️⃣ | Twentieth | Vigésimo | Twentieth anniversary | Vigésimo aniversario | Word
2️⃣1️⃣ | Twenty-first | Vigésimo primero | Twenty-first of June | Veintiuno de junio | Word
2️⃣2️⃣ | Twenty-second | Vigésimo segundo | Twenty-second day | Vigésimo segundo día | Word
2️⃣3️⃣ | Twenty-third | Vigésimo tercero | Twenty-third lesson | Vigésima tercera lección | Word
3️⃣0️⃣ | Thirtieth | Trigésimo | Thirtieth minute | Trigésimo minuto | Word
3️⃣1️⃣ | Thirty-first | Trigésimo primero | Thirty-first of December | Treinta y uno de diciembre | Word
🌸 | My favorite season is spring. | Mi estación favorita es la primavera. | Look, my favorite season is spring. | Mira, mi estación favorita es la primavera. | Phrase
☀️ | My favorite season is summer. | Mi estación favorita es el verano. | Look, my favorite season is summer. | Mira, mi estación favorita es el verano. | Phrase
🍂 | My favorite season is fall. | Mi estación favorita es el otoño. | Look, my favorite season is fall. | Mira, mi estación favorita es el otoño. | Phrase
❄️ | My favorite season is winter. | Mi estación favorita es el invierno. | Look, my favorite season is winter. | Mira, mi estación favorita es el invierno. | Phrase
☀️ | His favorite season is summer. | Su estación favorita es el verano. | Look, his favorite season is summer. | Mira, su estación favorita es el verano. | Phrase
❄️ | Her favorite season is winter. | Su estación favorita es el invierno. | Look, her favorite season is winter. | Mira, su estación favorita es el invierno. | Phrase
    """.trimIndent(), "Basics 3 - List 2")

    private fun getList3(): List<Flashcard> = parseCards("""
🖍️ | Chalk | Tiza | White chalk | Tiza blanca | Word
🎨 | Paint | Pintura | Color paint | Pintura de color | Word
📼 | Tape | Cinta pegante / Cinta adhesiva | Strong tape | Cinta pegante fuerte | Word
✂️ | Scissors | Tijeras | Cut with scissors | Corta con tijeras | Word
🧴 | Glue | Pegamento | Sticky glue | Pegamento pegajoso | Word
📄 | Paper | Papel | Clean paper | Papel limpio | Word
❓ | What does he have? | ¿Qué tiene él? | Tell me, what does he have? | Dime, ¿qué tiene él? | Question
🖍️ | What does he have? He has some chalk. | ¿Qué tiene él? Él tiene algo de tiza. | What does he have? He has some chalk. | ¿Qué tiene él? Él tiene algo de tiza. | Phrase
❓ | What does she have? | ¿Qué tiene ella? | Tell me, what does she have? | Dime, ¿qué tiene ella? | Question
🖍️ | What does she have? She has some chalk. | ¿Qué tiene ella? Ella tiene algo de tiza. | What does she have? She has some chalk. | ¿Qué tiene ella? Ella tiene algo de tiza. | Phrase
🍬 | Gum | Chicle | Chew gum | Masticar chicle | Word
🍿 | Popcorn | Palomitas | Eat popcorn | Come palomitas | Word
🥜 | Peanuts | Cacahuates | Crunchy peanuts | Cacahuates crujientes | Word
🥔 | Potato chips | Papas fritas | Salty potato chips | Papas fritas saladas | Word
🥤 | Soda | Refresco | Cold soda | Refresco frío | Word
🍭 | Candy | Dulces | Sweet candy | Dulces deliciosos | Word
❓ | What does he have? | ¿Qué tiene él? | Tell me, what does he have? | Dime, ¿qué tiene él? | Question
🍬 | What does he have? He has some gum. | ¿Qué tiene él? Él tiene algo de chicle. | What does he have? He has some gum. | ¿Qué tiene él? Él tiene algo de chicle. | Phrase
❓ | What does she have? | ¿Qué tiene ella? | Tell me, what does she have? | Dime, ¿qué tiene ella? | Question
🍬 | What does she have? She has some gum. | ¿Qué tiene ella? Ella tiene algo de chicle. | What does she have? She has some gum. | ¿Qué tiene ella? Ella tiene algo de chicle. | Phrase
❓ | Do you want some? | ¿Quieres un poco? | Tell me, do you want some? | Dime, ¿quieres un poco? | Question
🙏 | Do you want some? Yes, please. | ¿Quieres un poco? Sí, por favor. | Do you want some? Yes, please. | ¿Quieres un poco? Sí, por favor. | Phrase
🙅 | Do you want some? No, thank you. | ¿Quieres un poco? No, gracias. | Do you want some? No, thank you. | ¿Quieres un poco? No, gracias. | Phrase
👍 | Sure, thanks. | Claro, gracias. | Sure, thanks a lot. | Claro, muchas gracias. | Phrase
🤲 | Help yourself. | Sírvete tú mismo. | Please, help yourself. | Por favor, sírvete tú mismo. | Phrase
🤝 | Share with friends. | Comparte con amigos. | Always share with friends. | Siempre comparte con amigos. | Phrase
💖 | Be generous. | Sé generoso. | Always be generous. | Siempre sé generoso. | Phrase
🎁 | Give to others. | Da a los demás. | Please, give to others. | Por favor, da a los demás. | Phrase
🧵 | String | Cuerda | Long string | Cuerda larga | Word
🎀 | Ribbon | Cinta de tela / Listón | Red ribbon | Listón rojo | Word
📎 | Stapler | Grapadora / Cosedora | Office stapler | Grapadora de oficina | Word
📎 | Paper clips | Clips de papel / Ganchos para papel | Metal paper clips | Clips de metal | Word
📼 | What does he have? He has some tape. | ¿Qué tiene él? Él tiene cinta pegante. | What does he have? He has some tape. | ¿Qué tiene él? Él tiene cinta pegante. | Phrase
✂️ | What does he have? He has some scissors. | ¿Qué tiene él? Él tiene unas tijeras. | What does he have? He has some scissors. | ¿Qué tiene él? Él tiene unas tijeras. | Phrase
🧴 | What does he have? He has some glue. | ¿Qué tiene él? Él tiene algo de pegamento. | What does he have? He has some glue. | ¿Qué tiene él? Él tiene algo de pegamento. | Phrase
📄 | What does he have? He has some paper. | ¿Qué tiene él? Él tiene algo de papel. | What does he have? He has some paper. | ¿Qué tiene él? Él tiene algo de papel. | Phrase
📼 | What does she have? She has some tape. | ¿Qué tiene ella? Ella tiene cinta pegante. | What does she have? She has some tape. | ¿Qué tiene ella? Ella tiene cinta pegante. | Phrase
✂️ | What does she have? She has some scissors. | ¿Qué tiene ella? Ella tiene unas tijeras. | What does she have? She has some scissors. | ¿Qué tiene ella? Ella tiene unas tijeras. | Phrase
🧴 | What does she have? She has some glue. | ¿Qué tiene ella? Ella tiene algo de pegamento. | What does she have? She has some glue. | ¿Qué tiene ella? Ella tiene algo de pegamento. | Phrase
📄 | What does she have? She has some paper. | ¿Qué tiene ella? Ella tiene algo de papel. | What does she have? She has some paper. | ¿Qué tiene ella? Ella tiene algo de papel. | Phrase
🎨 | What does he have? He has some paint. | ¿Qué tiene él? Él tiene algo de pintura. | What does he have? He has some paint. | ¿Qué tiene él? Él tiene algo de pintura. | Phrase
✂️ | What does he have? He has some scissors. | ¿Qué tiene él? Él tiene unas tijeras. | What does he have? He has some scissors. | ¿Qué tiene él? Él tiene unas tijeras. | Phrase
🧴 | What does he have? He has some glue. | ¿Qué tiene él? Él tiene algo de pegamento. | What does he have? He has some glue. | ¿Qué tiene él? Él tiene algo de pegamento. | Phrase
📼 | What does she have? She has some tape. | ¿Qué tiene ella? Ella tiene cinta pegante. | What does she have? She has some tape. | ¿Qué tiene ella? Ella tiene cinta pegante. | Phrase
📄 | What does she have? She has some paper. | ¿Qué tiene ella? Ella tiene algo de papel. | What does she have? She has some paper. | ¿Qué tiene ella? Ella tiene algo de papel. | Phrase
🍿 | What does he have? He has some popcorn. | ¿Qué tiene él? Él tiene palomitas. | What does he have? He has some popcorn. | ¿Qué tiene él? Él tiene palomitas. | Phrase
🥜 | What does he have? He has some peanuts. | ¿Qué tiene él? Él tiene cacahuates. | What does he have? He has some peanuts. | ¿Qué tiene él? Él tiene cacahuates. | Phrase
🥔 | What does he have? He has some potato chips. | ¿Qué tiene él? Él tiene papas fritas. | What does he have? He has some potato chips. | ¿Qué tiene él? Él tiene papas fritas. | Phrase
🥤 | What does he have? He has some soda. | ¿Qué tiene él? Él tiene refresco. | What does he have? He has some soda. | ¿Qué tiene él? Él tiene refresco. | Phrase
🍭 | What does he have? He has some candy. | ¿Qué tiene él? Él tiene dulces. | What does he have? He has some candy. | ¿Qué tiene él? Él tiene dulces. | Phrase
🍿 | What does she have? She has some popcorn. | ¿Qué tiene ella? Ella tiene palomitas. | What does she have? She has some popcorn. | ¿Qué tiene ella? Ella tiene palomitas. | Phrase
🥜 | What does she have? She has some peanuts. | ¿Qué tiene ella? Ella tiene cacahuates. | What does she have? She has some peanuts. | ¿Qué tiene ella? Ella tiene cacahuates. | Phrase
🥔 | What does she have? She has some potato chips. | ¿Qué tiene ella? Ella tiene papas fritas. | What does she have? She has some potato chips. | ¿Qué tiene ella? Ella tiene papas fritas. | Phrase
🥤 | What does she have? She has some soda. | ¿Qué tiene ella? Ella tiene refresco. | What does she have? She has some soda. | ¿Qué tiene ella? Ella tiene refresco. | Phrase
🍭 | What does she have? She has some candy. | ¿Qué tiene ella? Ella tiene dulces. | What does she have? She has some candy. | ¿Qué tiene ella? Ella tiene dulces. | Phrase
❓ | Do you have some string? | ¿Tienes algo de cuerda? | Tell me, do you have some string? | Dime, ¿tienes algo de cuerda? | Question
❓ | Do you have some ribbon? | ¿Tienes algo de cinta de tela / listón? | Tell me, do you have some ribbon? | Dime, ¿tienes algo de cinta de tela / listón? | Question
📎 | He has some paper clips. | Él tiene algunos clips de papel. | Look, he has some paper clips. | Mira, él tiene algunos clips de papel. | Phrase
📎 | She has a stapler. | Ella tiene una grapadora. | Look, she has a stapler. | Mira, ella tiene una grapadora. | Phrase
    """.trimIndent(), "Basics 3 - List 3")

    private fun getList4(): List<Flashcard> = parseCards("""
🥕 | Carrot | Zanahoria | Orange carrot | Zanahoria naranja | Word
🧅 | Onion | Cebolla | Cut an onion | Corta una cebolla | Word
🫑 | Pepper | Pimiento | Green pepper | Pimiento verde | Word
🥬 | Cabbage | Col / Repollo | Fresh cabbage | Col fresca | Word
🥔 | Potato | Papa | Hot potato | Papa caliente | Word
🍅 | Tomato | Tomate | Red tomato | Tomate rojo | Word
🥕 | We need some carrots. | Necesitamos zanahorias. | Yes, we need some carrots. | Sí, necesitamos zanahorias. | Phrase
🥕 | We don't need any carrots. | No necesitamos zanahorias. | No, we don't need any carrots. | No, no necesitamos zanahorias. | Phrase
❓ | Do we need any carrots? | ¿Necesitamos zanahorias? | Tell me, do we need any carrots? | Dime, ¿necesitamos zanahorias? | Question
✅ | Yes, we do. | Sí, necesitamos. | Yes, we do need carrots. | Sí, necesitamos zanahorias. | Phrase
❌ | No, we don't. | No, no necesitamos. | No, we don't need carrots. | No, no necesitamos zanahorias. | Phrase
🍳 | Omelet | Tortilla de huevo | Cook an omelet | Cocina una tortilla de huevo | Word
🥤 | Smoothie | Batido | Fruit smoothie | Batido de frutas | Word
🥗 | Fruit salad | Ensalada de frutas | Sweet fruit salad | Ensalada de frutas dulce | Word
🥤 | Milkshake | Malteada | Chocolate milkshake | Malteada de chocolate | Word
❓ | What's she making? | ¿Qué está haciendo ella? | Tell me, what's she making? | Dime, ¿qué está haciendo ella? | Question
🍳 | She's making an omelet. | Ella está haciendo una tortilla. | Look, she's making an omelet. | Mira, ella está haciendo una tortilla. | Phrase
❓ | What's he making? | ¿Qué está haciendo él? | Tell me, what's he making? | Dime, ¿qué está haciendo él? | Question
🍳 | He's making an omelet. | Él está haciendo una tortilla. | Look, he's making an omelet. | Mira, él está haciendo una tortilla. | Phrase
🧂 | Can you pass the salt? | ¿Puedes pasar la sal? | Please, can you pass the salt? | Por favor, ¿puedes pasar la sal? | Phrase
🤲 | Here you are. | Aquí tienes. | Yes, here you are. | Sí, aquí tienes. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
😇 | Be polite. | Sé educado. | Always be polite. | Siempre sé educado. | Phrase
✨ | Use good manners. | Usa buenos modales. | Please, use good manners. | Por favor, usa buenos modales. | Phrase
🥣 | Bowl | Tazón | A deep bowl | Un tazón hondo | Word
🍽️ | Plate | Plato | White plate | Plato blanco | Word
🥄 | Spoon | Cuchara | Silver spoon | Cuchara de plata | Word
🍴 | Fork | Tenedor | Metal fork | Tenedor de metal | Word
🔪 | Knife | Cuchillo | Sharp knife | Cuchillo afilado | Word
🧻 | Napkin | Servilleta | Clean napkin | Servilleta limpia | Word
🧅 | We need some onions. | Necesitamos algunas cebollas. | Yes, we need some onions. | Sí, necesitamos algunas cebollas. | Phrase
🫑 | We need some peppers. | Necesitamos algunos pimientos. | Yes, we need some peppers. | Sí, necesitamos algunos pimientos. | Phrase
🥬 | We need some cabbage. | Necesitamos algo de col. | Yes, we need some cabbage. | Sí, necesitamos algo de col. | Phrase
🥔 | We need some potatoes. | Necesitamos algunas papas. | Yes, we need some potatoes. | Sí, necesitamos algunas papas. | Phrase
🍅 | We need some tomatoes. | Necesitamos algunos tomates. | Yes, we need some tomatoes. | Sí, necesitamos algunos tomates. | Phrase
🧅 | We don't need any onions. | No necesitamos cebollas. | No, we don't need any onions. | No, no necesitamos cebollas. | Phrase
🫑 | We don't need any peppers. | No necesitamos pimientos. | No, we don't need any peppers. | No, no necesitamos pimientos. | Phrase
🥬 | We don't need any cabbage. | No necesitamos col. | No, we don't need any cabbage. | No, no necesitamos col. | Phrase
🥔 | We don't need any potatoes. | No necesitamos papas. | No, we don't need any potatoes. | No, no necesitamos papas. | Phrase
🍅 | We don't need any tomatoes. | No necesitamos tomates. | No, we don't need any tomatoes. | No, no necesitamos tomates. | Phrase
❓ | Do we need any onions? | ¿Necesitamos cebollas? | Tell me, do we need any onions? | Dime, ¿necesitamos cebollas? | Question
❓ | Do we need any peppers? | ¿Necesitamos pimientos? | Tell me, do we need any peppers? | Dime, ¿necesitamos pimientos? | Question
❓ | Do we need any cabbage? | ¿Necesitamos col? | Tell me, do we need any cabbage? | Dime, ¿necesitamos col? | Question
❓ | Do we need any potatoes? | ¿Necesitamos papas? | Tell me, do we need any potatoes? | Dime, ¿necesitamos papas? | Question
❓ | Do we need any tomatoes? | ¿Necesitamos tomates? | Tell me, do we need any tomatoes? | Dime, ¿necesitamos tomates? | Question
🥤 | What's she making? She's making a smoothie. | ¿Qué está haciendo ella? Ella está haciendo un batido. | What's she making? She's making a smoothie. | ¿Qué está haciendo ella? Ella está haciendo un batido. | Phrase
🥗 | What's she making? She's making a fruit salad. | ¿Qué está haciendo ella? Ella está haciendo ensalada de frutas. | What's she making? She's making a fruit salad. | ¿Qué está haciendo ella? Ella está haciendo ensalada de frutas. | Phrase
🥤 | What's she making? She's making a milkshake. | ¿Qué está haciendo ella? Ella está haciendo una malteada. | What's she making? She's making a milkshake. | ¿Qué está haciendo ella? Ella está haciendo una malteada. | Phrase
🥤 | What's he making? He's making a smoothie. | ¿Qué está haciendo él? Él está haciendo un batido. | What's he making? He's making a smoothie. | ¿Qué está haciendo él? Él está haciendo un batido. | Phrase
🥗 | What's he making? He's making a fruit salad. | ¿Qué está haciendo él? Él está haciendo ensalada de frutas. | What's he making? He's making a fruit salad. | ¿Qué está haciendo él? Él está haciendo ensalada de frutas. | Phrase
🥤 | What's he making? He's making a milkshake. | ¿Qué está haciendo él? Él está haciendo una malteada. | What's he making? He's making a milkshake. | ¿Qué está haciendo él? Él está haciendo una malteada. | Phrase
❓ | What are you making? | ¿Qué estás haciendo? | Tell me, what are you making? | Dime, ¿qué estás haciendo? | Question
🍳 | I'm making an omelet. | Estoy haciendo una tortilla. | Look, I'm making an omelet. | Mira, estoy haciendo una tortilla. | Phrase
🥤 | I'm making a smoothie. | Estoy haciendo un batido. | Look, I'm making a smoothie. | Mira, estoy haciendo un batido. | Phrase
🥗 | I'm making a fruit salad. | Estoy haciendo ensalada de frutas. | Look, I'm making a fruit salad. | Mira, estoy haciendo ensalada de frutas. | Phrase
🥤 | I'm making a milkshake. | Estoy haciendo una malteada. | Look, I'm making a milkshake. | Mira, estoy haciendo una malteada. | Phrase
❓ | What are they making? | ¿Qué están haciendo ellos? | Tell me, what are they making? | Dime, ¿qué están haciendo ellos? | Question
🍳 | They're making an omelet. | Ellos están haciendo una tortilla. | Look, they're making an omelet. | Mira, ellos están haciendo una tortilla. | Phrase
🥤 | They're making a smoothie. | Ellos están haciendo un batido. | Look, they're making a smoothie. | Mira, ellos están haciendo un batido. | Phrase
🥗 | They're making fruit salad. | Ellos están haciendo ensalada de frutas. | Look, they're making fruit salad. | Mira, ellos están haciendo ensalada de frutas. | Phrase
🥤 | They're making a milkshake. | Ellos están haciendo una malteada. | Look, they're making a milkshake. | Mira, ellos están haciendo una malteada. | Phrase
🧂 | Pass the pepper, please. | Pasa la pimienta, por favor. | Please, pass the pepper. | Por favor, pasa la pimienta. | Phrase
🧻 | Pass the napkin, please. | Pasa la servilleta, por favor. | Please, pass the napkin. | Por favor, pasa la servilleta. | Phrase
🥣 | Here's the bowl. | Aquí está el tazón. | Look, here's the bowl. | Mira, aquí está el tazón. | Phrase
🍽️ | Here's the plate. | Aquí está el plato. | Look, here's the plate. | Mira, aquí está el plato. | Phrase
🥄 | Here's the spoon. | Aquí está la cuchara. | Look, here's the spoon. | Mira, aquí está la cuchara. | Phrase
🍴 | Here's the fork. | Aquí está el tenedor. | Look, here's the fork. | Mira, aquí está el tenedor. | Phrase
🔪 | Here's the knife. | Aquí está el cuchillo. | Look, here's the knife. | Mira, aquí está el cuchillo. | Phrase
🧻 | I need a napkin. | Necesito una servilleta. | Please, I need a napkin. | Por favor, necesito una servilleta. | Phrase
🥣 | He needs a bowl. | Él necesita un tazón. | Look, he needs a bowl. | Mira, él necesita un tazón. | Phrase
🍽️ | She needs a plate. | Ella necesita un plato. | Look, she needs a plate. | Mira, ella necesita un plato. | Phrase
🥄 | We need spoons. | Necesitamos cucharas. | Look, we need spoons. | Mira, necesitamos cucharas. | Phrase
🍴 | They need forks. | Ellos necesitan tenedores. | Look, they need forks. | Mira, ellos necesitan tenedores. | Phrase
    """.trimIndent(), "Basics 3 - List 4")

    private fun getList5(): List<Flashcard> = parseCards("""
🏞️ | Park | Parque | Play in the park | Juega en el parque | Word
🎬 | Movie theater | Cine | Watch films at the movie theater | Mira películas en el cine | Word
🛒 | Supermarket | Supermercado | Buy food at the supermarket | Compra comida en el supermercado | Word
📮 | Post office | Oficina de correos | Send mail at the post office | Envía correo en la oficina | Word
🏬 | Department store | Tienda departamental | Big department store | Gran tienda departamental | Word
📚 | Library | Biblioteca | Read books at the library | Lee libros en la biblioteca | Word
❓ | Where is he going? | ¿A dónde va él? | Tell me, where is he going? | Dime, ¿a dónde va él? | Question
🏞️ | He's going to the park. | Él va al parque. | Look, he's going to the park. | Mira, él va al parque. | Phrase
❓ | Where is she going? | ¿A dónde va ella? | Tell me, where is she going? | Dime, ¿a dónde va ella? | Question
🏞️ | She's going to the park. | Ella va al parque. | Look, she's going to the park. | Mira, ella va al parque. | Phrase
🚗 | By car | En carro | Travel by car | Viaja en carro | Phrase
🚌 | By bus | En autobús | Go by bus | Ve en autobús | Phrase
🚲 | By bike | En bicicleta | Ride by bike | Ve en bicicleta | Phrase
🚶 | On foot | A pie | Walk on foot | Camina a pie | Phrase
❓ | How is he going there? | ¿Cómo va él allí? | Tell me, how is he going there? | Dime, ¿cómo va él allí? | Question
🚗 | He's going by car. | Él va en carro. | Look, he's going by car. | Mira, él va en carro. | Phrase
❓ | How is she going there? | ¿Cómo va ella allí? | Tell me, how is she going there? | Dime, ¿cómo va ella allí? | Question
🚗 | She's going by car. | Ella va en carro. | Look, she's going by car. | Mira, ella va en carro. | Phrase
❓ | Excuse me. Where is the library? | Disculpe. ¿Dónde está la biblioteca? | Excuse me. Where is the library? | Disculpe. ¿Dónde está la biblioteca? | Question
👉 | It's over there. | Está por allí. | Look, it's over there. | Mira, está por allí. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
🛡️ | Be safe. | Cuídate. | Please, be safe. | Por favor, cuídate. | Phrase
👀 | Look both ways. | Mira hacia ambos lados. | Always look both ways. | Siempre mira a ambos lados. | Phrase
🥖 | Bakery | Panadería | Bread at the bakery | Pan en la panadería | Word
🏥 | Hospital | Hospital | Doctors at the hospital | Doctores en el hospital | Word
🏦 | Bank | Banco | Money in the bank | Dinero en el banco | Word
⛽ | Gas station | Gasolinera | Fuel at the gas station | Gasolina en la estación | Word
🎬 | Where is he going? He's going to the movie theater. | ¿A dónde va él? Él va al cine. | Where is he going? He's going to the movie theater. | ¿A dónde va él? Él va al cine. | Phrase
🛒 | Where is he going? He's going to the supermarket. | ¿A dónde va él? Él va al supermercado. | Where is he going? He's going to the supermarket. | ¿A dónde va él? Él va al supermercado. | Phrase
📮 | Where is he going? He's going to the post office. | ¿A dónde va él? Él va a la oficina de correos. | Where is he going? He's going to the post office. | ¿A dónde va él? Él va a la oficina de correos. | Phrase
🏬 | Where is he going? He's going to the department store. | ¿A dónde va él? Él va a la tienda departamental. | Where is he going? He's going to the department store. | ¿A dónde va él? Él va a la tienda departamental. | Phrase
📚 | Where is he going? He's going to the library. | ¿A dónde va él? Él va a la biblioteca. | Where is he going? He's going to the library. | ¿A dónde va él? Él va a la biblioteca. | Phrase
🎬 | Where is she going? She's going to the movie theater. | ¿A dónde va ella? Ella va al cine. | Where is she going? She's going to the movie theater. | ¿A dónde va ella? Ella va al cine. | Phrase
🛒 | Where is she going? She's going to the supermarket. | ¿A dónde va ella? Ella va al supermercado. | Where is she going? She's going to the supermarket. | ¿A dónde va ella? Ella va al supermercado. | Phrase
📮 | Where is she going? She's going to the post office. | ¿A dónde va ella? Ella va a la oficina de correos. | Where is she going? She's going to the post office. | ¿A dónde va ella? Ella va a la oficina de correos. | Phrase
🏬 | Where is she going? She's going to the department store. | ¿A dónde va ella? Ella va a la tienda departamental. | Where is she going? She's going to the department store. | ¿A dónde va ella? Ella va a la tienda departamental. | Phrase
📚 | Where is she going? She's going to the library. | ¿A dónde va ella? Ella va a la biblioteca. | Where is she going? She's going to the library. | ¿A dónde va ella? Ella va a la biblioteca. | Phrase
🚌 | How is he going there? He's going by bus. | ¿Cómo va él allí? Él va en autobús. | How is he going there? He's going by bus. | ¿Cómo va él allí? Él va en autobús. | Phrase
🚲 | How is he going there? He's going by bike. | ¿Cómo va él allí? Él va en bicicleta. | How is he going there? He's going by bike. | ¿Cómo va él allí? Él va en bicicleta. | Phrase
🚶 | How is he going there? He's going on foot. | ¿Cómo va él allí? Él va a pie. | How is he going there? He's going on foot. | ¿Cómo va él allí? Él va a pie. | Phrase
🚌 | How is she going there? She's going by bus. | ¿Cómo va ella allí? Ella va en autobús. | How is she going there? She's going by bus. | ¿Cómo va ella allí? Ella va en autobús. | Phrase
🚲 | How is she going there? She's going by bike. | ¿Cómo va ella allí? Ella va en bicicleta. | How is she going there? She's going by bike. | ¿Cómo va ella allí? Ella va en bicicleta. | Phrase
🚶 | How is she going there? She's going on foot. | ¿Cómo va ella allí? Ella va a pie. | How is she going there? She's going on foot. | ¿Cómo va ella allí? Ella va a pie. | Phrase
❓ | Where are you going? | ¿A dónde vas? | Tell me, where are you going? | Dime, ¿a dónde vas? | Question
🏞️ | I'm going to the park. | Voy al parque. | Look, I'm going to the park. | Mira, voy al parque. | Phrase
📚 | I'm going to the library. | Voy a la biblioteca. | Look, I'm going to the library. | Mira, voy a la biblioteca. | Phrase
🏥 | I'm going to the hospital. | Voy al hospital. | Look, I'm going to the hospital. | Mira, voy al hospital. | Phrase
❓ | How are you going there? | ¿Cómo vas allí? | Tell me, how are you going there? | Dime, ¿cómo vas allí? | Question
🚌 | I'm going by bus. | Voy en autobús. | Look, I'm going by bus. | Mira, voy en autobús. | Phrase
🚗 | I'm going by car. | Voy en carro. | Look, I'm going by car. | Mira, voy en carro. | Phrase
🚲 | I'm going by bike. | Voy en bicicleta. | Look, I'm going by bike. | Mira, voy en bicicleta. | Phrase
🚶 | I'm going on foot. | Voy a pie. | Look, I'm going on foot. | Mira, voy a pie. | Phrase
❓ | Where are they going? | ¿A dónde van ellos? | Tell me, where are they going? | Dime, ¿a dónde van ellos? | Question
🥖 | They're going to the bakery. | Ellos van a la panadería. | Look, they're going to the bakery. | Mira, ellos van a la panadería. | Phrase
🏦 | They're going to the bank. | Ellos van al banco. | Look, they're going to the bank. | Mira, ellos van al banco. | Phrase
⛽ | They're going to the gas station. | Ellos van a la gasolinera. | Look, they're going to the gas station. | Mira, ellos van a la gasolinera. | Phrase
❓ | How are they going there? | ¿Cómo van ellos allí? | Tell me, how are they going there? | Dime, ¿cómo van ellos allí? | Question
🚗 | They're going by car. | Ellos van en carro. | Look, they're going by car. | Mira, ellos van en carro. | Phrase
🚆 | They're going by train. | Ellos van en tren. | Look, they're going by train. | Mira, ellos van en tren. | Phrase
🚌 | They're going by bus. | Ellos van en autobús. | Look, they're going by bus. | Mira, ellos van en autobús. | Phrase
🏦 | It's next to the bank. | Está al lado del banco. | Look, it's next to the bank. | Mira, está al lado del banco. | Phrase
🏞️ | It's across from the park. | Está frente al parque. | Look, it's across from the park. | Mira, está frente al parque. | Phrase
🏬 | It's between the store and the bank. | Está entre la tienda y el banco. | Look, it's between the store and the bank. | Mira, está entre la tienda y el banco. | Phrase
⬅️ | Turn left. | Gira a la izquierda. | Please, turn left here. | Por favor, gira a la izquierda aquí. | Phrase
➡️ | Turn right. | Gira a la derecha. | Please, turn right here. | Por favor, gira a la derecha aquí. | Phrase
⬆️ | Go straight. | Sigue derecho. | Please, go straight ahead. | Por favor, sigue todo recto. | Phrase
    """.trimIndent(), "Basics 3 - List 5")

    private fun getList6(): List<Flashcard> = parseCards("""
🛒 | Cashier | Cajero | Pay the cashier | Paga al cajero | Word
📚 | Librarian | Bibliotecario | Ask the librarian | Pregunta al bibliotecario | Word
📮 | Postal worker | Cartero | The postal worker delivers | El cartero reparte | Word
🍽️ | Server | Mesero / Servidor | The server brings food | El mesero trae comida | Word
🐾 | Vet | Veterinario | The vet helps my dog | El veterinario ayuda a mi perro | Word
🗺️ | Tour guide | Guía turístico | Follow the tour guide | Sigue al guía turístico | Word
❓ | What does a cashier do? | ¿Qué hace un cajero? | Tell me, what does a cashier do? | Dime, ¿qué hace un cajero? | Question
🛒 | A cashier works in a store. | Un cajero trabaja en una tienda. | A cashier works in a store. | Un cajero trabaja en una tienda. | Phrase
❓ | What does a librarian do? | ¿Qué hace un bibliotecario? | Tell me, what does a librarian do? | Dime, ¿qué hace un bibliotecario? | Question
📚 | A librarian works in a library. | Un bibliotecario trabaja en una biblioteca. | A librarian works in a library. | Un bibliotecario trabaja en una biblioteca. | Phrase
🤧 | Cold | Resfriado | I have a cold | Tengo un resfriado | Word
🌡️ | Fever | Fiebre | High fever | Fiebre alta | Word
🤢 | Stomachache | Dolor de estómago | Bad stomachache | Fuerte dolor de estómago | Word
🤕 | Headache | Dolor de cabeza | I have a headache | Tengo dolor de cabeza | Word
🦷 | Toothache | Dolor de muelas | Sore toothache | Fuerte dolor de muelas | Word
😷 | Cough | Tos | Dry cough | Tos seca | Word
❓ | What's the matter? | ¿Qué pasa? / ¿Qué tienes? | Tell me, what's the matter? | Dime, ¿qué te pasa? | Question
🤧 | I have a cold. | Tengo un resfriado. | Oh, I have a cold. | Oh, tengo un resfriado. | Phrase
❓ | What's the matter with him? | ¿Qué le pasa a él? | Tell me, what's the matter with him? | Dime, ¿qué le pasa a él? | Question
🌡️ | He has a fever. | Él tiene fiebre. | Look, he has a fever. | Mira, él tiene fiebre. | Phrase
❓ | What's the matter with her? | ¿Qué le pasa a ella? | Tell me, what's the matter with her? | Dime, ¿qué le pasa a ella? | Question
🤕 | She has a headache. | Ella tiene dolor de cabeza. | Look, she has a headache. | Mira, ella tiene dolor de cabeza. | Phrase
😟 | That's too bad. | Qué lástima. | Oh, that's too bad. | Oh, qué lástima. | Phrase
💖 | I hope you feel better. | Espero que te sientas mejor. | I hope you feel better soon. | Espero que te sientas mejor pronto. | Phrase
🙏 | Thanks. | Gracias. | Thanks a lot. | Muchas gracias. | Phrase
💐 | Get well soon. | Que te mejores pronto. | Please, get well soon. | Por favor, mejórate pronto. | Phrase
🩺 | Take care. | Cuídate. | Please, take care. | Por favor, cuídate. | Phrase
💧 | Rest and drink water. | Descansa y bebe agua. | Please, rest and drink water. | Por favor, descansa y bebe agua. | Phrase
👨‍⚕️ | Doctor | Doctor | See a doctor | Ve a un doctor | Word
👩‍⚕️ | Nurse | Enfermera | Kind nurse | Enfermera amable | Word
🦷 | Dentist | Dentista | Visit the dentist | Visita al dentista | Word
👨‍✈️ | Pilot | Piloto | The pilot flies planes | El piloto vuela aviones | Word
📮 | A postal worker delivers mail. | Un cartero entrega correo. | A postal worker delivers mail. | Un cartero reparte el correo. | Phrase
🍽️ | A server serves food. | Un mesero sirve comida. | A server serves food nicely. | Un mesero sirve la comida amablemente. | Phrase
🐾 | A vet helps animals. | Un veterinario ayuda a los animales. | A vet helps sick animals. | Un veterinario ayuda a animales enfermos. | Phrase
🗺️ | A tour guide shows places. | Un guía muestra lugares. | A tour guide shows historic places. | Un guía muestra lugares históricos. | Phrase
🤧 | What's the matter with him? He has a cold. | ¿Qué le pasa a él? Él tiene un resfriado. | What's the matter with him? He has a cold. | ¿Qué le pasa a él? Él tiene un resfriado. | Phrase
🤢 | What's the matter with him? He has a stomachache. | ¿Qué le pasa a él? Él tiene dolor de estómago. | What's the matter with him? He has a stomachache. | ¿Qué le pasa a él? Él tiene dolor de estómago. | Phrase
🤕 | What's the matter with him? He has a headache. | ¿Qué le pasa a él? Él tiene dolor de cabeza. | What's the matter with him? He has a headache. | ¿Qué le pasa a él? Él tiene dolor de cabeza. | Phrase
🦷 | What's the matter with him? He has a toothache. | ¿Qué le pasa a él? Él tiene dolor de muelas. | What's the matter with him? He has a toothache. | ¿Qué le pasa a él? Él tiene dolor de muelas. | Phrase
😷 | What's the matter with him? He has a cough. | ¿Qué le pasa a él? Él tiene tos. | What's the matter with him? He has a cough. | ¿Qué le pasa a él? Él tiene tos. | Phrase
🤧 | What's the matter with her? She has a cold. | ¿Qué le pasa a ella? Ella tiene un resfriado. | What's the matter with her? She has a cold. | ¿Qué le pasa a ella? Ella tiene un resfriado. | Phrase
🌡️ | What's the matter with her? She has a fever. | ¿Qué le pasa a ella? Ella tiene fiebre. | What's the matter with her? She has a fever. | ¿Qué le pasa a ella? Ella tiene fiebre. | Phrase
🤢 | What's the matter with her? She has a stomachache. | ¿Qué le pasa a ella? Ella tiene dolor de estómago. | What's the matter with her? She has a stomachache. | ¿Qué le pasa a ella? Ella tiene dolor de estómago. | Phrase
🦷 | What's the matter with her? She has a toothache. | ¿Qué le pasa a ella? Ella tiene dolor de muelas. | What's the matter with her? She has a toothache. | ¿Qué le pasa a ella? Ella tiene dolor de muelas. | Phrase
😷 | What's the matter with her? She has a cough. | ¿Qué le pasa a ella? Ella tiene tos. | What's the matter with her? She has a cough. | ¿Qué le pasa a ella? Ella tiene tos. | Phrase
🌡️ | I have a fever. | Tengo fiebre. | Oh, I have a fever. | Oh, tengo fiebre. | Phrase
🤢 | I have a stomachache. | Tengo dolor de estómago. | Oh, I have a stomachache. | Oh, tengo dolor de estómago. | Phrase
🤕 | I have a headache. | Tengo dolor de cabeza. | Oh, I have a headache. | Oh, tengo dolor de cabeza. | Phrase
🦷 | I have a toothache. | Tengo dolor de muelas. | Oh, I have a toothache. | Oh, tengo dolor de muelas. | Phrase
😷 | I have a cough. | Tengo tos. | Oh, I have a cough. | Oh, tengo tos. | Phrase
👨‍⚕️ | You should see a doctor. | Deberías ver a un médico. | Please, you should see a doctor. | Por favor, deberías ver a un médico. | Phrase
🦷 | You should see a dentist. | Deberías ver a un dentista. | Please, you should see a dentist. | Por favor, deberías ver a un dentista. | Phrase
🏥 | Go to the hospital. | Ve al hospital. | Please, go to the hospital. | Por favor, ve al hospital. | Phrase
🛏️ | Stay in bed. | Quédate en la cama. | Please, stay in bed today. | Por favor, quédate en cama hoy. | Phrase
💊 | Take some medicine. | Toma algo de medicina. | Please, take some medicine. | Por favor, toma algo de medicina. | Phrase
🍵 | Drink warm water. | Bebe agua tibia. | Please, drink warm water. | Por favor, bebe agua tibia. | Phrase
🧼 | Wash your hands. | Lava tus manos. | Always wash your hands. | Siempre lava tus manos. | Phrase
😷 | Wear a mask. | Usa una mascarilla. | Please, wear a mask. | Por favor, usa una mascarilla. | Phrase
🥗 | Eat healthy food. | Come comida saludable. | Always eat healthy food. | Siempre come comida saludable. | Phrase
    """.trimIndent(), "Basics 3 - List 6")

    private fun getList7(): List<Flashcard> = parseCards("""
👨‍👩‍👧 | Parents | Padres | I love my parents | Amo a mis padres | Word
👵👴 | Grandparents | Abuelos | Visit grandparents | Visita a los abuelos | Word
👩 | Aunt | Tía | My aunt is nice | Mi tía es amable | Word
👨 | Uncle | Tío | My uncle is tall | Mi tío es alto | Word
👦 | Cousin | Primo / Prima | Play with my cousin | Juega con mi primo | Word
👶 | Baby | Bebé | The baby is cute | El bebé es lindo | Word
🙋‍♂️ | He | Él (se refiere a él - pronombre masculino) | He is my uncle | Él es mi tío | Word
🙋‍♀️ | She | Ella (se refiere a ella - pronombre femenino) | She is my aunt | Ella es mi tía | Word
🎒 | His | Su / Sus (se refiere a él / de él) | It is his cup | Es su taza (de él) | Word
👜 | Her | Su / Sus (se refiere a ella / de ella) | It is her glass | Es su vaso (de ella) | Word
❓ | Who is that? | ¿Quién es ese? | Tell me, who is that? | Dime, ¿quién es ese? | Question
👩 | That's my aunt. | Esa es mi tía. | Look, that's my aunt. | Mira, esa es mi tía. | Phrase
❓ | Who are they? | ¿Quiénes son ellos? | Tell me, who are they? | Dime, ¿quiénes son ellos? | Question
👨‍👩‍👧 | They're my parents. | Son mis padres. | Look, they're my parents. | Mira, son mis padres. | Phrase
☕ | Cup | Taza | A cup of tea | Una taza de té | Word
🥛 | Glass | Vaso | A glass of water | Un vaso de agua | Word
🥣 | Bowl | Tazón | A bowl of soup | Un tazón de sopa | Word
🍽️ | Plate | Plato | Clean plate | Plato limpio | Word
🍴 | Fork | Tenedor | Eat with a fork | Come con un tenedor | Word
🔪 | Knife | Cuchillo | Cut with a knife | Corta con un cuchillo | Word
🥄 | Spoon | Cuchara | Stir with a spoon | Revuelve con una cuchara | Word
🧻 | Napkin | Servilleta | Use a napkin | Usa una servilleta | Word
❓ | Whose glass is this? | ¿De quién es este vaso? | Tell me, whose glass is this? | Dime, ¿de quién es este vaso? | Question
🥛 | It's my glass. | Es mi vaso. | Look, it's my glass. | Mira, es mi vaso. | Phrase
❓ | Whose glasses are these? | ¿De quién son estos vasos? | Tell me, whose glasses are these? | Dime, ¿de quién son estos vasos? | Question
🥛 | They're my glasses. | Son mis vasos. | Look, they're my glasses. | Mira, son mis vasos. | Phrase
🏠 | Welcome to our house. | Bienvenidos a nuestra casa. | Welcome to our house, come in. | Bienvenidos a nuestra casa, pasen. | Phrase
🛋️ | Make yourself at home. | Siéntete como en casa. | Please, make yourself at home. | Por favor, siéntete como en casa. | Phrase
🙏 | Thank you for inviting me. | Gracias por invitarme. | Thank you for inviting me today. | Gracias por invitarme hoy. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
🤝 | Be friendly. | Sé amigable. | Always be friendly. | Siempre sé amigable. | Phrase
💖 | Treat guests well. | Trata bien a los invitados. | Always treat guests well. | Siempre trata bien a los invitados. | Phrase
🦒 | Tall | Alto | He is tall | Él es alto | Word
🐜 | Short | Bajo / Corto | She is short | Ella es baja | Word
👴 | Old | Viejo / Anciano | My grandfather is old | Mi abuelo es anciano | Word
🧒 | Young | Joven | The children are young | Los niños son jóvenes | Word
💪 | Strong | Fuerte | The man is strong | El hombre es fuerte | Word
🍂 | Weak | Débil | Feeling weak | Sintiéndose débil | Word
👨 | That's my uncle. | Ese es mi tío. | Look, that's my uncle. | Mira, ese es mi tío. | Phrase
👦 | That's my cousin. | Ese es mi primo. | Look, that's my cousin. | Mira, ese es mi primo. | Phrase
👶 | That's the baby. | Ese es el bebé. | Look, that's the baby. | Mira, ese es el bebé. | Phrase
👵👴 | They're my grandparents. | Son mis abuelos. | Look, they're my grandparents. | Mira, son mis abuelos. | Phrase
❓ | Who is he? | ¿Quién es él? | Tell me, who is he? | Dime, ¿quién es él? | Question
👨 | He's my uncle. | Él es mi tío. | Look, he's my uncle. | Mira, él es mi tío. | Phrase
❓ | Who is she? | ¿Quién es ella? | Tell me, who is she? | Dime, ¿quién es ella? | Question
👩 | She's my aunt. | Ella es mi tía. | Look, she's my aunt. | Mira, ella es mi tía. | Phrase
❓ | Who are they? They're my cousins. | ¿Quiénes son ellos? Son mis primos. | Who are they? They're my cousins. | ¿Quiénes son ellos? Son mis primos. | Phrase
❓ | Whose cup is this? | ¿De quién es esta taza? | Tell me, whose cup is this? | Dime, ¿de quién es esta taza? | Question
❓ | Whose bowl is this? | ¿De quién es este tazón? | Tell me, whose bowl is this? | Dime, ¿de quién es este tazón? | Question
❓ | Whose plate is this? | ¿De quién es este plato? | Tell me, whose plate is this? | Dime, ¿de quién es este plato? | Question
❓ | Whose fork is this? | ¿De quién es este tenedor? | Tell me, whose fork is this? | Dime, ¿de quién es este tenedor? | Question
❓ | Whose knife is this? | ¿De quién es este cuchillo? | Tell me, whose knife is this? | Dime, ¿de quién es este cuchillo? | Question
❓ | Whose spoon is this? | ¿De quién es esta cuchara? | Tell me, whose spoon is this? | Dime, ¿de quién es esta cuchara? | Question
❓ | Whose napkin is this? | ¿De quién es esta servilleta? | Tell me, whose napkin is this? | Dime, ¿de quién es esta servilleta? | Question
☕ | It's his cup. | Es su taza (de él - se refiere a él). | Look, it's his cup. | Mira, es su taza (de él). | Phrase
🥛 | It's her glass. | Es su vaso (de ella - se refiere a ella). | Look, it's her glass. | Mira, es su vaso (de ella). | Phrase
🥣 | It's his bowl. | Es su tazón (de él - se refiere a él). | Look, it's his bowl. | Mira, es su tazón (de él). | Phrase
🍽️ | It's her plate. | Es su plato (de ella - se refiere a ella). | Look, it's her plate. | Mira, es su plato (de ella). | Phrase
🍴 | It's his fork. | Es su tenedor (de él - se refiere a él). | Look, it's his fork. | Mira, es su tenedor (de él). | Phrase
🔪 | It's her knife. | Es su cuchillo (de ella - se refiere a ella). | Look, it's her knife. | Mira, es su cuchillo (de ella). | Phrase
🥄 | It's his spoon. | Es su cuchara (de él - se refiere a él). | Look, it's his spoon. | Mira, es su cuchara (de él). | Phrase
🧻 | It's her napkin. | Es su servilleta (de ella - se refiere a ella). | Look, it's her napkin. | Mira, es su servilleta (de ella). | Phrase
🍴 | They're his forks. | Son sus tenedores (de él - se refiere a él). | Look, they're his forks. | Mira, son sus tenedores (de él). | Phrase
🔪 | They're her knives. | Son sus cuchillos (de ella - se refiere a ella). | Look, they're her knives. | Mira, son sus cuchillos (de ella). | Phrase
🥄 | They're his spoons. | Son sus cucharas (de él - se refiere a él). | Look, they're his spoons. | Mira, son sus cucharas (de él). | Phrase
🧻 | They're her napkins. | Son sus servilletas (de ella - se refiere a ella). | Look, they're her napkins. | Mira, son sus servilletas (de ella). | Phrase
🦒 | He is tall. | Él es alto. | Look, he is tall. | Mira, él es alto. | Phrase
🐜 | He is short. | Él es bajo. | Look, he is short. | Mira, él es bajo. | Phrase
🧒 | She is young. | Ella es joven. | Look, she is young. | Mira, ella es joven. | Phrase
👵 | She is old. | Ella es anciana. | Look, she is old. | Mira, ella es anciana. | Phrase
💪 | He is strong. | Él es fuerte. | Look, he is strong. | Mira, él es fuerte. | Phrase
🍂 | He is weak. | Él es débil. | Look, he is weak. | Mira, él es débil. | Phrase
❓ | Is he strong? | ¿Es él fuerte? | Tell me, is he strong? | Dime, ¿es él fuerte? | Question
✅ | Yes, he is. | Sí, él es. | Yes, he is strong. | Sí, él es fuerte. | Phrase
❌ | No, he isn't. | No, él no es. | No, he isn't strong. | No, él no es fuerte. | Phrase
❓ | Is she tall? | ¿Es ella alta? | Tell me, is she tall? | Dime, ¿es ella alta? | Question
✅ | Yes, she is. | Sí, ella es. | Yes, she is tall. | Sí, ella es alta. | Phrase
❌ | No, she isn't. | No, ella no es. | No, she isn't tall. | No, ella no es alta. | Phrase
❓ | Are they old? | ¿Son ellos ancianos? | Tell me, are they old? | Dime, ¿son ellos ancianos? | Question
✅ | Yes, they are. | Sí, ellos son. | Yes, they are old. | Sí, ellos son ancianos. | Phrase
❌ | No, they aren't. | No, ellos no son. | No, they aren't old. | No, ellos no son ancianos. | Phrase
👨‍👩‍👧 | My family is big. | Mi familia es grande. | Look, my family is big. | Mira, mi familia es grande. | Phrase
💖 | My family is nice. | Mi familia es amable. | Look, my family is nice. | Mira, mi familia es amable. | Phrase
❤️ | I love my family. | Amo a mi familia. | Yes, I love my family. | Sí, amo a mi familia. | Phrase
    """.trimIndent(), "Basics 3 - List 7")

    private fun getList8(): List<Flashcard> = parseCards("""
🛏️ | Make my bed | Hacer mi cama | I make my bed every day | Hago mi cama todos los días | Phrase
🧹 | Clean my room | Limpiar mi habitación | Clean my room on Saturday | Limpio mi habitación el sábado | Phrase
🧺 | Do laundry | Lavar la ropa | Time to do laundry | Hora de lavar la ropa | Phrase
🍽️ | Set the table | Poner la mesa | Help set the table | Ayuda a poner la mesa | Phrase
🧼 | Wash the dishes | Lavar los platos | Wash the dishes after dinner | Lava los platos después de cenar | Phrase
🗑️ | Take out the trash | Sacar la basura | Take out the trash tonight | Saca la basura esta noche | Phrase
❓ | What do you have to do? | ¿Qué tienes que hacer? | Tell me, what do you have to do? | Dime, ¿qué tienes que hacer? | Question
🛏️ | I have to make my bed. | Tengo que hacer mi cama. | In the morning, I have to make my bed. | Por la mañana, tengo que hacer mi cama. | Phrase
❓ | What does he have to do? | ¿Qué tiene que hacer él? | Tell me, what does he have to do? | Dime, ¿qué tiene que hacer él? | Question
🛏️ | He has to make his bed. | Él tiene que hacer su cama (de él - se refiere a él). | In the morning, he has to make his bed. | Por la mañana, él tiene que hacer su cama (de él). | Phrase
❓ | What does she have to do? | ¿Qué tiene que hacer ella? | Tell me, what does she have to do? | Dime, ¿qué tiene que hacer ella? | Question
🛏️ | She has to make her bed. | Ella tiene que hacer su cama (de ella - se refiere a ella). | In the morning, she has to make her bed. | Por la mañana, ella tiene que hacer su cama (de ella). | Phrase
🏖️ | Beach | Playa | Swim at the beach | Nada en la playa | Word
🐠 | Aquarium | Acuario | Fishes at the aquarium | Peces en el acuario | Word
🎡 | Amusement park | Parque de atracciones | Rides at the amusement park | Juegos en el parque | Word
🏛️ | Museum | Museo | History at the museum | Historia en el museo | Word
🦁 | Zoo | Zoológico | Animals at the zoo | Animales en el zoológico | Word
🌊 | Water park | Parque acuático | Slides at the water park | Toboganes en el parque acuático | Word
❓ | Where did you go yesterday? | ¿A dónde fuiste ayer? | Tell me, where did you go yesterday? | Dime, ¿a dónde fuiste ayer? | Question
🏖️ | I went to the beach. | Fui a la playa. | Yesterday, I went to the beach. | Ayer, fui a la playa. | Phrase
❓ | Where did he go yesterday? | ¿A dónde fue él ayer? | Tell me, where did he go yesterday? | Dime, ¿a dónde fue él ayer? | Question
🏖️ | He went to the beach. | Él fue a la playa. | Yesterday, he went to the beach. | Ayer, él fue a la playa. | Phrase
❓ | Where did she go yesterday? | ¿A dónde fue ella ayer? | Tell me, where did she go yesterday? | Dime, ¿a dónde fue ella ayer? | Question
🏖️ | She went to the beach. | Ella fue a la playa. | Yesterday, she went to the beach. | Ayer, ella fue a la playa. | Phrase
❓ | Did you have fun? | ¿Te divertiste? | Tell me, did you have fun? | Dime, ¿te divertiste? | Question
🎉 | Yes, I did! | ¡Sí, me divertí! | Yes, I did have fun! | ¡Sí, me divertí mucho! | Phrase
🌟 | It was great! | ¡Fue genial! | Wow, it was great! | ¡Vaya, fue genial! | Phrase
😊 | I'm glad you had fun. | Me alegro de que te divirtieras. | I'm glad you had fun yesterday. | Me alegro de que te divirtieras ayer. | Phrase
🤝 | Be responsible. | Sé responsable. | Always be responsible. | Siempre sé responsable. | Phrase
🏠 | Help at home. | Ayuda en casa. | Always help at home. | Siempre ayuda en casa. | Phrase
☀️ | Sunny | Soleado | Sunny day | Día soleado | Word
🌧️ | Rainy | Lluvioso | Rainy day | Día lluvioso | Word
☁️ | Cloudy | Nublado | Cloudy sky | Cielo nublado | Word
💨 | Windy | Ventoso | Windy weather | Clima ventoso | Word
❄️ | Snowy | Nevado | Snowy mountain | Montaña nevada | Word
🔥 | Hot | Caluroso | Hot afternoon | Tarde calurosa | Word
🥶 | Cold | Frío | Cold morning | Mañana fría | Word
🧹 | I have to clean my room. | Tengo que limpiar mi habitación. | Today, I have to clean my room. | Hoy, tengo que limpiar mi habitación. | Phrase
🧺 | I have to do laundry. | Tengo que lavar la ropa. | Today, I have to do laundry. | Hoy, tengo que lavar la ropa. | Phrase
🍽️ | I have to set the table. | Tengo que poner la mesa. | Before eating, I have to set the table. | Antes de comer, tengo que poner la mesa. | Phrase
🧼 | I have to wash the dishes. | Tengo que lavar los platos. | After eating, I have to wash the dishes. | Después de comer, tengo que lavar los platos. | Phrase
🗑️ | I have to take out the trash. | Tengo que sacar la basura. | At night, I have to take out the trash. | Por la noche, tengo que sacar la basura. | Phrase
🧹 | He has to clean his room. | Él tiene que limpiar su habitación (de él - se refiere a él). | Today, he has to clean his room. | Hoy, él tiene que limpiar su habitación (de él). | Phrase
🧺 | He has to do laundry. | Él tiene que lavar la ropa. | Today, he has to do laundry. | Hoy, él tiene que lavar la ropa. | Phrase
🍽️ | He has to set the table. | Él tiene que poner la mesa. | He has to set the table. | Él tiene que poner la mesa. | Phrase
🧼 | He has to wash the dishes. | Él tiene que lavar los platos. | He has to wash the dishes. | Él tiene que lavar los platos. | Phrase
🗑️ | He has to take out the trash. | Él tiene que sacar la basura. | He has to take out the trash. | Él tiene que sacar la basura. | Phrase
🧹 | She has to clean her room. | Ella tiene que limpiar su habitación (de ella - se refiere a ella). | Today, she has to clean her room. | Hoy, ella tiene que limpiar su habitación (de ella). | Phrase
🧺 | She has to do laundry. | Ella tiene que lavar la ropa. | Today, she has to do laundry. | Hoy, ella tiene que lavar la ropa. | Phrase
🍽️ | She has to set the table. | Ella tiene que poner la mesa. | She has to set the table. | Ella tiene que poner la mesa. | Phrase
🧼 | She has to wash the dishes. | Ella tiene que lavar los platos. | She has to wash the dishes. | Ella tiene que lavar los platos. | Phrase
🗑️ | She has to take out the trash. | Ella tiene que sacar la basura. | She has to take out the trash. | Ella tiene que sacar la basura. | Phrase
🐠 | I went to the aquarium. | Fui al acuario. | Yesterday, I went to the aquarium. | Ayer, fui al acuario. | Phrase
🎡 | I went to the amusement park. | Fui al parque de atracciones. | Yesterday, I went to the amusement park. | Ayer, fui al parque de atracciones. | Phrase
🏛️ | I went to the museum. | Fui al museo. | Yesterday, I went to the museum. | Ayer, fui al museo. | Phrase
🦁 | I went to the zoo. | Fui al zoológico. | Yesterday, I went to the zoo. | Ayer, fui al zoológico. | Phrase
🌊 | I went to the water park. | Fui al parque acuático. | Yesterday, I went to the water park. | Ayer, fui al parque acuático. | Phrase
🐠 | He went to the aquarium. | Él fue al acuario. | Yesterday, he went to the aquarium. | Ayer, él fue al acuario. | Phrase
🏛️ | He went to the museum. | Él fue al museo. | Yesterday, he went to the museum. | Ayer, él fue al museo. | Phrase
🦁 | He went to the zoo. | Él fue al zoológico. | Yesterday, he went to the zoo. | Ayer, él fue al zoológico. | Phrase
🏖️ | She went to the beach. | Ella fue a la playa. | Yesterday, she went to the beach. | Ayer, ella fue a la playa. | Phrase
🎡 | She went to the amusement park. | Ella fue al parque de atracciones. | Yesterday, she went to the amusement park. | Ayer, ella fue al parque de atracciones. | Phrase
🌊 | She went to the water park. | Ella fue al parque acuático. | Yesterday, she went to the water park. | Ayer, ella fue al parque acuático. | Phrase
❓ | Did he have fun? | ¿Se divirtió él? | Tell me, did he have fun? | Dime, ¿se divirtió él? | Question
🎉 | Yes, he did. | Sí, se divirtió. | Yes, he did have fun. | Sí, él se divirtió. | Phrase
❌ | No, he didn't. | No, no se divirtió. | No, he didn't have fun. | No, no se divirtió. | Phrase
❓ | Did she have fun? | ¿Se divirtió ella? | Tell me, did she have fun? | Dime, ¿se divirtió ella? | Question
🎉 | Yes, she did. | Sí, se divirtió. | Yes, she did have fun. | Sí, ella se divirtió. | Phrase
❌ | No, she didn't. | No, no se divirtió. | No, she didn't have fun. | No, no se divirtió. | Phrase
❓ | What was the weather like? | ¿Cómo estuvo el clima? | Tell me, what was the weather like? | Dime, ¿cómo estuvo el clima? | Question
☀️ | It was sunny. | Estuvo soleado. | Yesterday, it was sunny. | Ayer, estuvo soleado. | Phrase
🌧️ | It was rainy. | Estuvo lluvioso. | Yesterday, it was rainy. | Ayer, estuvo lluvioso. | Phrase
☁️ | It was cloudy. | Estuvo nublado. | Yesterday, it was cloudy. | Ayer, estuvo nublado. | Phrase
💨 | It was windy. | Estuvo ventoso. | Yesterday, it was windy. | Ayer, estuvo ventoso. | Phrase
❄️ | It was snowy. | Estuvo nevado. | Yesterday, it was snowy. | Ayer, estuvo nevado. | Phrase
🔥 | It was hot. | Estuvo caluroso. | Yesterday, it was hot. | Ayer, estuvo caluroso. | Phrase
🥶 | It was cold. | Estuvo frío. | Yesterday, it was cold. | Ayer, estuvo frío. | Phrase
    """.trimIndent(), "Basics 3 - List 8")
}
