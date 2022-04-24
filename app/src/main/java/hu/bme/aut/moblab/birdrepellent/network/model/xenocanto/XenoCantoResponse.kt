package hu.bme.aut.moblab.birdrepellent.network.model.xenocanto

data class XenoCantoSound (
    val id: String?,
    val en: String?, //the English name of the species
    val cnt: String?, //the country where the recording was made
    val url: String?, //the URL specifying the details of this recording
    val file: String?, //the URL to the audio file
    val q: String?, //the current quality rating for the recording
    val length: String?, //the length of the recording in minutes
)

data class XenoCantoResponse (
    val numRecordings: String?, //the total number of recordings found for this query
    val numSpecies: String?, //the total number of species found for this query
    val page: Int?, //the page number of the results page that is being displayed
    val numPages: Int?, //the total number of pages available for this query
    val recordings: List<XenoCantoSound>? //an array of recording objects
)