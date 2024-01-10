# TODO 8 : Integrate and deploy your software continuously

### 1. Automate the execution of unit tests on every commit

This workflow [Run Unit Tests](.github/workflows/unit-tests.yml) triggers at every push:
- The `actions/setup-java@v2` action is used to set up a Java environment.
- The Maven build tool is used to compile the project and run tests using `mvn package` then `mvn test`


### 2. When a tag is created and the tests pass: automate the creation of a release (GitHub) or a Package (Gitlab), where you will publish your artifacts: code, documentation, sources


The workflow [Create Release](.github/workflows/create-release.yml)  triggers on tag pushes that match the pattern v*, which means any tag that starts with v.

- Jobs:
    - `build-and-test`: This job checks out the code, sets up Java, and runs Maven's verify phase, which includes running tests.
    - `create-release`: This job depends on the successful completion of `build-and-test`. It creates a release and uploads specified artifacts.
- Artifacts:
    - This is where we specify artifacts to show at each release

### 3. In the release, automatically generate the changelog based on the git history since the last tag

We insert a simple action in the [Create Release](.github/workflows/create-release.yml) workflow

```yaml
- name: "Build Changelog"
  id: build_changelog
  uses: mikepenz/release-changelog-builder-action@{latest-release}
```
After action execution it will return the changelog and additional information as step output. You can use it in any follow-up step by referencing the output by referencing it via the id of the step. For example build_changelog.

```yaml
    name: Create Release
    ...
    with:
      body: ${{steps.build_changelog.outputs.changelog}}
```

### 4. On the master branch only: automate the publication of your documentation with GitHub pages 

In the Github repository **settings**, we set up the Github Pages to look into `/docs/`

The workflow [Deploy Documentation](./.github/workflows/deploy-docs.yml) is triggered on pushes to the `main` branch.

- It checks out the repository and builds the documentation (you'll need to modify these steps to fit your documentation setup).
- It moves docs from `target/site/apidocs/` to `docs/`
