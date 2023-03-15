# Nim Game
Simple, console-based Nim game programmed in Java. For educational purposes.

## Usage
Tried my hardest to use `make` for compilation. Realized I was trying to drive screws using a hammer and accepted my fate.
This package uses ant for compilation.
Compile:
```bash
ant
```

Use:
```bash
java -cp bin/ nim.Main [pvp|pvc]
```

## Packaging
All classes can be found in the package `nim`. I refuse to use a fully-qualified package name like
`edu.miriamexe.nim` simply because of the horrid directory structure that ensues.

## Code style
This project uses the Google Java style, which can be found here: 
https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml

## Tests
Unit tests are available in directory `src/tests/`.
Compile:
```bash
ant tests
```

Use:
```bash
java -cp bin/ tests.NimTests
```

`TestUtils` are based on the testing framework by [denkspuren](https://github.com/denkspuren/): https://gist.github.com/denkspuren/c379cd6d4512144e595d1dab98bba5ff (CC BY-NC-SA)

I put the methods found there into a static class, in order for them to be useful in a full java project. 

## Copyright
This project is licensed under [CC BY-NC-SA](https://creativecommons.org/licenses/by-nc-sa/3.0/).
