package com.example.data.seed

import com.example.data.model.Flashcard
import com.example.data.model.FlashcardStatus

object InitialCards {
    fun getBaseCards(): List<Flashcard> {
        return listOf(
            // ==========================================
            // 1. Everyday & Social (5 words)
            // ==========================================
            Flashcard(
                english = "stubborn",
                spanish = "terco / testarudo",
                phonetic = "/ˈstʌb.ərn/",
                definition = "Unwilling to change one's mind or posture despite good reasons.",
                example = "He is very stubborn and refuses to apologize.",
                exampleTranslation = "Él es muy terco y se niega a disculparse.",
                type = "Word",
                category = "Everyday & Social",
                cefrLevel = "B1",
                emoji = "😤",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "awkward",
                spanish = "incómodo / extraño",
                phonetic = "/ˈɑː.kwɚd/",
                definition = "Causing or feeling embarrassment or a lack of ease.",
                example = "There was an awkward silence after his comment.",
                exampleTranslation = "Hubo un silencio incómodo después de su comentario.",
                type = "Word",
                category = "Everyday & Social",
                cefrLevel = "B1",
                emoji = "😬",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "freedom",
                spanish = "libertad",
                phonetic = "/ˈfriː.dəm/",
                definition = "The power or right to act, speak, or think without restraint.",
                example = "Education gives people the freedom to choose their future.",
                exampleTranslation = "La educación le da a la gente la libertad de elegir su futuro.",
                type = "Word",
                category = "Everyday & Social",
                cefrLevel = "A2",
                emoji = "🕊️",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "resilient",
                spanish = "resiliente / tenaz",
                phonetic = "/rɪˈzɪl.jənt/",
                definition = "Able to withstand or recover quickly from difficult conditions.",
                example = "Children are often remarkably resilient in hard times.",
                exampleTranslation = "Los niños suelen ser notablemente resilientes en tiempos difíciles.",
                type = "Word",
                category = "Everyday & Social",
                cefrLevel = "B2",
                emoji = "🌱",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "genuine",
                spanish = "auténtico / sincero",
                phonetic = "/ˈdʒen.ju.ɪn/",
                definition = "Truly what something is said to be; authentic and sincere.",
                example = "She had a genuine smile that warmed everyone.",
                exampleTranslation = "Ella tenía una sonrisa sincera que conmovía a todos.",
                type = "Word",
                category = "Everyday & Social",
                cefrLevel = "B1",
                emoji = "✨",
                status = FlashcardStatus.NEW.name
            ),

            // ==========================================
            // 2. Travel & Places (5 words)
            // ==========================================
            Flashcard(
                english = "wanderlust",
                spanish = "pasión por viajar / espíritu viajero",
                phonetic = "/ˈwɑːn.dɚ.lʌst/",
                definition = "A strong desire to travel and explore the world.",
                example = "Her wanderlust led her to explore over thirty countries.",
                exampleTranslation = "Su pasión por viajar la llevó a explorar más de treinta países.",
                type = "Word",
                category = "Travel & Places",
                cefrLevel = "B2",
                emoji = "✈️",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "breathtaking",
                spanish = "impresionante / que quita el aliento",
                phonetic = "/ˈbreθˌteɪ.kɪŋ/",
                definition = "Astonishing or awe-inspiring in quality or beauty.",
                example = "The view from the top of the mountain was breathtaking.",
                exampleTranslation = "La vista desde la cima de la montaña era impresionante.",
                type = "Word",
                category = "Travel & Places",
                cefrLevel = "B1",
                emoji = "🏔️",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "layover",
                spanish = "escala / parada de viaje",
                phonetic = "/ˈleɪˌoʊ.vɚ/",
                definition = "A period of rest or waiting before a further stage in a journey.",
                example = "We had a four-hour layover in Paris before our flight.",
                exampleTranslation = "Tuvimos una escala de cuatro horas en París antes de nuestro vuelo.",
                type = "Word",
                category = "Travel & Places",
                cefrLevel = "A2",
                emoji = "🧳",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "sightseeing",
                spanish = "hacer turismo / visitas turísticas",
                phonetic = "/ˈsaɪtˌsiː.ɪŋ/",
                definition = "The activity of visiting places of interest in a city or area.",
                example = "We spent our entire afternoon sightseeing around Rome.",
                exampleTranslation = "Pasamos toda la tarde haciendo turismo por Roma.",
                type = "Word",
                category = "Travel & Places",
                cefrLevel = "A2",
                emoji = "🏛️",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "itinerary",
                spanish = "itinerario / plan de viaje",
                phonetic = "/aɪˈtɪn.ə.rer.i/",
                definition = "A planned route or journey with scheduled events and places.",
                example = "Our itinerary includes three days in Tokyo and two in Kyoto.",
                exampleTranslation = "Nuestro itinerario incluye tres días en Tokio y dos en Kioto.",
                type = "Word",
                category = "Travel & Places",
                cefrLevel = "B1",
                emoji = "🗺️",
                status = FlashcardStatus.NEW.name
            ),

            // ==========================================
            // 3. Work & Business (5 words)
            // ==========================================
            Flashcard(
                english = "deadline",
                spanish = "fecha límite / plazo de entrega",
                phonetic = "/ˈded.laɪn/",
                definition = "The latest time or date by which something should be completed.",
                example = "We worked overtime to meet the project deadline on Friday.",
                exampleTranslation = "Trabajamos horas extras para cumplir con la fecha límite del proyecto el viernes.",
                type = "Word",
                category = "Work & Business",
                cefrLevel = "A2",
                emoji = "⏰",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "reliable",
                spanish = "confiable / de fiar",
                phonetic = "/rɪˈlaɪ.ə.bəl/",
                definition = "Consistently good in quality or performance; able to be trusted.",
                example = "She is the most reliable member on our team.",
                exampleTranslation = "Ella es el miembro más confiable de nuestro equipo.",
                type = "Word",
                category = "Work & Business",
                cefrLevel = "B1",
                emoji = "🤝",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "brainstorm",
                spanish = "hacer lluvia de ideas / idear",
                phonetic = "/ˈbreɪn.stɔːrm/",
                definition = "To produce an idea or way of solving a problem by spontaneous thinking.",
                example = "Let's brainstorm some creative marketing solutions together.",
                exampleTranslation = "Tengamos una lluvia de ideas para buscar soluciones creativas de marketing juntos.",
                type = "Word",
                category = "Work & Business",
                cefrLevel = "B1",
                emoji = "💡",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "milestone",
                spanish = "hito / logro clave",
                phonetic = "/ˈmaɪl.stoʊn/",
                definition = "A significant stage or event in the development of something.",
                example = "Launching the app was a major milestone for our company.",
                exampleTranslation = "El lanzamiento de la aplicación fue un hito importante para nuestra empresa.",
                type = "Word",
                category = "Work & Business",
                cefrLevel = "B2",
                emoji = "🚩",
                status = FlashcardStatus.NEW.name
            ),
            Flashcard(
                english = "feedback",
                spanish = "retroalimentación / comentarios",
                phonetic = "/ˈfiːd.bæk/",
                definition = "Information about reactions to a product or a person's performance.",
                example = "Constructive feedback helps us improve our daily workflow.",
                exampleTranslation = "La retroalimentación constructiva nos ayuda a mejorar nuestro flujo de trabajo diario.",
                type = "Word",
                category = "Work & Business",
                cefrLevel = "B1",
                emoji = "📊",
                status = FlashcardStatus.NEW.name
            )
        )
    }

    fun getPreloadedCards(): List<Flashcard> {
        return BasicsBooklet1.getAllCards() +
            BasicsBooklet2.getAllCards() +
            BasicsBooklet3.getAllCards() +
            BasicsBooklet4.getAllCards() +
            BasicsBooklet5.getAllCards() +
            BasicsBooklet6.getAllCards() +
            getBaseCards() + VocabularyBooklet1.getAllCards() + VocabularyBooklet2.getAllCards() + VocabularyBooklet3.getAllCards() + VocabularyBooklet4.getAllCards() + VocabularyBooklet5.getAllCards() + VocabularyBooklet6.getCards() +
            TranslationsSentences1.getAllCards() +
            TranslationsSentences2.getAllCards() +
            TranslationsSentences3.getAllCards() +
            TranslationsSentences4.getAllCards() +
            TranslationsSentences5.getAllCards() +
            TranslationsSentences6.getAllCards() +
            TranslationsSentences7.getAllCards() +
            TranslationsSentencesA.getAllCards() +
            TranslationsSentencesB.getAllCards() +
            TranslationsSentencesC.getAllCards()
    }
}
