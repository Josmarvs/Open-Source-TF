package com.appheathycook2.Controller;


import com.appheathycook2.Entity.Country;
import com.appheathycook2.Service.ICountryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/countries")
@Api(tags = "Country",value = "Service Web RESTFul de Countries")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Countrys", notes = "Métodos para listr todos todos los countrys")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Countrys encontrados"),
            @ApiResponse(code = 404, message = "Countrys no encontrados")
    })
    public ResponseEntity<List<Country>> findAll() {
        try {
            List<Country> countrys = countryService.getAll();
            if (countrys.size() > 0)
                return new ResponseEntity<List<Country>>(countrys, HttpStatus.OK);
            else
                return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            return new ResponseEntity<List<Country>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Country por Id", notes = "Métodos para encontrar un country por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Country encontrado"),
            @ApiResponse(code = 404, message = "Country no encontrado")
    })
    public ResponseEntity<Country> findById(@PathVariable("id") Long id) {
        try {
            Optional<Country> country = countryService.getById(id);
            if (!country.isPresent())
                return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Country>(country.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Country>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByFirstname/{name}")
    @ApiOperation(value = "Buscar Country por namecountry", notes = "Métodos para encontrar un Country por su respectivo nombre")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Country encontrados"),
            @ApiResponse(code = 404, message = "Country no encontrados")
    })
    public ResponseEntity<List<Country>> findByCountryName(@PathVariable("name") String name) {
        try {
            List<Country> countries = countryService.findByCountryName(name);
            if (countries.size() > 0)
                return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
            else
                return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Country>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Countrys", notes = "Método que registra countrys en BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Country creado"),
            @ApiResponse(code = 404, message = "Country no creado")
    })
    public ResponseEntity<Country> insertCountry(@Valid @RequestBody Country country) {
        try {
            Country countryNew = countryService.save(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(countryNew);
        } catch (Exception e) {
            return new ResponseEntity<Country>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de countrys", notes = "Metodo que actualiza los datos de Countrys")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Country actualizados"),
            @ApiResponse(code = 404, message = "Country no encontrado")
    })
    public ResponseEntity<Country> updateCountry(
            @PathVariable("id") Long id, @Valid @RequestBody Country country) {
        try {
            Optional<Country> countryUp = countryService.getById(id);
            if (!countryUp.isPresent())
                return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
            country.setId(id);
            countryService.save(country);
            return new ResponseEntity<Country>(country, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Country>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de countrys", notes = "Metodo que elimina los datos de Countrys")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Country eliminados"),
            @ApiResponse(code = 404, message = "Country no encontrado")
    })
    public ResponseEntity<Country> deleteCountry(@PathVariable("id") Long id) {
        try {
            Optional<Country> countryDelete = countryService.getById(id);
            if (!countryDelete.isPresent())
                return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
            countryService.delete(id);
            return new ResponseEntity<Country>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Country>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
