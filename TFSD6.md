# TFSD TODO 6 : Document, license, publish, maintain your software

### 1. Declare a FOSS LICENSE
Following this website https://choosealicense.com/, we opted for the most permissive one, the MIT License. See the See [LICENSE](LICENSE.txt). on GitHub.

### 2. Write a small but canonical README file 
See the README.md on GitHub.

### 3. Add technical documentation to the code (see slides for what is a technical documentation)
We focused only on the Java backend, since we don't know about javascript documentation yet, and due to lack of time.

We can generate the java code by adding Javadoc comment strings beggining by `/** My doc comment */` above each fields and methods.

Afterwards, we run this command that generates html pages at `target/site/apidocs` that uses a maven plugin that generates Javadoc HTML pages.

```
mvn javadoc:javadoc
```

See the documentation more in details here : https://github.com/Almiinh/flashcard_webapp/target/site/apidocs/
 
### 4. Manually create a release on your code hosting platform
We set the tag, the release title and description. Github takes charges of delivering .zip and .tar.gz compressed archives of the source code.
See this tag [v0.0.1](https://github.com/Almiinh/flashcard_webapp/releases/tag/v0.0.1)

### 5. Write and publish a small but canonical documentation (see slides for what a canonical documentation contains in addition to just technical documentation)
See the [README.md](README.md)

