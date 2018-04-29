import java.io.File
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun main(args: Array<String>) {

    val productsInputStream = File("/Users/magnus.lejonlid/dev/private/kotlinbaby/out/production/kotlinbaby/Products.txt").inputStream()
    val products = mutableListOf<Product>()

    productsInputStream.bufferedReader().useLines { lines ->
        lines.drop(1) //Skip header row
                .forEach { line ->
                    val parts = line.split(';')
                    products.add(Product(parts[0], parts[1], parts[2], LocalDate.parse(parts[3], dateFormatter), parts[4], parts[5], parts[6]))
                }
    }

    val productsMetaInputStream = File("/Users/magnus.lejonlid/dev/private/kotlinbaby/out/production/kotlinbaby/ProductsMeta.txt").inputStream()
    productsMetaInputStream.bufferedReader().useLines { lines ->
        lines.drop(1) //Skip header row
                .forEach { line ->
                    val parts = line.split(";")
                    val match = products.find { p -> p.productId == parts[0] }
                    if (match != null) {
                        match.weight = parts[1].replace(",", ".")
                        match.material = parts[2]
                        match.color = parts[3]
                        match.leadTime = parts[4]
                    }
                }
    }



    products.forEach { p -> println(p) }


    val currencyGroups = products
            .filter { p -> p.currency != "USD" && p.material != "Steel" && p.createdOn.dayOfWeek != DayOfWeek.SATURDAY && p.createdOn.dayOfWeek != DayOfWeek.SUNDAY }
            .groupBy { p -> p.currency }

    currencyGroups.forEach { c ->
        output(c.key, c.value
                .sortedBy { p -> p.price }
        )
    }

}

fun output(currency: String, products: List<Product>) {
    File(String.format("Products-%s.csv", currency)).bufferedWriter().use { out ->
        products.forEach { p ->
            val joiner = StringJoiner(";")
            joiner.add(p.productId)
            joiner.add(p.name)
            joiner.add(p.partNo)
            joiner.add(dateFormatter.format(p.createdOn))
            joiner.add(p.price)
            joiner.add(p.weight)
            joiner.add(p.material)
            joiner.add(p.color)
            joiner.add(p.leadTime)
            joiner.add(p.leadTime)
            out.write(joiner.toString())
        }
    }
}