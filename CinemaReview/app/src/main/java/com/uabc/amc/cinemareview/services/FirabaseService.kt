package com.uabc.amc.cinemareview.services

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.uabc.amc.cinemareview.components.MovieFragmentHorizontal
import com.uabc.amc.cinemareview.components.MovieImageFragment

interface FirestoreFirebase {
    fun updateDataFirebaseFirestore()
}

val FIREBASE_AUTH = Firebase.auth

fun FirestoreCollection(collection: String): CollectionReference {
    return FirebaseFirestore.getInstance().collection(collection)
}


fun CREATE_ALL_MOVIES_FIRESTORE() {
    val destacados_del_mes = listOf(
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BYzBhOWU4ODAtZDYzYi00NDU1LWIzZWUtNDZmMDgxODljZTVmXkEyXkFqcGdeQXVyMTAwMzM3NDI3._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BZDc3NjgzZGEtYzhmMi00Y2Q0LTlmMmUtNzZjNTY2ZDk4NWNmXkEyXkFqcGdeQXVyNDAyMjk2NDI@._V1_FMjpg_UX1024_.jpg",
            "1h 53min",
            "Project Power",
            "Action, Crime, Sci-Fi",
            "En las calles de Nueva Orleans, se empieza a correr la voz sobre una misteriosa nueva píldora que desbloquea superpoderes únicos para cada usuario.",
            "0",
            "Henry Joost, Ariel Schulman",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNWY2NWE0NWEtZGUwMC00NWMwLTkyNzUtNmIxMmIyYzA0MjNiXkEyXkFqcGdeQXVyMTA2OTQ3MTUy._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTVlZGM2NWMtNTRkZC00MDRhLTg2MDYtZTNmOWM1NjQ0ZTcwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1024_.jpg",
            "2h 31min",
            "Wonder Woman 1984",
            "Action, Adventure, Fantasy",
            "En los años 80 durante Guerra Fría, Diana Prince se ve involucrada en una disputa con la Unión Soviética. El conflicto la llevará a enfrentarse a Cheeta, un nuevo y espectacular enemigo que pondrá a prueba el poder de la Mujer Maravilla.",
            "0",
            "Patty Jenkins",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BZjNkNzk0ZjEtM2M1ZC00MmMxLTlmOWEtNWRlZTc1ZTUyNzY4XkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_UX280_CR0,0,280,414_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMDU5MDc0NzMtNTU3OC00MzgyLThlY2EtYTYyZGM3Mzg4MTI2XkEyXkFqcGdeQXVyNjY0Mzk3NzA@._V1_FMjpg_UX1280_.jpg",
            "2h 3min",
            "Enola Holmes",
            "Accion, Adventure, Crime",
            "Inglaterra, 1884. La mañana de su 16.º cumpleaños, Enola Holmes se despierta y descubre que su madre ha desaparecido. Le ha dejado unos regalos de lo más curiosos, pero ninguna pista sobre el paradero ni la causa de su marcha.",
            "0",
            "Harry Bradbeer",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNDJiZDliZDAtMjc5Yy00MzVhLThkY2MtNDYwNTQ2ZTM5MDcxXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BYzJlNmI5YTUtNmVhMy00OTdjLTkxODktZjIwNGU4ZjFmNTY4XkEyXkFqcGdeQXVyNzI1NzMxNzM@._V1_FMjpg_UX1024_.jpg",
            "2h 3min",
            "The Old Guard",
            "Action, Adventure, Fantasy",
            "Narra la historia de un pequeño grupo de mercenarios inmortales encubiertos, que deben luchar para mantener unido a su equipo cuando descubren la existencia de un nuevo tipo de ser inmortal.",
            "0",
            "Gina Prince-Bythewood",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BYWVkMWEyMDUtZTVmOC00MTYxLWE1ZTUtNjk4M2IzMjY2OTIxXkEyXkFqcGdeQXVyMDk5Mzc5MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BZDU1MThkM2ItNWUzZC00OWRmLWFhNWEtMDc1N2I5NTJiOGRmXkEyXkFqcGdeQXVyNjgzNTg4NDU@._V1_FMjpg_UX800_.jpg",
            "1h 49min",
            "Monster Problems",
            "Action, Adventure, Comedy",
            "En un mundo infestado de monstruos, Joel descubre que su novia esta a solo 80 millas y descubre a su héroe interior para emprender el peligroso viaje que le conducirá a la chica de sus sueños.",
            "0",
            "Michael Matthews",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMDg4NTQ2ZDgtMzI5Zi00Mzc1LTk0ZWQtZTI5ODhkNWY5NzdlXkEyXkFqcGdeQXVyNjEwNTM2Mzc@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMjliNWYzZTgtZjAxYS00Yjg4LTlmMWMtMjIxYTE0YjhhNDYyXkEyXkFqcGdeQXVyMjAzMjcxNTE@._V1_FMjpg_UX1024_.jpg",
            "50min 11E",
            "Warrior Nun",
            "Action, Drama, Fantasy",
            "Un joven despierta en la morgue con poderes inexplicables y se ve atrapada en una batalla entre el bien y el mal.",
            "0",
            "Simon Barry",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMzQ3NTQxMjItODBjYi00YzUzLWE1NzQtZTBlY2Y2NjZlNzkyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BNWVkMDkyZGQtOGE4NS00MzI5LTk4MTYtMzkyMTI5NDVmYzNhXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1024_.jpg",
            "1h 49min",
            "Birds of Prey: And the Fantabulous Emancipation of One Harley Quinn",
            "Action, Adventure, Comedy",
            "Tras romper cadenas con el Joker, Harley Quinn y otras tres superheroínas - Black Canary, Huntress y Renee Montoya - forman equipo para salvar a la joven Cassandra Cain de un peligroso criminal.",
            "0",
            "Cathy Yan",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BOTdmNTFjNDEtNzg0My00ZjkxLTg1ZDAtZTdkMDc2ZmFiNWQ1XkEyXkFqcGdeQXVyNTAzNzgwNTg@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BY2ZhMjI5MWEtOTVkYi00MjU1LWExOTgtYzIzZWY1YzQyZDM0XkEyXkFqcGdeQXVyNjg2NjQwMDQ@._V1_FMjpg_UX1024_.jpg",
            "1h 59min",
            "1917",
            "Drama, Thriller, War",
            "Dos jóvenes soldados ingleses tienen una misión imposible durante la Primera Guerra Mundial: entregar un mensaje tras las líneas enemigas para evitar que sus compañeros caigan en una trampa.",
            "0",
            "Sam Mendes",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BZDQ2NTdmNDgtMGIwMS00ODE2LTk5M2EtZGZhYzc4MWRlNTU3XkEyXkFqcGdeQXVyNTc4MjczMTM@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BYzEzYWQyMTktN2ZjNi00ZTgyLWJlODItZjc3ZGYzOGM4NTM2XkEyXkFqcGdeQXVyNTYwNjM1MTQ@._V1_FMjpg_UX1280_.jpg",
            "1h 34min",
            "The New Mutants",
            "Action, Horror, Sci-Fi",
            "Cinco jóvenes mutantes, habiendo descubierto sus habilidades en la instalación secreta en que se les retiene contra su voluntad, luchan por escapar de sus crímenes pasados y por salvarse a sí mismos.",
            "0",
            "Josh Boone",
            "id_document",
        ),
    )
    val fans_favorites = listOf(
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BZDhlMzY0ZGItZTcyNS00ZTAxLWIyMmYtZGQ2ODg5OWZiYmJkXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMWQ3MDlmYzktMWRlNS00NGE5LTk5NjItOWY2NGJjYjllMWFjXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_FMjpg_UX1280_.jpg",
            "40min 20E",
            "The Mandalorian",
            "Action, Adventure, Sci-Fi",
            "The Mandalorian se establece después de la caída del Imperio y antes de la aparición de la Primera Orden. La serie sigue la labor de un solitario pistolero en los confines de la galaxia, lejos de la autoridad de la Nueva República.",
            "0",
            "Jon Favreau",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMzFkM2YwOTQtYzk2Mi00N2VlLWE3NTItN2YwNDg1YmY0ZDNmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTgwODY4MzAzNl5BMl5BanBnXkFtZTcwNzMzMTk4NA@@._V1_FMjpg_UX1024_.jpg",
            "1h 43min",
            "Home Alone",
            "Comedy, Family",
            "Un travieso niño de ocho años debe proteger su casa de unos ladrones cuando es accidentalmente olvidado en casa por su familia cuando se van de vacaciones de Navidad.",
            "0",
            "Chris Columbus",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMjhiMzgxZTctNDc1Ni00OTIxLTlhMTYtZTA3ZWFkODRkNmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR5,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMjIyNDE4MDIzMl5BMl5BanBnXkFtZTgwMDA3MDU5NTM@._V1_FMjpg_UX1024_.jpg",
            "49min 62E",
            "Breaking Bad",
            "Crime, Drama, Thriller",
            "Un profesor de instituto diagnosticado con cáncer de pulmón empieza a manufacturar y vender metamfetamina para asegurar el futuro de su familia.",
            "0",
            "Chris Columbus",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BZGUzMWI4ZDktNTEzYi00ZmNiLThhNzItZDkwZDk2NTg5ZGNiXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1280_.jpg",
            "2h 2min",
            "Joker",
            "Crime, Drama, Thriller",
            "En Gotham, Arthur Fleck, un comediante con problemas de salud mental, es marginado y maltratado por la sociedad. Se adentra en una espiral de crimen que resulta revolucionaria. Pronto conoce a su alter-ego, el Joker.",
            "0",
            "Todd Phillips",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMWYwOThjM2ItZGYxNy00NTQwLWFlZWEtM2MzM2Q5MmY3NDU5XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BNjQxZTk4NDctMTYxOC00Nzc3LWFiY2MtMjczNTZmMjExMTZlXkEyXkFqcGdeQXVyNTQ3NzY1NjI@._V1_FMjpg_UX1280_.jpg",
            "1h 36min",
            "Klaus",
            "Animation, Adventure, Comedy",
            "Un simple acto de bondad puede invitar a otro, incluso en lugares lejanos y fríos. Jesper, el nuevo cartero de Smeerensburg, entabla amistad con el juguetero Klaus, derribando un viejo muro.",
            "0",
            "Sergio Pablos, Carlos Martínez López",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTc0MjI0NzI0MV5BMl5BanBnXkFtZTgwMjIyODgxMzE@._V1_FMjpg_UX1280_.jpg",
            "2h 49min",
            "Interstellar",
            "Adventure, Drama, Sci-Fi",
            "Un equipo de exploradores viaja a través de un agujero de gusano en el espacio en un intento de garantizar la supervivencia de la humanidad.",
            "0",
            "Christopher Nolan",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNGEyOGJiNWEtMTgwMi00ODU4LTlkMjItZWI4NjFmMzgxZGY2XkEyXkFqcGdeQXVyNjcyNjcyMzQ@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BNzhiYjc3OTgtYjRjYy00NTk4LTg0NDAtNzAwNzFiZmUxZjZjXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1280_.jpg",
            "1h 18E",
            "The Boys",
            "Action, Comedy, Crime",
            "Una historia de acción centrada en un escuadrón de la CIA con la misión de mantener a los superhéroes a raya a cualquier precio.",
            "0",
            "Eric Kripke",
            "id_document",
        ),
    )
    val familiar = listOf(
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNDliY2E1MjUtNzZkOS00MzJlLTgyOGEtZDg4MTI1NzZkMTBhXkEyXkFqcGdeQXVyNjMwMzc3MjE@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTU2OTZjYmItYjc5OS00MDlmLThkNTQtNGYxM2NlZWI1YWQ0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1024_.jpg",
            "1h 55min",
            "Mulan",
            "Action, Adventure, Drama",
            "Una joven doncella china se disfraza de guerrero para salvar a su padre.",
            "0",
            "Niki Caro",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNWNiNTczNzEtMjQyZC00MjFmLTkzMDMtODk4ZGMyZmE0N2E4XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTY2MzM3NzM2Nl5BMl5BanBnXkFtZTgwMjI0NjQzNDM@._V1_FMjpg_UX1024_.jpg",
            "1h 44min",
            "How the Grinch Stole Christmas",
            "Comedy, Family, Fantasy",
            "A las afueras de Whoville vive un Grinch verde sediento de venganza que planea arruinar las Navidades para todos los habitantes de la ciudad.",
            "0",
            "Ron Howard",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNGU1NGNiYzYtMTQ2Ni00M2ZlLTg0N2QtMDFhMzNjNzcyMGYyXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BOGI1OTM4YzctNmVlNi00MDVmLTk4NzctZDI4OTY0MjU1MGM4XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1024_.jpg",
            "1h 35min",
            "The Croods: A New Age",
            "Animation, Adventure, Comedy",
            "La prehistórica familia de los Croods se enfrenta a una familia rival que afirma ser mejor y más evolucionada.",
            "0",
            "Joel Crawford",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMjAyNjA3MzMxNV5BMl5BanBnXkFtZTYwOTc0NTg3._V1_FMjpg_UX500_.jpg",
            "2h 32min",
            "Harry Potter and the Sorcerer's Stone",
            "Adventure, Family, Fantasy",
            "Un niño huérfano es aceptado en una escuela de hechicería donde aprende la verdad sobre sí mismo, su familia y el terrible mal que acecha al mundo mágico.",
            "0",
            "Chris Columbus",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMmJlYzViNzctMjQ1Ni00ZWQ4LThkN2YtMzI2ZGU5Nzk0NTAyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTUzMTIyMjEwNl5BMl5BanBnXkFtZTgwODI0MzczNDM@._V1_FMjpg_UX1024_.jpg",
            "1h 29min",
            "Jingle All the Way",
            "Adventure, Comedy, Family",
            "Un padre promete comprarle a su hijo un Turbo Man por Navidad, pero parece que se ha acabado en todas las tiendas, y él no es el único que intenta hacerse con uno.",
            "0",
            "Brian Levant",
            "id_document",
        ),
        MovieImageFragment(
            "id_movie",
            "https://m.media-amazon.com/images/M/MV5BMTI4ODQ4MzgzMl5BMl5BanBnXkFtZTcwODc5NTUzMQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTczNTkyNTYzOF5BMl5BanBnXkFtZTcwNTczMjIyNw@@._V1_FMjpg_UX1280_.jpg",
            "1h 37min",
            "The Santa Clause 3: The Escape Clause",
            "Adventure, Comedy, Family",
            "Santa, alias Scott Calvin, se enfrenta a una doble tarea: cómo mantener a su nueva familia feliz y cómo evitar que Jack Frost se haga cargo de la Navidad.",
            "0",
            "Michael Lembeck",
            "id_document",
        ),
    )

    val categories = listOf(
        MovieFragmentHorizontal("Destacados del mes", destacados_del_mes),
        MovieFragmentHorizontal("Favoritos de los fans", fans_favorites),
        MovieFragmentHorizontal("Para toda la familia", familiar),
    )


    // todo No modificar de aqui en adelante
    categories.forEach {
        val idDocument = FirestoreCollection("categories").document().id
        FirestoreCollection("categories").document(idDocument).set(hashMapOf("name" to it.title))

        it.movies.forEach { movieImageFragment ->
            val idMovie = FirestoreCollection("categories").document(idDocument).collection("movies").document().id
            movieImageFragment.id = idMovie
            movieImageFragment.idDocument = idDocument

            val movie = hashMapOf(
                "categories" to movieImageFragment.categories,
                "cover" to movieImageFragment.image,
                "image" to movieImageFragment.bannerImage,
                "director" to movieImageFragment.director,
                "duration" to movieImageFragment.duration,
                "name" to movieImageFragment.name,
                "sinopsis" to movieImageFragment.sinopsis,
                "stars" to movieImageFragment.stars
            )

            FirestoreCollection("categories").document(idDocument).collection("movies").document(idMovie).set(movie)
                .addOnCompleteListener {
                    val tempMovie = hashMapOf(
                        "collection" to idDocument,
                        "categories" to movieImageFragment.categories,
                        "cover" to movieImageFragment.image,
                        "image" to movieImageFragment.bannerImage,
                        "director" to movieImageFragment.director,
                        "duration" to movieImageFragment.duration,
                        "name" to movieImageFragment.name,
                        "sinopsis" to movieImageFragment.sinopsis,
                        "stars" to movieImageFragment.stars,
                        "document" to idMovie
                    )
                    FirestoreCollection("movies").document(idMovie).set(tempMovie)
            }
        }
    }
}
