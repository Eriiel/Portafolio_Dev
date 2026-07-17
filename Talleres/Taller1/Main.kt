fun main() {

    var nombre = ""
    var apellido = ""
    var edad = 0
    var genero = ""
    var opcion = 0

    do {
        println("------ EAST COAST P.A. ------")
        println("1. Registrar pasajero")
        println("2. Realizar compra del boleto")
        println("3. Salir")
        print("Seleccione una opcion: ")

        try {
            opcion = readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            println("Opcion no valida, ingrese un numero")
            continue
        }

        if (opcion == 1) {

            print("Ingrese su nombre: ")
            val entradaNombre = readLine()!!.trim()
            try {
                if (entradaNombre.isEmpty()) throw Exception("El nombre no puede estar vacio")
                if (entradaNombre.toDoubleOrNull() != null) throw Exception("El nombre no puede ser un numero")
                nombre = entradaNombre
            } catch (e: Exception) {
                println(e.message)
                continue
            }

            print("Ingrese su apellido: ")
            val entradaApellido = readLine()!!.trim()
            try {
                if (entradaApellido.isEmpty()) throw Exception("El apellido no puede estar vacio")
                if (entradaApellido.toDoubleOrNull() != null) throw Exception("El apellido no puede ser un numero")
                apellido = entradaApellido
            } catch (e: Exception) {
                println(e.message)
                continue
            }

            print("Ingrese su edad: ")
            try {
                val entradaEdad = readLine()!!.trim()
                if (entradaEdad.isEmpty()) throw Exception("La edad no puede estar vacia")
                edad = entradaEdad.toInt()
                if (edad <= 0 || edad > 120) throw Exception("La edad debe estar entre 1 y 120")
            } catch (e: NumberFormatException) {
                println("La edad debe ser un numero entero")
                continue
            } catch (e: Exception) {
                println(e.message)
                continue
            }

            print("Ingrese su genero (M/F): ")
            val entradaGenero = readLine()!!.trim().uppercase()
            try {
                if (entradaGenero.isEmpty()) throw Exception("El genero no puede estar vacio")
                if (entradaGenero != "M" && entradaGenero != "F") throw Exception("El genero solo puede ser M o F")
                genero = entradaGenero
            } catch (e: Exception) {
                println(e.message)
                continue
            }

            val nombreCompleto = "$nombre $apellido"
            println("Pasajero registrado: $nombreCompleto")

        } else if (opcion == 2) {

            if (nombre == "" || apellido == "") {
                println("Debe registrar un pasajero primero (opcion 1)")
            } else {

                print("Tipo de pago (efectivo, visa, clave, cheque, transferencia): ")
                val tipoPago = readLine()!!.trim()
                try {
                    if (tipoPago.isEmpty()) throw Exception("El tipo de pago no puede estar vacio")
                    if (tipoPago.toDoubleOrNull() != null) throw Exception("El tipo de pago no puede ser un numero")
                } catch (e: Exception) {
                    println(e.message)
                    continue
                }

                val precioBase = 20.00
                var descuento = 0.0

                if (edad < 12) {
                    descuento = precioBase * 0.05
                } else if (genero == "F" && edad > 57) {
                    descuento = precioBase * 0.15
                } else if (genero == "M" && edad > 62) {
                    descuento = precioBase * 0.15
                }

                val total = precioBase - descuento

                println()
                println("------ EAST COAST P.A. ------")
                println("RUC: 01-2531-4507")
                println("TERMINAL PRINCIPAL")
                println("-----------------------------")
                println("CLIENTE : $nombre $apellido")
                println("EDAD    : $edad")
                println("COSTO   : B/ $total")
                println("PAGO    : $tipoPago")
                println("-----------------------------")
                println("        BUEN VIAJE!")
                println("-----------------------------")
                println()
            }

        } else if (opcion == 3) {
            println("Saliendo del sistema...")
        } else {
            println("Opcion no valida, elija entre 1 y 3")
        }

    } while (opcion != 3)

}
