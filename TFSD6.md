# TFSD TODO 6 : Document, license, publish, maintain your software

### Declare a FOSS LICENSE
Following this website https://choosealicense.com/, we opted for the most permissive one, the MIT License. See the LICENSE.txt on GitHub.

### Write a small but canonical README file 
See the README.md on GitHub.

### Add technical documentation to the code (see slides for what is a technical documentation)
We added a maven plugin that generates Javacode. We can generate the java code by adding Javadoc comment strings beggining by `/** My doc comment */`
above each fields and methods, and then run this command that generates html pages at `target/site/apidocs`

```
mvn javadoc:javadoc
```

See the documentation more in details on GitHub : https://github.com/Almiinh/flashcard_webapp/
 
### Manually create a release on your code hosting platform
We set the tag, the release title and description. Github takes charges of delivering .zip and .tar.gz compressed archives of the source code.
See here : example of https://github.com/Almiinh/flashcard_webapp/releases/tag/v0.0.1

### Write and publish a small but canonical documentation (see slides for what a canonical documentation contains in addition to just technical documentation)
See the README.md on GitHub. 

