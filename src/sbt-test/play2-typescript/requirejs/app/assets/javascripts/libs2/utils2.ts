import utils = module("libs/utils");
export module Text {
    export class Add {
        apply(a, b) {
            return new utils.Text.Add().apply(a, b);
        }
    }
}

