package com.loaizasoftware.phrasalverbshero.data.local

import com.loaizasoftware.phrasalverbshero.domain.model.Example
import com.loaizasoftware.phrasalverbshero.domain.model.Definition
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.model.Verb

class DummyData {

    companion object {
        private val instance = DummyData()
        fun getVerbs() = instance.listVerbs
    }

    private val verbs = arrayListOf(
        Verb(
            1, "Take", arrayListOf(
                PhrasalVerb(
                    1, "Take off", arrayListOf(
                        Definition(
                            1, "To remove something (clothes, etc.)", arrayListOf(
                                Example(
                                    1,
                                    "He took off his jacket.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        ),
                        Definition(
                            2, "To leave the ground and start flying", arrayListOf(
                                Example(
                                    2,
                                    "The plane took off at noon.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    2, "Take in", arrayListOf(
                        Definition(
                            3, "To absorb information", arrayListOf(
                                Example(
                                    3,
                                    "I couldn’t take in what he was saying.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            2, "Go", arrayListOf(
                PhrasalVerb(
                    3, "Go on", arrayListOf(
                        Definition(
                            4, "To continue happening", arrayListOf(
                                Example(
                                    4,
                                    "The meeting went on for hours.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    4, "Go over", arrayListOf(
                        Definition(
                            5, "To review or check something", arrayListOf(
                                Example(
                                    5,
                                    "Let’s go over your report.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            3, "Bring", arrayListOf(
                PhrasalVerb(
                    5, "Bring up", arrayListOf(
                        Definition(
                            6, "To mention a topic", arrayListOf(
                                Example(
                                    6,
                                    "She brought up an interesting point.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            4, "Put", arrayListOf(
                PhrasalVerb(
                    6, "Put off", arrayListOf(
                        Definition(
                            7, "To delay or postpone", arrayListOf(
                                Example(
                                    7,
                                    "The meeting was put off until next week.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    7, "Put up with", arrayListOf(
                        Definition(
                            8, "To tolerate something unpleasant", arrayListOf(
                                Example(
                                    8,
                                    "I can't put up with his behavior anymore.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            5, "Look", arrayListOf(
                PhrasalVerb(
                    8, "Look after", arrayListOf(
                        Definition(
                            9, "To take care of someone or something", arrayListOf(
                                Example(
                                    9,
                                    "She looks after her younger brother.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    9, "Look forward to", arrayListOf(
                        Definition(
                            10, "To anticipate something with excitement", arrayListOf(
                                Example(
                                    10,
                                    "I look forward to our next trip.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            6, "Give", arrayListOf(
                PhrasalVerb(
                    10, "Give up", arrayListOf(
                        Definition(
                            11, "To stop trying", arrayListOf(
                                Example(
                                    11,
                                    "Never give up on your dreams.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            7, "Run", arrayListOf(
                PhrasalVerb(
                    11, "Run out of", arrayListOf(
                        Definition(
                            12, "To have no more of something", arrayListOf(
                                Example(
                                    12,
                                    "We ran out of milk.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            8, "Break", arrayListOf(
                PhrasalVerb(
                    12, "Break down", arrayListOf(
                        Definition(
                            13, "To stop functioning (a machine)", arrayListOf(
                                Example(
                                    13,
                                    "My car broke down on the highway.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        ),
                        Definition(
                            14, "To become emotionally upset", arrayListOf(
                                Example(
                                    14,
                                    "She broke down in tears.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            9, "Turn", arrayListOf(
                PhrasalVerb(
                    13, "Turn up", arrayListOf(
                        Definition(
                            15, "To appear unexpectedly", arrayListOf(
                                Example(
                                    15,
                                    "He turned up late to the party.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            10, "Set", arrayListOf(
                PhrasalVerb(
                    14, "Set up", arrayListOf(
                        Definition(
                            16, "To arrange or establish something", arrayListOf(
                                Example(
                                    16,
                                    "They set up a new business.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    private val verbs2 = arrayListOf(
        Verb(
            1, "Take", arrayListOf(
                PhrasalVerb(
                    1, "Take off", arrayListOf(
                        Definition(
                            1, "To remove something (clothes, etc.)", arrayListOf(
                                Example(
                                    1,
                                    "He took off his jacket.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        ),
                        Definition(
                            2, "To leave the ground and start flying", arrayListOf(
                                Example(
                                    2,
                                    "The plane took off at noon.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    2, "Take in", arrayListOf(
                        Definition(
                            3, "To absorb information", arrayListOf(
                                Example(
                                    3,
                                    "I couldn’t take in what he was saying.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            2, "Go", arrayListOf(
                PhrasalVerb(
                    3, "Go on", arrayListOf(
                        Definition(
                            4, "To continue happening", arrayListOf(
                                Example(
                                    4,
                                    "The meeting went on for hours.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    4, "Go over", arrayListOf(
                        Definition(
                            5, "To review or check something", arrayListOf(
                                Example(
                                    5,
                                    "Let’s go over your report.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            3, "Bring", arrayListOf(
                PhrasalVerb(
                    5, "Bring up", arrayListOf(
                        Definition(
                            6, "To mention a topic", arrayListOf(
                                Example(
                                    6,
                                    "She brought up an interesting point.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            4, "Put", arrayListOf(
                PhrasalVerb(
                    6, "Put off", arrayListOf(
                        Definition(
                            7, "To delay or postpone", arrayListOf(
                                Example(
                                    7,
                                    "The meeting was put off until next week.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    7, "Put up with", arrayListOf(
                        Definition(
                            8, "To tolerate something unpleasant", arrayListOf(
                                Example(
                                    8,
                                    "I can't put up with his behavior anymore.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            5, "Look", arrayListOf(
                PhrasalVerb(
                    8, "Look after", arrayListOf(
                        Definition(
                            9, "To take care of someone or something", arrayListOf(
                                Example(
                                    9,
                                    "She looks after her younger brother.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                ),
                PhrasalVerb(
                    9, "Look forward to", arrayListOf(
                        Definition(
                            10, "To anticipate something with excitement", arrayListOf(
                                Example(
                                    10,
                                    "I look forward to our next trip.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            6, "Give", arrayListOf(
                PhrasalVerb(
                    10, "Give up", arrayListOf(
                        Definition(
                            11, "To stop trying", arrayListOf(
                                Example(
                                    11,
                                    "Never give up on your dreams.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            7, "Run", arrayListOf(
                PhrasalVerb(
                    11, "Run out of", arrayListOf(
                        Definition(
                            12, "To have no more of something", arrayListOf(
                                Example(
                                    12,
                                    "We ran out of milk.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            8, "Break", arrayListOf(
                PhrasalVerb(
                    12, "Break down", arrayListOf(
                        Definition(
                            13, "To stop functioning (a machine)", arrayListOf(
                                Example(
                                    13,
                                    "My car broke down on the highway.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        ),
                        Definition(
                            14, "To become emotionally upset", arrayListOf(
                                Example(
                                    14,
                                    "She broke down in tears.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            9, "Turn", arrayListOf(
                PhrasalVerb(
                    13, "Turn up", arrayListOf(
                        Definition(
                            15, "To appear unexpectedly", arrayListOf(
                                Example(
                                    15,
                                    "He turned up late to the party.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        ),

        Verb(
            10, "Set", arrayListOf(
                PhrasalVerb(
                    14, "Set up", arrayListOf(
                        Definition(
                            16, "To arrange or establish something", arrayListOf(
                                Example(
                                    16,
                                    "They set up a new business.",
                                    "https://img.freepik.com/free-vector/cute-koala-standing-cartoon-vector-icon-illustration-animal-nature-icon-isolated-flat-vector_138676-10956.jpg?semt=ais_hybrid"
                                )
                            )
                        )
                    )
                )
            )
        )
    )

    private val listVerbs = verbs + verbs2


}