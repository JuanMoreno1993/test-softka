function fn() {
  // Ambiente: se puede cambiar con -Dkarate.env=<ambiente>
  var env = karate.env || 'qa';
  karate.log('Ambiente de ejecución:', env);

  var config = {
    env: env,
    baseUrl: 'https://petstore.swagger.io/v2'
  };

  // Tiempos de espera (ms)
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 20000);

  // Reintentos para "retry until": la API pública responde con consistencia eventual
  karate.configure('retry', { count: 10, interval: 1500 });

  // Request y response formateados en el log y en el reporte HTML
  karate.configure('logPrettyRequest', true);
  karate.configure('logPrettyResponse', true);

  return config;
}
