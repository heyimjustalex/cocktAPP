# CocktAPP

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
