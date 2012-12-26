<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>

<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	</head>
	<body>
        <ui:navbar fixed="top">
            <ui:navbarCollapsedElements>
                <ui:menu>
                    <ui:menuItem name="Inicio" icon="${ui.iconApplicationHome()}" url="${createLinkTo(base: "/")}"/>
                    <ui:menuItem name="Artículo">
                        <ui:menuItem name="Comprado" controller="articuloComprado"/>
                        <ui:menuItem name="Elaborado" controller="articuloElaborado"/>
                        <ui:menuItem name="Ingrediente" controller="ingrediente"/>
                        <ui:menuItem name="Lugar de preparación" controller="lugarDePreparacion"/>
                        <ui:menuItem name="Marca" controller="marca"/>
                        <ui:menuItem name="Presentación" controller="presentacion"/>
                        <ui:menuItem name="Rubro" controller="rubro"/>
                        <ui:menuItem name="Subrubro" controller="subrubro"/>
                        <ui:menuItem name="Tipo de Artículo" controller="tipoDeArticulo"/>
                        <ui:menuItem name="Unidad de Medida" controller="unidadDeMedida"/>
                    </ui:menuItem>
                    <ui:menuItem name="Caja">
                        <ui:menuItem name="Caja" controller="caja"/>
                        <ui:menuItem name="Cierre de caja" controller="cierreCaja"/>
                        <ui:menuItem name="Cobro" controller="cobro"/>
                        <ui:menuItem name="Detalle de cobro" controller="detalleDeCobro"/>
                        <ui:menuItem name="Método de pago" controller="metodoDePago"/>
                    </ui:menuItem>
                    <ui:menuItem name="Carta">
                        <ui:menuItem name="Carta" controller="carta"/>
                        <ui:menuItem name="Categoría" controller="categoria"/>
                        <ui:menuItem name="Detalle de Artículo" controller="detalleDeArticulo"/>
                    </ui:menuItem>
                    <ui:menuItem name="Cliente" controller="cliente"/>
                    <ui:menuItem name="General">
                        <ui:menuItem name="Condición de IVA" controller="condicionIVA"/>
                        <ui:menuItem name="Mail" controller="mail"/>
                        <ui:menuItem name="Restaurante" controller="restaurante"/>
                        <ui:menuItem name="Tipo de Documento" controller="tipoDeDocumento"/>
                        <ui:menuItem name="Tipo de Teléfono" controller="tipoDeTelefono"/>
                    </ui:menuItem>
                    <ui:menuItem name="Ubicación">
                        <ui:menuItem name="Localidad" controller="localidad"/>
                        <ui:menuItem name="Provincia" controller="provincia"/>
                        <ui:menuItem name="País" controller="pais"/>
                    </ui:menuItem>
                    <ui:menuItem name="Empleado">
                        <ui:menuItem name="Empleado" controller="empleado"/>
                        <ui:menuItem name="Cargo" controller="cargo"/>
                    </ui:menuItem>
                    <ui:menuItem name="Pedido">
                        <ui:menuItem name="Pedido de salón" controller="pedidoDeSalon"/>
                        <ui:menuItem name="Pedido para delivery" controller="pedidoParaDelivery"/>
                        <ui:menuItem name="Mesa" controller="mesa"/>
                        <ui:menuItem name="Tipo de descuento" controller="tipoDeDescuento"/>
                    </ui:menuItem>
                    <ui:menuItem name="Persona" controller="persona"/>
                    <ui:menuItem name="Proveedor" controller="proveedor"/>
                    <ui:menuItem name="Reserva" controller="reserva"/>
                    <ui:menuItem name="Stock">
                        <ui:menuItem name="Stock" controller="stock"/>
                        <ui:menuItem name="Lote" controller="lote"/>
                        <ui:menuItem name="Subtipo Motivo" controller="subtipoMotivo"/>
                        <ui:menuItem name="Tipo Motivo" controller="tipoMotivo"/>
                    </ui:menuItem>
                </ui:menu>
            </ui:navbarCollapsedElements>
        </ui:navbar>
	</body>
</html>
