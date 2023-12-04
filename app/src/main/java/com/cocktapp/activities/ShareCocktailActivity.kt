package com.cocktapp.activities

import android.content.Context
import android.content.Intent
import com.cocktapp.model.Cocktail

class ShareCocktailActivity {
    companion object {
        fun shareRecipe(context: Context, cocktail: Cocktail) {
            val cocktailText = """
 ${cocktail.name}

 Ingredients:
 ${cocktail.ingredients.joinToString(separator = "\n- ")}

 Preparation:
 ${cocktail.instructions}
 """.trimIndent()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, cocktailText)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
    }

}