import java.time.LocalDate

class Product(
        val productId: String,
        val name: String,
        val partNo: String,
        val createdOn: LocalDate,
        val key: String,
        val price: String,
        val currency: String
) {
    var weight: String = ""
    var material: String = ""
    var color: String = ""
    var leadTime: String = ""

    override fun toString(): String {
        return "Product(productId='$productId', name='$name', partNo='$partNo', createdOn='$createdOn', key='$key', price='$price', currency='$currency', weight='$weight', material='$material', color='$color', leadTime='$leadTime')"
    }
}