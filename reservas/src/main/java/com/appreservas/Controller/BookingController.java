package com.appreservas.Controller;

import com.appreservas.Entity.Booking;
import com.appreservas.Entity.Customer;
import com.appreservas.Service.IBookingService;
import com.appreservas.Service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@Api(tags = "Booking", value = "Service Web RESTFul de Bookings")
public class BookingController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IBookingService bookingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Bookings",notes = "Método para listar todos los bookings")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Bookings encontrados"),
            @ApiResponse(code = 404,message = "Bookings no encontrados")
    })
    public ResponseEntity<List<Booking>>findAllBooking(){
        try {
            List<Booking>bookings= bookingService.getAll();
            if(bookings.size()>0)
                return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
            else
                return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Booking por Id",notes = "Método para listar un booking por Id")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Bookings encontrados"),
            @ApiResponse(code = 404,message = "Bookings no encontrados")
    })
    public ResponseEntity<Booking>findBookingById(@PathVariable("id")Long id){
        try{
            Optional<Booking>booking=bookingService.getById(id);
            if(!booking.isPresent())
                return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Booking>(booking.get(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //@GetMapping(value = "/searchBetweenDates/{checking_date}/{checkout_date}",produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/searchBetweenDates",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Booking entre fechas",notes = "Método para listar un booking entre fechas")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Bookings encontrados"),
            @ApiResponse(code = 404,message = "Bookings no encontrados")
    })
    public ResponseEntity<List<Booking>> findBookingBetweenDates(@RequestParam("checking_date") String checking_string,
                                                                 @RequestParam("checkout_date") String checkout_string){
        try {
            Date checking_date=ParseDate(checking_string);
            Date checkout_date=ParseDate(checkout_string);
            List<Booking>bookings=bookingService.find(checking_date,checkout_date);
            if (bookings.size()>0)
                return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
            else
                return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){

        }
        return result;
    }

    @PostMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Booking de un Customers", notes = "Método que registra un booking")
    @ApiResponses({
            @ApiResponse(code=201,message = "Booking creado"),
            @ApiResponse(code=404, message = "Booking no creado")
    })
    public ResponseEntity<Booking> insertBooking(@PathVariable("id")Long id,@Valid @RequestBody Booking booking){
        try {
            Optional<Customer>customer=customerService.getById(id);
            if(customer.isPresent()){
                booking.setCustomer(customer.get());
                Booking bookingNew=bookingService.save(booking);
                return ResponseEntity.status(HttpStatus.CREATED).body(bookingNew);
            }
            else
                return new ResponseEntity<Booking>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Booking",notes = "Metodo que actualiza los datos de Booking")
    @ApiResponses({
            @ApiResponse(code=201, message = "Datos de Booking actualizados"),
            @ApiResponse(code=404,message = "Booking no encontrado")
    })
    public ResponseEntity<Booking> updateBooking(@PathVariable("id")Long id,@Valid @RequestBody Booking booking){
        try {
            Optional<Booking>bookingOld=bookingService.getById(id);
            if(!bookingOld.isPresent())
                return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
            else{
                booking.setId(id);
                bookingService.save(booking);
                return new ResponseEntity<Booking>(booking,HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Booking por Id",notes = "Metodo que elimina los datos de un Booking")
    @ApiResponses({
            @ApiResponse(code=201, message = "Datos de Booking eliminados"),
            @ApiResponse(code=404,message = "Booking no encontrado")
    })
    public ResponseEntity<Booking> deleteBooking(@PathVariable("id")Long id){
        try {
            Optional<Booking> bookingDelete=bookingService.getById(id);
            if(bookingDelete.isPresent()){
                bookingService.delete(id);
                return new ResponseEntity<Booking>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<Booking>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
