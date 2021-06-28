package com.appheathycook2.Controller;

import com.appheathycook2.Entity.Chef;
import com.appheathycook2.Service.IChefService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/chefs")
@Api(tags = "Chef",value = "Service Web RESTFul de Chefs")
public class ChefController {

    @Autowired
    private IChefService chefService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Chefs",notes = "Métodos para listr todos todos los chefs")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Chefs encontrados"),
            @ApiResponse(code = 404, message = "Chefs no encontrados")
    })
    public ResponseEntity<List<Chef>> findAll(){
        try {
            List<Chef> chefs = chefService.getAll();
            if (chefs.size() > 0)
                return new ResponseEntity<List<Chef>>(chefs, HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Chef por Id", notes = "Métodos para encontrar un Chef por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Chef encontrado"),
            @ApiResponse(code = 404, message = "Chef no encontrado")
    })
    public ResponseEntity<Chef> findById(@PathVariable("id") Long id) {
        try {
            Optional<Chef> chef = chefService.getById(id);
            if (!chef.isPresent())
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Chef>(chef.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "searchByDni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Chef por DNI", notes = "Métodos para encontrar un Chef por su respectivo DNI")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer encontrado"),
            @ApiResponse(code = 404, message = "Customer no encontrado")
    })
    public ResponseEntity<Chef> findByDni(@PathVariable("dni") String dni) {
        try {
            Chef chef = chefService.findByDni(dni);
            if (chef == null)
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Chef>(chef, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("searchByLastname/{lastname}")
    @ApiOperation(value = "Buscar Chef por lastname", notes = "Métodos para encontrar un Chef por su respectivo lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Chef encontrados"),
            @ApiResponse(code = 404, message = "Chef no encontrados")
    })
    public ResponseEntity<List<Chef>> findByLastName(@PathVariable("lastname") String lastname) {
        try {
            List<Chef> chefs = chefService.findByLastname(lastname);
            if (chefs.size() > 0)
                return new ResponseEntity<List<Chef>>(chefs, HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("searchByFirstname/{firstname}")
    @ApiOperation(value = "Buscar Chef por firstname", notes = "Métodos para encontrar un Chef por su respectivo firstname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Chef encontrados"),
            @ApiResponse(code = 404, message = "Chef no encontrados")
    })
    public ResponseEntity<List<Chef>> findByFirstName(@PathVariable("firstname") String firstname) {
        try {
            List<Chef> chefs = chefService.findByFirstname(firstname);
            if (chefs.size() > 0)
                return new ResponseEntity<List<Chef>>(chefs, HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "searchByFirstnameAndLastname/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Chef por firstname y lastname", notes = "Métodos para encontrar un Chef por su respectivo firstname y lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Chef encontrados"),
            @ApiResponse(code = 404, message = "Chef no encontrados")
    })
    public ResponseEntity<List<Chef>> findByFirstnameAndLastname(
            @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname
    ) {
        try {
            List<Chef> chefs = chefService.findByFirstnameAndLastname(firstname, lastname);
            if (chefs.size() > 0)
                return new ResponseEntity<List<Chef>>(chefs, HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Chef", notes = "Método que registra Chefs en BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Chef creado"),
            @ApiResponse(code = 404, message = "Chef no creado")
    })
    public ResponseEntity<Chef> insertCustomer(@Valid @RequestBody Chef chef) {
        try {
            Chef chefNew = chefService.save(chef);
            return ResponseEntity.status(HttpStatus.CREATED).body(chefNew);
        } catch (Exception e) {
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Chefs", notes = "Metodo que actualiza los datos de Chefs")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Chef actualizados"),
            @ApiResponse(code = 404, message = "Chef no encontrado")
    })
    public ResponseEntity<Chef> updateCustomer(
            @PathVariable("id") Long id, @Valid @RequestBody Chef chef) {
        try {
            Optional<Chef> customerUp = chefService.getById(id);
            if (!customerUp.isPresent())
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            chef.setId(id);
            chefService.save(chef);
            return new ResponseEntity<Chef>(chef, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Chefs", notes = "Metodo que elimina los datos de Chefs")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Chef eliminados"),
            @ApiResponse(code = 404, message = "Chef no encontrado")
    })
    public ResponseEntity<Chef> deleteCustomer(@PathVariable("id") Long id) {
        try {
            Optional<Chef> customerDelete = chefService.getById(id);
            if (!customerDelete.isPresent())
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            chefService.delete(id);
            return new ResponseEntity<Chef>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
