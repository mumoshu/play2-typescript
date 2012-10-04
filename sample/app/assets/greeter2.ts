declare module "./sayings" {
    export class Greeter {
        public greeting: string;
        constructor (message: string);
        public greet(): string;
    }
}

import Sayings = module("./sayings");
var greeter = new Sayings.Greeter("world");
document.body.innerHTML = greeter.greet();
