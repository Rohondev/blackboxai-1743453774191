package com.mitraandsons.naturalwellness

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val detailedDescription: String,
    val ingredients: String,
    val imageUrl: String
) {
    companion object {
        fun getSampleProducts(): List<Product> {
            return listOf(
                Product(
                    1,
                    "Nonivita",
                    29.99,
                    "Pure noni extract for vitality",
                    "Nonivita is a premium noni fruit extract packed with antioxidants and nutrients to support your overall wellbeing.",
                    "Ingredients: 100% Pure Noni Fruit Extract, Natural Preservatives",
                    "https://images.pexels.com/photos/719528/pexels-photo-719528.jpeg"
                ),
                Product(
                    2,
                    "Nonivita Capsule",
                    34.99,
                    "Convenient noni supplement",
                    "Nonivita Capsules provide all the benefits of noni in an easy-to-take capsule form.",
                    "Ingredients: Noni Fruit Powder, Vegetarian Capsule, Rice Flour",
                    "https://images.pexels.com/photos/5946082/pexels-photo-5946082.jpeg"
                ),
                Product(
                    3,
                    "Noni Soap",
                    12.99,
                    "Nourishing natural soap",
                    "Our Noni Soap gently cleanses while delivering the antioxidant benefits of noni to your skin.",
                    "Ingredients: Organic Noni Extract, Coconut Oil, Olive Oil, Shea Butter",
                    "https://images.pexels.com/photos/4041392/pexels-photo-4041392.jpeg"
                )
            )
        }
    }
}