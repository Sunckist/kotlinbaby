import java.io.File
import java.util.*

fun main(args: Array<String>) {

    val productsInputStream = File("/Users/magnus.lejonlid/dev/private/kotlinbaby/out/production/kotlinbaby/Products.txt").inputStream()
    val products = mutableListOf<Product>()

    productsInputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(';')
            products.add(Product(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6] ))
        }
    }

    val productsMetaInputStream = File("/Users/magnus.lejonlid/dev/private/kotlinbaby/out/production/kotlinbaby/ProductsMeta.txt").inputStream()
    productsMetaInputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(";")
            val match = products.find { p -> p.productId == parts[0] }
            if (match != null) {
                match.weight = parts[1]
                match.material = parts[2]
                match.color = parts[3]
                match.leadTime = parts[4]
            }
        }
    }





    products.forEach { p -> println(p) }

    output(products)
}

fun output(products: List<Product>) {
    File("output.csv").bufferedWriter().use { out ->
        products.forEach { p ->
            val joiner = StringJoiner(";")
            joiner.add(p.productId)
            joiner.add(p.name)
            joiner.add(p.partNo)
            joiner.add(p.createdOn)
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