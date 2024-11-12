# VSC-ET

A VS Code Extension Template. Or an automated [VS Code](https://code.visualstudio.com) **extension development** workflow, with support for [coding](#coding), [building](#building), [testing](#testing), [end-to-end testing](#end-to-end-testing), and [publishing the extension](#publishing-the-extension) to the [VS Code Marketplace](https://marketplace.visualstudio.com/vscode) and the [Open VSX Registry](https://open-vsx.org/).

![alt text](assets/vsc-et-published.png)
The workflow is powered by [Interactive Programming](https://en.wikipedia.org/wiki/Interactive_programming). You can use the extension while you are hacking on it, without reloading the extension host. The feedback is instantaneous. As it should be to keep coding productive and maximum fun.

## Who is this for?

It is probably for you. 😄 Especially if you would like to build an extension for VS Code. Also if:

- You want to explore what it takes to publish an extension to the VS Code Marketplace in an automated manner.
- Like to have the plumbing for testing, including end-to-end testing, in place from the get-go, ready for you to fill in as you build your extension
- You would like to learn Clojure/ClojureScript in a fun way
- Interactive Programming is your thing
- You think that Interactive Programming might be your thing

## What are the prerequisites?

The tools you need to get started are:

- [VS Code](https://code.visualstudio.com/)
  - The [Calva](https://calva.io) extension
  - The [Joyride](https://github.com/BetterThanTomorrow/joyride) extension
- [Node.js](https://nodejs.org/)
- [Java](https://jdk.java.net/23/)
- [Clojure CLI](https://clojure.org/guides/install_clojure)
- [Babashka](https://github.com/babashka/babashka#installation)

Add your curiosity and you should be good to go. To just try the template/workflow out, you do not need to know any ClojureScript (or even programming). When you start to try make the extension do something interesting, you will need to know _some_ Clojure/ClojureScript. Learning Clojure will be a fun endeavor, I promise.

## How do I get started?

TBD...

## Who built this?

My name is Peter Strömberg, a.k.a. [PEZ](https://github.com/PEZ). I love Clojure and VS Code, and I love to build things. I created Calva, an extension for working with Clojure in VS Code. I also co-created Joyride, a scripting environment for VS Code that lets you extend the editor in user space (i.e. without creating an extension). I've also created Paste Replace, and a whole bunch of Joyride scripts, big and small (mostly small).

I created this template because I want to share what I have figured out about building and maintaining extensions for VS Code. To me it is the most fun and rewarding way to spend my time, and I want you to have as much fun as I get. 😄 Calva is mostly built with TypeScript, and I'd like to contribute to you building your extensions in a more civilized programming language.

That said, the workflow and automation used in the template are used for Calva, as well as with my pure ClojureScript extensions, so if you find the rest of the automation in this template useful, you can most definitely use it with a TypeScript tool chain.

## License

MIT

Free to use, modify and redistribute as you wish. 🍻🗽

## Sponsor my open source work ♥️

That, said, you are welcome to show me you like my work using this link:

* https://github.com/sponsors/PEZ
