class Product(
        val productId: String,
        val name: String,
        val partNo: String,
        val createdOn: String,
        val key: String,
        val price: String,
        val currency: String
) {
    var weight: String? = null
    var material: String? = null
    var color: String? = null
    var leadTime: String? = null

    override fun toString(): String {
        return "Product(productId='$productId', name='$name', partNo='$partNo', createdOn='$createdOn', key='$key', price='$price', currency='$currency', weight='$weight', material='$material', color='$color', leadTime='$leadTime')"
    }
}