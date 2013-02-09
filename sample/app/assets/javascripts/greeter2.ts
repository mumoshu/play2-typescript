
import sayings = module("./sayings")

var greeter = new sayings.sayings_module.Greeter("world123");
document.body.innerHTML = greeter.greet();
