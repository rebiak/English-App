package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object BasicsBooklet4 {
    const val FOLDER_NAME = "Basics 4"

    val categoryNames: List<String> = (1..8).map { "Basics 4 - List $it" }

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
⚖️ | Review of Level 3 | Repaso del Nivel 3 | Let's review Level 3 | Repasemos el Nivel 3 | Phrase
💬 | Talking about measurement | Hablando sobre medidas | We are talking about measurement | Estamos hablando sobre medidas | Phrase
⚖️ | There are one thousand grams in a kilogram | Hay mil gramos en un kilogramo | Look, there are one thousand grams in a kilogram | Mira, hay mil gramos en un kilogramo | Phrase
🧗 | Climb | Escalar | I like to climb | Me gusta escalar | Word
🥾 | Hike | Hacer senderismo | We hike in the park | Hacemos senderismo en el parque | Word
🛶 | Canoe | Canotaje / Andar en canoa | I ride a canoe | Ando en canoa | Word
🐟 | Fish | Pescar | They fish in the river | Pescan en el río | Word
🍔 | Grill hamburgers | Asar hamburguesas | We grill hamburgers | Asamos hamburguesas | Phrase
🐦 | Watch birds | Observar aves | I watch birds | Observo aves | Phrase
🤔 | What does he like doing? | ¿Qué le gusta hacer a él? | Tell me, what does he like doing? | Dime, ¿qué le gusta hacer a él? | Question
🤔 | What does she like doing? | ¿Qué le gusta hacer a ella? | Tell me, what does she like doing? | Dime, ¿qué le gusta hacer a ella? | Question
🧗 | What does he like doing? He likes climbing. | ¿Qué le gusta hacer a él? A él le gusta escalar. | What does he like doing? He likes climbing. | ¿Qué le gusta hacer a él? A él le gusta escalar. | Phrase
🧗 | What does she like doing? She likes climbing. | ¿Qué le gusta hacer a ella? A ella le gusta escalar. | What does she like doing? She likes climbing. | ¿Qué le gusta hacer a ella? A ella le gusta escalar. | Phrase
🤔 | Does he like climbing? | ¿Le gusta escalar a él? | Tell me, does he like climbing? | Dime, ¿le gusta escalar a él? | Question
🤔 | Does she like climbing? | ¿Le gusta escalar a ella? | Tell me, does she like climbing? | Dime, ¿le gusta escalar a ella? | Question
👍 | Does he like climbing? Yes, he likes it a lot. | ¿Le gusta escalar a él? Sí, le gusta mucho. | Does he like climbing? Yes, he likes it a lot. | ¿Le gusta escalar a él? Sí, le gusta mucho. | Phrase
👍 | Does she like climbing? Yes, she likes it a lot. | ¿Le gusta escalar a ella? Sí, le gusta mucho. | Does she like climbing? Yes, she likes it a lot. | ¿Le gusta escalar a ella? Sí, le gusta mucho. | Phrase
👎 | Does he like climbing? No, he doesn't like it at all. | ¿Le gusta escalar a él? No, no le gusta nada. | Does he like climbing? No, he doesn't like it at all. | ¿Le gusta escalar a él? No, no le gusta nada. | Phrase
👎 | Does she like climbing? No, she doesn't like it at all. | ¿Le gusta escalar a ella? No, no le gusta nada. | Does she like climbing? No, she doesn't like it at all. | ¿Le gusta escalar a ella? No, no le gusta nada. | Phrase
⛷️ | Ski | Esquiar | I want to ski | Quiero esquiar | Word
🏂 | Snowboard | Hacer snowboard / Deslizarse en tabla | He likes to snowboard | Le gusta hacer snowboard | Word
⛸️ | Ice skate | Patinar sobre hielo | We ice skate in winter | Patinamos sobre hielo en invierno | Word
🛼 | In-line skate | Patinar en línea | They in-line skate in the park | Patinan en línea en el parque | Word
🛹 | Skateboard | Andar en monopatín | I skateboard outside | Ando en monopatín afuera | Word
🏄 | Surf | Surfear | The boy can surf | El chico puede surfear | Word
⛷️ | He's good at skiing. | Él es bueno para esquiar. | Look, he's good at skiing. | Mira, él es bueno para esquiar. | Phrase
⛷️ | She's good at skiing. | Ella es buena para esquiar. | Look, she's good at skiing. | Mira, ella es buena para esquiar. | Phrase
⛷️ | He isn't good at skiing. | Él no es bueno para esquiar. | No, he isn't good at skiing. | No, él no es bueno para esquiar. | Phrase
⛷️ | She isn't good at skiing. | Ella no es buena para esquiar. | No, she isn't good at skiing. | No, ella no es buena para esquiar. | Phrase
🤔 | Is he good at skiing? | ¿Es él bueno para esquiar? | Tell me, is he good at skiing? | Dime, ¿es él bueno para esquiar? | Question
🤔 | Is she good at skiing? | ¿Es ella buena para esquiar? | Tell me, is she good at skiing? | Dime, ¿es ella buena para esquiar? | Question
⭐ | Is he good at skiing? Yes, he's very good at it. | ¿Es él bueno para esquiar? Sí, es muy bueno en ello. | Is he good at skiing? Yes, he's very good at it. | ¿Es él bueno para esquiar? Sí, es muy bueno en ello. | Phrase
⭐ | Is she good at skiing? Yes, she's very good at it. | ¿Es ella buena para esquiar? Sí, es muy buena en ello. | Is she good at skiing? Yes, she's very good at it. | ¿Es ella buena para esquiar? Sí, es muy buena en ello. | Phrase
⚠️ | Is he good at skiing? No, he's not very good at it. | ¿Es él bueno para esquiar? No, no es muy bueno en ello. | Is he good at skiing? No, he's not very good at it. | ¿Es él bueno para esquiar? No, no es muy bueno en ello. | Phrase
⚠️ | Is she good at skiing? No, she's not very good at it. | ¿Es ella buena para esquiar? No, no es muy buena en ello. | Is she good at skiing? No, she's not very good at it. | ¿Es ella buena para esquiar? No, no es muy buena en ello. | Phrase
⛸️ | Trying something new | Probar algo nuevo | I am trying something new | Estoy probando algo nuevo | Phrase
⛸️ | I'm not very good at ice skating. | No soy muy bueno patinando sobre hielo. | Look, I'm not very good at ice skating. | Mira, no soy muy bueno patinando sobre hielo. | Phrase
💪 | Don't worry. I can help you. | No te preocupes. Puedo ayudarte. | Don't worry. I can help you. | No te preocupes. Puedo ayudarte. | Phrase
🦁 | Be brave. | Sé valiente. | Please, be brave. | Por favor, sé valiente. | Phrase
🪖 | Wear a helmet | Usar un casco | Always wear a helmet | Siempre usa un casco | Phrase
🧴 | Put on sunscreen | Ponerse protector solar | Put on sunscreen outside | Ponte protector solar afuera | Phrase
🦺 | Wear a life jacket | Usar un chaleco salvavidas | Wear a life jacket in the water | Usa un chaleco salvavidas en el agua | Phrase
🚗 | Fasten your seatbelt | Abrocharse el cinturón de seguridad | Fasten your seatbelt in the car | Abróchate el cinturón de seguridad en el auto | Phrase
🏂 | When you go snowboarding, always wear a helmet. | Cuando hagas snowboard, usa siempre un casco. | When you go snowboarding, always wear a helmet. | Cuando hagas snowboard, usa siempre un casco. | Phrase
    """.trimIndent(), "Basics 4 - List 1")

    private fun getList2(): List<Flashcard> = parseCards("""
🦛 | Hippopotamus | Hipopótamo | The hippopotamus is big | El hipopótamo es grande | Word
🦍 | Gorilla | Gorila | The gorilla is strong | El gorila es fuerte | Word
🐼 | Panda | Panda | The panda is black and white | El panda es blanco y negro | Word
🦋 | Butterfly | Mariposa | The butterfly is pretty | La mariposa es bonita | Word
🐛 | Caterpillar | Oruga | The caterpillar is small | La oruga es pequeña | Word
🐝 | Bee | Abeja | The bee can fly | La abeja puede volar | Word
🦛 | The hippopotamus is bigger than the panda. | El hipopótamo es más grande que el panda. | Look, the hippopotamus is bigger than the panda. | Mira, el hipopótamo es más grande que el panda. | Phrase
🦛 | The hippopotamus is the biggest. | El hipopótamo es el más grande. | Look, the hippopotamus is the biggest. | Mira, el hipopótamo es el más grande. | Phrase
1️⃣ | Which one is the smallest? | ¿Cuál es el más pequeño? | Tell me, which one is the smallest? | Dime, ¿cuál es el más pequeño? | Question
🐝 | The bee is the smallest. | La abeja es la más pequeña. | Look, the bee is the smallest. | Mira, la abeja es la más pequeña. | Phrase
🐍 | Eel | Anguila | The eel is long | La anguila es larga | Word
🦭 | Seal | Foca | The seal can swim | La foca puede nadar | Word
🐬 | Dolphin | Delfín | The dolphin is smart | El delfín es inteligente | Word
🦑 | Squid | Calamar | The squid is in the sea | El calamar está en el mar | Word
🐋 | Whale | Ballena | The whale is big | La ballena es grande | Word
🦈 | Shark | Tiburón | The shark is fast | El tiburón es rápido | Word
🐍 | The eel is as long as the seal. | La anguila es tan larga como la foca. | Look, the eel is as long as the seal. | Mira, la anguila es tan larga como la foca. | Phrase
🐍 | The eel isn't as long as the seal. | La anguila no es tan larga como la foca. | Look, the eel isn't as long as the seal. | Mira, la anguila no es tan larga como la foca. | Phrase
🦭 | Is the eel as long as the seal? | ¿Es la anguila tan larga como la foca? | Tell me, is the eel as long as the seal? | Dime, ¿es la anguila tan larga como la foca? | Question
👍 | Yes, it is. | Sí, lo es. | Yes, it is. | Sí, lo es. | Phrase
🙅 | No, it isn't. | No, no lo es. | No, it isn't. | No, no lo es. | Phrase
📏 | It's shorter. | Es más corta. | Look, it's shorter. | Mira, es más corta. | Phrase
🧢 | The Best Cap | La mejor gorra | This is the best cap | Esta es la mejor gorra | Phrase
1️⃣ | Which one would you like? | ¿Cuál te gustaría? | Tell me, which one would you like? | Dime, ¿cuál te gustaría? | Question
🧢 | I'd like the longest one, please. | Me gustaría la más larga, por favor. | I'd like the longest one, please. | Me gustaría la más larga, por favor. | Phrase
💭 | Be thoughtful. | Sé considerado. | Please, be thoughtful. | Por favor, sé considerado. | Phrase
🦎 | Lizard | Lagartija | The lizard is green | La lagartija es verde | Word
🪲 | Beetle | Escarabajo | The beetle is small | El escarabajo es pequeño | Word
🦀 | Crab | Cangrejo | The crab is red | El cangrejo es rojo | Word
🐙 | Octopus | Pulpo | The octopus has eight arms | El pulpo tiene ocho brazos | Word
🦎 | How much does the lizard weigh? | ¿Cuánto pesa la lagartija? | Tell me, how much does the lizard weigh? | Dime, ¿cuánto pesa la lagartija? | Question
⚖️ | It weighs 150 kilograms. | Pesa 150 kilogramos. | Look, it weighs 150 kilograms. | Mira, pesa 150 kilogramos. | Phrase
🦎 | How long is the lizard? | ¿Qué tan larga es la lagartija? | Tell me, how long is the lizard? | Dime, ¿qué tan larga es la lagartija? | Question
📏 | It's 3 meters long. | Mide 3 metros de largo. | Look, it's 3 meters long. | Mira, mide 3 metros de largo. | Phrase
🦍 | The gorilla is bigger than the panda. | El gorila es más grande que el panda. | Look, the gorilla is bigger than the panda. | Mira, el gorila es más grande que el panda. | Phrase
🐼 | The panda is bigger than the bee. | El panda es más grande que la abeja. | Look, the panda is bigger than the bee. | Mira, el panda es más grande que la abeja. | Phrase
🦋 | The butterfly is smaller than the gorilla. | La mariposa es más pequeña que el gorila. | Look, the butterfly is smaller than the gorilla. | Mira, la mariposa es más pequeña que el gorila. | Phrase
🐛 | The caterpillar is smaller than the hippopotamus. | La oruga es más pequeña que el hipopótamo. | Look, the caterpillar is smaller than the hippopotamus. | Mira, la oruga es más pequeña que el hipopótamo. | Phrase
🦍 | The gorilla is the biggest. | El gorila es el más grande. | Look, the gorilla is the biggest. | Mira, el gorila es el más grande. | Phrase
🐼 | The panda is the biggest. | El panda es el más grande. | Look, the panda is the biggest. | Mira, el panda es el más grande. | Phrase
🦋 | The butterfly is the smallest. | La mariposa es la más pequeña. | Look, the butterfly is the smallest. | Mira, la mariposa es la más pequeña. | Phrase
🐛 | The caterpillar is the smallest. | La oruga es la más pequeña. | Look, the caterpillar is the smallest. | Mira, la oruga es la más pequeña. | Phrase
1️⃣ | Which one is the biggest? | ¿Cuál es el más grande? | Tell me, which one is the biggest? | Dime, ¿cuál es el más grande? | Question
🦛 | The hippopotamus is the biggest. | El hipopótamo es el más grande. | Look, the hippopotamus is the biggest. | Mira, el hipopótamo es el más grande. | Phrase
🐝 | Which one is the biggest? The gorilla or the bee? | ¿Cuál es el más grande? ¿El gorila o la abeja? | Tell me, which one is the biggest? The gorilla or the bee? | Dime, ¿cuál es el más grande? ¿El gorila o la abeja? | Question
🦍 | The gorilla is the biggest. | El gorila es el más grande. | Look, the gorilla is the biggest. | Mira, el gorila es el más grande. | Phrase
🦋 | Which one is the smallest? The panda or the butterfly? | ¿Cuál es el más pequeño? ¿El panda o la mariposa? | Tell me, which one is the smallest? The panda or the butterfly? | Dime, ¿cuál es el más pequeño? ¿El panda o la mariposa? | Question
🦋 | The butterfly is the smallest. | La mariposa es la más pequeña. | Look, the butterfly is the smallest. | Mira, la mariposa es la más pequeña. | Phrase
🦭 | The seal is as long as the dolphin. | La foca es tan larga como el delfín. | Look, the seal is as long as the dolphin. | Mira, la foca es tan larga como el delfín. | Phrase
🐬 | The dolphin isn't as long as the whale. | El delfín no es tan largo como la ballena. | Look, the dolphin isn't as long as the whale. | Mira, el delfín no es tan largo como la ballena. | Phrase
🦑 | The squid is as long as the shark. | El calamar es tan largo como el tiburón. | Look, the squid is as long as the shark. | Mira, el calamar es tan largo como el tiburón. | Phrase
🐋 | The whale isn't as long as the eel. | La ballena no es tan larga como la anguila. | Look, the whale isn't as long as the eel. | Mira, la ballena no es tan larga como la anguila. | Phrase
🐬 | Is the seal as long as the dolphin? | ¿Es la foca tan larga como el delfín? | Tell me, is the seal as long as the dolphin? | Dime, ¿es la foca tan larga como el delfín? | Question
🦈 | Is the whale as long as the shark? | ¿Es la ballena tan larga como el tiburón? | Tell me, is the whale as long as the shark? | Dime, ¿es la ballena tan larga como el tiburón? | Question
👍 | Yes, it is. | Sí, lo es. | Yes, it is. | Sí, lo es. | Phrase
🙅 | No, it isn't. | No, no lo es. | No, it isn't. | No, no lo es. | Phrase
🦭 | Which one would you like? The eel or the seal? | ¿Cuál te gustaría? ¿La anguila o la foca? | Tell me, which one would you like? The eel or the seal? | Dime, ¿cuál te gustaría? ¿La anguila o la foca? | Question
🐍 | I'd like the longest one, please. | Me gustaría la más larga, por favor. | I'd like the longest one, please. | Me gustaría la más larga, por favor. | Phrase
🐋 | Which one would you like? The dolphin or the whale? | ¿Cuál te gustaría? ¿El delfín o la ballena? | Tell me, which one would you like? The dolphin or the whale? | Dime, ¿cuál te gustaría? ¿El delfín o la ballena? | Question
🐋 | I'd like the longest one, please. | Me gustaría la más larga, por favor. | I'd like the longest one, please. | Me gustaría la más larga, por favor. | Phrase
🪲 | How much does the beetle weigh? | ¿Cuánto pesa el escarabajo? | Tell me, how much does the beetle weigh? | Dime, ¿cuánto pesa el escarabajo? | Question
⚖️ | It weighs 5 kilograms. | Pesa 5 kilogramos. | Look, it weighs 5 kilograms. | Mira, pesa 5 kilogramos. | Phrase
🦀 | How much does the crab weigh? | ¿Cuánto pesa el cangrejo? | Tell me, how much does the crab weigh? | Dime, ¿cuánto pesa el cangrejo? | Question
⚖️ | It weighs 20 kilograms. | Pesa 20 kilogramos. | Look, it weighs 20 kilograms. | Mira, pesa 20 kilogramos. | Phrase
🐙 | How much does the octopus weigh? | ¿Cuánto pesa el pulpo? | Tell me, how much does the octopus weigh? | Dime, ¿cuánto pesa el pulpo? | Question
⚖️ | It weighs 80 kilograms. | Pesa 80 kilogramos. | Look, it weighs 80 kilograms. | Mira, pesa 80 kilogramos. | Phrase
🪲 | How long is the beetle? | ¿Qué tan largo es el escarabajo? | Tell me, how long is the beetle? | Dime, ¿qué tan largo es el escarabajo? | Question
📏 | It's 1 meter long. | Mide 1 metro de largo. | Look, it's 1 meter long. | Mira, mide 1 metro de largo. | Phrase
🦀 | How long is the crab? | ¿Qué tan largo es el cangrejo? | Tell me, how long is the crab? | Dime, ¿qué tan largo es el cangrejo? | Question
📏 | It's 2 meters long. | Mide 2 metros de largo. | Look, it's 2 meters long. | Mira, mide 2 metros de largo. | Phrase
🐙 | How long is the octopus? | ¿Qué tan largo es el pulpo? | Tell me, how long is the octopus? | Dime, ¿qué tan largo es el pulpo? | Question
📏 | It's 4 meters long. | Mide 4 metros de largo. | Look, it's 4 meters long. | Mira, mide 4 metros de largo. | Phrase
    """.trimIndent(), "Basics 4 - List 2")

    private fun getList3(): List<Flashcard> = parseCards("""
👦 | Short hair | Cabello corto | The boy has short hair | El niño tiene cabello corto | Word
👩 | Shoulder-length hair | Cabello al hombro | She has shoulder-length hair | Ella tiene cabello al hombro | Word
👩 | Long hair | Cabello largo | The girl has long hair | La niña tiene cabello largo | Word
🧑 | Straight hair | Cabello lacio | He has straight hair | Él tiene cabello lacio | Word
👩 | Curly hair | Cabello rizado | She has curly hair | Ella tiene cabello rizado | Word
🧑 | Wavy hair | Cabello ondulado | He has wavy hair | Él tiene cabello ondulado | Word
🤔 | What does he look like? | ¿Cómo es su apariencia? (él) | Tell me, what does he look like? | Dime, ¿cómo es su apariencia? (él) | Question
🤔 | What does she look like? | ¿Cómo es su apariencia? (ella) | Tell me, what does she look like? | Dime, ¿cómo es su apariencia? (ella) | Question
👦 | He has short, black hair and brown eyes. | Él tiene cabello corto y negro y ojos marrones. | Look, he has short, black hair and brown eyes. | Mira, él tiene cabello corto y negro y ojos marrones. | Phrase
👧 | She has short, black hair and brown eyes. | Ella tiene cabello corto y negro y ojos marrones. | Look, she has short, black hair and brown eyes. | Mira, ella tiene cabello corto y negro y ojos marrones. | Phrase
👦 | He has short, black hair and glasses. | Él tiene cabello corto y negro y gafas. | Look, he has short, black hair and glasses. | Mira, él tiene cabello corto y negro y gafas. | Phrase
👧 | She has short, black hair and glasses. | Ella tiene cabello corto y negro y gafas. | Look, she has short, black hair and glasses. | Mira, ella tiene cabello corto y negro y gafas. | Phrase
👦 | He has short, black hair and a beard. | Él tiene cabello corto y negro y barba. | Look, he has short, black hair and a beard. | Mira, él tiene cabello corto y negro y barba. | Phrase
👧 | She has short, black hair and a beard. | Ella tiene cabello corto y negro y barba. | Look, she has short, black hair and a beard. | Mira, ella tiene cabello corto y negro y barba. | Phrase
👦 | Which one is your brother? | ¿Cuál es tu hermano? | Tell me, which one is your brother? | Dime, ¿cuál es tu hermano? | Question
👧 | Which one is your sister? | ¿Cuál es tu hermana? | Tell me, which one is your sister? | Dime, ¿cuál es tu hermana? | Question
👦 | He's the one with short, straight, black hair and brown eyes. | Él es el del cabello corto, lacio, negro y ojos marrones. | Look, he's the one with short, straight, black hair and brown eyes. | Mira, él es el del cabello corto, lacio, negro y ojos marrones. | Phrase
👧 | She's the one with short, straight, black hair and brown eyes. | Ella es la del cabello corto, lacio, negro y ojos marrones. | Look, she's the one with short, straight, black hair and brown eyes. | Mira, ella es la del cabello corto, lacio, negro y ojos marrones. | Phrase
👦 | He's the one with short, straight, black hair and glasses. | Él es el del cabello corto, lacio, negro y gafas. | Look, he's the one with short, straight, black hair and glasses. | Mira, él es el del cabello corto, lacio, negro y gafas. | Phrase
👧 | She's the one with short, straight, black hair and glasses. | Ella es la del cabello corto, lacio, negro y gafas. | Look, she's the one with short, straight, black hair and glasses. | Mira, ella es la del cabello corto, lacio, negro y gafas. | Phrase
👦 | He's the one with short, straight, black hair and a beard. | Él es el del cabello corto, lacio, negro y barba. | Look, he's the one with short, straight, black hair and a beard. | Mira, él es el del cabello corto, lacio, negro y barba. | Phrase
👧 | She's the one with short, straight, black hair and a beard. | Ella es la del cabello corto, lacio, negro y barba. | Look, she's the one with short, straight, black hair and a beard. | Mira, ella es la del cabello corto, lacio, negro y barba. | Phrase
⌚ | Watch | Reloj | I have a watch | Tengo un reloj | Word
📿 | Necklace | Collar | The necklace is new | El collar es nuevo | Word
💎 | Earrings | Aretes / Pendientes | She has earrings | Ella tiene aretes | Word
🕶️ | Sunglasses | Gafas de sol | I wear sunglasses | Uso gafas de sol | Word
🧤 | Gloves | Guantes | The gloves are warm | Los guantes son cálidos | Word
🪢 | Belt | Cinturón | The belt is black | El cinturón es negro | Word
⌚ | What does the watch look like? | ¿Cómo es el reloj? | Tell me, what does the watch look like? | Dime, ¿cómo es el reloj? | Question
💎 | What do the earrings look like? | ¿Cómo son los aretes? | Tell me, what do the earrings look like? | Dime, ¿cómo son los aretes? | Question
⌚ | It's new and black. | Es nuevo y negro. | Look, it's new and black. | Mira, es nuevo y negro. | Phrase
💎 | They're new and black. | Son nuevos y negros. | Look, they're new and black. | Mira, son nuevos y negros. | Phrase
⌚ | Which watch does he want to wear? | ¿Qué reloj quiere usar él? | Tell me, which watch does he want to wear? | Dime, ¿qué reloj quiere usar él? | Question
⌚ | Which watch does she want to wear? | ¿Qué reloj quiere usar ella? | Tell me, which watch does she want to wear? | Dime, ¿qué reloj quiere usar ella? | Question
🧤 | Which gloves does he want to wear? | ¿Qué guantes quiere usar él? | Tell me, which gloves does he want to wear? | Dime, ¿qué guantes quiere usar él? | Question
🧤 | Which gloves does she want to wear? | ¿Qué guantes quiere usar ella? | Tell me, which gloves does she want to wear? | Dime, ¿qué guantes quiere usar ella? | Question
⌚ | He wants to wear the black one. | Él quiere usar el negro. | Look, he wants to wear the black one. | Mira, él quiere usar el negro. | Phrase
⌚ | She wants to wear the black one. | Ella quiere usar el negro. | Look, she wants to wear the black one. | Mira, ella quiere usar el negro. | Phrase
🧤 | He wants to wear the black ones. | Él quiere usar los negros. | Look, he wants to wear the black ones. | Mira, él quiere usar los negros. | Phrase
🧤 | She wants to wear the black ones. | Ella quiere usar los negros. | Look, she wants to wear the black ones. | Mira, ella quiere usar los negros. | Phrase
🍀 | Good luck with the play. | Buena suerte con la obra. | Good luck with the play, my friend. | Buena suerte con la obra, amigo mío. | Phrase
🙏 | Thanks. You, too. | Gracias. Igualmente. | Thanks. You, too. | Gracias. Igualmente. | Phrase
❤️ | Be kind. | Sé amable. | Please, be kind. | Por favor, sé amable. | Phrase
🪵 | Stick | Palo / Rama | The stick is long | El palo es largo | Word
🍃 | Leaf | Hoja | The leaf is green | La hoja es verde | Word
🌱 | Grass | Hierba / Pasto | The grass is green | La hierba es verde | Word
🏖️ | Sand | Arena | The sand is hot | La arena está caliente | Word
🐛 | The caterpillar is the same color as the stick. | La oruga es del mismo color que el palo. | Look, the caterpillar is the same color as the stick. | Mira, la oruga es del mismo color que el palo. | Phrase
🐛 | The caterpillar is the same shape as the stick. | La oruga tiene la misma forma que el palo. | Look, the caterpillar is the same shape as the stick. | Mira, la oruga tiene la misma forma que el palo. | Phrase
    """.trimIndent(), "Basics 4 - List 3")

    private fun getList4(): List<Flashcard> = parseCards("""
⚾ | Baseball | Béisbol | I play baseball | Juego béisbol | Word
🏀 | Basketball | Baloncesto | He likes basketball | A él le gusta el baloncesto | Word
🏐 | Volleyball | Voleibol | We play volleyball | Jugamos voleibol | Word
⛳ | Golf | Golf | My father plays golf | Mi padre juega al golf | Word
🎾 | Tennis | Tenis | She plays tennis | Ella juega al tenis | Word
🏓 | Table tennis | Tenis de mesa | They play table tennis | Ellos juegan tenis de mesa | Word
⚾ | He played baseball yesterday. | Él jugó béisbol ayer. | Look, he played baseball yesterday. | Mira, él jugó béisbol ayer. | Phrase
⚾ | She played baseball yesterday. | Ella jugó béisbol ayer. | Look, she played baseball yesterday. | Mira, ella jugó béisbol ayer. | Phrase
🤔 | What did he do yesterday? | ¿Qué hizo él ayer? | Tell me, what did he do yesterday? | Dime, ¿qué hizo él ayer? | Question
🤔 | What did she do yesterday? | ¿Qué hizo ella ayer? | Tell me, what did she do yesterday? | Dime, ¿qué hizo ella ayer? | Question
🎹 | Practice the piano | Practicar el piano | I practice the piano | Practico el piano | Phrase
💻 | Use the computer | Usar la computadora | I use the computer | Uso la computadora | Phrase
📞 | Talk on the phone | Hablar por teléfono | I talk on the phone | Hablo por teléfono | Phrase
👪 | Help my parents | Ayudar a mis padres | I help my parents | Ayudo a mis padres | Phrase
🤝 | Visit my friend | Visitar a mi amigo | I visit my friend | Visito a mi amigo | Phrase
📊 | Work on a project | Trabajar en un proyecto | I work on a project | Trabajo en un proyecto | Phrase
🤔 | What did you do last weekend? | ¿Qué hiciste el fin de semana pasado? | Tell me, what did you do last weekend? | Dime, ¿qué hiciste el fin de semana pasado? | Question
🎹 | I practiced the piano. | Practiqué el piano. | Look, I practiced the piano. | Mira, practiqué el piano. | Phrase
🎹 | Did you practice the piano on Monday? | ¿Practicaste el piano el lunes? | Tell me, did you practice the piano on Monday? | Dime, ¿practicaste el piano el lunes? | Question
👍 | Yes, I did. | Sí, lo hice. | Yes, I did. | Sí, lo hice. | Phrase
🙅 | No, I didn't. | No, no lo hice. | No, I didn't. | No, no lo hice. | Phrase
⚾ | The Baseball Game | El juego de béisbol | It is a great baseball game | Es un gran juego de béisbol | Phrase
🧤 | I can't find my glove. | No encuentro mi guante. | Look, I can n't find my glove. | Mira, no encuentro mi guante. | Phrase
👍 | Don't worry. You can borrow mine. | No te preocupes. Puedes tomar prestado el mío. | Don't worry. You can borrow mine. | No te preocupes. Puedes tomar prestado el mío. | Phrase
🛡️ | Be prepared. | Prepárate. | Always be prepared. | Siempre prepárate. | Phrase
🪨 | Stone | Piedra | The stone is hard | La piedra es dura | Word
🏺 | Clay | Arcilla | The bowl is made of clay | El tazón es de arcilla | Word
🥛 | Glass | Vidrio | The window is glass | La ventana es de vidrio | Word
🪙 | Metal | Metal | The key is metal | La llave es de metal | Word
🤔 | What did they use to make homes in Rome? | ¿Qué usaron para hacer casas en Roma? | Tell me, what did they use to make homes in Rome? | Dime, ¿qué usaron para hacer casas en Roma? | Question
🪨 | They used stone. | Usaron piedra. | Look, they used stone. | Mira, usaron piedra. | Phrase
🏀 | He played basketball yesterday. | Él jugó baloncesto ayer. | Look, he played basketball yesterday. | Mira, él jugó baloncesto ayer. | Phrase
🏐 | She played volleyball yesterday. | Ella jugó voleibol ayer. | Look, she played volleyball yesterday. | Mira, ella jugó voleibol ayer. | Phrase
⛳ | He played golf yesterday. | Él jugó golf ayer. | Look, he played golf yesterday. | Mira, él jugó golf ayer. | Phrase
🎾 | She played tennis yesterday. | Ella jugó tenis ayer. | Look, she played tennis yesterday. | Mira, ella jugó tenis ayer. | Phrase
🏓 | He played table tennis yesterday. | Él jugó tenis de mesa ayer. | Look, he played table tennis yesterday. | Mira, él jugó table tennis ayer. | Phrase
💻 | I used the computer last weekend. | Usé la computadora el fin de semana pasado. | Look, I used the computer last weekend. | Mira, usé la computadora el fin de semana pasado. | Phrase
📞 | I talked on the phone last weekend. | Hablé por teléfono el fin de semana pasado. | Look, I talked on the phone last weekend. | Mira, hablé por teléfono el fin de semana pasado. | Phrase
👪 | I helped my parents last weekend. | Ayudé a mis padres el fin de semana pasado. | Look, I helped my parents last weekend. | Mira, ayudé a mis padres el fin de semana pasado. | Phrase
🤝 | I visited my friend last weekend. | Visité a mi amigo el fin de semana pasado. | Look, I visited my friend last weekend. | Mira, visité a mi amigo el fin de semana pasado. | Phrase
📊 | I worked on a project last weekend. | Trabajé en un proyecto el fin de semana pasado. | Look, I worked on a project last weekend. | Mira, trabajé en un proyecto el fin de semana pasado. | Phrase
🤔 | Did you use the computer on Monday? | ¿Usaste la computadora el lunes? | Tell me, did you use the computer on Monday? | Dime, ¿usaste la computadora el lunes? | Question
📱 | Did you talk on the phone on Monday? | ¿Hablaste por teléfono el lunes? | Tell me, did you talk on the phone on Monday? | Dime, ¿hablaste por teléfono el lunes? | Question
👨‍👩‍👧 | Did you help your parents on Monday? | ¿Ayudaste a tus padres el lunes? | Tell me, did you help your parents on Monday? | Dime, ¿ayudaste a tus padres el lunes? | Question
🤝 | Did you visit your friend on Monday? | ¿Visitaste a tu amigo el lunes? | Tell me, did you visit your friend on Monday? | Dime, ¿visitaste a tu amigo el lunes? | Question
🤔 | Did you work on a project on Monday? | ¿Trabajaste en un proyecto el lunes? | Tell me, did you work on a project on Monday? | Dime, ¿trabajaste en un proyecto el lunes? | Question
🏺 | They used clay. | Usaron arcilla. | Look, they used clay. | Mira, usaron arcilla. | Phrase
🪞 | They used glass. | Usaron vidrio. | Look, they used glass. | Mira, usaron vidrio. | Phrase
🪙 | They used metal. | Usaron metal. | Look, they used metal. | Mira, usaron metal. | Phrase
    """.trimIndent(), "Basics 4 - List 4")

    private fun getList5(): List<Flashcard> = parseCards("""
🍜 | Noodles | Fideos | I eat noodles | Como fideos | Word
🍛 | Curry | Curry | The curry is hot | El curry está picante | Word
🍣 | Sushi | Sushi | I like sushi | Me gusta el sushi | Word
🍋 | Lemonade | Limonada | I drink lemonade | Bebo limonada | Word
🧃 | Grape juice | Jugo de uva | The grape juice is sweet | El jugo de uva es dulce | Word
🍵 | Tea | Té | I want some tea | Quiero un poco de té | Word
🍜 | He ate noodles. | Él comió fideos. | Look, he ate noodles. | Mira, él comió fideos. | Phrase
🍜 | She ate noodles. | Ella comió fideos. | Look, she ate noodles. | Mira, ella comió fideos. | Phrase
🍋 | He drank lemonade. | Él bebió limonada. | Look, he drank lemonade. | Mira, él bebió limonada. | Phrase
🍋 | She drank lemonade. | Ella bebió limonada. | Look, she drank lemonade. | Mira, ella bebió limonada. | Phrase
🍱 | What did he eat for lunch? | ¿Qué almorzó él? | Tell me, what did he eat for lunch? | Dime, ¿qué almorzó él? | Question
🍱 | What did she eat for lunch? | ¿Qué almorzó ella? | Tell me, what did she eat for lunch? | Dime, ¿qué almorzó ella? | Question
🍜 | He ate noodles. | Él comió fideos. | Look, he ate noodles. | Mira, él comió fideos. | Phrase
🍜 | She ate noodles. | Ella comió fideos. | Look, she ate noodles. | Mira, ella comió fideos. | Phrase
🍱 | What did he drink with lunch? | ¿Qué bebió él con el almuerzo? | Tell me, what did he drink with lunch? | Dime, ¿qué bebió él con el almuerzo? | Question
🍱 | What did she drink with lunch? | ¿Qué bebió ella con el almuerzo? | Tell me, what did she drink with lunch? | Dime, ¿qué bebió ella con el almuerzo? | Question
🍋 | He drank lemonade. | Él bebió limonada. | Look, he drank lemonade. | Mira, él bebió limonada. | Phrase
🍋 | She drank lemonade. | Ella bebió limonada. | Look, she drank lemonade. | Mira, ella bebió limonada. | Phrase
🎳 | Go bowling | Ir a jugar boliche | I go bowling on Saturdays | Voy a jugar boliche los sábados | Phrase
📸 | Take a picture | Tomar una foto | I take a picture of a cat | Tomo una foto de un gato | Phrase
🎪 | See a parade | Ver un desfile | We see a parade | Vemos un desfile | Phrase
🧺 | Have a picnic | Hacer un pícnic | They have a picnic in the park | Hacen un pícnic en el parque | Phrase
💇 | Get a haircut | Cortarse el cabello | He gets a haircut | Él se corta el cabello | Phrase
🛍️ | Buy clothes | Comprar ropa | I buy clothes at the store | Compro ropa en la tienda | Phrase
🤔 | What did he do yesterday? | ¿Qué hizo él ayer? | Tell me, what did he do yesterday? | Dime, ¿qué hizo él ayer? | Question
🤔 | What did she do yesterday? | ¿Qué hizo ella ayer? | Tell me, what did she do yesterday? | Dime, ¿qué hizo ella ayer? | Question
🎳 | He went bowling. | Él fue a jugar boliche. | Look, he went bowling. | Mira, él fue a jugar boliche. | Phrase
🎳 | She went bowling. | Ella fue a jugar boliche. | Look, she went bowling. | Mira, ella fue a jugar boliche. | Phrase
🎳 | When did he go bowling? | ¿Cuándo fue él a jugar boliche? | Tell me, when did he go bowling? | Dime, ¿cuándo fue él a jugar boliche? | Question
🎳 | When did she go bowling? | ¿Cuándo fue ella a jugar boliche? | Tell me, when did she go bowling? | Dime, ¿cuándo fue ella a jugar boliche? | Question
🎳 | He went bowling yesterday. | Él fue a jugar boliche ayer. | Look, he went bowling yesterday. | Mira, él fue a jugar boliche ayer. | Phrase
🎳 | She went bowling yesterday. | Ella fue a jugar boliche ayer. | Look, she went bowling yesterday. | Mira, ella fue a jugar boliche ayer. | Phrase
🎒 | The Missing Backpack | La mochila perdida | This is The Missing Backpack | Esta es La mochila perdida | Phrase
🤔 | What happened? | ¿Qué pasó? | Tell me, what happened? | Dime, ¿qué pasó? | Question
🎒 | I lost my backpack. | Perdí mi mochila. | Oh no, I lost my backpack. | Oh no, perdí mi mochila. | Phrase
🤝 | Let's look for it together. | Busquémosla juntos. | Come on, let's look for it together. | Vamos, busquémosla juntos. | Phrase
🤝 | Be helpful. | Sé servicial. | Please, be helpful. | Por favor, sé servicial. | Phrase
🪶 | Feather | Pluma | The feather is light | La pluma es ligera | Word
🐾 | Tail | Cola | The cat has a long tail | El gato tiene una cola larga | Word
🦅 | Claw | Garra | The bird has a sharp claw | El pájaro tiene una garra afilada | Word
🦇 | Wing | Ala | The bird has a wing | El pájaro tiene un ala | Word
🦖 | Some dinosaurs had feathers. | Algunos dinosaurios tenían plumas. | Look, some dinosaurs had feathers. | Mira, algunos dinosaurios tenían plumas. | Phrase
🍛 | He ate curry. | Él comió curry. | Look, he ate curry. | Mira, él comió curry. | Phrase
🍛 | She ate curry. | Ella comió curry. | Look, she ate curry. | Mira, ella comió curry. | Phrase
🍣 | He ate sushi. | Él comió sushi. | Look, he ate sushi. | Mira, él comió sushi. | Phrase
🍣 | She ate sushi. | Ella comió sushi. | Look, she ate sushi. | Mira, ella comió sushi. | Phrase
🧃 | He drank grape juice. | Él bebió jugo de uva. | Look, he drank grape juice. | Mira, él bebió jugo de uva. | Phrase
🧃 | She drank grape juice. | Ella bebió jugo de uva. | Look, she drank grape juice. | Mira, ella bebió jugo de uva. | Phrase
🍵 | He drank tea. | Él bebió té. | Look, he drank tea. | Mira, él bebió té. | Phrase
🍵 | She drank tea. | Ella bebió té. | Look, she drank tea. | Mira, ella bebió té. | Phrase
🍽️ | What did he eat for dinner? | ¿Qué cenó él? | Tell me, what did he eat for dinner? | Dime, ¿qué cenó él? | Question
🍽️ | What did she eat for dinner? | ¿Qué cenó ella? | Tell me, what did she eat for dinner? | Dime, ¿qué cenó ella? | Question
🍽️ | What did he drink with dinner? | ¿Qué bebió él con la cena? | Tell me, what did he drink with dinner? | Dime, ¿qué bebió él con la cena? | Question
🍽️ | What did she drink with dinner? | ¿Qué bebió ella con la cena? | Tell me, what did she drink with dinner? | Dime, ¿qué bebió ella con la cena? | Question
📸 | He took a picture. | Él tomó una foto. | Look, he took a picture. | Mira, él tomó una foto. | Phrase
📸 | She took a picture. | Ella tomó una foto. | Look, she took a picture. | Mira, ella tomó una foto. | Phrase
🎪 | He saw a parade. | Él vio un desfile. | Look, he saw a parade. | Mira, él vio un desfile. | Phrase
🎪 | She saw a parade. | Ella vio un desfile. | Look, she saw a parade. | Mira, ella vio un desfile. | Phrase
🧺 | He had a picnic. | Él hizo un pícnic. | Look, he had a picnic. | Mira, él hizo un pícnic. | Phrase
🧺 | She had a picnic. | Ella hizo un pícnic. | Look, she had a picnic. | Mira, ella hizo un pícnic. | Phrase
💇 | He got a haircut. | Él se cortó el cabello. | Look, he got a haircut. | Mira, él se cortó el cabello. | Phrase
💇 | She got a haircut. | Ella se cortó el cabello. | Look, she got a haircut. | Mira, ella se cortó el cabello. | Phrase
🛍️ | He bought clothes. | Él compró ropa. | Look, he bought clothes. | Mira, él compró ropa. | Phrase
🛍️ | She bought clothes. | Ella compró ropa. | Look, she bought clothes. | Mira, ella compró ropa. | Phrase
🧱 | When did he take a picture? | ¿Cuándo tomó él una foto? | Tell me, when did he take a picture? | Dime, ¿cuándo tomó él una foto? | Question
🧱 | When did she take a picture? | ¿Cuándo tomó ella una foto? | Tell me, when did she take a picture? | Dime, ¿cuándo tomó ella una foto? | Question
🤔 | When did he see a parade? | ¿Cuándo vio él un desfile? | Tell me, when did he see a parade? | Dime, ¿cuándo vio él un desfile? | Question
🤔 | When did she see a parade? | ¿Cuándo vio ella un desfile? | Tell me, when did she see a parade? | Dime, ¿cuándo vio ella un desfile? | Question
🤔 | When did he have a picnic? | ¿Cuándo hizo él un pícnic? | Tell me, when did he have a picnic? | Dime, ¿cuándo hizo él un pícnic? | Question
🤔 | When did she have a picnic? | ¿Cuándo hizo ella un pícnic? | Tell me, when did she have a picnic? | Dime, ¿cuándo hizo ella un pícnic? | Question
🤔 | When did he get a haircut? | ¿Cuándo se cortó el cabello él? | Tell me, when did he get a haircut? | Dime, ¿cuándo se cortó el cabello él? | Question
🤔 | When did she get a haircut? | ¿Cuándo se cortó el cabello ella? | Tell me, when did she get a haircut? | Dime, ¿cuándo se cortó el cabello ella? | Question
🛍️ | When did he buy clothes? | ¿Cuándo compró ropa él? | Tell me, when did he buy clothes? | Dime, ¿cuándo compró ropa él? | Question
🛍️ | When did she buy clothes? | ¿Cuándo compró ropa ella? | Tell me, when did she buy clothes? | Dime, ¿cuándo compró ropa ella? | Question
📸 | He took a picture yesterday. | Él tomó una foto ayer. | Look, he took a picture yesterday. | Mira, él tomó una foto ayer. | Phrase
📸 | She took a picture yesterday. | Ella tomó una foto ayer. | Look, she took a picture yesterday. | Mira, ella tomó una foto ayer. | Phrase
🎪 | He saw a parade yesterday. | Él vio un desfile ayer. | Look, he saw a parade yesterday. | Mira, él vio un desfile ayer. | Phrase
🎪 | She saw a parade yesterday. | Ella vio un desfile ayer. | Look, she saw a parade yesterday. | Mira, ella vio un desfile ayer. | Phrase
🧺 | He had a picnic yesterday. | Él hizo un pícnic ayer. | Look, he had a picnic yesterday. | Mira, él hizo un pícnic ayer. | Phrase
🧺 | She had a picnic yesterday. | Ella hizo un pícnic ayer. | Look, she had a picnic yesterday. | Mira, ella hizo un pícnic ayer. | Phrase
💇 | He got a haircut yesterday. | Él se cortó el cabello ayer. | Look, he got a haircut yesterday. | Mira, él se cortó el cabello ayer. | Phrase
💇 | She got a haircut yesterday. | Ella se cortó el cabello ayer. | Look, she got a haircut yesterday. | Mira, ella se cortó el cabello ayer. | Phrase
🛍️ | He bought clothes yesterday. | Él compró ropa ayer. | Look, he bought clothes yesterday. | Mira, él compró ropa ayer. | Phrase
🛍️ | She bought clothes yesterday. | Ella compró ropa ayer. | Look, she bought clothes yesterday. | Mira, ella compró ropa ayer. | Phrase
🦖 | Some dinosaurs had tails. | Algunos dinosaurios tenían colas. | Look, some dinosaurs had tails. | Mira, algunos dinosaurios tenían colas. | Phrase
🦖 | Some dinosaurs had claws. | Algunos dinosaurios tenían garras. | Look, some dinosaurs had claws. | Mira, algunos dinosaurios tenían garras. | Phrase
🦖 | Some dinosaurs had wings. | Algunos dinosaurios tenían alas. | Look, some dinosaurs had wings. | Mira, algunos dinosaurios tenían alas. | Phrase
    """.trimIndent(), "Basics 4 - List 5")

    private fun getList6(): List<Flashcard> = parseCards("""
🎤 | Sing songs | Cantar canciones | I like to sing songs | Me gusta cantar canciones | Phrase
🎬 | Make movies | Hacer películas | We make movies | Hacemos películas | Phrase
📖 | Write stories | Escribir historias | She writes stories | Ella escribe historias | Phrase
👗 | Design clothes | Diseñar ropa | They design clothes | Ellos diseñan ropa | Phrase
🎨 | Paint pictures | Pintar cuadros | I paint pictures | Pinto cuadros | Phrase
🦖 | Make models | Hacer maquetas | He likes to make models | A él le gusta hacer maquetas | Phrase
⏰ | What does he like to do in his free time? | ¿Qué le gusta hacer a él en su tiempo libre? | Tell me, what does he like to do in his free time? | Dime, ¿qué le gusta hacer a él en su tiempo libre? | Question
⏰ | What does she like to do in her free time? | ¿Qué le gusta hacer a ella en su tiempo libre? | Tell me, what does she like to do in her free time? | Dime, ¿qué le gusta hacer a ella en su tiempo libre? | Question
🎤 | He likes to sing songs. | A él le gusta cantar canciones. | Look, he likes to sing songs. | Mira, a él le gusta cantar canciones. | Phrase
🎤 | She likes to sing songs. | A ella le gusta cantar canciones. | Look, she likes to sing songs. | Mira, a ella le gusta cantar canciones. | Phrase
🎤 | Does he like to sing songs in his free time? | ¿Le gusta cantar canciones a él en su tiempo libre? | Tell me, does he like to sing songs in his free time? | Dime, ¿le gusta cantar canciones a él en su tiempo libre? | Question
🎤 | Does she like to sing songs in her free time? | ¿Le gusta cantar canciones a ella en su tiempo libre? | Tell me, does she like to sing songs in her free time? | Dime, ¿le gusta cantar canciones a ella en su tiempo libre? | Question
👍 | Yes, he does. | Sí, sí le gusta. / Sí, lo hace. | Yes, he does. | Sí, lo hace. | Phrase
👍 | Yes, she does. | Sí, a ella sí. / Sí, lo hace. | Yes, she does. | Sí, lo hace. | Phrase
🙅 | No, he doesn't. | No, a él no. / No, no lo hace. | No, he doesn't. | No, no lo hace. | Phrase
🙅 | No, she doesn't. | No, a ella no. / No, no lo hace. | No, she doesn't. | No, no lo hace. | Phrase
🎬 | He likes to make movies. | A él le gusta hacer películas. | Look, he likes to make movies. | Mira, a él le gusta hacer películas. | Phrase
🍳 | Cook dinner | Cocinar la cena | I cook dinner | Cocino la cena | Phrase
🍪 | Bake cookies | Hornear galletas | We bake cookies | Horneamos galletas | Phrase
💍 | Make jewelry | Hacer joyería | She makes jewelry | Ella hace joyería | Phrase
🃏 | Make a card | Hacer una tarjeta | I make a card | Hago una tarjeta | Phrase
🧣 | Knit a scarf | Tejer una bufanda | My grandmother knits a scarf | Mi abuela teje una bufanda | Phrase
🎵 | Play music | Tocar música | They play music | Ellos tocan música | Phrase
🍲 | He cooked dinner for him. | Él cocinó la cena para él. | Look, he cooked dinner for him. | Mira, él cocinó la cena para él. | Phrase
🍲 | He cooked dinner for her. | Él cocinó la cena para ella. | Look, he cooked dinner for her. | Mira, él cocinó la cena para ella. | Phrase
🍲 | He cooked dinner for them. | Él cocinó la cena para ellos. | Look, he cooked dinner for them. | Mira, él cocinó la cena para ellos. | Phrase
🍲 | She cooked dinner for him. | Ella cocinó la cena para él. | Look, she cooked dinner for him. | Mira, ella cocinó la cena para él. | Phrase
🍲 | She cooked dinner for her. | Ella cocinó la cena para ella. | Look, she cooked dinner for her. | Mira, ella cocinó la cena para ella. | Phrase
🍲 | She cooked dinner for them. | Ella cocinó la cena para ellos. | Look, she cooked dinner for them. | Mira, ella cocinó la cena para ellos. | Phrase
👨‍🍳 | What did he cook for him? | ¿Qué cocinó él para él? | Tell me, what did he cook for him? | Dime, ¿qué cocinó él para él? | Question
👨‍🍳 | What did he cook for her? | ¿Qué cocinó él para ella? | Tell me, what did he cook for her? | Dime, ¿qué cocinó él para ella? | Question
👨‍🍳 | What did he cook for them? | ¿Qué cocinó él para ellos? | Tell me, what did he cook for them? | Dime, ¿qué cocinó él para ellos? | Question
👨‍🍳 | What did she cook for him? | ¿Qué cocinó ella para él? | Tell me, what did she cook for him? | Dime, ¿qué cocinó ella para él? | Question
👨‍🍳 | What did she cook for her? | ¿Qué cocinó ella para ella? | Tell me, what did she cook for her? | Dime, ¿qué cocinó ella para ella? | Question
👨‍🍳 | What did she cook for them? | ¿Qué cocinó ella para ellos? | Tell me, what did she cook for them? | Dime, ¿qué cocinó ella para ellos? | Question
🍲 | He cooked dinner for him. | Él cocinó la cena para él. | Look, he cooked dinner for him. | Mira, él cocinó la cena para él. | Phrase
🍲 | He cooked dinner for her. | Él cocinó la cena para ella. | Look, he cooked dinner for her. | Mira, él cocinó la cena para ella. | Phrase
🍲 | He cooked dinner for them. | Él cocinó la cena para ellos. | Look, he cooked dinner for them. | Mira, él cocinó la cena para ellos. | Phrase
🍲 | She cooked dinner for him. | Ella cocinó la cena para él. | Look, she cooked dinner for him. | Mira, ella cocinó la cena para él. | Phrase
🍲 | She cooked dinner for her. | Ella cocinó la cena para ella. | Look, she cooked dinner for her. | Mira, ella cocinó la cena para ella. | Phrase
🍲 | She cooked dinner for them. | Ella cocinó la cena para ellos. | Look, she cooked dinner for them. | Mira, ella cocinó la cena para ellos. | Phrase
🛍️ | Could you carry these bags for me? | ¿Podrías cargar estas bolsas por mí? | Please, could you carry these bags for me? | Por favor, ¿podrías cargar estas bolsas por mí? | Question
👍 | Sure. No problem. | Claro. No hay problema. | Yes, sure. No problem. | Sí, claro. No hay problema. | Phrase
❤️ | Be helpful. | Sé servicial. | Always be helpful. | Siempre sé servicial. | Phrase
🎨 | Painting | Pintura | This is a painting | Esta es una pintura | Word
📸 | Photograph | Fotografía | The photograph is old | La fotografía es vieja | Word
🧩 | Mosaic | Mosaico | I like this mosaic | Me gusta este mosaico | Word
🗿 | Sculpture | Escultura | The sculpture is big | La escultura es grande | Word
🖼️ | This is a painting of a bedroom. | Esta es una pintura de un dormitorio. | Look, this is a painting of a bedroom. | Mira, esta es una pintura de un dormitorio. | Phrase
    """.trimIndent(), "Basics 4 - List 6")

    private fun getList7(): List<Flashcard> = parseCards("""
🎭 | Actor | Actor | He is an actor | Él es un actor | Word
🎨 | Artist | Artista | The artist paints a picture | El artista pinta un cuadro | Word
🎵 | Musician | Músico | The musician plays music | El músico toca música | Word
🎮 | Game designer | Diseñador de videojuegos | I want to be a game designer | Quiero ser diseñador de videojuegos | Word
📰 | Journalist | Periodista | The journalist writes a story | El periodista escribe una historia | Word
🔬 | Scientist | Científico | The scientist works in a lab | El científico trabaja en un laboratorio | Word
🤔 | What do you want to be when you grow up? | ¿Qué quieres ser cuando seas grande? | Tell me, what do you want to be when you grow up? | Dime, ¿qué quieres ser cuando seas grande? | Question
🎭 | I want to be an actor. | Quiero ser actor. | Look, I want to be an actor. | Mira, quiero ser actor. | Phrase
🎨 | I want to be an artist. | Quiero ser artista. | Look, I want to be an artist. | Mira, quiero ser artista. | Phrase
🎵 | I want to be a musician. | Quiero ser músico. | Look, I want to be a musician. | Mira, quiero ser músico. | Phrase
🎮 | I want to be a game designer. | Quiero ser diseñador de videojuegos. | Look, I want to be a game designer. | Mira, quiero ser diseñador de videojuegos. | Phrase
📰 | I want to be a journalist. | Quiero ser periodista. | Look, I want to be a journalist. | Mira, quiero ser periodista. | Phrase
🔬 | I want to be a scientist. | Quiero ser científico. | Look, I want to be a scientist. | Mira, quiero ser científico. | Phrase
🤔 | What does he want to be when he grows up? | ¿Qué quiere ser él cuando sea grande? | Tell me, what does he want to be when he grows up? | Dime, ¿qué quiere ser él cuando sea grande? | Question
🤔 | What does she want to be when she grows up? | ¿Qué quiere ser ella cuando sea grande? | Tell me, what does she want to be when she grows up? | Dime, ¿qué quiere ser ella cuando sea grande? | Question
🎭 | He wants to be an actor. | Él quiere ser actor. | Look, he wants to be an actor. | Mira, él quiere ser actor. | Phrase
🎭 | She wants to be an actor. | Ella quiere ser actor. | Look, she wants to be an actor. | Mira, ella quiere ser actor. | Phrase
🎨 | He wants to be an artist. | Él quiere ser artista. | Look, he wants to be an artist. | Mira, él quiere ser artista. | Phrase
🎨 | She wants to be an artist. | Ella quiere ser artista. | Look, she wants to be an artist. | Mira, ella quiere ser artista. | Phrase
🎵 | He wants to be a musician. | Él quiere ser músico. | Look, he wants to be a musician. | Mira, él quiere ser músico. | Phrase
🎵 | She wants to be a musician. | Ella quiere ser músico. | Look, she wants to be a musician. | Mira, ella quiere ser músico. | Phrase
🎮 | He wants to be a game designer. | Él quiere ser diseñador de videojuegos. | Look, he wants to be a game designer. | Mira, él quiere ser diseñador de videojuegos. | Phrase
🎮 | She wants to be a game designer. | Ella quiere ser diseñadora de videojuegos. | Look, she wants to be a game designer. | Mira, ella quiere ser diseñadora de videojuegos. | Phrase
📰 | He wants to be a journalist. | Él quiere ser periodista. | Look, he wants to be a journalist. | Mira, él quiere ser periodista. | Phrase
📰 | She wants to be a journalist. | Ella quiere ser periodista. | Look, she wants to be a journalist. | Mira, ella quiere ser periodista. | Phrase
🔬 | He wants to be a scientist. | Él quiere ser científico. | Look, he wants to be a scientist. | Mira, él quiere ser científico. | Phrase
🔬 | She wants to be a scientist. | Ella quiere ser científica. | Look, she wants to be a scientist. | Mira, ella quiere ser científica. | Phrase
🚀 | Go to space | Ir al espacio | I want to go to space | Quiero ir al espacio | Phrase
🚁 | Fly a helicopter | Volar un helicóptero | He can fly a helicopter | Él puede volar un helicóptero | Phrase
🐾 | Work with animals | Trabajar con animales | The vet likes to work with animals | Al veterinario le gusta trabajar con animales | Phrase
🏎️ | Drive a race car | Conducir un auto de carreras | He wants to drive a race car | Él quiere conducir un auto de carreras | Phrase
🌴 | Explore the jungle | Explorar la selva | We explore the jungle | Exploramos la selva | Phrase
🌍 | Travel the world | Viajar por el mundo | I want to travel the world | Quiero viajar por el mundo | Phrase
🤔 | What do you want to do when you're older? | ¿Qué quieres hacer cuando seas mayor? | Tell me, what do you want to do when you're older? | Dime, ¿qué quieres hacer cuando seas mayor? | Question
🚀 | I want to go to space. | Quiero ir al espacio. | Look, I want to go to space. | Mira, quiero ir al espacio. | Phrase
🚁 | I want to fly a helicopter. | Quiero volar un helicóptero. | Look, I want to fly a helicopter. | Mira, quiero volar un helicóptero. | Phrase
🐾 | I want to work with animals. | Quiero trabajar con animales. | Look, I want to work with animals. | Mira, quiero trabajar con animales. | Phrase
🏎️ | I want to drive a race car. | Quiero conducir un auto de carreras. | Look, I want to drive a race car. | Mira, quiero conducir un auto de carreras. | Phrase
🌴 | I want to explore the jungle. | Quiero explorar la selva. | Look, I want to explore the jungle. | Mira, quiero explorar la selva. | Phrase
🌍 | I want to travel the world. | Quiero viajar por el mundo. | Look, I want to travel the world. | Mira, quiero viajar por el mundo. | Phrase
🤔 | What does he want to do when he's older? | ¿Qué quiere hacer él cuando sea mayor? | Tell me, what does he want to do when he's older? | Dime, ¿qué quiere hacer él cuando sea mayor? | Question
🤔 | What does she want to do when she's older? | ¿Qué quiere hacer ella cuando sea mayor? | Tell me, what does she want to do when she's older? | Dime, ¿qué quiere hacer ella cuando sea mayor? | Question
🚀 | He wants to go to space. | Él quiere ir al espacio. | Look, he wants to go to space. | Mira, él quiere ir al espacio. | Phrase
🚀 | She wants to go to space. | Ella quiere ir al espacio. | Look, she wants to go to space. | Mira, ella quiere ir al espacio. | Phrase
🚁 | He wants to fly a helicopter. | Él quiere volar un helicóptero. | Look, he wants to fly a helicopter. | Mira, él quiere volar un helicóptero. | Phrase
🚁 | She wants to fly a helicopter. | Ella quiere volar un helicóptero. | Look, she wants to fly a helicopter. | Mira, ella quiere volar un helicóptero. | Phrase
🐾 | He wants to work with animals. | Él quiere trabajar con animales. | Look, he wants to work with animals. | Mira, él quiere trabajar con animales. | Phrase
🐾 | She wants to work with animals. | Ella quiere trabajar con animales. | Look, she wants to work with animals. | Mira, ella quiere trabajar con animales. | Phrase
🏎️ | He wants to drive a race car. | Él quiere conducir un auto de carreras. | Look, he wants to drive a race car. | Mira, él quiere conducir un auto de carreras. | Phrase
🏎️ | She wants to drive a race car. | Ella quiere conducir un auto de carreras. | Look, she wants to drive a race car. | Mira, ella quiere conducir un auto de carreras. | Phrase
🌴 | He wants to explore the jungle. | Él quiere explorar la selva. | Look, he wants to explore the jungle. | Mira, él quiere explorar la selva. | Phrase
🌴 | She wants to explore the jungle. | Ella quiere explorar la selva. | Look, she wants to explore the jungle. | Mira, ella quiere explorar la selva. | Phrase
🌍 | He wants to travel the world. | Él quiere viajar por el mundo. | Look, he wants to travel the world. | Mira, él quiere viajar por el mundo. | Phrase
🌍 | She wants to travel the world. | Ella quiere viajar por el mundo. | Look, she wants to travel the world. | Mira, ella quiere viajar por el mundo. | Phrase
🏛️ | Space Museum | Museo del espacio | We visit the Space Museum | Visitamos el Museo del espacio | Phrase
🪧 | What does that sign mean? | ¿Qué significa ese cartel? | Tell me, what does that sign mean? | Dime, ¿qué significa ese cartel? | Question
⚠️ | It means you can't run here. | Significa que no puedes correr aquí. | Look, it means you can't run here. | Mira, significa que no puedes correr aquí. | Phrase
⏳ | Be patient. | Ten paciencia. | Please, be patient. | Por favor, ten paciencia. | Phrase
🚀 | Space shuttle | Transbordador espacial | The space shuttle is fast | El transbordador espacial es rápido | Word
🛸 | Space station | Estación espacial | The space station is in orbit | La estación espacial está en órbita | Word
🧑‍🚀 | Space suit | Traje espacial | The astronaut wears a space suit | El astronauta usa un traje espacial | Word
🌍 | Earth | Tierra | We live on Earth | Vivimos en la Tierra | Word
🧑‍🚀 | Astronauts have to take the space shuttle to get to the space station. | Los astronautas tienen que tomar el transbordador espacial para llegar a la estación espacial. | Look, astronauts have to take the space shuttle to get to the space station. | Mira, los astronautas tienen que tomar el transbordador espacial para llegar a la estación espacial. | Phrase
🧑‍🚀 | Astronauts don't have to take the space shuttle to get to the space station. | Los astronautas no tienen que tomar el transbordador espacial para llegar a la estación espacial. | No, astronauts don't have to take the space shuttle to get to the space station. | No, los astronautas no tienen que tomar el transbordador espacial para llegar a la estación espacial. | Phrase
🧑‍🚀 | Astronauts have to wear a space suit in the space station. | Los astronautas tienen que usar un traje espacial en la estación espacial. | Look, astronauts have to wear a space suit in the space station. | Mira, los astronautas tienen que usar un traje espacial en la estación espacial. | Phrase
🧑‍🚀 | Astronauts don't have to wear a space suit in the space station. | Los astronautas no tienen que usar un traje espacial en la estación espacial. | No, astronauts don't have to wear a space suit in the space station. | No, los astronautas no tienen que usar un traje espacial en la estación espacial. | Phrase
    """.trimIndent(), "Basics 4 - List 7")

    private fun getList8(): List<Flashcard> = parseCards("""
⛵ | Take a boat ride | Dar un paseo en bote | I take a boat ride | Doy un paseo en bote | Phrase
🎭 | See a show | Ver un espectáculo | We see a show | Vemos un espectáculo | Phrase
🚌 | Go on a bus tour | Ir a un recorrido en autobús | I go on a bus tour | Voy a un recorrido en autobús | Phrase
🐎 | Ride a horse | Montar a caballo | He likes to ride a horse | A él le gusta montar a caballo | Phrase
🌊 | Swim in the ocean | Nadar en el océano | I swim in the ocean | Nado en el océano | Phrase
🏨 | Stay in a hotel | Quedarse en un hotel | We stay in a hotel | Nos quedamos en un hotel | Phrase
🤔 | What's he going to do on vacation? | ¿Qué va a hacer él en las vacaciones? | Tell me, what's he going to do on vacation? | Dime, ¿qué va a hacer él en las vacaciones? | Question
🤔 | What's she going to do on vacation? | ¿Qué va a hacer ella en las vacaciones? | Tell me, what's she going to do on vacation? | Dime, ¿qué va a hacer ella en las vacaciones? | Question
⛵ | He's going to take a boat ride. | Él va a dar un paseo en bote. | Look, he's going to take a boat ride. | Mira, él va a dar un paseo en bote. | Phrase
⛵ | She's going to take a boat ride. | Ella va a dar un paseo en bote. | Look, she's going to take a boat ride. | Mira, ella va a dar un paseo en bote. | Phrase
⛵ | When is he going to take a boat ride? | ¿Cuándo va él a dar un paseo en bote? | Tell me, when is he going to take a boat ride? | Dime, ¿cuándo va él a dar un paseo en bote? | Question
⛵ | When is she going to take a boat ride? | ¿Cuándo va ella a dar un paseo en bote? | Tell me, when is she going to take a boat ride? | Dime, ¿cuándo va ella a dar un paseo en bote? | Question
⛵ | He's going to take a boat ride tomorrow. | Él va a dar un paseo en bote mañana. | Look, he's going to take a boat ride tomorrow. | Mira, él va a dar un paseo en bote mañana. | Phrase
⛵ | She's going to take a boat ride tomorrow. | Ella va a dar un paseo en bote mañana. | Look, she's going to take a boat ride tomorrow. | Mira, ella va a dar un paseo en bote mañana. | Phrase
🩳 | Swimsuit | Traje de baño | I have a new swimsuit | Tengo un traje de baño nuevo | Word
🧖 | Towel | Toalla | The towel is blue | La toalla es azul | Word
💵 | Money | Dinero | I need money | Necesito dinero | Word
⛺ | Tent | Carpa / Tienda de campaña | The tent is big | La carpa es grande | Word
🔦 | Flashlight | Linterna | I use a flashlight | Uso una linterna | Word
🛌 | Sleeping bag | Saco de dormir | My sleeping bag is warm | Mi saco de dormir es cálido | Word
🧱 | What's he going to take with him? | ¿Qué va a llevar él consigo? | Tell me, what's he going to take with him? | Dime, ¿qué va a llevar él consigo? | Question
🧱 | What's she going to take with her? | ¿Qué va a llevar ella consigo? | Tell me, what's she going to take with her? | Dime, ¿qué va a llevar ella consigo? | Question
🩳 | He's going to take a swimsuit. | Él va a llevar un traje de baño. | Look, he's going to take a swimsuit. | Mira, él va a llevar un traje de baño. | Phrase
🩳 | She's going to take a swimsuit. | Ella va a llevar un traje de baño. | Look, she's going to take a swimsuit. | Mira, ella va a llevar un traje de baño. | Phrase
🧱 | Are they going to take swimsuits with them? | ¿Van a llevar trajes de baño con ellos? | Tell me, are they going to take swimsuits with them? | Dime, ¿van a llevar trajes de baño con ellos? | Question
👍 | Yes, they are. | Sí, sí van. | Yes, they are. | Sí, sí van. | Phrase
🙅 | No, they aren't. | No, no van. | No, they aren't. | No, no van. | Phrase
🌴 | Vacation Plans | Planes de vacaciones | Here are our vacation plans | Aquí están nuestros planes de vacaciones | Phrase
👋 | Bye. Have a great time! | Adiós. ¡Que lo pases muy bien! | Bye. Have a great time! | Adiós. ¡Que lo pases muy bien! | Phrase
🙏 | Thank you. See you next month. | Gracias. Nos vemos el próximo mes. | Thank you. See you next month. | Gracias. Nos vemos el próximo mes. | Phrase
❤️ | Be thoughtful. | Sé considerado. | Please, be thoughtful. | Por favor, sé considerado. | Phrase
🚕 | Taxi | Taxi | I take a taxi | Tomo un taxi | Word
⛴️ | Ferry | Ferry / Transbordador | The ferry crosses the river | El ferry cruza el río | Word
🚇 | Subway | Metro | I ride the subway | Ando en metro | Word
⛵ | Gondola | Góndola | The gondola is in Italy | La góndola está en Italia | Word
🏬 | How's he going to get to the department store? | ¿Cómo va él a llegar a la tienda departamental? | Tell me, how's he going to get to the department store? | Dime, ¿cómo va él a llegar a la tienda departamental? | Question
🏬 | How's she going to get to the department store? | ¿Cómo va ella a llegar a la tienda departamental? | Tell me, how's she going to get to the department store? | Dime, ¿cómo va ella a llegar a la tienda departamental? | Question
🏬 | How are they going to get to the department store? | ¿Cómo van ellos a llegar a la tienda departamental? | Tell me, how are they going to get to the department store? | Dime, ¿cómo van ellos a llegar a la tienda departamental? | Question
🚕 | He's going to take a taxi. | Él va a tomar un taxi. | Look, he's going to take a taxi. | Mira, él va a tomar un taxi. | Phrase
🚕 | She's going to take a taxi. | Ella va a tomar un taxi. | Look, she's going to take a taxi. | Mira, ella va a tomar un taxi. | Phrase
🚕 | They're going to take a taxi. | Ellos van a tomar un taxi. | Look, they're going to take a taxi. | Mira, ellos van a tomar un taxi. | Phrase
🎭 | He's going to see a show. | Él va a ver un espectáculo. | Look, he's going to see a show. | Mira, él va a ver un espectáculo. | Phrase
🎭 | She's going to see a show. | Ella va a ver un espectáculo. | Look, she's going to see a show. | Mira, ella va a ver un espectáculo. | Phrase
🚌 | He's going to go on a bus tour. | Él va a ir a un recorrido en autobús. | Look, he's going to go on a bus tour. | Mira, él va a ir a un recorrido en autobús. | Phrase
🚌 | She's going to go on a bus tour. | Ella va a ir a un recorrido en autobús. | Look, she's going to go on a bus tour. | Mira, ella va a ir a un recorrido en autobús. | Phrase
🐎 | He's going to ride a horse. | Él va a montar a caballo. | Look, he's going to ride a horse. | Mira, él va a montar a caballo. | Phrase
🐎 | She's going to ride a horse. | Ella va a montar a caballo. | Look, she's going to ride a horse. | Mira, ella va a montar a caballo. | Phrase
🌊 | He's going to swim in the ocean. | Él va a nadar en el océano. | Look, he's going to swim in the ocean. | Mira, él va a nadar en el océano. | Phrase
🌊 | She's going to swim in the ocean. | Ella va a nadar en el océano. | Look, she's going to swim in the ocean. | Mira, ella va a nadar en el océano. | Phrase
🏨 | He's going to stay in a hotel. | Él va a quedarse en un hotel. | Look, he's going to stay in a hotel. | Mira, él va a quedarse en un hotel. | Phrase
🏨 | She's going to stay in a hotel. | Ella va a quedarse en un hotel. | Look, she's going to stay in a hotel. | Mira, ella va a quedarse en un hotel. | Phrase
🤔 | When is he going to see a show? | ¿Cuándo va él a ver un espectáculo? | Tell me, when is he going to see a show? | Dime, ¿cuándo va él a ver un espectáculo? | Question
🤔 | When is she going to see a show? | ¿Cuándo va ella a ver un espectáculo? | Tell me, when is she going to see a show? | Dime, ¿cuándo va ella a ver un espectáculo? | Question
🚌 | When is he going to go on a bus tour? | ¿Cuándo va él a ir a un recorrido en autobús? | Tell me, when is he going to go on a bus tour? | Dime, ¿cuándo va él a ir a un recorrido en autobús? | Question
🚌 | When is she going to go on a bus tour? | ¿Cuándo va ella a ir a un recorrido en autobús? | Tell me, when is she going to go on a bus tour? | Dime, ¿cuándo va ella a ir a un recorrido en autobús? | Question
🐴 | When is he going to ride a horse? | ¿Cuándo va él a montar a caballo? | Tell me, when is he going to ride a horse? | Dime, ¿cuándo va él a montar a caballo? | Question
🐴 | When is she going to ride a horse? | ¿Cuándo va ella a montar a caballo? | Tell me, when is she going to ride a horse? | Dime, ¿cuándo va ella a montar a caballo? | Question
🌊 | When is he going to swim in the ocean? | ¿Cuándo va él a nadar en el océano? | Tell me, when is he going to swim in the ocean? | Dime, ¿cuándo va él a nadar en el océano? | Question
🌊 | When is she going to swim in the ocean? | ¿Cuándo va ella a nadar en el océano? | Tell me, when is she going to swim in the ocean? | Dime, ¿cuándo va ella a nadar en el océano? | Question
🤔 | When is he going to stay in a hotel? | ¿Cuándo va él a quedarse en un hotel? | Tell me, when is he going to stay in a hotel? | Dime, ¿cuándo va él a quedarse en un hotel? | Question
🤔 | When is she going to stay in a hotel? | ¿Cuándo va ella a quedarse en un hotel? | Tell me, when is she going to stay in a hotel? | Dime, ¿cuándo va ella a quedarse en un hotel? | Question
🎭 | He's going to see a show tomorrow. | Él va a ver un espectáculo mañana. | Look, he's going to see a show tomorrow. | Mira, él va a ver un espectáculo mañana. | Phrase
🎭 | She's going to see a show tomorrow. | Ella va a ver un espectáculo mañana. | Look, she's going to see a show tomorrow. | Mira, ella va a ver un espectáculo mañana. | Phrase
🚌 | He's going to go on a bus tour tomorrow. | Él va a ir a un recorrido en autobús mañana. | Look, he's going to go on a bus tour tomorrow. | Mira, él va a ir a un recorrido en autobús mañana. | Phrase
🚌 | She's going to go on a bus tour tomorrow. | Ella va a ir a un recorrido en autobús mañana. | Look, she's going to go on a bus tour tomorrow. | Mira, ella va a ir a un recorrido en autobús mañana. | Phrase
🐎 | He's going to ride a horse tomorrow. | Él va a montar a caballo mañana. | Look, he's going to ride a horse tomorrow. | Mira, él va a montar a caballo mañana. | Phrase
🐎 | She's going to ride a horse tomorrow. | Ella va a montar a caballo mañana. | Look, she's going to ride a horse tomorrow. | Mira, ella va a montar a caballo mañana. | Phrase
🌊 | He's going to swim in the ocean tomorrow. | Él va a nadar en el océano mañana. | Look, he's going to swim in the ocean tomorrow. | Mira, él va a nadar en el océano mañana. | Phrase
🌊 | She's going to swim in the ocean tomorrow. | Ella va a nadar en el océano mañana. | Look, she's going to swim in the ocean tomorrow. | Mira, ella va a nadar en el océano mañana. | Phrase
🏨 | He's going to stay in a hotel tomorrow. | Él va a quedarse en un hotel mañana. | Look, he's going to stay in a hotel tomorrow. | Mira, él va a quedarse en un hotel mañana. | Phrase
🏨 | She's going to stay in a hotel tomorrow. | Ella va a quedarse en un hotel mañana. | Look, she's going to stay in a hotel tomorrow. | Mira, ella va a quedarse en un hotel mañana. | Phrase
🧖 | He's going to take a towel. | Él va a llevar una toalla. | Look, he's going to take a towel. | Mira, él va a llevar una toalla. | Phrase
🧖 | She's going to take a towel. | Ella va a llevar una toalla. | Look, she's going to take a towel. | Mira, ella va a llevar una toalla. | Phrase
💵 | He's going to take money. | Él va a llevar dinero. | Look, he's going to take money. | Mira, él va a llevar dinero. | Phrase
💵 | She's going to take money. | Ella va a llevar dinero. | Look, she's going to take money. | Mira, ella va a llevar dinero. | Phrase
⛺ | He's going to take a tent. | Él va a llevar una carpa. | Look, he's going to take a tent. | Mira, él va a llevar una carpa. | Phrase
⛺ | She's going to take a tent. | Ella va a llevar una carpa. | Look, she's going to take a tent. | Mira, ella va a llevar una carpa. | Phrase
🔦 | He's going to take a flashlight. | Él va a llevar una linterna. | Look, he's going to take a flashlight. | Mira, él va a llevar una linterna. | Phrase
🔦 | She's going to take a flashlight. | Ella va a llevar una linterna. | Look, she's going to take a flashlight. | Mira, ella va a llevar una linterna. | Phrase
🛌 | He's going to take a sleeping bag. | Él va a llevar un saco de dormir. | Look, he's going to take a sleeping bag. | Mira, él va a llevar un saco de dormir. | Phrase
🛌 | She's going to take a sleeping bag. | Ella va a llevar un saco de dormir. | Look, she's going to take a sleeping bag. | Mira, ella va a llevar un saco de dormir. | Phrase
🧱 | Are they going to take towels with them? | ¿Van a llevar toallas con ellos? | Tell me, are they going to take towels with them? | Dime, ¿van a llevar toallas con ellos? | Question
💵 | Are they going to take money with them? | ¿Van a llevar dinero con ellos? | Tell me, are they going to take money with them? | Dime, ¿van a llevar dinero con ellos? | Question
🧱 | Are they going to take tents with them? | ¿Van a llevar carpas con ellos? | Tell me, are they going to take tents with them? | Dime, ¿van a llevar carpas con ellos? | Question
🧱 | Are they going to take flashlights with them? | ¿Van a llevar linternas con ellos? | Tell me, are they going to take flashlights with them? | Dime, ¿van a llevar linternas con ellos? | Question
😴 | Are they going to take sleeping bags with them? | ¿Van a llevar sacos de dormir con ellos? | Tell me, are they going to take sleeping bags with them? | Dime, ¿van a llevar sacos de dormir con ellos? | Question
⛴️ | He's going to take a ferry. | Él va a tomar un ferry. | Look, he's going to take a ferry. | Mira, él va a tomar un ferry. | Phrase
⛴️ | She's going to take a ferry. | Ella va a tomar un ferry. | Look, she's going to take a ferry. | Mira, ella va a tomar un ferry. | Phrase
⛴️ | They're going to take a ferry. | Ellos van a tomar un ferry. | Look, they're going to take a ferry. | Mira, ellos van a tomar un ferry. | Phrase
🚇 | He's going to take a subway. | Él va a tomar el metro. | Look, he's going to take a subway. | Mira, él va a tomar el metro. | Phrase
🚇 | She's going to take a subway. | Ella va a tomar el metro. | Look, she's going to take a subway. | Mira, ella va a tomar el metro. | Phrase
🚇 | They're going to take a subway. | Ellos van a tomar el metro. | Look, they're going to take a subway. | Mira, ellos van a tomar el metro. | Phrase
⛵ | He's going to take a gondola. | Él va a tomar una góndola. | Look, he's going to take a gondola. | Mira, él va a tomar una góndola. | Phrase
⛵ | She's going to take a gondola. | Ella va a tomar una góndola. | Look, she's going to take a gondola. | Mira, ella va a tomar una góndola. | Phrase
⛵ | They're going to take a gondola. | Ellos van a tomar una góndola. | Look, they're going to take a gondola. | Mira, ellos van a tomar una góndola. | Phrase
    """.trimIndent(), "Basics 4 - List 8")
}
