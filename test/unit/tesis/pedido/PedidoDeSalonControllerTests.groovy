
package tesis.pedido



import org.junit.*
import grails.test.mixin.*

@TestFor(PedidoDeSalonController)
@Mock(PedidoDeSalon)
class PedidoDeSalonControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pedidoDeSalon/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pedidoDeSalonInstanceList.size() == 0
        assert model.pedidoDeSalonInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.pedidoDeSalonInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pedidoDeSalonInstance != null
        assert view == '/pedidoDeSalon/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pedidoDeSalon/show/1'
        assert controller.flash.message != null
        assert PedidoDeSalon.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pedidoDeSalon/list'


        populateValidParams(params)
        def pedidoDeSalon = new PedidoDeSalon(params)

        assert pedidoDeSalon.save() != null

        params.id = pedidoDeSalon.id

        def model = controller.show()

        assert model.pedidoDeSalonInstance == pedidoDeSalon
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pedidoDeSalon/list'


        populateValidParams(params)
        def pedidoDeSalon = new PedidoDeSalon(params)

        assert pedidoDeSalon.save() != null

        params.id = pedidoDeSalon.id

        def model = controller.edit()

        assert model.pedidoDeSalonInstance == pedidoDeSalon
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pedidoDeSalon/list'

        response.reset()


        populateValidParams(params)
        def pedidoDeSalon = new PedidoDeSalon(params)

        assert pedidoDeSalon.save() != null

        // test invalid parameters in update
        params.id = pedidoDeSalon.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pedidoDeSalon/edit"
        assert model.pedidoDeSalonInstance != null

        pedidoDeSalon.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pedidoDeSalon/show/$pedidoDeSalon.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pedidoDeSalon.clearErrors()

        populateValidParams(params)
        params.id = pedidoDeSalon.id
        params.version = -1
        controller.update()

        assert view == "/pedidoDeSalon/edit"
        assert model.pedidoDeSalonInstance != null
        assert model.pedidoDeSalonInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pedidoDeSalon/list'

        response.reset()

        populateValidParams(params)
        def pedidoDeSalon = new PedidoDeSalon(params)

        assert pedidoDeSalon.save() != null
        assert PedidoDeSalon.count() == 1

        params.id = pedidoDeSalon.id

        controller.delete()

        assert PedidoDeSalon.count() == 0
        assert PedidoDeSalon.get(pedidoDeSalon.id) == null
        assert response.redirectedUrl == '/pedidoDeSalon/list'
    }
}
