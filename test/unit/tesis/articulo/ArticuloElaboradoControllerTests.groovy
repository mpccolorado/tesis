
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(ArticuloElaboradoController)
@Mock(ArticuloElaborado)
class ArticuloElaboradoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/articuloElaborado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.articuloElaboradoInstanceList.size() == 0
        assert model.articuloElaboradoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.articuloElaboradoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.articuloElaboradoInstance != null
        assert view == '/articuloElaborado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/articuloElaborado/show/1'
        assert controller.flash.message != null
        assert ArticuloElaborado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/articuloElaborado/list'


        populateValidParams(params)
        def articuloElaborado = new ArticuloElaborado(params)

        assert articuloElaborado.save() != null

        params.id = articuloElaborado.id

        def model = controller.show()

        assert model.articuloElaboradoInstance == articuloElaborado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/articuloElaborado/list'


        populateValidParams(params)
        def articuloElaborado = new ArticuloElaborado(params)

        assert articuloElaborado.save() != null

        params.id = articuloElaborado.id

        def model = controller.edit()

        assert model.articuloElaboradoInstance == articuloElaborado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/articuloElaborado/list'

        response.reset()


        populateValidParams(params)
        def articuloElaborado = new ArticuloElaborado(params)

        assert articuloElaborado.save() != null

        // test invalid parameters in update
        params.id = articuloElaborado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/articuloElaborado/edit"
        assert model.articuloElaboradoInstance != null

        articuloElaborado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/articuloElaborado/show/$articuloElaborado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        articuloElaborado.clearErrors()

        populateValidParams(params)
        params.id = articuloElaborado.id
        params.version = -1
        controller.update()

        assert view == "/articuloElaborado/edit"
        assert model.articuloElaboradoInstance != null
        assert model.articuloElaboradoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/articuloElaborado/list'

        response.reset()

        populateValidParams(params)
        def articuloElaborado = new ArticuloElaborado(params)

        assert articuloElaborado.save() != null
        assert ArticuloElaborado.count() == 1

        params.id = articuloElaborado.id

        controller.delete()

        assert ArticuloElaborado.count() == 0
        assert ArticuloElaborado.get(articuloElaborado.id) == null
        assert response.redirectedUrl == '/articuloElaborado/list'
    }
}
