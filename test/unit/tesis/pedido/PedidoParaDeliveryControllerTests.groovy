
package tesis.pedido



import org.junit.*
import grails.test.mixin.*

@TestFor(PedidoParaDeliveryController)
@Mock(PedidoParaDelivery)
class PedidoParaDeliveryControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pedidoParaDelivery/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pedidoParaDeliveryInstanceList.size() == 0
        assert model.pedidoParaDeliveryInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.pedidoParaDeliveryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pedidoParaDeliveryInstance != null
        assert view == '/pedidoParaDelivery/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pedidoParaDelivery/show/1'
        assert controller.flash.message != null
        assert PedidoParaDelivery.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pedidoParaDelivery/list'


        populateValidParams(params)
        def pedidoParaDelivery = new PedidoParaDelivery(params)

        assert pedidoParaDelivery.save() != null

        params.id = pedidoParaDelivery.id

        def model = controller.show()

        assert model.pedidoParaDeliveryInstance == pedidoParaDelivery
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pedidoParaDelivery/list'


        populateValidParams(params)
        def pedidoParaDelivery = new PedidoParaDelivery(params)

        assert pedidoParaDelivery.save() != null

        params.id = pedidoParaDelivery.id

        def model = controller.edit()

        assert model.pedidoParaDeliveryInstance == pedidoParaDelivery
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pedidoParaDelivery/list'

        response.reset()


        populateValidParams(params)
        def pedidoParaDelivery = new PedidoParaDelivery(params)

        assert pedidoParaDelivery.save() != null

        // test invalid parameters in update
        params.id = pedidoParaDelivery.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pedidoParaDelivery/edit"
        assert model.pedidoParaDeliveryInstance != null

        pedidoParaDelivery.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pedidoParaDelivery/show/$pedidoParaDelivery.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pedidoParaDelivery.clearErrors()

        populateValidParams(params)
        params.id = pedidoParaDelivery.id
        params.version = -1
        controller.update()

        assert view == "/pedidoParaDelivery/edit"
        assert model.pedidoParaDeliveryInstance != null
        assert model.pedidoParaDeliveryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pedidoParaDelivery/list'

        response.reset()

        populateValidParams(params)
        def pedidoParaDelivery = new PedidoParaDelivery(params)

        assert pedidoParaDelivery.save() != null
        assert PedidoParaDelivery.count() == 1

        params.id = pedidoParaDelivery.id

        controller.delete()

        assert PedidoParaDelivery.count() == 0
        assert PedidoParaDelivery.get(pedidoParaDelivery.id) == null
        assert response.redirectedUrl == '/pedidoParaDelivery/list'
    }
}
