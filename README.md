# 🍹 CocktAPP 🍸

Discover the world of drinks with the CocktAPP! 🌍🍹 CocktAPP is built with Jetpack Compose, the modern UI 🔄 toolkit for building native Android applications. 

🔍 Search and explore a vast collection of cocktail recipes right at your fingertips. From classic concoctions to trendy mixes, find the perfect drink for any occasion.

➕ Create and customize your own cocktail recipes with ease. Mix and match ingredients, share your unique creations, and become the master mixologist among your friends! 🍹👨‍🍳

🌐 Share your favorite recipes with friends and spread the joy. Whether it's a refreshing cocktail or a sophisticated one, the CocktAPP is your passport to the world of delicious beverages. Cheers to unforgettable moments and endless flavor possibilities! 🥂🎉

## Dependencies
- Firebase: for authenticaiton and storing data
- Retrofit: Type-safe HTTP client for Android and Java.
- Dagger Hilt: Dependency injection library for Android.

## Git workflow

### Branches

In order to start developing, please create your own branch:
`git checkout -b "<type>/<branch-name>"`

- Type: feature, fix, build, chore, ci, docs, style, refactor, test, db
- Name: dash-case

ex. feature/login-page

### Commits

Please use following commits name convention:
`<type>: commit name`

- Type: feature, fix, build, chore, ci, docs, style, refactor, test, db
- Name: lowercase

ex. feature: add login button

### Pull request

Please use following pull request name convention:
`<Type>: commit name`

- Type: Feature, Fix, Build, Chore, CI, Docs, Style, Refactor, Test, Db
- Name: lowercase

ex. Feature: add login page
<br>
Additionally list in pull request description main changes.

### Merging

⚠ Use squash and merge ⚠

## Project structure

```
├── cocktapp                  <- Main app package
│   ├── activites             <- Package starting activites to FindBar or ShareCocktail
│   ├── components            <- Components shared across multiple screens  
│   ├── di                    <- Providing a singleton instance of the APIs
│   ├── model                 <- Models for database
│   ├── navigation            <- Avaliable screens and navController
│   ├── network               <- API definition
│   ├── repository            <- Repositories that use APIs
│   ├── screens               <- Views
│   |   ├── login             <- Login related feature
│   |   |   ├── LoginScreen.kt
│   |   |   ├── LoginScreenViewModel.kt
│   ├── utils                 <- Const variables for app
│   ├── wrappers              <- Wrapper for requests
|   CocktailApplication.kt
|   MainActivity.kt
```
<br>
