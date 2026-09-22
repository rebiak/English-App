package com.example.data.seed

import com.example.data.model.Flashcard

object TranslationsSentences1 {
    const val FOLDER_NAME = "Translations Sentences 1"
    val categoryNames: List<String> = (1..30).map { "Translations 1 - List $it" }

    fun getAllCards(): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 1", list1))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 2", list2))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 3", list3))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 4", list4))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 5", list5))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 6", list6))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 7", list7))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 8", list8))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 9", list9))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 10", list10))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 11", list11))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 12", list12))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 13", list13))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 14", list14))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 15", list15))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 16", list16))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 17", list17))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 18", list18))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 19", list19))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 20", list20))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 21", list21))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 22", list22))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 23", list23))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 24", list24))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 25", list25))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 26", list26))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 27", list27))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 28", list28))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 29", list29))
        list.addAll(TranslationDataHelper.parseList("Translations 1 - List 30", list30))
        return list
    }

    private const val list1 = """
🪑 | It's on the table. | Está en la mesa.
🧑‍🏫 | He's a teacher. | Él es profesor.
🦒 | She's tall. | Ella es alta.
🤷 | I don't know. | No sé.
📍 | It's not here. | No está aquí.
🇪🇸 | I'm from Spain. | Soy de España.
🌍 | Where are you from? | ¿De dónde eres?
📍 | Where are you? | ¿Dónde estás?
👤 | Who are you? | ¿Quién eres?
❓ | What's this? | ¿Qué es esto?
🎁 | That's not for me. | Ese no es para mí.
📖 | This isn't my book. | Este no es mi libro.
🧑‍🤝‍🧑 | He's not my brother. | Él no es mi hermano.
🧑‍🤝‍🧑 | Is he with his sister? | ¿Está él con su hermana?
✏️ | The pencil is under the table. | El lápiz está debajo de la mesa.
📍 | Where is it? | ¿Dónde está?
📍 | It's not there. | No está allí.
🇬🇧 | He's English. | Él es inglés.
🇬🇧 | He's from England. | Es de Inglaterra.
🌍 | Where's she from? | ¿De dónde es ella?
🇺🇸 | She's from the United States. | Ella es de Estados Unidos.
🏷️ | What's your name? | ¿Cuál es tu nombre?
🖊️ | Is this your pen? | ¿Es este tu bolígrafo?
🖊️ | My pen is red, not blue. | Mi bolígrafo es rojo, no azul.
👉 | He's in front of me. | Él está delante de mí.
👈 | Is she behind you? | ¿Está ella detrás de ti?
📞 | That's my only telephone. | Aquel es mi único teléfono.
🪑 | What's on the table? | ¿Que es lo que está en la mesa?
👥 | Who's with him? | ¿Quien está con el?
👩 | Who's she? | ¿Quien es ella?
👋 | How are you? | ¿Cómo está Ud.?
👋 | How are you doing? | ¿Que tal?
🙅 | That's not for her. | Aquel no es para ella.
📦 | What's in the box? | ¿Que es lo que está en la caja?
💭 | What's your opinion? | ¿Cuál es tu opinión?
📏 | You're very short. | Eres muy bajo.
🗼 | Where's Paris? | ¿Dónde está París?
😊 | I'm not happy. | No estoy contento
🏁 | Are you ready? | ¿Estás listo?
⭐ | It's very important. | Es muy importante.
✨ | This isn't interesting. | Esto no es interesante.
📋 | He's not on my list. | Él no está en mi lista.
😊 | She's very nice / pleasant. | Ella es muy agradable.
📅 | What day is today? | ¿Que día es hoy?
👋 | How is he (doing)? | ¿Cómo está el?
🗣️ | Is that your answer? | ¿Es esa tu respuesta?
🚪 | Is it next to the door? | ¿Está al lado de la puerta?
📍 | Where is it then? | ¿Dónde está entonces?
👥 | I'm the only person here. | Soy la única persona aquí.
👩‍💼 | She's not my secretary. | Ella no es mi secretaria.
"""

    private const val list2 = """
🤷 | I don't know. | No sé.
📍 | Where is it? | ¿Dónde está?
📍 | Where is she? | ¿Dónde está ella?
❓ | What's that? | ¿Qué es aquello?
🪟 | Where's the window? | ¿Dónde está la ventana?
🐕 | Is that your dog? | ¿Es ese tu perro?
👈 | I'm behind you. | Estoy detrás de ti.
👌 | It's easy. | Es fácil.
📍 | Is he here? | ¿Está él aquí?
🧗 | It's not difficult. | No es difícil.
🌍 | Where's he from? | ¿De dónde es él?
🏙️ | Is he from my city? | ¿Es de mi ciudad?
🏙️ | He's from a small town. | Es de un pueblo pequeño.
🐘 | He's not from a big city. | No es de una ciudad grande.
👉 | She's in front of me. | Ella está delante de mí.
❓ | What's this? | ¿Qué es esto?
🎨 | What colour is it? | ¿De qué color es?
🎁 | Is it for me? | ¿Es para mí?
👤 | Who's there? | ¿Quién está allí?
👤 | Who's that person? | ¿Quién es esa persona?
👉 | Who's in front of her? | ¿Quién está delante de ella?
🧩 | It's my problem. | Es mi problema.
🧩 | That's your problem. | Ese es tu problema.
🔑 | This is the solution. | Esta es la solución.
🚗 | It's a blue car. | Es un coche azul.
🏠 | It's a big house. | Es una casa grande.
🧗 | It's a difficult lesson. | Es una lección difícil.
📊 | This is an important report. | Este es un informe importante.
📞 | That's a green telephone. | Aquello es un teléfono verde.
🚗 | Where's the red car? | ¿Dónde está el coche rojo?
🚗 | What's in the car? | ¿Qué es lo que está en el coche?
🗣️ | Is this your answer? | ¿Es ésta tu respuesta?
❓ | What's your question? | ¿Cuál es tu pregunta?
👌 | The question is very easy. | La pregunta es muy fácil.
🧑‍🏫 | Who's the teacher? | ¿Quién es el profesor?
🧑‍🎓 | Are you the student? | ¿Eres tú el alumno?
📓 | Where's your notebook? | ¿Dónde está tu cuaderno?
🏷️ | Your name's not on this list. | Tu nombre no está en esta lista.
📍 | Where is it then?. | ¿Dónde está entonces?
🤷 | I don't know. | No lo sé.
👋 | How are you doing? | ¿Qué tal estás?
✅ | Fine, thank you. | Bien, gracias.
🏛️ | You're the president. | Ud.es el presidente.
🛏️ | He's in that room. | Él está en aquella habitación.
✉️ | Where's the letter? | ¿Dónde está la carta?
🪵 | It's on the floor. | Está en el suelo.
🧱 | It's not on the wall. | No está en la pared.
❓ | What is it? | ¿Qué es?
📻 | Is it a radio? | ¿Es una radio?
📍 | Where is it? | ¿Dónde está?
"""

    private const val list3 = """
🤝 | She's with me. | Ella está conmigo.
🧱 | The calendar's not on the wall. | El calendario no está en la pared.
📍 | Where is it then? | ¿Dónde está entonces?
📍 | It's over there. | Está allí.
🏷️ | What's your name? | ¿Cuál es tu nombre?
🏷️ | What's his name? | ¿Cuál es su nombre? (de él)
🏷️ | What's my last name / surname? | ¿Cuál es mi apellido?
📍 | What's her address? | ¿Cuál es su dirección?(de ella)
🏠 | His house is very big. | Su casa es muy grande (de él)
🧑‍🤝‍🧑 | Her brother is very tall. | Su hermano es muy alto (de ella)
👨 | Your father is very young. | Tu padre es muy joven.
🎯 | This is his last chance. | Esta es su última oportunidad (de él)
👩 | How's her mother? | ¿Cómo está su madre?(de ella)
🚗 | Where's his car? | ¿Dónde está su coche?(de él)
🇬🇧 | The small plane is English. | El avión pequeño es inglés.
🇫🇷 | The tall man is French. | El hombre alto es francés.
🇪🇸 | His wife is Spanish. | Su mujer es española.
🇪🇸 | She's from Madrid. | Ella es de Madrid.
🌍 | Where's he from? | ¿De dónde es él?
🗼 | He's from Paris. | Es de París.
🏠 | Where's his house? | ¿Dónde está su casa? (de él)
🗼 | It's near Paris. | Está cerca de Paris.
🇫🇷 | Is his wife happy in France? | ¿Está su mujer contenta en Francia?
📌 | I think so. | Creo que sí.
🤷 | I don't know. | No lo sé.
🗼 | Is Paris big? | ¿Es grande París?
📍 | Is it far from here? | ¿Está lejos de aquí?
✨ | Is his job interesting? | ¿Es interesante su trabajo? (de él)
🧗 | Is it difficult? | ¿Es difícil?
💼 | Is it an important job? | ¿Es un trabajo importante?
🏢 | Is his company important? | ¿Es importante su compañía?
🐘 | Is it big? | ¿Es grande?
📍 | Where is it? | ¿Dónde está?
🏠 | Is it near his house? | ¿Está cerca de su casa? (de él)
📖 | This is a book. | Esto es un libro.
📖 | It's not my book. | No es mi libro.
💎 | It's very expensive. | Es muy caro.
🌊 | The Rhine is a long river. | El Rin es un río largo.
📏 | The Miño is a short river. | El Miño es un río corto.
📊 | That's a short report. | Aquél es un informe corto.
😊 | I'm happy with the result. | Estoy contento con el resultado.
👥 | Is she with you? | ¿Está ella contigo?
👤 | Who's she with? | ¿Con quién está?
🌍 | Where's she from? | ¿De dónde es?
🧑‍💼 | I'm not your boss. | No soy tu jefe.
👩 | She's not his wife. | Ella no es su mujer (de él)
👨 | You're not her husband. | No eres su marido.
🧑‍🤝‍🧑 | He's not my brother. | Él no es mi hermano.
📍 | This isn't my address. | Esta no es mi dirección.
📋 | My address isn't on your list. | Mi dirección no está en tu lista.
"""

    private const val list4 = """
👨 | That man is good. | Ese hombre es bueno.
👨 | He's a good man. | Es un buen hombre.
🐕 | That dog is bad. | Ese perro es malo.
🐕 | It's a bad dog. | Es un perro malo.
✈️ | It's an old plane. | Es un avión viejo.
👧 | She's a young girl. | Ella es una chica joven.
👌 | This is an easy game. | Este es un juego fácil.
🧗 | I'm a difficult person. | Soy una persona difícil.
👔 | He's an important director. | Él es un director importante.
📞 | The telephone is heavy. | El teléfono es pesado.
✨ | The story is interesting. | La historia es interesante.
📰 | The magazine is boring. | La revista es aburrida.
🎬 | The movie's not long. | La película no es larga.
📰 | That's not her newspaper. | Ese no es su periódico (de ella)
❓ | What's that? | ¿Qué es eso?
🐕 | Where's the small dog? | ¿Dónde está el perro pequeño?
❓ | That's a good question. | Esa es una buena pregunta.
🎁 | Is it for you? | ¿Es para ti?
👈 | Is this the last page? | ¿Es ésta la última página?
🐕 | Which dog is big, this one or that one? | ¿Qué perro es grande, éste o aquél?
👤 | Who's in that truck? | ¿Quién está en ese camión?
📖 | Is the book in your pocket? | ¿Está el libro en tu bolsillo?
🗣️ | That's not the right answer. | Esa no es la respuesta correcta.
🧗 | This is a difficult game. | Este es un juego difícil.
👥 | I'm with you. | Estoy contigo.
📖 | Which book is easy, this one or that one? | ¿Qué libro es fácil, éste o aquél?
⚖️ | Is that your final decision? | ¿Es ésa tu decisión final?
👋 | How do you do? | ¿Cómo está Ud.?
🚫 | This isn't the only alternative. | Esta no es la única alternativa.
👤 | Who's there? | ¿Quién está allí?
🧑‍🏫 | Who's my teacher? | ¿Quién es mi profesor?
📌 | It's for her. | Es para ella.
📌 | It's for him. | Es para él.
👤 | Who's this for? | ¿Para quién es esto?
👤 | Who's it for? | ¿Para quién es?
🎁 | Is that for me? | ¿Es aquello para mí?
👥 | He's an important person. | Él es una persona importante.
🇬🇧 | He's from England. | Él es de Inglaterra.
⏱️ | He's here now. | Él está aquí ahora.
🧑‍🤝‍🧑 | He's with your sister. | Él está con tu hermana.
📌 | This is for him. | Esto es para él.
🏷️ | What's her name? | ¿Cuál es su nombre? (de ella)
📍 | What's her address? | ¿Cuál es su dirección? (de ella)
🧑‍🤝‍🧑 | Who's her brother? | ¿Quién es su hermano? (de ella)
👤 | Who's with her now? | ¿Quién está con ella ahora?
📍 | Where is she now? | ¿Dónde está ella ahora?
👨 | How's her father? | ¿Cómo está su padre? (de ella)
⭐ | Your problem isn't important. | Tu problema no es importante.
🌍 | Where's he from? | ¿De dónde es él?
✉️ | Who's this letter for? | ¿Para quién es esta carta?
"""

    private const val list5 = """
📍 | They're here. | Están aquí.
📍 | Where are they? | ¿Dónde están?
🪵 | Are they on the floor? | ¿Están en el suelo?
🎁 | Are they for you or for me? | ¿Son para ti o para mí?
👥 | We're with them. | Estamos con ellos.
👤 | Who are they? | ¿Quiénes son?
😊 | We're not happy. | No estamos contentos
🩹 | They're not my tapes. | No son mis cintas.
🌸 | Those flowers aren't pretty. | Esas flores no son bonitas.
👔 | His shirts aren't clean. | Sus camisas no están limpias ( de él)
🚗 | The red cars are behind me. | Los coches rojos están detrás de mí.
👁️ | Her eyes are blue. | Sus ojos (de ella) son azules.
📍 | Where are the children? | ¿Dónde están los niños?
🏠 | Are they at home? | ¿Están en casa?
🏫 | Are they at school? | ¿Están en la escuela?
📍 | Where are they? | ¿Dónde están?
🤷 | I don't know. | No lo sé.
👋 | How are they? | ¿Cómo están?
🧩 | What are the problems? | ¿Cuáles son los problemas?
🧩 | What's your problem? | ¿Cuál es tu problema?
📓 | Those are your notebooks. | Aquellos son tus cuadernos.
💎 | These lamps are expensive. | Estas lámparas son caras.
📝 | My grades / marks are good. | Mis notas son buenas.
🐘 | His ears are big. | Sus orejas son grandes ( de él)
🧒 | Her children are tall. | Sus hijos son altos (de ella)
🧒 | These numbers are wrong. | Estos números son erróneos.
🗣️ | Your answers are right / correct. | Tus respuestas son correctas.
👤 | Who are those people? | ¿Quiénes son aquellas personas?
🧗 | Are these exercises difficult? | ¿Son difíciles estos ejercicios?
🧒 | What are these? | ¿Qué son estos?
🧒 | What are they? | ¿Qué son?
🇺🇸 | We're from the U.S. | Somos de USA.
🌍 | Where are you from? | ¿De dónde sois?
🌍 | Where are they from? | ¿De dónde son?
👉 | Are we in front of them? | ¿Estamos delante de ellos?
↔️ | They're next to us. | Ellas están al lado de nosotros.
🪟 | The windows are open. | Las ventanas están abiertas.
⏱️ | The days are long now. | Los días son largos ahora.
👉 | Those are the new machines. | Aquellas son las máquinas nuevas.
📋 | What are your plans? | ¿Cuáles son tus planes?
👌 | Are they easy or difficult? | ¿Son fáciles o difíciles?
📍 | Where are our names? | ¿Dónde están nuestros nombres?
🤝 | We're friends. | Somos amigos.
🧒 | They're good. | Son buenos.
💎 | These are expensive. | Estos son caros.
💎 | Are those expensive too? | ¿Son caros aquellos también?
🍎 | Those apples are bad. | Esas manzanas son malas.
🚗 | Our cars aren't small. | Nuestros coches no son pequeños.
✅ | The answers are correct. | Las respuestas están bien.
📌 | You're crazy. | Estáis locos.
"""

    private const val list6 = """
🧒 | Those envelopes are mine. | Aquellos sobres son míos.
👈 | This picture is yours. | Este cuadro es tuyo.
🧒 | These pears are hers. | Estas peras son de ella.
📍 | Where are his? | ¿Dónde están los de él?
🏠 | Their house is near here. | Su casa (de ellos) está cerca de aquí.
🧩 | Their problems aren't mine. | Sus problemas (de ellos) no son míos.
👉 | What are their names? | ¿Cuáles son sus nombres?
✉️ | Where are her letters? | ¿Dónde están sus cartas? (de ella)
📖 | Are these their books? | ¿Son estos sus libros? (de ellas)
📍 | Where are his parents? | ¿Dónde están sus padres? (de él)
👤 | Who are their parents? | ¿Quiénes son sus padres? ( de ellos)
🧒 | Are these yours? | ¿Son tuyos éstos?
🧒 | Whose are they? | ¿De quién son?
🧒 | They're not mine. | No son míos.
🧒 | Are they ours? | ¿Son nuestros?
🤷 | I don't know. | No sé.
📍 | Where are his? | ¿Dónde están los de él?
🤝 | They're with me. | Están conmigo.
📍 | Where are ours? | ¿Dónde están los nuestros?
📍 | They're over there. | Están allí.
🧒 | Whose pictures are those? | ¿De quién son esas fotos?
🧒 | Are they yours? | ¿Son vuestras?
🧒 | Whose are they then? | ¿De quién son entonces?
🧒 | They're mine. | Son mías.
📍 | Where are their cards? | ¿Dónde están sus carnets? (de ellos)
💵 | These are their coins. | Estas son sus monedas(de ellas)
📍 | He's here. | Él está aquí.
📏 | They're very short. | Son muy cortos.
👨 | My father is very tall. | Mi padre es muy alto.
🦒 | Mine isn't tall. | El mío no es alto.
🧒 | My children are young. | Mis hijos son jóvenes.
🧒 | Mine are young too. | Los míos son jóvenes también.
🧒 | His aren't young. | Los de él no son jóvenes.
🏠 | They're at home now. | Están en casa ahora.
🏠 | Where's their house? | ¿Dónde está su casa? (de ellos)
📍 | It's far from here. | Está lejos de aquí.
🏠 | It's a big house. | Es una casa grande.
🐘 | Hers is big too. | La de ella es grande también.
🔬 | Ours is very small. | La nuestra es muy pequeña.
🏠 | Is your house big? | ¿Es grande vuestra casa?
🚫 | No, it's not. | No, no lo es.
🎁 | They're for me. | Son para mí.
📌 | It's for us. | Es para nosotros.
👥 | We're with them. | Estamos con ellos.
👥 | They're not with you. | No están contigo.
↔️ | She's next to him. | Ella está al lado de él.
📌 | This is for her. | Esto es para ella.
👥 | I'm with them. | Estoy con ellos.
🤝 | They're with me. | Están conmigo.
🐎 | The horses over there aren't white. | Los caballos allí no son blancos.
"""

    private const val list7 = """
🧩 | This is your problem, not mine. | Este es tu problema, no el mío.
👤 | Whose envelope is this? | ¿De quién es este sobre?
📍 | Where are they? | ¿Dónde están?
📍 | Where is it? | ¿Dónde está?
❓ | What's this? | ¿Qué es esto?
🧒 | What are these? | ¿Qué son estos?
❓ | What is it? | ¿Qué es?
🤷 | I don't know. | No lo sé.
👉 | What are their names? | ¿Cuáles son sus nombres?
📍 | Where are his parents? | ¿Dónde están sus padres? (de él)
👨 | Where's their father? | ¿Dónde está su padre? (de ellos)
↔️ | Mine is next to hers. | El mío está al lado del de ella.
📍 | Where are ours? | ¿Dónde están los nuestros?
🧒 | Are these magazines his? | ¿Son de él estas revistas?
🚢 | The ship's in the port. | El barco está en el puerto.
🛡️ | Are you sure? | ¿Estás seguro?
🛡️ | No, I'm not sure. | No, no estoy seguro.
👨 | Her father's my brother. | Su padre (de ella) es mi hermano.
🧒 | Whose lighters are these? | ¿De quién son estos mecheros?
🧒 | They're hers. | Son de ella.
🎯 | This is his only chance. | Esta es su única oportunidad (de él)
🧒 | These are theirs, not his. | Estos son de ellos, no de él.
🧩 | Those aren't the only problems. | Aquellos no son los únicos problemas.
🧑‍⚕️ | He's a doctor. | Él es médico.
❓ | When's the party? | ¿Cuándo es la fiesta?
👋 | How are they doing? | ¿Qué tal están?
🍎 | It's an apple. | Es una manzana.
📌 | He's an artist. | Él es artista.
🎓 | The university's near here. | La universidad está cerca de aquí.
🧒 | They're lawyers. | Son abogados.
👌 | Their job is easy. | Su trabajo es fácil(de ellos)
🧒 | The days are long in the summer. | Los días son largos en verano.
🌸 | She's a very pretty actress. | Ella es una actriz muy guapa.
🎭 | He's a very handsome actor. | Él es un actor muy guapo.
🌸 | It's a very beautiful city. | Es una ciudad muy hermosa.
✨ | It's a very interesting brochure. | Es un folleto muy interesante.
📊 | They're very important reports. | Son informes muy importantes.
📝 | This is a very boring class. | Esta es una clase muy aburrida.
🚫 | It's not necessary. | No es necesario.
👩 | My wife's with my children. | Mi mujer está con mis hijos.
🧑‍🏫 | Why are you a teacher? | ¿Por qué eres profesor?
🧗 | Why is it difficult? | ¿Por qué es difícil?
🐘 | The world is big. | El mundo es grande.
📌 | Europe is a continent. | Europa es un continente.
👥 | These are for them, not for her. | Estos son para ellos, no para ella.
🧒 | It's two p.m. | Son las dos de la tarde.
⌚ | What kind of watch is that? | ¿Qué clase de reloj es ése? ( reloj de pulsera )
🖥️ | They're under the desk. | Están debajo del escritorio.
🤔 | Why are they there? | ¿Por qué están allí?
🤔 | Why is it yours? | ¿Por qué es tuyo?
"""

    private const val list8 = """
📌 | He's a carpenter. | Él es carpintero.
👨 | They're here because they're my parents | Están aquí porque son mis padres.
🌍 | Where are they from? | ¿De dónde son?
📍 | Are you from there too? | ¿Eres de allí también?
⏰ | What time is it? | ¿Qué hora es?
🍊 | It's an orange, not a pear. | Es una naranja, no una pera.
🗣️ | Is this the answer? | ¿Es ésta la respuesta?
📌 | She's very intelligent. | Ella es muy inteligente.
🧑‍🤝‍🧑 | Is she your sister? | ¿Es tu hermana?
📌 | This is the last page. | Esta es la última página.
👋 | How are you doing? | ¿Qué tal estás?
✅ | Fine, thank you. | Bien, gracias.
🧱 | What colour are those walls? | ¿De qué color son esas paredes?
👉 | That one's mine. | Aquél es mio.
🧒 | These are yours. | Estos son tuyos.
🌸 | This one's pretty. | Este es bonito.
🙈 | That one's ugly. | Ese es feo.
👥 | This one's for them. | Este es para ellos.
🪑 | The one on the table is mine. | El que está en la mesa es mío.
🎨 | The green one's hers. | El verde es de ella.
🧒 | The blue ones are theirs. | Los azules son de ellos.
👈 | Which one's yours, this one or that one? | ¿Cuál es el tuyo, éste o aquél?
📍 | The one over there is his. | La que está allí es de él.
👉 | Which ones are theirs? | ¿Cuáles son los de ellos?
🎨 | Which one is yellow? | ¿Cuál es amarillo?
❓ | When is it? | ¿Cuándo es?
👥 | When are the meetings? | ¿Cuándo son las reuniones?
🥱 | I'm tired. | Estoy cansado.
🛏️ | They're not in that room. | No están en esa habitación.
👤 | Who are you? | ¿Quiénes son Uds.?
👋 | How do you do? | ¿Cómo está Ud?
❓ | What's that? | ¿Qué es eso?
🧑‍💼 | Who's their boss? | ¿Quién es su jefe? (de ellos)
👒 | These are hats. | Estos son sombreros.
📦 | The box isn't closed. | La caja no está cerrada.
🇬🇧 | These words aren't English. | Estas palabras no son inglesas.
🇬🇧 | Is London in Germany? | ¿Está Londres en Alemania?
🦒 | The Germans are very tall. | Los alemanes son muy altos.
🪑 | The cats are under the armchair. | .Los gatos están debajo del sillón.
👓 | Why are the glasses empty? | ¿Por qué están vacíos los vasos?
❌ | You're wrong, they're full. | Estás equivocado, están llenos.
📅 | How's the weather today? | ¿Qué tiempo hace hoy?
🥶 | It's cold. | Hace frio.
🥵 | It's hot. | Hace calor.
🌧️ | It's raining. | Está lloviendo.
❄️ | It's snowing. | Está nevando.
☁️ | It's cloudy. | Está nublado.
🌙 | It's dark. | Está oscuro.
😊 | The weather's nice. | Hace buen tiempo.
❌ | The weather's bad. | Hace mal tiempo.
"""

    private const val list9 = """
📍 | The one here is mine. | El que está aquí es mío.
🌸 | Theirs are pretty too. | Los de ellos son bonitos también.
📍 | Where's yours? | ¿Dónde está el tuyo?
🛏️ | Who's in that room? | ¿Quién está en aquella habitación?
👈 | Is this (one) hers? | ¿Es éste el de ella?
👍 | Theirs is like his. | El de ellos es como el de él.
🦒 | He's not the only tall person. | Él no es la única persona alta.
🤷 | I don't know. | No sé.
👤 | Who's this for? | ¿Para quién es esto?
👥 | It's for those people. | Es para aquella gente.
🧗 | Is it hard or soft? | ¿Es duro o blando?
📌 | His is round. | El de él es redondo.
🧩 | Their problem is funny. | Su problema (de ellos) es gracioso.
❓ | His questions are the same. | Sus preguntas (de él) son las mismas.
📅 | It's daytime. | Es de día.
🤝 | They're with her because they're her friends. | Están con ella porque son sus amigos.
📖 | Pepe's book is here. | El libro de Pepe está aquí.
👈 | This isn't the only one. | Este no es el único.
↔️ | Mine are next to his. | Los míos están al lado de los de él.
✉️ | Who is this letter from? | ¿De quién es esta carta? (procedencia)
✉️ | Whose letter is this? | ¿De quién es esta carta? (pertenencia)
👥 | Why are you with them? | ¿Por qué estás con ellos?
👥 | This isn't for them, it's for her. | Esto no es para ellos, es para ella.
🏠 | Enrique's house is very big. | La casa de Enrique es muy grande.
🗼 | Paris is famous for its art. | Paris es famoso por su arte.
🗣️ | His answers are right / correct. | Sus respuestas son correctas (de él)
📍 | Those beaches are far from here. | Aquellas playas están lejos de aquí.
👌 | The first part is very easy. | La primera parte es muy fácil.
👌 | Why is it so easy? | ¿Por qué es tan fácil?
🏁 | Are they ready? | ¿Están ellos listos?
🎨 | The blue one is theirs. | El azul es de ellos.
📍 | Mine are the ones over there. | Los míos son los que están allí.
🤝 | Is he one of your friends? | ¿Es él uno de tus amigos?
🤝 | Is he a friend of yours? | ¿Es amigo tuyo?
🤝 | He's a friend of mine. | Es amigo mío.
🤝 | They're friends of ours. | Son amigos nuestros.
🤝 | She's one of our friends. | Ella es una de nuestros amigos.
🤝 | I'm a friend of theirs. | Soy amigo de ellos.
👨 | That man is my cousin. | Ese señor es mi primo.
📌 | I'm her agent. | Soy su agente (de ella)
🟢 | Their problem is simple. | Su problema (de ellos) es sencillo.
👩 | John's wife is very intelligent. | La mujer de Juan es muy inteligente.
🐘 | Elephants are big. | Los elefantes son grandes.
🧒 | The elephants on Mars are small. | Los elefantes de Marte son pequeños.
👩 | Women are strong. | Las mujeres son fuertes.
🏠 | The women in this house are tall. | Las mujeres de esta casa son altas.
👦 | That man's son is famous. | El hijo de ese señor es famoso.
🥵 | It's hot here. | Hace calor aquí.
🛏️ | It's always cold in this room. | Siempre hace frió en ese cuarto.
📅 | How's the weather today? | ¿Qué tiempo hace hoy?
"""

    private const val list10 = """
👌 | Theirs are easy. | Los de ellos son fáciles.
🧒 | Whose papers are these? | ¿De quién son estos papeles?
💡 | The owner's explanation is clear. | La explicación del dueño está clara.
🧩 | Is it your problem or mine? | ¿Es tu problema o mío?
✨ | The article isn't interesting. | El artículo no es interesante.
📋 | The general's plan is brilliant. | El plan del general es brillante.
😊 | This place is very pleasant. | Este lugar es muy agradable.
🐦 | Birds are very intelligent. | Los pájaros son muy inteligentes.
🐦 | The birds here are dangerous. | Los pájaros aquí son peligrosos.
🚗 | Their car isn't the blue one, but the green one. | Su coche (de ellos) no es el azul, sino el verde.
💧 | The water's very hot. | El agua está muy caliente.
🥵 | It's a very hot day. | Es un día muy caluroso.
🥵 | I'm hot. | Tengo calor.
🥶 | They're cold. | Tienen frío.
😋 | I'm hungry. | Tengo hambre.
🥤 | She's thirsty. | Ella tiene sed.
🗣️ | What's their answer? | ¿Cuál es su respuesta? (de ellos)
⏱️ | They don't know. | No saben.
👥 | Why are the people angry? | ¿Por qué está la gente enfadada?
🧑‍🏫 | I'm here because I'm the teacher. | Estoy aquí porque soy el profesor.
✅ | Is it all right / okay? | ¿Está bien?
💵 | Her money's in good hands. | Su dinero (de ella) está en buenas manos.
👥 | They're not for them. | No son para ellos.
👉 | What are the real figures? | ¿Cuáles son las verdaderas cifras?
👨 | Who's that man over there? | ¿Quién es aquél hombre de allí?
❓ | What is it? | ¿Qué es?
🍳 | Is it next to the kitchen? | ¿Está al lado de la cocina?
❓ | When is it? | ¿Cuándo es?
❓ | What's that? | ¿Qué es aquello?
👉 | What are your intentions? | ¿Cuáles son tus intenciones?
⏰ | It's three o'clock. | Son las tres.
👨 | Are you the man who's sick / ill? | ¿Es Ud el señor que está enfermo?
🤑 | Is he rich? | ¿Es rico él?
🚗 | The rich man's car is the red one. | El coche del rico es el rojo.
🧩 | My uncle's problems are serious. | Los problemas de mi tío son graves.
🚫 | The moon's round because it's not square. | La luna es redonda porque no es cuadrada.
📋 | Why is this list so long? | ¿Por qué es tan larga esta lista?
👀 | The clown's nose is round. | La nariz del payaso es redonda.
🔒 | They're closed. | Están cerrados.
🎨 | The green one is Pepe's. | El verde es de Pepe.
🧒 | Mine and his are the same. | El mío y el de él son iguales.
🧒 | The yellow trucks are my uncle's. | Los camiones amarillos son de mi tío.
🍲 | Helen's soup is cold. | La sopa de Elena está fría.
💥 | His arms and legs are broken. | Sus brazos y piernas están rotos (de él)
📅 | Today's Mary's birthday. | Hoy es el cumpleaños de Maria.
📆 | She's years old. | Ella tiene años.
👦 | Where are your son's toys? | ¿Dónde están los juguetes de tu hijo?
👁️ | Why are John's eyes green? | ¿Por qué son verdes los ojos de Juan?
🏁 | They're not ready yet. | No están listos todavía.
✅ | I'm fine, thank you. | Estoy bien, gracias.
"""

    private const val list11 = """
👉 | That one's mine. | Aquél es el mío.
❓ | Is it yours? | ¿Es tuyo?
📍 | Where are ours? | ¿Dónde están los nuestros?
✨ | Is it new? | ¿Es nuevo?
✅ | His is okay / fine. | El de él está bien.
📌 | Theirs is for her. | El de ellos es para ella.
📌 | It's me. | Soy yo.
🌙 | It's dark. | Está oscuro.
👤 | Who is it? | ¿Quién es?
🖊️ | Those pens are ours. | Aquellos bolígrafos son nuestros.
👈 | This one is Pepe's. | Este es el de Pepe.
📌 | It's us. | Somos nosotros.
🥵 | It's hot. | Hace calor.
🧒 | Theirs are the white ones. | Los de ellos son los blancos.
👉 | Which ones are yours? | ¿Cuáles son los vuestros?
👈 | This isn't it. | Este no es.
📍 | Where are they? | ¿Dónde están?
⏰ | Is it two o'clock? | ¿Son las dos?
👥 | Why is she with you? | ¿Por qué está ella contigo?
📍 | Where are her parents? | ¿Dónde están sus padres? (de ella)
🤔 | Why are we here? | ¿Por qué estamos aquí?
⭐ | Ours is the best. | El nuestro es el mejor.
👤 | Whose is it? | ¿De quién es?
🖊️ | Whose pen is this? | ¿De quién es este bolígrafo?
✉️ | Whose letters are these? | ¿De quién son estas cartas?
👌 | Those problems are easy. | Esos problemas son fáciles.
🐎 | White horses are beautiful. | Los caballos blancos son hermosos.
📌 | It's a circle. | Es un círculo.
🧒 | They're teachers. | Son profesores.
🏠 | She's a housewife. | Ella es ama de casa.
🧑‍🤝‍🧑 | Her husband's brother is here. | El hermano de su marido está aquí.
🥱 | Are they tired? | ¿Están cansados?
📉 | I'm lost. | Estoy perdido.
👌 | Nothing is easy. | Nada es fácil.
🇪🇸 | Spanish guitars are good. | Las guitarras españolas son buenas.
🦒 | Tall people are normally slim. | La gente alta es normalmente delgada.
🧽 | Why is it dirty? | ¿Por qué está sucio?
📌 | Their houses are near hers. | Sus casas (de ellos) están cerca de la de ella.
📍 | They're far from here. | Están lejos de aquí.
📌 | It's very funny. | Es muy gracioso.
🧒 | Are you sure they're ours? | ¿Estás seguro de que son nuestros?
🏠 | They're here because this is their house. | Están aquí porque ésta es su casa.
🛡️ | Are you sure it's hers? | ¿Estás seguro de que es de ella?
📝 | Of course I am. | Por supuesto que si.
📋 | Am I on the list? | ¿Estoy en la lista?
🤝 | Dogs are our best friends. | Los perros son nuestros mejores amigos.
💡 | The light's on. | La luz está encendida.
👈 | This is the end. | Este es el final.
👉 | Which ones are theirs? | ¿Cuáles son los de ellos?
🏢 | Is this their office? | ¿Es éste su despacho? (de ellos)
"""

    private const val list12 = """
❓ | It's very long, isn't it? | Es muy largo, ¿no?
✨ | Isn't it interesting? | ¿No es interesante?
🧑‍🏫 | Isn't he a teacher? | ¿No es él profesor?
🧒 | They're buttons, aren't they? | Son botones, ¿no?
📊 | This is the report, isn't it? | Este es el informe, ¿no?
🤔 | Why is it theirs? | ¿Por qué es de ellos?
👉 | Yours is that one, isn't it? | El tuyo es aquél, ¿no?
👥 | Isn't she with them? | ¿No está ella con ellos?
📍 | Where is she then? | ¿Dónde está ella entonces?
⭐ | We're the best, aren't we? | Somos los mejores, ¿no?
👨 | Isn't that man MrPerez? | ¿No es ese señor el señor Pérez?
👈 | If this one is theirs, then that one's ours. | Si éste es de ellos, entonces ése es nuestro.
🌐 | What's the purpose of all this? | ¿Cuál es el propósito de todo esto?
🤔 | I don't understand. | No entiendo.
🐱 | Isn't it a cat? | ¿No es un gato?
👥 | Those people are angry. | Esa gente está enfadada.
💡 | What are their reasons? | ¿Cuáles son sus razones?
👨 | The old man's health was bad. | La salud del viejo era mala.
👩 | She's his friend's mother. | Ella es la madre de su amigo (de él)
🏷️ | What's her name? | ¿Cómo se llama ella?
🏷️ | Her name's Mary, isn't it? | Se llama María, ¿no?
🦒 | They're very tall mountains. | Son montañas muy altas.
🦒 | It's a very high temperature. | Es una temperatura muy alta.
👩 | The soldier's wife is sick / ill. | La mujer del soldado está enferma.
👩 | The soldiers' wives are here. | Las mujeres de los soldados están aquí.
🪟 | Aren't the windows broken? | ¿No están rotas las ventanas?
👥 | Aren't you the only person here? | ¿No es Ud la única persona aquí?
🚗 | Aren't her cars pretty? | ¿No son bonitos sus coches? (de ella)
🧒 | Elephants are grey, aren't they? | Los elefantes son grises, ¿no?
📖 | That's not the answer that's in the book. | Esa no es la respuesta que está en el libro.
📖 | What an interesting book! | ¡Qué libro más interesante!
🍎 | What good apples! | ¡Qué manzanas más buenas!
❓ | What is it? | ¿Qué es?
👔 | The director's answer is the following: | La respuesta del director es la siguiente:
💵 | The people's money is the country's money. | El dinero de la gente es el dinero del país.
📜 | Isn't this against the rules? | ¿No está esto contra las reglas?
📍 | Where are your parents' clothes? | ¿Dónde está la ropa de tus padres?
🧒 | Those are mine, aren't they? | Aquellos son míos, ¿no?
⏰ | Is it time now? | ¿Es hora ya?
🤔 | Why are they hers? | ¿Por qué son de ella?
✅ | This portion is his, isn't it? | Esta porción es suya, ¿no? (de él)
🥱 | This is boring. | Esto es aburrido.
🤔 | Why isn't he here? | ¿Por qué no está el aquí?
😋 | Who isn't hungry? | ¿Quién no tiene hambre?
✉️ | Why aren't the letters ready? | ¿Por qué no están listas las cartas?
📌 | That's life. | Así es la vida.
🔬 | Life's short, isn't it? | La vida es corta, ¿no?
🌧️ | Rain is necessary. | La lluvia es necesaria.
💎 | Her face is very expressive. | Su cara es muy expresiva(de ella)
📌 | The cathedral is very impressive. | La catedral es muy impresionante.
"""

    private const val list13 = """
🌳 | The presents / gifts are under the tree. | Los regalos están debajo del árbol.
🧒 | These aspects aren't important. | Estos aspectos no son importantes.
✔️ | He's not here, is he? | Él no está aquí, ¿verdad?
🥵 | They're not hot, are they? | No están calientes, ¿verdad?
👧 | She's a wonderful girl. | Es una chica maravillosa.
✔️ | It's not for them, is it? | No es para ellos, ¿verdad?
📈 | Their plan is quite useful. | Su plan (de ellos) es bastante útil.
👤 | Who is it? | ¿Quién es?
✔️ | It's not for me, is it? | No es para mí, ¿verdad?
📌 | This is only the beginning. | Esto sólo es el comienzo.
🛣️ | What highway is that? | ¿Qué carretera es ésa?
🌊 | It's a wide river. | Es un río ancho.
🌸 | It's a pretty wide river. | Es un río bastante ancho.
🌸 | It's pretty / quite wide. | Es bastante ancho.
📌 | He's really powerful. | Él es realmente poderoso.
😊 | Are you really happy? | ¿Eres feliz de verdad?
😊 | I'm glad. | Me alegro.
😊 | Why are you glad? | ¿Por qué te alegras?
😊 | I'm glad when the people are happy. | Me alegro cuando la gente es feliz.
🧒 | Flies are necessary. | Las moscas son necesarias.
🧑‍💼 | I'm here because I'm your boss. | Estoy aquí porque soy tu jefe.
🌍 | Why's everybody angry? | ¿Por qué está todo el mundo enfadado?
💵 | Where's the lawyer's money? | ¿Dónde está el dinero del abogado?
🛡️ | It's in a safe place. | Está en un sitio seguro.
🪧 | The workers are on strike. | Los obreros están en huelga.
🪧 | Why are they on strike? | ¿Por qué están en huelga?
👉 | I'm not a member of that club. | No soy socio de ese club.
📌 | It's a very exclusive club. | Es un club muy exclusivo.
✔️ | I'm right. | Tengo razón.
❌ | I'm wrong. | Estoy equivocado.
✔️ | They're almost / nearly right. | Casi tienen razón.
📌 | The streets are wet. | Las calles están mojadas.
🧽 | Big cities are dirty. | Las ciudades grandes están sucias.
🧒 | Are they from a European country? | ¿Son de un país europeo?
👉 | What are their occupations / jobs? | ¿Cuáles son sus ocupaciones?
📌 | The last line's very funny. | La última línea es muy graciosa.
🧗 | Why is it always difficult? | ¿Por qué es difícil siempre?
💡 | My reasons are a little strange. | Mis razones son un poco extrañas.
🧒 | Your responsibilities are the following | Tus responsabilidades son las siguientes
✉️ | The letter's in the mail /post. | La carta está en el correo.
🧒 | These results are a little better. | Estos resultados son un poco mejores.
🧒 | Ties aren't necessary. | Las corbatas no son necesarias.
👧 | David's sisters are crazy. | Las hermanas de David están locas.
🎮 | The game's a lot of fun. | El juego es muy divertido.
📋 | I'm completely against your plan. | Estoy completamente en contra de tu plan.
📍 | Theirs is the one over there. | El de ellos es el que está allí.
🔬 | The temperature's degrees below zero. | La temperatura es de grados bajo cero.
📌 | My level is above theirs. | Mi nivel está por encima del de ellos.
👈 | This is the end. | Este es el final.
⏳ | Things are never clear. | Las cosas nunca están claras.
"""

    private const val list14 = """
🧑‍🤝‍🧑 | My sister's room is over there. | La habitación de mi hermana está allí.
🛏️ | This is my brothers' room. | Esta es la habitación de mis hermanos.
☕ | Coffee and tea are hot drinks. | El café y el té son bebidas calientes.
📍 | Mine is here and yours is over there. | El mío está aquí y el tuyo allí.
📌 | The pages are torn. | Las páginas están rotas.
👔 | His shirt is torn. | Su camisa (de él) está rota.
💥 | The washing machine is broken. | La lavadora está rota.
💥 | The handle is broken. | El asa está rota.
👗 | Her clothes are quite elegant. | Su ropa (de ella) es bastante elegante.
🤕 | This headache is terrible. | Este dolor de cabeza es terrible.
💡 | The light's off. | La luz está apagada.
👉 | What's the next one? | ¿Cuál es el siguiente?
👈 | Which one's mine, this one or that one? | ¿Cuál es el mío, éste o aquél?
👉 | Which ones are hers? | ¿Cuales son los de ella?
👨 | It's a very elegant suit. | Es un traje (de hombre) muy elegante.
📺 | The game's on TV now. | El partido está en la tele ahora.
🍊 | The earth's round like an orange. | La tierra es redonda como una naranja.
🧒 | Gold and silver are metals. | El oro y la plata son metales.
🖥️ | The students' desks are old. | Los pupitres de los estudiantes son viejos.
👥 | Young people are crazy. | La gente joven está loca.
🧒 | The waves are very high. | Las olas son muy altas.
👌 | It's quite easy, isn't it? | Es bastante fácil, ¿no?
⭐ | It's not too important. | No es demasiado importante.
👋 | How are you doing? | ¿Qué tal?
✅ | Fine, thank you. | Bien, gracias.
🇩🇪 | Those people are German. | Aquella gente es alemana.
🧗 | It's difficult the first time. | Es difícil la primera vez.
🚫 | The names aren't in order. | Los nombres no están en orden.
📍 | The children's mothers are here. | Las madres de los niños están aquí.
🤔 | Why isn't this figure correct? | ¿Por qué no es correcta esta cifra?
📅 | How's the weather today? | ¿Qué tiempo hace hoy?
❄️ | Snow's normal in Sweden. | La nieve es normal en Suecia.
🛣️ | They're outside. | Están fuera (en la calle).
🏫 | My children / kids are at school. | Mis hijos están en el colegio.
📌 | It's a wonderful place. | Es un sitio maravilloso.
💍 | What a beautiful ring! | ¡Qué anillo más hermoso!
🛏️ | At least people are in the waiting room. | Al menos personas están en la sala de espera.
💭 | My opinion's important too. | Mi opinión es importante también.
✔️ | Is it really theirs? | ¿Es de ellos de verdad?
✨ | It's not too small, is it? | No es demasiado pequeño ¿verdad?
👤 | They're the ones who are late. | Son ellos los que están retrasados.
📌 | It's us. | Somos nosotros.
❓ | Is it for him or for her? | ¿Es para él o para ella?
2️⃣ | It's for both of them. | Es para los dos.
😊 | We're glad you're here. | Nos alegramos de que estéis aquí.
😊 | Are you glad we're here? | ¿Te alegras de que estemos aquí?
😊 | Who's glad I'm here? | ¿Quién se alegra de que yo esté aquí?
✅ | The sound's okay but not the picture. | El sonido está bien pero no la imagen.
🧒 | Their lives aren't important. | Sus vidas no son importantes.
💡 | He's here for a reason, isn't he? | Él está aquí por un motivo, ¿no?
"""

    private const val list15 = """
📍 | There's a potato in the oven. | Hay una patata en el horno.
✈️ | There's an airport near here. | Hay un aeropuerto cerca de aquí.
3️⃣ | There are three reasons. | Hay tres razones.
📌 | They are several cases. | Hay varios casos.
🏠 | There isn't a dog in this house. | No hay ningún perro en esta casa.
🔹 | There are some problems. | Hay algunos problemas.
🧩 | There aren't any problems. | No hay problemas.
✔️ | Is he really here? | ¿Está él aquí de verdad?
🧱 | Is there a picture on the wall? | ¿Hay un cuadro en la pared?
🏢 | Are there any serious problems in your company? | ¿Hay graves problemas en tu compañía?
🎯 | Is this my last chance? | ¿Es ésta mi última oportunidad?
👨 | There's a man in the hall / corridor. | Hay un señor en el pasillo.
🏢 | Why is there a frog in my office? | ¿Por qué hay una rana en mi despacho?
❓ | Is there an answer to this question? | ¿Hay una respuesta a esta pregunta?
🥛 | There isn't any milk in this bottle. | No hay leche en esta botella.
🏙️ | There aren't any rats in my city. | No hay ratas en mi ciudad.
🏁 | Why aren't you ready yet? | ¿Por qué no estás listo todavía?
🐘 | There's a big storm in the north. | Hay una gran tormenta en el norte.
🔹 | There are some soldiers with him. | Hay algunos soldados con él.
📍 | Is there a special way? | ¿Hay una forma especial?
🧍 | There's only one way: mine. | Hay solamente una forma: la mía.
🤔 | Why is there only one way? | ¿Por qué hay sólo una forma?
🤔 | Why's your way the only way? | ¿Por qué es tu forma la única forma?
♾️ | Because I'm always right. | Porque siempre tengo razón.
⏳ | Because I'm never wrong. | Porque nunca me equivoco.
👥 | Is there a person for this job? | ¿Hay una persona para este puesto?
💼 | Is he qualified for the job? | ¿Está él cualificado para el puesto?
💡 | There's a reason for everything. | Hay una razón para todo.
📈 | There are some ads, but not many. | Hay algunos anuncios, pero no muchos.
📍 | There are times in which it's necessary. | Hay veces en las cuales es necesario.
⏱️ | Is it necessary now? | ¿Es necesario ahora?
📍 | There's only one possibility. | Hay sólo una posibilidad.
📌 | It's your turn. | Te toca a ti.
💧 | There's some water in the glass. | Hay agua en el vaso.
🔹 | There's some ice in the freezer. | Hay hielo en el congelador.
🧩 | But there's a problem with that. | Pero hay un problema con eso.
👉 | What is it? | ¿Cuál es?
🧗 | It's hard for me to be sincere. | Es duro para mí ser sincero.
🌸 | There's a pretty long line /queue. | Hay una cola bastante larga.
📍 | There's a strange liquid in it. | Hay un extraño líquido en ello.
📺 | There's a good show on TV. | Hay un buen programa en la tele.
🎬 | There are good guys and bad guys in every movie. | Hay buenos y malos en todas las películas.
🚗 | There's a girl in your car. | Hay una chica en tu coche.
👧 | There's a little girl over there. | Hay una niña allí.
🌐 | There are songs from every age. | Hay canciones de todas las épocas.
📈 | There are a lot of problems. | Hay muchos problemas.
👩 | There aren't many women here. | No hay muchas mujeres aquí.
🌳 | Are there many trees? | ¿Hay muchos árboles?
✅ | The figures are okay. | Las cifras están bien.
👥 | There are a lot of people here. | Hay mucha gente aquí.
"""

    private const val list16 = """
🪑 | There's some salt on the table. | Hay sal en la mesa.
👜 | Is there any bread in the bag? | ¿Hay pan en la bolsa?
📉 | There are only a few. | Hay sólo unos cuantos.
🛣️ | There's a highway not far away. | Hay una carretera no lejos.
👌 | There are days when it's easy. | Hay días cuando es fácil.
🧱 | There are shelves along the wall. | Hay estantes a lo largo de la pared.
🖥️ | There are some drawers in my desk. | Hay unos cajones en mi mesa.
👥 | There aren't many people like him. | No hay mucha gente como él
🚪 | There's a dog at the gate. | Hay un perro en la puerta (de valla).
🐕 | Whose dog is it? | ¿De quién es el perro?
📌 | It's Mr.Bruno's. | Es del Sr Bruno.
👦 | He's the man whose son is a poet. | Es el señor cuyo hijo es poeta.
👍 | Why are there problems like this? | ¿Por qué hay problemas como éste?
📍 | There's one here. | Hay uno aquí.
📍 | There's another one over there. | Hay otro allí.
👈 | This one's mine and the other one's his. | Este es mío y el otro es de él.
👨 | There aren't many men like him. | No hay muchos hombres como él.
📍 | There are a lot of children like them. | Hay muchos niños como ellos.
👩 | Are there any women in your company? | ¿Hay mujeres en tu compañía?
📦 | There aren't any toys in the box. | No hay juguetes en la caja.
👧 | Their daughter's name is Elsie. | Su hija (de ellos) se llama Elsie.
🧑‍🤝‍🧑 | Her room's next to her brother's. | Su habitación está junto a la de su hermano.
🚪 | There's someone at the door. | Hay alguien en la puerta.
🚪 | There isn't anyone at the door. | No hay nadie en la puerta.
🔹 | There's someone outside. | Hay alguien fuera.
👤 | Who is it? | ¿Quién es?
📌 | I think it's MrBrown. | Creo que es el Sr Brown.
🛡️ | Are you sure it's him? | ¿Estás seguro de que es él?
📝 | Of course I am. | Por supuesto que sí.
🔹 | Is he with someone? | ¿Está con alguien?
👩 | There's a tall woman with him. | Hay una mujer alta con él.
👥 | There are some other people too. | Hay otra gente también.
🏠 | There's a picture of him at home. | Hay una foto de él en casa.
📍 | There's a tractor in the way. | Hay un tractor en medio.
⏰ | There isn't any time for that. | No hay tiempo para eso.
📍 | There's only one way: mine. | Sólo hay una manera: la mía.
📍 | There aren't any lions in Norway. | No hay leones en Noruega.
📦 | There are some tools in that box. | Hay unas herramientas en esa caja.
🏢 | There isn't a heating system in this office building. | No hay sistema de calefacción en este edificio comercial.
👥 | Why are there people like him? | ¿Por qué hay gente como él?
💵 | There's a lot of money for her. | Hay mucho dinero para ella.
👨 | There's a man there, isn't there? | Hay un hombre allí, ¿no?
🍷 | Is there any wine on the shelf? | ¿Hay vino en la estantería?
🗼 | The woman's dress is from Paris. | El vestido de la mujer es de París.
👉 | What are his intentions? | ¿Cuáles son sus intenciones?(de él)
⭐ | There are several reasons, one of which is important. | .Hay varias razones, una de las cuales es importante
🔹 | There's something strange here. | Hay algo extraño aquí.
⏰ | There's some time, but not much. | Hay tiempo, pero no mucho.
📈 | There are some ads, but not many. | Hay anuncios, pero no muchos.
⏰ | There's plenty of time for that. | Hay tiempo de sobra para eso.
"""

    private const val list17 = """
🚪 | Go to the door. | Ve a la puerta.
🚶 | Come to my side. | Ven a mi lado.
⏱️ | Do it now. | Hazlo ahora.
📍 | Stay there. | Quédate allí.
🏁 | Be prepared. | Estate preparado.
🛑 | Stop the engine. | Para el motor.
⏱️ | Start right now. | Empiece ahora mismo.
🎁 | Give me a chance. | Dame una oportunidad.
📌 | Show me your card. | Enséñame tu carné.
✋ | Eat it with your hands. | Cómelo con las manos.
🚫 | Don't drink it. | No lo bebas.
👀 | Don't look at her. | No la mires.
👀 | Look for them. | Búscalas.
⏳ | Wait for me. | Espérame.
⏳ | Don't do it yet. | No lo hagas todavía.
🔓 | Open it carefully. | Ábrelo con cuidado.
🔒 | Don't close it. | No lo cierres.
👍 | Don't be like them. | No seas como ellos.
🍳 | Take it to the kitchen. | Llévalo a la cocina.
📦 | Bring it immediately. | Tráelo inmediatamente.
🖊️ | Write it with this pen. | Escríbalo con esta pluma.
📖 | Don't read it yet. | No lo leas aún.
💬 | Tell him the following | : Dígale Udlo siguiente:
❓ | Don't ask them for anything. | No les pidas nada.
❓ | Ask him a question. | Hazle una pregunta.
🗣️ | Don't talk so much. | No hables tanto.
🐢 | Speak slowly when you're with me. | Habla despacio cuando estés conmigo.
📌 | Record it with this machine. | Grábelo con esta máquina.
📋 | Listen to me very carefully. | Escúchame con mucho cuidado.
⚠️ | Be careful. | Ten cuidado.
📦 | Put it in the box over there. | Métalo en la caja allí.
🪑 | Don't put your feet on the table. | No ponga sus pies en la mesa.
🔢 | Count to ten. | Cuente hasta diez.
✈️ | Take me to the airport. | Lléveme al aeropuerto.
✋ | Carry it with both hands. | Llévalo con ambas manos.
🚫 | Don't wear that tie. | No lleves puesta esa corbata.
🛏️ | Take two aspirins and go to bed. | Tómate dos aspirinas y acuéstate.
🛏️ | Don't try to read in bed. | No intentes leer en la cama.
⏳ | Don't be late. | No tardes.
💼 | Don't work so much. | No trabajes tanto.
⏱️ | Rest for a few minutes. | Descansa unos minutos.
📖 | Read it first and then sign it. | Léelo primero y después fírmalo.
🧹 | Wash it and dry it. | Lávalo y sécalo.
🪑 | Sit down, please. | Siéntate, por favor.
📌 | Have a seat, please. | Tome asiento, por favor.
👉 | Get up, please. | Levántese, por favor.
📚 | Study the lesson more carefully. | Estudia la lección con más cuidado.
⚡ | Don't walk so fast. | No andes tan deprisa.
💧 | Don't run where there's water. | No corras donde hay agua.
🍽️ | Try this food, it's very good. | Prueba esta comida, es muy buena.
"""

    private const val list18 = """
🎮 | Don't play with that. | No juegues con eso.
🍽️ | Don't touch the food. | No toques la comida.
⏰ | Wake up. | Despiértate.
📌 | Wake him up. | Despiértale.
📚 | Study it very carefully. | Estúdialo con mucho cuidado.
⚡ | Learn it as soon as possible. | Apréndelo cuanto antes.
📋 | Add this to the list. | Añade esto a la lista.
🎵 | Play the guitar a little. | Toque la guitarra un poco.
⌚ | Watch them. | Vigílales.
💥 | Don't break it. | No lo rompas.
⏱️ | Begin /start now. | Empiecen ahora.
⚡ | Finish it as soon as possible. | Termínalo cuanto antes.
🤷 | Don't forget it. | No lo olvides.
🔨 | Build it as far away as possible. | Constrúyelo lo más lejos posible.
💵 | Don't buy them with this money. | No los compres con este dinero.
📏 | Cut it as short as possible. | Córtalo lo más corto posible.
🍺 | Have a beer. | Tome una cerveza.
🍂 | Don't fall (down). | No te caigas.
🔍 | Find them. | Encuéntrales.
⚡ | Get it as soon as possible. | Consígalo lo antes posible.
😡 | Don't get angry. | No se enfade Ud.
👉 | Get up, please. | Levántese, por favor.
🎁 | Give them a chance. | Dales una oportunidad.
📌 | Keep it on the top shelf. | Guárdalo en el estante superior.
🛏️ | Leave the room where you are. | Sal de la habitación donde estás.
⏰ | Leave before three o'clock. | Márchese antes de las tres.
🚪 | Don't leave her. | No la abandones.
🚪 | Leave it to me. | Déjamelo a mí.
🚪 | Don't leave your toys on the rug. | No dejes tus juguetes en la alfombra.
📉 | Don't lose it. | No lo pierdas.
📌 | Let him do it. | Déjale hacerlo.
👀 | Let me see. | A ver.
🚪 | Let them leave. | Déjales marcharse.
💪 | Make an effort. | Haz un esfuerzo.
🏷️ | Say only your first name. | Di sólo tu nombre de pila.
🤔 | Tell them why you're here. | Diles por qué estás aquí.
💬 | Don't say anything. | No digas nada.
💬 | Don't tell them anything. | No les digas nada.
👨 | Sell it to that man. | Véndeselo a ese señor.
📤 | Send it by airmail. | Mándalo por correo aéreo.
📤 | Don't send it to me. | No me lo envíes.
🛏️ | Don't sleep on soft beds. | No duermas en camas blandas.
🇬🇧 | Speak English or leave. | Hable inglés o márchese.
🗣️ | Don't talk so much. | No hables tanto.
💵 | Don't spend so much money. | No gastes tanto dinero.
📌 | Teach him the alphabet. | Enséñale el alfabeto.
📷 | Show me the picture. | Enséñame la foto.
📌 | Think about it. | Piénsalo.
🗣️ | Answer them. | Contéstales.
⏰ | Call me at any time. | Llámame a cualquier hora.
"""

    private const val list19 = """
📌 | Cancel it | .Cancélalo.
1️⃣ | Change it for another (one). | Cámbialo por otro.
✨ | Don't clean it with that. | No lo limpies con eso.
🚫 | Don't cover them. | No los cubras.
😭 | Don't cry so much. | No llores tanto.
📌 | Enjoy it. | Disfruta con ello.
📢 | Explain everything. | Explica todo.
🚫 | Don't kill them. | No les mates.
🤥 | Don't lie to me. | No me mientas.
🛏️ | Move it to the other side of the room. | Muévelo al otro lado del cuarto.
👉 | Offer it to them. | Ofréceselo a ellos.
⚠️ | Plan your actions carefully. | Planifica tus acciones con cuidado.
🥩 | Order the steak well done. | Pide el filete bien hecho.
📌 | Prepare your speech in advance. | Prepara tu discurso con antelación.
🚫 | Don't push me. | No me empujes.
🚫 | Don't paint the ceiling. | No pintes el techo.
📌 | Type it. | Pásalo a máquina.
🚫 | Don't drop it. | Que no se te caiga.
📌 | Check it. | Compruébalo.
😟 | Don't worry. | No te preocupes.
📌 | Save her. | Sálvala.
💵 | Save more money. | Ahorra más dinero.
📍 | Stay where you are. | Quédate donde estás.
👈 | Use this one, not that one. | Utilice éste, no aquél.
📍 | Don't park near here. | No aparques cerca de aquí.
👉 | Don't smoke in front of them. | No fumes delante de ellos.
🧂 | Pass me the salt. | Pásame la sal.
🐢 | Cook it slowly. | Cocínalo lentamente.
🧱 | Don't jump over the wall. | No saltes la tapia.
🆘 | Help us. | Ayúdanos.
👥 | Don't attend the meeting. | No asistas a la reunión.
📌 | Wake us up before them. | Despiértanos antes que a ellos.
📉 | Don't lose your only chance. | No pierdas tu única oportunidad.
🌊 | Wait until it's time. | Espera hasta que sea la hora.
🌅 | Arrive early/ Get there early. | Llega pronto.
📍 | Don't get there before me. | No legues allí antes que yo.
🧑‍⚕️ | Take him to the doctor. | Llévale al médico.
👀 | Look for them. | Búscales.
🎁 | Give it to me. | Dámelo.
🎁 | Give it to them. | Dáselo a ellos.
🎁 | Give them to us. | Dánoslos.
🎁 | Give it to her. | Dáselo a ella.
📢 | Explain it to him. | Explícaselo a él.
📌 | Show it to us. | Muéstranoslo.
📦 | Bring them to me. | Tráemelos.
📦 | Take them to him. | Llévaselos a él.
⏳ | Wait for me. | Espérame.
👀 | Look at her. | Mírala.
📋 | Listen to it. | Escúchalo.
📖 | Read it to me. | Léemelo.
"""

    private const val list20 = """
🔹 | They're doing something. | Están haciendo algo.
🎮 | I'm playing. | Estoy jugando.
📌 | We're resting. | Estamos descansando.
🍽️ | I'm eating with my parents. | Estoy comiendo con mis padres.
📌 | He's showing me his uniform. | Él me está enseñando su uniforme.
⏱️ | They're coming now. | Están viniendo ahora.
✨ | I'm starting a new life. | Estoy empezando una nueva vida.
📻 | We're listening to the radio. | Estamos escuchando la radio.
⏱️ | She's bringing it to me right now. | Ella me lo está trayendo ahora mismo.
❓ | Are they looking at me? | ¿Están mirándome?
✍️ | Why are they writing that? | ¿Por qué están escribiendo eso?
🩹 | I'm recording a tape. | Estoy grabando una cinta.
🏬 | We're looking for a flat. | Estamos buscando un piso.
⏳ | Why are you waiting for them? | ¿Por qué estáis esperándoles?
🇩🇪 | I think she's speaking German. | Creo que ella está hablando alemán.
👤 | Who's she talking to? | ¿Con quién está ella hablando?
⏰ | I'm having a good time. | Me lo estoy pasando bien.
📍 | Where are they going? | ¿A dónde van?
📦 | He's opening the box carefully. | Él está abriendo la caja con cuidado.
🌧️ | It's raining. | Está lloviendo.
❄️ | It's not snowing. | No está nevando.
🧽 | They're washing it because it's dirty. | Están lavándolo porque está sucio.
📌 | He's giving it to me because it's mine. | Él me lo está dando porque es mío.
🏨 | They're staying at that hotel. | Están alojándose en ese hotel.
📈 | You're drinking a lot. | Estás bebiendo mucho.
❓ | Why are you asking me so many questions? | ¿Por qué me estás haciendo tantas preguntas?
1️⃣ | She's the one (who's) wearing a blouse. | Ella es la que lleva una blusa.
🌧️ | It's starting to rain. | Está empezando a llover.
🪵 | I'm sitting on the floor. | Estoy sentado en el suelo.
🛏️ | Somebody's sleeping in my bed. | Alguien está durmiendo en mi cama.
📖 | Are you reading it? | ¿Estás leyéndolo?
💼 | Why aren't you working? | ¿Por qué no estás trabajando?
📌 | I'm drying the dishes. | Estoy secando la vajilla.
❓ | What are you doing? | ¿Qué haces?
⭕ | Why aren't you doing anything? | ¿Por qué no estás haciendo nada?
⚡ | They're running faster than I am. | Están corriendo más rápido que yo.
📌 | They're bringing me presents. | Me están trayendo regalos.
🛒 | We're closing the store. | Estamos cerrando la tienda.
📌 | He's speaking with an accent. | Él está hablando con un acento.
😴 | I'm trying to sleep. | Estoy intentando dormir.
🚗 | They're trying to stop the car. | Están intentando parar el coche.
🔹 | We're giving them some problems. | Estamos dándoles algunos problemas.
✋ | I'm putting it in your hands. | Estoy poniéndolo en tus manos.
🌇 | I'm getting up later than before. | Estoy levantándome más tarde que antes.
📈 | You're drinking too much. | Estás bebiendo demasiado.
🧳 | Who's carrying the suitcases? | ¿Quién está llevando las maletas?
👤 | Who are you working with now? | ¿Con quién estás trabajando ahora?
🤔 | Why are you doing this to me? | ¿Por qué estás haciéndome esto?
😡 | I'm starting to get angry. | Estoy empezando a enfadarme.
📋 | I'm making a very long list. | Estoy haciendo una lista muy larga
"""

    private const val list21 = """
🗣️ | They're talking about you. | Están hablando de ti.
📋 | Why are you listening to them? | ¿Por qué estás escuchándoles?
👤 | Who are you playing with? | ¿Con quién estás jugando?
⏱️ | They're waking them up now. | Están despertándoles ahora.
⭕ | I'm not buying anything. | No estoy comprando nada.
🤔 | Why are they watching us? | ¿Por qué nos están vigilando?
📺 | We're watching TV. | Estamos viendo la tele.
💡 | I'm beginning to understand them. | Estoy empezando a comprenderles.
📈 | You're forgetting too many things. | Se te están olvidando demasiadas cosas.
🧠 | What are you learning? | ¿Qué estáis aprendiendo?
🧠 | We're learning how to drive. | Estamos aprendiendo a conducir.
🤔 | Why are you leaving her? | ¿Por qué la estás dejando?
⏱️ | They're leaving now. | Están marchándose ahora.
🗣️ | She's losing her voice. | Ella está perdiendo su voz.
🥱 | I'm getting tired of this. | Me estoy cansando de esto.
⏱️ | They're getting married now. | Se están casando ahora.
🛒 | They're buying everything there is in the store. | Están comprando todo lo que hay en la tienda.
🤔 | Why are you selling it? | ¿Por qué lo estás vendiendo?
❓ | What are you saying? | ¿Qué dices?
❓ | What are you telling them? | ¿Qué les estás diciendo?
🤔 | Why are you letting them escape? | ¿Por qué estás dejándoles escapar?
🏢 | He's leaving the company. | Él se está marchando de la compañía.
📌 | I'm keeping it because it's mine. | Me lo quedo porque es mío.
⭐ | You're forgetting something important. | Te olvidas de algo importante.
🌐 | You're breaking all the rules. | Estás incumpliendo todas las reglas.
🧒 | The child is falling. | El niño está cayendo.
💵 | You're spending too much money. | Estás gastando demasiado dinero.
👥 | Why aren't you attending the meeting? | ¿Por qué no estás asistiendo a la reunión?
📅 | I'm planning to do it tomorrow. | Pienso hacerlo mañana.
👀 | When are you planning to see him? | ¿Cuándo piensas verle?
❓ | I'm answering your questions. | Estoy contestando a tus preguntas.
🤔 | Why are you calling me now? | ¿Por qué estás llamándome ahora?
☂️ | They're carrying umbrellas. | Están llevando paraguas.
😭 | Why are you crying? | ¿Por qué estás llorando?
🎨 | They're changing the colour of the flag. | Están cambiando el color de la bandera.
🤔 | Why are you explaining it to me? | ¿Por qué estás explicándomelo?
👋 | They're pulling our leg. | Nos están tomando el pelo.
📈 | I'm enjoying it very much. | Lo estoy disfrutando mucho.
🤔 | Why are you offering this to me? | ¿Por qué estás ofreciéndome esto?
🛒 | We're opening a new store. | Estamos abriendo una nueva tienda.
🤔 | Why are you returning it to me? | ¿Por qué me lo estás devolviendo?
😂 | Why are you laughing? | ¿Por qué te ríes?
😄 | Why are you smiling? | ¿Por qué sonríes?
📌 | I'm repairing the engine. | Estoy reparando el motor.
📈 | Why are you saving so much? | ¿Por qué estás ahorrando tanto?
❓ | What are you doing? | ¿Qué haces?
📌 | He's painting a picture. | Él está pintando un cuadro.
🤔 | Why are you shouting at them? | ¿Por qué estás gritándoles?
👤 | Who are you waiting for? | ¿A quién estás esperando?
🤔 | Why are you pointing at me? | ¿Por qué estás señalándome a mí?
"""

    private const val list22 = """
🌳 | They're planting a lot of trees. | Están plantando muchos árboles.
❓ | Why are you asking me questions? | ¿Por qué me estás haciendo preguntas?
1️⃣ | They're asking me for a raise. | Me están pidiendo una subida salarial.
📌 | We're checking your record. | Estamos comprobando tu expediente.
👨‍🍳 | What are you cooking? | ¿Qué estás cocinando?
🛣️ | Why are you crossing the street? | ¿Por qué estás cruzando la calle?
💃 | They're dancing together. | Están bailando juntos.
💼 | I'm finishing the job. | Estoy terminando el trabajo.
🏷️ | Prices are going up. | Los precios están subiendo.
🤔 | Why are you helping her? | ¿Por qué la estás ayudando?
🚭 | He's smoking more than usual. | El está fumando más que de costumbre.
📌 | They're going down the stairs. | Están bajando las escaleras.
🤔 | Why are you washing the dishes? | ¿Por qué estás lavando los platos?
📊 | I'm typing the report. | Estoy mecanografiando el informe.
❓ | He's moving to Vienna, isn't he? | Él está trasladándose a Viena, ¿no?
🤔 | Why are you pushing him? | ¿Por qué estás empujándole?
💼 | I'm working in a foreign company. | Estoy trabajando en una compañía extranjera.
🚪 | Who's knocking at the door? | ¿Quién está llamando a la puerta?
👤 | Who are you talking to? | ¿Con quién estás hablando?
📋 | What are you planning to do? | ¿Qué piensas hacer?
📍 | Where are you planning to go? | ¿A dónde piensas ir?
👤 | Who are you planning to call? | ¿A quién piensas llamar?
🎯 | Which one are you planning to choose, this one or that one. | ¿Cuál piensas elegir, éste o aquél?
📌 | We're trying to convince him. | Estamos intentando convencerle.
📌 | He's trying to avoid us. | Él está intentando evitarnos.
📌 | We're making profits. | Estamos consiguiendo beneficios.
😋 | They're suffering because they're hungry. | Están sufriendo porque tienen hambre.
📆 | He's years old. | Él tiene años.
🧒 | He has three children. | Tiene tres hijos.
🚗 | He has a car. | Él tiene coche.
📞 | He doesn't have a telephone. | No tiene teléfono.
🏢 | He doesn't have an office. | No tiene despacho.
📈 | He has a lot of problems. | Tiene muchos problemas.
👥 | He doesn't have many people in his business. | No tiene a mucha gente en su negocio.
📈 | He has a lot of patience. | Tiene mucha paciencia.
⏰ | He doesn't have much time. | No tiene mucho tiempo.
📆 | I'm years old. | Tengo años.
🌍 | I have problems like everyone. | Tengo problemas como todo el mundo.
🧒 | I don't have any children. | No tengo hijos.
⏰ | They don't have enough time. | No tienen suficiente tiempo.
🔹 | We have some problems. | Tenemos algunos problemas.
🚲 | Does he have a bicycle? | ¿Tiene él bicicleta?
⭕ | He doesn't have anything. | Él no tiene nada.
📓 | Who has the notebook? | ¿Quién tiene el cuaderno?
⏰ | Do you have time to see me? | ¿Tienes tiempo para verme?
🧒 | How many children do you have? | ¿Cuántos hijos tienes?
💵 | How much time do we have? | ¿Cuánto tiempo tenemos?
🏠 | Where does he have his house? | ¿Dónde tiene él su casa?
⏰ | What time does he have breakfast? | ¿A qué hora desayuna él?
⏰ | What time do you have dinner? | ¿A qué hora cenas?
"""

    private const val list23 = """
⏱️ | Do you know him? | ¿Le conoces?
🎮 | Do they play with him often? | ¿Juegan con él a menudo?
🎵 | I like that kind of music. | Me gusta ese tipo de música.
👍 | He works like an ant. | Él trabaja como una hormiga.
📈 | He knows a lot about you. | Él sabe mucho acerca de ti.
📅 | She goes there every Saturday. | Ella va allí todos los sábados.
🤔 | Why does she go there? | ¿Por qué va ella allí?
🤷 | I don't know. | No lo sé.
⏱️ | What do you know? | ¿Qué sabes?
🎵 | She plays the piano very well. | Ella toca el piano muy bien.
⌚ | He watches TV all day. | Él ve la tele todo el día.
🌅 | She wakes up early on Fridays. | Ella se despierta pronto los viernes.
💵 | He spends a lot of money. | Él gasta mucho dinero.
💵 | Why does he spend so much money? | ¿Por qué gasta tanto dinero?
❓ | Ask him. | Pregúntale.
💵 | He keeps his money in that bank. | Él guarda su dinero en ese banco.
🤔 | Why does he keep it there? | ¿Por qué lo guarda allí?
📍 | Because he likes to keep it there. | Porque le gusta guardarlo allí.
❓ | Is this yours? | ¿Es tuyo esto?
📌 | Yes, it belongs to me. | Si, me pertenece a mí.
⏱️ | How do you know it belongs to you? | ¿Cómo sabes que te pertenece?
3️⃣ | Because mine has three circles. | Porque el mío tiene tres círculos.
🚗 | He sells cars to make a living. | El vende coches para ganarse la vida.
🚗 | Does he sell a lot of cars? | ¿Vende muchos coches?
📌 | I think so. | Creo que si.
⏱️ | Does he know me? | ¿Me conoce?
✅ | He knows you and l think he likes you. | Te conoce y creo que le caes bien.
⏰ | What time does he get to work? | ¿A qué hora llega él al trabajo?
⏰ | What time does he leave work? | ¿A qué hora deja el trabajo?
💼 | What does he do after work? | ¿Qué hace después del trabajo?
⏰ | What time does he get up in the morning? | ¿A qué hora se levanta por la mañana?
🏠 | What time does he leave home? | ¿A qué hora sale de casa?
⏰ | What time does he have breakfast? | ¿A qué hora desayuna?
💼 | How does he go to work? | ¿Cómo va al trabajo?
💼 | What does he do at work? | ¿Qué hace en el trabajo?
🍽️ | Does he have lunch with you? | ¿Come contigo?
🍽️ | Who does he have lunch with? | ¿Con quién come?
💼 | Does he travel a lot in his job? | ¿Viaja mucho en su trabajo?
📈 | Does he earn a lot? | ¿Gana mucho?
💵 | How much does he earn a month? | ¿Cuánto gana al mes?
⏰ | What time does he start work? | ¿A qué hora empieza el trabajo?
⏰ | What time does he finish? | ¿A qué hora termina?
⏰ | He gets to work at eight o'clock. | Él llega al trabajo a las ocho.
📌 | He gets up at six-thirty. | Él se levanta a las seis y media.
💼 | He leaves work at one-fifteen. | Él deja el trabajo a la una y cuarto.
🏠 | He goes home after work. | Va a casa después del trabajo.
🏠 | He leaves home at seven-twenty. | Sale de casa a las siete y veinte.
🚗 | He drives to work. | Va al trabajo en coche.
⚡ | He has breakfast at ten to seven. | Desayuna a las siete menos diez.
🍽️ | He doesn't have lunch with me. | No come conmigo.
"""

    private const val list24 = """
🍽️ | He has lunch alone. | Él come solo.
🍽️ | Why does he have lunch alone? | ¿Por qué come solo?
📌 | Because he likes to. | Porque le gusta.
🔬 | He earns very little. | Gana muy poco.
💼 | He starts work at five past eight. | Empieza el trabajo a las ocho y cinco.
🛏️ | He finishes at one-fifteen. | Termina a la una y cuarto.
🧑‍🤝‍🧑 | She's your sister, isn't she? | Ella es tu hermana, ¿no?
❓ | Is she older than you? | ¿Es mayor que tú?
❓ | Is she married? | ¿Está casada?
🧒 | Does she have any children? | ¿Tiene hijos?
📍 | Where does she live? | ¿Dónde vive?
🏠 | Does she live in a big house? | ¿Vive en una casa grande?
📞 | Does she call you often? | ¿Te llama a menudo?
📞 | Why doesn't she call you? | ¿Por qué no te llama?
💼 | Where does she work? | ¿Dónde trabaja?
👨 | Does her husband know you well? | ¿Su marido te conoce bien?
❓ | What does he do? | ¿Qué hace él?
📈 | Does he earn a lot? | ¿Gana mucho?
💼 | Does he work on Saturdays? | ¿Trabaja los sábados?
💼 | How does he like his job? | ¿Cómo le gusta su trabajo?
🧒 | Does he spend a lot of time with his children? | ¿Pasa mucho tiempo con sus hijos?
🏠 | Does he live in a house or a flat? | ¿Vive en un chalet o un piso?
📍 | She lives far from here. | Ella vive lejos de aquí.
🌐 | He breaks everything he touches. | Él rompe todo lo que toca.
👍 | He plays the guitar like you do. | Él toca la guitarra como tú.
👌 | He forgets things easily. | Él olvida las cosas fácilmente.
😡 | He gets angry when he loses. | Se enfada cuando pierde.
📉 | Does he lose a lot? | ¿Pierde a menudo?
📌 | He says what he thinks. | Dice lo que piensa.
📌 | He thinks we're crazy. | Cree que estamos locos.
✔️ | Is he right? | ¿Tiene razón?
⏱️ | Who knows? | ¿Quién sabe?
⏱️ | God only knows. | Sólo Dios sabe.
🗣️ | How many languages does he speak? | ¿Cuántos idiomas habla él?
💬 | What does he say when he sees you? | ¿Qué dice él cuando te ve?
💬 | He doesn't say anything. | No dice nada.
♾️ | He always falls down when he comes here. | Él siempre se cae cuando viene aquí.
🚶 | How often does he come here? | ¿Con qué frecuencia viene aquí?
📌 | He comes too often. | Viene con demasiada frecuencia.
🚶 | Why does he come so often? | ¿Por qué viene tan frecuentemente?
📌 | Because he likes to. | Porque le gusta.
💼 | Does he let you work? | ¿Os deja trabajar?
💼 | Of course he lets us work. | Por supuesto que nos deja trabajar.
⏰ | Does he let you rest from time to time? | ¿Os deja descansar de vez en cuando?
❓ | That's another question. | Esa es otra cuestión.
🧩 | Does he stay here when there are problems? | ¿Se queda aquí cuando hay problemas?
👍 | He doesn't like to stay here. | No le gusta quedarse aquí.
💼 | He prefers to send an assistant. | Prefiere mandar a un ayudante.
✅ | Does he treat you well? | ¿Os trata bien?
🌍 | He treats us like everyone else. | Nos trata como a todo el mundo.
"""

    private const val list25 = """
📍 | Who lives here? | ¿Quién vive aquí?
📍 | No one lives here. | Nadie vive aquí.
🏠 | Is the house empty? | ¿Está la casa vacía?
📈 | How many floors does it have? | ¿Cuántas plantas tiene?
📆 | How old is it? | ¿Cuántos años tiene?
❓ | Does it have a roof? | ¿Tiene tejado?
📍 | Where are you going? | ¿A dónde vas?
♾️ | Do you always go there? | ¿Siempre vas allí?
👍 | Why do you like to go there? | ¿Por qué te gusta ir allí?
📌 | Show me your tie. | Enséñame tu corbata.
👗 | Where do you buy your clothes? | ¿Dónde compras tu ropa?
🏷️ | Does a tie like that cost a lot? | ¿Cuesta mucho una corbata como ésa?
💵 | How much does it cost? | ¿Cuánto cuesta?
🏷️ | Why does it cost so much? | ¿Por qué cuesta tanto?
🛍️ | Why do you buy ties like that one? | ¿Por qué compras corbatas como ésa?
👍 | Because I like to. | Porque me gusta.
❓ | Why do you ask me so many questions? | ¿Por qué me haces tantas preguntas?
📌 | Because I want to. | Porque quiero.
❓ | Do you like asking me questions? | ¿Te gusta hacerme preguntas?
🏠 | He takes me home every day. | Él me lleva a casa todos los días.
📅 | She brings me here every Monday. | Ella me trae aquí cada lunes.
🤔 | Why does she do that? | ¿Por qué hace ella eso?
🚗 | Because she thinks I don't have a car. | Porque cree que no tengo coche.
🗣️ | Why does she talk so much? | ¿Por qué habla ella tanto?
🛣️ | Because nobody tells her to be quiet. | Porque nadie le dice que se calle.
⏱️ | Does she know you're married? | ¿Sabe (ella) que estás casado?
🛑 | Do you stop when the light's red? | ¿Paras cuando el semáforo está en rojo?
🇩🇪 | I know how to speak German. | Sé hablar alemán.
🇫🇷 | They write for a French magazine. | Escriben para una revista francesa.
🍽️ | Do you eat a lot? | ¿Comes mucho?
🥗 | He's fat because he eats too much. | Él está gordo porque come demasiado.
🚪 | How do you close this door? | ¿Cómo cierras esta puerta?
⏰ | How long do you wait for them? | ¿Durante cuánto tiempo les esperas?
📋 | He listens to the news every day. | Él escucha las noticias cada día.
👉 | Is that picture yours? | ¿Es tuyo ese cuadro?
❓ | Does it have a title? | ¿Tiene título?
💵 | How much does it cost? | ¿Cuánto cuesta?
❓ | What does the title mean? | ¿Qué significa el título?
🔤 | What does this word mean? | ¿Qué significa esta palabra?
💬 | What do you mean? | ¿Qué quieres decir?
🌍 | Does everyone know where you are? | ¿Sabe todo el mundo donde estás?
❓ | Do they ask you questions? | ¿Te hacen preguntas?
🗣️ | Do you have to answer them? | ¿Tienes que contestarlas?
💼 | How does it work? | ¿Cómo funciona?
💼 | How does he work? | ¿Cómo trabaja él?
🔉 | How does it sound? | ¿Cómo suena?
✍️ | I know how to read and write. | Sé leer y escribir.
⏱️ | Does he know how to spell correctly? | ¿Sabe él deletrear correctamente?
⏱️ | They know everything about us. | Saben todo acerca de nosotros.
⏳ | If you never play, you never win. | Si nunca juegas, nunca ganas.
"""

    private const val list26 = """
👥 | I attend a lot of meetings. | Asisto a muchas reuniones.
📅 | I'm going to attend one tomorrow. | Voy a asistir a una mañana.
🔨 | What are you going to do? | ¿Qué vas a hacer?
🗣️ | I'm not going to answer that. | No voy a contestar a eso.
🚶 | They come here quite often. | Vienen aquí bastante a menudo.
💪 | I always make an effort. | Siempre hago un esfuerzo.
💼 | I'm planning to give you a job. | Pienso darte un trabajo.
🤔 | Why do you want to do that? | ¿Por qué quieres hacer eso?
🤷 | I don't know yet. | No sé todavía.
⏳ | I'm still thinking about it. | Todavía estoy pensando en ello.
⏳ | Don't wait too long. | No esperes demasiado.
😟 | Don't worry. | No te preocupes.
😟 | You worry too much. | Te preocupas demasiado.
😟 | Why do you worry so much? | ¿Por qué te preocupas tanto?
👍 | He walks like a monkey. | Él camina como un mono.
👍 | He talks like a parrot. | Habla como un loro.
🧗 | He works hard / a lot. | Trabaja mucho.
🧗 | He likes to work hard. | Le gusta trabajar mucho.
⭐ | He does a lot of important jobs. | Hace muchos trabajos importantes.
🤒 | He gets sick when he sees me. | Se pone enfermo cuando me ve.
📌 | He loses control of himself. | Pierde el control de si mismo.
💊 | He takes too much medicine. | Toma demasiadas medicinas.
⭐ | He forgets important appointments. | Se le olvidan citas importantes.
🔬 | He reads a lot but learns very little. | Lee mucho pero aprende muy poco.
🌙 | He teaches Greek at night. | Enseña griego por las noches.
⏳ | He never goes out with anyone. | Nunca sale con nadie.
🏠 | He stays at home on the weekends. | Se queda en casa los fines de semana.
⏳ | He never does anything but read. | Nunca hace nada salvo leer.
🏠 | He wears strange clothes at home. | Lleva ropa extraña en casa.
🧾 | Sometimes he tells me a joke. | A veces me cuenta un chiste.
👥 | But he's a quiet person. | Pero es una persona callada.
🤝 | He talks only when he's with friends. | Habla sólo cuando está con amigos.
🤝 | Are you a friend of his? | ¿Eres amigo suyo?
📌 | I think I am. | Creo que lo soy.
🗣️ | Does he like to talk to you? | ¿Le gusta hablar contigo?
🚫 | Sometimes he does and sometimes he doesn't. | A veces si y a veces no.
❓ | Doesn't he want to get married? | ¿No se quiere casar?
❓ | Doesn't he have any hobbies? | ¿No tiene hobbys?
🧾 | Doesn't he realise that life is short? | ¿No se da cuenta de que la vida es corta?
🧍 | What does he do when he's alone? | ¿Qué hace cuando está solo?
⭕ | He doesn't do anything. | No hace nada.
🔹 | Do you ever visit him? | ¿Le visitas alguna vez?
⏳ | He never wants to see anyone. | Nunca quiere ver a nadie.
❗ | How strange! | ¡Qué extraño!
💼 | Does he travel in his job? | ¿Viaja en su trabajo?
📌 | I think so. | Creo que si.
👀 | Does he have to see customers? | ¿Tiene que ver a clientes?
👥 | He has to see a lot of people. | Tiene que ver a mucha gente.
🗣️ | How does he talk to them? | ¿Cómo les habla?
🗣️ | When he has to talk, he talks. | Cuando tiene que hablar, habla.
"""

    private const val list27 = """
💬 | I'm not going to tell you anything. | No voy a decirte nada.
💬 | What do you mean? | ¿Qué quieres decir?
⏱️ | You know what I mean. | Tú sabes lo que quiero decir.
🤔 | Why are you going to do that? | ¿Por qué vas a hacer eso?
📌 | Because I do what I want. | Porque hago lo que quiero.
⏱️ | Do you know what you're saying? | ¿Sabes lo que estás diciendo?
✔️ | I'm simply telling the truth. | Simplemente estoy diciendo la verdad.
❌ | I feel bad. | Me encuentro mal.
🍽️ | It's because you eat too much. | Es porque comes demasiado.
🍽️ | How do you know I eat too much? | ¿Cómo sabes que como demasiado?
📌 | I have my sources of information. | Tengo mis fuentes de información.
📈 | How many spies do you have in the organization? | ¿Cuántas espías tienes en la organización?
⏱️ | Enough to know a lot of things. | Los suficientes como para saber muchas cosas.
❓ | How does she feel? | ¿Cómo se encuentra ella?
⭐ | I think she's getting better. | Creo que está mejorando.
💭 | Does she remember the accident? | ¿Se acuerda del accidente?
💭 | She doesn't remember anything. | No se acuerda de nada.
👥 | Does she recognize people? | ¿Reconoce a la gente?
🏥 | When's she going to leave the hospital? | ¿Cuándo va a dejar el hospital?
❓ | Are you going to operate on her? | ¿Vais a operarla?
👨‍👩‍👧 | Do you let her family visit her? | ¿Dejáis que su familia la visite?
❓ | When are you going to check it? | ¿Cuándo lo vas a comprobar?
📍 | What do you do when you're here? | ¿Qué haces cuando estás aquí?
🇺🇸 | Who has relatives in America? | ¿Quién tiene parientes en América?
🇨🇦 | I have an uncle in Canada. | Tengo un tío en Canadá.
🇬🇧 | Does he speak English? | ¿Habla él inglés?
🗣️ | It's the only language he speaks. | Es la única lengua que habla.
⏱️ | Do you know him well? | ¿Le conoces bien?
🇨🇦 | I go to Canada every summer. | Voy a Canadá todos los veranos.
👥 | How do you communicate with him? | ¿Cómo comunicas con él?
💬 | What do you mean? | ¿Qué quieres decir?
🗣️ | I mean, "how do you speak to him?" | Quiero decir "¿cómo le hablas?"
⏱️ | I speak to him like I'm speaking to you now. | Le hablo como te hablo a ti ahora.
🇪🇸 | But he doesn't speak Spanish. | Pero él no habla español.
🇬🇧 | I speak to him in English. | Le hablo en inglés.
🇬🇧 | Then you know English. | Entonces sabes inglés.
📝 | Of course I do, don't you? | Por supuesto que si, ¿tú no?
⏱️ | I know a little, but not enough. | Sé un poco, pero no lo suficiente.
❓ | Not enough for what? | ¿No lo suficiente para qué?
🗣️ | Not enough to speak well. | No lo suficiente para hablar bien.
1️⃣ | It's a pity | .Es una lástima.
🤔 | Why do you say that? | ¿Por qué dices eso?
⏱️ | Because you don't know what you're missing. | Porque no sabes lo que estás perdiendo.
🗣️ | Teach me the language. | Enséñame el idioma.
🤷 | I don't know how to teach it. | No sé enseñarlo.
🧠 | Then, how am I going to learn it? | Entonces ¿cómo voy a aprenderlo?
👍 | Do it like I do it. | Hazlo como lo hago yo.
❓ | How do you do it? | ¿Cómo lo haces?
📖 | I read books and listen to tapes. | Leo libros y escucho cintas.
⭕ | Nobody learns languages that way. | Nadie aprende idiomas de esa manera.
"""

    private const val list28 = """
✉️ | Who types your letters? | ¿Quién mecanografía tus cartas?
👩‍💼 | The general manager's secretary. | La secretaria del director general.
✅ | She types very well. | Escribe muy bien a máquina.
👩‍💼 | That's why she's the general manager's secretary | Es por eso por lo que es la secretaria del director general
👩‍💼 | Don't you have your own secretary? | ¿No tienes tu propia secretaria?
🆘 | I don't have anyone to help me. | No tengo a nadie que me ayude.
🔹 | Do you need anyone? | ¿Necesitas a alguien?
📝 | Of course I do. | Por supuesto que si.
❓ | Then ask for someone. | Entonces pide a alguien.
👌 | It's not that easy. | No es tan fácil.
🚭 | Why does he smoke so much? | ¿Por qué fuma él tanto?
🚭 | Smoking relaxes him. | El fumar le relaja.
🚭 | So he needs to smoke. | Así que necesita fumar.
👥 | He's a very nervous person. | Es una persona muy nerviosa.
😰 | Why is he so nervous? | ¿Por qué es tan nerviosa?
📈 | I suppose it's because he has a lot of worries | Supongo que es porque tiene muchas preocupaciones.
😟 | And why does he worry so much? | ¿Y por qué se preocupa tanto?
📈 | He has a lot of responsibility. | Tiene mucha responsabilidad.
🤔 | Why is that? | ¿Por qué es eso?
👥 | Because a lot of people depend on him. | Porque mucha gente depende de él.
💬 | Don't repeat everything I say. | No repitas todo lo que digo.
💵 | He saves a lot of money. | Él ahorra mucho dinero.
💵 | Do you save a lot of money too? | ¿Ahorras tú mucho dinero también?
🌐 | I spend everything I earn. | Gasto todo lo que gano.
🚫 | I try to save but I can't. | Intento ahorrar pero no puedo.
🤝 | How much does your friend save? | ¿Cuánto ahorra tu amigo?
🔢 | He saves percent of his salary. | Ahorra el por ciento de su sueldo.
💵 | What does he do with the money? | ¿Qué hace con el dinero?
🇺🇸 | He uses it to buy presents. | Lo usa para comprar regalos.
🛍️ | Who does he buy presents for? | ¿Para quién compra regalos?
🌍 | For everyone except you. | Para todo el mundo excepto para ti.
🎁 | Why doesn't he give me anything? | ¿Por qué no me regala nada?
❓ | Because you ask too many questions. | Porque haces demasiadas preguntas.
❓ | What do you want? | ¿Qué quieres?
💳 | When are you going to pay for it? | ¿Cuándo vas a pagarlo?
📍 | Where are you going to keep it? | ¿Dónde lo vas a guardar?
🎁 | When are you going to give it to her? | ¿Cuándo se lo vas a dar a ella?
💍 | Why do you want to give her a ring? | ¿Por qué quieres darle un anillo?
⏱️ | Does she know you want to marry her? | ¿Sabe que quieres casarte con ella?
📆 | How old is she? | ¿Cuántos años tiene ella?
🔬 | Isn't she a little young? | ¿No es un poco joven?
⏱️ | I hope you know what you're doing. | Espero que sepas lo que haces.
👥 | I like to help people. | Me gusta ayudar a la gente.
💪 | You have to make an effort. | Tienes que hacer un esfuerzo.
🚶 | Why do they have to come here? | ¿Por qué tienen que venir aquí?
⏰ | The show's going to last an hour. | El espectáculo va a durar una hora.
👥 | He likes to shout at people. | Le gusta gritar a la gente.
❓ | Is this the only thing you have? | ¿Es esto lo único que tienes?
⏱️ | He knows more than you think | Él sabe más de lo que tú crees.
⭕ | Nobody knows more than I do. | Nadie sabe más que yo.
"""

    private const val list29 = """
⏱️ | I know there's a way to do it. | Sé que hay una manera de hacerlo.
🚪 | Nobody knows why she left. | Nadie sabe por qué ella se fue.
❄️ | Everyone thinks it's going to snow. | Todo el mundo piensa que va a nevar.
📋 | I'm planning to visit Tom soon. | Pienso visitar a Tom pronto.
🥶 | February's a cold month. | Febrero es un mes frío.
❤️‍🩹 | He has health problems. | Él tiene problemas de salud.
❓ | Who doesn't? | ¿Quién no?
🧩 | I don't have problems of any kind. | Yo no tengo problemas de ningún tipo.
🍀 | You're very lucky. | Tienes mucha suerte.
🍀 | It's not a question of luck. | No es una cuestión de suerte.
🍽️ | I never eat too much. | Nunca como demasiado.
🍽️ | I always eat balanced meals. | Siempre como comidas equilibradas.
⏳ | I never spend everything I earn. | Nunca gasto todo lo que gano.
🗓️ | I always save a little each month. | Siempre ahorro un poco cada mes.
⏳ | I don't smoke and I never drink too much. | No fumo y nunca bebo demasiado.
🛏️ | I go to bed at a normal time. | Me acuesto a una hora normal.
🌅 | I do exercises every morning. | Hago ejercicios todas las mañanas.
☕ | I drink very little coffee. | Bebo muy poco café.
😟 | I don't let things worry me. | No dejo que las cosas me preocupen.
💼 | I organise my time at work. | Organizo mi tiempo en el trabajo.
⏱️ | I don't wait until the last minute to do something. | No espero hasta el último momento para hacer algo.
💼 | I don't live in order to work. | No vivo para trabajar.
👨‍👩‍👧 | I spend a lot of time with my family. | Paso mucho tiempo con mi familia.
📅 | I take vitamins every day. | Tomo vitaminas todos los días.
💊 | I don't take medicine unless it's absolutely necessary | No tomo medicinas a no ser que sean absolutamente necesarias
🍀 | I still think you're very lucky. | Todavía creo que tienes mucha suerte.
📌 | Do what I do. | Haz lo que hago.
❌ | Do you ever do anything bad? | ¿Haces alguna vez algo malo?
❌ | I never do anything bad. | Nunca hago nada malo.
🚫 | I don't believe it. | No me lo creo.
⏳ | I never believe what he says. | Yo nunca creo lo que él dice.
💬 | What does he say? | ¿Qué dice?
🤥 | He tells lies. | Dice mentiras.
🛡️ | Are you sure about that? | ¿Estás seguro de eso?
♾️ | I always tell the truth. | Siempre digo la verdad.
⏱️ | I'm going to call him right now. | Le voy a llamar ahora mismo.
📞 | Why do you need to call him? | ¿Por qué necesitas llamarle?
💡 | I need his advice. | Necesito su consejo.
🧩 | Do you have a problem? | ¿Tienes un problema?
🌐 | No, but I want his advice anyway. | No, pero quiero su consejo de todas formas.
💬 | Tell me what's the matter. | Dime lo que pasa.
💵 | I need some money. | Necesito dinero.
💵 | How much do you need? | ¿Cuánto necesitas?
📌 | More than you have. | Más de lo que tú tienes.
🎁 | Who's going to give it to you? | ¿Quién va a dártelo?
🤷 | I don't know yet. | No sé todavía.
🔹 | Are you still looking for someone? | ¿Todavía estás buscando a alguien?
💵 | It's not easy to find money. | No es fácil encontrar dinero.
⏱️ | Does Morgan know anything? | ¿Sabe Morgan algo?
🏁 | He already knows everything. | Ya sabe todo.
"""

    private const val list30 = """
❓ | What are you doing? | ¿Qué estás haciendo?
❓ | How often do you do that? | ¿Con qué frecuencia haces eso?
💡 | Do you understand why I'm looking for him? | ¿Entiendes por qué le estoy buscando?
♾️ | You're always looking for him. | Siempre le estás buscando.
♾️ | It's because I always need to find him. | Es porque siempre necesito encontrarle.
🧑‍🤝‍🧑 | What does you brother need? | ¿Qué es lo que necesita tu hermano?
🗣️ | I'm not talking to you. | No estoy hablando contigo.
👥 | What's the matter (with you)? | ¿Qué te pasa?
🌍 | Everybody wants to know why. | Todo el mundo quiere saber por qué.
1️⃣ | It's a normal reaction. | Es una reacción normal.
❓ | What do you think is going to happen? | ¿Qué crees que va a pasar?
⭕ | Nothing's going to happen. | No va a pasar nada.
🎁 | Don't give me problems. | No me des problemas.
💬 | What do you mean? | ¿Qué quieres decir?
🤔 | Why are you looking at me? | ¿Por qué me estás mirando?
🚶 | Do you think he's going to come? | ¿Crees que él va a venir?
🤷 | I don't know what to think. | No sé qué creer.
🤔 | Why don't they ask us for it? | ¿Por qué no nos lo piden?
👥 | They never ask people for things. | Nunca piden cosas a la gente.
🍷 | They always order wine first. | Siempre piden vino primero.
⭕ | I don't have anything. | No tengo nada.
👌 | That's the one that breaks easily. | Ese es el que se rompe fácilmente.
💼 | It doesn't work. | No funciona.
💼 | These things never work. | Estas cosas nunca funcionan.
💼 | They work if you treat them well. | Funcionan si las tratas bien.
⏰ | What time do you have to be here? | ¿A qué hora tienes que estar aquí?
🪵 | I always have to clean the floor. | Siempre tengo que limpiar el suelo.
💳 | They pay you well, don't they? | Te pagan bien, ¿no?
👎 | But I have the worst jobs. | Pero tengo los peores trabajos.
👥 | I like to meet people like you. | Me gusta conocer a gente como tú.
😊 | I'm glad you like the painting. | Me alegro de que te guste el cuadro.
👍 | I like to paint very much. | Me gusta mucho pintar.
⏱️ | I know he studies very little. | Sé que él estudia muy poco.
📋 | I'm planning to ask him for it. | Pienso pedírselo.
💼 | Doesn't it work well? | ¿No funciona bien?
💡 | Don't they understand you? | ¿No te entienden?
🔨 | Doesn't he try to do anything? | ¿No intenta él hacer nada?
🌧️ | Doesn't it rain in your country? | ¿No llueve en tu país?
⏱️ | He knows you, doesn't he? | Él te conoce, ¿no?
💬 | What does the message say? | ¿Qué dice el mensaje?
❓ | Does this belong to you? | ¿Te pertenece esto?
❓ | Who does it belong to? | ¿A quién pertenece?
⏳ | I never win, I always lose. | Nunca gano, siempre pierdo.
⭕ | Nobody loves me. | Nadie me quiere.
♾️ | Does he always go alone? | ¿Siempre va él solo?
🔨 | That's what he likes to do. | Eso es lo que le gusta hacer.
👍 | What do you like to do? | ¿Qué te gusta a ti hacer?
👍 | I like to do what he does. | Me gusta hacer lo que él hace.
⚠️ | Be careful when you go out with him. | Ten cuidado cuando sales con él.
🌙 | He works during the day and sleeps at night. | Trabaja de día y duerme de noche.
"""
}
