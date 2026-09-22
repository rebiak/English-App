package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object BasicsBooklet6 {
    const val FOLDER_NAME = "Basics 6"

    val categoryNames: List<String> = (1..8).map { "Basics 6 - List $it" }

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
                    cefrLevel = "B1",
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
🔄 | Review of level 5 | Repaso del nivel 5 | Let's review level 5 | Repasemos el nivel 5 | Phrase
🗣️ | Talking about languages of other countries | Hablando sobre idiomas de otros países | We are talking about languages of other countries | Estamos hablando sobre idiomas de otros países | Phrase
🇪🇬 | What language do they speak in Egypt? | ¿Qué idioma hablan en Egipto? | Tell me, what language do they speak in Egypt? | Dime, ¿qué idioma hablan en Egipto? | Question
⬆️ | Up the street | Calle arriba | Walk up the street | Camina calle arriba | Phrase
🌉 | Over the bridge | Sobre el puente | Walk over the bridge | Camina sobre el puente | Phrase
🚆 | Under the train tracks | Debajo de las vías del tren | Walk under the train tracks | Camina debajo de las vías del tren | Phrase
⛲ | Around the fountain | Alrededor de la fuente | Walk around the fountain | Camina alrededor de la fuente | Phrase
🌊 | Along the river | A lo largo del río | Walk along the river | Camina a lo largo del río | Phrase
⬇️ | Down the stairs | Escaleras abajo | Walk down the stairs | Camina escaleras abajo | Phrase
🧭 | Asking and giving directions | Preguntar y dar direcciones | We are asking and giving directions | Estamos preguntando y dando direcciones | Phrase
🤔 | How do I get to the hotel? | ¿Cómo llego al hotel? | Tell me, how do I get to the hotel? | Dime, ¿cómo llego al hotel? | Question
🚶 | First walk up the street and turn right. | Primero camina calle arriba y gira a la derecha. | Look, first walk up the street and turn right. | Mira, primero camina calle arriba y gira a la derecha. | Phrase
🚶 | Then, walk over the bridge and turn left. | Luego, camina sobre el puente y gira a la izquierda. | Look, then, walk over the bridge and turn left. | Mira, luego, camina sobre el puente y gira a la izquierda. | Phrase
🚕 | Catch a taxi | Tomar un taxi | I catch a taxi | Tomo un taxi | Phrase
⛴️ | Ride the ferry | Andar en ferry | I ride the ferry | Ando en ferry | Phrase
🚇 | Take the subway | Tomar el metro | I take the subway | Tomo el metro | Phrase
🚌 | Ride the bus | Andar en autobús | I ride the bus | Ando en autobús | Phrase
🚆 | Take the train | Tomar el tren | I take the train | Tomo el tren | Phrase
🚗 | Drive a car | Conducir un auto | I drive a car | Conduzco un auto | Phrase
🚗 | Talking about transportation using the past tense | Hablando sobre transporte usando el tiempo pasado | We are talking about transportation using the past tense | Estamos hablando sobre transporte usando el tiempo pasado | Phrase
🤔 | What did they do this morning? | ¿Qué hicieron esta mañana? | Tell me, what did they do this morning? | Dime, ¿qué hicieron esta mañana? | Question
🚕 | They caught a taxi to the zoo. | Tomaron un taxi al zoológico. | Look, they caught a taxi to the zoo. | Mira, tomamos un taxi al zoológico. | Phrase
🏛️ | How did you get to the museum? | ¿Cómo llegaste al museo? | Tell me, how did you get to the museum? | Dime, ¿cómo llegaste al museo? | Question
🚕 | We caught a taxi. | Tomamos un taxi. | Look, we caught a taxi. | Mira, tomamos un taxi. | Phrase
🎪 | Finding the Fair | Encontrando la feria | This is Finding the Fair | Esta es Encontrando la feria | Phrase
🧭 | Asking and giving directions | Preguntar y dar direcciones | We are asking and giving directions | Estamos preguntando y dando direcciones | Phrase
🎪 | We're looking for the art fair. | Estamos buscando la feria de arte. | Look, we're looking for the art fair. | Mira, estamos buscando la feria de arte. | Phrase
📍 | It's in that direction. | Está en esa dirección. | Look, it's in that direction. | Mira, está en esa dirección. | Phrase
👍 | OK. Thanks. | De acuerdo. Gracias. | Yes, OK. Thanks. | Sí, de acuerdo. Gracias. | Phrase
🤷 | Saying you don't know where a place is | Decir que no sabes dónde está un lugar | Saying you don't know where a place is is common | Decir que no sabes dónde está un lugar es común | Phrase
🎨 | The art fair? I don't know where it is. | ¿La feria de arte? No sé dónde está. | Tell me, the art fair? I don't know where it is. | Dime, ¿la feria de arte? No sé dónde está. | Question
🙏 | Thanks, anyway. | Gracias de todos modos. | Thanks, anyway. | Gracias de todos modos. | Phrase
🛡️ | Be prepared. | Prepárate. | Always be prepared. | Siempre prepárate. | Phrase
🏛️ | Capital | Capital | The capital is big | La capital es grande | Word
🏃 | Busy | Ocupado | The street is busy | La calle está ocupada | Word
⚖️ | Similar | Similar | They are similar | Son similares | Word
🐼 | Giant panda | Panda gigante | The giant panda eats bamboo | El panda gigante come bambú | Word
🎖️ | Honor | Honor | We honor them | Los honramos | Word
👀 | Observe | Observar | I observe the cat | Observo al gato | Word
📅 | Future with be going to for plans | Futuro con be going to para planes | We use the future with be going to for plans | Usamos el futuro con be going to para planes | Phrase
⛩️ | I'm going to Meiji Shrine on Friday afternoon. | Voy al santuario Meiji el viernes por la tarde. | Look, I'm going to Meiji Shrine on Friday afternoon. | Mira, voy al santuario Meiji el viernes por la tarde. | Phrase
🤔 | Asking and saying how you will get to a place | Preguntar y decir cómo llegarás a un lugar | We are asking and saying how you will get to a place | Estamos preguntando y diciendo cómo llegarás a un lugar | Phrase
🤔 | How will you get there? | ¿Cómo llegarás allí? | Tell me, how will you get there? | Dime, ¿cómo llegarás allí? | Question
🚆 | I'll take the train to Harajuku Station and walk to the shrine entrance. | Tomaré el tren hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Look, I'll take the train to Harajuku Station and walk to the shrine entrance. | Mira, tomaré el tren hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Phrase
⬆️ | First walk up the street and turn left. | Primero camina calle arriba y gira a la izquierda. | Look, first walk up the street and turn left. | Mira, primero camina calle arriba y gira a la izquierda. | Phrase
⬆️ | First walk over the bridge and turn right. | Primero camina sobre el puente y gira a la derecha. | Look, first walk over the bridge and turn right. | Mira, primero camina sobre el puente y gira a la derecha. | Phrase
⬆️ | First walk under the train tracks and turn left. | Primero camina debajo de las vías del tren y gira a la izquierda. | Look, first walk under the train tracks and turn left. | Mira, primero camina debajo de las vías del tren y gira a la izquierda. | Phrase
⬆️ | First walk around the fountain and turn right. | Primero camina alrededor de la fuente y gira a la derecha. | Look, first walk over the bridge and turn right. | Mira, primero camina alrededor de la fuente y gira a la derecha. | Phrase
⬆️ | First walk along the river and turn left. | Primero camina a lo largo del río y gira a la izquierda. | Look, first walk along the river and turn left. | Mira, primero camina a lo largo del río y gira a la izquierda. | Phrase
⬆️ | First walk down the stairs and turn right. | Primero camina escaleras abajo y gira a la derecha. | Look, first walk down the stairs and turn right. | Mira, primero camina escaleras abajo y gira a la derecha. | Phrase
⬆️ | Then, walk up the street and turn right. | Luego, camina calle arriba y gira a la derecha. | Look, then, walk up the street and turn right. | Mira, luego, camina calle arriba y gira a la derecha. | Phrase
⬆️ | Then, walk over the bridge and turn left. | Luego, camina sobre el puente y gira a la izquierda. | Look, then, walk over the bridge and turn left. | Mira, luego, camina sobre el puente y gira a la izquierda. | Phrase
⬆️ | Then, walk under the train tracks and turn right. | Luego, camina debajo de las vías del tren y gira a la derecha. | Look, then, walk under the train tracks and turn right. | Mira, luego, camina debajo de las vías del tren y gira a la derecha. | Phrase
⬆️ | Then, walk around the fountain and turn left. | Luego, camina alrededor de la fuente y gira a la izquierda. | Look, then, walk around the fountain and turn left. | Mira, luego, camina alrededor de la fuente y gira a la izquierda. | Phrase
⬆️ | Then, walk along the river and turn right. | Luego, camina a lo largo del río y gira a la derecha. | Look, then, walk along the river and turn right. | Mira, luego, camina a lo largo del río y gira a la derecha. | Phrase
⬆️ | Then, walk down the stairs and turn left. | Luego, camina escaleras abajo y gira a la izquierda. | Look, then, walk down the stairs and turn left. | Mira, luego, camina escaleras abajo y gira a la izquierda. | Phrase
🏛️ | How do I get to the museum? | ¿Cómo llego al museo? | Tell me, how do I get to the museum? | Dime, ¿cómo llego al museo? | Question
🏞️ | How do I get to the park? | ¿Cómo llego al parque? | Tell me, how do I get to the park? | Dime, ¿cómo llego al parque? | Question
🤔 | How do I get to the station? | ¿Cómo llego a la estación? | Tell me, how do I get to the station? | Dime, ¿cómo llego a la estación? | Question
🏬 | How do I get to the store? | ¿Cómo llego a la tienda? | Tell me, how do I get to the store? | Dime, ¿cómo llego a la tienda? | Question
🚕 | They caught a ferry to the zoo. | Tomaron un ferry al zoológico. | Look, they caught a ferry to the zoo. | Mira, tomaron un ferry al zoológico. | Phrase
🚕 | They caught the subway to the zoo. | Tomaron el metro al zoológico. | Look, they caught the subway to the zoo. | Mira, tomaron el metro al zoológico. | Phrase
🚕 | They caught a bus to the zoo. | Tomaron un autobús al zoológico. | Look, they caught a bus to the zoo. | Mira, tomaron un autobús al zoológico. | Phrase
🚕 | They caught the train to the zoo. | Tomaron el tren al zoológico. | Look, they caught the train to the zoo. | Mira, tomaron el tren al zoológico. | Phrase
🚕 | They drove a car to the zoo. | Condujeron un auto al zoológico. | Look, they drove a car to the zoo. | Mira, condujeron un auto al zoológico. | Phrase
🚕 | We caught a ferry. | Tomamos un ferry. | Look, we caught a ferry. | Mira, tomamos un ferry. | Phrase
🚕 | We caught the subway. | Tomamos el metro. | Look, we caught the subway. | Mira, tomamos el metro. | Phrase
🚕 | We caught a bus. | Tomamos un autobús. | Look, we caught a bus. | Mira, tomamos un autobús. | Phrase
🚕 | We caught the train. | Tomamos el tren. | Look, we caught the train. | Mira, tomamos el tren. | Phrase
🚕 | We drove a car. | Condujimos un auto. | Look, we drove a car. | Mira, condujimos un auto. | Phrase
⛩️ | I'm going to the capital on Friday afternoon. | Voy a la capital el viernes por la tarde. | Look, I'm going to the capital on Friday afternoon. | Mira, voy a la capital el viernes por la tarde. | Phrase
⛩️ | I'm going to the park on Friday afternoon. | Voy al parque el viernes por la tarde. | Look, I'm going to the park on Friday afternoon. | Mira, voy al parque el viernes por la tarde. | Phrase
⛩️ | I'm going to the museum on Friday afternoon. | Voy al museo el viernes por la tarde. | Look, I'm going to the museum on Friday afternoon. | Mira, voy al museo el viernes por la tarde. | Phrase
⛩️ | I'm going to the store on Friday afternoon. | Voy a la tienda el viernes por la tarde. | Look, I'm going to the store on Friday afternoon. | Mira, voy a la tienda el viernes por la tarde. | Phrase
🚆 | I'll take a taxi to Harajuku Station and walk to the shrine entrance. | Tomaré un taxi hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Look, I'll take a taxi to Harajuku Station and walk to the shrine entrance. | Mira, tomaré un taxi hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Phrase
🚆 | I'll take the ferry to Harajuku Station and walk to the shrine entrance. | Tomaré el ferry hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Look, I'll take the ferry to Harajuku Station and walk to the shrine entrance. | Mira, tomaré el ferry hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Phrase
🚆 | I'll take the subway to Harajuku Station and walk to the shrine entrance. | Tomaré el metro hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Look, I'll take the subway to Harajuku Station and walk to the shrine entrance. | Mira, tomaré el metro hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Phrase
🚆 | I'll take a bus to Harajuku Station and walk to the shrine entrance. | Tomaré un autobús hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Look, I'll take a bus to Harajuku Station and walk to the shrine entrance. | Mira, tomaré un autobús hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Phrase
🚆 | I'll drive a car to Harajuku Station and walk to the shrine entrance. | Conduciré un auto hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Look, I'll drive a car to Harajuku Station and walk to the shrine entrance. | Mira, conduciré un auto hasta la estación Harajuku y caminaré hasta la entrada del santuario. | Phrase
    """.trimIndent(), "Basics 6 - List 1")

    private fun getList2(): List<Flashcard> = parseCards("""
📚 | Read textbooks | Leer libros de texto | I read textbooks | Leo libros de texto | Phrase
💵 | Pay bills | Pagar facturas | I pay bills | Pago facturas | Phrase
🛏️ | Go to bed early | Irse a la cama temprano | I go to bed early | Me voy a la cama temprano | Phrase
🏠 | Repair the house | Reparar la casa | I repair the house | Reparo la casa | Phrase
💻 | Type a report | Escribir un informe | I type a report | Escribo un informe | Phrase
🧽 | Scrub the sink | Fregar el fregadero | I scrub the sink | Frego el fregadero | Phrase
➕ | Compound sentences with but and and | Oraciones compuestas con but y and | We use compound sentences with but and and | Usamos oraciones compuestas con but y and | Phrase
🔑 | Modal verb have to | Verbo modal have to | We use the modal verb have to | Usamos el verbo modal have to | Phrase
📚 | I have to read textbooks, but my parents don't have to. | Tengo que leer libros de texto, pero mis padres no tienen que hacerlo. | Look, I have to read textbooks, but my parents don't have to. | Mira, tengo que leer libros de texto, pero mis padres no tienen que hacerlo. | Phrase
📚 | I have to read textbooks, and my parents do, too. | Tengo que leer libros de texto, y mis padres también. | Look, I have to read textbooks, and my parents do, too. | Mira, tengo que leer libros de texto, y mis padres también. | Phrase
📚 | I don't have to read textbooks, but my son does. | No tengo que leer libros de texto, pero mi hijo sí. | Look, I don't have to read textbooks, but my son does. | Mira, no tengo que leer libros de texto, pero mi hijo sí. | Phrase
📚 | I don't have to read textbooks, but my daughter does. | No tengo que leer libros de texto, pero mi hija sí. | Look, I don't have to read textbooks, but my daughter does. | Mira, no tengo que leer libros de texto, pero mi hija sí. | Phrase
💵 | I don't have to pay bills, but my parents do. | No tengo que pagar facturas, pero mis padres sí. | Look, I don't have to pay bills, but my parents do. | Mira, no tengo que pagar facturas, pero mis padres sí. | Phrase
📞 | Call friends | Llamar a amigos | I call friends | Llamo a amigos | Phrase
🌙 | Stay up late | Quedarse hasta tarde | I stay up late | Me quedo hasta tarde | Phrase
🤝 | Invite a friend over | Invitar a un amigo a casa | I invite a friend over | Invito a un amigo a casa | Phrase
🍿 | Watch a scary movie | Ver una película de terror | I watch a scary movie | Veo una película de terror | Phrase
🎤 | Sing karaoke | Cantar karaoke | I sing karaoke | Canto karaoke | Phrase
🎉 | Have a party | Tener una fiesta | I have a party | Tengo una fiesta | Phrase
➕ | Compound sentences with but and and | Oraciones compuestas con but y and | We use compound sentences with but and and | Usamos oraciones compuestas con but y and | Phrase
🔑 | Modal verbs can and could | Verbos modales can y could | We use the modal verbs can and could | Usamos los verbos modales can y could | Phrase
📞 | I can call friends on weekends, but I can't call friends on weekdays. | Puedo llamar a amigos los fines de semana, pero no puedo llamar a amigos los días de semana. | Look, I can call friends on weekends, but I can't call friends on weekdays. | Mira, puedo llamar a amigos los fines de semana, pero no puedo llamar a amigos los días de semana. | Phrase
📞 | My dad could call friends when he was my age, but my mom couldn't. | Mi papá podía llamar a amigos cuando tenía mi edad, pero mi mamá no. | Look, My dad could call friends when he was my age, but my mom couldn't. | Mira, mi papá podía llamar a amigos cuando tenía mi edad, pero mi mamá no. | Phrase
👶 | Babysitting | Cuidar niños | I like babysitting | Me gusta cuidar niños | Phrase
🤔 | Asking if something is mandatory | Preguntar si algo es obligatorio | We are asking if something is mandatory | Estamos preguntando si algo es obligatorio | Phrase
👶 | Do I have to babysit? | ¿Tengo que cuidar niños? | Tell me, do I have to babysit? | Dime, ¿tengo que cuidar niños? | Question
👍 | You don't have to, but you should. | No tienes que hacerlo, pero deberías. | Look, you don't have to, but you should. | Mira, no tienes que hacerlo, pero deberías. | Phrase
👍 | OK. I will. | De acuerdo. Lo haré. | Yes, OK. I will. | Sí, de acuerdo. Lo haré. | Phrase
👶 | Do I have to babysit? | ¿Tengo que cuidar niños? | Tell me, do I have to babysit? | Dime, ¿tengo que cuidar niños? | Question
👍 | Yes, I'm afraid you do. | Sí, me temo que sí. | Yes, I'm afraid you do. | Sí, me temo que sí. | Phrase
👍 | All right, Mom. | Está bien, mamá. | All right, Mom. | Está bien, mamá. | Phrase
❤️ | Be responsible. | Sé responsable. | Please, be responsible. | Por favor, sé responsable. | Phrase
➕ | Addition | Suma | Addition is easy | La suma es fácil | Word
🤔 | Something | Algo | I need something | Necesito algo | Word
🔢 | Order | Orden | Put it in order | Ponlo en orden | Word
➖ | Subtraction | Resta | Subtraction is math | La resta es matemática | Word
✖️ | Multiplication | Multiplicación | Multiplication is useful | La multiplicación es útil | Word
➗ | Division | División | Division is math | La división es matemática | Word
🔢 | Asking what is the best way to do math problems | Preguntar cuál es la mejor manera de hacer problemas de matemáticas | We are asking what is the best way to do math problems | Estamos preguntando cuál es la mejor manera de hacer problemas de matemáticas | Phrase
1️⃣ | What kind of math should you use for the first problem? | ¿Qué tipo de matemáticas debes usar para el primer problema? | Tell me, what kind of math should you use for the first problem? | Dime, ¿qué tipo de matemáticas debes usar para el primer problema? | Question
➕ | Addition. | Suma. | Look, addition. | Mira, suma. | Phrase
💵 | I have to pay bills, but my parents don't have to. | Tengo que pagar facturas, pero mis padres no tienen que hacerlo. | Look, I have to pay bills, but my parents don't have to. | Mira, tengo que pagar facturas, pero mis padres no tienen que hacerlo. | Phrase
💵 | I have to pay bills, and my parents do, too. | Tengo que pagar facturas, y mis padres también. | Look, I have to pay bills, and my parents do, too. | Mira, tengo que pagar facturas, y mis padres también. | Phrase
🛏️ | I have to go to bed early, but my parents don't have to. | Tengo que irme a la cama temprano, pero mis padres no tienen que hacerlo. | Look, I have to go to bed early, but my parents don't have to. | Mira, tengo que irme a la cama temprano, pero mis padres no tienen que hacerlo. | Phrase
🛏️ | I have to go to bed early, and my parents do, too. | Tengo que irme a la cama temprano, y mis padres también. | Look, I have to go to bed early, and my parents do, too. | Mira, tengo que irme a la cama temprano, y mis padres también. | Phrase
🏠 | I have to repair the house, but my parents don't have to. | Tengo que reparar la casa, pero mis padres no tienen que hacerlo. | Look, I have to repair the house, but my parents don't have to. | Mira, tengo que reparar la casa, pero mis padres no tienen que hacerlo. | Phrase
🏠 | I have to repair the house, and my parents do, too. | Tengo que reparar la casa, y mis padres también. | Look, I have to repair the house, and my parents do, too. | Mira, tengo que reparar la casa, y mis padres también. | Phrase
💻 | I have to type a report, but my parents don't have to. | Tengo que escribir un informe, pero mis padres no tienen que hacerlo. | Look, I have to type a report, but my parents don't have to. | Mira, tengo que escribir un informe, pero mis padres no tienen que hacerlo. | Phrase
💻 | I have to type a report, and my parents do, too. | Tengo que escribir un informe, y mis padres también. | Look, I have to type a report, and my parents do, too. | Mira, tengo que escribir un informe, y mis padres también. | Phrase
🧽 | I have to scrub the sink, but my parents don't have to. | Tengo que fregar el fregadero, pero mis padres no tienen que hacerlo. | Look, I have to scrub the sink, but my parents don't have to. | Mira, tengo que fregar el fregadero, pero mis padres no tienen que hacerlo. | Phrase
🧽 | I have to scrub the sink, and my parents do, too. | Tengo que fregar el fregadero, y mis padres también. | Look, I have to scrub the sink, and my parents do, too. | Mira, tengo que fregar el fregadero, y mis padres también. | Phrase
🛏️ | I don't have to go to bed early, but my son does. | No tengo que irme a la cama temprano, pero mi hijo sí. | Look, I don't have to go to bed early, but my son does. | Mira, no tengo que irme a la cama temprano, pero mi hijo sí. | Phrase
🛏️ | I don't have to go to bed early, but my daughter does. | No tengo que irme a la cama temprano, pero mi hija sí. | Look, I don't have to go to bed early, but my daughter does. | Mira, no tengo que irme a la cama temprano, pero mi hija sí. | Phrase
🏠 | I don't have to repair the house, but my son does. | No tengo que reparar la casa, pero mi hijo sí. | Look, I don't have to repair the house, but my son does. | Mira, no tengo que reparar la casa, pero mi hijo sí. | Phrase
🏠 | I don't have to repair the house, but my daughter does. | No tengo que reparar la casa, pero mi hija sí. | Look, I don't have to repair the house, but my daughter does. | Mira, no tengo que reparar la casa, pero mi hija sí. | Phrase
💻 | I don't have to type a report, but my son does. | No tengo que escribir un informe, pero mi hijo sí. | Look, I don't have to type a report, but my son does. | Mira, no tengo que escribir un informe, pero mi hijo sí. | Phrase
💻 | I don't have to type a report, but my daughter does. | No tengo que escribir un informe, pero mi hija sí. | Look, I don't have to type a report, but my daughter does. | Mira, no tengo que escribir un informe, pero mi hija sí. | Phrase
🧽 | I don't have to scrub the sink, but my son does. | No tengo que fregar el fregadero, pero mi hijo sí. | Look, I don't have to scrub the sink, but my son does. | Mira, no tengo que fregar el fregadero, pero mi hijo sí. | Phrase
🧽 | I don't have to scrub the sink, but my daughter does. | No tengo que fregar el fregadero, pero mi hija sí. | Look, I don't have to scrub the sink, but my daughter does. | Mira, no tengo que fregar el fregadero, pero mi hija sí. | Phrase
🌙 | I can stay up late on weekends, but I can't stay up late on weekdays. | Puedo quedarme hasta tarde los fines de semana, pero no puedo quedarme hasta tarde los días de semana. | Look, I can stay up late on weekends, but I can't stay up late on weekdays. | Mira, puedo quedarme hasta tarde los fines de semana, pero no puedo quedarme hasta tarde los días de semana. | Phrase
🤝 | I can invite a friend over on weekends, but I can't invite a friend over on weekdays. | Puedo invitar a un amigo a casa los fines de semana, pero no puedo invitar a un amigo a casa los días de semana. | Look, I can invite a friend over on weekends, but I can't invite a friend over on weekdays. | Mira, puedo invitar a un amigo a casa los fines de semana, pero no puedo invitar a un amigo a casa los días de semana. | Phrase
🍿 | I can watch a scary movie on weekends, but I can't watch a scary movie on weekdays. | Puedo ver una película de terror los fines de semana, pero no puedo ver una película de terror los días de semana. | Look, I can watch a scary movie on weekends, but I can't watch a scary movie on weekdays. | Mira, puedo ver una película de terror los fines de semana, pero no puedo ver una película de terror los días de semana. | Phrase
🎤 | I can sing karaoke on weekends, but I can't sing karaoke on weekdays. | Puedo cantar karaoke los fines de semana, pero no puedo cantar karaoke los días de semana. | Look, I can sing karaoke on weekends, but I can't sing karaoke on weekdays. | Mira, puedo cantar karaoke los fines de semana, pero no puedo cantar karaoke los días de semana. | Phrase
🎉 | I can have a party on weekends, but I can't have a party on weekdays. | Puedo tener una fiesta los fines de semana, pero no puedo tener una fiesta los días de semana. | Look, I can have a party on weekends, but I can't have a party on weekdays. | Mira, puedo tener una fiesta los fines de semana, pero no puedo tener una fiesta los días de semana. | Phrase
🌙 | My dad could stay up late when he was my age, but my mom couldn't. | Mi papá podía quedarse hasta tarde cuando tenía mi edad, pero mi mamá no. | Look, My dad could stay up late when he was my age, but my mom couldn't. | Mira, mi papá podía quedarse hasta tarde cuando tenía mi edad, pero mi mamá no. | Phrase
🤝 | My dad could invite a friend over when he was my age, but my mom couldn't. | Mi papá podía invitar a un amigo a casa cuando tenía mi edad, pero mi mamá no. | Look, My dad could invite a friend over when he was my age, but my mom couldn't. | Mira, mi papá podía invitar a un amigo a casa cuando tenía mi edad, pero mi mamá no. | Phrase
🍿 | My dad could watch a scary movie when he was my age, but my mom couldn't. | Mi papá podía ver una película de terror cuando tenía mi edad, pero mi mamá no. | Look, My dad could watch a scary movie when he was my age, but my mom couldn't. | Mira, mi papá podía ver una película de terror cuando tenía mi edad, pero mi mamá no. | Phrase
🎤 | My dad could sing karaoke when he was my age, but my mom couldn't. | Mi papá podía cantar karaoke cuando tenía mi edad, pero mi mamá no. | Look, My dad could sing karaoke when he was my age, but my mom couldn't. | Mira, mi papá podía cantar karaoke cuando tenía mi edad, pero mi mamá no. | Phrase
🎉 | My dad could have a party when he was my age, but my mom couldn't. | Mi papá podía tener una fiesta cuando tenía mi edad, pero mi mamá no. | Look, My dad could have a party when he was my age, but my mom couldn't. | Mira, mi papá podía tener una fiesta cuando tenía mi edad, pero mi mamá no. | Phrase
    """.trimIndent(), "Basics 6 - List 2")

    private fun getList3(): List<Flashcard> = parseCards("""
💻 | Own a laptop | Tener una computadora portátil | I own a laptop | Tengo una computadora portátil | Phrase
📚 | Like mystery novels | Gustar las novelas de misterio | I like mystery novels | Me gustan las novelas de misterio | Phrase
🥋 | Belong to the judo club | Pertenecer al club de judo | I belong to the judo club | Pertenezco al club de judo | Phrase
👥 | Know your best friend | Conocer a tu mejor amigo | I know my best friend | Conozco a mi mejor amigo | Phrase
🏫 | Be class president | Ser presidente de clase | I want to be class president | Quiero ser presidente de clase | Phrase
📱 | Have a smartphone | Tener un teléfono inteligente | I have a smartphone | Tengo un teléfono inteligente | Phrase
💻 | He's owned a laptop since September. | Él ha tenido una computadora portátil desde septiembre. | Look, he's owned a laptop since September. | Mira, él ha tenido una computadora portátil desde septiembre. | Phrase
💻 | He's owned a laptop for two years. | Él ha tenido una computadora portátil durante dos años. | Look, he's owned a laptop for two years. | Mira, él ha tenido una computadora portátil durante dos años. | Phrase
🐍 | How long have you owned a laptop? | ¿Cuánto tiempo has tenido una computadora portátil? | Tell me, how long have you owned a laptop? | Dime, ¿cuánto tiempo has tenido una computadora portátil? | Question
💻 | I've owned a laptop since May. | He tenido una computadora portátil desde mayo. | Look, I've owned a laptop since mayo. | Mira, he tenido una computadora portátil desde mayo. | Phrase
💻 | I've owned a laptop for six months. | He tenido una computadora portátil durante seis meses. | Look, I've owned a laptop for six months. | Mira, he tenido una computadora portátil durante seis meses. | Phrase
🇫🇷 | Live in Paris | Vivir en París | I live in Paris | Vivo en París | Phrase
🎷 | Play the saxophone | Tocar el saxofón | I play the saxophone | Toco el saxofón | Phrase
💻 | Build websites | Construir sitios web | I build websites | Construyo sitios web | Phrase
🥣 | Volunteer at a soup kitchen | Ser voluntario en un comedor social | I volunteer at a soup kitchen | Soy voluntario en un comedor social | Phrase
📚 | Collect comic books | Coleccionar cómics | I collect comic books | Colecciono cómics | Phrase
🩰 | Study ballet | Estudiar ballet | I study ballet | Estudio ballet | Phrase
🇫🇷 | We've been living in Paris since we were ten. | Hemos estado viviendo en París desde que teníamos diez años. | Look, We've been living in Paris since we were ten. | Mira, hemos estado viviendo en París desde que teníamos diez años. | Phrase
🇫🇷 | They've been living in Paris since they were ten. | Han estado viviendo en París desde que tenían diez años. | Look, They've been living in Paris since they were ten. | Mira, han estado viviendo en París desde que tenían diez años. | Phrase
🇫🇷 | We've been living in Paris for a long time. | Hemos estado viviendo en París durante mucho tiempo. | Look, We've been living in Paris for a long time. | Mira, hemos estado viviendo en París durante mucho tiempo. | Phrase
🇫🇷 | They've been living in Paris for a long time. | Han estado viviendo en París durante mucho tiempo. | Look, They've been living in Paris for a long time. | Mira, han estado viviendo en París durante mucho tiempo. | Phrase
🐍 | How long have you been living in Paris? | ¿Cuánto tiempo has estado viviendo en París? | Tell me, How long have you been living in Paris? | Dime, ¿cuánto tiempo has estado viviendo en París? | Question
🇫🇷 | Since I was ten. | Desde que tenía diez años. | Since I was ten. | Desde que tenía diez años. | Phrase
🇫🇷 | For five years. | Por cinco años. | For five years. | Por cinco años. | Phrase
🚶 | The Walk-a-Thon | La caminata benéfica | This is The Walk-a-Thon | Esta es La caminata benéfica | Phrase
🚶 | I've been walking all morning. | He estado caminando toda la mañana. | Look, I've been walking all morning. | Mira, he estado caminando toda la mañana. | Phrase
😴 | You must be tired. | Debes estar cansado. | You must be tired. | Debes estar cansado. | Phrase
👍 | I sure am! | ¡Claro que sí! | I sure am! | ¡Claro que sí! | Phrase
☕ | You should take a break. | Deberías tomar un descanso. | You should take a break. | Deberías tomar un descanso. | Phrase
👍 | I think you're right. | Creo que tienes razón. | I think you're right. | Creo que tienes razón. | Phrase
⏰ | Be on time. | Sé puntual. | Please, be on time. | Por favor, sé puntual. | Phrase
⏳ | Century | Siglo | A century is one hundred years | Un siglo son cien años | Word
📜 | Papyrus | Papiro | Papyrus was used to write | El papiro se usaba para escribir | Word
💡 | Introduce | Presentar / Introducir | I introduce my friend | Presento a mi amigo | Word
🛠️ | Tool | Herramienta | The tool is useful | La herramienta es útil | Word
🪖 | Samurai helmet | Casco de samurái | The samurai helmet is old | El casco de samurái es viejo | Word
📐 | Edge | Borde | The edge is sharp | El borde está afilado | Word
👨 | I've finished step 3. What do I do next? | He terminado el paso 3. ¿Qué hago después? | Tell me, I've finished step 3. What do I do next? | Dime, he terminado el paso 3. ¿Qué hago después? | Question
📄 | For step 4, you should fold the corners up to the top again. | Para el paso 4, debes doblar las esquinas hacia arriba nuevamente. | Look, For step 4, you should fold the corners up to the top again. | Mira, para el paso 4, debes doblar las esquinas hacia arriba nuevamente. | Phrase
📱 | He's owned a smartphone since September. | Él ha tenido un teléfono inteligente desde septiembre. | Look, He's owned a smartphone since September. | Mira, él ha tenido un teléfono inteligente desde septiembre. | Phrase
📱 | He's owned a smartphone for two years. | Él ha tenido un teléfono inteligente durante dos años. | Look, He's owned a smartphone for two years. | Mira, él ha tenido un teléfono inteligente durante dos años. | Phrase
📱 | I've owned a smartphone since May. | He tenido un teléfono inteligente desde mayo. | Look, I've owned a smartphone since May. | Mira, he tenido un teléfono inteligente desde mayo. | Phrase
📱 | I've owned a smartphone for six months. | He tenido un teléfono inteligente durante seis meses. | Look, I've owned a smartphone for six months. | Mira, he tenido un teléfono inteligente durante seis meses. | Phrase
🎷 | He's played the saxophone since September. | Él ha tocado el saxofón desde septiembre. | Look, He's played the saxophone since September. | Mira, él ha tocado el saxofón desde septiembre. | Phrase
🎷 | He's played the saxophone for two years. | Él ha tocado el saxofón durante dos años. | Look, He's played the saxophone for two years. | Mira, él ha tocado el saxofón durante dos años. | Phrase
💻 | He's built websites since September. | Él ha construido sitios web desde septiembre. | Look, He's built websites since September. | Mira, él ha construido sitios web desde septiembre. | Phrase
💻 | He's built websites for two years. | Él ha construido sitios web durante dos años. | Look, He's built websites for two years. | Mira, él ha construido sitios web durante dos años. | Phrase
🥣 | He's volunteered at a soup kitchen since September. | Él ha sido voluntario en un comedor social desde septiembre. | Look, He's volunteered at a soup kitchen since September. | Mira, él ha sido voluntario en un comedor social desde septiembre. | Phrase
🥣 | He's volunteered at a soup kitchen for two years. | Él ha sido voluntario en un comedor social durante dos años. | Look, He's volunteered at a soup kitchen for two years. | Mira, él ha sido voluntario en un comedor social durante dos años. | Phrase
📚 | He's collected comic books since September. | Él ha coleccionado cómics desde septiembre. | Look, He's collected comic books since September. | Mira, él ha coleccionado cómics desde septiembre. | Phrase
📚 | He's collected comic books for two years. | Él ha coleccionado cómics durante dos años. | Look, He's collected comic books for two years. | Mira, él ha coleccionado cómics durante dos años. | Phrase
🩰 | He's studied ballet since September. | Él ha estudiado ballet desde septiembre. | Look, He's studied ballet since September. | Mira, él ha estudiado ballet desde septiembre. | Phrase
🩰 | He's studied ballet for two years. | Él ha estudiado ballet durante dos años. | Look, He's studied ballet for two years. | Mira, él ha estudiado ballet durante dos años. | Phrase
🐍 | How long have you played the saxophone? | ¿Cuánto tiempo has tocado el saxofón? | Tell me, How long have you played the saxophone? | Dime, ¿cuánto tiempo has tocado el saxofón? | Question
🐍 | How long have you built websites? | ¿Cuánto tiempo has construido sitios web? | Tell me, How long have you built websites? | Dime, ¿cuánto tiempo has construido sitios web? | Question
🍳 | How long have you volunteered at a soup kitchen? | ¿Cuánto tiempo has sido voluntario en un comedor social? | Tell me, How long have you volunteered at a soup kitchen? | Dime, ¿cuánto tiempo has sido voluntario en un comedor social? | Question
📚 | How long have you collected comic books? | ¿Cuánto tiempo has coleccionado cómics? | Tell me, How long have you collected comic books? | Dime, ¿cuánto tiempo has coleccionado cómics? | Question
🐍 | How long have you studied ballet? | ¿Cuánto tiempo has estudiado ballet? | Tell me, How long have you studied ballet? | Dime, ¿cuánto tiempo has estudiado ballet? | Question
🎷 | I've played the saxophone since May. | He tocado el saxofón desde mayo. | Look, I've played the saxophone since May. | Mira, he tocado el saxofón desde mayo. | Phrase
🎷 | I've played the saxophone for six months. | He tocado el saxofón durante seis meses. | Look, I've played the saxophone for six months. | Mira, he tocado el saxofón durante seis meses. | Phrase
💻 | I've built websites since May. | He construido sitios web desde mayo. | Look, I've built websites since May. | Mira, he construido sitios web desde mayo. | Phrase
💻 | I've built websites for six months. | He construido sitios web durante seis meses. | Look, I've built websites for six months. | Mira, he construido sitios web durante seis meses. | Phrase
🥣 | I've volunteered at a soup kitchen since May. | He sido voluntario en un comedor social desde mayo. | Look, I've volunteered at a soup kitchen since May. | Mira, he sido voluntario en un comedor social desde mayo. | Phrase
🥣 | I've volunteered at a soup kitchen for six months. | He sido voluntario en un comedor social durante seis meses. | Look, I've volunteered at a soup kitchen for six months. | Mira, he sido voluntario en un comedor social durante seis meses. | Phrase
📚 | I've collected comic books since May. | He coleccionado cómics desde mayo. | Look, I've collected comic books since May. | Mira, he coleccionado cómics desde mayo. | Phrase
📚 | I've collected comic books for six months. | He coleccionado cómics durante seis meses. | Look, I've collected comic books for six months. | Mira, he coleccionado cómics durante seis meses. | Phrase
🩰 | I've studied ballet since May. | He estudiado ballet desde mayo. | Look, I've studied ballet since May. | Mira, he estudiado ballet desde mayo. | Phrase
🩰 | I've studied ballet for six months. | He estudiado ballet durante seis meses. | Look, I've studied ballet for six months. | Mira, he estudiado ballet durante seis meses. | Phrase
    """.trimIndent(), "Basics 6 - List 3")

    private fun getList4(): List<Flashcard> = parseCards("""
🧼 | Soap | Jabón | I use soap | Uso jabón | Word
🌸 | Perfume | Perfume | The perfume smells good | El perfume huele bien | Word
🦷 | Toothpaste | Pasta de dientes | I buy toothpaste | Compro pasta de dientes | Word
🧴 | Hair gel | Gel para el cabello | He uses hair gel | Él usa gel para el cabello | Word
🧴 | Shampoo | Champú | I need shampoo | Necesito champú | Word
🌸 | Cologne | Colonia | The cologne is nice | La colonia es agradable | Word
🧼 | I went to the store because I needed a bar of soap. | Fui a la tienda porque necesitaba una barra de jabón. | Look, I went to the store because I needed a bar of soap. | Mira, fui a la tienda porque necesitaba una barra de jabón. | Phrase
🌸 | I went to the store because I wanted a bottle of perfume. | Fui a la tienda porque quería una botella de perfume. | Look, I went to the store because I wanted a bottle of perfume. | Mira, fui a la tienda porque quería una botella de perfume. | Phrase
🏬 | Why is she going to the store? | ¿Por qué va ella a la tienda? | Tell me, why is she going to the store? | Dime, ¿por qué va ella a la tienda? | Question
🧼 | Why is she going to the store? Because she needs a bar of soap. | ¿Por qué va ella a la tienda? Porque necesita una barra de jabón. | Why is she going to the store? Because she needs a bar of soap. | ¿Por qué va ella a la tienda? Porque necesita una barra de jabón. | Phrase
🌸 | Why is she going to the store? Because she wants a bottle of perfume. | ¿Por qué va ella a la tienda? Porque quiere una botella de perfume. | Why is she going to the store? Because she wants a bottle of perfume. | ¿Por qué va ella a la tienda? Porque quiere una botella de perfume. | Phrase
🧴 | Conditioner | Acondicionador | She uses conditioner | Ella usa acondicionador | Word
🧴 | Deodorant | Desodorante | He uses deodorant | Él usa desodorante | Word
🧴 | Mouthwash | Enjuague bucal | I use mouthwash | Uso enjuague bucal | Word
🧴 | Sunscreen | Protector solar | Put on sunscreen | Ponte protector solar | Word
🧴 | Lotion | Loción | The lotion is soft | La loción es suave | Word
🦷 | Dental floss | Hilo dental | I use dental floss | Uso hilo dental | Word
🧴 | You should use conditioner after you wash your hair. | Deberías usar acondicionador después de lavarte el cabello. | Look, you should use conditioner after you wash your hair. | Mira, deberías usar acondicionador después de lavarte el cabello. | Phrase
🧴 | You should use deodorant before you go to school. | Deberías usar desodorante antes de ir a la escuela. | Look, you should use deodorant before you go to school. | Mira, deberías usar desodorante antes de ir a la escuela. | Phrase
🧴 | He ran out of conditioner while he was at camp, so he bought some more. | Se le acabó el acondicionador mientras estaba en el campamento, así que compró más. | Look, he ran out of conditioner while he was at camp, so he bought some more. | Mira, se le acabó el acondicionador mientras estaba en el campamento, así que compró más. | Phrase
⭐ | The Talent Show | El show de talentos | This is The Talent Show | Este es El show de talentos | Phrase
⏳ | Saying it's not necessary to wait | Decir que no es necesario esperar | We are saying it's not necessary to wait | Estamos diciendo que no es necesario esperar | Phrase
🤔 | Should I wait until you're ready? | ¿Debería esperar hasta que estés listo? | Tell me, should I wait until you're ready? | Dime, ¿debería esperar hasta que estés listo? | Question
🚶 | No, just go without me. | No, vete sin mí. | No, just go without me. | No, vete sin mí. | Phrase
👍 | OK. See you there. | De acuerdo. Nos vemos allí. | Yes, OK. See you there. | Sí, de acuerdo. Nos vemos allí. | Phrase
🤝 | Agreeing to wait and go together | Aceptar esperar e ir juntos | We are agreeing to wait and go together | Estamos aceptando esperar e ir juntos | Phrase
🤔 | Should I wait until you are ready? | ¿Debería esperar hasta que estés listo? | Tell me, should I wait until you are ready? | Dime, ¿debería esperar hasta que estés listo? | Question
👍 | Yes, if you don't mind. | Sí, si no te importa. | Yes, if you don't mind. | Sí, si no te importa. | Phrase
👍 | No, not at all. | No, para nada. | No, not at all. | No, para nada. | Phrase
❤️ | Be thoughtful. | Sé considerado. | Please, be thoughtful. | Por favor, sé considerado. | Phrase
🌊 | Water | Agua | Water is important | El agua es importante | Word
🔄 | Cycle | Ciclo | The water cycle is natural | El ciclo del agua es natural | Word
☁️ | Evaporation | Evaporación | Evaporation is part of the cycle | La evaporación es parte del ciclo | Word
💨 | Water vapor | Vapor de agua | Water vapor rises | El vapor de agua asciende | Word
🌧️ | Condensation | Condensación | Condensation makes clouds | La condensación forma nubes | Word
🌧️ | Precipitation | Precipitación | Precipitation is rain | La precipitación es lluvia | Word
💧 | Collection | Recolección / Acumulación | Water collection in the ocean | Acumulación de agua en el océano | Word
☁️ | What happens after evaporation? | ¿Qué pasa después de la evaporación? | Tell me, what happens after evaporation? | Dime, ¿qué pasa después de la evaporación? | Question
☁️ | Condensation. | Condensación. | Look, condensation. | Mira, condensación. | Phrase
🌧️ | What is condensation? | ¿Qué es la condensación? | Tell me, what is condensation? | Dime, ¿qué es la condensación? | Question
☁️ | It's when the water vapor gets cold and becomes clouds. | Es cuando el vapor de agua se enfría y se convierte en nubes. | Look, it's when the water vapor gets cold and becomes clouds. | Mira, es cuando el vapor de agua se enfría y se convierte en nubes. | Phrase
🦷 | I went to the store because I needed toothpaste. | Fui a la tienda porque necesitaba pasta de dientes. | Look, I went to the store because I needed toothpaste. | Mira, fui a la tienda porque necesitaba pasta de dientes. | Phrase
🧴 | I went to the store because I needed hair gel. | Fui a la tienda porque necesitaba gel para el cabello. | Look, I went to the store because I needed hair gel. | Mira, fui a la tienda porque necesitaba gel para el cabello. | Phrase
🧴 | I went to the store because I needed shampoo. | Fui a la tienda porque necesitaba champú. | Look, I went to the store because I needed shampoo. | Mira, fui a la tienda porque necesitaba champú. | Phrase
🌸 | I went to the store because I needed cologne. | Fui a la tienda porque necesitaba colonia. | Look, I went to the store because I needed cologne. | Mira, fui a la tienda porque necesitaba colonia. | Phrase
🧴 | I went to the store because I wanted shampoo. | Fui a la tienda porque quería champú. | Look, I went to the store because I wanted shampoo. | Mira, fui a la tienda porque quería champú. | Phrase
🦷 | I went to the store because I wanted toothpaste. | Fui a la tienda porque quería pasta de dientes. | Look, I went to the store because I wanted toothpaste. | Mira, fui a la tienda porque quería pasta de dientes. | Phrase
🏬 | Why is he going to the store? | ¿Por qué va él a la tienda? | Tell me, why is he going to the store? | Dime, ¿por qué va él a la tienda? | Question
🧼 | Why is he going to the store? Because he needs soap. | ¿Por qué va él a la tienda? Porque necesita jabón. | Why is he going to the store? Because he needs soap. | ¿Por qué va él a la tienda? Porque necesita jabón. | Phrase
🦷 | Why is he going to the store? Because he needs toothpaste. | ¿Por qué va él a la tienda? Porque necesita pasta de dientes. | Why is he going to the store? Because he needs toothpaste. | ¿Por qué va él a la tienda? Porque necesita pasta de dientes. | Phrase
🧴 | Why is he going to the store? Because he needs hair gel. | ¿Por qué va él a la tienda? Porque necesita gel para el cabello. | Why is he going to the store? Because he needs hair gel. | ¿Por qué va él a la tienda? Porque necesita gel para el cabello. | Phrase
🧴 | Why is he going to the store? Because he needs shampoo. | ¿Por qué va él a la tienda? Porque necesita champú. | Why is he going to the store? Because he needs shampoo. | ¿Por qué va él a la tienda? Porque necesita champú. | Phrase
🌸 | Why is he going to the store? Because he needs cologne. | ¿Por qué va él a la tienda? Porque necesita colonia. | Why is he going to the store? Because he needs cologne. | ¿Por qué va él a la tienda? Porque necesita colonia. | Phrase
🧼 | Why is he going to the store? Because he wants soap. | ¿Por qué va él a la tienda? Porque quiere jabón. | Why is he going to the store? Because he wants soap. | ¿Por qué va él a la tienda? Porque quiere jabón. | Phrase
🌸 | Why is he going to the store? Because he wants perfume. | ¿Por qué va él a la tienda? Porque quiere perfume. | Why is he going to the store? Because he wants perfume. | ¿Por qué va él a la tienda? Porque quiere perfume. | Phrase
🦷 | Why is he going to the store? Because he wants toothpaste. | ¿Por qué va él a la tienda? Porque quiere pasta de dientes. | Why is he going to the store? Because he wants toothpaste. | ¿Por qué va él a la tienda? Porque quiere pasta de dientes. | Phrase
🧴 | Why is he going to the store? Because he wants hair gel. | ¿Por qué va él a la tienda? Porque quiere gel para el cabello. | Why is he going to the store? Because he wants hair gel. | ¿Por qué va él a la tienda? Porque quiere gel para el cabello. | Phrase
🧴 | Why is he going to the store? Because he wants shampoo. | ¿Por qué va él a la tienda? Porque quiere champú. | Why is he going to the store? Because he wants shampoo. | ¿Por qué va él a la tienda? Porque quiere champú. | Phrase
🌸 | Why is he going to the store? Because he wants cologne. | ¿Por qué va él a la tienda? Porque quiere colonia. | Why is he going to the store? Because he wants cologne. | ¿Por qué va él a la tienda? Porque quiere colonia. | Phrase
🧴 | You should use hair gel after you wash your hair. | Deberías usar gel para el cabello después de lavarte el cabello. | Look, you should use hair gel after you wash your hair. | Mira, deberías usar gel para el cabello después de lavarte el cabello. | Phrase
🧴 | You should use sunscreen before you go to school. | Deberías usar protector solar antes de ir a la escuela. | Look, you should use sunscreen before you go to school. | Mira, deberías usar protector solar antes de ir a la escuela. | Phrase
🧴 | He ran out of deodorant while he was at camp, so he bought some more. | Se le acabó el desodorante mientras estaba en el campamento, así que compró más. | Look, he ran out of deodorant while he was at camp, so he bought some more. | Mira, se le acabó el desodorante mientras estaba en el campamento, así que compró más. | Phrase
🧴 | He ran out of sunscreen while he was at camp, so he bought some more. | Se le acabó el protector solar mientras estaba en el campamento, así que compró más. | Look, he ran out of sunscreen while he was at camp, so he bought some more. | Mira, se le acabó el protector solar mientras estaba en el campamento, así que compró más. | Phrase
🧴 | He ran out of shampoo while he was at camp, so he bought some more. | Se le acabó el champú mientras estaba en el campamento, así que compró más. | Look, he ran out of shampoo while he was at camp, so he bought some more. | Mira, se le acabó el champú mientras estaba en el campamento, así que compró más. | Phrase
    """.trimIndent(), "Basics 6 - List 4")

    private fun getList5(): List<Flashcard> = parseCards("""
🥪 | Deli | Deli / Charcutería | I go to the deli | Voy a la charcutería (deli) | Word
🐶 | Pet shop | Tienda de mascotas | The pet shop has dogs | La tienda de mascotas tiene perros | Word
🏪 | Convenience store | Tienda de conveniencia | I buy water at the convenience store | Compro agua en la tienda de conveniencia | Word
💍 | Jewelry store | Joyería | The jewelry store sells rings | La joyería vende anillos | Word
🍕 | Pizzeria | Pizzería | We eat pizza at the pizzeria | Comemos pizza en la pizzería | Word
🍞 | Bakery | Panadería | The bakery smells like bread | La panadería huele a pan | Word
🔗 | Relative clauses with who | Oraciones relativas con who | We use relative clauses with who | Usamos oraciones relativas con who | Phrase
👦 | The boy who is going to the deli is my younger brother. | El niño que va a la charcutería (deli) es mi hermano menor. | Look, the boy who is going to the deli is my younger brother. | Mira, el niño que va a la charcutería (deli) es mi hermano menor. | Phrase
👧 | The girl who is going to the deli is my younger sister. | La niña que va a la charcutería (deli) es mi hermana menor. | Look, the girl who is going to the deli is my younger sister. | Mira, la niña que va a la charcutería (deli) es mi hermana menor. | Phrase
👦 | The boy who is going to the deli is my older brother. | El niño que va a la charcutería (deli) es mi hermano mayor. | Look, the boy who is going to the deli is my older brother. | Mira, el niño que va a la charcutería (deli) es mi hermano mayor. | Phrase
👧 | The girl who is going to the deli is my older sister. | La niña que va a la charcutería (deli) es mi hermana mayor. | Look, the girl who is going to the deli is my older sister. | Mira, la niña que va a la charcutería (deli) es mi hermana mayor. | Phrase
👦 | Which one is your younger brother? | ¿Cuál es tu hermano menor? | Tell me, which one is your younger brother? | Dime, ¿cuál es tu hermano menor? | Question
👧 | Which one is your younger sister? | ¿Cuál es tu hermana menor? | Tell me, which one is your younger sister? | Dime, ¿cuál es tu hermana menor? | Question
👦 | Which one is your older brother? | ¿Cuál es tu hermano mayor? | Tell me, which one is your older brother? | Dime, ¿cuál es tu hermano mayor? | Question
👧 | Which one is your older sister? | ¿Cuál es tu hermana mayor? | Tell me, which one is your older sister? | Dime, ¿cuál es tu hermana mayor? | Question
👦 | He's the one who is going to the deli. | Él es quien va a la charcutería (deli). | Look, he's the one who is going to the deli. | Mira, él es quien va a la charcutería (deli). | Phrase
👧 | She's the one who is going to the deli. | Ella es quien va a la charcutería (deli). | Look, she's the one who is going to the deli. | Mira, ella es quien va a la charcutería (deli). | Phrase
🛍️ | Mall | Centro comercial | I go to the mall | Voy al centro comercial | Word
🛹 | Skate park | Parque de patinaje | The skate park is fun | El parque de patinaje es divertido | Word
🏟️ | Sports stadium | Estadio deportivo | The sports stadium is big | El estadio deportivo es grande | Word
🏛️ | Science museum | Museo de ciencia | We visit the science museum | Visitamos el museo de ciencia | Word
🖼️ | Art gallery | Galería de arte | The art gallery has paintings | La galería de arte tiene pinturas | Word
🕹️ | Arcade | Salón de juegos | I play games at the arcade | Juego en el salón de juegos | Word
💬 | Questions and answers with reported speech | Preguntas y respuestas con estilo indirecto | We study questions and answers with reported speech | Estudiamos preguntas y respuestas con estilo indirecto | Phrase
🤔 | What did he say? | ¿Qué dijo él? | Tell me, what did he say? | Dime, ¿qué dijo él? | Question
🗣️ | What did he say? He said that he was going to the mall. | ¿Qué dijo él? Él dijo que iba al centro comercial. | What did he say? He said that he was going to the mall. | ¿Qué dijo él? Él dijo que iba al centro comercial. | Phrase
🛍️ | Did she say that she was going to the mall? | ¿Dijo ella que iba al centro comercial? | Tell me, Did she say that she was going to the mall? | Dime, ¿dijo ella que iba al centro comercial? | Question
🛍️ | Did she say that she was going to the mall? Yes, she did. | ¿Dijo ella que iba al centro comercial? Sí, ella lo dijo. | Did she say that she was going to the mall? Yes, she did. | ¿Dijo ella que iba al centro comercial? Sí, ella lo dijo. | Phrase
🛍️ | Did she say that she was going to the mall? No, she didn't. | ¿Dijo ella que iba al centro comercial? No, ella no lo dijo. | Did she say that she was going to the mall? No, she didn't. | ¿Dijo ella que iba al centro comercial? No, ella no lo dijo. | Phrase
🃏 | The Missing Card | La tarjeta perdida | This is The Missing Card | Esta es La tarjeta perdida | Phrase
❤️ | Expressing you liked something | Expresar que te gustó algo | We are expressing you liked something | Estamos expresando que te gustó algo | Phrase
🎁 | I really like the card you gave me for my birthday. | Me gusta mucho la tarjeta que me diste por mi cumpleaños. | Look, I really like the card you gave me for my birthday. | Mira, me gusta mucho la tarjeta que me diste por mi cumpleaños. | Phrase
👍 | Good. I'm glad you like it. | Bien. Me alegra que te guste. | Good. I'm glad you like it. | Bien. Me alegra que te guste. | Phrase
😊 | I'm happy to hear that. | Me alegra escuchar eso. | I'm happy to hear that. | Me alegra escuchar eso. | Phrase
⚠️ | Be careful. | Ten cuidado. | Please, be careful. | Por favor, ten cuidado. | Phrase
🦴 | Bone | Hueso | The bone is hard | El hueso es duro | Word
💪 | Muscle | Músculo | The muscle is strong | El músculo es fuerte | Word
🔗 | Ligament | Ligamento | The ligament connects bones | El ligamento conecta huesos | Word
🛡️ | Support | Soporte / Apoyo | We need support | Necesitamos apoyo | Word
🛡️ | Protect | Proteger | We protect our body | Protegemos nuestro cuerpo | Word
🔗 | Tendon | Tendón | The tendon connects muscle to bone | El tendón conecta el músculo con el hueso | Word
🤔 | Questions in the simple present | Preguntas en presente simple | We use questions in the simple present | Usamos preguntas en presente simple | Phrase
🤔 | What do bones do? | ¿Qué hacen los huesos? | Tell me, what do bones do? | Dime, ¿qué hacen los huesos? | Question
🛡️ | They support and protect your body. | Ellas apoyan y protegen tu cuerpo. / Ellos apoyan y protegen tu cuerpo. | Look, they support and protect your body. | Mira, apoyan y protegen tu cuerpo. | Phrase
👦 | The boy who is going to the pet shop is my younger brother. | El niño que va a la tienda de mascotas es mi hermano menor. | Look, the boy who is going to the pet shop is my younger brother. | Mira, el niño que va a la tienda de mascotas es mi hermano menor. | Phrase
👦 | The boy who is going to the convenience store is my younger brother. | El niño que va a la tienda de conveniencia es mi hermano menor. | Look, the boy who is going to the convenience store is my younger brother. | Mira, el niño que va a la tienda de conveniencia es mi hermano menor. | Phrase
👦 | The boy who is going to the jewelry store is my younger brother. | El niño que va a la joyería es mi hermano menor. | Look, the boy who is going to the jewelry store is my younger brother. | Mira, el niño que va a la joyería es mi hermano menor. | Phrase
👦 | The boy who is going to the pizzeria is my younger brother. | El niño que va a la pizzería es mi hermano menor. | Look, the boy who is going to the pizzeria is my younger brother. | Mira, el niño que va a la pizzería es mi hermano menor. | Phrase
👦 | The boy who is going to the bakery is my younger brother. | El niño que va a la panadería es mi hermano menor. | Look, the boy who is going to the bakery is my younger brother. | Mira, el niño que va a la panadería es mi hermano menor. | Phrase
👧 | The girl who is going to the pet shop is my younger sister. | La niña que va a la tienda de mascotas es mi hermana menor. | Look, the girl who is going to the pet shop is my younger sister. | Mira, la niña que va a la tienda de mascotas es mi hermana menor. | Phrase
👧 | The girl who is going to the convenience store is my younger sister. | La niña que va a la tienda de conveniencia es mi hermana menor. | Look, the girl who is going to the convenience store is my younger sister. | Mira, la niña que va a la tienda de conveniencia es mi hermana menor. | Phrase
👧 | The girl who is going to the jewelry store is my younger sister. | La niña que va a la joyería es mi hermana menor. | Look, the girl who is going to the jewelry store is my younger sister. | Mira, la niña que va a la joyería es mi hermana menor. | Phrase
👧 | The girl who is going to the pizzeria is my younger sister. | La niña que va a la pizzería es mi hermana menor. | Look, the girl who is going to the pizzeria is my younger sister. | Mira, la niña que va a la pizzería es mi hermana menor. | Phrase
👧 | The girl who is going to the bakery is my younger sister. | La niña que va a la panadería es mi hermana menor. | Look, the girl who is going to the bakery is my younger sister. | Mira, la niña que va a la panadería es mi hermana menor. | Phrase
👦 | He's the one who is going to the pet shop. | Él es quien va a la tienda de mascotas. | Look, he's the one who is going to the pet shop. | Mira, él es quien va a la tienda de mascotas. | Phrase
👦 | He's the one who is going to the convenience store. | Él es quien va a la tienda de conveniencia. | Look, he's the one who is going to the convenience store. | Mira, él es quien va a la tienda de conveniencia. | Phrase
👦 | He's the one who is going to the jewelry store. | Él es quien va a la joyería. | Look, he's the one who is going to the jewelry store. | Mira, él es quien va a la joyería. | Phrase
👦 | He's the one who is going to the pizzeria. | Él es quien va a la pizzería. | Look, he's the one who is going to the pizzeria. | Mira, él es quien va a la pizzería. | Phrase
👦 | He's the one who is going to the bakery. | Él es quien va a la panadería. | Look, he's the one who is going to the bakery. | Mira, él es quien va a la panadería. | Phrase
👧 | She's the one who is going to the pet shop. | Ella es quien va a la tienda de mascotas. | Look, she's the one who is going to the pet shop. | Mira, ella es quien va a la tienda de mascotas. | Phrase
👧 | She's the one who is going to the convenience store. | Ella es quien va a la tienda de conveniencia. | Look, she's the one who is going to the convenience store. | Mira, ella es quien va a la tienda de conveniencia. | Phrase
👧 | She's the one who is going to the jewelry store. | Ella es quien va a la joyería. | Look, she's the one who is going to the jewelry store. | Mira, ella es quien va a la joyería. | Phrase
👧 | She's the one who is going to the pizzeria. | Ella es quien va a la pizzería. | Look, she's the one who is going to the pizzeria. | Mira, ella es quien va a la pizzería. | Phrase
👧 | She's the one who is going to the bakery. | Ella es quien va a la panadería. | Look, she's the one who is going to the bakery. | Mira, ella es quien va a la panadería. | Phrase
🗣️ | What did he say? He said that he was going to the skate park. | ¿Qué dijo él? Él dijo que iba al parque de patinaje. | What did he say? He said that he was going to the skate park. | ¿Qué dijo él? Él dijo que iba al parque de patinaje. | Phrase
🗣️ | What did he say? He said that he was going to the sports stadium. | ¿Qué dijo él? Él dijo que iba al estadio deportivo. | What did he say? He said that he was going to the sports stadium. | ¿Qué dijo él? Él dijo que iba al estadio deportivo. | Phrase
🗣️ | What did he say? He said that he was going to the science museum. | ¿Qué dijo él? Él dijo que iba al museo de ciencia. | What did he say? He said that he was going to the science museum. | ¿Qué dijo él? Él dijo que iba al museo de ciencia. | Phrase
🗣️ | What did he say? He said that he was going to the art gallery. | ¿Qué dijo él? Él dijo que iba a la galería de arte. | What did he say? He said that he was going to the art gallery. | ¿Qué dijo él? Él dijo que iba a la galería de arte. | Phrase
🗣️ | What did he say? He said that he was going to the arcade. | ¿Qué dijo él? Él dijo que iba al salón de juegos. | What did he say? He said that he was going to the arcade. | ¿Qué dijo él? Él dijo que iba al salón de juegos. | Phrase
🛹 | Did she say that she was going to the skate park? | ¿Dijo ella que iba al parque de patinaje? | Tell me, did she say that she was going to the skate park? | Dime, ¿dijo ella que iba al parque de patinaje? | Question
🏟️ | Did she say that she was going to the sports stadium? | ¿Dijo ella que iba al estadio deportivo? | Tell me, did she say that she was going to the sports stadium? | Dime, ¿dijo ella que iba al estadio deportivo? | Question
🏛️ | Did she say that she was going to the science museum? | ¿Dijo ella que iba al museo de ciencia? | Tell me, did she say that she was going to the science museum? | Dime, ¿dijo ella que iba al museo de ciencia? | Question
🖼️ | Did she say that she was going to the art gallery? | ¿Dijo ella que iba a la galería de arte? | Tell me, did she say that she was going to the art gallery? | Dime, ¿dijo ella que iba a la galería de arte? | Question
🕹️ | Did she say that she was going to the arcade? | ¿Dijo ella que iba al salón de juegos? | Tell me, did she say that she was going to the arcade? | Dime, ¿dijo ella que iba al salón de juegos? | Question
🤔 | What do muscles do? | ¿Qué hacen los músculos? | Tell me, what do muscles do? | Dime, ¿qué hacen los músculos? | Question
🤔 | What do ligaments do? | ¿Qué hacen los ligamentos? | Tell me, what do ligaments do? | Dime, ¿qué hacen los ligamentos? | Question
🤔 | What do tendons do? | ¿Qué hacen los tendones? | Tell me, what do tendons do? | Dime, ¿qué hacen los tendones? | Question
    """.trimIndent(), "Basics 6 - List 5")

    private fun getList6(): List<Flashcard> = parseCards("""
📄 | Reuse paper | Reutilizar papel | I reuse paper | Reutilizo papel | Phrase
💡 | Turn off the lights | Apagar las luces | I turn off the lights | Apago las luces | Phrase
🌱 | Start a compost pile | Iniciar una pila de compostaje | I start a compost pile | Inicio una pila de compostaje | Phrase
♻️ | Recycle bottles and cans | Reciclar botellas y latas | I recycle bottles and cans | Reciclo botellas y latas | Phrase
🚰 | Shut off the water | Cerrar el agua | I shut off the water | Cierro el agua | Phrase
🌻 | Plant a garden | Plantar un jardín | I plant a garden | Planto un jardín | Phrase
🌍 | Sentences with the zero conditional | Oraciones con el cero condicional | We use sentences with the zero conditional | Usamos oraciones con el cero condicional | Phrase
🌍 | If you want to help the environment, reuse paper. | Si quieres ayudar al medio ambiente, reutiliza papel. | Look, if you want to help the environment, reuse paper. | Mira, si quieres ayudar al medio ambiente, reutiliza papel. | Phrase
🤔 | Questions with the zero conditional | Preguntas con el cero condicional | We use questions with the zero conditional | Usamos preguntas con el cero condicional | Phrase
🤔 | If you want to help the environment, what can you do? | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? | Tell me, if you want to help the environment, what can you do? | Dime, si quieres ayudar al medio ambiente, ¿qué puedes hacer? | Question
📄 | I can reuse paper. | Puedo reutilizar papel. | Look, I can reuse paper. | Mira, puedo reutilizar papel. | Phrase
🚌 | Take public transportation | Tomar transporte público | I take public transportation | Tomo transporte público | Phrase
🛍️ | Take reusable shopping bags | Llevar bolsas de compras reutilizables | I take reusable shopping bags | Llevo bolsas de compras reutilizables | Phrase
💡 | Use energy-saving light bulbs | Usar bombillas ahorradoras de energía | I use energy-saving light bulbs | Uso bombillas ahorradoras de energía | Phrase
❄️ | Keep the air conditioner on low | Mantener el aire acondicionado en bajo | I keep the air conditioner on low | Mantengo el aire acondicionado en bajo | Phrase
🥕 | Grow your own vegetables | Cultivar tus propias verduras | I grow my own vegetables | Cultivo mis propias verduras | Phrase
☀️ | Dry your clothes outside | Secar tu ropa afuera | I dry my clothes outside | Seco mi ropa afuera | Phrase
⚡ | First conditional | Primer condicional | We study the first conditional | Estudiamos el primer condicional | Phrase
⚡ | If we take public transportation, we'll conserve energy. | Si tomamos transporte público, conservaremos energía. | Look, if we take public transportation, we'll conserve energy. | Mira, si tomamos transporte público, conservaremos energía. | Phrase
⚡ | He'll conserve energy if he takes public transportation. | Él conservará energía si toma transporte público. | Look, He'll conserve energy if he takes public transportation. | Mira, él conservará energía si toma transporte público. | Phrase
🚲 | A New Bicycle | Una nueva bicicleta | This is A New Bicycle | Esta es Una nueva bicicleta | Phrase
💬 | Talking with someone about what you would do | Hablar con alguien sobre lo que harías | We are talking with someone about what you would do | Estamos hablando con alguien sobre lo que harías | Phrase
🚲 | If I had a new bicycle, I would use it all the time. | Si tuviera una bicicleta nueva, la usaría todo el tiempo. | Look, if I had a new bicycle, I would use it all the time. | Mira, si tuviera una bicicleta nueva, la usaría todo el tiempo. | Phrase
🤔 | You would? | ¿Lo harías? | Tell me, you would? | Dime, ¿lo harías? | Question
👍 | Yes, of course. | Sí, por supuesto. | Yes, of course. | Sí, por supuesto. | Phrase
👍 | Are you sure? | ¿Estás seguro? | Tell me, are you sure? | Dime, ¿estás seguro? | Question
⭐ | Definitely. | Definitivamente. | Yes, definitely. | Sí, definitivamente. | Expression
💡 | Be resourceful. | Sé recursivo. | Please, be resourceful. | Por favor, sé recursivo. | Phrase
🧴 | Plastic | Plástico | The bottle is plastic | La botella es de plástico | Word
🧪 | Chemical | Químico | The chemical is dangerous | El químico es peligroso | Word
⚠️ | Harmful | Dañino | The smoke is harmful | El humo es dañino | Word
🏭 | Pollution | Contaminación | Pollution is bad | La contaminación es mala | Word
⚡ | Power plant | Central eléctrica | The power plant makes electricity | La central eléctrica genera electricidad | Word
🏭 | Factory | Fábrica | The factory is big | La fábrica es grande | Word
🔄 | Complex sentences with whenever | Oraciones complejas con whenever | We study complex sentences with whenever | Estudiamos oraciones complejas con whenever | Phrase
⚠️ | Whenever we throw away chemicals, we pollute the land and the water. | Siempre que desechamos químicos, contaminamos la tierra y el agua. | Look, whenever we throw away chemicals, we pollute the land and the water. | Mira, siempre que desechamos químicos, contaminamos la tierra y el agua. | Phrase
🚲 | Whenever we ride a bike, we protect the air. | Siempre que montamos en bicicleta, protegemos el aire. | Look, whenever we ride a bike, we protect the air. | Mira, siempre que montamos en bicicleta, protegemos el aire. | Phrase
📄 | If you want to help the environment, turn off the lights. | Si quieres ayudar al medio ambiente, apaga las luces. | Look, if you want to help the environment, turn off the lights. | Mira, si quieres ayudar al medio ambiente, apaga las luces. | Phrase
📄 | If you want to help the environment, start a compost pile. | Si quieres ayudar al medio ambiente, inicia una pila de compostaje. | Look, if you want to help the environment, start a compost pile. | Mira, si quieres ayudar al medio ambiente, inicia una pila de compostaje. | Phrase
📄 | If you want to help the environment, recycle bottles and cans. | Si quieres ayudar al medio ambiente, recicla botellas y latas. | Look, if you want to help the environment, recycle bottles and cans. | Mira, si quieres ayudar al medio ambiente, recicla botellas y latas. | Phrase
📄 | If you want to help the environment, shut off the water. | Si quieres ayudar al medio ambiente, cierra el agua. | Look, if you want to help the environment, shut off the water. | Mira, si quieres ayudar al medio ambiente, cierra el agua. | Phrase
📄 | If you want to help the environment, plant a garden. | Si quieres ayudar al medio ambiente, planta un jardín. | Look, if you want to help the environment, plant a garden. | Mira, si quieres ayudar al medio ambiente, planta un jardín. | Phrase
🤔 | If you want to help the environment, what can you do? | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? | Tell me, if you want to help the environment, what can you do? | Dime, si quieres ayudar al medio ambiente, ¿qué puedes hacer? | Question
💡 | If you want to help the environment, what can you do? I can turn off the lights. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo apagar las luces. | If you want to help the environment, what can you do? I can turn off the lights. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo apagar las luces. | Phrase
🌱 | If you want to help the environment, what can you do? I can start a compost pile. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo iniciar una pila de compostaje. | If you want to help the environment, what can you do? I can start a compost pile. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo iniciar una pila de compostaje. | Phrase
♻️ | If you want to help the environment, what can you do? I can recycle bottles and cans. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo reciclar botellas y latas. | If you want to help the environment, what can you do? I can recycle bottles and cans. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo reciclar botellas y latas. | Phrase
🚰 | If you want to help the environment, what can you do? I can shut off the water. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo cerrar el agua. | If you want to help the environment, what can you do? I can shut off the water. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo cerrar el agua. | Phrase
🌻 | If you want to help the environment, what can you do? I can plant a garden. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo plantar un jardín. | If you want to help the environment, what can you do? I can plant a garden. | Si quieres ayudar al medio ambiente, ¿qué puedes hacer? Puedo plantar un jardín. | Phrase
⚡ | If we take reusable shopping bags, we'll conserve energy. | Si llevamos bolsas de compras reutilizables, conservaremos energía. | Look, if we take reusable shopping bags, we'll conserve energy. | Mira, si llevamos bolsas de compras reutilizables, conservaremos energía. | Phrase
⚡ | If we use energy-saving light bulbs, we'll conserve energy. | Si usamos bombillas ahorradoras de energía, conservaremos energía. | Look, if we use energy-saving light bulbs, we'll conserve energy. | Mira, si usamos bombillas ahorradoras de energía, conservaremos energía. | Phrase
⚡ | If we keep the air conditioner on low, we'll conserve energy. | Si mantenemos el aire acondicionado en bajo, conservaremos energía. | Look, if we keep the air conditioner on low, we'll conserve energy. | Mira, si mantenemos el aire acondicionado en bajo, conservaremos energía. | Phrase
⚡ | If we grow our own vegetables, we'll conserve energy. | Si cultivamos nuestras propias verduras, conservaremos energía. | Look, if we grow our own vegetables, we'll conserve energy. | Mira, si cultivamos nuestras propias verduras, conservaremos energía. | Phrase
⚡ | If we dry our clothes outside, we'll conserve energy. | Si secamos nuestra ropa afuera, conservaremos energía. | Look, if we dry our clothes outside, we'll conserve energy. | Mira, si secamos nuestra ropa afuera, conservaremos energía. | Phrase
⚡ | He'll conserve energy if he takes reusable shopping bags. | Él conservará energía si lleva bolsas de compras reutilizables. | Look, He'll conserve energy if he takes reusable shopping bags. | Mira, él conservará energía si lleva bolsas de compras reutilizables. | Phrase
⚡ | He'll conserve energy if he uses energy-saving light bulbs. | Él conservará energía si usa bombillas ahorradoras de energía. | Look, He'll conserve energy if he uses energy-saving light bulbs. | Mira, él conservará energía si usa bombillas ahorradoras de energía. | Phrase
⚡ | He'll conserve energy if he keeps the air conditioner on low. | Él conservará energía si mantiene el aire acondicionado en bajo. | Look, He'll conserve energy if he keeps the air conditioner on low. | Mira, él conservará energía si mantiene el aire acondicionado en bajo. | Phrase
⚡ | He'll conserve energy if he grows his own vegetables. | Él conservará energía si cultiva sus propias verduras. | Look, He'll conserve energy if he grows his own vegetables. | Mira, él conservará energía si cultiva sus propias verduras. | Phrase
⚡ | He'll conserve energy if he dries his clothes outside. | Él conservará energía si seca su ropa afuera. | Look, He'll conserve energy if he dries his clothes outside. | Mira, él conservará energía si seca su ropa afuera. | Phrase
🚲 | Whenever we take public transportation, we protect the air. | Siempre que tomamos transporte público, protegemos el aire. | Look, whenever we take public transportation, we protect the air. | Mira, siempre que tomamos transporte público, protegemos el aire. | Phrase
🚲 | Whenever we take reusable shopping bags, we protect the air. | Siempre que llevamos bolsas de compras reutilizables, protegemos el aire. | Look, whenever we take reusable shopping bags, we protect the air. | Mira, siempre que llevamos bolsas de compras reutilizables, protegemos el aire. | Phrase
🚲 | Whenever we use energy-saving light bulbs, we protect the air. | Siempre que usamos bombillas ahorradoras de energía, protegemos el aire. | Look, whenever we use energy-saving light bulbs, we protect the air. | Mira, siempre que usamos bombillas ahorradoras de energía, protegemos el aire. | Phrase
    """.trimIndent(), "Basics 6 - List 6")

    private fun getList7(): List<Flashcard> = parseCards("""
🎭 | Verdi's operas | Óperas de Verdi | Verdi's operas are good | Las óperas de Verdi son buenas | Phrase
🎨 | Picasso's paintings | Pinturas de Picasso | Picasso's paintings are old | Las pinturas de Picasso son viejas | Phrase
📖 | Shakespeare's plays | Obras de Shakespeare | Shakespeare's plays are long | Las obras de Shakespeare son largas | Phrase
🎵 | Beethoven's symphonies | Sinfonías de Beethoven | Beethoven's symphonies are nice | Las sinfonías de Beethoven son bonitas | Phrase
🗿 | Michelangelo's sculptures | Esculturas de Miguel Ángel | Michelangelo's sculptures are big | Las esculturas de Miguel Ángel son grandes | Phrase
🩰 | Balanchine's ballets | Ballets de Balanchine | Balanchine's ballets are famous | Los ballets de Balanchine son famosos | Phrase
🎭 | Verdi's operas are performed here. | Las óperas de Verdi se representan aquí. | Look, Verdi's operas are performed here. | Mira, las óperas de Verdi se representan aquí. | Phrase
🎨 | Picasso's paintings are displayed here. | Las pinturas de Picasso se exhiben aquí. | Look, Picasso's paintings are displayed here. | Mira, las pinturas de Picasso se exhiben aquí. | Phrase
🤔 | Whose operas are performed here? | ¿De quién son las óperas que se representan aquí? | Tell me, whose operas are performed here? | Dime, ¿de quién son las óperas que se representan aquí? | Question
🎭 | Verdi's operas are performed here. | Las óperas de Verdi se representan aquí. | Look, Verdi's operas are performed here. | Mira, las óperas de Verdi se representan aquí. | Phrase
🤔 | Whose paintings are displayed here? | ¿De quién son las pinturas que se exhiben aquí? | Tell me, whose paintings are displayed here? | Dime, ¿de quién son las pinturas que se exhiben aquí? | Question
🎨 | Picasso's paintings are displayed here. | Las pinturas de Picasso se exhiben aquí. | Look, Picasso's paintings are displayed here. | Mira, las pinturas de Picasso se exhiben aquí. | Phrase
🌊 | Erie Canal | Canal de Erie | The Erie Canal is long | El canal de Erie es largo | Word
🚂 | Trans-Siberian Railway | Ferrocarril transiberiano | The Trans-Siberian Railway is big | El ferrocarril transiberiano es grande | Word
🧱 | Hoover Dam | Presa Hoover | The Hoover Dam is high | La presa Hoover es alta | Word
🚇 | Seikan tunnel | Túnel Seikan | The Seikan tunnel is deep | El túnel Seikan es profundo | Word
🏙️ | Taipei 101 Building | Edificio Taipei 101 | The Taipei 101 Building is tall | El edificio Taipei 101 es alto | Word
🌉 | Oliveira Bridge | Puente Oliveira | The Oliveira Bridge is old | El puente Oliveira es viejo | Word
🌊 | The Erie Canal was completed in 1825. | El canal de Erie se completó en 1825. | Look, The Erie Canal was completed in 1825. | Mira, el canal de Erie se completó en 1825. | Phrase
🌊 | When was the Erie Canal constructed? | ¿Cuándo se construyó el canal de Erie? | Tell me, When was the Erie Canal constructed? | Dime, ¿cuándo se construyó el canal de Erie? | Question
🌊 | It was started in 1817, and it was finished in 1825. | Se comenzó en 1817 y se terminó en 1825. | Look, It was started in 1817, and it was finished in 1825. | Mira, se comenzó en 1817 y se terminó en 1825. | Phrase
🎭 | An Afternoon at the Opera | Una tarde en la ópera | This is An Afternoon at the Opera | Esta es Una tarde en la ópera | Phrase
🤔 | Asking if someone knows about something | Preguntar si alguien sabe sobre algo | We are asking if someone knows about something | Estamos preguntando si alguien sabe sobre algo | Phrase
🇪🇬 | Did you know that Aida was first performed in Egypt? | ¿Sabías que Aida se representó por primera vez en Egipto? | Tell me, did you know that Aida was first performed in Egypt? | Dime, ¿sabías que Aida se representó por primera vez en Egipto? | Question
👍 | I didn't know that. | No sabía eso. | I didn't know that. | No sabía eso. | Phrase
👍 | Yes, I knew that. | Sí, sabía eso. | Yes, I knew that. | Sí, sabía eso. | Phrase
❤️ | Be polite. | Sé educado. | Please, be polite. | Por favor, sé educado. | Phrase
🏙️ | Modern | Moderno | The city is modern | La ciudad es moderna | Word
🌊 | Body of water | Masa de agua | The ocean is a body of water | El océano es una masa de agua | Word
📅 | Daily | Diario | I read daily | Leo a diario | Word
📏 | Height | Altura | What is the height? | ¿Cuál es la altura? | Word
📐 | Width | Ancho | What is the width? | ¿Cuál es el ancho? | Word
🌊 | Underwater | Subacuático | The fish is underwater | El pez está bajo el agua | Word
🤔 | Asking questions with comparisons | Hacer preguntas con comparaciones | We are asking questions with comparisons | Estamos haciendo preguntas con comparaciones | Phrase
🤔 | Which is higher, the Golden Gate Bridge or the Channel Tunnel? | ¿Cuál es más alto, el puente Golden Gate o el túnel del Canal? | Tell me, which is higher, the Golden Gate Bridge or the Channel Tunnel? | Dime, ¿cuál es más alto, el puente Golden Gate o el túnel del Canal? | Question
🌉 | Which is higher, the Golden Gate Bridge or the Channel Tunnel? The Golden Gate Bridge is higher. | ¿Cuál es más alto, el puente Golden Gate o el túnel del Canal? El puente Golden Gate es más alto. | Which is higher, the Golden Gate Bridge or the Channel Tunnel? The Golden Gate Bridge is higher. | ¿Cuál es más alto, el puente Golden Gate o el túnel del Canal? El puente Golden Gate es más alto. | Phrase
📅 | Which has more daily use, the Golden Gate Bridge or the Channel Tunnel? | ¿Cuál tiene más uso diario, el puente Golden Gate o el túnel del Canal? | Tell me, which has more daily use, the Golden Gate Bridge or the Channel Tunnel? | Dime, ¿cuál tiene más uso diario, el puente Golden Gate o el túnel del Canal? | Question
🌉 | Which has more daily use, the Golden Gate Bridge or the Channel Tunnel? The Golden Gate Bridge has more daily use. | ¿Cuál tiene más uso diario, el puente Golden Gate o el túnel del Canal? El puente Golden Gate tiene más uso diario. | Which has more daily use, the Golden Gate Bridge or the Channel Tunnel? The Golden Gate Bridge has more daily use. | ¿Cuál tiene más uso diario, el puente Golden Gate o el túnel del Canal? El puente Golden Gate tiene más uso diario. | Phrase
📖 | Shakespeare's plays are performed here. | Las obras de Shakespeare se representan aquí. | Look, Shakespeare's plays are performed here. | Mira, las obras de Shakespeare se representan aquí. | Phrase
🎵 | Beethoven's symphonies are performed here. | Las sinfonías de Beethoven se representan aquí. | Look, Beethoven's symphonies are performed here. | Mira, las sinfonías de Beethoven se representan aquí. | Phrase
🗿 | Michelangelo's sculptures are performed here. | Las esculturas de Miguel Ángel se representan aquí. | Look, Michelangelo's sculptures are performed here. | Mira, las esculturas de Miguel Ángel se representan aquí. | Phrase
🩰 | Balanchine's ballets are performed here. | Los ballets de Balanchine se representan aquí. | Look, Balanchine's ballets are performed here. | Mira, los ballets de Balanchine se representan aquí. | Phrase
📖 | Shakespeare's plays are displayed here. | Las obras de Shakespeare se exhiben aquí. | Look, Shakespeare's plays are displayed here. | Mira, las obras de Shakespeare se exhiben aquí. | Phrase
🎵 | Beethoven's symphonies are displayed here. | Las sinfonías de Beethoven se exhiben aquí. | Look, Beethoven's symphonies are displayed here. | Mira, las sinfonías de Beethoven se exhiben aquí. | Phrase
🗿 | Michelangelo's sculptures are displayed here. | Las esculturas de Miguel Ángel se exhiben aquí. | Look, Michelangelo's sculptures are displayed here. | Mira, las esculturas de Miguel Ángel se exhiben aquí. | Phrase
🩰 | Balanchine's ballets are displayed here. | Los ballets de Balanchine se exhiben aquí. | Look, Balanchine's ballets are displayed here. | Mira, los ballets de Balanchine se exhiben aquí. | Phrase
🤔 | Whose plays are performed here? | ¿De quién son las obras que se representan aquí? | Tell me, whose plays are performed here? | Dime, ¿de quién son las obras que se representan aquí? | Question
🤔 | Whose symphonies are performed here? | ¿De quién son las sinfonías que se representan aquí? | Tell me, whose symphonies are performed here? | Dime, ¿de quién son las sinfonías que se representan aquí? | Question
🤔 | Whose sculptures are performed here? | ¿De quién son las esculturas que se representan aquí? | Tell me, whose sculptures are performed here? | Dime, ¿de quién son las esculturas que se representan aquí? | Question
🤔 | Whose ballets are performed here? | ¿De quién son los ballets que se representan aquí? | Tell me, whose ballets are performed here? | Dime, ¿de quién son los ballets que se representan aquí? | Question
📖 | Shakespeare's plays are performed here. | Las obras de Shakespeare se representan aquí. | Look, Shakespeare's plays are performed here. | Mira, las obras de Shakespeare se representan aquí. | Phrase
🎵 | Beethoven's symphonies are performed here. | Las sinfonías de Beethoven se representan aquí. | Look, Beethoven's symphonies are performed here. | Mira, las sinfonías de Beethoven se representan aquí. | Phrase
🗿 | Michelangelo's sculptures are performed here. | Las esculturas de Miguel Ángel se representan aquí. | Look, Michelangelo's sculptures are performed here. | Mira, las esculturas de Miguel Ángel se representan aquí. | Phrase
🩰 | Balanchine's ballets are performed here. | Los ballets de Balanchine se representan aquí. | Look, Balanchine's ballets are performed here. | Mira, los ballets de Balanchine se representan aquí. | Phrase
🤔 | Whose plays are displayed here? | ¿De quién son las obras que se exhiben aquí? | Tell me, whose plays are displayed here? | Dime, ¿de quién son las obras que se exhiben aquí? | Question
🤔 | Whose symphonies are displayed here? | ¿De quién son las sinfonías que se exhiben aquí? | Tell me, whose symphonies are displayed here? | Dime, ¿de quién son las sinfonías que se exhiben aquí? | Question
🤔 | Whose sculptures are displayed here? | ¿De quién son las esculturas que se exhiben aquí? | Tell me, whose sculptures are displayed here? | Dime, ¿de quién son las esculturas que se exhiben aquí? | Question
🤔 | Whose ballets are displayed here? | ¿De quién son los ballets que se exhiben aquí? | Tell me, whose ballets are displayed here? | Dime, ¿de quién son los ballets que se exhiben aquí? | Question
📖 | Shakespeare's plays are displayed here. | Las obras de Shakespeare se exhiben aquí. | Look, Shakespeare's plays are displayed here. | Mira, las obras de Shakespeare se exhiben aquí. | Phrase
🎵 | Beethoven's symphonies are displayed here. | Las sinfonías de Beethoven se exhiben aquí. | Look, Beethoven's symphonies are displayed here. | Mira, las sinfonías de Beethoven se exhiben aquí. | Phrase
🗿 | Michelangelo's sculptures are displayed here. | Las esculturas de Miguel Ángel se exhiben aquí. | Look, Michelangelo's sculptures are displayed here. | Mira, las esculturas de Miguel Ángel se exhiben aquí. | Phrase
🩰 | Balanchine's ballets are displayed here. | Los ballets de Balanchine se exhiben aquí. | Look, Balanchine's ballets are displayed here. | Mira, los ballets de Balanchine se exhiben aquí. | Phrase
🚂 | The Trans-Siberian Railway was completed in 1825. | El ferrocarril transiberiano se completó en 1825. | Look, The Trans-Siberian Railway was completed in 1825. | Mira, el ferrocarril transiberiano se completó en 1825. | Phrase
🧱 | The Hoover Dam was completed in 1825. | La presa Hoover se completó en 1825. | Look, The Hoover Dam was completed in 1825. | Mira, la presa Hoover se completó en 1825. | Phrase
🚇 | The Seikan tunnel was completed in 1825. | El túnel Seikan se completó en 1825. | Look, The Seikan tunnel was completed in 1825. | Mira, el túnel Seikan se completó en 1825. | Phrase
🏙️ | The Taipei 101 Building was completed in 1825. | El edificio Taipei 101 se completó en 1825. | Look, The Taipei 101 Building was completed in 1825. | Mira, el edificio Taipei 101 se completó en 1825. | Phrase
🌉 | The Oliveira Bridge was completed in 1825. | El puente Oliveira se completó en 1825. | Look, The Oliveira Bridge was completed in 1825. | Mira, el puente Oliveira se completó en 1825. | Phrase
🤔 | When was the Trans-Siberian Railway constructed? | ¿Cuándo se construyó el ferrocarril transiberiano? | Tell me, When was the Trans-Siberian Railway constructed? | Dime, ¿cuándo se construyó el ferrocarril transiberiano? | Question
🧱 | When was the Hoover Dam constructed? | ¿Cuándo se construyó la presa Hoover? | Tell me, When was the Hoover Dam constructed? | Dime, ¿cuándo se construyó la presa Hoover? | Question
🚇 | When was the Seikan tunnel constructed? | ¿Cuándo se construyó el túnel Seikan? | Tell me, When was the Seikan tunnel constructed? | Dime, ¿cuándo se construyó el túnel Seikan? | Question
🤔 | When was the Taipei 101 Building constructed? | ¿Cuándo se construyó el edificio Taipei 101? | Tell me, When was the Taipei 101 Building constructed? | Dime, ¿cuándo se construyó el edificio Taipei 101? | Question
🌉 | When was the Oliveira Bridge constructed? | ¿Cuándo se construyó el puente Oliveira? | Tell me, When was the Oliveira Bridge constructed? | Dime, ¿cuándo se construyó el puente Oliveira? | Question
🚂 | It was started in 1817, and it was finished in 1825. | Se comenzó en 1817 y se terminó en 1825. | Look, It was started in 1817, and it was finished in 1825. | Mira, se comenzó en 1817 y se terminó en 1825. | Phrase
    """.trimIndent(), "Basics 6 - List 7")

    private fun getList8(): List<Flashcard> = parseCards("""
😴 | Boring speech | Discurso aburrido | The speech is boring | El discurso es aburrido | Word
🥱 | Bored audience | Audiencia aburrida | The audience is bored | La audiencia está aburrida | Word
🎉 | Exciting award ceremony | Ceremonia de premiación emocionante | The award ceremony is exciting | La ceremonia de premiación es emocionante | Word
😃 | Excited award recipients | Ganadores de premios emocionados | The award recipients are excited | Los ganadores de premios están emocionados | Word
🎭 | Interesting performance | Interpretación interesante | The performance is interesting | La interpretación es interesante | Word
😊 | Interested guests | Invitados interesados | The guests are interested | Los invitados están interesados | Word
📝 | Sentences with adjectives with -ed and -ing | Oraciones con adjetivos con -ed e -ing | We study sentences with adjectives with -ed and -ing | Estudiamos oraciones con adjetivos con -ed e -ing | Phrase
🔄 | Comparing present tense with past tense and future tense | Comparar el tiempo presente con el tiempo pasado y el tiempo futuro | We are comparing present tense with past tense and future tense | Estamos comparando el tiempo presente con el tiempo pasado y el tiempo futuro | Phrase
😴 | This year the audience is bored, but last year they were even more bored. | Este año la audiencia está aburrida, pero el año pasado estaban aún más aburridos. | Look, this year the audience is bored, but last year they were even more bored. | Mira, este año la audiencia está aburrida, pero el año pasado estaban aún más aburridos. | Phrase
😴 | The speech is boring this year, and it will be just as boring next year. | El discurso es aburrido este año, y será igual de aburrido el próximo año. | Look, the speech is boring this year, and it will be just as boring next year. | Mira, el discurso es aburrido este año, y será igual de aburrido el próximo año. | Phrase
🎁 | Open presents | Abrir regalos | I open presents | Abro regalos | Phrase
💌 | Write thank-you cards | Escribir tarjetas de agradecimiento | I write thank-you cards | Escribo tarjetas de agradecimiento | Phrase
📱 | Send text messages | Enviar mensajes de texto | I send text messages | Envío mensajes de texto | Phrase
🤝 | Hang out with friends | Pasar el rato con amigos | I hang out with friends | Paso el rato con amigos | Phrase
👋 | Visit relatives | Visitar familiares | I visit relatives | Visito familiares | Phrase
📸 | Pose for pictures | Posar para fotos | I pose for pictures | Poso para fotos | Phrase
⏳ | Present progressive and present perfect progressive | Presente progresivo y presente perfecto progresivo | We study present progressive and present perfect progressive | Estudiamos el presente progresivo y el presente perfecto progresivo | Phrase
🎁 | I'm opening my presents now. | Estoy abriendo mis regalos ahora. | Look, I'm opening my presents now. | Mira, estoy abriendo mis regalos ahora. | Phrase
🎁 | I've been opening my presents all afternoon. | He estado abriendo mis regalos toda la tarde. | Look, I've been opening my presents all afternoon. | Mira, he estado abriendo mis regalos toda la tarde. | Phrase
🤔 | What are you doing now? | ¿Qué estás haciendo ahora? | Tell me, what are you doing now? | Dime, ¿qué estás haciendo ahora? | Question
🎁 | I'm opening presents. | Estoy abriendo regalos. | Look, I'm opening presents. | Mira, estoy abriendo regalos. | Phrase
🤔 | What have you been doing all day? | ¿Qué has estado haciendo todo el día? | Tell me, what have you been doing all day? | Dime, ¿qué has estado haciendo todo el día? | Question
🎁 | I've been opening presents. | He estado abriendo regalos. | Look, I've been opening presents. | Mira, he estado abriendo regalos. | Phrase
🎭 | Behind the Curtains | Detrás de las cortinas | This is Behind the Curtains | Esto es Detrás de las cortinas | Phrase
🔍 | Finding out where someone has been | Descubrir dónde ha estado alguien | We are finding out where someone has been | Estamos descubriendo dónde ha estado alguien | Phrase
🤔 | Where have you been? | ¿Dónde has estado? | Tell me, where have you been? | Dime, ¿dónde has estado? | Question
🎓 | I was at my brother's graduation ceremony. | Estuve en la ceremonia de graduación de mi hermano. | Look, I was at my brother's graduation ceremony. | Mira, estuve en la ceremonia de graduación de mi hermano. | Phrase
🙇 | Sorry! | ¡Lo siento! | Sorry, I am late. | Lo siento, llego tarde. | Expression
🤔 | It's O.K. What are you doing now? | Está bien. ¿Qué estás haciendo ahora? | Tell me, it's O.K. What are you doing now? | Dime, está bien. ¿Qué estás haciendo ahora? | Question
🙇 | Sorry. I didn't hear my phone. | Lo siento. No escuché mi teléfono. | Look, sorry. I didn't hear my phone. | Mira, lo siento. No escuché mi teléfono. | Phrase
👍 | Don't worry about it. | No te preocupes por eso. | Don't worry about it. | No te preocupes por eso. | Phrase
⏳ | Be patient. | Ten paciencia. | Please, be patient. | Por favor, ten paciencia. | Phrase
🔬 | Physicist | Físico | He is a physicist | Él es un físico | Word
💡 | Discover | Descubrir | I discover new things | Descubro cosas nuevas | Word
⚛️ | Graphene | Grafeno | Graphene is strong | El grafeno es fuerte | Word
🍃 | Flake | Lámina / Escama | The flake is small | La lámina es pequeña | Word
✨ | Transparent | Transparente | The glass is transparent | El vidrio es transparente | Word
🪨 | Carbon | Carbono | Carbon is an element | El carbono es un elemento | Word
📚 | Verb tense review | Repaso de tiempos verbales | We do a verb tense review | Hacemos un repaso de tiempos verbales | Phrase
🪨 | Asking about graphite, graphene, and carbon. | Preguntar sobre grafito, grafeno y carbono. | We are asking about graphite, graphene, and carbon. | Estamos preguntando sobre grafito, grafeno y carbono. | Phrase
🤔 | Where can you find graphite? | ¿Dónde puedes encontrar grafito? | Tell me, where can you find graphite? | Dime, ¿dónde puedes encontrar grafito? | Question
✏️ | You can find it in a pencil. | Puedes encontrarlo en un lápiz. | Look, you can find it in a pencil. | Mira, puedes encontrarlo en un lápiz. | Phrase
🥱 | Bored speech | Discurso aburrido | The speech is bored | El discurso está aburrido | Word
😴 | Boring audience | Audiencia aburrida | The audience is boring | La audiencia es aburrida | Word
🎉 | Excited award ceremony | Ceremonia de premiación emocionada | The award ceremony is excited | La ceremonia de premiación está emocionada | Word
😃 | Exciting award recipients | Ganadores de premios emocionantes | The award recipients are exciting | Los ganadores de premios son emocionantes | Word
🎭 | Interested performance | Interpretación interesada | The performance is interested | La interpretación está interesada | Word
😊 | Interesting performance | Interpretación interesante | The performance is interesting | La interpretación es interesante | Word
💌 | Write thank-you cards for pictures | Escribir tarjetas de agradecimiento para fotos | I write thank-you cards for pictures | Escribo tarjetas de agradecimiento para fotos | Phrase
📱 | Send text messages for pictures | Enviar mensajes de texto para fotos | I send text messages for pictures | Envío mensajes de texto para fotos | Phrase
🤝 | Hang out with friends for pictures | Pasar el rato con amigos para fotos | I hang out with friends for pictures | Paso el rato con amigos para fotos | Phrase
👋 | Visit relatives for pictures | Visitar familiares para fotos | I visit relatives for pictures | Visito familiares para fotos | Phrase
🎁 | Open presents for pictures | Abrir regalos para fotos | I open presents for pictures | Abro regalos para fotos | Phrase
💌 | Write thank-you cards with friends | Escribir tarjetas de agradecimiento con amigos | I write thank-you cards with friends | Escribo tarjetas de agradecimiento con amigos | Phrase
📱 | Send text messages with friends | Enviar mensajes de texto con amigos | I send text messages with friends | Envío mensajes de texto con amigos | Phrase
👋 | Visit relatives with friends | Visitar familiares con amigos | I visit relatives with friends | Visito familiares con amigos | Phrase
🎁 | Open presents with friends | Abrir regalos con amigos | I open presents with friends | Abro regalos con amigos | Phrase
📸 | Pose for pictures with friends | Posar para fotos con amigos | I pose for pictures with friends | Poso para fotos con amigos | Phrase
💌 | Write thank-you cards now | Escribir tarjetas de agradecimiento ahora | I write thank-you cards now | Escribo tarjetas de agradecimiento ahora | Phrase
📱 | Send text messages now | Enviar mensajes de texto ahora | I send text messages now | Envío mensajes de texto ahora | Phrase
🤝 | Hang out with friends now | Pasar el rato con amigos ahora | I hang out with friends now | Paso el rato con amigos ahora | Phrase
👋 | Visit relatives now | Visitar familiares ahora | I visit relatives now | Visito familiares ahora | Phrase
📸 | Pose for pictures now | Posar para fotos ahora | I pose for pictures now | Poso para fotos ahora | Phrase
💌 | Write thank-you cards all afternoon | Escribir tarjetas de agradecimiento toda la tarde | I write thank-you cards all afternoon | Escribo tarjetas de agradecimiento toda la tarde | Phrase
📱 | Send text messages all afternoon | Enviar mensajes de texto toda la tarde | I send text messages all afternoon | Envío mensajes de texto toda la tarde | Phrase
🤝 | Hang out with friends all afternoon | Pasar el rato con amigos toda la tarde | I hang out with friends all afternoon | Paso el rato con amigos toda la tarde | Phrase
👋 | Visit relatives all afternoon | Visitar familiares toda la tarde | I visit relatives all afternoon | Visito familiares toda la tarde | Phrase
📸 | Pose for pictures all afternoon | Posar para fotos toda la tarde | I pose for pictures all afternoon | Poso para fotos toda la tarde | Phrase
💌 | I've been writing thank-you cards all afternoon. | He estado escribiendo tarjetas de agradecimiento toda la tarde. | Look, I've been writing thank-you cards all afternoon. | Mira, he estado escribiendo tarjetas de agradecimiento toda la tarde. | Phrase
📱 | I've been sending text messages all afternoon. | He estado enviando mensajes de texto toda la tarde. | Look, I've been sending text messages all afternoon. | Mira, he estado enviando mensajes de texto toda la tarde. | Phrase
🤝 | I've been hanging out with friends all afternoon. | He estado pasando el rato con amigos toda la tarde. | Look, I've been hanging out with friends all afternoon. | Mira, he estado pasando el rato con amigos toda la tarde. | Phrase
👋 | I've been visiting relatives all afternoon. | He estado visitando familiares toda la tarde. | Look, I've been visiting relatives all afternoon. | Mira, he estado visitando familiares toda la tarde. | Phrase
📸 | I've been posing for pictures all afternoon. | He estado posando para fotos toda la tarde. | Look, I've been posing for pictures all afternoon. | Mira, he estado posando para fotos toda la tarde. | Phrase
💌 | I'm writing thank-you cards now. | Estoy escribiendo tarjetas de agradecimiento ahora. | Look, I'm writing thank-you cards now. | Mira, estoy escribiendo tarjetas de agradecimiento ahora. | Phrase
📱 | I'm sending text messages now. | Estoy enviando mensajes de texto ahora. | Look, I'm sending text messages now. | Mira, estoy enviando mensajes de texto ahora. | Phrase
🤝 | I'm hanging out with friends now. | Estoy pasando el rato con amigos ahora. | Look, I'm hanging out with friends now. | Mira, estoy pasando el rato con amigos ahora. | Phrase
👋 | I'm visiting relatives now. | Estoy visitando familiares ahora. | Look, I'm visiting relatives now. | Mira, estoy visitando familiares ahora. | Phrase
📸 | I'm posing for pictures now. | Estoy posando para fotos ahora. | Look, I'm posing for pictures now. | Mira, estoy posando para fotos ahora. | Phrase
💌 | I've been writing thank-you cards all day. | He estado escribiendo tarjetas de agradecimiento todo el día. | Look, I've been writing thank-you cards all day. | Mira, he estado escribiendo tarjetas de agradecimiento todo el día. | Phrase
📱 | I've been sending text messages all day. | He estado enviando mensajes de texto todo el día. | Look, I've been sending text messages all day. | Mira, he estado enviando mensajes de texto todo el día. | Phrase
🤝 | I've been hanging out with friends all day. | He estado pasando el rato con amigos todo el día. | Look, I've been hanging out with friends all day. | Mira, he estado pasando el rato con amigos todo el día. | Phrase
👋 | I've been visiting relatives all day. | He estado visitando familiares todo el día. | Look, I've been visiting relatives all day. | Mira, he estado visitando familiares todo el día. | Phrase
📸 | I've been posing for pictures all day. | He estado posando para fotos todo el día. | Look, I've been posing for pictures all day. | Mira, he estado posando para fotos todo el día. | Phrase
💌 | I'm writing thank-you cards. | Estoy escribiendo tarjetas de agradecimiento. | Look, I'm writing thank-you cards. | Mira, estoy escribiendo tarjetas de agradecimiento. | Phrase
📱 | I'm sending text messages. | Estoy enviando mensajes de texto. | Look, I'm sending text messages. | Mira, estoy enviando mensajes de texto. | Phrase
🤝 | I'm hanging out with friends. | Estoy pasando el rato con amigos. | Look, I'm hanging out with friends. | Mira, estoy pasando el rato con amigos. | Phrase
👋 | I'm visiting relatives. | Estoy visitando familiares. | Look, I'm visiting relatives. | Mira, estoy visitando familiares. | Phrase
📸 | I'm posing for pictures. | Estoy posando para fotos. | Look, I'm posing for pictures. | Mira, estoy posando para fotos. | Phrase
⚛️ | Where can you find graphene? | ¿Dónde puedes encontrar grafeno? | Tell me, where can you find graphene? | Dime, ¿dónde puedes encontrar grafeno? | Question
🪨 | Where can you find carbon? | ¿Dónde puedes encontrar carbono? | Tell me, where can you find carbon? | Dime, ¿dónde puedes encontrar carbono? | Question
✏️ | You can find it in a flake. | Puedes encontrarlo en una lámina / escama. | Look, you can find it in a flake. | Mira, puedes encontrarlo en una lámina o escama. | Phrase
    """.trimIndent(), "Basics 6 - List 8")
}
