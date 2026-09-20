package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object BasicsBooklet1 {
    const val FOLDER_NAME = "Basics 1"

    val categoryNames: List<String> = (1..8).map { "Basics 1 - List $it" }

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
🖊️ | Pen | Bolígrafo | I use a pen | Uso un bolígrafo | Word
✏️ | Pencil | Lápiz | I need a pencil | Necesito un lápiz | Word
🧽 | Eraser | Borrador | I have an eraser | Tengo un borrador | Word
📏 | Ruler | Regla | I see a ruler | Veo una regla | Word
👝 | Pencil case | Estuche / Cartuchera | Open the pencil case | Abre el estuche | Word
🎒 | Backpack | Mochila | My backpack is big | Mi mochila es grande | Word
❓ | What is it? | ¿Qué es? | Tell me, what is it? | Dime, ¿qué es? | Question
🖊️ | What is it? It's a pen. | ¿Qué es? Es un bolígrafo. | What is it? It's a pen. | ¿Qué es? Es un bolígrafo. | Phrase
📖 | Book | Libro | I read a book | Leo un libro | Word
📓 | Notebook | Cuaderno | Write in the notebook | Escribe en el cuaderno | Word
🪑 | Desk | Escritorio | Clean the desk | Limpia el escritorio | Word
🪑 | Chair | Silla | Sit on the chair | Siéntate en la silla | Word
📖 | What is it? It's a book. | ¿Qué es? Es un libro. | What is it? It's a book. | ¿Qué es? Es un libro. | Phrase
❌ | It isn't a notebook. | No es un cuaderno. | No, it isn't a notebook. | No, no es un cuaderno. | Phrase
😃 | I'm Great! | ¡Estoy muy bien! | Hello, I'm great! | ¡Hola, estoy muy bien! | Phrase
🤔 | How are you? | ¿Cómo estás? | Hi, how are you? | Hola, ¿cómo estás? | Question
🙂 | I'm fine. | Estoy bien. | Today I'm fine. | Hoy estoy bien. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
🤝 | Be friendly. | Sé amigable. | Please, be friendly. | Por favor, sé amigable. | Phrase
⭕ | Circle | Círculo | Draw a circle | Dibuja un círculo | Word
🟥 | Square | Cuadrado | I see a square | Veo un cuadrado | Word
🔺 | Triangle | Triángulo | It is a triangle | Es un triángulo | Word
▭ | Rectangle | Rectángulo | A long rectangle | Un rectángulo largo | Word
⭕ | Is it a circle? | ¿Es un círculo? | Is it a circle? | ¿Es un círculo? | Question
✅ | Is it a circle? Yes, it is. | ¿Es un círculo? Sí, lo es. | Is it a circle? Yes, it is. | ¿Es un círculo? Sí, lo es. | Phrase
❌ | Is it a circle? No, it isn't. | ¿Es un círculo? No, no lo es. | Is it a circle? No, it isn't. | ¿Es un círculo? No, no lo es. | Phrase
🖊️ | What is it? It's a pencil. | ¿Qué es? Es un lápiz. | What is it? It's a pencil. | ¿Qué es? Es un lápiz. | Phrase
🧽 | What is it? It's an eraser. | ¿Qué es? Es un borrador. | What is it? It's an eraser. | ¿Qué es? Es un borrador. | Phrase
📏 | What is it? It's a ruler. | ¿Qué es? Es una regla. | What is it? It's a ruler. | ¿Qué es? Es una regla. | Phrase
👝 | What is it? It's a pencil case. | ¿Qué es? Es un estuche. | What is it? It's a pencil case. | ¿Qué es? Es un estuche. | Phrase
🎒 | What is it? It's a backpack. | ¿Qué es? Es una mochila. | What is it? It's a backpack. | ¿Qué es? Es una mochila. | Phrase
📓 | What is it? It's a notebook. | ¿Qué es? Es un cuaderno. | What is it? It's a notebook. | ¿Qué es? Es un cuaderno. | Phrase
🪑 | What is it? It's a desk. | ¿Qué es? Es un escritorio. | What is it? It's a desk. | ¿Qué es? Es un escritorio. | Phrase
🪑 | What is it? It's a chair. | ¿Qué es? Es una silla. | What is it? It's a chair. | ¿Qué es? Es una silla. | Phrase
⭕ | What is it? It's a circle. | ¿Qué es? Es un círculo. | What is it? It's a circle. | ¿Qué es? Es un círculo. | Phrase
🟥 | What is it? It's a square. | ¿Qué es? Es un cuadrado. | What is it? It's a square. | ¿Qué es? Es un cuadrado. | Phrase
🔺 | What is it? It's a triangle. | ¿Qué es? Es un triángulo. | What is it? It's a triangle. | ¿Qué es? Es un triángulo. | Phrase
▭ | What is it? It's a rectangle. | ¿Qué es? Es un rectángulo. | What is it? It's a rectangle. | ¿Qué es? Es un rectángulo. | Phrase
❌ | It isn't a pen. | No es un bolígrafo. | No, it isn't a pen. | No, no es un bolígrafo. | Phrase
❌ | It isn't a pencil. | No es un lápiz. | No, it isn't a pencil. | No, no es un lápiz. | Phrase
❌ | It isn't an eraser. | No es un borrador. | No, it isn't an eraser. | No, no es un borrador. | Phrase
❌ | It isn't a ruler. | No es una regla. | No, it isn't a ruler. | No, no es una regla. | Phrase
❌ | It isn't a pencil case. | No es un estuche. | No, it isn't a pencil case. | No, no es un estuche. | Phrase
❌ | It isn't a backpack. | No es una mochila. | No, it isn't a backpack. | No, no es una mochila. | Phrase
❌ | It isn't a book. | No es un libro. | No, it isn't a book. | No, no es un libro. | Phrase
❌ | It isn't a desk. | No es un escritorio. | No, it isn't a desk. | No, no es un escritorio. | Phrase
❌ | It isn't a chair. | No es una silla. | No, it isn't a chair. | No, no es una silla. | Phrase
❌ | It isn't a circle. | No es un círculo. | No, it isn't a circle. | No, no es un círculo. | Phrase
❌ | It isn't a square. | No es un cuadrado. | No, it isn't a square. | No, no es un cuadrado. | Phrase
❌ | It isn't a triangle. | No es un triángulo. | No, it isn't a triangle. | No, no es un triángulo. | Phrase
❌ | It isn't a rectangle. | No es un rectángulo. | No, it isn't a rectangle. | No, no es un rectángulo. | Phrase
❓ | Is it a pen? | ¿Es un bolígrafo? | Tell me, is it a pen? | Dime, ¿es un bolígrafo? | Question
❓ | Is it a pencil? | ¿Es un lápiz? | Tell me, is it a pencil? | Dime, ¿es un lápiz? | Question
❓ | Is it an eraser? | ¿Es un borrador? | Tell me, is it an eraser? | Dime, ¿es un borrador? | Question
❓ | Is it a ruler? | ¿Es una regla? | Tell me, is it a ruler? | Dime, ¿es una regla? | Question
❓ | Is it a pencil case? | ¿Es un estuche? | Tell me, is it a pencil case? | Dime, ¿es un estuche? | Question
❓ | Is it a backpack? | ¿Es una mochila? | Tell me, is it a backpack? | Dime, ¿es una mochila? | Question
❓ | Is it a book? | ¿Es un libro? | Tell me, is it a book? | Dime, ¿es un libro? | Question
❓ | Is it a notebook? | ¿Es un cuaderno? | Tell me, is it a notebook? | Dime, ¿es un cuaderno? | Question
❓ | Is it a desk? | ¿Es un escritorio? | Tell me, is it a desk? | Dime, ¿es un escritorio? | Question
❓ | Is it a chair? | ¿Es una silla? | Tell me, is it a chair? | Dime, ¿es una silla? | Question
❓ | Is it a square? | ¿Es un cuadrado? | Tell me, is it a square? | Dime, ¿es un cuadrado? | Question
❓ | Is it a triangle? | ¿Es un triángulo? | Tell me, is it a triangle? | Dime, ¿es un triángulo? | Question
❓ | Is it a rectangle? | ¿Es un rectángulo? | Tell me, is it a rectangle? | Dime, ¿es un rectángulo? | Question
    """.trimIndent(), "Basics 1 - List 1")

    private fun getList2(): List<Flashcard> = parseCards("""
🎨 | Paint | Pintura | I use paint | Uso pintura | Word
📄 | Paper | Papel | I need paper | Necesito papel | Word
🖍️ | Chalk | Tiza | Write with chalk | Escribe con tiza | Word
🧶 | Yarn | Lana / Hilo de tejer | The yarn is red | La lana es roja | Word
🧴 | Glue | Pegamento | Use the glue | Usa el pegamento | Word
📼 | Tape | Cinta pegante / Cinta adhesiva | I have tape | Tengo cinta pegante | Word
❓ | What's this? | ¿Qué es esto? | Tell me, what's this? | Dime, ¿qué es esto? | Question
🎨 | What's this? This is paint. | ¿Qué es esto? Esto es pintura. | What's this? This is paint. | ¿Qué es esto? Esto es pintura. | Phrase
🔴 | Red | Rojo | The car is red | El carro es rojo | Word
🟡 | Yellow | Amarillo | The sun is yellow | El sol es amarillo | Word
🔵 | Blue | Azul | The sky is blue | El cielo es azul | Word
⚪ | White | Blanco | The paper is white | El papel es blanco | Word
⚫ | Black | Negro | The cat is black | El gato es negro | Word
❓ | What color is it? | ¿Qué color es? | Tell me, what color is it? | Dime, ¿qué color es? | Question
🔴 | What color is it? It's red. | ¿Qué color es? Es rojo. | What color is it? It's red. | ¿Qué color es? Es rojo. | Phrase
🤝 | Let's share | Compartamos | Come on, let's share | Vamos, compartamos | Phrase
👍 | OK. | Está bien. | Yes, OK. | Sí, está bien. | Word
😇 | Be nice. | Sé amable. | Please, be nice. | Por favor, sé amable. | Phrase
🟢 | Green | Verde | The frog is green | La rana es verde | Word
🟣 | Purple | Morado | The flower is purple | La flor es morada | Word
🟠 | Orange | Naranja | The fish is orange | El pez es naranja | Word
🌸 | Pink | Rosa | The pig is pink | El cerdo es rosa | Word
🔘 | Gray | Gris | The mouse is gray | El ratón es gris | Word
🟤 | Brown | Marrón | The dog is brown | El perro es marrón | Word
🟢 | Blue and yellow make green. | Azul y amarillo hacen verde. | Look, blue and yellow make green. | Mira, azul y amarillo hacen verde. | Phrase
📄 | This is paper. | Esto es papel. | Look, this is paper. | Mira, esto es papel. | Phrase
🖍️ | This is chalk. | Esto es tiza. | Look, this is chalk. | Mira, esto es tiza. | Phrase
🧶 | This is yarn. | Esto es lana (hilo de tejer). | Look, this is yarn. | Mira, esto es lana. | Phrase
🧴 | This is glue. | Esto es pegamento. | Look, this is glue. | Mira, esto es pegamento. | Phrase
📼 | This is tape. | Esto es cinta pegante. | Look, this is tape. | Mira, esto es cinta pegante. | Phrase
🟡 | It's yellow. | Es amarillo. | Look, it's yellow. | Mira, es amarillo. | Phrase
🔵 | It's blue. | Es azul. | Look, it's blue. | Mira, es azul. | Phrase
⚪ | It's white. | Es blanco. | Look, it's white. | Mira, es blanco. | Phrase
⚫ | It's black. | Es negro. | Look, it's black. | Mira, es negro. | Phrase
🟢 | It's green. | Es verde. | Look, it's green. | Mira, es verde. | Phrase
🟣 | It's purple. | Es morado. | Look, it's purple. | Mira, es morado. | Phrase
🟠 | It's orange. | Es naranja. | Look, it's orange. | Mira, es naranja. | Phrase
🌸 | It's pink. | Es rosa. | Look, it's pink. | Mira, es rosa. | Phrase
🔘 | It's gray. | Es gris. | Look, it's gray. | Mira, es gris. | Phrase
🟤 | It's brown. | Es marrón. | Look, it's brown. | Mira, es marrón. | Phrase
🟠 | Red and yellow make orange. | Rojo y amarillo hacen naranja. | Look, red and yellow make orange. | Mira, rojo y amarillo hacen naranja. | Phrase
🟣 | Red and blue make purple. | Rojo y azul hacen morado. | Look, red and blue make purple. | Mira, rojo y azul hacen morado. | Phrase
🌸 | Red and white make pink. | Rojo y blanco hacen rosa. | Look, red and white make pink. | Mira, rojo y blanco hacen rosa. | Phrase
🔘 | Black and white make gray. | Negro y blanco hacen gris. | Look, black and white make gray. | Mira, negro y blanco hacen gris. | Phrase
    """.trimIndent(), "Basics 1 - List 2")

    private fun getList3(): List<Flashcard> = parseCards("""
1️⃣ | One | Uno | I have one car | Tengo un carro | Word
2️⃣ | Two | Dos | I have two balls | Tengo dos pelotas | Word
3️⃣ | Three | Tres | I have three kites | Tengo tres cometas | Word
4️⃣ | Four | Cuatro | I have four dolls | Tengo cuatro muñecas | Word
5️⃣ | Five | Cinco | I have five books | Tengo cinco libros | Word
6️⃣ | Six | Seis | I have six pens | Tengo seis bolígrafos | Word
7️⃣ | Seven | Siete | I have seven games | Tengo siete juegos | Word
8️⃣ | Eight | Ocho | I have eight marbles | Tengo ocho canicas | Word
9️⃣ | Nine | Nueve | I have nine puzzles | Tengo nueve rompecabezas | Word
🔟 | Ten | Diez | I have ten cards | Tengo diez cartas | Word
1️⃣1️⃣ | Eleven | Once | I have eleven pencils | Tengo once lápices | Word
1️⃣2️⃣ | Twelve | Doce | I have twelve chairs | Tengo doce sillas | Word
❓ | How old are you? | ¿Cuántos años tienes? | Tell me, how old are you? | Dime, ¿cuántos años tienes? | Question
🧒 | I'm eight. | Tengo ocho años. | Hello, I'm eight. | Hola, tengo ocho años. | Phrase
🪆 | Doll | Muñeca | I have a doll | Tengo una muñeca | Word
🪆 | Dolls | Muñecas | The dolls are red | Las muñecas son rojas | Word
⚽ | Ball | Pelota | I play with a ball | Juego con una pelota | Word
⚽ | Balls | Pelotas | I have two balls | Tengo dos pelotas | Word
🚗 | Car | Carro | The car is blue | El carro es azul | Word
🚗 | Cars | Carros | I see three cars | Veo tres carros | Word
🪁 | Kite | Cometa | The kite is yellow | La cometa es amarilla | Word
🪁 | Kites | Cometas | Four kites in the sky | Cuatro cometas en el cielo | Word
❓ | How many | Cuántos | How many cars? | ¿Cuántos carros? | Question
❓ | How many dolls? | ¿Cuántas muñecas? | Tell me, how many dolls? | Dime, ¿cuántas muñecas? | Question
🪆 | How many dolls? One doll. | ¿Cuántas muñecas? Una muñeca. | How many dolls? One doll. | ¿Cuántas muñecas? Una muñeca. | Phrase
🪆 | How many dolls? Two dolls. | ¿Cuántas muñecas? Dos muñecas. | How many dolls? Two dolls. | ¿Cuántas muñecas? Dos muñecas. | Phrase
🔄 | It's your turn. | Es tu turno. | Now, it's your turn. | Ahora, es tu turno. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
⚖️ | Be fair. | Sé justo. | Please, be fair. | Por favor, sé justo. | Phrase
🎮 | Game | Juego | This is a game | Este es un juego | Word
🔮 | Marble | Canica | The marble is small | La canica es pequeña | Word
🧩 | Puzzle | Rompecabezas | I like this puzzle | Me gusta este rompecabezas | Word
🃏 | Card | Carta | Pick a card | Elige una carta | Word
🎮 | I have one game. | Tengo un juego. | Look, I have one game. | Mira, tengo un juego. | Phrase
🎮 | I have two games. | Tengo dos juegos. | Look, I have two games. | Mira, tengo dos juegos. | Phrase
🧒 | I'm one. | Tengo un año. | Look, I'm one. | Mira, tengo un año. | Phrase
🧒 | I'm two. | Tengo dos años. | Look, I'm two. | Mira, tengo dos años. | Phrase
🧒 | I'm three. | Tengo tres años. | Look, I'm three. | Mira, tengo tres años. | Phrase
🧒 | I'm four. | Tengo cuatro años. | Look, I'm four. | Mira, tengo cuatro años. | Phrase
🧒 | I'm five. | Tengo cinco años. | Look, I'm five. | Mira, tengo cinco años. | Phrase
🧒 | I'm six. | Tengo seis años. | Look, I'm six. | Mira, tengo seis años. | Phrase
🧒 | I'm seven. | Tengo siete años. | Look, I'm seven. | Mira, tengo siete años. | Phrase
🧒 | I'm nine. | Tengo nueve años. | Look, I'm nine. | Mira, tengo nueve años. | Phrase
🧒 | I'm ten. | Tengo diez años. | Look, I'm ten. | Mira, tengo diez años. | Phrase
🧒 | I'm eleven. | Tengo once años. | Look, I'm eleven. | Mira, tengo once años. | Phrase
🧒 | I'm twelve. | Tengo doce años. | Look, I'm twelve. | Mira, tengo doce años. | Phrase
❓ | How many balls? | ¿Cuántas pelotas? | Tell me, how many balls? | Dime, ¿cuántas pelotas? | Question
⚽ | One ball. | Una pelota. | Look, one ball. | Mira, una pelota. | Phrase
⚽ | Two balls. | Dos pelotas. | Look, two balls. | Mira, dos pelotas. | Phrase
❓ | How many cars? | ¿Cuántos carros? | Tell me, how many cars? | Dime, ¿cuántos carros? | Question
🚗 | One car. | Un carro. | Look, one car. | Mira, un carro. | Phrase
🚗 | Three cars. | Tres carros. | Look, three cars. | Mira, tres carros. | Phrase
❓ | How many kites? | ¿Cuántas cometas? | Tell me, how many kites? | Dime, ¿cuántas cometas? | Question
🪁 | One kite. | Una cometa. | Look, one kite. | Mira, una cometa. | Phrase
🪁 | Four kites. | Cuatro cometas. | Look, four kites. | Mira, cuatro cometas. | Phrase
❓ | How many games? | ¿Cuántos juegos? | Tell me, how many games? | Dime, ¿cuántos juegos? | Question
🎮 | One game. | Un juego. | Look, one game. | Mira, un juego. | Phrase
🎮 | Five games. | Cinco juegos. | Look, five games. | Mira, cinco juegos. | Phrase
❓ | How many marbles? | ¿Cuántas canicas? | Tell me, how many marbles? | Dime, ¿cuántas canicas? | Question
🔮 | One marble. | Una canica. | Look, one marble. | Mira, una canica. | Phrase
🔮 | Six marbles. | Seis canicas. | Look, six marbles. | Mira, seis canicas. | Phrase
❓ | How many puzzles? | ¿Cuántos rompecabezas? | Tell me, how many puzzles? | Dime, ¿cuántos rompecabezas? | Question
🧩 | One puzzle. | Un rompecabezas. | Look, one puzzle. | Mira, un rompecabezas. | Phrase
🧩 | Seven puzzles. | Siete rompecabezas. | Look, seven puzzles. | Mira, siete rompecabezas. | Phrase
❓ | How many cards? | ¿Cuántas cartas? | Tell me, how many cards? | Dime, ¿cuántas cartas? | Question
🃏 | One card. | Una carta. | Look, one card. | Mira, una carta. | Phrase
🃏 | Eight cards. | Ocho cartas. | Look, eight cards. | Mira, ocho cartas. | Phrase
❓ | How many pens? | ¿Cuántos bolígrafos? | Tell me, how many pens? | Dime, ¿cuántos bolígrafos? | Question
🖊️ | One pen. | Un bolígrafo. | Look, one pen. | Mira, un bolígrafo. | Phrase
🖊️ | Nine pens. | Nueve bolígrafos. | Look, nine pens. | Mira, nueve bolígrafos. | Phrase
❓ | How many books? | ¿Cuántos libros? | Tell me, how many books? | Dime, ¿cuántos libros? | Question
📖 | One book. | Un libro. | Look, one book. | Mira, un libro. | Phrase
📖 | Ten books. | Diez libros. | Look, ten books. | Mira, diez libros. | Phrase
❓ | How many colors? | ¿Cuántos colores? | Tell me, how many colors? | Dime, ¿cuántos colores? | Question
🎨 | One color. | Un color. | Look, one color. | Mira, un color. | Phrase
🎨 | Eleven colors. | Once colores. | Look, eleven colors. | Mira, once colores. | Phrase
🪆 | I have one doll. | Tengo una muñeca. | Yes, I have one doll. | Sí, tengo una muñeca. | Phrase
🪆 | I have three dolls. | Tengo tres muñecas. | Yes, I have three dolls. | Sí, tengo tres muñecas. | Phrase
⚽ | I have one ball. | Tengo una pelota. | Yes, I have one ball. | Sí, tengo una pelota. | Phrase
⚽ | I have four balls. | Tengo cuatro pelotas. | Yes, I have four balls. | Sí, tengo cuatro pelotas. | Phrase
🚗 | I have one car. | Tengo un carro. | Yes, I have one car. | Sí, tengo un carro. | Phrase
🚗 | I have five cars. | Tengo cinco carros. | Yes, I have five cars. | Sí, tengo cinco carros. | Phrase
🪁 | I have one kite. | Tengo una cometa. | Yes, I have one kite. | Sí, tengo una cometa. | Phrase
🪁 | I have six kites. | Tengo seis cometas. | Yes, I have six kites. | Sí, tengo seis cometas. | Phrase
🔮 | I have one marble. | Tengo una canica. | Yes, I have one marble. | Sí, tengo una canica. | Phrase
🔮 | I have seven marbles. | Tengo siete canicas. | Yes, I have seven marbles. | Sí, tengo siete canicas. | Phrase
🧩 | I have one puzzle. | Tengo un rompecabezas. | Yes, I have one puzzle. | Sí, tengo un rompecabezas. | Phrase
🧩 | I have eight puzzles. | Tengo ocho rompecabezas. | Yes, I have eight puzzles. | Sí, tengo ocho rompecabezas. | Phrase
🃏 | I have one card. | Tengo una carta. | Yes, I have one card. | Sí, tengo una carta. | Phrase
🃏 | I have nine cards. | Tengo nueve cartas. | Yes, I have nine cards. | Sí, tengo nueve cartas. | Phrase
🖊️ | I have ten pens. | Tengo diez bolígrafos. | Yes, I have ten pens. | Sí, tengo diez bolígrafos. | Phrase
📖 | I have eleven books. | Tengo once libros. | Yes, I have eleven books. | Sí, tengo once libros. | Phrase
🪑 | I have twelve chairs. | Tengo doce sillas. | Yes, I have twelve chairs. | Sí, tengo doce sillas. | Phrase
    """.trimIndent(), "Basics 1 - List 3")

    private fun getList4(): List<Flashcard> = parseCards("""
👩 | Mother | Madre | I love my mother | Amo a mi madre | Word
👨 | Father | Padre | He is my father | Él es mi padre | Word
👦 | Brother | Hermano | I have a brother | Tengo un hermano | Word
👧 | Sister | Hermana | She is my sister | Ella es mi hermana | Word
👵 | Grandmother | Abuela | My grandmother is nice | Mi abuela es buena | Word
👴 | Grandfather | Abuelo | My grandfather is tall | Mi abuelo es alto | Word
🙋‍♂️ | He | Él (se refiere a él - pronombre masculino) | He is my brother | Él es mi hermano (se refiere a él) | Word
🙋‍♀️ | She | Ella (se refiere a ella - pronombre femenino) | She is my sister | Ella es mi hermana (se refiere a ella) | Word
🎒 | His | Su / Sus (se refiere a él / de él) | His name is Tom | Su nombre es Tom (de él) | Word
👜 | Her | Su / Sus (se refiere a ella / de ella) | Her name is Anna | Su nombre es Anna (de ella) | Word
🏷️ | My | Mi / Mis (me pertenece / de mí) | This is my family | Esta es mi familia (de mí) | Word
🏷️ | Your | Tu / Tus (te pertenece / de ti) | What is your name? | ¿Cuál es tu nombre? (de ti) | Word
👨‍👩‍👧‍👦 | Our | Nuestro / Nuestra (de nosotros) | This is our mother | Esta es nuestra madre (de nosotros) | Word
👥 | Their | Su / Sus (se refiere a ellos o ellas / de ellos) | This is their house | Esta es su casa (de ellos) | Word
🧃 | Juice | Jugo | I drink juice | Bebo jugo | Word
🍗 | Chicken | Pollo | I eat chicken | Como pollo | Word
🐟 | Fish | Pescado | The fish is good | El pescado es bueno | Word
🍦 | Ice cream | Helado | I want ice cream | Quiero helado | Word
🍕 | Pizza | Pizza | I eat pizza | Como pizza | Word
🍚 | Rice | Arroz | The rice is white | El arroz es blanco | Word
🍰 | Cake | Pastel | A big cake | Un pastel grande | Word
🍞 | Bread | Pan | I like bread | Me gusta el pan | Word
❓ | Who's this? | ¿Quién es este? | Tell me, who's this? | Dime, ¿quién es este? | Question
❓ | Who's he? | ¿Quién es él? (se refiere a él) | Who's he? He is my father | ¿Quién es él? Él es mi padre | Question
👨 | Who's he? He's my father. | ¿Quién es él? Él es mi padre. | Who's he? He's my father. | ¿Quién es él? Él es mi padre. | Phrase
👦 | Who's he? He's my brother. | ¿Quién es él? Él es mi hermano. | Who's he? He's my brother. | ¿Quién es él? Él es mi hermano. | Phrase
👴 | Who's he? He's my grandfather. | ¿Quién es él? Él es mi abuelo. | Who's he? He's my grandfather. | ¿Quién es él? Él es mi abuelo. | Phrase
❓ | Who's she? | ¿Quién es ella? (se refiere a ella) | Who's she? She is my mother | ¿Quién es ella? Ella es mi madre | Question
👩 | Who's she? She's my mother. | ¿Quién es ella? Ella es mi madre. | Who's she? She's my mother. | ¿Quién es ella? Ella es mi madre. | Phrase
👧 | Who's she? She's my sister. | ¿Quién es ella? Ella es mi hermana. | Who's she? She's my sister. | ¿Quién es ella? Ella es mi hermana. | Phrase
👵 | Who's she? She's my grandmother. | ¿Quién es ella? Ella es mi abuela. | Who's she? She's my grandmother. | ¿Quién es ella? Ella es mi abuela. | Phrase
❓ | What's his name? | ¿Cuál es su nombre? (de él - se refiere a él) | Tell me, what's his name? | Dime, ¿cuál es su nombre? (de él) | Question
🙋‍♂️ | What's his name? His name is Tom. | ¿Cuál es su nombre? Su nombre es Tom. | What's his name? His name is Tom. | ¿Cuál es su nombre? Su nombre es Tom. | Phrase
❓ | What's her name? | ¿Cuál es su nombre? (de ella - se refiere a ella) | Tell me, what's her name? | Dime, ¿cuál es su nombre? (de ella) | Question
🙋‍♀️ | What's her name? Her name is Anna. | ¿Cuál es su nombre? Su nombre es Anna. | What's her name? Her name is Anna. | ¿Cuál es su nombre? Su nombre es Anna. | Phrase
👨 | This is his father. | Este es su padre (de él - se refiere a él). | Look, this is his father. | Mira, este es su padre (de él). | Phrase
👨 | This is her father. | Este es su padre (de ella - se refiere a ella). | Look, this is her father. | Mira, este es su padre (de ella). | Phrase
👩 | This is his mother. | Esta es su madre (de él - se refiere a él). | Look, this is his mother. | Mira, esta es su madre (de él). | Phrase
👩 | This is her mother. | Esta es su madre (de ella - se refiere a ella). | Look, this is her mother. | Mira, esta es su madre (de ella). | Phrase
👦 | This is his brother. | Este es su hermano (de él - se refiere a él). | Look, this is his brother. | Mira, este es su hermano (de él). | Phrase
👧 | This is her sister. | Esta es su hermana (de ella - se refiere a ella). | Look, this is her sister. | Mira, esta es su hermana (de ella). | Phrase
👩 | This is my mother. | Esta es mi madre. | Look, this is my mother. | Mira, esta es mi madre. | Phrase
👨 | This is my father. | Este es mi padre. | Look, this is my father. | Mira, este es mi padre. | Phrase
👦 | This is my brother. | Este es mi hermano. | Look, this is my brother. | Mira, este es mi hermano. | Phrase
👧 | This is my sister. | Esta es mi hermana. | Look, this is my sister. | Mira, esta es mi hermana. | Phrase
👵 | This is my grandmother. | Esta es mi abuela. | Look, this is my grandmother. | Mira, esta es mi abuela. | Phrase
👴 | This is my grandfather. | Este es mi abuelo. | Look, this is my grandfather. | Mira, este es mi abuelo. | Phrase
👍 | I like juice. | Me gusta el jugo. | Yes, I like juice. | Sí, me gusta el jugo. | Phrase
👎 | I don't like juice. | No me gusta el jugo. | No, I don't like juice. | No, no me gusta el jugo. | Phrase
🤲 | Here you are. | Aquí tienes. | Please, here you are. | Por favor, aquí tienes. | Phrase
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
😊 | You're welcome. | De nada. | Oh, you're welcome. | Oh, de nada. | Phrase
❤️ | Be kind. | Sé amable. | Always be kind. | Siempre sé amable. | Phrase
❓ | What's this? | ¿Qué es esto? | Tell me, what's this? | Dime, ¿qué es esto? | Question
🍕 | This is pizza. | Esto es pizza. | Look, this is pizza. | Mira, esto es pizza. | Phrase
👍 | I like pizza. | Me gusta la pizza. | Yes, I like pizza. | Sí, me gusta la pizza. | Phrase
👍 | I like chicken. | Me gusta el pollo. | Yes, I like chicken. | Sí, me gusta el pollo. | Phrase
👎 | I don't like chicken. | No me gusta el pollo. | No, I don't like chicken. | No, no me gusta el pollo. | Phrase
👍 | I like fish. | Me gusta el pescado. | Yes, I like fish. | Sí, me gusta el pescado. | Phrase
👎 | I don't like fish. | No me gusta el pescado. | No, I don't like fish. | No, no me gusta el pescado. | Phrase
👍 | I like ice cream. | Me gusta el helado. | Yes, I like ice cream. | Sí, me gusta el helado. | Phrase
👎 | I don't like ice cream. | No me gusta el helado. | No, I don't like ice cream. | No, no me gusta el helado. | Phrase
👎 | I don't like pizza. | No me gusta la pizza. | No, I don't like pizza. | No, no me gusta la pizza. | Phrase
👍 | I like rice. | Me gusta el arroz. | Yes, I like rice. | Sí, me gusta el arroz. | Phrase
👎 | I don't like rice. | No me gusta el arroz. | No, I don't like rice. | No, no me gusta el arroz. | Phrase
👍 | I like cake. | Me gusta el pastel. | Yes, I like cake. | Sí, me gusta el pastel. | Phrase
👎 | I don't like cake. | No me gusta el pastel. | No, I don't like cake. | No, no me gusta el pastel. | Phrase
👍 | I like bread. | Me gusta el pan. | Yes, I like bread. | Sí, me gusta el pan. | Phrase
👎 | I don't like bread. | No me gusta el pan. | No, I don't like bread. | No, no me gusta el pan. | Phrase
🧃 | This is juice. | Esto es jugo. | Look, this is juice. | Mira, esto es jugo. | Phrase
🍗 | This is chicken. | Esto es pollo. | Look, this is chicken. | Mira, esto es pollo. | Phrase
🐟 | This is fish. | Esto es pescado. | Look, this is fish. | Mira, esto es pescado. | Phrase
🍦 | This is ice cream. | Esto es helado. | Look, this is ice cream. | Mira, esto es helado. | Phrase
🍚 | This is rice. | Esto es arroz. | Look, this is rice. | Mira, esto es arroz. | Phrase
🍰 | This is cake. | Esto es pastel. | Look, this is cake. | Mira, esto es pastel. | Phrase
🍞 | This is bread. | Esto es pan. | Look, this is bread. | Mira, esto es pan. | Phrase
🍗 | This is chicken. I like chicken. | Esto es pollo. Me gusta el pollo. | Look, this is chicken. I like chicken. | Mira, esto es pollo. Me gusta el pollo. | Phrase
🐟 | This is fish. I like fish. | Esto es pescado. Me gusta el pescado. | Look, this is fish. I like fish. | Mira, esto es pescado. Me gusta el pescado. | Phrase
🍦 | This is ice cream. I like ice cream. | Esto es helado. Me gusta el helado. | Look, this is ice cream. I like ice cream. | Mira, esto es helado. Me gusta el helado. | Phrase
🍚 | This is rice. I like rice. | Esto es arroz. Me gusta el arroz. | Look, this is rice. I like rice. | Mira, esto es arroz. Me gusta el arroz. | Phrase
🍰 | This is cake. I like cake. | Esto es pastel. Me gusta el pastel. | Look, this is cake. I like cake. | Mira, esto es pastel. Me gusta el pastel. | Phrase
🍞 | This is bread. I like bread. | Esto es pan. Me gusta el pan. | Look, this is bread. I like bread. | Mira, esto es pan. Me gusta el pan. | Phrase
    """.trimIndent(), "Basics 1 - List 4")

    private fun getList5(): List<Flashcard> = parseCards("""
🌸 | Flower | Flor | The flower is red | La flor es roja | Word
🌳 | Tree | Árbol | The tree is big | El árbol es grande | Word
🪨 | Rock | Roca | The rock is gray | La roca es gris | Word
🏞️ | River | Río | The river is long | El río es largo | Word
⛰️ | Hill | Colina | Walk on the hill | Camina en la colina | Word
🌊 | Lake | Lago | The lake is blue | El lago es azul | Word
❓ | What can you see? | ¿Qué puedes ver? | Tell me, what can you see? | Dime, ¿qué puedes ver? | Question
👁️ | What can you see? I can see a flower. | ¿Qué puedes ver? Puedo ver una flor. | What can you see? I can see a flower. | ¿Qué puedes ver? Puedo ver una flor. | Phrase
👁️ | What can you see? I can see flowers. | ¿Qué puedes ver? Puedo ver flores. | What can you see? I can see flowers. | ¿Qué puedes ver? Puedo ver flores. | Phrase
⚽ | Play soccer | Jugar fútbol | I want to play soccer | Quiero jugar fútbol | Phrase
🪢 | Jump rope | Saltar la cuerda | I jump rope today | Salto la cuerda hoy | Phrase
🪁 | Fly a kite | Volar una cometa | Fly a kite high | Vuela una cometa alto | Phrase
🚲 | Ride a bike | Montar en bicicleta | I ride a bike fast | Monto en bicicleta rápido | Phrase
✅ | I can play soccer. | Puedo jugar fútbol. | Yes, I can play soccer. | Sí, puedo jugar fútbol. | Phrase
❌ | I can't play soccer. | No puedo jugar fútbol. | No, I can't play soccer. | No, no puedo jugar fútbol. | Phrase
🆘 | Please help me. | Por favor ayúdame. | Please help me now. | Por favor ayúdame ahora. | Phrase
👍 | Sure. | Claro. | Yes, sure. | Sí, claro. | Word
🤝 | Be helpful. | Sé servicial. | Always be helpful. | Siempre sé servicial. | Phrase
🐢 | Turtle | Tortuga | The turtle is slow | La tortuga es lenta | Word
🐸 | Frog | Rana | The frog is green | La rana es verde | Word
🕷️ | Spider | Araña | The spider is black | La araña es negra | Word
🐜 | Ant | Hormiga | The ant is small | La hormiga es pequeña | Word
❓ | Can you see a turtle? | ¿Puedes ver una tortuga? | Tell me, can you see a turtle? | Dime, ¿puedes ver una tortuga? | Question
❓ | Can you see an ant? | ¿Puedes ver una hormiga? | Tell me, can you see an ant? | Dime, ¿puedes ver una hormiga? | Question
✅ | Can you see a turtle? Yes, I can. | ¿Puedes ver una tortuga? Sí, puedo. | Can you see a turtle? Yes, I can. | ¿Puedes ver una tortuga? Sí, puedo. | Phrase
❌ | Can you see a turtle? No, I can't. | ¿Puedes ver una tortuga? No, no puedo. | Can you see a turtle? No, I can't. | ¿Puedes ver una tortuga? No, no puedo. | Phrase
👁️ | I can see a tree. | Puedo ver un árbol. | Look, I can see a tree. | Mira, puedo ver un árbol. | Phrase
👁️ | I can see a rock. | Puedo ver una roca. | Look, I can see a rock. | Mira, puedo ver una roca. | Phrase
👁️ | I can see a river. | Puedo ver un río. | Look, I can see a river. | Mira, puedo ver un río. | Phrase
👁️ | I can see a hill. | Puedo ver una colina. | Look, I can see a hill. | Mira, puedo ver una colina. | Phrase
👁️ | I can see a lake. | Puedo ver un lago. | Look, I can see a lake. | Mira, puedo ver un lago. | Phrase
👁️ | I can see a turtle. | Puedo ver una tortuga. | Look, I can see a turtle. | Mira, puedo ver una tortuga. | Phrase
👁️ | I can see a frog. | Puedo ver una rana. | Look, I can see a frog. | Mira, puedo ver una rana. | Phrase
👁️ | I can see a spider. | Puedo ver una araña. | Look, I can see a spider. | Mira, puedo ver una araña. | Phrase
👁️ | I can see an ant. | Puedo ver una hormiga. | Look, I can see an ant. | Mira, puedo ver una hormiga. | Phrase
👁️ | I can see trees. | Puedo ver árboles. | Look, I can see trees. | Mira, puedo ver árboles. | Phrase
👁️ | I can see rocks. | Puedo ver rocas. | Look, I can see rocks. | Mira, puedo ver rocas. | Phrase
👁️ | I can see rivers. | Puedo ver ríos. | Look, I can see rivers. | Mira, puedo ver ríos. | Phrase
👁️ | I can see hills. | Puedo ver colinas. | Look, I can see hills. | Mira, puedo ver colinas. | Phrase
👁️ | I can see lakes. | Puedo ver lagos. | Look, I can see lakes. | Mira, puedo ver lagos. | Phrase
👁️ | I can see turtles. | Puedo ver tortugas. | Look, I can see turtles. | Mira, puedo ver tortugas. | Phrase
👁️ | I can see frogs. | Puedo ver ranas. | Look, I can see frogs. | Mira, puedo ver ranas. | Phrase
👁️ | I can see spiders. | Puedo ver arañas. | Look, I can see spiders. | Mira, puedo ver arañas. | Phrase
👁️ | I can see ants. | Puedo ver hormigas. | Look, I can see ants. | Mira, puedo ver hormigas. | Phrase
✅ | I can jump rope. | Puedo saltar la cuerda. | Yes, I can jump rope. | Sí, puedo saltar la cuerda. | Phrase
❌ | I can't jump rope. | No puedo saltar la cuerda. | No, I can't jump rope. | No, no puedo saltar la cuerda. | Phrase
✅ | I can fly a kite. | Puedo volar una cometa. | Yes, I can fly a kite. | Sí, puedo volar una cometa. | Phrase
❌ | I can't fly a kite. | No puedo volar una cometa. | No, I can't fly a kite. | No, no puedo volar una cometa. | Phrase
✅ | I can ride a bike. | Puedo montar en bicicleta. | Yes, I can ride a bike. | Sí, puedo montar en bicicleta. | Phrase
❌ | I can't ride a bike. | No puedo montar en bicicleta. | No, I can't ride a bike. | No, no puedo montar en bicicleta. | Phrase
❓ | Can you see a tree? | ¿Puedes ver un árbol? | Tell me, can you see a tree? | Dime, ¿puedes ver un árbol? | Question
❓ | Can you see a rock? | ¿Puedes ver una roca? | Tell me, can you see a rock? | Dime, ¿puedes ver una roca? | Question
❓ | Can you see a river? | ¿Puedes ver un río? | Tell me, can you see a river? | Dime, ¿puedes ver un río? | Question
❓ | Can you see a hill? | ¿Puedes ver una colina? | Tell me, can you see a hill? | Dime, ¿puedes ver una colina? | Question
❓ | Can you see a lake? | ¿Puedes ver un lago? | Tell me, can you see a lake? | Dime, ¿puedes ver un lago? | Question
❓ | Can you see a frog? | ¿Puedes ver una rana? | Tell me, can you see a frog? | Dime, ¿puedes ver una rana? | Question
❓ | Can you see a spider? | ¿Puedes ver una araña? | Tell me, can you see a spider? | Dime, ¿puedes ver una araña? | Question
❓ | Can you see a flower? | ¿Puedes ver una flor? | Tell me, can you see a flower? | Dime, ¿puedes ver una flor? | Question
👁️ | I can see a pen. | Puedo ver un bolígrafo. | Look, I can see a pen. | Mira, puedo ver un bolígrafo. | Phrase
👁️ | I can see a pencil. | Puedo ver un lápiz. | Look, I can see a pencil. | Mira, puedo ver un lápiz. | Phrase
👁️ | I can see a book. | Puedo ver un libro. | Look, I can see a book. | Mira, puedo ver un libro. | Phrase
👁️ | I can see a desk. | Puedo ver un escritorio. | Look, I can see a desk. | Mira, puedo ver un escritorio. | Phrase
👁️ | I can see a car. | Puedo ver un carro. | Look, I can see a car. | Mira, puedo ver un carro. | Phrase
👁️ | I can see a doll. | Puedo ver una muñeca. | Look, I can see a doll. | Mira, puedo ver una muñeca. | Phrase
👁️ | I can see a ball. | Puedo ver una pelota. | Look, I can see a ball. | Mira, puedo ver una pelota. | Phrase
👁️ | I can see pizza. | Puedo ver pizza. | Look, I can see pizza. | Mira, puedo ver pizza. | Phrase
👁️ | I can see ice cream. | Puedo ver helado. | Look, I can see ice cream. | Mira, puedo ver helado. | Phrase
❓ | Can you see a pen? | ¿Puedes ver un bolígrafo? | Tell me, can you see a pen? | Dime, ¿puedes ver un bolígrafo? | Question
❓ | Can you see a book? | ¿Puedes ver un libro? | Tell me, can you see a book? | Dime, ¿puedes ver un libro? | Question
❓ | Can you see a car? | ¿Puedes ver un carro? | Tell me, can you see a car? | Dime, ¿puedes ver un carro? | Question
❓ | Can you see a ball? | ¿Puedes ver una pelota? | Tell me, can you see a ball? | Dime, ¿puedes ver una pelota? | Question
❓ | Can you see a backpack? | ¿Puedes ver una mochila? | Tell me, can you see a backpack? | Dime, ¿puedes ver una mochila? | Question
❓ | Can you see an eraser? | ¿Puedes ver un borrador? | Tell me, can you see an eraser? | Dime, ¿puedes ver un borrador? | Question
    """.trimIndent(), "Basics 1 - List 5")

    private fun getList6(): List<Flashcard> = parseCards("""
🐒 | Monkey | Mono | I see a monkey | Veo un mono | Word
🐘 | Elephant | Elefante | The elephant is big | El elefante es grande | Word
🐅 | Tiger | Tigre | The tiger is fast | El tigre es rápido | Word
🐻 | Bear | Oso | The bear is brown | El oso es marrón | Word
🦘 | Kangaroo | Canguro | The kangaroo can jump | El canguro puede saltar | Word
🐧 | Penguin | Pingüino | The penguin is cold | El pingüino tiene frío | Word
📥 | In | En / Dentro | The ball is in the box | La pelota está en la caja | Word
🔛 | On | En / Sobre | The book is on the desk | El libro está en el escritorio | Word
⬇️ | Under | Debajo | The cat is under the chair | El gato está debajo de la silla | Word
🅰️ | The | El / La / Los / Las | The car is red | El carro es rojo | Word
❓ | Where is the monkey? | ¿Dónde está el mono? | Tell me, where is the monkey? | Dime, ¿dónde está el mono? | Question
🪨 | Where is the monkey? It's on the rock. | ¿Dónde está el mono? Está en la roca. | Where is the monkey? It's on the rock. | ¿Dónde está el mono? Está en la roca. | Phrase
🐍 | Snake | Serpiente | The snake is long | La serpiente es larga | Word
🦒 | Giraffe | Jirafa | The giraffe is tall | La jirafa es alta | Word
🦁 | Lion | León | The lion is sleeping | El león está durmiendo | Word
🦓 | Zebra | Cebra | The zebra is black and white | La cebra es blanca y negra | Word
❓ | Where are the snakes? | ¿Dónde están las serpientes? | Tell me, where are the snakes? | Dime, ¿dónde están las serpientes? | Question
🪨 | Where are the snakes? They're on the rock. | ¿Dónde están las serpientes? Están en la roca. | Where are the snakes? They're on the rock. | ¿Dónde están las serpientes? Están en la roca. | Phrase
😔 | I'm sorry. | Lo siento. | Oh, I'm sorry. | Oh, lo siento. | Phrase
👍 | That's OK. | Está bien. | Yes, that's OK. | Sí, está bien. | Phrase
🛡️ | Be safe. | Cuídate. | Please, be safe. | Por favor, cuídate. | Phrase
🏃 | Run | Correr | I can run fast | Puedo correr rápido | Word
🦘 | Hop | Saltar | The frog can hop | La rana puede saltar | Word
🏊 | Swim | Nadar | I like to swim | Me gusta nadar | Word
🚶 | Walk | Caminar | I walk in the park | Camino en el parque | Word
❓ | Can zebras run? | ¿Pueden correr las cebras? | Tell me, can zebras run? | Dime, ¿pueden correr las cebras? | Question
❓ | Can penguins run? | ¿Pueden correr los pingüinos? | Tell me, can penguins run? | Dime, ¿pueden correr los pingüinos? | Question
✅ | Yes, they can. | Sí, sí pueden. | Yes, they can run. | Sí, sí pueden correr. | Phrase
❌ | No, they can't. | No, no pueden. | No, they can't fly. | No, no pueden volar. | Phrase
❓ | Where is the elephant? | ¿Dónde está el elefante? | Tell me, where is the elephant? | Dime, ¿dónde está el elefante? | Question
❓ | Where is the tiger? | ¿Dónde está el tigre? | Tell me, where is the tiger? | Dime, ¿dónde está el tigre? | Question
❓ | Where is the bear? | ¿Dónde está el oso? | Tell me, where is the bear? | Dime, ¿dónde está el oso? | Question
❓ | Where is the kangaroo? | ¿Dónde está el canguro? | Tell me, where is the kangaroo? | Dime, ¿dónde está el canguro? | Question
❓ | Where is the penguin? | ¿Dónde está el pingüino? | Tell me, where is the penguin? | Dime, ¿dónde está el pingüino? | Question
❓ | Where is the snake? | ¿Dónde está la serpiente? | Tell me, where is the snake? | Dime, ¿dónde está la serpiente? | Question
❓ | Where is the giraffe? | ¿Dónde está la jirafa? | Tell me, where is the giraffe? | Dime, ¿dónde está la jirafa? | Question
❓ | Where is the lion? | ¿Dónde está el león? | Tell me, where is the lion? | Dime, ¿dónde está el león? | Question
❓ | Where is the zebra? | ¿Dónde está la cebra? | Tell me, where is the zebra? | Dime, ¿dónde está la cebra? | Question
🌳 | It's under the tree. | Está debajo del árbol. | Look, it's under the tree. | Mira, está debajo del árbol. | Phrase
🌊 | It's in the river. | Está en el río. | Look, it's in the river. | Mira, está en el río. | Phrase
⛰️ | It's on the hill. | Está en la colina. | Look, it's on the hill. | Mira, está en la colina. | Phrase
🌊 | It's in the lake. | Está en el lago. | Look, it's in the lake. | Mira, está en el lago. | Phrase
🌳 | It's in the tree. | Está en el árbol. | Look, it's in the tree. | Mira, está en el árbol. | Phrase
❓ | Where are the monkeys? | ¿Dónde están los monos? | Tell me, where are the monkeys? | Dime, ¿dónde están los monos? | Question
❓ | Where are the elephants? | ¿Dónde están los elefantes? | Tell me, where are the elephants? | Dime, ¿dónde están los elefantes? | Question
❓ | Where are the tigers? | ¿Dónde están los tigres? | Tell me, where are the tigers? | Dime, ¿dónde están los tigres? | Question
❓ | Where are the bears? | ¿Dónde están los osos? | Tell me, where are the bears? | Dime, ¿dónde están los osos? | Question
❓ | Where are the kangaroos? | ¿Dónde están los canguros? | Tell me, where are the kangaroos? | Dime, ¿dónde están los kangaroos? | Question
❓ | Where are the penguins? | ¿Dónde están los pingüinos? | Tell me, where are the penguins? | Dime, ¿dónde están los pingüinos? | Question
❓ | Where are the giraffes? | ¿Dónde están las jirafas? | Tell me, where are the giraffes? | Dime, ¿dónde están las jirafas? | Question
❓ | Where are the lions? | ¿Dónde están los leones? | Tell me, where are the lions? | Dime, ¿dónde están los leones? | Question
❓ | Where are the zebras? | ¿Dónde están las cebras? | Tell me, where are the zebras? | Dime, ¿dónde están las cebras? | Question
🌳 | They're in the tree. | Están en el árbol. | Look, they're in the tree. | Mira, están en el árbol. | Phrase
🪨 | They're under the rock. | Están debajo de la roca. | Look, they're under the rock. | Mira, están debajo de la roca. | Phrase
🌊 | They're in the river. | Están en el río. | Look, they're in the river. | Mira, están en el río. | Phrase
⛰️ | They're on the hill. | Están en la colina. | Look, they're on the hill. | Mira, están en la colina. | Phrase
🌊 | They're in the lake. | Están en el lago. | Look, they're in the lake. | Mira, están en el lago. | Phrase
🌳 | They're under the tree. | Están debajo del árbol. | Look, they're under the tree. | Mira, están debajo del árbol. | Phrase
❓ | Can monkeys hop? | ¿Pueden saltar los monos? | Tell me, can monkeys hop? | Dime, ¿pueden saltar los monos? | Question
❓ | Can elephants walk? | ¿Pueden caminar los elefantes? | Tell me, can elephants walk? | Dime, ¿pueden caminar los elefantes? | Question
❓ | Can tigers run? | ¿Pueden correr los tigres? | Tell me, can tigers run? | Dime, ¿pueden correr los tigres? | Question
❓ | Can bears swim? | ¿Pueden nadar los osos? | Tell me, can bears swim? | Dime, ¿pueden nadar los osos? | Question
❓ | Can kangaroos hop? | ¿Pueden saltar los canguros? | Tell me, can kangaroos hop? | Dime, ¿pueden saltar los canguros? | Question
❓ | Can penguins swim? | ¿Pueden nadar los pingüinos? | Tell me, can penguins swim? | Dime, ¿pueden nadar los pingüinos? | Question
❓ | Can snakes walk? | ¿Pueden caminar las serpientes? | Tell me, can snakes walk? | Dime, ¿pueden caminar las serpientes? | Question
❓ | Can giraffes run? | ¿Pueden correr las jirafas? | Tell me, can giraffes run? | Dime, ¿pueden correr las jirafas? | Question
❓ | Can lions run? | ¿Pueden correr los leones? | Tell me, can lions run? | Dime, ¿pueden correr los leones? | Question
❓ | Can zebras walk? | ¿Pueden caminar las cebras? | Tell me, can zebras walk? | Dime, ¿pueden caminar las cebras? | Question
❓ | Can turtles swim? | ¿Pueden nadar las tortugas? | Tell me, can turtles swim? | Dime, ¿pueden nadar las tortugas? | Question
❓ | Can frogs hop? | ¿Pueden saltar las ranas? | Tell me, can frogs hop? | Dime, ¿pueden saltar las ranas? | Question
❓ | Can spiders walk? | ¿Pueden caminar las arañas? | Tell me, can spiders walk? | Dime, ¿pueden caminar las arañas? | Question
❓ | Can ants run? | ¿Pueden correr las hormigas? | Tell me, can ants run? | Dime, ¿pueden correr las hormigas? | Question
    """.trimIndent(), "Basics 1 - List 6")

    private fun getList7(): List<Flashcard> = parseCards("""
💪 | Arm | Brazo | I have an arm | Tengo un brazo | Word
🖐️ | Hand | Mano | This is a hand | Esta es una mano | Word
☝️ | Finger | Dedo | I see a finger | Veo un dedo | Word
🦵 | Leg | Pierna | The leg is long | La pierna es larga | Word
🦶 | Foot | Pie | My foot is small | Mi pie es pequeño | Word
🦶 | Toe | Dedo del pie | I have a toe | Tengo un dedo del pie | Word
❓ | What's this? | ¿Qué es esto? | Tell me, what's this? | Dime, ¿qué es esto? | Question
💪 | This is my arm. | Este es mi brazo. | Look, this is my arm. | Mira, este es mi brazo. | Phrase
❓ | What are these? | ¿Qué son estos? | Tell me, what are these? | Dime, ¿qué son estos? | Question
💪 | These are my arms. | Estos son mis brazos. | Look, these are my arms. | Mira, estos son mis brazos. | Phrase
👁️ | Eye | Ojo | Open your eye | Abre tu ojo | Word
👃 | Nose | Nariz | This is a nose | Esta es una nariz | Word
👄 | Mouth | Boca | Open your mouth | Abre tu boca | Word
👂 | Ear | Oreja | I have an ear | Tengo una oreja | Word
❓ | Is this my eye? | ¿Es este mi ojo? | Tell me, is this my eye? | Dime, ¿es este mi ojo? | Question
✅ | Yes, it is. | Sí, lo es. | Yes, it is my eye. | Sí, es mi ojo. | Phrase
❌ | No, it isn't. | No, no lo es. | No, it isn't my eye. | No, no es mi ojo. | Phrase
❓ | Are these my eyes? | ¿Son estos mis ojos? | Tell me, are these my eyes? | Dime, ¿son estos mis ojos? | Question
✅ | Yes, they are. | Sí, sí son. | Yes, they are my eyes. | Sí, son mis ojos. | Phrase
❌ | No, they aren't. | No, no son. | No, they aren't my eyes. | No, no son mis ojos. | Phrase
🙋 | Excuse me. | Disculpe. | Excuse me, please. | Disculpe, por favor. | Phrase
👍 | Sure. | Claro. | Yes, sure. | Sí, claro. | Word
🙏 | Thank you. | Gracias. | Thank you very much. | Muchas gracias. | Phrase
🤝 | Be polite. | Sé educado. | Please, be polite. | Por favor, sé educado. | Phrase
🧼 | Wash my face | Lavar mi cara | I wash my face | Lavo mi cara | Phrase
👐 | Wash my hands | Lavar mis manos | I wash my hands | Lavo mis manos | Phrase
🪮 | Brush my hair | Cepillar mi cabello | I brush my hair | Cepillo mi cabello | Phrase
🪥 | Brush my teeth | Cepillar mis dientes | I brush my teeth | Cepillo mis dientes | Phrase
🧼 | I can wash my face. | Puedo lavar mi cara. | Look, I can wash my face. | Mira, puedo lavar mi cara. | Phrase
🖐️ | This is my hand. | Esta es mi mano. | Look, this is my hand. | Mira, esta es mi mano. | Phrase
☝️ | This is my finger. | Este es mi dedo. | Look, this is my finger. | Mira, este es mi dedo. | Phrase
🦵 | This is my leg. | Esta es mi pierna. | Look, this is my leg. | Mira, esta es mi pierna. | Phrase
🦶 | This is my foot. | Este es mi pie. | Look, this is my foot. | Mira, este es mi pie. | Phrase
🦶 | This is my toe. | Este es mi dedo del pie. | Look, this is my toe. | Mira, este es mi dedo del pie. | Phrase
👁️ | This is my eye. | Este es mi ojo. | Look, this is my eye. | Mira, este es mi ojo. | Phrase
👃 | This is my nose. | Esta es mi nariz. | Look, this is my nose. | Mira, esta es mi nariz. | Phrase
👄 | This is my mouth. | Esta es mi boca. | Look, this is my mouth. | Mira, esta es mi boca. | Phrase
👂 | This is my ear. | Esta es mi oreja. | Look, this is my ear. | Mira, esta es mi oreja. | Phrase
🖐️ | These are my hands. | Estas son mis manos. | Look, these are my hands. | Mira, estas son mis manos. | Phrase
☝️ | These are my fingers. | Estos son mis dedos. | Look, these are my fingers. | Mira, estos son mis dedos. | Phrase
🦵 | These are my legs. | Estas son mis piernas. | Look, these are my legs. | Mira, estas son mis piernas. | Phrase
🦶 | These are my feet. | Estos son mis pies. | Look, these are my feet. | Mira, estos son mis pies. | Phrase
🦶 | These are my toes. | Estos son mis dedos del pie. | Look, these are my toes. | Mira, estos son mis dedos del pie. | Phrase
👂 | These are my ears. | Estas son mis orejas. | Look, these are my ears. | Mira, estas son mis orejas. | Phrase
❓ | Is this my arm? | ¿Es este mi brazo? | Tell me, is this my arm? | Dime, ¿es este mi brazo? | Question
❓ | Is this my hand? | ¿Es esta mi mano? | Tell me, is this my hand? | Dime, ¿es esta mi mano? | Question
❓ | Is this my finger? | ¿Es este mi dedo? | Tell me, is this my finger? | Dime, ¿es este mi dedo? | Question
❓ | Is this my leg? | ¿Es esta mi pierna? | Tell me, is this my leg? | Dime, ¿es esta mi pierna? | Question
❓ | Is this my foot? | ¿Es este mi pie? | Tell me, is this my foot? | Dime, ¿es este mi pie? | Question
❓ | Is this my toe? | ¿Es este mi dedo del pie? | Tell me, is this my toe? | Dime, ¿es este mi dedo del pie? | Question
❓ | Is this my nose? | ¿Es esta mi nariz? | Tell me, is this my nose? | Dime, ¿es esta mi nariz? | Question
❓ | Is this my mouth? | ¿Es esta mi boca? | Tell me, is this my mouth? | Dime, ¿es esta mi boca? | Question
❓ | Is this my ear? | ¿Es esta mi oreja? | Tell me, is this my ear? | Dime, ¿es esta mi oreja? | Question
❓ | Are these my arms? | ¿Son estos mis brazos? | Tell me, are these my arms? | Dime, ¿son estos mis brazos? | Question
❓ | Are these my hands? | ¿Son estas mis manos? | Tell me, are these my hands? | Dime, ¿son estas mis manos? | Question
❓ | Are these my fingers? | ¿Son estos mis dedos? | Tell me, are these my fingers? | Dime, ¿son estos mis dedos? | Question
❓ | Are these my legs? | ¿Son estas mis piernas? | Tell me, are these my legs? | Dime, ¿son estas mis piernas? | Question
❓ | Are these my feet? | ¿Son estos mis pies? | Tell me, are these my feet? | Dime, ¿son estos mis pies? | Question
❓ | Are these my toes? | ¿Son estos mis dedos del pie? | Tell me, are these my toes? | Dime, ¿son estos mis dedos del pie? | Question
❓ | Are these my ears? | ¿Son estas mis orejas? | Tell me, are these my ears? | Dime, ¿son estas mis orejas? | Question
👐 | I can wash my hands. | Puedo lavar mis manos. | Look, I can wash my hands. | Mira, puedo lavar mis manos. | Phrase
🪮 | I can brush my hair. | Puedo cepillar mi cabello. | Look, I can brush my hair. | Mira, puedo cepillar mi cabello. | Phrase
🪥 | I can brush my teeth. | Puedo cepillar mis dientes. | Look, I can brush my teeth. | Mira, puedo cepillar mis dientes. | Phrase
📖 | This is my book. | Este es mi libro. | Look, this is my book. | Mira, este es mi libro. | Phrase
📖 | These are my books. | Estos son mis libros. | Look, these are my books. | Mira, estos son mis libros. | Phrase
🖊️ | This is my pen. | Este es mi bolígrafo. | Look, this is my pen. | Mira, este es mi bolígrafo. | Phrase
🖊️ | These are my pens. | Estos son mis bolígrafos. | Look, these are my pens. | Mira, estos son mis bolígrafos. | Phrase
🚗 | This is my car. | Este es mi carro. | Look, this is my car. | Mira, este es mi carro. | Phrase
🚗 | These are my cars. | Estos son mis carros. | Look, these are my cars. | Mira, estos son mis carros. | Phrase
🪆 | This is my doll. | Esta es mi muñeca. | Look, this is my doll. | Mira, esta es mi muñeca. | Phrase
🪆 | These are my dolls. | Estas son mis muñecas. | Look, these are my dolls. | Mira, estas son mis muñecas. | Phrase
📖 | This is his book. | Este es su libro (de él - se refiere a él). | Look, this is his book. | Mira, este es su libro (de él). | Phrase
📖 | This is her book. | Este es su libro (de ella - se refiere a ella). | Look, this is her book. | Mira, este es su libro (de ella). | Phrase
🖊️ | This is his pen. | Este es su bolígrafo (de él - se refiere a él). | Look, this is his pen. | Mira, este es su bolígrafo (de él). | Phrase
🖊️ | This is her pen. | Este es su bolígrafo (de ella - se refiere a ella). | Look, this is her pen. | Mira, este es su bolígrafo (de ella). | Phrase
🚗 | This is his car. | Este es su carro (de él - se refiere a él). | Look, this is his car. | Mira, este es su carro (de él). | Phrase
🪆 | This is her doll. | Esta es su muñeca (de ella - se refiere a ella). | Look, this is her doll. | Mira, esta es su muñeca (de ella). | Phrase
📖 | These are his books. | Estos son sus libros (de él - se refiere a él). | Look, these are his books. | Mira, estos son sus libros (de él). | Phrase
📖 | These are her books. | Estos son sus libros (de ella - se refiere a ella). | Look, these are her books. | Mira, estos son sus libros (de ella). | Phrase
🖊️ | These are his pens. | Estos son sus bolígrafos (de él - se refiere a él). | Look, these are his pens. | Mira, estos son sus bolígrafos (de él). | Phrase
🖊️ | These are her pens. | Estos son sus bolígrafos (de ella - se refiere a ella). | Look, these are her pens. | Mira, estos son sus bolígrafos (de ella). | Phrase
    """.trimIndent(), "Basics 1 - List 7")

    private fun getList8(): List<Flashcard> = parseCards("""
🕰️ | Old | Viejo | It is an old book | Es un libro viejo | Word
✨ | New | Nuevo | It is a new car | Es un carro nuevo | Word
🐘 | Big | Grande | The elephant is big | El elefante es grande | Word
🐜 | Small | Pequeño | The ant is small | La hormiga es pequeña | Word
📏 | Long | Largo | The ruler is long | La regla es larga | Word
🖍️ | Short | Corto | The pencil is short | El lápiz es corto | Word
❓ | What's that? | ¿Qué es eso? | Tell me, what's that? | Dime, ¿qué es eso? | Question
🪆 | That's an old doll. | Esa es una muñeca vieja. | Look, that's an old doll. | Mira, esa es una muñeca vieja. | Phrase
❓ | What are those? | ¿Qué son esos? | Tell me, what are those? | Dime, ¿qué son esos? | Question
🚲 | Those are new bikes. | Esas son bicicletas nuevas. | Look, those are new bikes. | Mira, esas son bicicletas nuevas. | Phrase
🏎️ | Fast | Rápido | The car is fast | El carro es rápido | Word
🐢 | Slow | Lento | The turtle is slow | La tortuga es lenta | Word
🔊 | Noisy | Ruidoso | The bus is noisy | El autobús es ruidoso | Word
🤫 | Quiet | Silencioso | The library is quiet | La biblioteca es silenciosa | Word
❓ | Is that a fast car? | ¿Es ese un carro rápido? | Tell me, is that a fast car? | Dime, ¿es ese un carro rápido? | Question
✅ | Yes, it is. | Sí, lo es. | Yes, it is fast. | Sí, es rápido. | Phrase
❌ | No, it isn't. | No, no lo es. | No, it isn't fast. | No, no es rápido. | Phrase
❓ | Are those fast cars? | ¿Son esos carros rápidos? | Tell me, are those fast cars? | Dime, ¿son esos carros rápidos? | Question
✅ | Yes, they are. | Sí, sí son. | Yes, they are fast. | Sí, sí son rápidos. | Phrase
❌ | No, they aren't. | No, no son. | No, they aren't fast. | No, no son rápidos. | Phrase
🤫 | Please be quiet. | Por favor, guarda silencio. | Please be quiet now. | Por favor, guarda silencio ahora. | Phrase
😔 | OK. I'm sorry. | Está bien. Lo siento. | OK. I'm sorry. | Está bien. Lo siento. | Phrase
🙏 | Thanks. | Gracias. | Thanks a lot. | Muchas gracias. | Phrase
😇 | Be nice. | Sé amable. | Please, be nice. | Por favor, sé amable. | Phrase
🚌 | Bus | Autobús | I take the bus | Tomo el autobús | Word
🚚 | Truck | Camión | The truck is big | El camión es grande | Word
🚆 | Train | Tren | The train is long | El tren es largo | Word
⛵ | Boat | Barco | The boat is slow | El barco es lento | Word
❓ | What's this? | ¿Qué es esto? | Tell me, what's this? | Dime, ¿qué es esto? | Question
🚌 | It's an old bus. | Es un autobús viejo. | Look, it's an old bus. | Mira, es un autobús viejo. | Phrase
❓ | What are these? | ¿Qué son estos? | Tell me, what are these? | Dime, ¿qué son estos? | Question
🚌 | They're new buses. | Son autobuses nuevos. | Look, they're new buses. | Mira, son autobuses nuevos. | Phrase
📖 | That's a new book. | Ese es un libro nuevo. | Look, that's a new book. | Mira, ese es un libro nuevo. | Phrase
🖊️ | That's an old pen. | Ese es un bolígrafo viejo. | Look, that's an old pen. | Mira, ese es un bolígrafo viejo. | Phrase
🎒 | That's a big backpack. | Esa es una mochila grande. | Look, that's a big backpack. | Mira, esa es una mochila grande. | Phrase
🧽 | That's a small eraser. | Ese es un borrador pequeño. | Look, that's a small eraser. | Mira, ese es un borrador pequeño. | Phrase
📏 | That's a long ruler. | Esa es una regla larga. | Look, that's a long ruler. | Mira, esa es una regla larga. | Phrase
✏️ | That's a short pencil. | Ese es un lápiz corto. | Look, that's a short pencil. | Mira, ese es un lápiz corto. | Phrase
🚆 | That's a fast train. | Ese es un tren rápido. | Look, that's a fast train. | Mira, ese es un tren rápido. | Phrase
⛵ | That's a slow boat. | Ese es un barco lento. | Look, that's a slow boat. | Mira, ese es un barco lento. | Phrase
🚚 | That's a noisy truck. | Ese es un camión ruidoso. | Look, that's a noisy truck. | Mira, ese es un camión ruidoso. | Phrase
🚗 | That's a quiet car. | Ese es un carro silencioso. | Look, that's a quiet car. | Mira, ese es un carro silencioso. | Phrase
🪑 | Those are old desks. | Esos son escritorios viejos. | Look, those are old desks. | Mira, esos son escritorios viejos. | Phrase
🪑 | Those are new chairs. | Esas son sillas nuevas. | Look, those are new chairs. | Mira, esas son sillas nuevas. | Phrase
⚽ | Those are big balls. | Esas son pelotas grandes. | Look, those are big balls. | Mira, esas son pelotas grandes. | Phrase
🪆 | Those are small dolls. | Esas son muñecas pequeñas. | Look, those are small dolls. | Mira, esas son muñecas pequeñas. | Phrase
🚆 | Those are long trains. | Esos son trenes largos. | Look, those are long trains. | Mira, esos son trenes largos. | Phrase
✏️ | Those are short pencils. | Esos son lápices cortos. | Look, those are short pencils. | Mira, esos son lápices cortos. | Phrase
🚚 | Those are fast trucks. | Esos son camiones rápidos. | Look, those are fast trucks. | Mira, esos son camiones rápidos. | Phrase
🚌 | Those are slow buses. | Esos son autobuses lentos. | Look, those are slow buses. | Mira, esos son autobuses lentos. | Phrase
🐒 | Those are noisy monkeys. | Esos son monos ruidosos. | Look, those are noisy monkeys. | Mira, esos son monos ruidosos. | Phrase
🐢 | Those are quiet turtles. | Esas son tortugas silenciosas. | Look, those are quiet turtles. | Mira, esas son tortugas silenciosas. | Phrase
❓ | Is that a new bike? | ¿Es esa una bicicleta nueva? | Tell me, is that a new bike? | Dime, ¿es esa una bicicleta nueva? | Question
❓ | Is that a fast train? | ¿Es ese un tren rápido? | Tell me, is that a fast train? | Dime, ¿es ese un tren rápido? | Question
❓ | Is that an old bus? | ¿Es ese un autobús viejo? | Tell me, is that an old bus? | Dime, ¿es ese un autobús viejo? | Question
❓ | Is that a noisy truck? | ¿Es ese un camión ruidoso? | Tell me, is that a noisy truck? | Dime, ¿es ese un camión ruidoso? | Question
❓ | Is that a slow boat? | ¿Es ese un barco lento? | Tell me, is that a slow boat? | Dime, ¿es ese un barco lento? | Question
❓ | Is that a big elephant? | ¿Es ese un elefante grande? | Tell me, is that a big elephant? | Dime, ¿es ese un elefante grande? | Question
❓ | Is that a small frog? | ¿Es esa una rana pequeña? | Tell me, is that a small frog? | Dime, ¿es esa una rana pequeña? | Question
❓ | Are those new bikes? | ¿Son esas bicicletas nuevas? | Tell me, are those new bikes? | Dime, ¿son esas bicicletas nuevas? | Question
❓ | Are those fast trains? | ¿Son esos trenes rápidos? | Tell me, are those fast trains? | Dime, ¿son esos trenes rápidos? | Question
❓ | Are those old buses? | ¿Son esos autobuses viejos? | Tell me, are those old buses? | Dime, ¿son esos autobuses viejos? | Question
❓ | Are those noisy trucks? | ¿Son esos camiones ruidosos? | Tell me, are those noisy trucks? | Dime, ¿son esos camiones ruidosos? | Question
❓ | Are those slow boats? | ¿Son esos barcos lentos? | Tell me, are those slow boats? | Dime, ¿son esos barcos lentos? | Question
❓ | Are those big elephants? | ¿Son esos elefantes grandes? | Tell me, are those big elephants? | Dime, ¿son esos elefantes grandes? | Question
❓ | Are those small frogs? | ¿Son esas ranas pequeñas? | Tell me, are those small frogs? | Dime, ¿son esas ranas pequeñas? | Question
🚗 | It's a new car. | Es un carro nuevo. | Look, it's a new car. | Mira, es un carro nuevo. | Phrase
🏞️ | It's a long river. | Es un río largo. | Look, it's a long river. | Mira, es un río largo. | Phrase
🪨 | It's a small rock. | Es una roca pequeña. | Look, it's a small rock. | Mira, es una roca pequeña. | Phrase
🌊 | It's a big lake. | Es un lago grande. | Look, it's a big lake. | Mira, es un lago grande. | Phrase
🚗 | They're new cars. | Son carros nuevos. | Look, they're new cars. | Mira, son carros nuevos. | Phrase
🏞️ | They're long rivers. | Son ríos largos. | Look, they're long rivers. | Mira, son ríos largos. | Phrase
🪨 | They're small rocks. | Son rocas pequeñas. | Look, they're small rocks. | Mira, son rocas pequeñas. | Phrase
🌊 | They're big lakes. | Son lagos grandes. | Look, they're big lakes. | Mira, son lagos grandes. | Phrase
    """.trimIndent(), "Basics 1 - List 8")
}
