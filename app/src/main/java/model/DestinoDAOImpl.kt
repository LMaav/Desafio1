package model

class DestinoDAOImpl {
    companion object {
        private val destinos = mutableListOf<Destino>()

        init {
            destinos.add(Destino("Paris", "França", "https://www.civitatis.com/br/paris/visita-guiada-paris?aid=100&cmp=pt_BR_Nonbrand&cmpint=_FreeTours_Paris_RSA_5320&gclsrc=aw.ds&gad_source=1&gad_campaignid=19697383411&gclid=CjwKCAjwspPOBhB9EiwATFbi5CHxa8VWSbmq8LTlFldUkfijpFhVm9nVH-QC3bHR9w_4ISS4oj5MBhoC8rQQAvD_BwE"))
            destinos.add(Destino("Tóquio", "Japão", "https://www.tourradar.com/d/japan?utm_source=google&utm_medium=cpc&utm_campaignid=21079892343&utm_campaign=ROW+%7C+Asia+%7C+Japan&gclsrc=aw.ds&gad_source=1&gad_campaignid=21079892343&gclid=CjwKCAjwspPOBhB9EiwATFbi5IxTck7eoAr8X7K1X_H8wiTR9rg4PR04Z8kBuzvXwFir6BHGggnl6hoCEncQAvD_BwE"))
            destinos.add(Destino("Santos", "Brasil", "https://www.turismosantos.com.br/pt-br"))
        }
    }

    fun getDestinos(): List<Destino> {
        return destinos
    }

    fun postDestino(destino: Destino) {
        destinos.add(destino)
    }

    fun excluirDestino(posicao: Int) {
        if (posicao in destinos.indices) {
            destinos.removeAt(posicao)
        }
    }
}