import api.kudago.HttpClient
import api.kudago.KudaGoClient
import api.kudago.KudaGoManager

/**
 * Created by xmn on 09.04.2017.
 */

fun main(args: Array<String>) {
    val kudaGoManager = KudaGoManager(KudaGoClient(HttpClient()))
    kudaGoManager.printFilms()
}