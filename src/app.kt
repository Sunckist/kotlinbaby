import java.io.File

fun main(args: Array<String>) {

    val productsInputStream = File("/Users/magnus.lejonlid/dev/kotlinbaby/out/production/kotlinbaby/Products.txt").inputStream()
    val products = mutableListOf<Product>()

    productsInputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(';')
            products.add(Product(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6] ))
        }
    }

    val productsMetaInputStream = File("/Users/magnus.lejonlid/dev/kotlinbaby/out/production/kotlinbaby/ProductsMeta.txt").inputStream()
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
}