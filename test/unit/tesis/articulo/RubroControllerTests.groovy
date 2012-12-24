
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(RubroController)
@Mock(Rubro)
class RubroControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rubro/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rubroInstanceList.size() == 0
        assert model.rubroInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.rubroInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rubroInstance != null
        assert view == '/rubro/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rubro/show/1'
        assert controller.flash.message != null
        assert Rubro.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rubro/list'


        populateValidParams(params)
        def rubro = new Rubro(params)

        assert rubro.save() != null

        params.id = rubro.id

        def model = controller.show()

        assert model.rubroInstance == rubro
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rubro/list'


        populateValidParams(params)
        def rubro = new Rubro(params)

        assert rubro.save() != null

        params.id = rubro.id

        def model = controller.edit()

        assert model.rubroInstance == rubro
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rubro/list'

        response.reset()


        populateValidParams(params)
        def rubro = new Rubro(params)

        assert rubro.save() != null

        // test invalid parameters in update
        params.id = rubro.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rubro/edit"
        assert model.rubroInstance != null

        rubro.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rubro/show/$rubro.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rubro.clearErrors()

        populateValidParams(params)
        params.id = rubro.id
        params.version = -1
        controller.update()

        assert view == "/rubro/edit"
        assert model.rubroInstance != null
        assert model.rubroInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rubro/list'

        response.reset()

        populateValidParams(params)
        def rubro = new Rubro(params)

        assert rubro.save() != null
        assert Rubro.count() == 1

        params.id = rubro.id

        controller.delete()

        assert Rubro.count() == 0
        assert Rubro.get(rubro.id) == null
        assert response.redirectedUrl == '/rubro/list'
    }
}
