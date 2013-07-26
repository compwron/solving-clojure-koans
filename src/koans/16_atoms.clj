(def atomic-clock (atom 0))

(meditations
  "Atoms are like refs"
  (= 0 @atomic-clock)

  "You can change at the swap meet"
  (= 1 (do
          (swap! atomic-clock inc)
          @atomic-clock))

  "Keep taxes out of this: swapping requires no transaction"
  (= 5 (do
         (swap! atomic-clock + 4)
         @atomic-clock))

  "Any number of arguments might happen during a swap"
  (= 20 (do
          (swap! atomic-clock + 1 2 3 4 5)
          @atomic-clock))

  "Atomic atoms are atomic"
  (= 20 (do ; why?
          (compare-and-set! atomic-clock 100 :fin)
          @atomic-clock))

  "When your expectations are aligned with reality things, proceed that way"
  (= :fin (do
            (compare-and-set! atomic-clock 20 :fin) ; kind of like a ternary?
            @atomic-clock)))
