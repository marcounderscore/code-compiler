import tornadofx.*

class Interface: View() {
    override val root = vbox {
        button("Press me")
        label("Waiting")
    }
}