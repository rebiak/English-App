package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object BasicsBooklet2 {
    const val FOLDER_NAME = "Basics 2"

    val categoryNames: List<String> = (1..8).map { "Basics 2 - List $it" }

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
😄 | Happy | Feliz | I am happy | Estoy feliz | Word
😢 | Sad | Triste | He is sad | Él está triste | Word
🔥 | Hot | Caluroso | It is hot today | Hace calor hoy | Word
❄️ | Cold | Frío | I am cold | Tengo frío | Word
😴 | Tired | Cansado | She is tired | Ella está cansada | Word
🤒 | Sick | Enfermo | He is sick | Él está enfermo | Word
👨 | He | Él | He is my friend | Él es mi amigo | Word
👩 | She | Ella | She is my sister | Ella es mi hermana | Word
❓ | Is he happy? | ¿Está él feliz? | Tell me, is he happy? | Dime, ¿está él feliz? | Question
✅ | Is he happy? Yes, he is. | ¿Está él feliz? Sí, él está. | Is he happy? Yes, he is. | ¿Está él feliz? Sí, él está. | Phrase
❌ | Is he happy? No, he isn't. | ¿Está él feliz? No, él no está. | Is he happy? No, he isn't. | ¿Está él feliz? No, él no está. | Phrase
😴 | She's tired. | Ella está cansada. | Look, she's tired. | Mira, ella está cansada. | Phrase
😢 | He's sad. | Él está triste. | Look, he's sad. | Mira, él está triste. | Phrase
👨‍⚕️ | Doctor | Doctor | He is a doctor | Él es un doctor | Word
👩‍⚕️ | Nurse | Enfermera | She is a nurse | Ella es una enfermera | Word
👨‍🏫 | Teacher | Profesor | The teacher is nice | El profesor es amable | Word
👨‍✈️ | Pilot | Piloto | The pilot flies | El piloto vuela | Word
👨‍🍳 | Cook | Cocinero | He is a good cook | Él es un buen cocinero | Word
👨‍🎓 | Student | Estudiante | I am a student | Soy un estudiante | Word
👨‍⚕️ | He's a doctor. | Él es un doctor. | Look, he's a doctor. | Mira, él es un doctor. | Phrase
👩‍⚕️ | She's a nurse. | Ella es una enfermera. | Look, she's a nurse. | Mira, ella es una enfermera. | Phrase
❓ | Is he a doctor? | ¿Es él un doctor? | Tell me, is he a doctor? | Dime, ¿es él un doctor? | Question
✅ | Is he a doctor? Yes, he is. | ¿Es él un doctor? Sí, él es. | Is he a doctor? Yes, he is. | ¿Es él un doctor? Sí, él es. | Phrase
❌ | Is he a doctor? No, he isn't. | ¿Es él un doctor? No, él no es. | Is he a doctor? No, he isn't. | ¿Es él un doctor? No, él no es. | Phrase
❓ | Is she a nurse? | ¿Es ella una enfermera? | Tell me, is she a nurse? | Dime, ¿es ella una enfermera? | Question
✅ | Is she a nurse? Yes, she is. | ¿Es ella una enfermera? Sí, ella es. | Is she a nurse? Yes, she is. | ¿Es ella una enfermera? Sí, ella es. | Phrase
❌ | Is she a nurse? No, she isn't. | ¿Es ella una enfermera? No, ella no es. | Is she a nurse? No, she isn't. | ¿Es ella una enfermera? No, ella no es. | Phrase
❓ | Are you OK? | ¿Estás bien? | Tell me, are you OK? | Dime, ¿estás bien? | Question
🤔 | I think so. | Creo que sí. | Well, I think so. | Bueno, creo que sí. | Phrase
🙏 | Thanks. | Gracias. | Thanks a lot. | Muchas gracias. | Phrase
🦸 | Be brave. | Sé valiente. | Please, be brave. | Por favor, sé valiente. | Phrase
👮 | Police officer | Oficial de policía | He is a police officer | Él es un oficial de policía | Word
👨‍🚒 | Firefighter | Bombero | The firefighter is brave | El bombero es valiente | Word
🚌 | Bus driver | Conductor de autobús | He is a bus driver | Él es un conductor de autobús | Word
⚽ | Soccer player | Jugador de fútbol | She is a soccer player | Ella es una jugadora de fútbol | Word
👮 | They're police officers. | Ellos son oficiales de policía. | Look, they're police officers. | Mira, ellos son oficiales de policía. | Phrase
❓ | Are they police officers? | ¿Son ellos oficiales de policía? | Tell me, are they police officers? | Dime, ¿son ellos oficiales de policía? | Question
✅ | Are they police officers? Yes, they are. | ¿Son ellos oficiales de policía? Sí, ellos son. | Are they police officers? Yes, they are. | ¿Son ellos oficiales de policía? Sí, ellos son. | Phrase
❌ | Are they police officers? No, they aren't. | ¿Son ellos oficiales de policía? No, ellos no son. | Are they police officers? No, they aren't. | ¿Son ellos oficiales de policía? No, ellos no son. | Phrase
😄 | He's happy. | Él está feliz. | Look, he's happy. | Mira, él está feliz. | Phrase
🔥 | He's hot. | Él tiene calor. | Look, he's hot. | Mira, él tiene calor. | Phrase
❄️ | He's cold. | Él tiene frío. | Look, he's cold. | Mira, él tiene frío. | Phrase
😴 | He's tired. | Él está cansado. | Look, he's tired. | Mira, él está cansado. | Phrase
🤒 | He's sick. | Él está enfermo. | Look, he's sick. | Mira, él está enfermo. | Phrase
😄 | She's happy. | Ella está feliz. | Look, she's happy. | Mira, ella está feliz. | Phrase
😢 | She's sad. | Ella está triste. | Look, she's sad. | Mira, ella está triste. | Phrase
🔥 | She's hot. | Ella tiene calor. | Look, she's hot. | Mira, ella tiene calor. | Phrase
❄️ | She's cold. | Ella tiene frío. | Look, she's cold. | Mira, ella tiene frío. | Phrase
🤒 | She's sick. | Ella está enferma. | Look, she's sick. | Mira, ella está enferma. | Phrase
❓ | Is he sad? | ¿Está él triste? | Tell me, is he sad? | Dime, ¿está él triste? | Question
❓ | Is he hot? | ¿Tiene él calor? | Tell me, is he hot? | Dime, ¿tiene él calor? | Question
❓ | Is he cold? | ¿Tiene él frío? | Tell me, is he cold? | Dime, ¿tiene él frío? | Question
❓ | Is he tired? | ¿Está él cansado? | Tell me, is he tired? | Dime, ¿está él cansado? | Question
❓ | Is he sick? | ¿Está él enfermo? | Tell me, is he sick? | Dime, ¿está él enfermo? | Question
❓ | Is she happy? | ¿Está ella feliz? | Tell me, is she happy? | Dime, ¿está ella feliz? | Question
❓ | Is she sad? | ¿Está ella triste? | Tell me, is she sad? | Dime, ¿está ella triste? | Question
❓ | Is she hot? | ¿Tiene ella calor? | Tell me, is she hot? | Dime, ¿tiene ella calor? | Question
❓ | Is she cold? | ¿Tiene ella frío? | Tell me, is she cold? | Dime, ¿tiene ella frío? | Question
❓ | Is she tired? | ¿Está ella cansada? | Tell me, is she tired? | Dime, ¿está ella cansada? | Question
❓ | Is she sick? | ¿Está ella enferma? | Tell me, is she sick? | Dime, ¿está ella enferma? | Question
👨‍🏫 | He's a teacher. | Él es un profesor. | Look, he's a teacher. | Mira, él es un profesor. | Phrase
👨‍✈️ | He's a pilot. | Él es un piloto. | Look, he's a pilot. | Mira, él es un piloto. | Phrase
👨‍🍳 | He's a cook. | Él es un cocinero. | Look, he's a cook. | Mira, él es un cocinero. | Phrase
👨‍🎓 | He's a student. | Él es un estudiante. | Look, he's a student. | Mira, él es un estudiante. | Phrase
👩‍⚕️ | She's a doctor. | Ella es una doctora. | Look, she's a doctor. | Mira, ella es una doctora. | Phrase
👩‍🏫 | She's a teacher. | Ella es una profesora. | Look, she's a teacher. | Mira, ella es una profesora. | Phrase
👩‍✈️ | She's a pilot. | Ella es una piloto. | Look, she's a pilot. | Mira, ella es una piloto. | Phrase
👩‍🍳 | She's a cook. | Ella es una cocinera. | Look, she's a cook. | Mira, ella es una cocinera. | Phrase
👩‍🎓 | She's a student. | Ella es una estudiante. | Look, she's a student. | Mira, ella es una estudiante. | Phrase
👨‍🚒 | They're firefighters. | Ellos son bomberos. | Look, they're firefighters. | Mira, ellos son bomberos. | Phrase
🚌 | They're bus drivers. | Ellos son conductores de autobús. | Look, they're bus drivers. | Mira, ellos son conductores de autobús. | Phrase
⚽ | They're soccer players. | Ellos son jugadores de fútbol. | Look, they're soccer players. | Mira, ellos son jugadores de fútbol. | Phrase
    """.trimIndent(), "Basics 2 - List 1")

    private fun getList2(): List<Flashcard> = parseCards("""
🥗 | Salad | Ensalada | I like salad | Me gusta la ensalada | Word
🍝 | Spaghetti | Espagueti | I eat spaghetti | Como espagueti | Word
🍟 | French fries | Papas fritas | I want French fries | Quiero papas fritas | Word
🥩 | Steak | Bistec | The steak is good | El bistec es bueno | Word
🥣 | I want soup. | Quiero sopa. | Yes, I want soup. | Sí, quiero sopa. | Phrase
🥣 | I don't want soup. | No quiero sopa. | No, I don't want soup. | No, no quiero sopa. | Phrase
🥣 | He wants soup. | Él quiere sopa. | Look, he wants soup. | Mira, él quiere sopa. | Phrase
🥣 | He doesn't want soup. | Él no quiere sopa. | No, he doesn't want soup. | No, él no quiere sopa. | Phrase
🥣 | She wants soup. | Ella quiere sopa. | Look, she wants soup. | Mira, ella quiere sopa. | Phrase
🥣 | She doesn't want soup. | Ella no quiere sopa. | No, she doesn't want soup. | No, ella no quiere sopa. | Phrase
❓ | Does he want soup? | ¿Quiere él sopa? | Tell me, does he want soup? | Dime, ¿quiere él sopa? | Question
❓ | Does she want soup? | ¿Quiere ella sopa? | Tell me, does she want soup? | Dime, ¿quiere ella sopa? | Question
✅ | Does he want soup? Yes, he does. | ¿Quiere él sopa? Sí, él quiere. | Does he want soup? Yes, he does. | ¿Quiere él sopa? Sí, él quiere. | Phrase
❌ | Does he want soup? No, he doesn't. | ¿Quiere él sopa? No, él no quiere. | Does he want soup? No, he doesn't. | ¿Quiere él sopa? No, él no quiere. | Phrase
✅ | Does she want soup? Yes, she does. | ¿Quiere ella sopa? Sí, ella quiere. | Does she want soup? Yes, she does. | ¿Quiere ella sopa? Sí, ella quiere. | Phrase
❌ | Does she want soup? No, she doesn't. | ¿Quiere ella sopa? No, ella no quiere. | Does she want soup? No, she doesn't. | ¿Quiere ella sopa? No, ella no quiere. | Phrase
🍎 | Apple | Manzana | I eat an apple | Como una manzana | Word
🍌 | Banana | Plátano | The banana is yellow | El plátano es amarillo | Word
🍊 | Orange | Naranja | I want an orange | Quiero una naranja | Word
🍑 | Peach | Melocotón | The peach is sweet | El melocotón es dulce | Word
🍎 | Apple | Manzana | One apple | Una manzana | Word
🍎 | Apples | Manzanas | Two apples | Dos manzanas | Word
🍌 | Banana | Plátano | One banana | Un plátano | Word
🍌 | Bananas | Plátanos | Three bananas | Tres plátanos | Word
🍊 | Orange | Naranja | One orange | Una naranja | Word
🍊 | Oranges | Naranjas | Four oranges | Cuatro naranjas | Word
🍑 | Peach | Melocotón | One peach | Un melocotón | Word
🍑 | Peaches | Melocotones | Five peaches | Cinco melocotones | Word
❓ | Do you want apples? | ¿Quieres manzanas? | Tell me, do you want apples? | Dime, ¿quieres manzanas? | Question
✅ | Do you want apples? Yes, I do. | ¿Quieres manzanas? Sí, sí quiero. | Do you want apples? Yes, I do. | ¿Quieres manzanas? Sí, sí quiero. | Phrase
❌ | Do you want apples? No, I don't. | ¿Quieres manzanas? No, no quiero. | Do you want apples? No, I don't. | ¿Quieres manzanas? No, no quiero. | Phrase
🥛 | Here's your milk. | Aquí está tu leche. | Look, here's your milk. | Mira, aquí está tu leche. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
😇 | Be polite. | Sé educado. | Please, be polite. | Por favor, sé educado. | Phrase
🥛 | Milk | Leche | I drink milk | Bebo leche | Word
🥛 | Yogurt | Yogur | I like yogurt | Me gusta el yogur | Word
🧀 | Cheese | Queso | The cheese is yellow | El queso es amarillo | Word
🧈 | Butter | Mantequilla | Bread with butter | Pan con mantequilla | Word
🥛 | I have milk. | Tengo leche. | Yes, I have milk. | Sí, tengo leche. | Phrase
🥛 | I don't have milk. | No tengo leche. | No, I don't have milk. | No, no tengo leche. | Phrase
🥛 | He has milk. | Él tiene leche. | Look, he has milk. | Mira, él tiene leche. | Phrase
🥛 | He doesn't have milk. | Él no tiene leche. | No, he doesn't have milk. | No, él no tiene leche. | Phrase
🥛 | She has milk. | Ella tiene leche. | Look, she has milk. | Mira, ella tiene leche. | Phrase
🥛 | She doesn't have milk. | Ella no tiene leche. | No, she doesn't have milk. | No, ella no tiene leche. | Phrase
❓ | Does he have milk? | ¿Tiene él leche? | Tell me, does he have milk? | Dime, ¿tiene él leche? | Question
❓ | Does she have milk? | ¿Tiene ella leche? | Tell me, does she have milk? | Dime, ¿tiene ella leche? | Question
✅ | Does he have milk? Yes, he does. | ¿Tiene él leche? Sí, él tiene. | Does he have milk? Yes, he does. | ¿Tiene él leche? Sí, él tiene. | Phrase
❌ | Does he have milk? No, he doesn't. | ¿Tiene él leche? No, él no tiene. | Does he have milk? No, he doesn't. | ¿Tiene él leche? No, él no tiene. | Phrase
✅ | Does she have milk? Yes, she does. | ¿Tiene ella leche? Sí, ella tiene. | Does she have milk? Yes, she does. | ¿Tiene ella leche? Sí, ella tiene. | Phrase
❌ | Does she have milk? No, she doesn't. | ¿Tiene ella leche? No, ella no tiene. | Does she have milk? No, she doesn't. | ¿Tiene ella leche? No, ella no tiene. | Phrase
🥗 | I want salad. | Quiero ensalada. | Yes, I want salad. | Sí, quiero ensalada. | Phrase
🍝 | I want spaghetti. | Quiero espagueti. | Yes, I want spaghetti. | Sí, quiero espagueti. | Phrase
🍟 | I want French fries. | Quiero papas fritas. | Yes, I want French fries. | Sí, quiero papas fritas. | Phrase
🥩 | I want steak. | Quiero bistec. | Yes, I want steak. | Sí, quiero bistec. | Phrase
🥗 | I don't want salad. | No quiero ensalada. | No, I don't want salad. | No, no quiero ensalada. | Phrase
🍝 | I don't want spaghetti. | No quiero espagueti. | No, I don't want spaghetti. | No, no quiero espagueti. | Phrase
🍟 | I don't want French fries. | No quiero papas fritas. | No, I don't want French fries. | No, no quiero papas fritas. | Phrase
🥩 | I don't want steak. | No quiero bistec. | No, I don't want steak. | No, no quiero bistec. | Phrase
🥗 | He wants salad. | Él quiere ensalada. | Look, he wants salad. | Mira, él quiere ensalada. | Phrase
🍝 | He wants spaghetti. | Él quiere espagueti. | Look, he wants spaghetti. | Mira, él quiere espagueti. | Phrase
🍟 | He wants French fries. | Él quiere papas fritas. | Look, he wants French fries. | Mira, él quiere papas fritas. | Phrase
🥩 | He wants steak. | Él quiere bistec. | Look, he wants steak. | Mira, él quiere bistec. | Phrase
🥗 | He doesn't want salad. | Él no quiere ensalada. | No, he doesn't want salad. | No, él no quiere ensalada. | Phrase
🍝 | He doesn't want spaghetti. | Él no quiere espagueti. | No, he doesn't want spaghetti. | No, él no quiere espagueti. | Phrase
🍟 | He doesn't want French fries. | Él no quiere papas fritas. | No, he doesn't want French fries. | No, él no quiere papas fritas. | Phrase
🥩 | He doesn't want steak. | Él no quiere bistec. | No, he doesn't want steak. | No, él no quiere bistec. | Phrase
🥗 | She wants salad. | Ella quiere ensalada. | Look, she wants salad. | Mira, ella quiere ensalada. | Phrase
🍝 | She wants spaghetti. | Ella quiere espagueti. | Look, she wants spaghetti. | Mira, ella quiere espagueti. | Phrase
🍟 | She wants French fries. | Ella quiere papas fritas. | Look, she wants French fries. | Mira, ella quiere papas fritas. | Phrase
🥩 | She wants steak. | Ella quiere bistec. | Look, she wants steak. | Mira, ella quiere bistec. | Phrase
🥗 | She doesn't want salad. | Ella no quiere ensalada. | No, she doesn't want salad. | No, ella no quiere ensalada. | Phrase
🍝 | She doesn't want spaghetti. | Ella no quiere espagueti. | No, she doesn't want spaghetti. | No, ella no quiere espagueti. | Phrase
🍟 | She doesn't want French fries. | Ella no quiere papas fritas. | No, she doesn't want French fries. | No, ella no quiere papas fritas. | Phrase
🥩 | She doesn't want steak. | Ella no quiere bistec. | No, she doesn't want steak. | No, ella no quiere bistec. | Phrase
❓ | Does he want salad? | ¿Quiere él ensalada? | Tell me, does he want salad? | Dime, ¿quiere él ensalada? | Question
❓ | Does he want spaghetti? | ¿Quiere él espagueti? | Tell me, does he want spaghetti? | Dime, ¿quiere él espagueti? | Question
❓ | Does he want French fries? | ¿Quiere él papas fritas? | Tell me, does he want French fries? | Dime, ¿quiere él papas fritas? | Question
❓ | Does he want steak? | ¿Quiere él bistec? | Tell me, does he want steak? | Dime, ¿quiere él bistec? | Question
❓ | Does she want salad? | ¿Quiere ella ensalada? | Tell me, does she want salad? | Dime, ¿quiere ella ensalada? | Question
❓ | Does she want spaghetti? | ¿Quiere ella espagueti? | Tell me, does she want spaghetti? | Dime, ¿quiere ella espagueti? | Question
❓ | Does she want French fries? | ¿Quiere ella papas fritas? | Tell me, does she want French fries? | Dime, ¿quiere ella papas fritas? | Question
❓ | Does she want steak? | ¿Quiere ella bistec? | Tell me, does she want steak? | Dime, ¿quiere ella bistec? | Question
❓ | Do you want bananas? | ¿Quieres plátanos? | Tell me, do you want bananas? | Dime, ¿quieres plátanos? | Question
❓ | Do you want oranges? | ¿Quieres naranjas? | Tell me, do you want oranges? | Dime, ¿quieres naranjas? | Question
❓ | Do you want peaches? | ¿Quieres melocotones? | Tell me, do you want peaches? | Dime, ¿quieres melocotones? | Question
    """.trimIndent(), "Basics 2 - List 2")

    private fun getList3(): List<Flashcard> = parseCards("""
👔 | Shirt | Camisa | I wear a white shirt | Uso una camisa blanca | Word
👗 | Dress | Vestido | She wears a red dress | Ella usa un vestido rojo | Word
👗 | Skirt | Falda | The skirt is blue | La falda es azul | Word
👖 | Pants | Pantalones | I have black pants | Tengo pantalones negros | Word
🧦 | Socks | Calcetines | White socks | Calcetines blancos | Word
👞 | Shoes | Zapatos | My shoes are new | Mis zapatos son nuevos | Word
🧢 | Cap | Gorra | A blue cap | Una gorra azul | Word
🧥 | Jacket | Chaqueta | Wear a jacket | Usa una chaqueta | Word
❓ | What's he wearing? | ¿Qué está usando él? | Tell me, what's he wearing? | Dime, ¿qué está usando él? | Question
👔 | He's wearing a white shirt. | Él está usando una camisa blanca. | Look, he's wearing a white shirt. | Mira, él está usando una camisa blanca. | Phrase
❓ | What's she wearing? | ¿Qué está usando ella? | Tell me, what's she wearing? | Dime, ¿qué está usando ella? | Question
👗 | She's wearing a yellow dress. | Ella está usando un vestido amarillo. | Look, she's wearing a yellow dress. | Mira, ella está usando un vestido amarillo. | Phrase
👕 | T-shirt | Camiseta | A cool T-shirt | Una camiseta genial | Word
🥾 | Boots | Botas | Winter boots | Botas de invierno | Word
👒 | Hat | Sombrero | Wear a hat | Usa un sombrero | Word
🧥 | Coat | Abrigo | A warm coat | Un abrigo cálido | Word
❓ | Whose T-shirt is this? | ¿De quién es esta camiseta? | Tell me, whose T-shirt is this? | Dime, ¿de quién es esta camiseta? | Question
👕 | It's Jim's T-shirt. | Es la camiseta de Jim. | Look, it's Jim's T-shirt. | Mira, es la camiseta de Jim. | Phrase
❓ | Whose boots are these? | ¿De quién son estas botas? | Tell me, whose boots are these? | Dime, ¿de quién son estas botas? | Question
🥾 | They're Jim's boots. | Son las botas de Jim. | Look, they're Jim's boots. | Mira, son las botas de Jim. | Phrase
🌧️ | It's raining. | Está lloviendo. | Look, it's raining outside. | Mira, está lloviendo afuera. | Phrase
😟 | Oh, no! I don't have a hat. | ¡Oh, no! No tengo un sombrero. | Oh, no! I don't have a hat. | ¡Oh, no! No tengo un sombrero. | Phrase
👒 | I have a hat. Here. | Tengo un sombrero. Ten. | Look, I have a hat. Here. | Mira, tengo un sombrero. Ten. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
🥰 | Be thoughtful. | Sé considerado. | Please, be thoughtful. | Por favor, sé considerado. | Phrase
🧣 | Scarf | Bufanda | A warm scarf | Una bufanda cálida | Word
🧤 | Mittens | Manoplas | Wool mittens | Manoplas de lana | Word
🧤 | Gloves | Guantes | Warm gloves | Guantes cálidos | Word
☂️ | Umbrella | Paraguas | Open the umbrella | Abre el paraguas | Word
🧣 | He's wearing a scarf. | Él está usando una bufanda. | Look, he's wearing a scarf. | Mira, él está usando una bufanda. | Phrase
🧣 | She's wearing a scarf. | Ella está usando una bufanda. | Look, she's wearing a scarf. | Mira, ella está usando una bufanda. | Phrase
❓ | What's he wearing? | ¿Qué está usando él? | Tell me, what's he wearing? | Dime, ¿qué está usando él? | Question
❓ | What's she wearing? | ¿Qué está usando ella? | Tell me, what's she wearing? | Dime, ¿qué está usando ella? | Question
👗 | What's he wearing? He's wearing a skirt. | ¿Qué está usando él? Él está usando una falda. | What's he wearing? He's wearing a skirt. | ¿Qué está usando él? Él está usando una falda. | Phrase
👖 | What's he wearing? He's wearing pants. | ¿Qué está usando él? Él está usando pantalones. | What's he wearing? He's wearing pants. | ¿Qué está usando él? Él está usando pantalones. | Phrase
🧦 | What's he wearing? He's wearing socks. | ¿Qué está usando él? Él está usando calcetines. | What's he wearing? He's wearing socks. | ¿Qué está usando él? Él está usando calcetines. | Phrase
👞 | What's he wearing? He's wearing shoes. | ¿Qué está usando él? Él está usando zapatos. | What's he wearing? He's wearing shoes. | ¿Qué está usando él? Él está usando zapatos. | Phrase
🧢 | What's he wearing? He's wearing a cap. | ¿Qué está usando él? Él está usando una gorra. | What's he wearing? He's wearing a cap. | ¿Qué está usando él? Él está usando una gorra. | Phrase
🧥 | What's he wearing? He's wearing a jacket. | ¿Qué está usando él? Él está usando una chaqueta. | What's he wearing? He's wearing a jacket. | ¿Qué está usando él? Él está usando una chaqueta. | Phrase
👔 | What's she wearing? She's wearing a shirt. | ¿Qué está usando ella? Ella está usando una camisa. | What's she wearing? She's wearing a shirt. | ¿Qué está usando ella? Ella está usando una camisa. | Phrase
👗 | What's she wearing? She's wearing a skirt. | ¿Qué está usando ella? Ella está usando una falda. | What's she wearing? She's wearing a skirt. | ¿Qué está usando ella? Ella está usando una falda. | Phrase
👖 | What's she wearing? She's wearing pants. | ¿Qué está usando ella? Ella está usando pantalones. | What's she wearing? She's wearing pants. | ¿Qué está usando ella? Ella está usando pantalones. | Phrase
🧦 | What's she wearing? She's wearing socks. | ¿Qué está usando ella? Ella está usando calcetines. | What's she wearing? She's wearing socks. | ¿Qué está usando ella? Ella está usando calcetines. | Phrase
👞 | What's she wearing? She's wearing shoes. | ¿Qué está usando ella? Ella está usando zapatos. | What's she wearing? She's wearing shoes. | ¿Qué está usando ella? Ella está usando zapatos. | Phrase
🧢 | What's she wearing? She's wearing a cap. | ¿Qué está usando ella? Ella está usando una gorra. | What's she wearing? She's wearing a cap. | ¿Qué está usando ella? Ella está usando una gorra. | Phrase
🧥 | What's she wearing? She's wearing a jacket. | ¿Qué está usando ella? Ella está usando una chaqueta. | What's she wearing? She's wearing a jacket. | ¿Qué está usando ella? Ella está usando una chaqueta. | Phrase
👔 | What's he wearing? He's wearing a shirt. | ¿Qué está usando él? Él está usando una camisa. | What's he wearing? He's wearing a shirt. | ¿Qué está usando él? Él está usando una camisa. | Phrase
👗 | What's he wearing? He's wearing a dress. | ¿Qué está usando él? Él está usando un vestido. | What's he wearing? He's wearing a dress. | ¿Qué está usando él? Él está usando un vestido. | Phrase
👗 | What's he wearing? He's wearing a skirt. | ¿Qué está usando él? Él está usando una falda. | What's he wearing? He's wearing a skirt. | ¿Qué está usando él? Él está usando una falda. | Phrase
👖 | What's he wearing? He's wearing pants. | ¿Qué está usando él? Él está usando pantalones. | What's he wearing? He's wearing pants. | ¿Qué está usando él? Él está usando pantalones. | Phrase
🧦 | What's he wearing? He's wearing socks. | ¿Qué está usando él? Él está usando calcetines. | What's he wearing? He's wearing socks. | ¿Qué está usando él? Él está usando calcetines. | Phrase
👞 | What's he wearing? He's wearing shoes. | ¿Qué está usando él? Él está usando zapatos. | What's he wearing? He's wearing shoes. | ¿Qué está usando él? Él está usando zapatos. | Phrase
🧢 | What's he wearing? He's wearing a cap. | ¿Qué está usando él? Él está usando una gorra. | What's he wearing? He's wearing a cap. | ¿Qué está usando él? Él está usando una gorra. | Phrase
🧥 | What's he wearing? He's wearing a jacket. | ¿Qué está usando él? Él está usando una chaqueta. | What's he wearing? He's wearing a jacket. | ¿Qué está usando él? Él está usando una chaqueta. | Phrase
👔 | What's she wearing? He's wearing a shirt. | ¿Qué está usando ella? Él está usando una camisa. | What's she wearing? He's wearing a shirt. | ¿Qué está usando ella? Él está usando una camisa. | Phrase
👗 | What's she wearing? He's wearing a dress. | ¿Qué está usando ella? Él está usando un vestido. | What's she wearing? He's wearing a dress. | ¿Qué está usando ella? Él está usando un vestido. | Phrase
👗 | What's she wearing? He's wearing a skirt. | ¿Qué está usando ella? Él está usando una falda. | What's she wearing? He's wearing a skirt. | ¿Qué está usando ella? Él está usando una falda. | Phrase
👖 | What's she wearing? He's wearing pants. | ¿Qué está usando ella? Él está usando pantalones. | What's she wearing? He's wearing pants. | ¿Qué está usando ella? Él está usando pantalones. | Phrase
🧦 | What's she wearing? He's wearing socks. | ¿Qué está usando ella? Él está usando calcetines. | What's she wearing? He's wearing socks. | ¿Qué está usando ella? Él está usando calcetines. | Phrase
👞 | What's she wearing? He's wearing shoes. | ¿Qué está usando ella? Él está usando zapatos. | What's she wearing? He's wearing shoes. | ¿Qué está usando ella? Él está usando zapatos. | Phrase
🧢 | What's she wearing? He's wearing a cap. | ¿Qué está usando ella? Él está usando una gorra. | What's she wearing? He's wearing a cap. | ¿Qué está usando ella? Él está usando una gorra. | Phrase
🧥 | What's she wearing? He's wearing a jacket. | ¿Qué está usando ella? Él está usando una chaqueta. | What's she wearing? He's wearing a jacket. | ¿Qué está usando ella? Él está usando una chaqueta. | Phrase
❓ | Whose hat is this? | ¿De quién es este sombrero? | Tell me, whose hat is this? | Dime, ¿de quién es este sombrero? | Question
❓ | Whose coat is this? | ¿De quién es este abrigo? | Tell me, whose coat is this? | Dime, ¿de quién es este abrigo? | Question
❓ | Whose shoes are these? | ¿De quién son estos zapatos? | Tell me, whose shoes are these? | Dime, ¿de quién son estos zapatos? | Question
❓ | Whose socks are these? | ¿De quién son estos calcetines? | Tell me, whose socks are these? | Dime, ¿de quién son estos calcetines? | Question
❓ | Whose pants are these? | ¿De quién son estos pantalones? | Tell me, whose pants are these? | Dime, ¿de quién son estos pantalones? | Question
👕 | It's my T-shirt. | Es mi camiseta. | Look, it's my T-shirt. | Mira, es mi camiseta. | Phrase
👒 | It's my hat. | Es mi sombrero. | Look, it's my hat. | Mira, es mi sombrero. | Phrase
🧥 | It's my coat. | Es mi abrigo. | Look, it's my coat. | Mira, es mi abrigo. | Phrase
🧥 | It's my jacket. | Es mi chaqueta. | Look, it's my jacket. | Mira, es mi chaqueta. | Phrase
🥾 | They're my boots. | Son mis botas. | Look, they're my boots. | Mira, son mis botas. | Phrase
👞 | They're my shoes. | Son mis zapatos. | Look, they're my shoes. | Mira, son mis zapatos. | Phrase
🧦 | They're my socks. | Son mis calcetines. | Look, they're my socks. | Mira, son mis calcetines. | Phrase
👖 | They're my pants. | Son mis pantalones. | Look, they're my pants. | Mira, son mis pantalones. | Phrase
🧤 | He's wearing mittens. | Él está usando manoplas. | Look, he's wearing mittens. | Mira, él está usando manoplas. | Phrase
🧤 | He's wearing gloves. | Él está usando guantes. | Look, he's wearing gloves. | Mira, él está usando guantes. | Phrase
☂️ | He's wearing an umbrella. | Él lleva un paraguas. | Look, he's carrying an umbrella. | Mira, él lleva un paraguas. | Phrase
🧤 | She's wearing mittens. | Ella está usando manoplas. | Look, she's wearing mittens. | Mira, ella está usando manoplas. | Phrase
🧤 | She's wearing gloves. | Ella está usando guantes. | Look, she's wearing gloves. | Mira, ella está usando guantes. | Phrase
☂️ | She's wearing an umbrella. | Ella lleva un paraguas. | Look, she's carrying an umbrella. | Mira, ella lleva un paraguas. | Phrase
🧣 | What's he wearing? He's wearing a scarf. | ¿Qué está usando él? Él está usando una bufanda. | What's he wearing? He's wearing a scarf. | ¿Qué está usando él? Él está usando una bufanda. | Phrase
🧤 | What's he wearing? He's wearing mittens. | ¿Qué está usando él? Él está usando manoplas. | What's he wearing? He's wearing mittens. | ¿Qué está usando él? Él está usando manoplas. | Phrase
🧤 | What's he wearing? He's wearing gloves. | ¿Qué está usando él? Él está usando guantes. | What's he wearing? He's wearing gloves. | ¿Qué está usando él? Él está usando guantes. | Phrase
☂️ | What's he wearing? He's wearing an umbrella. | ¿Qué lleva él? Él lleva un paraguas. | What's he carrying? He's carrying an umbrella. | ¿Qué lleva él? Él lleva un paraguas. | Phrase
🧣 | What's she wearing? She's wearing a scarf. | ¿Qué está usando ella? Ella está usando una bufanda. | What's she wearing? She's wearing a scarf. | ¿Qué está usando ella? Ella está usando una bufanda. | Phrase
🧤 | What's she wearing? She's wearing mittens. | ¿Qué está usando ella? Ella está usando manoplas. | What's she wearing? She's wearing mittens. | ¿Qué está usando ella? Ella está usando manoplas. | Phrase
🧤 | What's she wearing? She's wearing gloves. | ¿Qué está usando ella? Ella está usando guantes. | What's she wearing? She's wearing gloves. | ¿Qué está usando ella? Ella está usando guantes. | Phrase
☂️ | What's she wearing? She's wearing an umbrella. | ¿Qué lleva ella? Ella lleva un paraguas. | What's she carrying? She's carrying an umbrella. | ¿Qué lleva ella? Ella lleva un paraguas. | Phrase
    """.trimIndent(), "Basics 2 - List 3")

    private fun getList4(): List<Flashcard> = parseCards("""
📖 | Read | Leer | I read books | Leo libros | Word
✍️ | Write | Escribir | I write my name | Escribo mi nombre | Word
🎨 | Draw | Dibujar | Draw a picture | Dibuja una imagen | Word
🗣️ | Talk | Hablar | We talk together | Hablamos juntos | Word
🎤 | Sing | Cantar | She can sing | Ella puede cantar | Word
💃 | Dance | Bailar | I like to dance | Me gusta bailar | Word
❓ | What's he doing? | ¿Qué está haciendo él? | Tell me, what's he doing? | Dime, ¿qué está haciendo él? | Question
📖 | He's reading. | Él está leyendo. | Look, he's reading. | Mira, él está leyendo. | Phrase
❓ | What's she doing? | ¿Qué está haciendo ella? | Tell me, what's she doing? | Dime, ¿qué está haciendo ella? | Question
📖 | She's reading. | Ella está leyendo. | Look, she's reading. | Mira, ella está leyendo. | Phrase
❓ | What are they doing? | ¿Qué están haciendo ellos? | Tell me, what are they doing? | Dime, ¿qué están haciendo ellos? | Question
📖 | They're reading. | Ellos están leyendo. | Look, they're reading. | Mira, ellos están leyendo. | Phrase
🍽️ | Eat | Comer | Time to eat | Hora de comer | Word
🥤 | Drink | Beber | Drink water | Bebe agua | Word
😴 | Sleep | Dormir | I sleep at night | Duermo por la noche | Word
🎮 | Play | Jugar | Play a game | Juega un juego | Word
❓ | What's he doing? | ¿Qué está haciendo él? | Tell me, what's he doing? | Dime, ¿qué está haciendo él? | Question
🍽️ | He's eating. | Él está comiendo. | Look, he's eating. | Mira, él está comiendo. | Phrase
❓ | What's she doing? | ¿Qué está haciendo ella? | Tell me, what's she doing? | Dime, ¿qué está haciendo ella? | Question
🍽️ | She's eating. | Ella está comiendo. | Look, she's eating. | Mira, ella está comiendo. | Phrase
❓ | What are they doing? | ¿Qué están haciendo ellos? | Tell me, what are they doing? | Dime, ¿qué están haciendo ellos? | Question
🍽️ | They're eating. | Ellos están comiendo. | Look, they're eating. | Mira, ellos están comiendo. | Phrase
❓ | Where's the guitar? | ¿Dónde está la guitarra? | Tell me, where's the guitar? | Dime, ¿dónde está la guitarra? | Question
🤷 | I don't know. | No sé. | Well, I don't know. | Bueno, no lo sé. | Phrase
🎸 | Look! Here it is. | ¡Mira! Aquí está. | Look! Here it is. | ¡Mira! Aquí está. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
👀 | Look at me. | Mírame. | Please, look at me. | Por favor, mírame. | Phrase
🎤 | I can sing. | Puedo cantar. | Yes, I can sing. | Sí, puedo cantar. | Phrase
👂 | Listen to me. | Escúchame. | Please, listen to me. | Por favor, escúchame. | Phrase
👏 | Good job! | ¡Buen trabajo! | Wow, good job! | ¡Vaya, buen trabajo! | Phrase
✍️ | What's he doing? He's writing. | ¿Qué está haciendo él? Él está escribiendo. | What's he doing? He's writing. | ¿Qué está haciendo él? Él está escribiendo. | Phrase
🎨 | What's he doing? He's drawing. | ¿Qué está haciendo él? Él está dibujando. | What's he doing? He's drawing. | ¿Qué está haciendo él? Él está dibujando. | Phrase
🗣️ | What's he doing? He's talking. | ¿Qué está haciendo él? Él está hablando. | What's he doing? He's talking. | ¿Qué está haciendo él? Él está hablando. | Phrase
🎤 | What's he doing? He's singing. | ¿Qué está haciendo él? Él está cantando. | What's he doing? He's singing. | ¿Qué está haciendo él? Él está cantando. | Phrase
💃 | What's he doing? He's dancing. | ¿Qué está haciendo él? Él está bailando. | What's he doing? He's dancing. | ¿Qué está haciendo él? Él está bailando. | Phrase
✍️ | What's she doing? She's writing. | ¿Qué está haciendo ella? Ella está escribiendo. | What's she doing? She's writing. | ¿Qué está haciendo ella? Ella está escribiendo. | Phrase
🎨 | What's she doing? She's drawing. | ¿Qué está haciendo ella? Ella está dibujando. | What's she doing? She's drawing. | ¿Qué está haciendo ella? Ella está dibujando. | Phrase
🗣️ | What's she doing? She's talking. | ¿Qué está haciendo ella? Ella está hablando. | What's she doing? She's talking. | ¿Qué está haciendo ella? Ella está hablando. | Phrase
🎤 | What's she doing? She's singing. | ¿Qué está haciendo ella? Ella está cantando. | What's she doing? She's singing. | ¿Qué está haciendo ella? Ella está cantando. | Phrase
💃 | What's she doing? She's dancing. | ¿Qué está haciendo ella? Ella está bailando. | What's she doing? She's dancing. | ¿Qué está haciendo ella? Ella está bailando. | Phrase
✍️ | What are they doing? They're writing. | ¿Qué están haciendo ellos? Ellos están escribiendo. | What are they doing? They're writing. | ¿Qué están haciendo ellos? Ellos están escribiendo. | Phrase
🎨 | What are they doing? They're drawing. | ¿Qué están haciendo ellos? Ellos están dibujando. | What are they doing? They're drawing. | ¿Qué están haciendo ellos? Ellos están dibujando. | Phrase
🗣️ | What are they doing? They're talking. | ¿Qué están haciendo ellos? Ellos están hablando. | What are they doing? They're talking. | ¿Qué están haciendo ellos? Ellos están hablando. | Phrase
🎤 | What are they doing? They're singing. | ¿Qué están haciendo ellos? Ellos están cantando. | What are they doing? They're singing. | ¿Qué están haciendo ellos? Ellos están cantando. | Phrase
💃 | What are they doing? They're dancing. | ¿Qué están haciendo ellos? Ellos están bailando. | What are they doing? They're dancing. | ¿Qué están haciendo ellos? Ellos están bailando. | Phrase
🥤 | What's he doing? He's drinking. | ¿Qué está haciendo él? Él está bebiendo. | What's he doing? He's drinking. | ¿Qué está haciendo él? Él está bebiendo. | Phrase
😴 | What's he doing? He's sleeping. | ¿Qué está haciendo él? Él está durmiendo. | What's he doing? He's sleeping. | ¿Qué está haciendo él? Él está durmiendo. | Phrase
🎮 | What's he doing? He's playing. | ¿Qué está haciendo él? Él está jugando. | What's he doing? He's playing. | ¿Qué está haciendo él? Él está jugando. | Phrase
🥤 | What's she doing? She's drinking. | ¿Qué está haciendo ella? Ella está bebiendo. | What's she doing? She's drinking. | ¿Qué está haciendo ella? Ella está bebiendo. | Phrase
😴 | What's she doing? She's sleeping. | ¿Qué está haciendo ella? Ella está durmiendo. | What's she doing? She's sleeping. | ¿Qué está haciendo ella? Ella está durmiendo. | Phrase
🎮 | What's she doing? She's playing. | ¿Qué está haciendo ella? Ella está jugando. | What's she doing? She's playing. | ¿Qué está haciendo ella? Ella está jugando. | Phrase
🥤 | What are they doing? They're drinking. | ¿Qué están haciendo ellos? Ellos están bebiendo. | What are they doing? They're drinking. | ¿Qué están haciendo ellos? Ellos están bebiendo. | Phrase
😴 | What are they doing? They're sleeping. | ¿Qué están haciendo ellos? Ellos están durmiendo. | What are they doing? They're sleeping. | ¿Qué están haciendo ellos? Ellos están durmiendo. | Phrase
🎮 | What are they doing? They're playing. | ¿Qué están haciendo ellos? Ellos están jugando. | What are they doing? They're playing. | ¿Qué están haciendo ellos? Ellos están jugando. | Phrase
    """.trimIndent(), "Basics 2 - List 4")

    private fun getList5(): List<Flashcard> = parseCards("""
🔑 | Key | Llave | I have a key | Tengo una llave | Word
🔑 | Keys | Llaves | Two keys | Dos llaves | Word
🧻 | Tissue | Pañuelo de papel | Use a tissue | Usa un pañuelo de papel | Word
🧻 | Tissues | Pañuelos de papel | A box of tissues | Una caja de pañuelos | Word
⌚ | Watch | Reloj de pulsera | Look at your watch | Mira tu reloj | Word
⌚ | Watches | Relojes de pulsera | Two watches | Dos relojes de pulsera | Word
📚 | Comic book | Cómic / Historieta | Read a comic book | Lee un cómic | Word
📚 | Comic books | Cómics / Historietas | I like comic books | Me gustan los cómics | Word
🪮 | Comb | Peine | Use a comb | Usa un peine | Word
🪮 | Combs | Peines | Two combs | Dos peines | Word
🪙 | Coin | Moneda | A shiny coin | Una moneda brillante | Word
🪙 | Coins | Monedas | Three coins | Tres monedas | Word
🍫 | Candy bar | Barra de chocolate / dulce | Eat a candy bar | Come una barra de dulce | Word
🍫 | Candy bars | Barras de chocolate / dulce | Two candy bars | Dos barras de dulce | Word
🪥 | Brush | Cepillo | A hair brush | Un cepillo de pelo | Word
🪥 | Brushes | Cepillos | Two brushes | Dos cepillos | Word
📷 | Camera | Cámara | Take a picture with the camera | Toma una foto con la cámara | Word
📷 | Cameras | Cámaras | Digital cameras | Cámaras digitales | Word
🔑 | Key chain | Llavero | A cool key chain | Un llavero genial | Word
🔑 | Key chains | Llaveros | Many key chains | Muchos llaveros | Word
🎵 | Music player | Reproductor de música | Listen to the music player | Escucha el reproductor de música | Word
🎵 | Music players | Reproductores de música | Portable music players | Reproductores de música portátiles | Word
🔢 | Calculator | Calculadora | Use a calculator | Usa una calculadora | Word
🔢 | Calculators | Calculadoras | Math calculators | Calculadoras de matemáticas | Word
🎫 | Train pass | Pase de tren | Show your train pass | Muestra tu pase de tren | Word
🎫 | Train passes | Pases de tren | Two train passes | Dos pases de tren | Word
☂️ | Umbrella | Paraguas | Open the umbrella | Abre el paraguas | Word
☂️ | Umbrellas | Paraguas (plural) | Big umbrellas | Paraguas grandes | Word
👛 | Wallet | Billetera | Money in the wallet | Dinero en la billetera | Word
👛 | Wallets | Billeteras | Leather wallets | Billeteras de cuero | Word
🍱 | Lunch box | Lonchera | Pack the lunch box | Empaca la lonchera | Word
🍱 | Lunch boxes | Loncheras | School lunch boxes | Loncheras escolares | Word
❓ | What do you have? | ¿Qué tienes? | Tell me, what do you have? | Dime, ¿qué tienes? | Question
🔑 | What do you have? I have a key. | ¿Qué tienes? Tengo una llave. | What do you have? I have a key. | ¿Qué tienes? Tengo una llave. | Phrase
🧻 | What do you have? I have a tissue. | ¿Qué tienes? Tengo un pañuelo de papel. | What do you have? I have a tissue. | ¿Qué tienes? Tengo un pañuelo de papel. | Phrase
⌚ | What do you have? I have a watch. | ¿Qué tienes? Tengo un reloj. | What do you have? I have a watch. | ¿Qué tienes? Tengo un reloj. | Phrase
📚 | What do you have? I have a comic book. | ¿Qué tienes? Tengo un cómic. | What do you have? I have a comic book. | ¿Qué tienes? Tengo un cómic. | Phrase
🪮 | What do you have? I have a comb. | ¿Qué tienes? Tengo un peine. | What do you have? I have a comb. | ¿Qué tienes? Tengo un peine. | Phrase
🪙 | What do you have? I have a coin. | ¿Qué tienes? Tengo una moneda. | What do you have? I have a coin. | ¿Qué tienes? Tengo una moneda. | Phrase
🪥 | What do you have? I have a brush. | ¿Qué tienes? Tengo un cepillo. | What do you have? I have a brush. | ¿Qué tienes? Tengo un cepillo. | Phrase
🍫 | What do you have? I have a candy bar. | ¿Qué tienes? Tengo una barra de dulce. | What do you have? I have a candy bar. | ¿Qué tienes? Tengo una barra de dulce. | Phrase
📷 | What do you have? I have a camera. | ¿Qué tienes? Tengo una cámara. | What do you have? I have a camera. | ¿Qué tienes? Tengo una cámara. | Phrase
🔢 | What do you have? I have a calculator. | ¿Qué tienes? Tengo una calculadora. | What do you have? I have a calculator. | ¿Qué tienes? Tengo una calculadora. | Phrase
🎫 | What do you have? I have a train pass. | ¿Qué tienes? Tengo un pase de tren. | What do you have? I have a train pass. | ¿Qué tienes? Tengo un pase de tren. | Phrase
☂️ | What do you have? I have an umbrella. | ¿Qué tienes? Tengo un paraguas. | What do you have? I have an umbrella. | ¿Qué tienes? Tengo un paraguas. | Phrase
👛 | What do you have? I have a wallet. | ¿Qué tienes? Tengo una billetera. | What do you have? I have a wallet. | ¿Qué tienes? Tengo una billetera. | Phrase
🍱 | What do you have? I have a lunch box. | ¿Qué tienes? Tengo una lonchera. | What do you have? I have a lunch box. | ¿Qué tienes? Tengo una lonchera. | Phrase
❓ | What does he have? | ¿Qué tiene él? | Tell me, what does he have? | Dime, ¿qué tiene él? | Question
🔑 | What does he have? He has a key. | ¿Qué tiene él? Él tiene una llave. | What does he have? He has a key. | ¿Qué tiene él? Él tiene una llave. | Phrase
⌚ | What does he have? He has a watch. | ¿Qué tiene él? Él tiene un reloj. | What does he have? He has a watch. | ¿Qué tiene él? Él tiene un reloj. | Phrase
📚 | What does he have? He has a comic book. | ¿Qué tiene él? Él tiene un cómic. | What does he have? He has a comic book. | ¿Qué tiene él? Él tiene un cómic. | Phrase
📷 | What does he have? He has a camera. | ¿Qué tiene él? Él tiene una cámara. | What does he have? He has a camera. | ¿Qué tiene él? Él tiene una cámara. | Phrase
🔢 | What does he have? He has a calculator. | ¿Qué tiene él? Él tiene una calculadora. | What does he have? He has a calculator. | ¿Qué tiene él? Él tiene una calculadora. | Phrase
👛 | What does he have? He has a wallet. | ¿Qué tiene él? Él tiene una billetera. | What does he have? He has a wallet. | ¿Qué tiene él? Él tiene una billetera. | Phrase
❓ | What does she have? | ¿Qué tiene ella? | Tell me, what does she have? | Dime, ¿qué tiene ella? | Question
🧻 | What does she have? She has a tissue. | ¿Qué tiene ella? Ella tiene un pañuelo de papel. | What does she have? She has a tissue. | ¿Qué tiene ella? Ella tiene un pañuelo de papel. | Phrase
🪮 | What does she have? She has a comb. | ¿Qué tiene ella? Ella tiene un peine. | What does she have? She has a comb. | ¿Qué tiene ella? Ella tiene un peine. | Phrase
🪥 | What does she have? She has a brush. | ¿Qué tiene ella? Ella tiene un cepillo. | What does she have? She has a brush. | ¿Qué tiene ella? Ella tiene un cepillo. | Phrase
🍫 | What does she have? She has a candy bar. | ¿Qué tiene ella? Ella tiene una barra de dulce. | What does she have? She has a candy bar. | ¿Qué tiene ella? Ella tiene una barra de dulce. | Phrase
🎵 | What does she have? She has a music player. | ¿Qué tiene ella? Ella tiene un reproductor de música. | What does she have? She has a music player. | ¿Qué tiene ella? Ella tiene un reproductor de música. | Phrase
☂️ | What does she have? She has an umbrella. | ¿Qué tiene ella? Ella tiene un paraguas. | What does she have? She has an umbrella. | ¿Qué tiene ella? Ella tiene un paraguas. | Phrase
🍱 | What does she have? She has a lunch box. | ¿Qué tiene ella? Ella tiene una lonchera. | What does she have? She has a lunch box. | ¿Qué tiene ella? Ella tiene una lonchera. | Phrase
❓ | Do you have a key? | ¿Tienes una llave? | Tell me, do you have a key? | Dime, ¿tienes una llave? | Question
✅ | Do you have a key? Yes, I do. | ¿Tienes una llave? Sí, tengo. | Do you have a key? Yes, I do. | ¿Tienes una llave? Sí, tengo. | Phrase
❌ | Do you have a key? No, I don't. | ¿Tienes una llave? No, no tengo. | Do you have a key? No, I don't. | ¿Tienes una llave? No, no tengo. | Phrase
❓ | Does he have a camera? | ¿Tiene él una cámara? | Tell me, does he have a camera? | Dime, ¿tiene él una cámara? | Question
✅ | Does he have a camera? Yes, he does. | ¿Tiene él una cámara? Sí, él tiene. | Does he have a camera? Yes, he does. | ¿Tiene él una cámara? Sí, él tiene. | Phrase
❌ | Does he have a camera? No, he doesn't. | ¿Tiene él una cámara? No, él no tiene. | Does he have a camera? No, he doesn't. | ¿Tiene él una cámara? No, él no tiene. | Phrase
❓ | Does she have a music player? | ¿Tiene ella un reproductor de música? | Tell me, does she have a music player? | Dime, ¿tiene ella un reproductor de música? | Question
✅ | Does she have a music player? Yes, she does. | ¿Tiene ella un reproductor de música? Sí, ella tiene. | Does she have a music player? Yes, she does. | ¿Tiene ella un reproductor de música? Sí, ella tiene. | Phrase
❌ | Does she have a music player? No, she doesn't. | ¿Tiene ella un reproductor de música? No, ella no tiene. | Does she have a music player? No, she doesn't. | ¿Tiene ella un reproductor de música? No, ella no tiene. | Phrase
❓ | What do they have? | ¿Qué tienen ellos? | Tell me, what do they have? | Dime, ¿qué tienen ellos? | Question
🔑 | What do they have? They have keys. | ¿Qué tienen ellos? Ellos tienen llaves. | What do they have? They have keys. | ¿Qué tienen ellos? Ellos tienen llaves. | Phrase
🪙 | What do they have? They have coins. | ¿Qué tienen ellos? Ellos tienen monedas. | What do they have? They have coins. | ¿Qué tienen ellos? Ellos tienen monedas. | Phrase
📚 | What do they have? They have comic books. | ¿Qué tienen ellos? Ellos tienen cómics. | What do they have? They have comic books. | ¿Qué tienen ellos? Ellos tienen cómics. | Phrase
📷 | What do they have? They have cameras. | ¿Qué tienen ellos? Ellos tienen cámaras. | What do they have? They have cameras. | ¿Qué tienen ellos? Ellos tienen cámaras. | Phrase
❓ | Do they have watches? | ¿Tienen ellos relojes? | Tell me, do they have watches? | Dime, ¿tienen ellos relojes? | Question
✅ | Do they have watches? Yes, they do. | ¿Tienen ellos relojes? Sí, ellos tienen. | Do they have watches? Yes, they do. | ¿Tienen ellos relojes? Sí, ellos tienen. | Phrase
❌ | Do they have watches? No, they don't. | ¿Tienen ellos relojes? No, ellos no tienen. | Do they have watches? No, they don't. | ¿Tienen ellos relojes? No, ellos no tienen. | Phrase
🔍 | I can't find my keys. | No puedo encontrar mis llaves. | Oh no, I can't find my keys. | Oh no, no puedo encontrar mis llaves. | Phrase
🔑 | Look! Here they are. | ¡Mira! Aquí están. | Look! Here they are on the desk. | ¡Mira! Aquí están en el escritorio. | Phrase
🙏 | Thank you! | ¡Gracias! | Thank you very much! | ¡Muchas gracias! | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
⚠️ | Be careful with your things. | Ten cuidado con tus cosas. | Always be careful with your things. | Siempre ten cuidado con tus cosas. | Phrase
    """.trimIndent(), "Basics 2 - List 5")

    private fun getList6(): List<Flashcard> = parseCards("""
🛏️ | Bed | Cama | The bed is soft | La cama es suave | Word
🛁 | Bathtub | Bañera | The bathtub is big | La bañera es grande | Word
🛋️ | Sofa | Sofá | Sit on the sofa | Siéntate en el sofá | Word
🍳 | Stove | Estufa | The stove is hot | La estufa está caliente | Word
💡 | Lamp | Lámpara | Turn on the lamp | Enciende la lámpara | Word
🚰 | Sink | Fregadero / Lavabo | Wash hands at the sink | Lava las manos en el lavabo | Word
📺 | TV | Televisión | Watch TV | Mira la televisión | Word
🧊 | Refrigerator | Refrigerador | Food in the refrigerator | Comida en el refrigerador | Word
❓ | Where's the bed? | ¿Dónde está la cama? | Tell me, where's the bed? | Dime, ¿dónde está la cama? | Question
🛏️ | It's in the bedroom. | Está en el dormitorio. | Look, it's in the bedroom. | Mira, está en el dormitorio. | Phrase
➡️ | Next to | Al lado de | Next to the chair | Al lado de la silla | Word
⏹️ | In front of | Delante de | In front of the desk | Delante del escritorio | Word
🔙 | Behind | Detrás de | Behind the sofa | Detrás del sofá | Word
❓ | Where's the bed? | ¿Dónde está la cama? | Tell me, where's the bed? | Dime, ¿dónde está la cama? | Question
🛁 | It's next to the bathtub. | Está al lado de la bañera. | Look, it's next to the bathtub. | Mira, está al lado de la bañera. | Phrase
🛁 | It's behind the bathtub. | Está detrás de la bañera. | Look, it's behind the bathtub. | Mira, está detrás de la bañera. | Phrase
🛁 | It's in front of the bathtub. | Está delante de la bañera. | Look, it's in front of the bathtub. | Mira, está delante de la bañera. | Phrase
🛋️ | Living room | Sala de estar | Relax in the living room | Relájate en la sala | Word
🍳 | Kitchen | Cocina | Cook in the kitchen | Cocina en la cocina | Word
🛏️ | Bedroom | Dormitorio | Sleep in the bedroom | Duerme en el dormitorio | Word
🛁 | Bathroom | Baño | Clean the bathroom | Limpia el baño | Word
❓ | What's in the living room? | ¿Qué hay en la sala de estar? | Tell me, what's in the living room? | Dime, ¿qué hay en la sala? | Question
🛋️ | There's a sofa. | Hay un sofá. | Look, there's a sofa. | Mira, hay un sofá. | Phrase
🛋️ | There are two sofas. | Hay dos sofás. | Look, there are two sofas. | Mira, hay dos sofás. | Phrase
🔍 | I can't find my book. | No puedo encontrar mi libro. | I can't find my book anywhere. | No encuentro mi libro por ningún lado. | Phrase
🛋️ | Look! It's under the sofa. | ¡Mira! Está debajo del sofá. | Look! It's under the sofa. | ¡Mira! Está debajo del sofá. | Phrase
📖 | Oh, here it is! | ¡Oh, aquí está! | Oh, here it is! | ¡Oh, aquí está! | Phrase
🙏 | Thanks! | ¡Gracias! | Thanks a lot! | ¡Muchas gracias! | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
🧹 | Be neat. | Sé ordenado. | Always be neat. | Siempre sé ordenado. | Phrase
🧽 | Clean up. | Limpia. | Please, clean up. | Por favor, limpia. | Phrase
🪵 | Table | Mesa | On the table | En la mesa | Word
🪑 | Chair | Silla | On the chair | En la silla | Word
🪑 | Desk | Escritorio | On the desk | En el escritorio | Word
📚 | Bookshelf | Estantería | Books on the bookshelf | Libros en la estantería | Word
❓ | Where's the table? | ¿Dónde está la mesa? | Tell me, where's the table? | Dime, ¿dónde está la mesa? | Question
🍳 | It's in the kitchen. | Está en la cocina. | Look, it's in the kitchen. | Mira, está en la cocina. | Phrase
❓ | Where's the chair? | ¿Dónde está la silla? | Tell me, where's the chair? | Dime, ¿dónde está la silla? | Question
🍽️ | It's in the dining room. | Está en el comedor. | Look, it's in the dining room. | Mira, está en el comedor. | Phrase
❓ | Where's the bathtub? | ¿Dónde está la bañera? | Tell me, where's the bathtub? | Dime, ¿dónde está la bañera? | Question
❓ | Where's the sofa? | ¿Dónde está el sofá? | Tell me, where's the sofa? | Dime, ¿dónde está el sofá? | Question
❓ | Where's the stove? | ¿Dónde está la estufa? | Tell me, where's the stove? | Dime, ¿dónde está la estufa? | Question
❓ | Where's the lamp? | ¿Dónde está la lámpara? | Tell me, where's the lamp? | Dime, ¿dónde está la lámpara? | Question
❓ | Where's the sink? | ¿Dónde está el lavabo? | Tell me, where's the sink? | Dime, ¿dónde está el lavabo? | Question
❓ | Where's the TV? | ¿Dónde está la televisión? | Tell me, where's the TV? | Dime, ¿dónde está la televisión? | Question
❓ | Where's the refrigerator? | ¿Dónde está el refrigerador? | Tell me, where's the refrigerator? | Dime, ¿dónde está el refrigerador? | Question
🛋️ | It's in the living room. | Está en la sala de estar. | Look, it's in the living room. | Mira, está en la sala de estar. | Phrase
🍳 | It's in the kitchen. | Está en la cocina. | Look, it's in the kitchen. | Mira, está en la cocina. | Phrase
🛁 | It's in the bathroom. | Está en el baño. | Look, it's in the bathroom. | Mira, está en el baño. | Phrase
🛏️ | Where's the sofa? It's next to the bed. | ¿Dónde está el sofá? Está al lado de la cama. | Where's the sofa? It's next to the bed. | ¿Dónde está el sofá? Está al lado de la cama. | Phrase
🛏️ | Where's the sofa? It's behind the bed. | ¿Dónde está el sofá? Está detrás de la cama. | Where's the sofa? It's behind the bed. | ¿Dónde está el sofá? Está detrás de la cama. | Phrase
🛏️ | Where's the sofa? It's in front of the bed. | ¿Dónde está el sofá? Está delante de la cama. | Where's the sofa? It's in front of the bed. | ¿Dónde está el sofá? Está delante de la cama. | Phrase
🛏️ | Where's the stove? It's next to the bed. | ¿Dónde está la estufa? Está al lado de la cama. | Where's the stove? It's next to the bed. | ¿Dónde está la estufa? Está al lado de la cama. | Phrase
🛏️ | Where's the stove? It's behind the bed. | ¿Dónde está la estufa? Está detrás de la cama. | Where's the stove? It's behind the bed. | ¿Dónde está la estufa? Está detrás de la cama. | Phrase
🛏️ | Where's the stove? It's in front of the bed. | ¿Dónde está la estufa? Está delante de la cama. | Where's the stove? It's in front of the bed. | ¿Dónde está la estufa? Está delante de la cama. | Phrase
🛏️ | Where's the lamp? It's next to the bed. | ¿Dónde está la lámpara? Está al lado de la cama. | Where's the lamp? It's next to the bed. | ¿Dónde está la lámpara? Está al lado de la cama. | Phrase
🛏️ | Where's the lamp? It's behind the bed. | ¿Dónde está la lámpara? Está detrás de la cama. | Where's the lamp? It's behind the bed. | ¿Dónde está la lámpara? Está detrás de la cama. | Phrase
🛏️ | Where's the lamp? It's in front of the bed. | ¿Dónde está la lámpara? Está delante de la cama. | Where's the lamp? It's in front of the bed. | ¿Dónde está la lámpara? Está delante de la cama. | Phrase
🛏️ | Where's the sink? It's next to the bed. | ¿Dónde está el lavabo? Está al lado de la cama. | Where's the sink? It's next to the bed. | ¿Dónde está el lavabo? Está al lado de la cama. | Phrase
🛏️ | Where's the sink? It's behind the bed. | ¿Dónde está el lavabo? Está detrás de la cama. | Where's the sink? It's behind the bed. | ¿Dónde está el lavabo? Está detrás de la cama. | Phrase
🛏️ | Where's the sink? It's in front of the bed. | ¿Dónde está el lavabo? Está delante de la cama. | Where's the sink? It's in front of the bed. | ¿Dónde está el lavabo? Está delante de la cama. | Phrase
🛏️ | Where's the TV? It's next to the bed. | ¿Dónde está la televisión? Está al lado de la cama. | Where's the TV? It's next to the bed. | ¿Dónde está la televisión? Está al lado de la cama. | Phrase
🛏️ | Where's the TV? It's behind the bed. | ¿Dónde está la televisión? Está detrás de la cama. | Where's the TV? It's behind the bed. | ¿Dónde está la televisión? Está detrás de la cama. | Phrase
🛏️ | Where's the TV? It's in front of the bed. | ¿Dónde está la televisión? Está delante de la cama. | Where's the TV? It's in front of the bed. | ¿Dónde está la televisión? Está delante de la cama. | Phrase
🛏️ | Where's the refrigerator? It's next to the bed. | ¿Dónde está el refrigerador? Está al lado de la cama. | Where's the refrigerator? It's next to the bed. | ¿Dónde está el refrigerador? Está al lado de la cama. | Phrase
🛏️ | Where's the refrigerator? It's behind the bed. | ¿Dónde está el refrigerador? Está detrás de la cama. | Where's the refrigerator? It's behind the bed. | ¿Dónde está el refrigerador? Está detrás de la cama. | Phrase
🛏️ | Where's the refrigerator? It's in front of the bed. | ¿Dónde está el refrigerador? Está delante de la cama. | Where's the refrigerator? It's in front of the bed. | ¿Dónde está el refrigerador? Está delante de la cama. | Phrase
❓ | What's in the kitchen? | ¿Qué hay en la cocina? | Tell me, what's in the kitchen? | Dime, ¿qué hay en la cocina? | Question
❓ | What's in the bedroom? | ¿Qué hay en el dormitorio? | Tell me, what's in the bedroom? | Dime, ¿qué hay en el dormitorio? | Question
❓ | What's in the bathroom? | ¿Qué hay en el baño? | Tell me, what's in the bathroom? | Dime, ¿qué hay en el baño? | Question
📺 | What's in the living room? There's a TV. | ¿Qué hay en la sala? Hay una televisión. | What's in the living room? There's a TV. | ¿Qué hay en la sala? Hay una televisión. | Phrase
📺 | What's in the living room? There are two TVs. | ¿Qué hay en la sala? Hay dos televisiones. | What's in the living room? There are two TVs. | ¿Qué hay en la sala? Hay dos televisiones. | Phrase
🍳 | What's in the kitchen? There's a stove. | ¿Qué hay en la cocina? Hay una estufa. | What's in the kitchen? There's a stove. | ¿Qué hay en la cocina? Hay una estufa. | Phrase
🍳 | What's in the kitchen? There are two stoves. | ¿Qué hay en la cocina? Hay dos estufas. | What's in the kitchen? There are two stoves. | ¿Qué hay en la cocina? Hay dos estufas. | Phrase
🧊 | What's in the kitchen? There's a refrigerator. | ¿Qué hay en la cocina? Hay un refrigerador. | What's in the kitchen? There's a refrigerator. | ¿Qué hay en la cocina? Hay un refrigerador. | Phrase
🧊 | What's in the kitchen? There are two refrigerators. | ¿Qué hay en la cocina? Hay dos refrigeradores. | What's in the kitchen? There are two refrigerators. | ¿Qué hay en la cocina? Hay dos refrigeradores. | Phrase
🚰 | What's in the kitchen? There's a sink. | ¿Qué hay en la cocina? Hay un fregadero. | What's in the kitchen? There's a sink. | ¿Qué hay en la cocina? Hay un fregadero. | Phrase
🚰 | What's in the kitchen? There are two sinks. | ¿Qué hay en la cocina? Hay dos fregaderos. | What's in the kitchen? There are two sinks. | ¿Qué hay en la cocina? Hay dos fregaderos. | Phrase
🛏️ | What's in the bedroom? There's a bed. | ¿Qué hay en el dormitorio? Hay una cama. | What's in the bedroom? There's a bed. | ¿Qué hay en el dormitorio? Hay una cama. | Phrase
🛏️ | What's in the bedroom? There are two beds. | ¿Qué hay en el dormitorio? Hay dos camas. | What's in the bedroom? There are two beds. | ¿Qué hay en el dormitorio? Hay dos camas. | Phrase
🛁 | What's in the bathroom? There's a bathtub. | ¿Qué hay en el baño? Hay una bañera. | What's in the bathroom? There's a bathtub. | ¿Qué hay en el baño? Hay una bañera. | Phrase
🛁 | What's in the bathroom? There are two bathtubs. | ¿Qué hay en el baño? Hay dos bañeras. | What's in the bathroom? There are two bathtubs. | ¿Qué hay en el baño? Hay dos bañeras. | Phrase
🚰 | What's in the bathroom? There's a sink. | ¿Qué hay en el baño? Hay un lavabo. | What's in the bathroom? There's a sink. | ¿Qué hay en el baño? Hay un lavabo. | Phrase
🚰 | What's in the bathroom? There are two sinks. | ¿Qué hay en el baño? Hay dos lavabos. | What's in the bathroom? There are two sinks. | ¿Qué hay en el baño? Hay dos lavabos. | Phrase
❓ | Where's the desk? | ¿Dónde está el escritorio? | Tell me, where's the desk? | Dime, ¿dónde está el escritorio? | Question
❓ | Where's the bookshelf? | ¿Dónde está la estantería? | Tell me, where's the bookshelf? | Dime, ¿dónde está la estantería? | Question
🛏️ | It's in the bedroom. | Está en el dormitorio. | Look, it's in the bedroom. | Mira, está en el dormitorio. | Phrase
🛋️ | It's in the living room. | Está en la sala de estar. | Look, it's in the living room. | Mira, está en la sala de estar. | Phrase
🪵 | Where's the table? It's in the living room. | ¿Dónde está la mesa? Está en la sala. | Where's the table? It's in the living room. | ¿Dónde está la mesa? Está en la sala. | Phrase
🪑 | Where's the chair? It's in the kitchen. | ¿Dónde está la silla? Está en la cocina. | Where's the chair? It's in the kitchen. | ¿Dónde está la silla? Está en la cocina. | Phrase
🪑 | Where's the desk? It's in the bedroom. | ¿Dónde está el escritorio? Está en el dormitorio. | Where's the desk? It's in the bedroom. | ¿Dónde está el escritorio? Está en el dormitorio. | Phrase
📚 | Where's the bookshelf? It's in the living room. | ¿Dónde está la estantería? Está en la sala. | Where's the bookshelf? It's in the living room. | ¿Dónde está la estantería? Está en la sala. | Phrase
    """.trimIndent(), "Basics 2 - List 6")

    private fun getList7(): List<Flashcard> = parseCards("""
🕐 | One o'clock | La una en punto | It is one o'clock | Es la una en punto | Phrase
🕑 | Two o'clock | Las dos en punto | It is two o'clock | Son las dos en punto | Phrase
🕒 | Three o'clock | Las tres en punto | It is three o'clock | Son las tres en punto | Phrase
🕓 | Four o'clock | Las cuatro en punto | It is four o'clock | Son las cuatro en punto | Phrase
🕔 | Five o'clock | Las cinco en punto | It is five o'clock | Son las cinco en punto | Phrase
🕕 | Six o'clock | Las seis en punto | It is six o'clock | Son las seis en punto | Phrase
🕖 | Seven o'clock | Las siete en punto | It is seven o'clock | Son las siete en punto | Phrase
🕗 | Eight o'clock | Las ocho en punto | It is eight o'clock | Son las ocho en punto | Phrase
🕘 | Nine o'clock | Las nueve en punto | It is nine o'clock | Son las nueve en punto | Phrase
🕙 | Ten o'clock | Las diez en punto | It is ten o'clock | Son las diez en punto | Phrase
🕚 | Eleven o'clock | Las once en punto | It is eleven o'clock | Son las once en punto | Phrase
🕛 | Twelve o'clock | Las doce en punto | It is twelve o'clock | Son las doce en punto | Phrase
❓ | What time is it? | ¿Qué hora es? | Tell me, what time is it? | Dime, ¿qué hora es? | Question
🕐 | It's one o'clock. | Es la una en punto. | Look, it's one o'clock. | Mira, es la una en punto. | Phrase
🥞 | It's time for breakfast. | Es hora de desayunar. | Come, it's time for breakfast. | Ven, es hora de desayunar. | Phrase
🥪 | It's time for lunch. | Es hora de almorzar. | Come, it's time for lunch. | Ven, es hora de almorzar. | Phrase
🍲 | It's time for dinner. | Es hora de cenar. | Come, it's time for dinner. | Ven, es hora de cenar. | Phrase
⏰ | Wake up | Despertarse | I wake up early | Me despierto temprano | Phrase
🧼 | Wash my face | Lavar mi cara | I wash my face | Lavo mi cara | Phrase
🪥 | Brush my teeth | Cepillar mis dientes | I brush my teeth | Cepillo mis dientes | Phrase
👕 | Get dressed | Vestirse | Get dressed quickly | Vístete rápido | Phrase
🥞 | Eat breakfast | Desayunar | I eat breakfast | Tomo el desayuno | Phrase
🪮 | Brush my hair | Cepillar mi cabello | I brush my hair | Cepillo mi cabello | Phrase
🎒 | Go to school | Ir a la escuela | I go to school | Voy a la escuela | Phrase
❓ | What do you do in the morning? | ¿Qué haces por la mañana? | Tell me, what do you do in the morning? | Dime, ¿qué haces por la mañana? | Question
⏰ | I wake up. | Me despierto. | Every day, I wake up. | Todos los días, me despierto. | Phrase
❓ | What does he do in the morning? | ¿Qué hace él por la mañana? | Tell me, what does he do in the morning? | Dime, ¿qué hace él por la mañana? | Question
⏰ | He wakes up. | Él se despierta. | Every day, he wakes up. | Todos los días, él se despierta. | Phrase
❓ | What does she do in the morning? | ¿Qué hace ella por la mañana? | Tell me, what does she do in the morning? | Dime, ¿qué hace ella por la mañana? | Question
⏰ | She wakes up. | Ella se despierta. | Every day, she wakes up. | Todos los días, ella se despierta. | Phrase
🏃 | Hurry up! | ¡Apúrate! | Come on, hurry up! | Vamos, ¡apúrate! | Phrase
⏳ | Wait a minute. | Espera un minuto. | Please, wait a minute. | Por favor, espera un minuto. | Phrase
👍 | OK, I'm ready. | Está bien, estoy listo. | OK, I'm ready now. | Está bien, ya estoy listo. | Phrase
👏 | Good job! | ¡Buen trabajo! | Wow, good job! | ¡Vaya, buen trabajo! | Phrase
⏱️ | Be on time. | Sé puntual. | Always be on time. | Siempre sé puntual. | Phrase
🥪 | Eat lunch | Almorzar | I eat lunch at noon | Almuerzo al mediodía | Phrase
🍲 | Eat dinner | Cenar | I eat dinner at night | Ceno por la noche | Phrase
🛁 | Take a bath | Tomar un baño | I take a bath | Tomo un baño | Phrase
🛏️ | Go to bed | Ir a la cama | I go to bed early | Me voy a la cama temprano | Phrase
❓ | What do you do in the afternoon? | ¿Qué haces por la tarde? | Tell me, what do you do in the afternoon? | Dime, ¿qué haces por la tarde? | Question
🥪 | I eat lunch. | Almuerzo. | Every day, I eat lunch. | Todos los días, almuerzo. | Phrase
❓ | What do you do in the evening? | ¿Qué haces por la noche? | Tell me, what do you do in the evening? | Dime, ¿qué haces por la noche? | Question
🍲 | I eat dinner. | Ceno. | Every day, I eat dinner. | Todos los días, ceno. | Phrase
❓ | What does he do in the afternoon? | ¿Qué hace él por la tarde? | Tell me, what does he do in the afternoon? | Dime, ¿qué hace él por la tarde? | Question
🥪 | He eats lunch. | Él almuerza. | Every day, he eats lunch. | Todos los días, él almuerza. | Phrase
❓ | What does he do in the evening? | ¿Qué hace él por la noche? | Tell me, what does he do in the evening? | Dime, ¿qué hace él por la noche? | Question
🍲 | He eats dinner. | Él cena. | Every day, he eats dinner. | Todos los días, él cena. | Phrase
❓ | What does she do in the afternoon? | ¿Qué hace ella por la tarde? | Tell me, what does she do in the afternoon? | Dime, ¿qué hace ella por la tarde? | Question
🥪 | She eats lunch. | Ella almuerza. | Every day, she eats lunch. | Todos los días, ella almuerza. | Phrase
❓ | What does she do in the evening? | ¿Qué hace ella por la noche? | Tell me, what does she do in the evening? | Dime, ¿qué hace ella por la noche? | Question
🍲 | She eats dinner. | Ella cena. | Every day, she eats dinner. | Todos los días, ella cena. | Phrase
🕑 | It's two o'clock. | Son las dos en punto. | Look, it's two o'clock. | Mira, son las dos en punto. | Phrase
🕒 | It's three o'clock. | Son las tres en punto. | Look, it's three o'clock. | Mira, son las tres en punto. | Phrase
🕓 | It's four o'clock. | Son las cuatro en punto. | Look, it's four o'clock. | Mira, son las cuatro en punto. | Phrase
🕔 | It's five o'clock. | Son las cinco en punto. | Look, it's five o'clock. | Mira, son las cinco en punto. | Phrase
🕕 | It's six o'clock. | Son las seis en punto. | Look, it's six o'clock. | Mira, son las seis en punto. | Phrase
🕖 | It's seven o'clock. | Son las siete en punto. | Look, it's seven o'clock. | Mira, son las siete en punto. | Phrase
🕗 | It's eight o'clock. | Son las ocho en punto. | Look, it's eight o'clock. | Mira, son las ocho en punto. | Phrase
🕘 | It's nine o'clock. | Son las nueve en punto. | Look, it's nine o'clock. | Mira, son las nueve en punto. | Phrase
🕙 | It's ten o'clock. | Son las diez en punto. | Look, it's ten o'clock. | Mira, son las diez en punto. | Phrase
🕚 | It's eleven o'clock. | Son las once en punto. | Look, it's eleven o'clock. | Mira, son las once en punto. | Phrase
🕛 | It's twelve o'clock. | Son las doce en punto. | Look, it's twelve o'clock. | Mira, son las doce en punto. | Phrase
🧼 | What do you do in the morning? I wash my face. | ¿Qué haces por la mañana? Lavo mi cara. | What do you do in the morning? I wash my face. | ¿Qué haces por la mañana? Lavo mi cara. | Phrase
🪥 | What do you do in the morning? I brush my teeth. | ¿Qué haces por la mañana? Cepillo mis dientes. | What do you do in the morning? I brush my teeth. | ¿Qué haces por la mañana? Cepillo mis dientes. | Phrase
👕 | What do you do in the morning? I get dressed. | ¿Qué haces por la mañana? Me visto. | What do you do in the morning? I get dressed. | ¿Qué haces por la mañana? Me visto. | Phrase
🥞 | What do you do in the morning? I eat breakfast. | ¿Qué haces por la mañana? Tomo el desayuno. | What do you do in the morning? I eat breakfast. | ¿Qué haces por la mañana? Tomo el desayuno. | Phrase
🪮 | What do you do in the morning? I brush my hair. | ¿Qué haces por la mañana? Cepillo mi cabello. | What do you do in the morning? I brush my hair. | ¿Qué haces por la mañana? Cepillo mi cabello. | Phrase
🎒 | What do you do in the morning? I go to school. | ¿Qué haces por la mañana? Voy a la escuela. | What do you do in the morning? I go to school. | ¿Qué haces por la mañana? Voy a la escuela. | Phrase
🧼 | What does he do in the morning? He washes his face. | ¿Qué hace él por la mañana? Él se lava la cara (su cara - se refiere a él). | What does he do in the morning? He washes his face. | ¿Qué hace él por la mañana? Él se lava la cara. | Phrase
🪥 | What does he do in the morning? He brushes his teeth. | ¿Qué hace él por la mañana? Él se cepilla los dientes (sus dientes - se refiere a él). | What does he do in the morning? He brushes his teeth. | ¿Qué hace él por la mañana? Él se cepilla los dientes. | Phrase
👕 | What does he do in the morning? He gets dressed. | ¿Qué hace él por la mañana? Él se viste. | What does he do in the morning? He gets dressed. | ¿Qué hace él por la mañana? Él se viste. | Phrase
🥞 | What does he do in the morning? He eats breakfast. | ¿Qué hace él por la mañana? Él desayuna. | What does he do in the morning? He eats breakfast. | ¿Qué hace él por la mañana? Él desayuna. | Phrase
🪮 | What does he do in the morning? He brushes his hair. | ¿Qué hace él por la mañana? Él se cepilla el cabello (su cabello - se refiere a él). | What does he do in the morning? He brushes his hair. | ¿Qué hace él por la mañana? Él se cepilla el cabello. | Phrase
🎒 | What does he do in the morning? He goes to school. | ¿Qué hace él por la mañana? Él va a la escuela. | What does he do in the morning? He goes to school. | ¿Qué hace él por la mañana? Él va a la escuela. | Phrase
🧼 | What does she do in the morning? She washes her face. | ¿Qué hace ella por la mañana? Ella se lava la cara (su cara - se refiere a ella). | What does she do in the morning? She washes her face. | ¿Qué hace ella por la mañana? Ella se lava la cara. | Phrase
🪥 | What does she do in the morning? She brushes her teeth. | ¿Qué hace ella por la mañana? Ella se cepilla los dientes (sus dientes - se refiere a ella). | What does she do in the morning? She brushes her teeth. | ¿Qué hace ella por la mañana? Ella se cepilla los dientes. | Phrase
👕 | What does she do in the morning? She gets dressed. | ¿Qué hace ella por la mañana? Ella se viste. | What does she do in the morning? She gets dressed. | ¿Qué hace ella por la mañana? Ella se viste. | Phrase
🥞 | What does she do in the morning? She eats breakfast. | ¿Qué hace ella por la mañana? Ella desayuna. | What does she do in the morning? She eats breakfast. | ¿Qué hace ella por la mañana? Ella desayuna. | Phrase
🪮 | What does she do in the morning? She brushes her hair. | ¿Qué hace ella por la mañana? Ella se cepilla el cabello (su cabello - se refiere a ella). | What does she do in the morning? She brushes her hair. | ¿Qué hace ella por la mañana? Ella se cepilla el cabello. | Phrase
🎒 | What does she do in the morning? She goes to school. | ¿Qué hace ella por la mañana? Ella va a la escuela. | What does she do in the morning? She goes to school. | ¿Qué hace ella por la mañana? Ella va a la escuela. | Phrase
🛁 | What do you do in the evening? I take a bath. | ¿Qué haces por la noche? Tomo un baño. | What do you do in the evening? I take a bath. | ¿Qué haces por la noche? Tomo un baño. | Phrase
🛏️ | What do you do in the evening? I go to bed. | ¿Qué haces por la noche? Me voy a la cama. | What do you do in the evening? I go to bed. | ¿Qué haces por la noche? Me voy a la cama. | Phrase
🛁 | What does he do in the evening? He takes a bath. | ¿Qué hace él por la noche? Él toma un baño. | What does he do in the evening? He takes a bath. | ¿Qué hace él por la noche? Él toma un baño. | Phrase
🛏️ | What does he do in the evening? He goes to bed. | ¿Qué hace él por la noche? Él va a la cama. | What does he do in the evening? He goes to bed. | ¿Qué hace él por la noche? Él va a la cama. | Phrase
🛁 | What does she do in the evening? She takes a bath. | ¿Qué hace ella por la noche? Ella toma un baño. | What does she do in the evening? She takes a bath. | ¿Qué hace ella por la noche? Ella toma un baño. | Phrase
🛏️ | What does she do in the evening? She goes to bed. | ¿Qué hace ella por la noche? Ella va a la cama. | What does she do in the evening? She goes to bed. | ¿Qué hace ella por la noche? Ella va a la cama. | Phrase
    """.trimIndent(), "Basics 2 - List 7")

    private fun getList8(): List<Flashcard> = parseCards("""
💃 | Dancing | Bailando | He is dancing | Él está bailando | Word
🏊 | Swimming | Nadando | She is swimming | Ella está nadando | Word
😴 | Sleeping | Durmiendo | The dog is sleeping | El perro está durmiendo | Word
🎨 | Drawing | Dibujando | I am drawing | Estoy dibujando | Word
🎤 | Singing | Cantando | They are singing | Ellos están cantando | Word
🏃 | Running | Corriendo | He is running fast | Él está corriendo rápido | Word
📖 | Reading | Leyendo | She is reading a book | Ella está leyendo un libro | Word
🚶 | Walking | Caminando | I am walking | Estoy caminando | Word
❓ | What's he doing? | ¿Qué está haciendo él? | Tell me, what's he doing? | Dime, ¿qué está haciendo él? | Question
💃 | He's dancing. | Él está bailando. | Look, he's dancing. | Mira, él está bailando. | Phrase
❓ | What's she doing? | ¿Qué está haciendo ella? | Tell me, what's she doing? | Dime, ¿qué está haciendo ella? | Question
💃 | She's dancing. | Ella está bailando. | Look, she's dancing. | Mira, ella está bailando. | Phrase
❓ | What are they doing? | ¿Qué están haciendo ellos? | Tell me, what are they doing? | Dime, ¿qué están haciendo ellos? | Question
💃 | They're dancing. | Ellos están bailando. | Look, they're dancing. | Mira, ellos están bailando. | Phrase
❓ | Is he dancing? | ¿Está él bailando? | Tell me, is he dancing? | Dime, ¿está él bailando? | Question
✅ | Yes, he is. | Sí, él está. | Yes, he is dancing. | Sí, él está bailando. | Phrase
❌ | No, he isn't. | No, él no está. | No, he isn't dancing. | No, él no está bailando. | Phrase
❓ | Is she dancing? | ¿Está ella bailando? | Tell me, is she dancing? | Dime, ¿está ella bailando? | Question
✅ | Yes, she is. | Sí, ella está. | Yes, she is dancing. | Sí, ella está bailando. | Phrase
❌ | No, she isn't. | No, ella no está. | No, she isn't dancing. | No, ella no está bailando. | Phrase
❓ | Are they dancing? | ¿Están ellos bailando? | Tell me, are they dancing? | Dime, ¿están ellos bailando? | Question
✅ | Yes, they are. | Sí, ellos están. | Yes, they are dancing. | Sí, ellos están bailando. | Phrase
❌ | No, they aren't. | No, ellos no están. | No, they aren't dancing. | No, ellos no están bailando. | Phrase
💪 | I can do this! | ¡Puedo hacer esto! | Yes, I can do this! | ¡Sí, puedo hacer esto! | Phrase
🎉 | That's great! | ¡Eso es genial! | Wow, that's great! | ¡Vaya, eso es genial! | Phrase
🔄 | Keep trying! | ¡Sigue intentándolo! | Come on, keep trying! | Vamos, ¡sigue intentándolo! | Phrase
✨ | You can do it! | ¡Tú puedes hacerlo! | Yes, you can do it! | ¡Sí, tú puedes hacerlo! | Phrase
🧘 | Be patient. | Sé paciente. | Always be patient. | Siempre sé paciente. | Phrase
🎸 | Play the guitar | Tocar la guitarra | He can play the guitar | Él puede tocar la guitarra | Phrase
🎹 | Play the piano | Tocar el piano | She plays the piano | Ella toca el piano | Phrase
🥁 | Play the drums | Tocar la batería | I play the drums | Toco la batería | Phrase
🎻 | Play the violin | Tocar el violín | Play the violin nicely | Toca el violín bien | Phrase
🎸 | He can play the guitar. | Él puede tocar la guitarra. | Look, he can play the guitar. | Mira, él puede tocar la guitarra. | Phrase
🎸 | She can play the guitar. | Ella puede tocar la guitarra. | Look, she can play the guitar. | Mira, ella puede tocar la guitarra. | Phrase
🎸 | They can play the guitar. | Ellos pueden tocar la guitarra. | Look, they can play the guitar. | Mira, ellos pueden tocar la guitarra. | Phrase
❓ | Can he play the guitar? | ¿Puede él tocar la guitarra? | Tell me, can he play the guitar? | Dime, ¿puede él tocar la guitarra? | Question
✅ | Yes, he can. | Sí, él puede. | Yes, he can play it. | Sí, él puede tocarla. | Phrase
❌ | No, he can't. | No, él no puede. | No, he can't play it. | No, él no puede tocarla. | Phrase
❓ | Can she play the guitar? | ¿Puede ella tocar la guitarra? | Tell me, can she play the guitar? | Dime, ¿puede ella tocar la guitarra? | Question
✅ | Yes, she can. | Sí, ella puede. | Yes, she can play it. | Sí, ella puede tocarla. | Phrase
❌ | No, she can't. | No, ella no puede. | No, she can't play it. | No, ella no puede tocarla. | Phrase
❓ | Can they play the guitar? | ¿Pueden ellos tocar la guitarra? | Tell me, can they play the guitar? | Dime, ¿pueden ellos tocar la guitarra? | Question
✅ | Yes, they can. | Sí, ellos pueden. | Yes, they can play it. | Sí, ellos pueden tocarla. | Phrase
❌ | No, they can't. | No, ellos no pueden. | No, they can't play it. | No, ellos no pueden tocarla. | Phrase
🏊 | What's he doing? He's swimming. | ¿Qué está haciendo él? Él está nadando. | What's he doing? He's swimming. | ¿Qué está haciendo él? Él está nadando. | Phrase
😴 | What's he doing? He's sleeping. | ¿Qué está haciendo él? Él está durmiendo. | What's he doing? He's sleeping. | ¿Qué está haciendo él? Él está durmiendo. | Phrase
🎨 | What's he doing? He's drawing. | ¿Qué está haciendo él? Él está dibujando. | What's he doing? He's drawing. | ¿Qué está haciendo él? Él está dibujando. | Phrase
🎤 | What's he doing? He's singing. | ¿Qué está haciendo él? Él está cantando. | What's he doing? He's singing. | ¿Qué está haciendo él? Él está cantando. | Phrase
🏃 | What's he doing? He's running. | ¿Qué está haciendo él? Él está corriendo. | What's he doing? He's running. | ¿Qué está haciendo él? Él está corriendo. | Phrase
📖 | What's he doing? He's reading. | ¿Qué está haciendo él? Él está leyendo. | What's he doing? He's reading. | ¿Qué está haciendo él? Él está leyendo. | Phrase
🚶 | What's he doing? He's walking. | ¿Qué está haciendo él? Él está caminando. | What's he doing? He's walking. | ¿Qué está haciendo él? Él está caminando. | Phrase
🏊 | What's she doing? She's swimming. | ¿Qué está haciendo ella? Ella está nadando. | What's she doing? She's swimming. | ¿Qué está haciendo ella? Ella está nadando. | Phrase
😴 | What's she doing? She's sleeping. | ¿Qué está haciendo ella? Ella está durmiendo. | What's she doing? She's sleeping. | ¿Qué está haciendo ella? Ella está durmiendo. | Phrase
🎨 | What's she doing? She's drawing. | ¿Qué está haciendo ella? Ella está dibujando. | What's she doing? She's drawing. | ¿Qué está haciendo ella? Ella está dibujando. | Phrase
🎤 | What's she doing? She's singing. | ¿Qué está haciendo ella? Ella está cantando. | What's she doing? She's singing. | ¿Qué está haciendo ella? Ella está cantando. | Phrase
🏃 | What's she doing? She's running. | ¿Qué está haciendo ella? Ella está corriendo. | What's she doing? She's running. | ¿Qué está haciendo ella? Ella está corriendo. | Phrase
📖 | What's she doing? She's reading. | ¿Qué está haciendo ella? Ella está leyendo. | What's she doing? She's reading. | ¿Qué está haciendo ella? Ella está leyendo. | Phrase
🚶 | What's she doing? She's walking. | ¿Qué está haciendo ella? Ella está caminando. | What's she doing? She's walking. | ¿Qué está haciendo ella? Ella está caminando. | Phrase
🏊 | What are they doing? They're swimming. | ¿Qué están haciendo ellos? Ellos están nadando. | What are they doing? They're swimming. | ¿Qué están haciendo ellos? Ellos están nadando. | Phrase
😴 | What are they doing? They're sleeping. | ¿Qué están haciendo ellos? Ellos están durmiendo. | What are they doing? They're sleeping. | ¿Qué están haciendo ellos? Ellos están durmiendo. | Phrase
🎨 | What are they doing? They're drawing. | ¿Qué están haciendo ellos? Ellos están dibujando. | What are they doing? They're drawing. | ¿Qué están haciendo ellos? Ellos están dibujando. | Phrase
🎤 | What are they doing? They're singing. | ¿Qué están haciendo ellos? Ellos están cantando. | What are they doing? They're singing. | ¿Qué están haciendo ellos? Ellos están cantando. | Phrase
🏃 | What are they doing? They're running. | ¿Qué están haciendo ellos? Ellos están corriendo. | What are they doing? They're running. | ¿Qué están haciendo ellos? Ellos están corriendo. | Phrase
📖 | What are they doing? They're reading. | ¿Qué están haciendo ellos? Ellos están leyendo. | What are they doing? They're reading. | ¿Qué están haciendo ellos? Ellos están leyendo. | Phrase
🚶 | What are they doing? They're walking. | ¿Qué están haciendo ellos? Ellos están caminando. | What are they doing? They're walking. | ¿Qué están haciendo ellos? Ellos están caminando. | Phrase
❓ | Is he swimming? | ¿Está él nadando? | Tell me, is he swimming? | Dime, ¿está él nadando? | Question
❓ | Is he sleeping? | ¿Está él durmiendo? | Tell me, is he sleeping? | Dime, ¿está él durmiendo? | Question
❓ | Is he drawing? | ¿Está él dibujando? | Tell me, is he drawing? | Dime, ¿está él dibujando? | Question
❓ | Is he singing? | ¿Está él cantando? | Tell me, is he singing? | Dime, ¿está él cantando? | Question
❓ | Is he running? | ¿Está él corriendo? | Tell me, is he running? | Dime, ¿está él corriendo? | Question
❓ | Is he reading? | ¿Está él leyendo? | Tell me, is he reading? | Dime, ¿está él leyendo? | Question
❓ | Is he walking? | ¿Está él caminando? | Tell me, is he walking? | Dime, ¿está él caminando? | Question
❓ | Is she swimming? | ¿Está ella nadando? | Tell me, is she swimming? | Dime, ¿está ella nadando? | Question
❓ | Is she sleeping? | ¿Está ella durmiendo? | Tell me, is she sleeping? | Dime, ¿está ella durmiendo? | Question
❓ | Is she drawing? | ¿Está ella dibujando? | Tell me, is she drawing? | Dime, ¿está ella dibujando? | Question
❓ | Is she singing? | ¿Está ella cantando? | Tell me, is she singing? | Dime, ¿está ella cantando? | Question
❓ | Is she running? | ¿Está ella corriendo? | Tell me, is she running? | Dime, ¿está ella corriendo? | Question
❓ | Is she reading? | ¿Está ella leyendo? | Tell me, is she reading? | Dime, ¿está ella leyendo? | Question
❓ | Is she walking? | ¿Está ella caminando? | Tell me, is she walking? | Dime, ¿está ella caminando? | Question
❓ | Are they swimming? | ¿Están ellos nadando? | Tell me, are they swimming? | Dime, ¿están ellos nadando? | Question
❓ | Are they sleeping? | ¿Están ellos durmiendo? | Tell me, are they sleeping? | Dime, ¿están ellos durmiendo? | Question
❓ | Are they drawing? | ¿Están ellos dibujando? | Tell me, are they drawing? | Dime, ¿están ellos dibujando? | Question
❓ | Are they singing? | ¿Están ellos cantando? | Tell me, are they singing? | Dime, ¿están ellos cantando? | Question
❓ | Are they running? | ¿Están ellos corriendo? | Tell me, are they running? | Dime, ¿están ellos corriendo? | Question
❓ | Are they reading? | ¿Están ellos leyendo? | Tell me, are they reading? | Dime, ¿están ellos leyendo? | Question
❓ | Are they walking? | ¿Están ellos caminando? | Tell me, are they walking? | Dime, ¿están ellos caminando? | Question
    """.trimIndent(), "Basics 2 - List 8")
}
