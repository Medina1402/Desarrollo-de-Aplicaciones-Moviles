package com.uabc.amc.workout

class Workout(private val name: String, private val description: String) {
    companion object {
        var Workouts: Array<Workout> = arrayOf(
            Workout("The Limb Loosener", "5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            Workout("Core Agony", "100 Pulls-ups\n100 push-ups\n100 Sit-ups\n100 Squats"),
            Workout("The Wimp Special", "5 Pulls-ups\n10 Push-ups\n15 Squats"),
            Workout("Strength and Length", "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
        )
    }

    fun getDescription(): String {
        return this.description
    }

    fun getName(): String {
        return this.name
    }

    override fun toString(): String {
        return this.name
    }

}
